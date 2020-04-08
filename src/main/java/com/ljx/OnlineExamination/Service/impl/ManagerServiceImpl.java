package com.ljx.OnlineExamination.Service.impl;
import com.ljx.OnlineExamination.Repository.ManagerRepository;
import com.ljx.OnlineExamination.Repository.UserRepository;
import com.ljx.OnlineExamination.Service.ManagerService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ManagerService")
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public ServerResponse<Manager> login(String username, String password) {
        int resultCount = managerRepository.countByUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("管理员用户名错误");
        }

        Manager manager = managerRepository.findByUsernameAndPwd(username, password);
        if (manager == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }

        manager.setPwd(null);
        return ServerResponse.createBySuccess("登陆成功", manager);
    }

}
