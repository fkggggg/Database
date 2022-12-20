package com.database.dao;

import com.database.bean.User;

import java.sql.SQLException;

//登录：返回值：-2：用户名不存在，-1：用户名和密码不匹配，0,1,2,3：该用户对应角色
public interface UserDao {
    User login(User user) throws SQLException;
}
