package com.database.dao;

import com.database.bean.CheckReport;
import com.database.bean.Student;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CheckReportDao {
    int addCheckReport(CheckReport checkReport, Student student) throws SQLException;
    CheckReport getlatestCheckReport(String student_id) throws SQLException;

    List<CheckReport> getAllCheckReport(String student_id)throws SQLException;
}
