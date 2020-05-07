package com.ljx.OnlineExamination.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by lyy
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Exampaper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    private String pname;
    private Date examdate;
    private Time examtime;
    private Integer duration;
    private Integer pdnum;
    private Integer danxnum;
    private Integer duoxnum;
    private Integer zgnum;
    private Integer tknum;
    private Integer totalpoints;
    private String soup;
    private String note;
}
