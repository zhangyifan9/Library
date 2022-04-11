package com.library.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @FileName: ResData
 * @Author: Zilong Lin
 * @Date: 2022/4/11 15:49
 * @Version: v1.0
 * @Description:
 * @UpdateList:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResData {

    private int ret;
    private String msg;
    private Map<String, Object> data;

}
