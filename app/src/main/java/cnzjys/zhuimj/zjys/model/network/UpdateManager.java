package cnzjys.zhuimj.zjys.model.network;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class UpdateManager {

    /**
     * 是否需要更新,需要则下载
     *
     * @param context     上下文
     * @param url         新版本地址
     * @param apkPath     本地apk保存路径
     * @param compositeDisposable          订阅关系集合，在数据传输完毕时解除订阅
     */
    public static void downloadApk(final Context context, final String url, final String apkPath,
                                   final CompositeDisposable compositeDisposable) {
        NetWork.getInstance().startUpdate(url)
                .map(new Function<ResponseBody, BufferedSource>() {
                    @Override
                    public BufferedSource apply(ResponseBody responseBody) throws Exception {
                        return responseBody.source();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<BufferedSource>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BufferedSource bufferedSource) {
                        try {
                            writeFile(bufferedSource, new File(apkPath));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        unSubscribe(compositeDisposable);
                    }

                    @Override
                    public void onComplete() {
                        installApk(context, apkPath, compositeDisposable);
                    }
                });
    }

    /**
     * 适配系统安装apk
     */
    private static void installApk(Context context, String apkPath, CompositeDisposable compositeDisposable){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data;
        File file = new File(apkPath);
        // 判断版本大于等于7.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // "cn.woodsoo.woodsootv"即是在清单文件中配置的authorities
                data = FileProvider.getUriForFile(context,
                    "cn.zhuimj.zjys", file);
            // 给目标应用一个临时授权
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }else {
            data = Uri.fromFile(file);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        }
        intent.setDataAndType(data,
                "application/vnd.android.package-archive");
        context.startActivity(intent);
        unSubscribe(compositeDisposable);
    }

    /**
     * 写入文件
     */
    private static void writeFile(BufferedSource source, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (file.exists())
            file.delete();

        BufferedSink bufferedSink = Okio.buffer(Okio.sink(file));
        bufferedSink.writeAll(source);

        bufferedSink.close();
        source.close();
    }

    /**
     * 解除订阅
     *
     * @param compositeDisposable 订阅关系集合
     */
    private static void unSubscribe(CompositeDisposable compositeDisposable) {
        if (compositeDisposable != null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}
