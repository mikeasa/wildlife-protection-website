package com.lecheng.protectAnimals.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordUtil {
//    MD5加密
    public static String md5Hash(String password,String salt){
        return new Md5Hash(password,salt,2).toString();
    }
//    base64加密
    public static String encBase64(String password){
        return Base64.encodeToString(password.getBytes());
    }
//    base64解密
    public static  String decBase64(String str){
        return Base64.decodeToString(str);
    }

}
