package com.ljx.OnlineExamination.Service;

import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Manager;

/**
 * @author lyy
 */
public interface ManagerService {

    ServerResponse<Manager> login(String username, String password);

}