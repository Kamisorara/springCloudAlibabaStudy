package com.user.service;


import com.commons.entity.User;

public interface UserService {
    User getUserById(Long userId);

    //获取用户可借阅数量
    int getRemain(Long userId);

    //更新用户可借阅数量
    Boolean setRemain(Long userId, int count);
}
