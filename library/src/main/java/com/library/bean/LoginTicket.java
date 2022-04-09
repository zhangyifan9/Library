package com.library.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
/**
 * @Author: Ruilin Jiang
 * @Date: 2022/4/5 23:12
 * @Version: v1.0
 * @Description: 登录凭证，整体存放在数据库中，其中的ticket字段也放在cookie中发给前端
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginTicket {
    private String id; //凭证的id，用于唯一标记一个凭证
    private String mem_id; //持有该凭证的用户id
    private String ticket; //核心，是使用UUID生成的随机字符串
    private int status; //该凭证的状态
    private Date expired; //凭证到期时间
}
