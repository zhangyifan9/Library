package com.library.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @Author: Zilong Lin
 * @Date: 2022/4/5 22:18
 * @Version: v1.0
 * @Description: Test BorrowMapper
 */
@SpringBootTest
public class BorrowMapperTests {

    @Autowired
    private BorrowMapper borrowMapper;

    @Test
    void testCheckByReaderId() {
        System.out.println(borrowMapper.checkByReaderId("19030502"));
    }

    @Test
    void testAdd() {
//        System.out.println(borrowMapper.addBorrow("19030501", 5));
    }

    // borrow_id是自动生成的，根据表项目内容测试
    @Test
    void testRenew() {
        System.out.println(borrowMapper.renewBorrow(6));
    }

    // borrow_id是自动生成的，根据表项目内容测试
    @Test
    void testDelte() {
        System.out.println(borrowMapper.deleteBorrow(6));
    }

    @Test
    void testCalDate() {
//        System.out.println(borrowMapper.backMinusCur(7));
//        System.out.println(borrowMapper.backMinusLend(7));
    }

    @Test
    void testGetDate() {
        Date lend_date = borrowMapper.getLendDate(7);
        Date back_date = borrowMapper.getBackDate(7);
        System.out.println(lend_date);
        System.out.println(back_date);
    }

    @Test
    void testGetFine() {
//        System.out.println(borrowMapper.getFine(9));
//        System.out.println(borrowMapper.getFine(10));
//        System.out.println(borrowMapper.getFine(11));
    }
}
