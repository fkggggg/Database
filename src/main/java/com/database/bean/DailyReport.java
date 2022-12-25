package com.database.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class DailyReport {
    private int daily_report_id;
    private String student_id;
    private LocalDate date;
    private LocalTime time;
    private int health_condition;
    private String abnormal_description;
    private String temperature;
    private String location;

    public DailyReport(){

    }

    public DailyReport(int daily_report_id){
        this.daily_report_id = daily_report_id;
    }

    public DailyReport(String student_id,LocalDate date,LocalTime time,int health_condition,
                       String abnormal_description,String temperature,String location){
        this.student_id=student_id;
        this.date=date;
        this.time=time;
        this.health_condition=health_condition;
        this.abnormal_description=abnormal_description;
        this.temperature=temperature;
        this.location=location;
    }

    public DailyReport(int daily_report_id,String student_id,LocalDate date,LocalTime time,int health_condition,
                       String abnormal_description,String temperature,String location){
        this.daily_report_id = daily_report_id;
        this.student_id=student_id;
        this.date=date;
        this.time=time;
        this.health_condition=health_condition;
        this.abnormal_description=abnormal_description;
        this.temperature=temperature;
        this.location=location;
    }

    public int getDaily_report_id() {
        return daily_report_id;
    }

    public void setDaily_report_id(int daily_report_id) {
        this.daily_report_id = daily_report_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getHealth_condition() {
        return health_condition;
    }

    public void setHealth_condition(int health_condition) {
        this.health_condition = health_condition;
    }

    public String getAbnormal_description() {
        return abnormal_description;
    }

    public void setAbnormal_description(String abnormal_description) {
        this.abnormal_description = abnormal_description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "学号：" + student_id +
                "\t日期：" + date +
                "\t时间：" + time +
                "\t是否感觉不适：" + (health_condition==1?"否":("是\t异常状况："+abnormal_description)) +
                "\t体温：" + temperature +
                "\t地点：" + location;
    }
}
