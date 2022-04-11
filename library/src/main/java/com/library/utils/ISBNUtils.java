package com.library.utils;

import com.library.bean.ResData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

/**
 * @FileName: ISBNUtils
 * @Author: Zilong Lin
 * @Date: 2022/4/11 14:44
 * @Version: v1.0
 * @Description: 通过ISBN码获取书籍信息
 * @UpdateList:
 */
@Slf4j
public class ISBNUtils {

    private static String url = "https://api.jike.xyz/situ/book/isbn/";
    private static String apikey = "12379.71be299ccae7fc2d7be146a7bba627a6.6c340c05a0ab8a65f57fec9befc030a8";

    public static ResData getBookInfoByISBN(String isbn) {

        url = url+isbn+"?apikey="+apikey;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url ,ResData.class);
    }

}