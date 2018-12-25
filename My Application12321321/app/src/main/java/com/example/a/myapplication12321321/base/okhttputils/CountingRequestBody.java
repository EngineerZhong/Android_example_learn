package com.example.a.myapplication12321321.base.okhttputils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Time:2018/10/31 - 10:39
 * Author:Zhongwb
 * Description:
 */
public class CountingRequestBody extends RequestBody {

    private RequestBody requestBody;
    private onBackListener listener;

    private CountingSink countingSink;


    public CountingRequestBody(RequestBody requestBody, onBackListener listener) {
        this.requestBody = requestBody;
        this.listener = listener;
    }

    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public long contentLength() {
        try {
            return requestBody.contentLength();
        } catch (IOException e) {
            return -1;
        }
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        countingSink = new CountingSink(sink);
        BufferedSink bufferedSink = Okio.buffer(countingSink);
        requestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }


    protected class CountingSink extends ForwardingSink{
        private long byteWritten = 0;

        public CountingSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            byteWritten += byteCount;
            listener.onRequestProgress(byteWritten,requestBody.contentLength());
        }
    }


    /**
     * 回调监听接口
     */
    public static interface onBackListener{
        /**
         * 暴露出上传进度
         * @param byteWritted  已经上传的字节大小
         * @param contentLength 文件的总字节大小
         */
        void onRequestProgress(long byteWritted, long contentLength);
    }

}
