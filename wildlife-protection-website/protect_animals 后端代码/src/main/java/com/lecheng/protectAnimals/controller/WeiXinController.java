package com.lecheng.protectAnimals.controller;

import com.lecheng.protectAnimals.pojo.*;
import com.lecheng.protectAnimals.service.Role_UserService;
import com.lecheng.protectAnimals.service.UserService;
import com.lecheng.protectAnimals.service.WeiXinService;
import com.lecheng.protectAnimals.utils.CreateUUID;
import com.lecheng.protectAnimals.utils.PasswordUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/weixin")
public class WeiXinController {

    @Autowired
    private WeiXinService weiXinService;

    @Autowired
    private UserService userService;

    @Autowired
    private Role_UserService role_userService;

    /**
     * 待执行微信用户任务队列
     */
    public static LinkedList<Map<String, String>> todoTaskList = new LinkedList<Map<String, String>>();
    //日志打印
    private static Logger logger = Logger.getLogger(WeiXinController.class);
    private static final String APPID = "wxfbe2f4571943b913";
    private static final String redirect_uri = "http://47.108.95.77/protect_animals/weixin/weixinAuth";

    /**
     * 拉取微信用户信息，进行授权
     *
     * @param code
     * @param uuid
     * @return
     */
    @RequestMapping("/weixinAuth")
    public synchronized ResponseMessage weixinAuth(String code, String uuid) {
        Map<String, String> weiXinUser;
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        logger.info("前端取到的code:" + code);
        logger.info("前端取到的uuid:" + uuid);
        Map<String, String> userinfo_map = weiXinService.weixinAuth(code);
        logger.info("微信授权是否成功：" + userinfo_map);
        if (userinfo_map == null) {
            return responseMessage.False("登陆失败");
        }
        weiXinUser = userinfo_map;
        String openid = userinfo_map.get("openid");
        String headimgurl = userinfo_map.get("headimgurl");
        String nickname = userinfo_map.get("nickname");
        WeiXinUser infoByOpenid = weiXinService.getInfoByOpenid(openid);
        if (infoByOpenid == null) {
            weiXinService.insert(userinfo_map);
            logger.info("数据库获取微信用户信息成功！");
        }
        User check_user = userService.getUser(nickname);
        if (check_user == null) {
            User user = new User();
            user.setUpwd(PasswordUtil.md5Hash(openid, nickname));
            user.setUname(nickname);
            user.setHead_img(headimgurl);
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!！！"+headimgurl);
            int i = userService.insertUser(user);
            userService.insertUserImg(user);
            Role_User role_user = new Role_User(user.getUserid(), 1);
            int j = role_userService.addRole_User(role_user);
            logger.info("添加微信用户角色权限成功！");
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(nickname, openid);
            subject.login(token);
            String AUTH_TOKEN = (String) subject.getSession().getId();
            User user = userService.getUser(nickname);
            int weixin_sysId = user.getUserid();
            logger.info("微信用户对应系统用户id：" + weixin_sysId);
            weiXinUser.put("userid", Integer.toString(weixin_sysId));
            weiXinUser.put("isAuth", "Success");
            weiXinUser.put("AUTH_TOKEN", AUTH_TOKEN);
            weiXinUser.put("uuid", uuid);
            todoTaskList.add(weiXinUser);
            logger.info("微信用户授权成功");
//            responseMessage.setData(userinfo_map);
            responseMessage.setCode(200);
            responseMessage.setMsg("微信登陆成功");
            return responseMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return responseMessage.False();
        }

    }

    /**
     * 检查当前用户是否授权，获取用户信息
     *
     * @return
     */
    @RequestMapping("/isAuth")
    public ResponseMessage isAuth(String uuid) {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        Map<String, String> weiXinUser = null;
        User user = null;
//        System.out.println("3待采集大小："+todoTaskList.size());
        for (Map<String, String> weixin_user : todoTaskList
        ) {
            if (weixin_user.get("isAuth").equals("Success") && weixin_user.get("uuid").equals(uuid)) {
                weiXinUser = weixin_user;
                User temp = new User();
                temp.setUserid(Integer.parseInt(weixin_user.get("userid")));
                user = userService.getUserBy(temp).get(0);
                System.out.println("当前微信用户对应user是："+user);
                todoTaskList.remove(weixin_user);
                break;
            }
        }
        System.out.println(todoTaskList.size());
        if (weiXinUser != null) {
            HashMap<Object, Object> map = new HashMap<>();
            map.put("user", user);
            map.put("AUTH_TOKEN", weiXinUser.get("AUTH_TOKEN"));
            responseMessage.setMsg("Success");
            responseMessage.setCode(200);
            responseMessage.setData(map);
            return responseMessage;
        }
        logger.error("PC端获取微信用户空指针异常");
        responseMessage.setData(null);
        responseMessage.setMsg("请再次请求");
        responseMessage.setCode(200);
        return responseMessage;
    }

    /**
     * 获取二维码携带信息
     *
     * @return
     */
    @RequestMapping("/weiXinQRCode")
    public ResponseMessage weiXinQRCode() {
        HashMap<Object, Object> map = new HashMap<>();
        String uuid = CreateUUID.create();//标识微信用户，pc获取用户信息
        String QRcode = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID + "&redirect_uri=" + redirect_uri + "?uuid=" + uuid + "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        map.put("QRcode", QRcode);
        map.put("uuid", uuid);
        responseMessage.setMsg("获取成功");
        responseMessage.setCode(200);
        responseMessage.setData(map);
        return responseMessage;
    }
}
