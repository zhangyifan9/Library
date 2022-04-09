package com.library.controller;

import com.library.annotation.LoginRequired;
import com.library.bean.Member;
import com.library.service.MemberService;
import com.library.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Ruilin Jiang
 * @Date: 2022/4/8 16:30
 * @Version: v1.0
 * @Description: 接收登录、注册、登出请求
 */
@RestController
public class LoginController {
    private static final int DEFAULT_EXPIRED_SECONDS = 12*3600; //默认情况下cookie有效期12小时
    private static final int REMEMBER_EXPIRED_SECONDS = 7*24*3600;//用户勾选“记住我"时有效期7天

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private MemberService memberService;

    private String cookiePath = "/library"; //当前项目下所有都对cookie有效

    /**
     * 登录接口
     * 发放给用户登录凭证并保存至数据库
     * 前端可以在用户访问网页时先自动访问一下这个接口，如果返回“用户已登录”信息则直接跳转为登录状态，
     *         不需要重新使用账号密码登录，这是我猜测的自动登录实现
     * @param memId 用户id
     * @param pwd 密码
     * @param rememberMe 是否“记住我”
     * @param response 给前端的响应信息
     * @return 返回处理结果信息（key为"msg"）和状态
     */
    @CrossOrigin(origins = "*", allowCredentials = "true")//跨域支持，allowCredentials表示是否允许接收和发送cookie
    @RequestMapping(path="/login", method= RequestMethod.POST)
    public Map<String,Object> login(String memId, String pwd,
                                    boolean rememberMe,
                                    Integer mtype,
                                    HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        //这里对用户是否已登录进行验证，若已登录就返回已登录信息
        //前端可以在用户访问网页时先访问一下这个接口，如果返回“用户已登录”信息则直接跳转为登录状态
        //不需要重新使用账号密码登录
        if(hostHolder.getMember()!=null){
            map.put("msg","用户已登录");
            return map;
        }

        //对用户是否勾选”记住我“进行判断
        int expiredSeconds = rememberMe?REMEMBER_EXPIRED_SECONDS:DEFAULT_EXPIRED_SECONDS;
        map = memberService.doLogin(memId,pwd,expiredSeconds,mtype);
        //如果上一行doLogin返回的map中包含ticket键，则代表登录成功，需要创建cookie返回给前端
        if(map.containsKey("ticket")){
            String ticket = map.get("ticket").toString();
            Cookie cookie = new Cookie("ticket",ticket);
            cookie.setMaxAge(expiredSeconds);
            cookie.setPath(cookiePath);
            //将cookie放入响应中
            response.addCookie(cookie);
            //从将要返回给前端的map中去除ticket，只保留状态码和信息
            map.remove("ticket");
        }

        return map;
    }

    /**
     * 注册接口
     * @param member
     * @return
     */
    @CrossOrigin
    @RequestMapping("/register")
    public Map<String,Object> register(Member member){
        return memberService.doRegister(member);
    }

    /**
     * 退出接口
     * 退出的实现就是删除数据库中该用户持有的凭证，下次就无法自动登录了
     * @param ticket 从cookie中获取的登录凭证
     * @return 返回操作信息
     */
//    @LoginRequired
    @CrossOrigin(origins = "*",allowCredentials = "true")
    @RequestMapping(path = "/logout",method = RequestMethod.GET)
    public Map<String,Object> logout(@CookieValue String ticket){
        Map<String,Object> map = new HashMap<>();
//        //防止多次退出
//        if(hostHolder.getMember()==null){
//            map.put("msg","已经登出");
//            return map;
//        }
        String msg = memberService.doLogout(ticket);

        map.put("msg",msg);
        return map;
    }
}
