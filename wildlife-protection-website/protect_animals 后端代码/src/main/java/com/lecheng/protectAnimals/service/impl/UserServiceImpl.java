package com.lecheng.protectAnimals.service.impl;

import com.lecheng.protectAnimals.dao.UserDao;
import com.lecheng.protectAnimals.pojo.*;
import com.lecheng.protectAnimals.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    //日志打印
    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public List<User> getAllUser() {
        try {
            return userDao.getAllUser();
        } catch (Exception e) {
            logger.error("获取失败");
            return null;
        }
    }

    @Override
    public User getUser(String uname) {
        try {
            return userDao.getUser(uname);
        } catch (Exception e) {
            logger.error("获取失败");
            return null;
        }
    }

    @Override
    public int insertUser(User user) {
        try {
            return userDao.insertUser(user);
        } catch (Exception e) {
            logger.error("添加失败");
            return -1;
        }
    }

    @Override
    public int insertRecommend(Recommend recommend) {
        try {
            return userDao.insertRecommend(recommend);
        } catch (Exception e) {
            logger.error("添加失败");
            return -1;
        }
    }

    @Override
    public int insertUserImg(User user) {
        try {
            return userDao.insertUserImg(user);
        } catch (Exception e) {
            logger.error("添加失败");
            return -1;
        }
    }

    @Override
    public List<User> getUserBy(User user) {
        try {
            return userDao.getUserBy(user);
        } catch (Exception e) {
            logger.error("获取失败");
            return null;
        }
    }

    @Override
    public List<CallMe> getAtMeByMyId(Integer otherid) {
        List<AtMe> atMes = userDao.getAtMeByMyId(otherid);
        List<CallMe> callMes = new ArrayList<>();
        for (AtMe m : atMes
        ) {
            CallMe callMe = new CallMe();
            String userName = userDao.getUserName(m.getMyid());
            callMe.setOtherName(userName);
            callMe.setCallMe(m.getAtme());
            callMe.setCreate_time(m.getCreate_time());
            callMe.setId(m.getId());
            callMes.add(callMe);
        }
        return callMes;
    }

    @Override
    public List<ReturnFollow> getFollowUser(Integer user2id) {
        List<FollowOtherUser> followOtherUsers = userDao.getFollowOtherUser(user2id);
        ArrayList<ReturnFollow> res = new ArrayList<>();
        for (FollowOtherUser user : followOtherUsers
        ) {
            ReturnFollow returnFollow = new ReturnFollow();
            System.out.println(user);
            returnFollow.setId(user.getId());
            String userName = userDao.getUserName(user.getUser1id());
            System.out.println(userName);
            returnFollow.setUsername(userName);
            String userImg = userDao.getUserImg(user.getUser1id());
            System.out.println(userImg);
            returnFollow.setHead_img_url(userImg);
            res.add(returnFollow);
        }
        return res;
    }
}
