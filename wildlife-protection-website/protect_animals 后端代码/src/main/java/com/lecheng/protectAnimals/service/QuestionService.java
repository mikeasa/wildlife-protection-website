package com.lecheng.protectAnimals.service;


import com.lecheng.protectAnimals.pojo.AnswerVo;
import com.lecheng.protectAnimals.pojo.Question;

import java.util.List;
import java.util.Map;

public interface QuestionService {
    List<Question> getQuestionByLimit();
    Map getScore(AnswerVo answerVo);
}
