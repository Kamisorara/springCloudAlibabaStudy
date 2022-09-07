package com.commons.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (Borrow)表实体类
 *
 * @author Kamisora
 * @since 2022-07-24 11:02:25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "db_borrow")
public class Borrow {
    //主键id
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long uid;

    private Long bid;

}

