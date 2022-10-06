package com.lecheng.protectAnimals.service.impl;

import com.lecheng.protectAnimals.dao.WeiXinDao;
import com.lecheng.protectAnimals.pojo.WeiXinUser;
import com.lecheng.protectAnimals.service.WeiXinService;
import com.lecheng.protectAnimals.utils.WeiXinAuthUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class WeiXinServiceImpl implements WeiXinService {
    @Autowired
    private WeiXinDao weiXinDao;
    //日志打印
    private static Logger logger = Logger.getLogger(WeiXinServiceImpl.class);
    @Override
    public Map weixinAuth(String code){
        //获取access_token
        Map<String, String> accesstoken_map = WeiXinAuthUtil.get_access_token_by_code(code);
        //拉取用户信息
        Map<String, String> userinfo_map = WeiXinAuthUtil.get_userinfo_by_token(accesstoken_map.get("access_token"), accesstoken_map.get("openid"));
        if (userinfo_map.get("errcode") != null) {
          logger.info("拉取用户信息:" + userinfo_map.get("errmsg"));
            //刷新access_token
            Map<String, String> access_token_by_refresh_tokenMap = WeiXinAuthUtil.get_access_token_by_refresh_token(accesstoken_map.get("refresh_token"));
            // 再次拉取用户信息
            userinfo_map = WeiXinAuthUtil.get_userinfo_by_token(access_token_by_refresh_tokenMap.get("access_token"), access_token_by_refresh_tokenMap.get("openid"));
        }
       logger.info("用户具体信息："+userinfo_map.toString());
        String openid = userinfo_map.get("openid");
        if (openid == null) {
            logger.info("用户不存在");
            return null;
        }else {
            return userinfo_map;
        }
    }

    @Override
    public WeiXinUser getInfoByOpenid(String openid) {
        try {
            return weiXinDao.getInfoByOpenid(openid);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void insert(Map<String, String> resMap) {
        try {
            weiXinDao.insert(resMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
