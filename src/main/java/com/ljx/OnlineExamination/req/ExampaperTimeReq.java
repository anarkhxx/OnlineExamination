package com.ljx.OnlineExamination.req;

import com.ljx.OnlineExamination.pojo.Paperquestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExampaperTimeReq {
    private Date examdate;
    private Time examtime;
    private Integer duration;
    private List<Paperquestion> paperquestion;
}
