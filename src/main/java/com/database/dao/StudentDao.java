package com.database.dao;

import com.database.bean.Student;
import com.database.bean.User;

import java.sql.SQLException;

public interface StudentDao {
    Student getStudent(User user) throws SQLException;
    boolean updateStudent(Student student) throws SQLException;
    Student getStudentByStuid(String stu_id) throws SQLException;
}
