package com.ljx.OnlineExamination.req;

import lombok.Data;

import java.util.List;

/**
 * Created by lyy on 2020/4/13 下午10:35
 */
@Data
public class QueryQuestionReq {

    private Integer pageNumber = 1;

    private Integer pageSize = 3;

    //题类
    private List<String> typeid;

    //难度
    private List<String> difflevel;

    //题型标签号
    private List<String> labelid;


}
