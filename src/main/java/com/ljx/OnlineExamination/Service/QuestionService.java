package com.ljx.OnlineExamination.Service;

import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.dto.PageChunk;
import com.ljx.OnlineExamination.pojo.Manager;
import com.ljx.OnlineExamination.pojo.Question;

/**
 * @author ljx
 */
public interface QuestionService {
    ServerResponse getAllQuestion(Integer pageNumber, Integer pageSize);
}
