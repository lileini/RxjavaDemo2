package com.example.administrator.rxjavademo.net;

public class Net {
        public static final String HOST_URL = "http://pre-api.zangcun.com:81/";//无图地址
//    public static final String HOST_URL = "http://api.zangcun.com:81/";
    //        public static final String HOST_URL = "http://192.168.100.9:3000/";//地址
    public static final String IMAG = "http://backend.zangcun.com:81/";//图片地址

    public final static String URL_SPECIAAL = HOST_URL + "topic_web/topic_1/index.html"; //专题
    public final static String URL_SENDMESSGE = HOST_URL + "auth_tokens/get_code.json"; // 获取手机验证码
    public final static String URL_CODE = HOST_URL + "auth_tokens/auth_phone.json";//验证服务器发送的验证码
    public final static String URL_AUTH_TOKEN = HOST_URL + "users/login.json";//用户登录
    public final static String URL_USER = HOST_URL + "users.json";//POST马上注册
    public final static String URL_NEW_PASSWORD = HOST_URL + "users/new_password.json"; //PUT重置密码
    public final static String URL_RESET_PASSWORD = HOST_URL + "users/reset_password.json"; //PUT修改密码
    public static final String URL_CARTS = HOST_URL + "carts.json";//购物车商品列表,Get请求
    public static final String URL_GOODS_CARTS = HOST_URL + "carts.json";//POST请求添加至购物车
    public static final String URL_ADDRESSES = HOST_URL + "addresses/regions.json";//收货地址，登录之后带上Token请求
    public static final String URL_ADD_ADDRESSES = HOST_URL + "addresses.json";//添加收货地址
    public static final String URL_GET_ADDRESSES = HOST_URL + "users.json";//获取收货地址
    public static final String URL_SORT = HOST_URL + "categories/base_cate.json";//获取基础的分类
    public static final String URL_GET_GOODS = HOST_URL + "goods.json";
    public static final String URL_WAIT_FOR_PAY = HOST_URL + "orders/waiting_for_pay.json";//待付款
    public static final String URL_WAIT_FOR_SHIP = HOST_URL + "orders/waiting_for_send.json";//待发货
    public static final String URL_WAIT_FOR_RECEIVE = HOST_URL + "orders/waiting_for_receive.json";//待收货
    public static final String URL_CEAT_ORDER = HOST_URL + "carts/commit.json";//创建订单
    public static final String URL_GET_ORDER = HOST_URL + "orders.json";//创建订单
    public final static String URL_COLLECT = HOST_URL + "collects.json";//POST收藏商品
    public final static String URL_COLLECT_LIEBIAO = HOST_URL + "collects.json";//GET获取收藏商品列表

    public final static String URL_SHOP_PAY = HOST_URL + "orders.json";//POT直接购买

    public final static String SHOP_CAR_RECIEVER = "shop_car_reciever";

    public final static String URL_AUTH_LOGIN = HOST_URL + "oauth/login.json";//post请求三方登录
    public final static String URL_AUTH_BIND = HOST_URL + "oauth/bind.json";//post请求三方绑定



}
