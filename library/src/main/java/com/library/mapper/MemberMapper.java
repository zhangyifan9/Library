package com.library.mapper;

import com.library.bean.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    //根据id查询用户
    public Member findMemberById(String mem_id);
    //插入用户
    public int insertMember(Member member);
    //更新密码
    public int updatePwd(String id,String newPwd);
    //更新住址
    public int updateAddress(String id,String address);
    //更新电话
    public int updatePhone(String id,String phone);
    // 借阅数+1
    public int addBorrowNum(String id);
    // 借阅数-1
    public int reduceBorrowNum(String id);
}
