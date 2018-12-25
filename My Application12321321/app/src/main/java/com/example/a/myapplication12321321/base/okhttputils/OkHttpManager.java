package com.example.a.myapplication12321321.base.okhttputils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Time:2018/10/30 - 10:26
 * Author:Zhongwb
 * Description:
 */

public class OkHttpManager {


    private static OkHttpManager instance = null;

    private static OkHttpClient okHttpClient = null;

    private Gson gson;

    private Handler mHandler;

    /**
     * okHttpClient 默认基础配置初始化
     * EventBus注册
     */
    private OkHttpManager() {
        okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        okHttpClient.newBuilder().writeTimeout(10, TimeUnit.SECONDS);
        okHttpClient.newBuilder().readTimeout(10, TimeUnit.SECONDS);

        gson = new Gson();

        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * OkHttpManager 单例模式
     *
     * @return
     */
    public static OkHttpManager getInstance() {
        if (instance == null) {
            synchronized (OkHttpManager.class) {
                if (instance == null) {
                    instance = new OkHttpManager();
                }

            }
        }
        return instance;
    }

    /**
     * 获取OkHttpClient用来封装Retrofit
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * 异步Get方式请求。
     *
     * @param url
     * @param callback
     */
    private void _getAsyn(String url, final RequestCallback callback) {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        deliveryResult(callback, request);
    }

    /**
     * 异步POST请求
     *
     * @param url
     * @param callback
     * @param params
     */
    private void _postAsyn(String url, final RequestCallback callback, Param... params) {
        Request request = buildPostRequest(url, params);
        deliveryResult(callback, request);
    }

    /**
     * 异步POST请求，Map传值转Params
     *
     * @param url
     * @param callback
     * @param params
     */
    private void _postAsyn(String url, final RequestCallback callback, Map<String, String> params) {
        Param[] paramsArry = mapToParams(params);
        Request request = buildPostRequest(url, paramsArry);
        deliveryResult(callback, request);
    }


    /**
     * Map转参数请求数组
     *
     * @param params
     * @return
     */
    private Param[] mapToParams(Map<String, String> params) {
        if (params == null) {
            return new Param[0];
        } else {
            int size = params.size();
            Param[] paramsArry = new Param[size];
            Set<Map.Entry<String, String>> entries = params.entrySet();
            int i = 0;
            for (Map.Entry<String, String> entry : entries) {
                paramsArry[i++] = new Param(entry.getKey(), entry.getValue());
            }
            return paramsArry;
        }
    }


    /**
     * 构建POST方法请求体。
     *
     * @param url
     * @param params
     * @return
     */
    private Request buildPostRequest(String url, Param[] params) {
        if (params == null) {
            params = new Param[0];
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Param param : params) {
            builder.add(param.key, param.value);
        }

        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    /**
     * 同步基于POST的文件上传
     *
     * @param url
     * @param files
     * @param fileKeys
     * @param params
     * @return
     * @throws IOException
     */
    private Response _post(String url, File[] files, String[] fileKeys, LoadListener listener, Param... params) throws IOException {

        Request request = buildMultiPartFormRequest(url, files, fileKeys, params, listener);

        return okHttpClient.newCall(request).execute();

    }

    private Response _post(String url, File file, String fileKeys, LoadListener listener) throws IOException {

        Request request = buildMultiPartFormRequest(url, new File[]{file}, new String[]{fileKeys}, null, listener);
        return okHttpClient.newCall(request).execute();

    }

    private Response _post(String url, File file, String fileKeys, LoadListener listener, Param... params) throws IOException {

        Request request = buildMultiPartFormRequest(url, new File[]{file}, new String[]{fileKeys}, params, listener);
        return okHttpClient.newCall(request).execute();
    }

    /**
     * 异步基于POST的文件上传
     *
     * @param url
     * @param callback
     * @param files
     * @param fileKeys
     * @param parms
     */
    private void _postAsyn(String url, RequestCallback callback, File[] files, String[] fileKeys, LoadListener listener, Param... parms) {
        Request request = buildMultiPartFormRequest(url, files, fileKeys, parms, listener);
        deliveryResult(callback, request);
    }

    /**
     * 异步基于POST的单文件上传
     *
     * @param url
     * @param callback
     * @param files
     * @param fileKeys
     */
    private void _postAsyn(String url, RequestCallback callback, File files, String fileKeys, LoadListener listener) {
        Request request = buildMultiPartFormRequest(url, new File[]{files}, new String[]{fileKeys}, null, listener);
        deliveryResult(callback, request);
    }

    /**
     * 异步基于POST单文件携带其它Form参数上传
     *
     * @param url
     * @param callback
     * @param file
     * @param fileKey
     * @param params
     */
    private void _postAsyn(String url, RequestCallback callback, File file, String fileKey, LoadListener listener, Param... params) {
        Request request = buildMultiPartFormRequest(url, new File[]{file}, new String[]{fileKey}, params, listener);
        deliveryResult(callback, request);
    }

    /**
     * 异步下载文件
     *
     * @param url
     * @param destFileDir 本地保存文件的目标文件夹
     * @param callback
     */
    private void _downloadAsyn(final String url, final String destFileDir, final RequestCallback callback, final LoadListener percentlistener) {

        final Request request = new Request.Builder()
                .url(url)
                .build();

        if(percentlistener != null){
            final ProgressResponseBody.ProgressListener listener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead,  long contentLength,  Long totalBytesRead, boolean done) {
                    //计算百分比并更新ProgressBar
                    final int percent = (int) (100 * bytesRead / contentLength);
                    if (percentlistener != null) {
                        percentlistener.onloading(new Speed(percent,
                                String.format("%2.2f", ((float) totalBytesRead / 1048576.0)) + "M/"
                                        + String.format("%.2f", ((float) contentLength / 1048576.0)) + "M"));
                    }
                }
            };

            okHttpClient = okHttpClient.newBuilder().addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    //这里将ResponseBody包装成我们的ProgressResponseBody
                    return response.newBuilder()
                            .body(new ProgressResponseBody(response.body(), listener))
                            .build();
                }
            }).build();
        }

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream in = null;
                byte[] buf = new byte[1024];
                int len = 0;
                FileOutputStream out = null;
                try {
                    in = response.body().byteStream();
                    File file = new File(destFileDir, getFileName(url));
                    Log.i("FileLocation",file.toString());
                    out = new FileOutputStream(file);
                    while ((len = in.read(buf)) != -1) {
                        out.write(buf, 0, len);
                    }
                    out.flush();
                    sendSuccessResultCallback(file.getAbsoluteFile(), callback);
                } catch (Exception e) {
                    sendFailedStringCallback(response.request(), e, callback);
                } finally {
                    try {
                        if (in != null) {
                            in.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void _downloadStreamAsyn(final String url,final RequestCallback callback, final LoadListener percentlistener){
        final Request request = new Request.Builder()
                .url(url)
                .build();
        if(percentlistener != null){
            final ProgressResponseBody.ProgressListener listener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead,  long contentLength,  Long totalBytesRead, boolean done) {
                    //计算百分比并更新ProgressBar
                    final int percent = (int) (100 * bytesRead / contentLength);
                    if (percentlistener != null) {
                        percentlistener.onloading(new Speed(percent,
                                String.format("%2.2f", ((float) totalBytesRead / 1048576.0)) + "M/"
                                        + String.format("%.2f", ((float) contentLength / 1048576.0)) + "M"));
                    }
                }
            };

            okHttpClient = okHttpClient.newBuilder().addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    //这里将ResponseBody包装成我们的ProgressResponseBody
                    return response.newBuilder()
                            .body(new ProgressResponseBody(response.body(), listener))
                            .build();
                }
            }).build();
        }

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] bytes = null;
                try {
                    bytes = response.body().bytes();
                    sendSuccessResultCallback(bytes, callback);
                } catch (Exception e) {
                    sendFailedStringCallback(response.request(), e, callback);
                }
            }
        });
    }

    private String getFileName(String path) {
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }

    /**
     * 构建文件上传的请求
     *
     * @param url
     * @param files
     * @param fileKeys
     * @param params
     * @param percentlistener 上传进度回调函数
     * @return
     */
    private Request buildMultiPartFormRequest(String url, File[] files, String[] fileKeys, Param[] params, final LoadListener percentlistener) {

        params = validateParam(params);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (Param param : params) {
            builder.addPart(Headers.of("Content-Disposition", "form-data;name=\"" + param.key + "\"")
                    , RequestBody.create(null, param.value));
        }
        if (files != null) {
            RequestBody fileBody = null;
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String filename = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(filename)), file);
                builder.addPart(Headers.of("Content-Disposition", "form-data;name=\"" + fileKeys[i] + "\";filename=\"" + filename + "\"")
                        , fileBody);
            }
        }
        CountingRequestBody requestBody = new CountingRequestBody(builder.build(), new CountingRequestBody.onBackListener() {
            @Override
            public void onRequestProgress(long byteWritted, long contentLength) {
                final int percent = (int) (100 * byteWritted / contentLength);
                if (percentlistener != null) {
                    percentlistener.onloading(new Speed(percent, String.format("%2.2f", (float) byteWritted / 1048576.0)
                            + "M/" + String.format("%2.2f", (float) contentLength / 1048576.0) + "M"));
                }
            }
        });
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }


    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }


    private Param[] validateParam(Param[] params) {
        if (params == null) {
            return new Param[0];
        } else {
            return params;
        }
    }

    /**
     * OkhttpClient发起请求,并将结果通过接口回调。
     *
     * @param callback
     * @param request
     */
    private void deliveryResult(final RequestCallback callback, final Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(request, e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {

                    if (callback.mType == String.class) {
                        String temp = response.body().string();
                        sendSuccessResultCallback(temp, callback);
                    }else if(callback.mType == Response.class){
                        sendSuccessResultCallback(response,callback);
                    }else{
                        String temp = response.body().string();
                        Object o = gson.fromJson(temp, callback.mType);
                        sendSuccessResultCallback(o, callback);
                    }
                } catch (Exception e) {
                    sendFailedStringCallback(response.request(), e, callback);
                }
            }
        });
    }

    /**
     * 失败回调方法
     *
     * @param request
     * @param e
     * @param callback
     */
    private void sendFailedStringCallback(final Request request, final Exception e, final RequestCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null && mHandler != null) {
                    callback.onError(request, e);
                }
            }
        });
    }

    /**
     * 请求成功方法
     *
     * @param object
     * @param callback
     */
    private void sendSuccessResultCallback(final Object object, final RequestCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null && mHandler != null) {
                    callback.onResponse(object);
                }
            }
        });
    }

    /**
     * Post参数
     */
    public static class Param {
        public Param() {
        }

        public Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }


    /**
     ****************** 对外公开的方法 ****************
     */

    /**
     * 异步的GET请求
     *
     * @param url
     * @param callback
     */
    public static void getAsyn(String url, RequestCallback callback) {
        getInstance()._getAsyn(url, callback);
    }

    /**
     * 异步的POST请求
     *
     * @param url
     * @param callback
     * @param params
     */
    public static void postAsyn(String url, RequestCallback callback, Param... params) {
        getInstance()._postAsyn(url, callback, params);
    }

    public static void postAsyn(String url, RequestCallback callback, Map<String, String> map) {
        getInstance()._postAsyn(url, callback, map);
    }

    public static Response post(String url, File file, String fileKey, LoadListener listener) throws IOException {
        return getInstance()._post(url, file, fileKey, listener);
    }

    public static Response post(String url, File file, String fileKey, LoadListener listener, Param... params) throws IOException {
        return getInstance()._post(url, file, fileKey, listener, params);
    }

    public static Response post(String url, File[] files, String[] fileKeys, LoadListener listener, Param... params) throws IOException {
        return getInstance()._post(url, files, fileKeys, listener, params);
    }

    public static void postAsyn(String url, RequestCallback callback, File[] files, String[] fileKeys, LoadListener listener, Param... params) {
        getInstance()._postAsyn(url, callback, files, fileKeys, listener, params);
    }

    public static void postAsyn(String url, RequestCallback callback, File file, String fileKey, LoadListener listener, Param... params) {
        getInstance()._postAsyn(url, callback, file, fileKey, listener, params);
    }

    public static void postAsyn(String url, RequestCallback callback, File file, String fileKey, LoadListener listener) {
        getInstance()._postAsyn(url, callback, file, fileKey, listener);
    }

    public static void downloadAsyn(String url, String destDir, RequestCallback callback, LoadListener loadListener) {
        getInstance()._downloadAsyn(url, destDir, callback, loadListener);
    }

    public static void downloadStreamAsyn(String url, RequestCallback callback, LoadListener loadListener){
        getInstance()._downloadStreamAsyn(url,callback, loadListener);
    }

    public interface LoadListener {

        /**
         * 进度
         *
         * @param speed
         */
        void onloading(Speed speed);

    }
}
