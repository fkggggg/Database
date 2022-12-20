package com.database.view;

import java.util.Scanner;

public class StudentView {
    private static Scanner input = new Scanner(System.in);
    //个人信息
    public static int PersonalInformationView(){
        System.out.println("***********************\t\t个人信息页面\t\t************************");
        System.out.println("***********************\t\t基本信息\t\t************************");
        System.out.println("\t\t学号：");
        System.out.println("\t\t姓名：");
        System.out.println("\t\t院系：");
        System.out.println("\t\t班级：");
        System.out.println("\t\t联系电话：");
        System.out.println("\t\t电子邮箱：");
        System.out.println("\t\t宿舍：");
        System.out.println("\t\t住址：");
        System.out.println("\t\t证件类型：");
        System.out.println("\t\t证件号：");
        System.out.println("***********************\t\t权限信息\t\t************************");
        System.out.println("\t\t今日是否健康打卡：");
        System.out.println("\t\t是否在校：");
        System.out.println("\t\t进校权限：");
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
            return PersonalInformationView();
        }
    }

    public static String EditPersonalInformationView(){
        System.out.println("***********************\t\t请选择一项进行修改：\t\t************************");
        System.out.println("***********************\t\t0：放弃修改\t\t************************");
        System.out.println("***********************\t\t1：联系电话\t\t************************");
        System.out.println("***********************\t\t2：电子邮箱\t\t************************");
        System.out.println("***********************\t\t3：宿舍\t\t************************");
        System.out.println("***********************\t\t4：住址\t\t************************");
        System.out.println("***********************\t\t5：证件类型\t\t************************");
        System.out.println("***********************\t\t6：证件号\t\t************************");
        String CHOOSE = input.nextLine();
        int choose = Integer.parseInt(CHOOSE);
        if(choose > -1 && choose < 7)
        {
            System.out.println("***********************\t\t请输入新值：\t\t************************");
            String data = input.nextLine();
            return CHOOSE+','+data;
        }
        else
        {
            System.out.println("请按提示输入指令！");
            return EditPersonalInformationView();
        }
    }
}