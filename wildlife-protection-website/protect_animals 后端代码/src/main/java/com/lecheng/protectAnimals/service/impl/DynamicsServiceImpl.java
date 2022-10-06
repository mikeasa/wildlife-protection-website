package com.lecheng.protectAnimals.service.impl;


import com.lecheng.protectAnimals.dao.DynamicsDao;
import com.lecheng.protectAnimals.pojo.AtMe;
import com.lecheng.protectAnimals.pojo.Comment;
import com.lecheng.protectAnimals.pojo.Dynamics;
import com.lecheng.protectAnimals.pojo.FollowOtherUser;
import com.lecheng.protectAnimals.service.DynamicsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
@Transactional
public class DynamicsServiceImpl implements DynamicsService {
    //日志打印
    private static Logger logger = Logger.getLogger(DynamicsServiceImpl.class);
    @Autowired
    private DynamicsDao dynamicsDao;
    @Override
    public List<Dynamics> getDynamicsByLikes() {
        try {
            return dynamicsDao.getDynamicsByLikes();
        } catch (Exception e) {
            logger.error("getDynamicsByLikes获取失败");
           return null;
        }
    }

    @Override
    public List<Dynamics> getDynamicsByTime() {
        try {
            return dynamicsDao.getDynamicsByTime();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getDynamicsByTime获取失败");
            return null;
        }
    }

    @Override
    public List<Dynamics> getDynamicsFromConcern(Integer id) {
        try {
            List<Integer> concernedId = dynamicsDao.getConcernedId(id);
            List<Dynamics> dynamicsFromConcern = dynamicsDao.getDynamicsFromConcern(concernedId);
            return dynamicsFromConcern;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getDynamicsFromConcern获取失败");
            return null;
        }
    }

    @Override
    public Dynamics getDynamicById(Integer id) {
        try {
            Dynamics dynamicById = dynamicsDao.getDynamicById(id);
            String dynamicAuthorById = dynamicsDao.getDynamicAuthorById(dynamicById.getAuthorid());
            dynamicById.setContent(HtmlUtils.htmlUnescape(dynamicById.getContent()));
            dynamicById.setImg_url(dynamicAuthorById);
            return dynamicById;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getDynamicById获取失败");
            return null;
        }
    }

    @Override
    public boolean likeDynamic(Integer id) {
        try {
            dynamicsDao.likeDynamic(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("likeDynamic失败");
            return false;
        }

    }

    @Override
    public boolean FollowOtherUser(FollowOtherUser followOtherUser) {
        try {
            dynamicsDao.FollowOtherUser(followOtherUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("FollowOtherUser失败");
            return false;
        }
    }

    @Override
    public boolean atMe(AtMe atMe) {
        try {
            dynamicsDao.atMe(atMe);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("atMe失败");
            return false;
        }
    }

    @Override
    public boolean addDynamic(Dynamics dynamic) {
        try {
            dynamicsDao.addDynamic(dynamic);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("addDynamic失败");
            return false;
        }
    }

    @Override
    public List<Comment> getCommentByDynamicsId(Integer dynamicsid) {
        try {
            List<Comment> comment = dynamicsDao.getCommentByDynamicsId(dynamicsid);
            for (int i=0;i<comment.size();i++){
                Integer commentid2 = comment.get(i).getId();
                List<Comment> comments = dynamicsDao.getCommentsByCommentId2(commentid2);
                comment.get(i).setChildrenComments(comments);
            }
            return comment;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("getCommentById失败");
            return null;
        }
    }

    @Override
    public boolean releaseComment(Comment comment) {
        try {
            if (comment.getHead_img()==null){
                comment.setHead_img("http://47.108.95.77/protect_animals/file/animals/3e2a7a6c8900.jpg");
            }
            dynamicsDao.releaseComment(comment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("releaseComment错误");
            return false;
        }
    }

    @Override
    public boolean releaseChildrenComment(Comment comment) {
        try {
            if (comment.getHead_img()==null){
                comment.setHead_img("http://47.108.95.77/protect_animals/file/animals/3e2a7a6c8900.jpg");
            }
            comment.setCommentid2(comment.getCommentid2());
            dynamicsDao.releaseChildrenComment(comment);
            return true;
        } catch (Exception e) {
            logger.error("releaseChildrenComment失败");
            return false;
        }
    }

    @Override
    public boolean likeComment(Integer id) {
        try {
            dynamicsDao.likeComment(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("likeComment失败");
            return false;
        }
    }
}
