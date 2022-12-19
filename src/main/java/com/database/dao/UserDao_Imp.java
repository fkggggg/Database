package com.database.dao;

import com.database.bean.User;
import com.database.jdbc.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao_Imp implements UserDao{
    private static final String LOGIN = "SELECT permission from user WHERE user_name=? AND password=?";

    @Override
    public int login(User user) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(LOGIN);

        return 0;
    }
}
