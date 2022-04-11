package com.library.service;

import com.library.bean.Borrow;
import com.library.bean.Member;
import com.library.bean.Reservation;

import java.util.List;
import java.util.Map;

public interface MemberService {
    public Map<String,Object> doLogin(String mem_id, String pwd, int expiredSeconds,int mtype);

    public Map<String,Object> doRegister(Member member);

    public String doLogout(String ticket);

    public String updatePwd(String oldPwd,String newPwd);

    public String updateAddress(String pwd,String address);

    public String updatePhone(String pwd,String phone);

    public List<Borrow> checkBorrowInformation();

    public String borrowBook(int book_id);

    public String renewBook(int borrow_id);

    public String returnBook(int borrow_id);

    public Map<String, Object> isFined(int borrow_id);

    public List<Reservation> notifyReservation();

    public String reserveBook(int book_id);

    public int deleteReservation(List<Integer> reservations);


}
