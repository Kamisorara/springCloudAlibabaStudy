package com.borrow.service.client;

import com.borrow.service.client.impl.UserClientImpl;
import com.commons.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "user-service", fallback = UserClientImpl.class) //实例名称
public interface UserClient {

    @RequestMapping("/user/{uid}")
    User getUserById(@PathVariable("uid") Long uid);

    @RequestMapping("/user/borrow/{uid}")
    boolean userBorrow(@PathVariable("uid") Long uid);

    @RequestMapping("/user/remain/{uid}")
    int userRemain(@PathVariable("uid") Long uid);

}
