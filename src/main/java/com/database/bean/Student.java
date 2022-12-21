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
    private int limits_H;
    private int limits_J;
    private int limits_F;
    private int limits_Z;
    private String limits;

    private int isreport;
    private int ischeck;

    public Student(User user, String student_id, String name, String college_name, String class_name,
                   String phone, String email, String dormitory, String address,
                   String id_type, String id_number,
                   int limits_H, int limits_J, int limits_F, int limits_Z, String limits) {
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
        this.limits_H=limits_H;
        this.limits_J=limits_J;
        this.limits_F=limits_F;
        this.limits_Z=limits_Z;
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

    public int getIsreport() {
        return isreport;
    }

    public void setIsreport(int isreport) {
        this.isreport = isreport;
    }

    public int getIscheck() {
        return ischeck;
    }

    public void setIscheck(int ischeck) {
        this.ischeck = ischeck;
    }

    public int getLimits_H() {
        return limits_H;
    }

    public void setLimits_H(int limits_H) {
        this.limits_H = limits_H;
    }

    public int getLimits_J() {
        return limits_J;
    }

    public void setLimits_J(int limits_J) {
        this.limits_J = limits_J;
    }

    public int getLimits_F() {
        return limits_F;
    }

    public void setLimits_F(int limits_F) {
        this.limits_F = limits_F;
    }

    public int getLimits_Z() {
        return limits_Z;
    }

    public void setLimits_Z(int limits_Z) {
        this.limits_Z = limits_Z;
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
