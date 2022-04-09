package com.library.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Zilong Lin, Ning Zhang
 * @Date: 2022/4/5 22:14
 * @Version: v1.0
 * @Description: Borrow
 */
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Librarian {
    private Integer id;
    private String mname;
    private String pwd;

    public Librarian(Integer lId, String mName, String pwd){
        this.id = id;
        this.mname = mname;
        this.pwd = pwd;
    }

}
