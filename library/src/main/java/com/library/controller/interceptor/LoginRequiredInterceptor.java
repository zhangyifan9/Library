package com.library.controller.interceptor;

import com.library.annotation.LibrarianRequired;
import com.library.annotation.LoginRequired;
import com.library.utils.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: Ruilin Jiang
 * @Date: 2022/4/8 16:30
 * @Version: v1.0
 * @Description:
 * 该拦截器后于LoginTicketInterceptor执行
 * 该拦截器获取请求想要访问的api方法，判断是否有@LoginRequired注解（也就是是否要登录才能访问）
 * 若需要登录才能访问且处于登录状态或是不需要登录，则放行；若需要登录却不是登录状态则直接返回；前端需要跳转至登录界面
 */
@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Autowired
    //尝试获取当前的用户，如果能获取到则已登录
    private HostHolder hostHolder;

    //在请求的最初判断是否已登录
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断拦截的对象Object是不是一个方法
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();;

            //获取方法的注解
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
            LibrarianRequired librarianRequired = method.getAnnotation(LibrarianRequired.class);

            //当前方法需要登录，但是user为null表示没有登录；拒绝后续的请求
            //配置中是所有动态资源请求都要拦截，但是这里人为的筛选了只处理带有注解的部分
            if(loginRequired != null && hostHolder.getMember() == null)
            {
                //response.getWriter().write("请先登录");
                return false;
            }

            //当前方法需要管理员身份，但是如果member类型不是管理员就不能继续访问
            if(librarianRequired != null && hostHolder.getMember().getMtype() != 3){
                return false;
            }
        }
        return true;
    }
}
