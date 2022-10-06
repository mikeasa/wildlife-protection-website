package com.lecheng.protectAnimals.service;

import com.lecheng.protectAnimals.pojo.WeiXinUser;

import java.util.Map;

public interface WeiXinService {
    Map weixinAuth(String code);
    WeiXinUser getInfoByOpenid(String openid);
    void insert(Map<String,String> resMap);
}
