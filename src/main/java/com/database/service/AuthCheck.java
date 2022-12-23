package com.database.service;

import com.database.bean.User;

public class AuthCheck {
    public enum Auth{
        AUTH_DETAIL,
        AUTH_STAT,
        NO_AUTH
    }

    public enum Range{
        SCHOOL,
        COLLEGE,
        CLASS,
        STUDENT
    }

    // 检查user对于rangeToCheck对应范围内的权限
    public static Auth check(User user, User rangeToCheck){
        return Auth.NO_AUTH;
    }

    public static Auth checkStu(User user, String stu_id){
        return Auth.NO_AUTH;
    }
}
