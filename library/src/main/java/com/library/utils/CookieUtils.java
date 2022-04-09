package com.library.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Ruilin Jiang
 * @Date: 2022/4/8 16:30
 * @Version: v1.0
 * @Description:
 */
public class CookieUtils {

    public static String getValue(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();

        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals(name)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
