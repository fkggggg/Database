package com.database.dao;

import com.database.bean.User;
import com.database.jdbc.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao_Imp implements UserDao{
    private static final String CHECK_USERNAME = "SELECT * from user WHERE user_name=?";
    private static final String CHECK_PASSWORD = "SELECT * from user WHERE user_name=? AND password=?";


    @Override
    public User login(User user) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME);
        preparedStatement.setString(1,user.getUser_name());
        ResultSet result = preparedStatement.executeQuery();
        User return_user = new User();
        //ResultSet没有size参数，通过next()方法判断是否为空
        if (result.next())
        {
            PreparedStatement preparedStatement2 = connection.prepareStatement(CHECK_PASSWORD);
            preparedStatement2.setString(1,user.getUser_name());
            preparedStatement2.setString(2,user.getPassword());
            ResultSet result2 = preparedStatement2.executeQuery();
            if(result2.next())
            {
                return_user.setUser_id(result2.getInt("user_id"));
                return_user.setUser_name(user.getUser_name());
                return_user.setPassword(user.getPassword());
                return_user.setPermission(result2.getInt("permission"));
                return return_user;
            }
            return_user.setPermission(-1);
            return return_user;
        }
        return_user.setPermission(-2);
        return return_user;
    }
}
