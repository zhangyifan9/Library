package com.library.mapper;

import com.library.bean.LoginTicket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginTicketMapper {
    //添加新凭证
    public int insertTicket(LoginTicket ticket);
    //删除凭证
    public int deleteTicket(String ticket);
    //根据ticket值查询完整登录凭证
    public LoginTicket findLoginTicket(String ticket);
}
