package com.ljx.OnlineExamination.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by lyy on 2020/4/15 下午11:30
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer labelid;
    private String labelname;
}

