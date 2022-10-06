package com.lecheng.protectAnimals.controller;

import com.lecheng.protectAnimals.pojo.AnswerVo;
import com.lecheng.protectAnimals.pojo.Question;
import com.lecheng.protectAnimals.pojo.ResponseMessage;
import com.lecheng.protectAnimals.service.QuestionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lawQuestion")
public class LawQuestionController {
    @Autowired
    private QuestionService questionService;
    /**
     * 日志打印
     */
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 获取法律问题
     * @return
     */
    @RequestMapping("/getQuestion")
    public ResponseMessage getQuestions(){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            List<Question> questions = questionService.getQuestionByLimit();
                responseMessage.setData(questions);
                responseMessage.setCode(200);
                responseMessage.setMsg("获取成功");
            return responseMessage;
        } catch (Exception e) {
            return responseMessage.False();
        }
    }

    /**
     * 获取最终答题得分
     * @param answerVo
     * @return
     */
    @RequestMapping("/getScore")
    public ResponseMessage getScore(AnswerVo answerVo){
        ResponseMessage<Object> responseMessage = new ResponseMessage<>();
        try {
            Map result = questionService.getScore(answerVo);
            return responseMessage.Success(result);
        } catch (Exception e) {
            return responseMessage.False();
        }
    }
}
