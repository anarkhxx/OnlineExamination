package com.ljx.OnlineExamination.Service;

import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Exampaper;
import com.ljx.OnlineExamination.pojo.Paperquestion;
import com.ljx.OnlineExamination.req.CreatePaperReq;
import com.ljx.OnlineExamination.req.ExampaperNameReq;
import com.ljx.OnlineExamination.req.ExampaperTimeReq;
import com.ljx.OnlineExamination.req.StudentPaperReq;

import java.util.List;

/**
 * Created by lyy on 2020/5/3 下午6:39
 */

public interface ExampaperService {
    ServerResponse<Exampaper> create(CreatePaperReq createPaperReq);

    ServerResponse<List<ExampaperNameReq>> getAllPapersName();

    ServerResponse<ExampaperTimeReq> getQuestionById(Integer id);

    ServerResponse<List<StudentPaperReq>> getAllPapersNameByStudent();


}
