package com.ljx.OnlineExamination.req;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by lyy on 2020/5/3 下午6:34
 */

@Data
public class CreatePaperReq {
    private String name;

    private Date examDate;
    private Time examTime;
    private Integer duration;

    private Integer pdNum;
    private Integer pdPoint;
    private Integer danxNum;
    private Integer danxPoint;

    private Integer duoxNum;
    private Integer duoxPoint;

    private Integer zgNum;
    private Integer zgPoint;

    private Integer tkNum;
    private Integer tkPoint;

    private Integer totalPoints;

    private String soup;

    private String note;
}
