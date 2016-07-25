package com.example.administrator.rxjavademo.util;


import android.util.Base64;
import android.util.Log;

import com.example.administrator.rxjavademo.BuildConfig;
import com.example.administrator.rxjavademo.net.ApiService;
import com.example.administrator.rxjavademo.net.MyHostnameVerify;
import com.example.administrator.rxjavademo.net.Net;
import com.example.administrator.rxjavademo.net.SSLContextUtils;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class HttpMethods {
    private String version = "1.0.1";
    private final ApiService apiService;
    private Retrofit retrofit;
    private HttpMethods(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(8, TimeUnit.SECONDS)
            .sslSocketFactory(SSLContextUtils.getSSLContext().getSocketFactory())

            .hostnameVerifier(new MyHostnameVerify());
        if(BuildConfig.DEBUG){
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
       retrofit = new Retrofit.Builder()
               .baseUrl(Net.AIS_BASE_URL)
               .client(builder.build())
//               .addConverterFactory(Con)
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
               .build();
        apiService = retrofit.create(ApiService.class);

    }
    private static class SingletonHolder{
        private final static HttpMethods instance = new HttpMethods();
    }
    public static HttpMethods getInstance(){
        return SingletonHolder.instance;
    }
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .throttleFirst(3,TimeUnit.SECONDS)//3s 内只能执行一次
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
//     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    /*private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCount() == 0) {
                throw new ApiException(100);
            }
            return httpResult.getSubjects();
        }
    }*/

    /**
     * 上传图片
     * @param subscriber
     * @param orderId
     * @param token
     * @param params
     */
    public void upLoadImge(Subscriber<ResponseBody> subscriber, String orderId, String token, Map<String, RequestBody> params){
        toSubscribe(apiService.uploadImage(orderId, token, version, params),subscriber);
    }

    public void loginWithPwd(Subscriber<ResponseBody> subscriber,Map<String,String> params){
        toSubscribe(apiService.loginWithPWD(params),subscriber);
    }

    public Observable<ResponseBody> requestApplicationToken(){
        Map<String,String> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("","");
        String base64Sercet= "Basic " + Base64.encodeToString(Net.AIS_BASE64.getBytes(),Base64.DEFAULT);
        Log.i("request",base64Sercet);
//        return apiService.getApplicationToken(base64Sercet,params);
        return apiService.getApplicationToken(/*base64Sercet,*/"client_credentials");/*.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("onResponse",response.message());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtil.e("onFailure",t.getMessage());
                t.printStackTrace();
            }
        });*/

    }
}
