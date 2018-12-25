package com.example.a.myapplication12321321.rxjava.rx_download_zrn;

import com.example.a.myapplication12321321.base.okhttputils.OkHttpManager;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Time:2018/12/18 - 17:34
 * Author:Zhongwb
 * Description:
 */
public class RxModel implements RxContract.RxModel {

    @Override
    public Observable<byte[]> downloadFileFromBytes(final String url) {
        return Observable.create(new ObservableOnSubscribe<byte[]>() {
            @Override
            public void subscribe(final ObservableEmitter<byte[]> emitter) throws Exception {
                Request request = new Request.Builder().url(url).get().build();
                OkHttpManager.getInstance().getOkHttpClient().newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        emitter.onError(e);
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            byte[] bitmap = response.body().bytes();
                            if(bitmap != null){
                                emitter.onNext(bitmap);
                            }
                            emitter.onComplete();
                        }
                    }
                });
            }
        });
    }
}
