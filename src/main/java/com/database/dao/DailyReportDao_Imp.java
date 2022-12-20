package com.database.dao;

import com.database.bean.DailyReport;
import com.database.jdbc.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DailyReportDao_Imp implements DailyReportDao{
    private static final String ADD_DAILY_HEALTH_REPORT = "INSERT INTO `daily_health_report` VALUES (null,?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_DAILY_HEALTH_REPORT = "SELECT * from daily_health_report WHERE student_id=? AND date=?";

    Connection connection = JDBCUtils.getConnection();

    public DailyReportDao_Imp() throws SQLException {
    }

    @Override
    public boolean addDailyReport(DailyReport dailyReport) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_DAILY_HEALTH_REPORT);
        preparedStatement.setString(1,String.valueOf(dailyReport.getStudent_id()));
        preparedStatement.setString(2,dailyReport.getDate().toString());
        preparedStatement.setString(3,dailyReport.getTime().toString());
        preparedStatement.setString(4,String.valueOf(dailyReport.getHealth_condition()));
        preparedStatement.setString(5,dailyReport.getAbnormal_description());
        preparedStatement.setString(6,dailyReport.getTemperature());
        preparedStatement.setString(7,dailyReport.getLocation());
        int result = preparedStatement.executeUpdate();
        return result > 0;
    }

    @Override
    public DailyReport getDailyReport(String student_id, LocalDate date) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DAILY_HEALTH_REPORT);
        preparedStatement.setString(1,student_id);
        preparedStatement.setString(2,date.toString());
        ResultSet result = preparedStatement.executeQuery();
        if(result.next())
        {
            int daily_report_id = result.getInt("daily_report_id");
            DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime time = LocalTime.parse(result.getString("time"),df);
            int health_condition = result.getInt("health_condition");
            String abnormal_description = result.getString("abnormal_description");
            String temperature = result.getString("temperature");
            String location = result.getString("location");
            return new DailyReport(daily_report_id, student_id, date, time, health_condition, abnormal_description, temperature, location);
        }
        return new DailyReport(-1);
    }
}
