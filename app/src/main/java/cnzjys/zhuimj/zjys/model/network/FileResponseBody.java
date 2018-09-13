package cnzjys.zhuimj.zjys.model.network;

import android.util.Log;

import java.io.IOException;

import cnzjys.zhuimj.zjys.model.bean.DownLoadBean;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;


public class FileResponseBody extends ResponseBody {

    private Response originalResponse;

    FileResponseBody(Response originalResponse) {
        this.originalResponse = originalResponse;
    }


    @Override
    public MediaType contentType() {
        return originalResponse.body().contentType();
    }

    @Override
    public long contentLength() {// 返回文件的总长度，也就是进度条的max
        return originalResponse.body().contentLength();
    }

    @Override
    public BufferedSource source() {
        return Okio.buffer(new ForwardingSource(originalResponse.body().source()) {
            long bytesRead = 0;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long br = super.read(sink, byteCount);
                bytesRead += br == -1 ? 0 : br;
                Log.i("WXX", "read: "+bytesRead);
                // 通过RxBus发布进度信息
                RxBus.getDefault().send(new DownLoadBean(contentLength(), bytesRead));

                return br;
            }
        });
    }
}
