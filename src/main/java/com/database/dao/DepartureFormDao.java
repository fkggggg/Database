package com.database.dao;

import com.database.bean.DepartureForm;
import com.database.bean.Student;

import java.sql.SQLException;

public interface DepartureFormDao {
    boolean addDepartureForm(DepartureForm departureReport) throws SQLException;
    //这个方法仅用于获取单个学生最新的离校申请
    DepartureForm getmyDepartureForm(Student student) throws SQLException;
    //这个方法仅用于学生更新自己的离校申请，用于被拒绝后修改或审核状态下重新提交新的申请，state重新置为0
    boolean updatemyDepartureForm(DepartureForm departureReport) throws SQLException;
}
