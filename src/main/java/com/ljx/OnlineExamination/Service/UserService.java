package com.ljx.OnlineExamination.Service;

import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.User;

/**
 * @author ljx
 */
public interface UserService {

    ServerResponse<User> login(String username, String password);
}
