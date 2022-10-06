package com.lecheng.protectAnimals.dao;



import com.lecheng.protectAnimals.pojo.Answer;
import com.lecheng.protectAnimals.pojo.AnswerVo;
import com.lecheng.protectAnimals.pojo.Question;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface QuestionDao {
    List<Question> getQuestionByLimit();
    List<Answer> getAnswer(AnswerVo answerVo);
}
