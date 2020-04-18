package com.ljx.OnlineExamination.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by lyy on 2020/4/15
 */
@Data
public class AddQuestionReq {
        //题类 1-单选 2-多选 3-判断 4-主观 5-填空
        private Integer typeid;

        //题干
        private String content;

        //选项ABCD
        private String optiona;
        private String optionb;
        private String optionc;
        private String optiond;

        //答案
        private String answer;

        //图片
        private MultipartFile img;

        //难度等级 1-5
        private Integer difflevel;

        //标签 传name 没有则会在数据库中创建
        private String labelname;
}
