package com.example.administrator.rxjavademo.net;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2016/7/14 0014.
 */
public interface ApiService {
    @Headers("{CLIENT_TYPE:android}")
    @Multipart
    @PUT("refunds/{orderId}/upload_images.json")
    Observable<ResponseBody> uploadImage(@Path("orderId") String orderId, @Header("AUTHORIZATION") String token,
                                         @Header("APP_VERSION") String APP_VERSION,
                                         @PartMap Map<String, RequestBody> params);
    @Headers("{CLIENT_TYPE:android}")
    @POST("users/login.json")
    Observable<ResponseBody> loginWithPWD(@Body Map<String, String> params);

    @FormUrlEncoded
    @Headers({"Content-Type:application/x-www-form-urlencoded",
            "Authorization:Basic NjV1MEM5alFVTWs3dWZXaUJNMVlNREhHc0dBYTpadHdjbVE4VmkwNTgxZlp1V013TmQwbVBSdGdh"})
    @POST("https://devais.pfingo.com:8243/token")

    Observable<ResponseBody> getApplicationToken(/*@Header("Authorization") String base64Secert,*/
                                                     @Field("grant_type") String p);
}
