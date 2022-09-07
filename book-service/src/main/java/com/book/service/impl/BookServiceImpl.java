package com.book.service.impl;


import com.book.mapper.BookMapper;
import com.book.service.BookService;
import com.commons.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Book getBookById(Long id) {
        return bookMapper.getBookById(id);
    }

    @Override
    public int getBookRemain(Long bookId) {
        return bookMapper.getRemain(bookId);
    }

    @Override
    public Boolean setBookRemain(Long bookId, int count) {
        return bookMapper.setRemain(bookId, count) > 0;
    }
}
