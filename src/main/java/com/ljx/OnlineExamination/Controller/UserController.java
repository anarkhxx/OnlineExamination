package com.ljx.OnlineExamination.Controller;

import com.ljx.OnlineExamination.Service.UserService;
import com.ljx.OnlineExamination.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.common.Const;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("api/user")
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
    public ServerResponse<User> register(String phone,
                                      String vfcode,
                                      HttpSession session
    ){
        ServerResponse<User> response = userService.register(phone,vfcode);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @PostMapping("/message")
    @ResponseBody
    public ServerResponse<User> message(String phone,
                                         String password,
                                         HttpSession session
    ){
        ServerResponse<User> response = userService.message(phone,password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @PostMapping("/vfphone")
    @ResponseBody
    public ServerResponse<User> vfphone(String phone,
                                        HttpSession session
    ){
        ServerResponse<User> response = userService.vfphone(phone);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }



}

