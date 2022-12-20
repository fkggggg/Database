package com.database.testtime;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;

//定义一个系统时间，方便测试用
public class Testtime {
    LocalDate date = LocalDate.of(2022, 12, 20);
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

}
