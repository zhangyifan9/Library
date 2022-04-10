package com.library.mapper;

import com.library.bean.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @FileName: ReservationMapper
 * @Author: Zilong Lin
 * @Date: 2022/4/10 14:53
 * @Version: v1.0
 * @Description: Operations related to the reservation table
 * @UpdateList:
 */
@Mapper
public interface ReservationMapper {

    public int addReservation(@Param("reader_id") String reader_id, @Param("book_id") int book_id);

    public int deleteReservation(@Param("resv_id") int resv_id);

    public Reservation getEarliestByBookId(@Param("book_id") int book_id);

    // 查询是否有指定读者和书籍的记录
    public Reservation getResvByReadAndBook(@Param("reader_id") String reader_id, @Param("book_id") int book_id);

}
