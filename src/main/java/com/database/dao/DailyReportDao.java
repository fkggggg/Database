package com.database.dao;

import com.database.bean.DailyReport;

import java.sql.SQLException;
import java.time.LocalDate;

public interface DailyReportDao {
    boolean addDailyReport(DailyReport dailyReport) throws SQLException;
    DailyReport getDailyReport(String student_id, LocalDate date) throws SQLException;
}
