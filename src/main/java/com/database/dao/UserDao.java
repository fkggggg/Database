package com.database.dao;

import com.database.bean.User;

import java.sql.SQLException;

//登录：返回值：-1：登录失败，
public interface UserDao {
    int login(User user) throws SQLException;
}
