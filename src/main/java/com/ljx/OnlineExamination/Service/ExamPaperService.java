package com.ljx.OnlineExamination.Service;

import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Paperquestion;

import java.util.List;

public interface ExamPaperService {
    ServerResponse<List<String>> getAllPapersName();

    ServerResponse<List<Paperquestion>> getQuestionById(Integer id);
}
