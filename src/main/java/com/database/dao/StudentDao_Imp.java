package com.database.dao;

import com.database.bean.DailyReport;
import com.database.bean.Student;
import com.database.bean.User;
import com.database.testtime.Testtime;
import com.database.jdbc.JDBCUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDao_Imp implements StudentDao{
    private static final String SELECT_STUDENT = "SELECT * from student WHERE user_id=?";
    private static final String UPDATE_STUDENT = "UPDATE `student` SET `phone`=?,`email`=?,`dormitory`=?,`address`=?,`id_type`=?,`id_number`=? WHERE user_id=?";

    Connection connection = JDBCUtils.getConnection();

    public StudentDao_Imp() throws SQLException {
    }

    @Override
    public Student getStudent(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT);
        preparedStatement.setString(1,String.valueOf(user.getUser_id()));
        ResultSet result = preparedStatement.executeQuery();
        //ResultSet没有size参数，通过next()方法判断是否为空
        if (result.next()) {
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
            int limits_H = result.getInt("limits_H");
            int limits_J = result.getInt("limits_J");
            int limits_F = result.getInt("limits_F");
            int limits_Z = result.getInt("limits_Z");
            String limits = "";
            if (limits_H == 0)
                limits += " H";
            if (limits_J == 0)
                limits += " J";
            if (limits_F == 0)
                limits += " F";
            if (limits_Z == 0)
                limits += " Z";
            if (limits.equals(""))
                limits += "无进校权限";

            return new Student(user, student_id, name, college_name, class_name, phone, email, dormitory, address, id_type, id_number, limits_H, limits_J, limits_F, limits_Z, limits);
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

    @Override
    public Student getStudentByStuid(String stu_id) throws SQLException {
        String sql = "SELECT user_id from student WHERE student_id=?";
        PreparedStatement preparedStatement0 = connection.prepareStatement(sql);
        preparedStatement0.setString(1, stu_id);
        ResultSet result0 = preparedStatement0.executeQuery();
        //ResultSet没有size参数，通过next()方法判断是否为空
        if (result0.next()) {
            int user_id = result0.getInt("user_id");
            User user = new User(user_id,"", "");

            return getStudent(user);
        }
        else
            return null;
    }

    @Override
    public List<Student> getAllStudent(int perm, String range) throws SQLException {
        String sql;
        switch (perm){
            case 0:
                sql = "SELECT * from student";
                break;
            case 1:
                sql = "SELECT * from student where college_name =" + range;
                break;
            case 2:
                sql = "SELECT * from student where class_name =" + range;
                break;
            default: return null;
        }
        PreparedStatement preparedStatement0 = connection.prepareStatement(sql);
        ResultSet result = preparedStatement0.executeQuery();
        List<Student> list = new ArrayList<>();
        while (result.next()){
            int userid = result.getInt("user_id");
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
            int limits_H = result.getInt("limits_H");
            int limits_J = result.getInt("limits_J");
            int limits_F = result.getInt("limits_F");
            int limits_Z = result.getInt("limits_Z");
            String limits = "";
            if (limits_H == 0)
                limits += " H";
            if (limits_J == 0)
                limits += " J";
            if (limits_F == 0)
                limits += " F";
            if (limits_Z == 0)
                limits += " Z";
            if (limits.equals(""))
                limits += "无进校权限";

            list.add(new Student(new User(userid, "",""), student_id, name, college_name,
                    class_name, phone, email, dormitory, address, id_type, id_number, limits_H, limits_J, limits_F, limits_Z, limits) );

        }
        return list;
    }
}
