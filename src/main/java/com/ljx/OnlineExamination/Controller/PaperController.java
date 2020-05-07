package com.ljx.OnlineExamination.Controller;

import com.ljx.OnlineExamination.Service.ExampaperService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Exampaper;
import com.ljx.OnlineExamination.req.CreatePaperReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by lyy on 2020/5/3 下午8:55
 */
@RestController
@RequestMapping("api/paper")
public class PaperController {
    private final ExampaperService exampaperService;
    @Autowired
    public PaperController(ExampaperService exampaperService
    ) {

        this.exampaperService = exampaperService;
    }

    @PostMapping("/auto")
    @ResponseBody
    public ServerResponse<Exampaper> autoCreate(
            CreatePaperReq createPaperReq,
            HttpSession session
    ){
        return  exampaperService.create(createPaperReq);
    }



}