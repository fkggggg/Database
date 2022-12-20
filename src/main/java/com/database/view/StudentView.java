package com.database.view;

import com.database.bean.Student;

import java.util.Scanner;

public class StudentView {
    private static Scanner input = new Scanner(System.in);
    //个人信息
    public static int PersonalInformationView(Student student){
        System.out.println("***********************\t\t个人信息页面\t\t************************");
        System.out.println("***********************\t\t基本信息\t\t************************");
        System.out.println("\t\t学号："+student.getStudent_id());
        System.out.println("\t\t姓名："+student.getName());
        System.out.println("\t\t院系："+student.getCollege_name());
        System.out.println("\t\t班级："+student.getClass_name());
        System.out.println("\t\t联系电话："+student.getPhone());
        System.out.println("\t\t电子邮箱："+student.getEmail());
        System.out.println("\t\t宿舍："+student.getDormitory());
        System.out.println("\t\t住址："+student.getAddress());
        System.out.println("\t\t证件类型："+student.getId_type());
        System.out.println("\t\t证件号："+student.getId_number());
        System.out.println("***********************\t\t权限信息\t\t************************");
        System.out.println("\t\t今日是否健康打卡：");
        System.out.println("\t\t是否在校：");
        System.out.println("\t\t进校权限："+student.getLimits());
        System.out.println("***********************\t\t操作\t\t************************");
        System.out.println("***********************\t\t0：返回上一页面\t\t************************");
        System.out.println("***********************\t\t1：修改基本信息\t\t************************");
        String CHOOSE = input.nextLine();
        int choose = Integer.parseInt(CHOOSE);
        if(choose > -1 && choose < 2)
            return choose;
        else
        {
            System.out.println("请按提示输入指令！");
            return PersonalInformationView(student);
        }
    }

    public static Student EditPersonalInformationView(Student student){
        System.out.println("***********************\t\t请选择一项进行修改：\t\t************************");
        System.out.println("***********************\t\t0：放弃所有修改\t\t************************");
        System.out.println("***********************\t\t1：联系电话\t\t************************");
        System.out.println("***********************\t\t2：电子邮箱\t\t************************");
        System.out.println("***********************\t\t3：宿舍\t\t************************");
        System.out.println("***********************\t\t4：住址\t\t************************");
        System.out.println("***********************\t\t5：证件类型\t\t************************");
        System.out.println("***********************\t\t6：证件号\t\t************************");
        System.out.println("***********************\t\t7：确认修改\t\t************************");
        String CHOOSE = input.nextLine();
        int choose = Integer.parseInt(CHOOSE);
        if(choose == 0) {
            student.setUser_id(-1);
            return student;
        }
        else if(choose > 0 && choose < 7) {
            System.out.println("***********************\t\t请输入新值：\t\t************************");
            String data = input.nextLine();
            switch (choose) {
                case 1 -> student.setPhone(data);
                case 2 -> student.setEmail(data);
                case 3 -> student.setDormitory(data);
                case 4 -> student.setAddress(data);
                case 5 -> student.setId_type(data);
                case 6 -> student.setId_number(data);
                default -> {
                }
            }
            return EditPersonalInformationView(student);
        }
        else if(choose == 7){
            return student;
        }
        else {
            System.out.println("请按提示输入指令！");
            return EditPersonalInformationView(student);
        }
    }
}
