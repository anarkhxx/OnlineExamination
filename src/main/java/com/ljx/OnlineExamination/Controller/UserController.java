package com.ljx.OnlineExamination.Controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.OnlineExamination.Repository.UserRepository;
import com.ljx.OnlineExamination.Service.UserService;
import com.ljx.OnlineExamination.pojo.User;
import com.ljx.OnlineExamination.req.UserModifyReq;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ljx.OnlineExamination.common.ServerResponse;
import com.ljx.OnlineExamination.common.Const;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private String apiUrl = "https://sms_developer.zhenzikj.com";
    private String appId = "100880";
    private String appSecret = "c20ca481-2a39-4863-94d3-0fc00d395e79";
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

    @PostMapping("/getCode")
    @ResponseBody
    public ServerResponse<User> getCode(String phone,
                                      HttpSession session
    ){
        try {
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId,appSecret);
            Map<String, String> params = new HashMap<String, String>();
            String code = String.valueOf(new Random().nextInt(999999));
            params.put("message", "您的验证码为:" + code + "，该码有效期为5分钟，该码只能使用一次!");
            params.put("number", phone);
            String result = client.send(params);
            JSONObject json = JSONObject.parseObject(result);
            if (json.getIntValue("code")!=0){//发送短信失败
                return  ServerResponse.createByErrorMessage("发送验证码失败");
            }
            json = new JSONObject();
            json.put("phone",phone);
            json.put("code",code);
            json.put("createTime",System.currentTimeMillis());
            session.setAttribute("code",json);
        }catch (Exception e){
            e.printStackTrace();
            return  ServerResponse.createByErrorMessage("发送验证码失败");
        }
        return  ServerResponse.createBySuccessMessage("发送验证码成功");
    }

    @PostMapping("/register")
    @ResponseBody
    public ServerResponse<User> register(String phone,
                                        String password,
                                        String vfcode,
                                        HttpSession session
    ){
        JSONObject userCode = (JSONObject)session.getAttribute("code");
        if(!vfcode.equals(userCode.getString("code"))){
            return  ServerResponse.createByErrorMessage("验证码错误");
        }
        ServerResponse<User> response = userService.register(phone,password);
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

    //个人信息填写、修改 按id
    @PostMapping("/modify")
    @ResponseBody
    public ServerResponse<User> modify(UserModifyReq userModifyReq,
                                        HttpSession session
    ){
        ServerResponse<User> response = userService.modifyUser(userModifyReq);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }


}

