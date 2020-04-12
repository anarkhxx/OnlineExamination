package com.ljx.OnlineExamination.Service.impl;


import com.alibaba.fastjson.JSONObject;
import com.ljx.OnlineExamination.Repository.UserRepository;
import com.ljx.OnlineExamination.Service.UserService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.User;
import com.ljx.OnlineExamination.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * @author ljx
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    @Override
    public ServerResponse<User> login(String phone, String password) {
        int resultCount = userRepository.countByPhone(phone);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("该手机号未被注册");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userRepository.findByPhoneAndPwd(phone, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("手机号或密码错误");
        }

        user.setPwd(null);
        return ServerResponse.createBySuccess("登陆成功", user);
    }

    @Override
    public ServerResponse<User> register(String phone,String password) {
        User user=new User();
        user.setPhone(phone);
        user.setPwd(MD5Util.MD5EncodeUtf8(password));
        userRepository.save(user);
        return ServerResponse.createBySuccessMessage("注册成功");
    }


    @Override
    public ServerResponse<User> vfphone(String phone) {
        User user=userRepository.findByPhone(phone);
        if (user != null){
            return ServerResponse.createByErrorMessage("该手机号已被注册");
        }
        return ServerResponse.createBySuccessMessage("该手机号未被注册");
    }
}
