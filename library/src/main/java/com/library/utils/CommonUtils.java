package com.library.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

/**
 * @Author: Ruilin Jiang
 * @Date: 2022/4/8 16:30
 * @Version: v1.0
 * @Description:
 * 以下类包含了一些七七八八的工具方法
 */
public class CommonUtils {
    //对密码进行md5加密的工具类
    //其实从安全性考虑，应该前端就加密后再进行传输
    //但是本次就不考虑那么多，或是之后在修改
    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
