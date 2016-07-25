package com.example.administrator.rxjavademo.net;

import com.example.administrator.rxjavademo.util.LogUtil;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 *  Created by globalroam on 2015/9/17.
 */
public class MyHostnameVerify implements HostnameVerifier{

    private static final String TAG = "MyHostnameVerify";

    @Override
    public boolean verify(String hostname, SSLSession session) {

        if(hostname != null){
            if( hostname.equals("dev.api.pfingo.com")){
                LogUtil.d(TAG, "The environment is not Staging");
            }else{
                LogUtil.d(TAG,"The environment is  Staging");
            }
        }
        return true;
    }
}
