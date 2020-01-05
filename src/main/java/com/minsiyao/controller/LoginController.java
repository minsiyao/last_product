package com.minsiyao.controller;

import com.alibaba.fastjson.JSON;
import com.minsiyao.dto.Error;
import com.minsiyao.entity.User;
import com.minsiyao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("account")
public class LoginController {
    @Autowired
    private UserService us;

    /*
     * 登入
     * */
    @RequestMapping("login")
    @ResponseBody
    public Object login(String phone, String password, String code) {
        User user = us.queryByPhone(phone);
        if (password != null) {
            if (password.equals(user.getPassword())) {
                return JSON.toJSONString(user);
            } else {
                return JSON.toJSONString(new Error("-200", "密码错误"));
            }
        } else if (code != null) {
            if (code.equals("qwer")) {
                return JSON.toJSONString(user);
            } else {
                return JSON.toJSONString(new Error("-200", "密码错误"));
            }
        } else {
            return JSON.toJSONString(new Error("-200", "密码错误"));
        }
    }

    /*
     * 修改
     * */
    @ResponseBody
    @RequestMapping("modify")
    public Object modify(String uid, String gender, String photo, String location, String description, String nickname, String province, String city, String password) {
        return null;
    }

}
