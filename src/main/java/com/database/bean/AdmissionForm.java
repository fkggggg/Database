package com.database.bean;

import java.time.LocalDate;

public class AdmissionForm {
    private int adform_id;
    private LocalDate application_date;
    private String student_id;
    private String name;
    private String college_name;
    private String class_name;
    private String reason;
    private LocalDate estimated_date;
    private int state;
    private String reject_reason;

    public AdmissionForm(){

    }

    public AdmissionForm(int adform_id){
        this.adform_id=adform_id;
    }

    public AdmissionForm(int adform_id, LocalDate application_date, String student_id, String name,
                         String college_name, String class_name, String reason, LocalDate estimated_date){
        this.adform_id=adform_id;
        this.application_date=application_date;
        this.student_id=student_id;
        this.name=name;
        this.college_name=college_name;
        this.class_name=class_name;
        this.reason=reason;
        this.estimated_date=estimated_date;
    }

    public AdmissionForm(int adform_id, LocalDate application_date, String student_id, String name,
                         String college_name, String class_name, String reason, LocalDate estimated_date,
                         int state, String reject_reason){
        this.adform_id=adform_id;
        this.application_date=application_date;
        this.student_id=student_id;
        this.name=name;
        this.college_name=college_name;
        this.class_name=class_name;
        this.reason=reason;
        this.estimated_date=estimated_date;
        this.state=state;
        this.reject_reason=reject_reason;
    }

    public int getAdform_id() {
        return adform_id;
    }

    public void setAdform_id(int adform_id) {
        this.adform_id = adform_id;
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

    public LocalDate getEstimated_date() {
        return estimated_date;
    }

    public void setEstimated_date(LocalDate estimated_date) {
        this.estimated_date = estimated_date;
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
        return "入校申请表单号：" + adform_id +
                "\t申请日期：" + application_date +
                "\t学号：" + student_id +
                "\t姓名：" + name +
                "\t院系：" + college_name +
                "\t班级：" + class_name +
                "\t入校理由：" + reason +
                "\t预计返校日期：" + estimated_date;
    }
}
