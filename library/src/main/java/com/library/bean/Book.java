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
    private Integer price;
    private String type;
    private String introduction;
    private String barcode;
    private String racknum;
    private Integer copiesnum;
    private Integer total;
    // 记录当前书籍的预约数
    private Integer resvnum;

    public Book(Integer total ,String author, Integer copiesnum,String bname, String type, Integer bid, String isbn, Integer price, String introduction, String barcode, String racknum) {
        this.bname = bname;
        this.type = type;
        this.author = author;
        this.copiesnum = copiesnum;
        this.bid = bid;
        this.isbn = isbn;
        this.price = price;
        this.introduction = introduction;
        this.barcode = barcode;
        this.racknum = racknum;
        this.total = total;
    }
}
