package com.ljx.OnlineExamination.Controller;

import com.ljx.OnlineExamination.Service.ExampaperService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Exampaper;
import com.ljx.OnlineExamination.pojo.Paperquestion;
import com.ljx.OnlineExamination.req.ExampaperNameReq;
import com.ljx.OnlineExamination.req.ExampaperTimeReq;
import com.ljx.OnlineExamination.req.StudentPaperReq;
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
    private final ExampaperService exampaperService;
    @Autowired
    public ExamPaperController(ExampaperService exampaperService
    ) {

        this.exampaperService = exampaperService;
    }

    //查看所有试卷名称
    @GetMapping("/getAllPapersName")
    @ResponseBody
    public ServerResponse<List<ExampaperNameReq>> getAllPapersName(
    ){
        ServerResponse<List<ExampaperNameReq>> response = exampaperService.getAllPapersName();

        return response;
    }

    //根据试卷id查试卷内容
    @GetMapping("/getQuestionById")
    @ResponseBody
    public ServerResponse<ExampaperTimeReq> getQuestionById(
            Integer id
    ){
        ServerResponse<ExampaperTimeReq> response = exampaperService.getQuestionById(id);

        return response;
    }

    //考生的查看考生所有试卷
    @GetMapping("/getAllPapersNameByStudent")
    @ResponseBody
    public ServerResponse<List<StudentPaperReq>> getAllPapersNameByStudent(
            Integer id
    ){
        ServerResponse<List<StudentPaperReq>> response = exampaperService.getAllPapersNameByStudent();

        return response;
    }
}
