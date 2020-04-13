package com.ljx.OnlineExamination.req;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lyy on 2020/4/13 下午10:35
 */
@Data
public class QueryQuestionReq {

    private Integer pageNumber = 1;

    private Integer pageSize = 3;

    //题类
    private Integer typeid;

    //难度
    private Integer difflevel;

    //题型标签号
    private Integer labelid;


}
