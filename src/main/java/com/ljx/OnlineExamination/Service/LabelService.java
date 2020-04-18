package com.ljx.OnlineExamination.Service;

import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Label;

import java.util.List;

/**
 * Created by lyy on 2020/4/17 下午10:21
 */

public interface LabelService {

    ServerResponse<List<Label>> getAll();

}