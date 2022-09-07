package com.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.commons.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookMapper extends BaseMapper<Book> {

    Book getBookById(Long bookId);

    //获取剩余书本
    int getRemain(Long bookId);

    //借阅减少书籍借阅
    int setRemain(@Param("bookId") Long bookId, @Param("count") int count);
}
