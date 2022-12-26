package com.database.testtime;

import com.database.jdbc.JDBCUtils;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

//定义一个系统时间，方便测试用
public class Testtime {

    String CHANGE_DATE = "UPDATE `testdate` SET `today`=?";

    LocalDate date = LocalDate.of(2022, 12, 23);
    //时间可用真实时间，避免打卡时间重复
    LocalTime time = LocalTime.of(16, 30, 30);

    public Testtime() throws ParseException {
    }

    public void changedate() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_DATE);
        preparedStatement.setString(1,date.toString());
        preparedStatement.executeUpdate();
    }

    public void printtestdate(){
        System.out.println(date);
    }
    public void printtesttime(){
        System.out.println(time);
    }

    public LocalDate gettestdate(){
        return date;
    }
    public LocalTime gettesttime(){
        return time;
    }

    public void setTestDate(LocalDate date1){
        this.date = date1;
    }
    public void setTestTime(LocalTime time1){
        this.time = time1;
    }

}
