package com.atlanticyu.loganalysisback.commo;

public class Const {

    public static final long EXPIRATION_TIME = 432_000_000;     // 5天(以毫秒ms计)
    public static final String SECRET = "AtlanticYuSecret";      // JWT加解密使用的密钥
    public static final String TOKEN_PREFIX = "Bearer";         // Token前缀,JWT负载中拿到开头
    public static final String HEADER_STRING = "Authorization"; // 存放Token的Header Key,JWT存储的请求头

}
