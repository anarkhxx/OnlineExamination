package com.ljx.OnlineExamination.req;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by lyy on 2020/4/29 上午9:57
 */


@Data
public class AutoCreatePaperReq {
    //试卷名
    private String pname;
    //考试日期
    private Date examdate;
    //考试时间
    private Time examtime;
    //考试时长
    private Integer duration;
    //判断题个数
    private Integer pdnum;
    //单选题个数
    private Integer danxnum;
    private Integer duoxnum;
    //主观题
    private Integer zgnum;
    //填空
    private Integer tknum;
    //试卷总分
    private Integer totalpoints;
    //鸡汤
    private String soap;
    //备注
    private String note;
}