package com.example.administrator.rxjavademo.net;

public class Net {
    public static final String AIS_BASE_URL="https://devais.pfingo.com:8243/";
    //    public static final String AIS_BASE_URL="https://devotp.pfingo.com:8243";
    public static final String AIS_REQUEST_OTP_URL=AIS_BASE_URL+"CoreApi/v1/otp";
    public static final String AIS_BASE64="65u0C9jQUMk7ufWiBM1YMDHGsGAa:ZtwcmQ8Vi0581fZuWMwNd0mPRtga";
    //    public static final String AIS_BASE64_2="isV7O76F9M48TiXtwqf08AnEHPUa:e1JC33t2xiRGfu0U02F2WbupqLka";
    public static final String AIS_REQUEST_TOKEN=AIS_BASE_URL+"token";
    public static final String AIS_QUERY_CONSUMPTION=AIS_BASE_URL+"CoreApi/v1/consumption";
    public static final String AIS_CALLHISTORY=AIS_BASE_URL+"CoreApi/v1/accounts/cdr";
    public static final String AIS_ACCOUNT_VALIDATION=AIS_BASE_URL+"CoreApi/v1/accounts/validation";
    public static final String AIS_ACCOUNT_LOGIN_STATUS=AIS_BASE_URL+"CoreApi/v1/accounts/login";
    public static final String AIS_PUSH_TOKEN_CREATION=AIS_BASE_URL+"CoreApi/v1/tokens";

}
