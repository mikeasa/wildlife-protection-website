package com.lecheng.protectAnimals.dao;




import com.lecheng.protectAnimals.pojo.AtMe;
import com.lecheng.protectAnimals.pojo.FollowOtherUser;
import com.lecheng.protectAnimals.pojo.Recommend;
import com.lecheng.protectAnimals.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserDao {
    List<User> getAllUser();
    User getUser(String uname);
    List<User> getUserBy(User user);
    int insertUser(User user);
    int insertRecommend(Recommend recommend);
    int insertUserImg(User user);
    User getUserToMyActivity(Integer id);
    List<AtMe> getAtMeByMyId(Integer otherid);
    String getUserName(Integer userid);
    List<FollowOtherUser> getFollowOtherUser(Integer user2id);
    String getUserImg(Integer userid);
}
