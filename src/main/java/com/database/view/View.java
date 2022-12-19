package com.database.view;

import com.database.bean.User;

import java.util.Scanner;

public class View {

    private static Scanner input = new Scanner(System.in);

    //登录页
    public static User LoginView(){
        System.out.println("************************************************************************");
        System.out.println("***********************\t\tA大学进出校管理系统\t\t************************");
        System.out.println("***********************\t\t用户登录\t\t\t\t************************");
        System.out.println("***********************\t\t请输入账号：\t\t\t************************");
        String user_name = input.nextLine();
        System.out.println("***********************\t\t请输入密码：\t\t\t************************");
        String password = input.nextLine();
        return new User(user_name, password);
    }

    //超级管理员页面
    public static int SchoolView(){
        System.out.println("************************************************************************");
        System.out.println("***********************\t\t学校管理\t\t\t\t************************");
        System.out.println("***********************\t\t0：退出\t\t\t\t************************");
        System.out.println("***********************\t\t1：查询学生信息\t\t************************");
        String CHOOSE = input.nextLine();
        int choose = Integer.parseInt(CHOOSE);
        if(choose == 0 || choose == 1)
            return choose;
        else
        {
            System.out.println("请按提示输入指令！");
            return SchoolView();
        }
    }

    //学院页面
    public static int CollegeView(){
        System.out.println("************************************************************************");
        System.out.println("***********************\t\t学院管理\t\t\t\t************************");
        System.out.println("***********************\t\t0：退出\t\t\t\t************************");
        System.out.println("***********************\t\t1：查询学生信息\t\t************************");
        System.out.println("***********************\t\t2：查询入校申请\t\t************************");
        System.out.println("***********************\t\t3：查询出校申请\t\t************************");
        String CHOOSE = input.nextLine();
        int choose = Integer.parseInt(CHOOSE);
        if(choose > -1 && choose < 4)
            return choose;
        else
        {
            System.out.println("请按提示输入指令！");
            return CollegeView();
        }
    }

    //班级页面
    public static int ClassView(){
        System.out.println("************************************************************************");
        System.out.println("***********************\t\t班级管理\t\t\t\t************************");
        System.out.println("***********************\t\t0：退出\t\t\t\t************************");
        System.out.println("***********************\t\t1：查询学生信息\t\t************************");
        System.out.println("***********************\t\t2：查询入校申请\t\t************************");
        System.out.println("***********************\t\t3：查询出校申请\t\t************************");
        System.out.println("***********************\t\t4：查看其他班级\t\t************************");
        String CHOOSE = input.nextLine();
        int choose = Integer.parseInt(CHOOSE);
        if(choose > -1 && choose < 5)
            return choose;
        else
        {
            System.out.println("请按提示输入指令！");
            return ClassView();
        }
    }

    //学生页面
    public static int StudentView(){
        System.out.println("************************************************************************");
        System.out.println("***********************\t\t个人页面\t\t\t\t************************");
        System.out.println("***********************\t\t0：退出\t\t\t\t************************");
        System.out.println("***********************\t\t1：个人信息\t\t\t************************");
        System.out.println("***********************\t\t2：填写健康日报\t\t************************");
        System.out.println("***********************\t\t3：进/出校打卡 \t\t************************");
        System.out.println("***********************\t\t4：我的入校申请 \t\t************************");
        System.out.println("***********************\t\t5：我的出校申请 \t\t************************");
        System.out.println("***********************\t\t6：查看本班数据 \t\t************************");
        String CHOOSE = input.nextLine();
        int choose = Integer.parseInt(CHOOSE);
        if(choose > -1 && choose < 7)
            return choose;
        else
        {
            System.out.println("请按提示输入指令！");
            return StudentView();
        }
    }
}
