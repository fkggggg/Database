package com.database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
    public static void main(String[] args)
    {
       try
       {
           Class.forName("com.mysql.cj.jdbc.Driver");
           System.out.println("驱动加载成功");
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?serverTimezone=UTC&useSSL=false","root","12345678");
           System.out.println("数据库连接成功");
       } catch (Exception e)
       {
           e.printStackTrace();
           System.out.println("数据库连接失败");
       }
    }
}
