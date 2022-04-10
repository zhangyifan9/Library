package com.library.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @FileName: ReservationMapperTests
 * @Author: Zilong Lin
 * @Date: 2022/4/10 15:11
 * @Version: v1.0
 * @Description: Test ReservationMapper
 * @UpdateList:
 */
@SpringBootTest
public class ReservationMapperTests {

    @Autowired
    private ReservationMapper reservationMapper;

    @Test
    void testBasicOperation() {
        System.out.println(reservationMapper.addReservation("19030503", 7));
        System.out.println(reservationMapper.getEarliestByBookId(7));
        System.out.println(reservationMapper.deleteReservation(3));
    }

    @Test
    void testGetReservation() {
        if(reservationMapper.getResvByReadAndBook("19030503", 7) == null) {
            System.out.println("未预约");
        }
        else {
            System.out.println("已预约");
        }
    }
}
