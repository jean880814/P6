package com.study.p6_3.dubbo.impl;

import com.jean.model.User;
import com.jean.service.UserService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class UserServiceImpl extends AbstractServiceImpl implements UserService {
    @Override
    public String getUser(String id) {
        System.out.println("getUser + " + id);
        return "hello jean";
    }

    @Override
    public void saveUser(User user) {
        System.out.println(user);
    }
}
