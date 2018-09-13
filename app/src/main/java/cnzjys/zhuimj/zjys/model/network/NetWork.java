package cnzjys.zhuimj.zjys.model.network;


import java.io.IOException;
import java.util.concurrent.TimeUnit;
import cnzjys.zhuimj.zjys.constant.Constant;
import cnzjys.zhuimj.zjys.model.NetWorkService;
import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWork {

    private volatile static NetWork instance;

    public static NetWork getInstance(){
        if (instance  == null){
            synchronized (NetWork.class){
                if (instance == null){
                    instance = new NetWork();
                }
            }
        }
        return instance;
    }


    public NetWorkService startNetWork(){
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(Constant.TIME_OUT, TimeUnit.SECONDS);
        okHttpClient.readTimeout(Constant.TIME_OUT, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(Constant.TIME_OUT, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient.build())
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(NetWorkService.class);
    }


    public NetWorkService upLoadFile(){
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(Constant.TIME_OUT, TimeUnit.SECONDS);
        okHttpClient.readTimeout(Constant.TIME_OUT, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(Constant.TIME_OUT, TimeUnit.SECONDS);


        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient.build())
                .baseUrl(Constant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())

                .build();

        return retrofit.create(NetWorkService.class);
    }

    public Observable<ResponseBody> startUpdate(String url){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Constant.TIME_OUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());
                        return originalResponse
                                .newBuilder()
                                .body(new FileResponseBody(originalResponse))//将自定义的ResposeBody设置给它
                                .build();
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        NetWorkService downLoadService = retrofit.create(NetWorkService.class);
        return downLoadService.updateApp(url);
    }
}
