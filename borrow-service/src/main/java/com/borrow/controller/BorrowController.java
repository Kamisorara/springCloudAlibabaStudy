package com.borrow.controller;

import com.alibaba.fastjson.JSONObject;
import com.borrow.entity.resp.UserBorrowDetail;
import com.borrow.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @RequestMapping("/borrow/{uid}")
    UserBorrowDetail findUserBorrows(@PathVariable("uid") Long uid) {
        return borrowService.getUserBorrowDetailByUid(uid);
    }

    //测试
    @RequestMapping("/borrow2/{uid}")
    String findUserBorrows2(@PathVariable("uid") Long uid) {
        return "Hello World";
    }

    //限流页面
    @RequestMapping("/blocked")
    JSONObject blocked() {
        JSONObject object = new JSONObject();
        object.put("code", 403);
        object.put("success", false);
        object.put("massage", "您的请求频率过快，请稍后再试！");
        return object;
    }


    //用户借阅api
    @RequestMapping("/borrow/take/{uid}/{bid}")
    JSONObject borrow(@PathVariable("uid") Long uid,
                      @PathVariable("bid") Long bid) {
        borrowService.doBorrow(uid, bid);

        JSONObject object = new JSONObject();
        object.put("code", "200");
        object.put("success", false);
        object.put("message", "借阅成功！");
        return object;
    }
}
