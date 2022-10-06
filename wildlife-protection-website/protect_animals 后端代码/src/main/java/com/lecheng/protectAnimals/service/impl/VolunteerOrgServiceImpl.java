package com.lecheng.protectAnimals.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lecheng.protectAnimals.dao.UserDao;
import com.lecheng.protectAnimals.dao.VolunteerOrgDao;
import com.lecheng.protectAnimals.pojo.*;
import com.lecheng.protectAnimals.service.VolunteerOrgService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class VolunteerOrgServiceImpl implements VolunteerOrgService {

    Logger logger=Logger.getLogger(VolunteerOrgServiceImpl.class);

    @Autowired
    private VolunteerOrgDao volunteerOrgDao;
    @Autowired
    private UserDao userDao;
    @Override
    public List<VolunteerOrg> getVolunteerOrg() {
        try {
            return volunteerOrgDao.getVolunteerOrg();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getVolunteerOrg异常");
            return null;
        }
    }

    @Override
    public PageInfo<VolunteerActivityEntrance> getV_A_EEntrance(Integer page, Integer rows,String statute) {
        try {
            PageHelper.startPage(page,rows);
            List<VolunteerActivityEntrance> result = volunteerOrgDao.getV_A_EEntrance(statute);
            PageInfo<VolunteerActivityEntrance> pageInfo=new PageInfo(result);
            return  pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getV_A_EEntrance异常");
            return null;
        }
    }

    @Override
    public VolunteerActivityEntrance getVolunteerActivity(Integer v_a_id) {
        try {
            return volunteerOrgDao.getVolunteerActivity(v_a_id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getVolunteerActivity异常");
            return null;
        }
    }

    @Override
    public int joinVolunteerActivity(JoinVolunteerActivity joinVolunteerActivity) {
        try {
            CheckVolunteerNumber flag = volunteerOrgDao.getCurrentNumber(joinVolunteerActivity.getVid());
            if (flag.getNumber()>=flag.getCurrent_number())
            {
                return -2;
            }
            return volunteerOrgDao.joinVolunteerActivity(joinVolunteerActivity);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("joinVolunteerActivity异常");
            return -1;
        }
    }

    @Override
    public PageInfo<VolunteerActivityEntrance> getAllV_A_EEntrance(Integer page) {
        try {
            PageHelper.startPage(page,9);
            List<VolunteerActivityEntrance> result = volunteerOrgDao.getAllV_A_EEntrance();
            PageInfo<VolunteerActivityEntrance> pageInfo=new PageInfo(result);
            return  pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getAllV_A_EEntrance异常");
            return null;
        }
    }

    @Override
    public boolean addMoneyById(Map map) {
        try {
            volunteerOrgDao.addMoneyById(map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Map getMyVolunteerActivity(JoinVolunteerActivity joinVolunteerActivity) {
        HashMap<Object, Object> map = new HashMap<>();
        try {
            joinVolunteerActivity.setStatute("进行中");
            List<JoinVolunteerActivity> myActivityIds = volunteerOrgDao.getMyActivityId(joinVolunteerActivity);
            List<VolunteerActivityEntrance> volunteerActivityById = volunteerOrgDao.getVolunteerActivityById(myActivityIds);
            joinVolunteerActivity.setStatute("已结束");
            List<JoinVolunteerActivity> myActivityIds2 = volunteerOrgDao.getMyActivityId(joinVolunteerActivity);
            List<VolunteerActivityEntrance> volunteerActivityById2 = volunteerOrgDao.getVolunteerActivityById(myActivityIds2);
            map.put("doing",volunteerActivityById);
            map.put("after",volunteerActivityById2);
            map.put("activityNumber",volunteerActivityById.size()+volunteerActivityById2.size());
            User user = userDao.getUserToMyActivity(joinVolunteerActivity.getUid());
            map.put("userInfo",user);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getMyVolunteerActivity异常");
            return null;
        }
    }

}
