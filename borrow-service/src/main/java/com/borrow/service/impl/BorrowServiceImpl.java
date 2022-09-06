package com.borrow.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.borrow.entity.resp.UserBorrowDetail;
import com.borrow.mapper.BorrowMapper;
import com.borrow.service.BorrowService;
import com.borrow.service.client.BookClient;
import com.borrow.service.client.UserClient;
import com.commons.entity.Book;
import com.commons.entity.Borrow;
import com.commons.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    private BorrowMapper borrowMapper;

    @Resource
    private BookClient bookClient;

    @Resource
    private UserClient userClient;

    @Override
    @SentinelResource(value = "getBorrow", blockHandler = "blocked")
    public UserBorrowDetail getUserBorrowDetailByUid(Long uid) {
        List<Borrow> borrow = borrowMapper.getBorrowByUid(uid);
        //获取获取用户信息
        User user = userClient.getUserById(uid);
        //获取每一本书的详细信息
        List<Book> bookList = borrow.stream().map(b -> bookClient.getBookById(b.getBid())).collect(Collectors.toList());
        return new UserBorrowDetail(user, bookList);
    }


    //替代方案，注意参数和返回值需要保持一致，并且参数最后还需要额外添加一个BlockException
    public UserBorrowDetail blocked(Long uid, BlockException e) {
        return new UserBorrowDetail(null, Collections.emptyList());
    }
}
