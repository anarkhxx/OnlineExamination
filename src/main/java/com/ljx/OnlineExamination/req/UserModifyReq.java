package com.ljx.OnlineExamination.req;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lyy on 2020/4/11 下午9:22
 */

@Data
public class UserModifyReq implements Serializable {
    private Integer id;
    private String username;
    private String email;
    private String name;
    private String identification;
    private String sex;
}
