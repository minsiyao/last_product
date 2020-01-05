package com.minsiyao.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class User {

    private String id;
    private String headImg;
    private String dharma;
    private String name;
    private String sex;
    private String address;
    private String sign;
    private java.sql.Date create_date;
    private String status;
    private String phoneNum;
    private String password;
    private String salt;


}
