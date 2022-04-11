package com.library.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.bean.ResData;

/**
 * @FileName: JsonUtils
 * @Author: Zilong Lin
 * @Date: 2022/4/11 15:08
 * @Version: v1.0
 * @Description: Json与实体类相互转换
 * @UpdateList:
 */
public class JsonUtils {

    public static String getJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(object);
        return str;
    }

    public static ResData JsonToResData(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, ResData.class);
    }

}
