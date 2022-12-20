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
    private static final String UPDATE_STUDENT = "UPDATE `student` SET `phone`=?,`email`=?,`dormitory`=?,`address`=?,`id_type`=?,`id_number`=? WHERE user_id=?";

    Connection connection = JDBCUtils.getConnection();

    public StudentDao_Imp() throws SQLException {
    }

    @Override
    public Student getStudent(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT);
        String USERID = String.valueOf(user.getUser_id());
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
            return new Student(user,student_id,name,college_name,class_name,phone,email,dormitory,address,id_type,id_number,limits);
        }
        return new Student();
    }

    @Override
    public boolean updateStudent(Student student) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT);
        String phone = student.getPhone();
        String email = student.getEmail();
        String dormitory = student.getDormitory();
        String address = student.getAddress();
        String id_type = student.getId_type();
        String id_number = student.getId_number();
        String user_id = String.valueOf(student.getUser_id());
        preparedStatement.setString(1,phone);
        preparedStatement.setString(2,email);
        preparedStatement.setString(3,dormitory);
        preparedStatement.setString(4,address);
        preparedStatement.setString(5,id_type);
        preparedStatement.setString(6,id_number);
        preparedStatement.setString(7,user_id);
        int result = preparedStatement.executeUpdate();
        return result > 0;
    }
}
