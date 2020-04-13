package com.ljx.OnlineExamination.req;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lyy on 2020/4/11 下午9:22
 */

@Data
public class UserModifyReq implements Serializable {
    //phone
    private String phone;

    //用户名
    private String username;

    //邮箱
    private String email;

    //真实姓名
    private String name;

    //身份证号
    private String identification;

    //性别
    private String sex;
}
