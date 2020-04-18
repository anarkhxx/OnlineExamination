package com.ljx.OnlineExamination.Service;

import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.dto.PageChunk;
import com.ljx.OnlineExamination.pojo.Manager;
import com.ljx.OnlineExamination.pojo.Question;
import com.ljx.OnlineExamination.req.AddQuestionReq;
import com.ljx.OnlineExamination.req.QueryQuestionReq;

/**
 * @author ljx
 */
public interface QuestionService {
    ServerResponse<Question> getAllQuestion(Integer pageNumber, Integer pageSize);
    ServerResponse<Question> getQuestion(QueryQuestionReq queryQuestionReq);

    ServerResponse<Question> addQuestion(AddQuestionReq addQuestionReq);
}
