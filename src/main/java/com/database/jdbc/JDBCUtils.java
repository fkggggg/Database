package com.database.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
        Properties p = new Properties();
        try {
            p.load(is);
            driver = p.getProperty("driver");
            url = p.getProperty("url");
            username = p.getProperty("username");
            password = p.getProperty("password");
            Class.forName(driver);
            System.out.println("驱动加载成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("数据库连接成功");
        return DriverManager.getConnection(url,username,password);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if(connection != null)
        {
            connection.close();
            connection = null;
        }
        if(statement != null)
        {
            statement.close();
            statement = null;
        }
        if(resultSet != null)
        {
            resultSet.close();
            resultSet = null;
        }
    }
}
