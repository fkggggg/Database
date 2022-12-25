package com.database.testtime;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

//定义一个系统时间，方便测试用
public class Testtime {
    int year;

    LocalDate date = LocalDate.of(2022, 12, 20);
    //时间可用真实时间，避免打卡时间重复
    LocalTime time = LocalTime.of(16, 30, 30);

    public Testtime() throws ParseException {
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
