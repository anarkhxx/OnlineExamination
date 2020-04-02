package com.ljx.OnlineExamination.Controller;

import com.ljx.OnlineExamination.Service.UserService;
import com.ljx.OnlineExamination.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.common.Const;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService
                              ) {

        this.userService = userService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ServerResponse<User> login(@RequestParam("phone") String phone,
                                      @RequestParam("password") String password,
                                      HttpSession session
                      ){
        ServerResponse<User> response = userService.login(phone, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @PostMapping("/register")
    @ResponseBody
    public ServerResponse<User> register(User user,
                                      HttpSession session
    ){
        ServerResponse<User> response = userService.register(user);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

}

