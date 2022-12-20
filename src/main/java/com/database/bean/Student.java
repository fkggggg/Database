package com.database.bean;

public class Student extends User implements Cloneable{
    private String student_id;
    private String name;
    private String college_name;
    private String class_name;
    private String phone;
    private String email;
    private String dormitory;
    private String address;
    private String id_type;
    private String id_number;
    private String limits;

    private int report;
    private int check;

    public Student(User user, String student_id, String name, String college_name, String class_name,
                   String phone, String email, String dormitory, String address,
                   String id_type, String id_number, int report, int check, String limits) {
        this.setUser_id(user.getUser_id());
        this.setUser_name(user.getUser_name());
        this.setPassword(user.getPassword());
        this.setPermission(user.getPermission());
        this.student_id=student_id;
        this.name=name;
        this.college_name=college_name;
        this.class_name=class_name;
        this.phone=phone;
        this.email=email;
        this.dormitory=dormitory;
        this.address=address;
        this.id_type=id_type;
        this.id_number=id_number;
        this.report=report;
        this.check=check;
        this.limits=limits;
    }

    public Student(){

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        this.limits = limits;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id='" + student_id + '\'' +
                ", name='" + name + '\'' +
                ", college_name='" + college_name + '\'' +
                ", class_name='" + class_name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", dormitory='" + dormitory + '\'' +
                ", address='" + address + '\'' +
                ", id_type='" + id_type + '\'' +
                ", id_number='" + id_number + '\'' +
                ", limits='" + limits + '\'' +
                '}';
    }
}
