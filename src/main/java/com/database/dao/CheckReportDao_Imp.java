package com.database.dao;

import com.database.bean.CheckReport;
import com.database.bean.Student;
import com.database.jdbc.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CheckReportDao_Imp implements CheckReportDao{
    private static final String ADD_DAILY_HEALTH_REPORT = "INSERT INTO `check_report` VALUES (null,?, ?, ?, ?, ?)";
    private static final String SELECT_CHECK_REPORT = "SELECT * from check_report WHERE student_id=? ORDER BY date DESC,time DESC";

    Connection connection = JDBCUtils.getConnection();

    public CheckReportDao_Imp() throws SQLException {
    }

    @Override
    public int addCheckReport(CheckReport checkReport, Student student) throws SQLException {
        //入校请求需检查权限
        if(checkReport.getState()==1){
            switch (checkReport.getCampus()){
                case 'H':
                    if(student.getLimits_H()==0)
                        break;
                    else return 0;
                case 'J':
                    if(student.getLimits_J()==0)
                        break;
                    else return 0;
                case 'F':
                    if(student.getLimits_F()==0)
                        break;
                    else return 0;
                case 'Z':
                    if(student.getLimits_Z()==0)
                        break;
                    else return 0;
            }
        }
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_DAILY_HEALTH_REPORT);
        preparedStatement.setString(1,String.valueOf(checkReport.getStudent_id()));
        preparedStatement.setString(2,checkReport.getDate().toString());
        preparedStatement.setString(3,checkReport.getTime().toString());
        preparedStatement.setString(4,String.valueOf(checkReport.getState()));
        preparedStatement.setString(5,String.valueOf(checkReport.getCampus()));
        int result = preparedStatement.executeUpdate();
        return result>0?1:-1;
    }

    @Override
    public CheckReport getlatestCheckReport(String student_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CHECK_REPORT);
        preparedStatement.setString(1, student_id);
        ResultSet result = preparedStatement.executeQuery();
        if(result.next()) {
            int check_report_id = result.getInt("check_report_id");
            DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(result.getString("date"),df1);
            DateTimeFormatter df2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime time = LocalTime.parse(result.getString("time"),df2);
            int state = result.getInt("state");
            char campus = result.getString("campus").charAt(0);
            return new CheckReport(check_report_id,student_id, date, time, state, campus);
        }
        return new CheckReport();
    }

    @Override
    public List<CheckReport> getAllCheckReport(String student_id) throws SQLException {
        String sql = "SELECT * from check_report WHERE student_id=? ORDER BY date DESC,time DESC";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, student_id);
        ResultSet result = preparedStatement.executeQuery();
        List<CheckReport> list = new ArrayList<>();
        while(result.next()) {
            int check_report_id = result.getInt("check_report_id");
            DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(result.getString("date"),df1);
            DateTimeFormatter df2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime time = LocalTime.parse(result.getString("time"),df2);
            int state = result.getInt("state");
            char campus = result.getString("campus").charAt(0);
            list.add(new CheckReport(check_report_id,student_id, date, time, state, campus)) ;
        }
        return list;
    }


}
