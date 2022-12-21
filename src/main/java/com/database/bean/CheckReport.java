package com.database.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class CheckReport {
    private int check_report_id;
    private String student_id;
    private LocalDate date;
    private LocalTime time;
    private int state;
    private char campus;

    public CheckReport(){

    }

    public CheckReport(int check_report_id,String student_id,LocalDate date,LocalTime time,int state,char campus){
        this.check_report_id=check_report_id;
        this.student_id=student_id;
        this.date=date;
        this.time=time;
        this.state=state;
        this.campus=campus;
    }

    public int getCheck_report_id() {
        return check_report_id;
    }

    public void setCheck_report_id(int check_report_id) {
        this.check_report_id = check_report_id;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public char getCampus() {
        return campus;
    }

    public void setCampus(char campus) {
        this.campus = campus;
    }
}
