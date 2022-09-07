package com.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.commons.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getUserById(Long userId);

    //获取用户可借阅数量
    int getUserBookRemain(Long userId);

    //更新用户可借阅数量
    int updateBookCount(@Param("userId") Long userId, @Param("count") int count);
}
