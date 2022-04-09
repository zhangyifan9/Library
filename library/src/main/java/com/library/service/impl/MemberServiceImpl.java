package com.library.service.impl;


import com.library.bean.Borrow;
import com.library.bean.LoginTicket;
import com.library.bean.Member;
import com.library.mapper.BookMapper;
import com.library.mapper.BorrowMapper;
import com.library.mapper.LoginTicketMapper;
import com.library.mapper.MemberMapper;
import com.library.service.MemberService;
import com.library.utils.CommonUtils;
import com.library.utils.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BookMapper bookMapper;

    //登录的业务逻辑
    @Override
    public Map<String,Object> doLogin(String mem_id,String pwd,int expiredSeconds,int mtype){
        Map<String,Object> map = new HashMap<>();

        if(StringUtils.isBlank(mem_id)){
            //map.put("status",400);
            map.put("msg","用户名不能为空");
            return map;
        }

        if(StringUtils.isBlank(pwd)){
            //map.put("status",400);
            map.put("msg","密码不能为空");
            return map;
        }

        //根据id查找该用户是否存在，不存在则直接返回
        Member member = memberMapper.findMemberById(mem_id);
        if(member==null){
            //map.put("status",400);
            map.put("msg","账号不存在");
            return map;
        }

        if(member.getMtype()!=mtype){
            map.put("msg","身份不一致");
            return map;
        }

        //对输入的密码加密后与数据库密码比较是否一致，不一致则密码错误返回信息
        pwd = CommonUtils.md5(pwd);
        if(!pwd.equals(member.getPwd())){
            //map.put("status",400);
            map.put("msg","密码错误");
            return map;
        }

        //密码正确则生成登录凭证存储进数据库，并且将ticket返回
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setMem_id(mem_id);
        loginTicket.setStatus(1);
        loginTicket.setExpired(new Date(System.currentTimeMillis()+expiredSeconds*1000));
        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        loginTicketMapper.insertTicket(loginTicket);

        //map.put("status",200);
        map.put("msg","登录成功");
        map.put("ticket",loginTicket.getTicket());
        return map;
    }

    //注册的业务逻辑
    @Override
    public Map<String, Object> doRegister(Member member) {
        Map<String,Object> map = new HashMap<>();

        if(StringUtils.isBlank(member.getId())){
            map.put("status",400);
            map.put("msg","账号不能为空");
            return map;
        }

        if(StringUtils.isBlank(member.getPwd())){
            map.put("status",400);
            map.put("msg","密码不能为空");
            return map;
        }

        if(StringUtils.isBlank(member.getMname())){
            map.put("status",400);
            map.put("msg","姓名不能为空");
            return map;
        }

        //根据id查询是否存在用户，存在则已注册，返回信息
        Member member1 = memberMapper.findMemberById(member.getId());
        if(member1!=null){
            map.put("status",400);
            map.put("msg","该学号或工号已注册");
            return map;
        }

        //对密码进行md5加密
        member.setPwd(CommonUtils.md5(member.getPwd()));
        //插入操作如果成功返回1，失败则返回0
        int flag = memberMapper.insertMember(member);
        if(1==flag){
            map.put("status",200);
            map.put("msg","注册成功");
        }else{
            map.put("status",400);
            map.put("msg","添加用户信息失败");
        }
        return map;
    }

    //退出的业务逻辑
    @Override
    public String doLogout(String ticket) {
        //删除用户所持ticket在数据库中的存储
        //下次用户再拿这个ticket来时就无效了
        //用户下次就需要重新登录发放新凭证
        int flag = loginTicketMapper.deleteTicket(ticket);
        if(1==flag) return "退出成功";
        else return "退出失败";
    }

    //更新密码的业务逻辑
    @Override
    public String updatePwd(String oldPwd, String newPwd) {
        //判断用户输入的旧密码经md5加密后与用cookie中凭证查询到的用户在数据库中的密码是否一致
        // 若不一致则旧密码输入错误，不予修改
        String msg = null;
        oldPwd = CommonUtils.md5(oldPwd);
        newPwd = CommonUtils.md5(newPwd);

        if(!(oldPwd.equals(hostHolder.getMember().getPwd()))){
            msg = "原密码错误";
            return msg;
        }

        //判断新旧密码是否相同
        if(oldPwd.equals(newPwd)){
            msg = "新旧密码不能相同";
            return msg;
        }
        //修改密码
        int flag = memberMapper.updatePwd(hostHolder.getMember().getId(),newPwd);
        if(1==flag){
            msg = "密码修改成功";
        }else{
            msg = "密码修改失败";
        }
        return msg;
    }

    //更新地址的业务逻辑
    @Override
    public String updateAddress(String pwd,String address) {
        String msg;
        //如果密码错误不予修改
        if(!(CommonUtils.md5(pwd).equals(hostHolder.getMember().getPwd()))){
            msg = "密码错误";
            return msg;
        }

        if(address.equals(hostHolder.getMember().getAddress())){
            msg = "新旧地址不能相同";
            return msg;
        }

        int flag = memberMapper.updateAddress(hostHolder.getMember().getId(),address);

        if(1==flag){
            msg = "地址修改成功";
        }else{
            msg = "地址修改失败";
        }
        return msg;
    }

    //更新电话的业务逻辑
    @Override
    public String updatePhone(String pwd,String phone) {
        String msg;
        //如果密码错误不予修改
        if(!(CommonUtils.md5(pwd).equals(hostHolder.getMember().getPwd()))){
            msg = "密码错误";
            return msg;
        }

        if(phone.equals(hostHolder.getMember().getPhone())){
            msg = "新旧电话不能相同";
            return msg;
        }

        int flag = memberMapper.updatePhone(hostHolder.getMember().getId(),phone);

        if(1==flag){
            msg = "电话修改成功";
        }else{
            msg = "电话修改失败";
        }
        return msg;
    }

    @Override
    public List<Borrow> checkBorrowInformation() {
        return borrowMapper.checkByReaderId(hostHolder.getMember().getId());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String borrowBook(int book_id) {
        // card_state异常，借书失败
        if(hostHolder.getMember().getCard_state() == 0) {
            return "card_state异常";
        }
        // 超出最大借阅数
        if(hostHolder.getMember().getBorrownum() >= 5) {
            return "超出最大借阅数";
        }
        // 书已被全部借出
        if(bookMapper.getCopiesNumById(book_id) <= 0) {
            return "书已被全部借出";
        }
        borrowMapper.addBorrow(hostHolder.getMember().getId(), book_id);
        bookMapper.reduceCopiesNum(book_id);
        memberMapper.addBorrowNum(hostHolder.getMember().getId());
        return "借书成功";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String renewBook(int borrow_id) {
        // card_state异常
        if(hostHolder.getMember().getCard_state() == 0) {
            return "card_state异常";
        }
        // 检查待续借图书是否已经超期。如已超期，则无法继续执行续借操作。
        if(borrowMapper.calDiffCur(borrow_id) < 0){
            return "已超期";
        }
        // 检查`back_date - lend_date`是否大于等于40（已经执行了3次续借操作）。若大于等于40，则无法继续续借。
        if(borrowMapper.calDiffLend(borrow_id) >= 40){
            return "超过最大续借次数";
        }
        borrowMapper.renewBorrow(borrow_id);
        return "续借成功";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String returnBook(int borrow_id, double payment) {
        // card_state异常
        if(hostHolder.getMember().getCard_state() == 0) {
            return "card_state异常";
        }
        // 余额是否充足
        // 逾期1天1块钱
        // calDiffCur = cur_date - back_date
        if(-borrowMapper.calDiffCur(borrow_id) > payment){
            return "余额不足";
        }
        int book_id = borrowMapper.getBookId(borrow_id);
        bookMapper.addCopiesNum(book_id);
        memberMapper.reduceBorrowNum(hostHolder.getMember().getId());
        borrowMapper.deleteBorrow(borrow_id);
        return "还书成功";
    }
}
