package com.library.controller;

import com.library.annotation.LoginRequired;
import com.library.bean.Borrow;
import com.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author: Ruilin Jiang
 * @Date: 2022/4/8 16:30
 * @Version: v1.0
 * @Description:
 */
@RestController
public class MemberController {

    @Autowired
    @Qualifier("memberServiceImpl")
    private MemberService memberService;

    /**
     * 修改密码接口
     * @param oldPwd 原密码
     * @param newPwd 新密码
     * @return 返回操作信息msg
     */
//    @LoginRequired
    @RequestMapping(path = "/member/updatePwd",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",allowCredentials = "true")
    public Map<String,Object> updatePwd(String oldPwd,String newPwd){
        String msg = memberService.updatePwd(oldPwd,newPwd);
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        return map;
    }

    /**
     * 修改住址接口,要求用户在前端修改住址时要再次输入密码
     * @param pwd 密码
     * @param address 新地址
     * @return 返回操作信息msg
     */
//    @LoginRequired
    @RequestMapping(path = "/member/updateAddress",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",allowCredentials = "true")
    public Map<String,Object> updateAddress(String pwd,String address){
        String msg = memberService.updateAddress(pwd,address);

        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        return map;
    }

    /**
     * 修改电话接口,要求用户在前端修改电话时要再次输入密码
     * @param pwd 密码
     * @param phone 新电话
     * @return 返回操作信息msg
     */
//    @LoginRequired
    @RequestMapping(path = "/member/updatePhone",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",allowCredentials = "true")
    public Map<String,Object> updatePhone(String pwd,String phone){
        String msg = memberService.updatePhone(pwd,phone);

        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        return map;
    }


    /**
     * @return: borrow information list
     * @Description: Members can view their borrowing information.
     */
//    @LoginRequired
    @RequestMapping(path = "member/viewBorrow", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true")
    public List<Borrow> checkBorrowInformation() {
        return memberService.checkBorrowInformation();
    }

    // 还书后，书籍划到预约者名下暂未实现
//    @LoginRequired
    @RequestMapping(path = "member/borrowBook", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true")
    public Map<String, Object> borrowBook(@RequestParam int book_id) {
        String msg = memberService.borrowBook(book_id);
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        return map;
    }

//    @LoginRequired
    @RequestMapping(path = "member/renewBook", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true")
    public Map<String, Object> renewBook(@RequestParam int borrow_id) {
        String msg = memberService.renewBook(borrow_id);
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        return map;
    }

    // 扣款功能暂未实现
//    @LoginRequired
    @RequestMapping(path = "member/returnBook", method = RequestMethod.POST)
    @CrossOrigin(origins = "*", allowCredentials = "true")
    public Map<String, Object> returnBook(@RequestParam int borrow_id, @RequestParam double payment) {
        String msg = memberService.returnBook(borrow_id, payment);
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        return map;
    }
}
