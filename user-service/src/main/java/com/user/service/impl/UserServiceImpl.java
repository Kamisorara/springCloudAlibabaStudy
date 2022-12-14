package com.user.service.impl;


import com.commons.entity.User;
import com.user.mapper.UserMapper;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public int getRemain(Long userId) {
        return userMapper.getUserBookRemain(userId);
    }

    @Override
    public Boolean setRemain(Long userId, int count) {
        return userMapper.updateBookCount(userId, count) > 0;
    }
}
