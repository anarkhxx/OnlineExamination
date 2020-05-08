package com.ljx.OnlineExamination.Controller;

import com.ljx.OnlineExamination.Service.ExamPaperService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Paperquestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by ljx
 */

@RestController
@RequestMapping("api/exampaper")
public class ExamPaperController {
    private final ExamPaperService examPaperService;
    @Autowired
    public ExamPaperController(ExamPaperService examPaperService
    ) {

        this.examPaperService = examPaperService;
    }

    //查看所有试卷名称
    @GetMapping("/getAllPapersName")
    @ResponseBody
    public ServerResponse<List<String>> getAllPapersName(
    ){
        ServerResponse<List<String>> response = examPaperService.getAllPapersName();

        return response;
    }

    //根据试卷id查试卷内容
    @GetMapping("/getQuestionById")
    @ResponseBody
    public ServerResponse<List<Paperquestion>> getQuestionById(
            Integer id
    ){
        ServerResponse<List<Paperquestion>> response = examPaperService.getQuestionById(id);

        return response;
    }

}
