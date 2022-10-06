package com.lecheng.protectAnimals.controller;

import com.alibaba.fastjson.JSONObject;

import com.lecheng.protectAnimals.Oauth.OauthQQ;
import com.lecheng.protectAnimals.utils.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/login/qq")
public class OauthQqController {
    //OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中
    private static final String SESSION_STATE = "_SESSION_STATE_QQ_";
    private static Logger log = LoggerFactory.getLogger(OauthQqController.class);

    @RequestMapping("/callback")
    public String callback(HttpServletRequest request){
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        // 取消了授权
        if (StringUtils.isBlank(state)||StringUtils.isBlank(code)){
            return "取消了授权";
        }
        //清除state以防下次登录授权失败
        //session.removeAttribute(SESSION_STATE);
        //获取用户信息
        try{
            JSONObject userInfo = OauthQQ.me().getUserInfoByCode(code);
            log.error(userInfo.toString());
            String type = "qq";
            String openid = userInfo.getString("openid");
            String nickname = userInfo.getString("nickname");
            String photoUrl = userInfo.getString("figureurl_2");
            System.out.println(openid);
            System.out.println(nickname);
            System.out.println(photoUrl);
            // 将相关信息存储数据库...
            return userInfo.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        //这里你们可以自己修改，授权成功后，调到首页
        return "error";
    }

    /**
     * 构造授权请求url
     * @return void    返回类型
     * @throws
     */
    @RequestMapping("/login")
    public String index(HttpServletRequest request) throws UnsupportedEncodingException {
        //state就是一个随机数，保证安全
        String state = TokenUtil.randomState();
        try {
            String url = OauthQQ.me().getAuthorizeUrl(state);
            return "redirect:"+url;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "false";
    }
}
