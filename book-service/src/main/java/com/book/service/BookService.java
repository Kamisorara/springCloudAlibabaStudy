package com.book.service;


import com.commons.entity.Book;
import com.sun.org.apache.xpath.internal.operations.Bool;

public interface BookService {

    Book getBookById(Long id);

    //获取剩余书本
    int getBookRemain(Long bookId);

    //更新剩余书本
    Boolean setBookRemain(Long bookId, int count);
}
