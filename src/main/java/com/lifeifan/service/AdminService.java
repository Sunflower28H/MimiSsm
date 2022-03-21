package com.lifeifan.service;

import com.lifeifan.pojo.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminService {
    //登陆判断
    Admin login(String name, String pwd);
}
