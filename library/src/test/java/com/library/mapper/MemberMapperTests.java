package com.library.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Zilong Lin
 * @Date: 2022/4/8 21:16
 * @Version: v1.0
 * @Description: test MemberMapper
 */
@SpringBootTest
public class MemberMapperTests {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void testAddBorrowNum() {
        System.out.println(memberMapper.addBorrowNum("19030501"));
    }

    @Test
    void testReduceBorrowNum() {
        System.out.println(memberMapper.reduceBorrowNum("19030501"));
    }
}
