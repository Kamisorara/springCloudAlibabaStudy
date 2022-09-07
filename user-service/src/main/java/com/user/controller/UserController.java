package com.user.controller;


import com.commons.entity.User;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //此注解可以实现自动刷新Nacos config配置文件中的信息
public class UserController {
    @Autowired
    private UserService userService;

    @Value("${text.lbwnb}")
    String test;

    @RequestMapping(value = "/user/{uid}", method = RequestMethod.GET)
    public User findUserById(@PathVariable("uid") Long uid) {
        System.out.println(test);
        return userService.getUserById(uid);
    }

    @RequestMapping("/user/remain/{uid}")
    public int userRemain(@PathVariable("uid") Long uid) {
        return userService.getRemain(uid);
    }

    @RequestMapping("/user/borrow/{uid}")
    public boolean userBorrow(@PathVariable("uid") Long uid) {
        int remain = userService.getRemain(uid);
        return userService.setRemain(uid, remain - 1);
    }
}
