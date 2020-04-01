package com.ljx.OnlineExamination.Service.impl;


import com.ljx.OnlineExamination.Repository.UserRepository;
import com.ljx.OnlineExamination.Service.userService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.User;
import com.ljx.OnlineExamination.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ljx
 */
@Service("userService")
public class userServiceImpl implements userService {
    private final UserRepository userRepository;
    @Autowired
    public userServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userRepository.countByUsername(username);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("该账号未被注册");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userRepository.findByUsernameAndPassword(username, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户名或密码错误");
        }

        user.setPassword(null);
        return ServerResponse.createBySuccess("登陆成功", user);
    }
}
