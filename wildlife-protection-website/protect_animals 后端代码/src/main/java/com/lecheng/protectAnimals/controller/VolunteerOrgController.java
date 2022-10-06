package com.lecheng.protectAnimals.controller;

import com.github.pagehelper.PageInfo;
import com.lecheng.protectAnimals.pojo.*;
import com.lecheng.protectAnimals.service.VolunteerOrgService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/volunteerOrg")
public class VolunteerOrgController {
    Logger logger = Logger.getLogger(VolunteerOrgController.class);
    @Autowired
    private VolunteerOrgService volunteerOrgService;

    /**
     * 获取志愿者组织
     * @return
     */
    @RequestMapping("/getVolunteerOrg")
    public ResponseMessage getVolunteerOrg(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            List<VolunteerOrg> volunteerOrg = volunteerOrgService.getVolunteerOrg();
//            responseMessage.setData(volunteerOrg);
//            return responseMessage;
            return responseMessage.Success(volunteerOrg);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getVolunteerOrg异常");
            return responseMessage.False();
        }
    }

    @RequestMapping("/getAllVolunteerActivity")
    public ResponseMessage getAllVolunteerActivity(Integer page){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            return responseMessage.Success(volunteerOrgService.getAllV_A_EEntrance(page));
        } catch (Exception e) {
            e.printStackTrace();
            return responseMessage.False();
        }
    }

    /**
     * 获取志愿活动入口
     * @return
     */
    @RequestMapping("/getV_A_EEntrance")
    public ResponseMessage getV_A_EEntrance(Integer page,Integer flag) {
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            if (flag == 0) {
                PageInfo<VolunteerActivityEntrance> result1 = volunteerOrgService.getV_A_EEntrance(page, 2, "未开始");
                return responseMessage.Success(result1);
            }
            if (flag == 1) {
                PageInfo<VolunteerActivityEntrance> result2 = volunteerOrgService.getV_A_EEntrance(page, 2, "进行中");
                return responseMessage.Success(result2);
            }
            if (flag == 2) {
                PageInfo<VolunteerActivityEntrance> result3 = volunteerOrgService.getV_A_EEntrance(page, 2, "已结束");
                return responseMessage.Success(result3);
            }
            return responseMessage.False("不存在"+flag+"状态的活动");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getV_A_EEntrance异常");
            return responseMessage.False();
        }
    }

    /**
     * 获取具体志愿活动
     * @param v_a_id
     * @return
     */
    @RequestMapping("/getVolunteerActivity")
    public ResponseMessage getVolunteerActivity(Integer v_a_id){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            VolunteerActivityEntrance volunteerActivity = volunteerOrgService.getVolunteerActivity(v_a_id);
            return responseMessage.Success(volunteerActivity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getVolunteerActivity异常");
            return responseMessage.False();
        }
    }

    /**
     * 报名志愿者活动
     * @param joinApplication
     * @return
     */
    @RequestMapping("/joinActivity")
    public ResponseMessage joinActivity(JoinVolunteerActivity joinApplication){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            int i = volunteerOrgService.joinVolunteerActivity(joinApplication);
            if (i==-2){
                responseMessage.setCode(200);
                responseMessage.setMsg("报名人数已满");
                return responseMessage;
            }
            return responseMessage.Success();
        } catch (Exception e){
            e.printStackTrace();
            logger.error("joinActivity异常");
            return responseMessage.False("joinActivity异常");
        }
    }

    /**
     * 获取我的志愿活动
     * @param joinVolunteerActivity
     * @return
     */
    @RequestMapping("/getMyActivity")
    public ResponseMessage getMyActivity(JoinVolunteerActivity joinVolunteerActivity){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            Map myVolunteerActivity = volunteerOrgService.getMyVolunteerActivity(joinVolunteerActivity);
            return responseMessage.Success(myVolunteerActivity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getMyActivity异常");
            return responseMessage.False("getMyActivity异常");
        }
    }

}
