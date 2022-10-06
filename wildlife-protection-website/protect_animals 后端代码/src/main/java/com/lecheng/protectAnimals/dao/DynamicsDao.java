package com.lecheng.protectAnimals.dao;



import com.lecheng.protectAnimals.pojo.AtMe;
import com.lecheng.protectAnimals.pojo.Comment;
import com.lecheng.protectAnimals.pojo.Dynamics;
import com.lecheng.protectAnimals.pojo.FollowOtherUser;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface DynamicsDao {
    List<Dynamics> getDynamicsByLikes();
    List<Dynamics> getDynamicsByTime();
    List<Dynamics> getDynamicsFromConcern(List<Integer> list);
    List<Integer> getConcernedId(Integer id);
    Dynamics getDynamicById(Integer id);
    String getDynamicAuthorById(Integer authorid);
    int likeDynamic(Integer id);
    int FollowOtherUser(FollowOtherUser followOtherUser);
    int atMe(AtMe atMe);
    int addDynamic(Dynamics dynamic);
    List<Comment> getCommentByDynamicsId(Integer dynamicsid);
    List<Comment> getCommentsByCommentId2(Integer commentid2);
    int releaseComment(Comment comment);
    int releaseChildrenComment(Comment comment);
    int likeComment(Integer id);
}
