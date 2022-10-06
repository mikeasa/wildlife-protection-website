package com.lecheng.protectAnimals.controller;
import com.lecheng.protectAnimals.pojo.AdminReleaseEntrance;
import com.lecheng.protectAnimals.pojo.ResponseMessage;
import com.lecheng.protectAnimals.service.AdminReleaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adminRelease")
public class AdminReleaseController {
    @Autowired
    private AdminReleaseService adminReleaseService;
    //日志打印
    private static Logger logger = Logger.getLogger(AdminReleaseController.class);

    /**
     * 获取管理员发布的最新动态入口
     * @return
     */
    @RequestMapping("/getReleaseEntrance")
    public ResponseMessage getReleaseEntrance(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            List<AdminReleaseEntrance> adminReleaseEntrance = adminReleaseService.getAdminReleaseEntrance();
            return responseMessage.Success(adminReleaseEntrance);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return responseMessage.False();
        }
    }

    /**
     * 获取去具体新闻内容
     * @param id
     * @return
     */
    @RequestMapping("/getAdminReleaseById")
    public ResponseMessage getAdminReleaseById(int id){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            AdminReleaseEntrance adminReleaseById = adminReleaseService.getAdminReleaseById(id);
            return responseMessage.Success(adminReleaseById);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return responseMessage.False();
        }
    }

}
