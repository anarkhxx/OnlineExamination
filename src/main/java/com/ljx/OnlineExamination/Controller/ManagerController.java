package com.ljx.OnlineExamination.Controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.OnlineExamination.Service.ManagerService;
import com.ljx.OnlineExamination.Service.UserService;
import com.ljx.OnlineExamination.common.Const;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.Manager;
import com.ljx.OnlineExamination.pojo.User;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by lyy on 2020/4/7 下午9:18
 */

@RestController
@RequestMapping("api/admin")
public class ManagerController {
    private final ManagerService managerService;
    @Autowired
    public ManagerController(ManagerService managerService
    ) {

        this.managerService = managerService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ServerResponse<Manager> login(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      HttpSession session
    ){
        ServerResponse<Manager> response = managerService.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_MANAGER, response.getData());
        }
        return response;
    }


}

