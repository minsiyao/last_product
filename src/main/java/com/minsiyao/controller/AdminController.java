package com.minsiyao.controller;

import com.minsiyao.entity.Admin;
import com.minsiyao.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("Admin")
public class AdminController {
    @Autowired
    private AdminService as;

    @RequestMapping("login")
    @ResponseBody
    public String login(String username, String password, String code, HttpServletRequest request) {
        String code1 = (String) request.getSession().getAttribute("code");
        System.out.println(username);
        System.out.println(password);
        System.out.println(code);
        if (!code1.equals(code)) {
            return "验证码错误";
        }
        request.getSession().setAttribute("code", null);//清空验证码
        Admin admin = as.queryByUsername(username);//查询出来的账号
        if (admin == null) {
            return "账号不存在";
        }
        if (!admin.getPassword().equals(password)) {
            return "密码错误";
        }
        request.getSession().setAttribute("loginUser", admin);
        return "登入成功";
    }

}
