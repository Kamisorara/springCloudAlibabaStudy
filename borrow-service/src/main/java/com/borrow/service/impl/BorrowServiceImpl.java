package com.borrow.service.impl;

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
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public UserBorrowDetail getUserBorrowDetailByUid(Long uid) {
        List<Borrow> borrow = borrowMapper.getBorrowByUid(uid);
        //获取获取用户信息
        User user = userClient.getUserById(uid);
        //获取每一本书的详细信息
        List<Book> bookList = borrow
                .stream()
                .map(b -> bookClient.getBookById(b.getBid()))
                .collect(Collectors.toList());
        return new UserBorrowDetail(user, bookList);
    }
}
