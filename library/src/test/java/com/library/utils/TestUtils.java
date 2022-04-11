package com.library.utils;

import com.library.bean.ResData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @FileName: TestUtils
 * @Author: Zilong Lin
 * @Date: 2022/4/11 15:13
 * @Version: v1.0
 * @Description: 工具类测试
 * @UpdateList:
 */
@SpringBootTest
public class TestUtils {

    @Test
    void testISBN() {

        String isbn = "9787508622187";
        ResData resData = ISBNUtils.getBookInfoByISBN(isbn);
        System.out.println(resData);
        System.out.println(resData.getData().get("author"));
        System.out.println(resData.getData().get("photoUrl"));
        System.out.println("=======================");
        System.out.println(resData.getData().get("published").getClass());
        System.out.println(resData.getData().get("published"));
    }

}
