package com.library.controller.interceptor;

import com.library.bean.LoginTicket;
import com.library.bean.Member;
import com.library.mapper.LoginTicketMapper;
import com.library.mapper.MemberMapper;
import com.library.utils.CookieUtils;
import com.library.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author: Ruilin Jiang
 * @Date: 2022/4/8 16:30
 * @Version: v1.0
 * @Description:
 * 该拦截器的prehandle方法先于LoginRequiredInterceptor执行
 * 其获取请求cookie中包含的ticket值，这是一个用户的唯一凭证
 * 若有该凭证，则从数据查询用户信息存入HostHolder中待用，表示用户已登录
 * 之后执行LoginRequiredInterceptor的prehandle方法
 */
@Component
public class LoginTicketInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = CookieUtils.getValue(request,"ticket");

        if(ticket != null){
            //查询凭证
            LoginTicket loginTicket = loginTicketMapper.findLoginTicket(ticket);

            //要判断查询到的凭证是否有效，有效三要素：
            // 凭证不为空；状态是1表示有效；超时时间晚于当前时间
            if(loginTicket != null && loginTicket.getStatus() == 1 && loginTicket.getExpired().after(new Date())){
                //凭证有效，则可以利用凭证查询user
                Member member = memberMapper.findMemberById(loginTicket.getMem_id());

                /**
                 * 在本次请求中持有用户
                 * 为了后面的使用，要把user暂存
                 * 一个服务器能够处理多个浏览器请求，每个浏览器访问服务器，服务器会创建一个独立的线程处理请求
                 * 服务器处理请求时，是一个多线程的环境
                 * 在存储用户时，要考虑到多线程的情况
                 * 线程隔离，防止并发访问干扰；在多线程之间隔离存储用户对象
                 */
                hostHolder.setMembers(member);
            }

        }
        return true;


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
