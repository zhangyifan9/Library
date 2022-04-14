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

    private String isbn;
    private String bname ;
    private String author;
    private Double price;
    private String type;
    private String introduction;
    private Integer copiesnum;
    private String publishing;
    private String published;
    private String photoUrl;

}