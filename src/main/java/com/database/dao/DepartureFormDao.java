package com.database.dao;

import com.database.bean.DepartureForm;

import java.sql.SQLException;
import java.util.List;

public interface DepartureFormDao {
    boolean addDepartureForm(DepartureForm departureReport) throws SQLException;
    boolean deleteDepartureForm(int deform_id) throws SQLException;
    //这个方法仅用于获取单个学生最新的离校申请
    DepartureForm getmyDepartureForm(String student_id) throws SQLException;
    List<DepartureForm> getAllDepartureForm(String student_id) throws SQLException;
}
