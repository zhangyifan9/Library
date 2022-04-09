package com.library.mapper;

import com.library.bean.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * @Author: Zilong Lin
 * @Date: 2022/4/5 22:07
 * @Version: v1.0
 * @Description: Operations related to the borrowmanage table
 */
@Mapper
public interface BorrowMapper {

    // Get all lending information from the user through the reader_id
    List<Borrow> checkByReaderId(String reader_id);

    // 借书日期和还书日期由sql自动计算生成
    int addBorrow(@Param("reader_id") String reader_id, @Param("book_id") int book_id);

    // 续借一次只能续借10天
    int renewBorrow(@Param("borrow_id") int borrow_id);

    // 还书操作
    int deleteBorrow(int borrow_id);

    // 计算back_date和当前时间之差
    int calDiffCur(int borrow_id);

    // 计算back_date和lend_date之差
    int calDiffLend(int borrow_id);

    // 获取borrow_id对应的book_id
    int getBookId(int borrow_id);

}
