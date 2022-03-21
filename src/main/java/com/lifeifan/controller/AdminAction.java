package com.lifeifan.controller;
import com.lifeifan.pojo.Admin;
import com.lifeifan.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminAction {
    @Autowired
    private AdminService adminService;
    /**
     * 实现登陆判断并跳转
     * 所有界面层一定会有业务逻辑层对象
     */
    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest request){
        Admin admin = adminService.login(name, pwd);
        if (admin!=null){
            request.setAttribute("admin",admin);
            return "main";
        }else {
            request.setAttribute("errmsg","用户账户或密码不正确！");
            return "login";
        }
    }
}
