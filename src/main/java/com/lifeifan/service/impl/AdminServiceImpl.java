package com.lifeifan.service.impl;

import com.lifeifan.mapper.AdminMapper;
import com.lifeifan.pojo.Admin;
import com.lifeifan.pojo.AdminExample;
import com.lifeifan.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {
        /**
         *  根据传入的用户，到DB查询相应用户对象
         *  如果有条件，则一定创建AdminExample对象
         *  用来封装条件
         *  （逆向工程创建的AdminMapper.xml和AdminExample）
         */
        AdminExample example = new AdminExample();
        example.createCriteria().andANameEqualTo(name);
        List<Admin> admins = adminMapper.selectByExample(example);
        if (admins.size()>0){
            Admin admin = admins.get(0);
            if (pwd.equals(admin.getaPass())) {
                return admin;
            }
        }
        return null;
    }
}
