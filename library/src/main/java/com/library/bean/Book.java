package com.library.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Zilong Lin ,Ning Zhang
 * @Date: 2022/4/5 13:20
 * @Version: v1.0
 * @Description: Book entity Object
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer bid;
    private String bname ;
    private String author;
    private String isbn;
    private Double price;
    private String type;
    private String introduction;
    private String racknum;
    private Integer copiesnum;
    private Integer total;
    private String publishing;
    private String published;
    private String photoUrl;

}