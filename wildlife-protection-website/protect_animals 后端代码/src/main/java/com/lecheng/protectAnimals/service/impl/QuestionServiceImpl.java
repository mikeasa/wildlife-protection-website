package com.lecheng.protectAnimals.service.impl;


import com.lecheng.protectAnimals.dao.QuestionDao;
import com.lecheng.protectAnimals.pojo.Answer;
import com.lecheng.protectAnimals.pojo.AnswerVo;
import com.lecheng.protectAnimals.pojo.Question;
import com.lecheng.protectAnimals.service.QuestionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;
    //日志打印
    private static Logger logger = Logger.getLogger(QuestionServiceImpl.class);


    public List<Question> getQuestionByLimit() {
        try {
            return questionDao.getQuestionByLimit();
        } catch (Exception e) {
            logger.error("获取失败");
            return  null;
        }
    }


    public Map getScore(AnswerVo answerVo) {
        try {
            List<Answer> answers = questionDao.getAnswer(answerVo);
            answers.retainAll(answerVo.getQuestionList());
            Map<Object, Object> map = new HashMap<Object, Object>();
            map.put("score",answers.size());
            map.put("answers",answers);
            return map;
        } catch (Exception e) {
            logger.error("获取失败");
            return null;
        }
    }
}
