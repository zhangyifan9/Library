package com.library.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Zilong Lin,Ning Zhang
 * @Date: 2022/4/5 23:12
 * @Version: v1.0
 * @Description: Member
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private String id;
    private Integer mtype;
    private String mname;
    private String pwd;
    private Integer sex;
    private String address;
    private String phone;
    private Integer borrownum;
    private Integer card_state;
    // 增加账户金额信息，用于支付罚款
    private double account;
    // 增加该字段，用于记录用户以预约的数量
    private Integer resvnum;

}
