package com.database.dao;

import com.database.bean.CheckReport;
import com.database.bean.Student;

import java.sql.SQLException;
import java.time.LocalDate;

public interface CheckReportDao {
    int addCheckReport(CheckReport checkReport, Student student) throws SQLException;
    CheckReport getlatestCheckReport(String student_id) throws SQLException;
}
