package com.borrow.service.client.impl;

import com.borrow.service.client.UserClient;
import com.commons.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientImpl implements UserClient {
    @Override
    public User getUserById(Long uid) {
        User user = new User();
        user.setName("我是替代方案");
        return user;
    }

    @Override
    public boolean userBorrow(Long uid) {
        return false;
    }

    @Override
    public int userRemain(Long uid) {
        return 0;
    }
}
