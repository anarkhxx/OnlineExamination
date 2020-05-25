package com.ljx.OnlineExamination.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentPaperReq implements Serializable {
    private Integer pid;
    private String pname;
    private Time examtime;
    private Integer duration;
    private String note;

    public StudentPaperReq(Integer pid,
                     String pname,
                     Object  examtime,
                     Integer duration,
                     String note) {
        this.pid=pid;
        this.pname = pname;
        this.examtime = stringToTime(examtime.toString());
        this.duration = duration;
        this.note = note;

    }

    public static Time stringToTime(String dateStr){

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        try {
            Date date = sdf.parse(dateStr);
            date.getTime();
            cal.setTime(date);
            return new Time(cal.getTimeInMillis());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        cal.setTime(new java.util.Date());
        return new Time(cal.getTimeInMillis());
    }

}
