package com.lecheng.protectAnimals.service;



import com.lecheng.protectAnimals.pojo.AtMe;
import com.lecheng.protectAnimals.pojo.Comment;
import com.lecheng.protectAnimals.pojo.Dynamics;
import com.lecheng.protectAnimals.pojo.FollowOtherUser;

import java.util.List;

public interface DynamicsService {
    List<Dynamics> getDynamicsByLikes();
    List<Dynamics> getDynamicsByTime();
    List<Dynamics> getDynamicsFromConcern( Integer id);
    Dynamics getDynamicById(Integer id);
    boolean likeDynamic(Integer id);
    boolean FollowOtherUser(FollowOtherUser followOtherUser);
    boolean atMe(AtMe atMe);
    boolean addDynamic(Dynamics dynamic);
    List<Comment> getCommentByDynamicsId(Integer dynamicsid);
    boolean releaseComment(Comment comment);
    boolean releaseChildrenComment(Comment comment);
    boolean likeComment(Integer id);
}
