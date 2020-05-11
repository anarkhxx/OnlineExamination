package com.ljx.OnlineExamination.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExampaperNameReq implements Serializable {
    private Integer pid;
    private String pname;
}
