package com.lecheng.protectAnimals.controller;


import com.lecheng.protectAnimals.pojo.*;
import com.lecheng.protectAnimals.service.DynamicsService;
import com.lecheng.protectAnimals.utils.Common;
import com.lecheng.protectAnimals.utils.CreateUUID;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dynamics")
public class DynamicsController {

    @Autowired
    private DynamicsService dynamicsService;
    /**
     * 日志打印
     */
    private static Logger log = Logger.getLogger(DynamicsController.class);

    /**
     * 获取推荐动态
     * @return
     */
    @RequestMapping("/getDynamicsByLikes")
    public ResponseMessage getDynamicsByLikes(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            List<Dynamics> allDynamics = dynamicsService.getDynamicsByLikes();
            return responseMessage.Success(allDynamics);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getDynamicsByLikes异常");
          return responseMessage.False("getDynamicsByLikes异常");
        }
    }

    /**
     * 获取最近动态
     * @return
     */
    @RequestMapping("/getDynamicsByTime")
    public  ResponseMessage getDynamicsByTime(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            List<Dynamics> dynamicsByTime = dynamicsService.getDynamicsByTime();
            return responseMessage.Success(dynamicsByTime);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getDynamicsByTime异常");
            return responseMessage.False("getDynamicsByTime异常");
        }
    }

    /**
     * 获取关注动态
     * @param id
     * @return
     */
    @RequestMapping("/getDynamicsByConcern")
    public ResponseMessage getDynamicsByConcern(Integer id){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            List<Dynamics> dynamicsFromConcern = dynamicsService.getDynamicsFromConcern(id);
            return responseMessage.Success(dynamicsFromConcern);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getDynamicsByConcern异常");
            return responseMessage.False("getDynamicsByConcern异常");
        }

    }

    /**
     * 获取指定动态详情
     * @param id
     * @return
     */
    @RequestMapping("/getDynamicById")
    public ResponseMessage getDynamicById(Integer id){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            Dynamics dynamicById = dynamicsService.getDynamicById(id);
            return responseMessage.Success(dynamicById);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("getDynamicById异常");
            return responseMessage.False("getDynamicById异常");
        }
    }

    /**
     * 对指定动态点赞
     * @param id
     * @return
     */
    @RequestMapping("/likeDynamic")
    public ResponseMessage likeDynamic(Integer id){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        if (dynamicsService.likeDynamic(id)){
            return responseMessage.Success();
        }else return responseMessage.False("点赞失败");
    }

    /**
     * 关注指定的用户
     * @param followOtherUser
     * @return
     */
    @RequestMapping("/followOtherUser")
    public ResponseMessage followOtherUser(FollowOtherUser followOtherUser){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        if (dynamicsService.FollowOtherUser(followOtherUser)){
            return responseMessage.Success();
        }else return responseMessage.False("关注失败");
    }

    /**
     * 私信
     * @param atMe
     * @return
     */
    @RequestMapping("/atMe")
    public ResponseMessage atMe(AtMe atMe){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        if (dynamicsService.atMe(atMe)){
            return responseMessage.Success();
        }else return responseMessage.False("私信失败");
    }

    /**
     * 发布动态
     * @param dynamics
     * @return
     * @throws IOException
     */
    @RequestMapping("/addDynamic")
    public ResponseMessage addDynamic(Dynamics dynamics){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            if (dynamics.getImg_url()==null){
                dynamics.setImg_url("http://47.108.95.77/protect_animals/file/dynamic_img/jhk-1615725752780.jpg");
            }
            String content = HtmlUtils.htmlEscapeHex(dynamics.getContent());
            System.out.println("发布内容context:"+content);
            dynamics.setContent(content);
            dynamicsService.addDynamic(dynamics);
            return responseMessage.Success();
        } catch (Exception e) {
            e.printStackTrace();
            return responseMessage.False("发布动态失败");
        }
    }

    /**
     * 获取评论
     * @param dynamicsid
     * @return
     */
    @RequestMapping("/getComment")
    public ResponseMessage getCommentByDynamicsId(Integer dynamicsid){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            List<Comment> comment = dynamicsService.getCommentByDynamicsId(dynamicsid);
            return responseMessage.Success(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return responseMessage.False("评论失败");
        }
    }

    /**
     * 发布评论
     * @param comment
     * @return
     */
    @RequestMapping("/releaseComment")
    public ResponseMessage releaseComment(Comment comment){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        if (dynamicsService.releaseComment(comment)){
            return responseMessage.Success();
        }else return responseMessage.False("发布评论失败");
    }

    /**
     * 发布子评论
     * @param comment
     * @return
     */
    @RequestMapping("/releaseChildrenComment")
    public ResponseMessage releaseChildrenComment(Comment comment){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        if (dynamicsService.releaseChildrenComment(comment)){
            return responseMessage.Success();
        }else return responseMessage.False("发布子评论失败");
    }

    /**
     * 点赞评论
     * @param id
     * @return
     */
    @RequestMapping("/likeComment")
    public ResponseMessage likeComment(Integer id){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        if (dynamicsService.likeComment(id)){
            return responseMessage.Success();
        }else return responseMessage.False("点赞评论失败");
    }

}
