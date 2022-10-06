package com.lecheng.protectAnimals.utils;

import java.util.Map;
import com.google.gson.Gson;

public class WeiXinAuthUtil {
    private static final String APPID = "wxfbe2f4571943b913";
    private static final String SECRET = "eeb52720efb2d683f2549952f6e320a6";

    /**
     * 通过code换取网页授权access_token
     */
    public static Map<String, String> get_access_token_by_code(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID +
                "&secret=" + SECRET +
                "&grant_type=authorization_code&code=" + code;
        String res = URLUtils.sendGet(url);

        Map<String, String> resMap = new Gson().fromJson(res, Map.class);

        System.out.println("通过code换取网页授权access_token 返回结果: " + resMap);

        return resMap;
    }

    /**
     * 拉取用户信息
     */
    public static Map<String, String> get_userinfo_by_token(String access_token,String openid) {
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token +
                "&openid=" + openid +
                "&lang=zh_CN";
        String res = URLUtils.sendGet(url);

        Map<String, String> resMap = new Gson().fromJson(res, Map.class);

        System.out.println("拉取用户信息 返回结果: " + resMap);

        return resMap;
    }

    /**
     * 刷新access_token（如果需要）
     * 由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，
     * refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。
     */
    public static Map<String, String> get_access_token_by_refresh_token(String refresh_token) {

        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + APPID +
                "&grant_type=refresh_token&refresh_token=" + refresh_token;
        String res = URLUtils.sendGet(url);

        Map<String, String> resMap = new Gson().fromJson(res, Map.class);

        System.out.println("刷新access_token 返回结果" + resMap);

        return resMap;
    }
}
