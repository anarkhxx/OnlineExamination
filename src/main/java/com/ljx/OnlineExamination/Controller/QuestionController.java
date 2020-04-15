package com.ljx.OnlineExamination.Controller;

import com.ljx.OnlineExamination.Service.QuestionService;
import com.ljx.OnlineExamination.common.Const;
import com.ljx.OnlineExamination.common.ResponseCode;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Question;
import com.ljx.OnlineExamination.pojo.User;
import com.ljx.OnlineExamination.req.AddQuestionReq;
import com.ljx.OnlineExamination.req.QueryQuestionReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/question")
public class QuestionController {
    private final QuestionService questionService;
    @Autowired
    public QuestionController(QuestionService questionService
    ) {

        this.questionService = questionService;
    }

    @PostMapping("/all")
    @ResponseBody
    public ServerResponse<Question> getAllQuestion(
                                        @RequestParam(defaultValue = "1") Integer pageNumber,
                                        @RequestParam(defaultValue = "3") Integer pageSize,
                                        HttpSession session
    ){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }
        return questionService.getAllQuestion(pageNumber, pageSize);
    }

    //question条件查询
    @PostMapping("/condition")
    @ResponseBody
    public ServerResponse<Question> getAllQuestion(
            QueryQuestionReq queryQuestionReq,
            HttpSession session
    ){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }

        if(queryQuestionReq==null) queryQuestionReq = new QueryQuestionReq();
        return questionService.getQuestion(queryQuestionReq);
    }

    //添加题目
    @PostMapping("/add")
    @ResponseBody
    public ServerResponse<Question> getAllQuestion(
            AddQuestionReq addQuestionReq,
            HttpSession session
    ){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录");
        }

        if(addQuestionReq==null) addQuestionReq = new AddQuestionReq();
        return questionService.addQuestion(addQuestionReq);
    }
}
