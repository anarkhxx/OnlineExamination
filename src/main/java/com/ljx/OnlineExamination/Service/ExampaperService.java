package com.ljx.OnlineExamination.Service;

import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Exampaper;
import com.ljx.OnlineExamination.req.CreatePaperReq;

/**
 * Created by lyy on 2020/5/3 下午6:39
 */

public interface ExampaperService {
    ServerResponse<Exampaper> create(CreatePaperReq createPaperReq);
}
