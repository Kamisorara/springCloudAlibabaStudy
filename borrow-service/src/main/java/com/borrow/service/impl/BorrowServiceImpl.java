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
import io.seata.spring.annotation.GlobalTransactional;
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

    @GlobalTransactional //开始分布式事务
    @Override
    public Boolean doBorrow(Long uid, Long bid) {
//        System.out.println(RootContext.getXID()); //打印XID
        //1. 判断图书和用户是否都支持借阅
        if (bookClient.bookRemain(bid) < 1) throw new RuntimeException("图书数量不足");
        if (userClient.userRemain(uid) < 1) throw new RuntimeException("用户借阅量不足");
        //2. 首先将图书的数量-1
        if (!bookClient.bookBorrow(bid)) throw new RuntimeException("在借阅图书时出现错误！");
        //3. 添加借阅信息
        if (borrowMapper.getBorrow(uid, bid) != null) throw new RuntimeException("此书籍已经被此用户借阅了！");
        if (borrowMapper.addBorrow(uid, bid) <= 0) throw new RuntimeException("在录入借阅信息时出现错误！");
        //4. 用户可借阅-1
        if (!userClient.userBorrow(uid)) throw new RuntimeException("在借阅时出现错误！");
        //完成
        return true;
    }


    //替代方案，注意参数和返回值需要保持一致，并且参数最后还需要额外添加一个BlockException
    public UserBorrowDetail blocked(Long uid, BlockException e) {
        return new UserBorrowDetail(null, Collections.emptyList());
    }
}
