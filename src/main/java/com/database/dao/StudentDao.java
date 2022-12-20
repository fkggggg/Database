package com.database.dao;

import com.database.bean.Student;
import com.database.bean.User;

import java.sql.SQLException;

public interface StudentDao {
    Student getStudent(int user_id) throws SQLException;
}
