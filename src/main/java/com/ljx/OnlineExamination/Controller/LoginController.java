package com.ljx.OnlineExamination.Controller;

import com.ljx.OnlineExamination.Service.userService;
import com.ljx.OnlineExamination.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.common.Const;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class LoginController {
    private final userService US;

    @Autowired
    public LoginController(userService US
                              ) {

        this.US = US;
    }

    @PostMapping("/login")
    @ResponseBody
    public ServerResponse<User> login(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      HttpSession session
                      ){
        ServerResponse<User> response = US.login(username, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }
}

