package com.database.dao;

import com.database.bean.Student;
import com.database.bean.User;
import com.database.jdbc.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao_Imp implements StudentDao{
    private static final String SELECT_STUDENT = "SELECT * from student WHERE user_id=?";

    @Override
    public Student getStudent(int user_id) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT);
        String USERID = String.valueOf(user_id);
        preparedStatement.setString(1,USERID);
        ResultSet result = preparedStatement.executeQuery();
        //ResultSet没有size参数，通过next()方法判断是否为空
        if (result.next())
        {
            String student_id = result.getString("student_id");
            String name = result.getString("name");
            String college_name = result.getString("college_name");
            String class_name = result.getString("class_name");
            String phone = result.getString("phone");
            String email = result.getString("email");
            String dormitory = result.getString("dormitory");
            String address = result.getString("address");
            String id_type = result.getString("id_type");
            String id_number = result.getString("id_number");
            String limits = "";
            if(result.getInt("limits_H") == 0)
                limits += " H";
            if(result.getInt("limits_G") == 0)
                limits += " G";
            if(result.getInt("limits_F") == 0)
                limits += " F";
            if(result.getInt("limits_Z") == 0)
                limits += " Z";
            if(limits.equals(""))
                limits += "无进校权限";
            return new Student(student_id,name,college_name,class_name,phone,email,dormitory,address,id_type,id_number,limits);
        }
        return new Student();
    }
}
