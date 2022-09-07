package com.borrow.service.client;

import com.commons.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("book-service") //实例名称
public interface BookClient {

    @RequestMapping("/book/{bid}")
    Book getBookById(@PathVariable("bid") Long bid);

    @RequestMapping("/book/borrow/{bid}")
    boolean bookBorrow(@PathVariable("bid") Long bid);

    @RequestMapping("/book/remain/{bid}")
    int bookRemain(@PathVariable("bid") Long bid);
}
