package com.library.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @FileName: Reservation
 * @Author: Zilong Lin
 * @Date: 2022/4/10 14:57
 * @Version: v1.0
 * @Description:
 * @UpdateList:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    private int resv_id;
    private int book_id;
    private String reader_id;
    private Timestamp time;

}
