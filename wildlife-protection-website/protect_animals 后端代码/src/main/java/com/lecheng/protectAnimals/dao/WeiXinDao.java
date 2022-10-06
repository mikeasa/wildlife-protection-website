package com.lecheng.protectAnimals.dao;

import com.lecheng.protectAnimals.pojo.WeiXinUser;

import java.util.HashMap;
import java.util.Map;

public interface WeiXinDao {
    WeiXinUser getInfoByOpenid(String openid);
    void insert(Map<String,String> resMap);
}
