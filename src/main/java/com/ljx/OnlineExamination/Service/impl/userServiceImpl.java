package com.ljx.OnlineExamination.Service.impl;


import com.ljx.OnlineExamination.Repository.UserRepository;
import com.ljx.OnlineExamination.Service.UserService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.User;
import com.ljx.OnlineExamination.req.UserModifyReq;
import com.ljx.OnlineExamination.utils.MD5Util;
import com.ljx.OnlineExamination.utils.UpdateUtil;
import org.springframework.beans.BeanUtils;
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

     @Override
     public ServerResponse<User> modifyUser(UserModifyReq userModifyReq){
        User user2 = new User();
        user2.setPhone(userModifyReq.getPhone());
        user2.setUsername(userModifyReq.getUsername());
        user2.setEmail(userModifyReq.getEmail());
        user2.setName(userModifyReq.getName());
        user2.setIdentification(userModifyReq.getIdentification());
        user2.setSex(userModifyReq.getSex());
        User user = new User();
        // 从数据库中获取对象
        User original =new User();
        original = userRepository.findByPhone(user2.getPhone());;
        // 复制想要更改的字段值
        BeanUtils.copyProperties(user2, original, UpdateUtil.getNullPropertyNames(user2));
        // 更新操作
        user = userRepository.save(original);
        user.setPwd(null);
        return ServerResponse.createBySuccess("修改成功", user);
    }
}
