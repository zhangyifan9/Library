package com.library.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Zilong Lin
 * @Date: 2022/4/5 20:13
 * @Version: v1.0
 * @Description: An entity class used to encapsulate paging information.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPage {

    private int total;  // the total number of records
    private List<?> data;

}
