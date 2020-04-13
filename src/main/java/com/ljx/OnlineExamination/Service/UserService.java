package com.ljx.OnlineExamination.Service;

import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.User;
import com.ljx.OnlineExamination.req.UserModifyReq;

/**
 * @author ljx
 */
public interface UserService {

    ServerResponse<User> login(String phone, String password);

    ServerResponse<User> register(String phone, String password);

    ServerResponse<User> vfphone(String phone);

    ServerResponse<User> modifyUser(UserModifyReq userModifyReq);
}
