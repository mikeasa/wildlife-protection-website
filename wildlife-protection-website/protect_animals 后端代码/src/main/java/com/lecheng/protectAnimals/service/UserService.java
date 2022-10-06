package com.lecheng.protectAnimals.service;


import com.lecheng.protectAnimals.pojo.CallMe;
import com.lecheng.protectAnimals.pojo.Recommend;
import com.lecheng.protectAnimals.pojo.ReturnFollow;
import com.lecheng.protectAnimals.pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();

    User getUser(String uname);//shiro

    int insertUser(User user);

    int insertRecommend(Recommend recommend);

    int insertUserImg(User user);

    List<User> getUserBy(User user);

    List<CallMe> getAtMeByMyId(Integer otherid);

    List<ReturnFollow> getFollowUser(Integer user2id);
}
