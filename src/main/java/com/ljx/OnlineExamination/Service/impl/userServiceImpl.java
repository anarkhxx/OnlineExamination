package com.ljx.OnlineExamination.Service.impl;


import com.ljx.OnlineExamination.Repository.UserRepository;
import com.ljx.OnlineExamination.Service.UserService;
import com.ljx.OnlineExamination.common.Const;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.User;
import com.ljx.OnlineExamination.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ljx
 */
@Service("userService")
public class userServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public userServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    @Override
    public ServerResponse<User> login(String phone, String password) {
        int resultCount = userRepository.countByPhone(phone);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("该手机号未被注册");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userRepository.findByPhoneAndPassword(phone, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("手机号或密码错误");
        }

        user.setPassword(null);
        return ServerResponse.createBySuccess("登陆成功", user);
    }

    @Override
    public ServerResponse<User> register(User user) {
        int resultCount = userRepository.countByPhone(user.getPhone());
        if (resultCount != 0) {
            return ServerResponse.createByErrorMessage("该手机号已被注册");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        userRepository.save(user);

        return ServerResponse.createBySuccessMessage("注册成功");
    }

}
