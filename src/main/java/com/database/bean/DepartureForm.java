package com.database.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class DepartureForm {
    private int deform_id;
    private LocalDate application_date;
    private String student_id;
    private String name;
    private String college_name;
    private String class_name;
    private String reason;
    private String destination;
    private LocalDate estimated_date;
    private LocalDate return_date;
    private int state;
    private String reject_reason;

    public DepartureForm(){

    }

    public DepartureForm(int deform_id){
        this.deform_id=deform_id;
    }

    public DepartureForm(int deform_id, LocalDate application_date, String student_id, String name,
                         String college_name, String class_name, String reason, String destination,
                         LocalDate estimated_date, LocalDate return_date){
        this.deform_id=deform_id;
        this.application_date=application_date;
        this.student_id=student_id;
        this.name=name;
        this.college_name=college_name;
        this.class_name=class_name;
        this.reason=reason;
        this.destination=destination;
        this.estimated_date=estimated_date;
        this.return_date=return_date;
    }

    public DepartureForm(int deform_id, LocalDate application_date, String student_id, String name,
                         String college_name, String class_name, String reason, String destination,
                         LocalDate estimated_date, LocalDate return_date,
                         int state, String reject_reason){
        this.deform_id=deform_id;
        this.application_date=application_date;
        this.student_id=student_id;
        this.name=name;
        this.college_name=college_name;
        this.class_name=class_name;
        this.reason=reason;
        this.destination=destination;
        this.estimated_date=estimated_date;
        this.return_date=return_date;
        this.state=state;
        this.reject_reason=reject_reason;
    }

    public int getDeform_id() {
        return deform_id;
    }

    public void setDeform_id(int deform_id) {
        this.deform_id = deform_id;
    }

    public LocalDate getApplication_date() {
        return application_date;
    }

    public void setApplication_date(LocalDate application_date) {
        this.application_date = application_date;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege_name() {
        return college_name;
    }

    public void setCollege_name(String college_name) {
        this.college_name = college_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getEstimated_date() {
        return estimated_date;
    }

    public void setEstimated_date(LocalDate estimated_date) {
        this.estimated_date = estimated_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getReject_reason() {
        return reject_reason;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    @Override
    public String toString() {
        String state_str="";
        String reject_reason_str="";
        switch (state) {
            case 0 -> state_str = "????????????????????????";
            case 1 -> state_str = "????????????????????????";
            case 2 -> state_str = "?????????";
            case -1 -> state_str = "????????????????????????";
            case -2 -> state_str = "????????????????????????";
            case -3 -> state_str = "?????????";
        }
        if(state==-1||state==-2)
            reject_reason_str="\t???????????????"+reject_reason;
        return "????????????????????????" + deform_id +
                "\t???????????????" + application_date +
                "\t?????????" + student_id +
                "\t?????????" + name +
                "\t?????????" + college_name +
                "\t?????????" + class_name +
                "\t???????????????" + reason +
                "\t????????????" + destination +
                "\t?????????????????????" + estimated_date +
                "\t?????????????????????" + return_date +
                "\t???????????????" + state_str +
                reject_reason_str
                ;
    }
}
