package com.library.utils;

import com.library.bean.ResData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;

/**
 * @FileName: ISBNUtils
 * @Author: Zilong Lin,ZhangNing
 * @Date: 2022/4/14 14:44
 * @Version: v1.0
 * @Description: 通过ISBN码获取书籍信息
 * @UpdateList:
 */
//@Slf4j
//public class ISBNUtils {
//
//    private static String url = "https://api.jike.xyz/situ/book/isbn/";
//    private static String apikey = "12379.71be299ccae7fc2d7be146a7bba627a6.6c340c05a0ab8a65f57fec9befc030a8";
//
//    public static ResData getBookInfoByISBN(String isbn) {
//
//        url = url+isbn+"?apikey="+apikey;
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(url ,ResData.class);
//    }
//
//}
@Slf4j
public class ISBNUtils {

    private static String front_url = "https://openlibrary.org/api/books?bibkeys=ISBN:";
    private static String back_url = "&jscmd=data&format=json";

    public static String getBookInfoByISBN(String isbn) {
//        String url = front_url+isbn+back_url;
        RestTemplate restTemplate = new RestTemplate();
//
//
//        URI uri = UriComponentsBuilder.fromUriString(url).build(true).toUri();
        String url = "https://openlibrary.org/api/books?bibkeys=ISBN:9780980200447&jscmd=data&format=json";

        String template = restTemplate.getForObject(url, String.class);
//        System.out.println(uri);
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
//
//        System.out.println(responseEntity.getBody());

        return template;
    }

}

