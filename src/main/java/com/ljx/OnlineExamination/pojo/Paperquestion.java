package com.ljx.OnlineExamination.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Paperquestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eid;

    private Integer pid;
    private Integer qid;
    private Integer id;
    private Integer typeid;
    private String content;
    private String optiona;
    private String optionb;
    private String optionc;
    private String optiond;
    private String img;
    private String answer;
    private Integer point;
}
