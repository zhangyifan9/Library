package com.library.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Copies {
    private Integer barcode;
    private String isbn;
    private  String racknum;
    private Integer reserved;
    private  Integer borrowed;
}

