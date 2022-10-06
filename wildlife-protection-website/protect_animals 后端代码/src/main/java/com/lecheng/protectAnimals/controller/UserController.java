package com.lecheng.protectAnimals.controller;


import com.lecheng.protectAnimals.pojo.*;
import com.lecheng.protectAnimals.service.Role_UserService;
import com.lecheng.protectAnimals.service.UserService;
import com.lecheng.protectAnimals.utils.PasswordUtil;
import com.lecheng.protectAnimals.utils.RandomUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
    private UserService userService;
@Autowired
    private Role_UserService role_userService;
    //日志打印
    private static Logger logger = Logger.getLogger(UserController.class);
    //加密的字符串,相当于签名
    private static final String SINGNATURE_TOKEN = "chunxiadong";
    /**
     * 登录验证
     * @param user
     * @return
     */
    @RequestMapping("/login")
public ResponseMessage login(User user, ServletRequest request){
        String token= WebUtils.toHttp(request).getHeader("token");
        logger.info("user:"+user.getUname()+user.getUpwd());
        logger.info("token:"+token);
    ResponseMessage<Object> responseMessage = new ResponseMessage<>();
    if (token==null){
        //得到当前登录主体
        Subject subject = SecurityUtils.getSubject();
        //创建口令
        UsernamePasswordToken shirotoken = new UsernamePasswordToken(user.getUname(),user.getUpwd());
        try {
            //登录
            subject.login(shirotoken);
            LoginUserVo loginUserVo = (LoginUserVo) SecurityUtils.getSubject().getPrincipal();
            logger.info("user.getUpwd():"+user.getUpwd()+" user.getUname():"+user.getUname());
            //sessionManager实现
            String AUTH_TOKEN = (String) subject.getSession().getId();
            HashMap<Object, Object> map = new HashMap<>();
            logger.info("加密密码:"+ PasswordUtil.md5Hash(user.getUpwd(),user.getUname()));
            map.put("token",PasswordUtil.md5Hash(
                    PasswordUtil.md5Hash(user.getUpwd(),user.getUname())+user.getUname(),SINGNATURE_TOKEN));
            map.put("user",loginUserVo.getUser());
            map.put("AUTH_TOKEN",AUTH_TOKEN);
            responseMessage.setData(map);
            responseMessage.setCode(200);
            responseMessage.setMsg("登录成功");
            return responseMessage;
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        //登录失败
        return responseMessage.False();
    }else {
            LoginUserVo loginUserVo = (LoginUserVo) SecurityUtils.getSubject().getPrincipal();
           logger.info("loginUserVo.getUser().getUpwd()"+loginUserVo.getUser().getUpwd()+" loginUserVo.getUser().getUname():"+loginUserVo.getUser().getUname());
            String encryptionKey =
                    PasswordUtil.md5Hash(loginUserVo.getUser().getUpwd() + loginUserVo.getUser().getUname(), SINGNATURE_TOKEN);
            logger.info(encryptionKey);
            if (token.equals(encryptionKey)) {
                HashMap<Object, Object> map = new HashMap<>();
                map.put("token", token);
                map.put("user",loginUserVo.getUser());
                responseMessage.setData(map);
                responseMessage.setCode(200);
                responseMessage.setMsg("登录成功");
                return responseMessage;
            }else  return responseMessage.False();//登录失败
        }
    }

    /**
     * 注册
     * @param user
     * @return
     * @throws IOException
     */
    @RequestMapping("/register")
public ResponseMessage register(User user){
    ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            user.setUpwd( PasswordUtil.md5Hash(user.getUpwd(),user.getUname()));
            int i = userService.insertUser(user);
            Role_User role_user = new Role_User(user.getUserid(),1);
            int j=role_userService.addRole_User(role_user);
            HashMap<Object, Object> map = new HashMap<>();
            map.put("user", userService.getUserBy(user).get(0));
            responseMessage.setData(map);
            responseMessage.setCode(200);
            responseMessage.setMsg("success");
            return responseMessage;
        } catch (Exception e) {
            logger.info("err!");
            return responseMessage.False();
        }
    }

    /**
     * 未授权
     * @return
     */
    @RequestMapping("/unauthorized")
    public ResponseMessage unauthorized(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        return responseMessage.Unauthorized();
    }

    /**
     * 未登录
     * @return
     */
    @RequestMapping("/unlogin")
    public ResponseMessage unlogin(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        return responseMessage.unLogin();
    }
    /**
     * 登出
     */
    @RequestMapping("/logout")
    public ResponseMessage logout(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return responseMessage.Success();
        } catch (Exception e) {
            return responseMessage.False();
        }
    }
    /**
     * 添加推荐词
     * @return

     */
    @RequestMapping("/addRecommend")
    public ResponseMessage addRecommend(String uchoice, Integer userid){
        logger.info("用户选择的推荐："+uchoice);
        logger.info("用户ID："+userid);
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        Recommend recommend = new Recommend();
        recommend.setRecommend(uchoice);
        recommend.setUserid(userid);
        try {
            userService.insertRecommend(recommend);
            return responseMessage.Success();
        } catch (Exception e) {
            return responseMessage.False();
        }
    }

    /* 获取校验码 */
    @RequestMapping("/getVerifyCode")
    public ResponseMessage generate(HttpServletResponse response, HttpSession session){
        logger.info("获取校验码");
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String verifyCodeValue = drawImg(output);
        Map<String, String> map = new HashMap<>();
        map.put("verifyCode",verifyCodeValue);
        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
            responseMessage.setCode(200);
            responseMessage.setMsg("Success");
            responseMessage.setData(map);
            return responseMessage;
        } catch (IOException e) {
           logger.info("<--验证码前端写出.出现异常-->");
            e.printStackTrace();
            return responseMessage.False();
        }
    }
    /* 绘制验证码 */
    private String drawImg(ByteArrayOutputStream output) {
        logger.info("绘制验证码");
        String code = "";
        // 随机产生4个字符
        code=RandomUtils.getRandomChars(4);
        int width = 70;
        int height = 25;
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman", Font.PLAIN, 20);
        // 调用Graphics2D绘画验证码
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66, 2, 82);
        g.setColor(color);
        g.setBackground(new Color(226, 226, 240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int) x, (int) baseY);
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 获取留言
     * @param myid
     * @return
     */
    @RequestMapping("/getAtMe")
    public ResponseMessage getAtMe(Integer myid){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            List<CallMe> res = userService.getAtMeByMyId(myid);
            return responseMessage.Success(res);
        } catch (Exception e) {
            logger.error("出错了"+e.getMessage());
            return responseMessage.False();
        }
    }

    /**
     * 获取关注的用户
     * @param myid
     * @return
     */
    @RequestMapping("/getFollowUser")
    public ResponseMessage getFollowUser(Integer myid){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            List<ReturnFollow> res = userService.getFollowUser(myid);
            return responseMessage.Success(res);
        } catch (Exception e) {
            logger.error("出错了"+e.getMessage());
            return responseMessage.False();
        }
    }
}
