package com.database.dao;

import com.database.bean.User;
import com.database.jdbc.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao_Imp implements UserDao{
    private static final String CHECK_USERNAME = "SELECT permission from user WHERE user_name=?";
    private static final String CHECK_PASSWORD = "SELECT permission from user WHERE user_name=? AND password=?";

    @Override
    public int login(User user) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME);
        preparedStatement.setString(1,user.getUser_name());
        ResultSet result = preparedStatement.executeQuery();
        //ResultSet没有size参数，通过next()方法判断是否为空
        if (result.next())
        {
            PreparedStatement preparedStatement2 = connection.prepareStatement(CHECK_PASSWORD);
            preparedStatement2.setString(1,user.getUser_name());
            preparedStatement2.setString(2,user.getPassword());
            ResultSet result2 = preparedStatement2.executeQuery();
            if(result2.next())
            {
                return result2.getInt("permission");
            }
            return -1;
        }
        return -2;
    }
}
