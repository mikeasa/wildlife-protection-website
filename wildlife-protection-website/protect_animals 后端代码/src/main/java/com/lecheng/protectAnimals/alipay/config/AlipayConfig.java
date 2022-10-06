package com.lecheng.protectAnimals.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000117628185";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCRrNf2OK6LedoZe9ajyw+Tqt2a7udGpevbtSrr8qQvONYGPLUMrlsXFAoPcAcNefxQsLdW6ySiQHQh/+r4Bl073Cd9km5dzVSrtmgPNA/JbYm4lfd0nIcU7NqKYDm1JPNzoH2e5KTzXE4Q/otGErJXFKX+bwV97WF9UuJ3ishv8xBGbC6b0venO5dGFBXFApl8XUqvpWbffbG9mC89xv2CUuhspsMlmKMzUQWOvcJkuqOVZuJM6OPrI6cw8qIjmhPGU7GSzTfBgqxHQIhI6msyTweAP+4Z7g7sv0dBeYUoKDZGFDCBO1HZ8hTAbJv/oHbjPcf5TiYzkRUisAeQqcQfAgMBAAECggEAYjNlJgtRxp4u4yzzJVlWt9HgL3hLGfnxxBsdww6/lUG4LV4xIkpLB4Svv3ZimrgX2gE2pnY/LM1MPDs7l1Ua9qZTTLgwhDVNla+R+GW9GqknxPH7bXWzLez46X0yiaVOJC0VYc56T79ytMwS8PrblA5MU/ddeWVTads85R7+PVBTNiva9b5gz9sgNNNLpCLT98sUH6wEsD2C+JncnSiQ19x90oEAeQ3hiC8KvcXEmPyL9FuCu86Zb9VahSzyPszRnKlcYC7dkHGtarOXeJchgp9Pn9g+aPNKhq8DAiE+h+tNvV4s5VNpo1fH0WKTWiD+YGQNH59XGrIQCwqlQSyf0QKBgQDegzIxyt+kfTi/qHsDGtLGCvmPuJ70XhSnIVedmCJScKMR9Yz9cF9u3cWxyvnhH35Heh/k89QMlVya1nyRAiYHgY0ZtmlNhzX8wy5GdjU4w3hn2VlwhfdDGRK7JurKS4o2jioxp48Mkpwnxba59Gead6qyDWPNntm8SXn97+rbKQKBgQCnmVBQPNsQvrT32HNsMd1nIGLv3Azq82OcVR/W+T8udBy1bKXxqlzAPp4RQVlHu211NZf97+s+/IwtxI7KQh95JYURS5xQug1IJMjj/WwTAp6Kv/lxd1Qt6Y6gLmWVmfxmnXw+fVQJTNIabOz8RZ5AjH897e3jWxUBv8ZJcG5WBwKBgQCgvWfSPnrmiasoEN6kNIlUlXMI1tbFMv16aoSvjMomgcXbYGY0Yywdm8mHkViHv0dq/RbzPwzM9k0Kca1U15d/TqAOlg5l+ioO6TSRpCz4T4S4iHOq7KvdZcx5NDjiYEV8+aATdLbK31I9DP/eP2dkV9QQgcm6LWmfMfkVb25aWQKBgQCIOHlprfzT4F1QcPJnhyQQFOe1UuKA+LAGub12SgGLbT6ESzmZccGPU5XAKqA5SGKP+K5/7EgFWtlIvdDdHd9c5R0B0LXNe1sePx+1Bx6XWPKpjZ1eFwIpiwKsuOlCLfVTJj98vvrV0EWYyt3XsXINvuQQ3tkSiM5LLKcqu6JCcwKBgQCOcqHFKQu+LhTo1EUkI4+v/cnS6ddeuExCl8VBE+PtYT0HC3htf8h5KcoQ7j87WPoMg20R4Yrk2dOdmTWiG/p/Uue7jTzx8rDhEMX/gZutSe6HGgY+SMUhBnKifSdVjBaMhbm2bNJOpwhPPNlz0JidumhvCD5fx/X9lqGMSt0tWw==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh3mx+mylsSCErQxLNK33OPqQx3ucsRUUOA19gPY1OKH2vYxihiNQ35Z8zWldC2H0SAZIn3lG21xJR3+9cIMVktNTgof388+1+X2R16zZz53sTzdfM9Y1DvxV5+BScQXyqYbhpzVUpL1FiyfgRxVj1YExfluTKwlCasa+haPjm3KYDXY4SILIYwjVKS+Q5dcz9o1txGNdpXnx6iZD5ycsn+Vx6g/ruMoZmCYcoeZoVkZWJr57MfwIpeKAWY8LUI7dENx8uRbiV7uj234R2epr6kRPB4pJr6sZ0ESUeyge+cpbf+aRsZD7sxh4YR+N8Ghm+NKyLrrrAbERWcLVRAKjBwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://47.108.95.77/protect_animals/util/notify_ur";
//	public static String notify_url = "http://localhost:8080/util/notify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://47.108.95.77/protect_animals/util/return_url";
//	public static String return_url = "http://localhost:8080/util/return_url";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

