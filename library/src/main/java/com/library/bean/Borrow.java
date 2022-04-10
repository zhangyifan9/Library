package com.library.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


/**
 * @Author: Zilong Lin,Ning Zhang
 * @Date: 2022/4/5 22:14
 * @Version: v1.0
 * @Description: Borrow
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {

    private Integer borrow_id;
    private Integer reader_id;
    private Integer book_id;
    private String book_name;
    private Date lend_date;
    private Date back_date;
}
