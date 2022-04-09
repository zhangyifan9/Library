package com.library.utils;

import com.library.bean.Member;
import org.springframework.stereotype.Component;

/**
 * @Author: Ruilin Jiang
 * @Date: 2022/4/8 16:30
 * @Version: v1.0
 * @Description:
 * 这是一个保存当前线程用户信息的类
 * 当有任何请求发送到服务器时，请求先被拦截（见controller/interceptor包下的两个拦截器）
 * 拦截器检验请求中是否有cookie，并且含有的cookie是否有效
 * 如果请求中含有的cookie有效，则对该类进行填充
 * 由此可得，如果在执行请求时，该类中可以取出member，则是已登录状态；反之则是退出状态
 */
@Component
public class HostHolder {
    private ThreadLocal<Member> members = new ThreadLocal<>();

    public Member getMember(){
        return members.get();
    }

    public void setMembers(Member member){
        members.set(member);
    }

    public void clear(){
        members.remove();
    }
}
