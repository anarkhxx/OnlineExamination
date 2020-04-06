package com.ljx.OnlineExamination.Service.impl;


import com.alibaba.fastjson.JSONObject;
import com.ljx.OnlineExamination.Repository.UserRepository;
import com.ljx.OnlineExamination.Service.UserService;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.pojo.User;
import com.ljx.OnlineExamination.utils.MD5Util;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


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
    public ServerResponse<User> register(String phone,String vfcode) {
        User user=userRepository.findByPhone(phone);
        String code=user.getVfcode();
        if (!vfcode.equals(code)){
            return ServerResponse.createByErrorMessage("验证码错误");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<User> message(String phone,String password) {
        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "100880","c20ca481-2a39-4863-94d3-0fc00d395e79");
        Map<String, String> params = new HashMap<String, String>();
        String code=GetRandomCode();
        params.put("message", "验证码为:"+code);
        params.put("number", phone);
        JSONObject json = null;
        try {

            String result = client.send(params);
            json = JSONObject.parseObject(result);
            if (json.getString("code").equals("0")){
                User user=new User();
                user.setPhone(phone);
                user.setPwd(MD5Util.MD5EncodeUtf8(password));
                user.setVfcode(code);
                userRepository.save(user);
            }
        }catch (Exception e){
           e.printStackTrace();
        }

        return ServerResponse.createBySuccessMessage("发送验证码成功");
    }

    public static String GetRandomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
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
