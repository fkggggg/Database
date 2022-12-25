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
        System.out.println("***********************\t\t请输入用户名：\t\t\t************************");
        String user_name = input.nextLine();
        System.out.println("***********************\t\t请输入密码：\t\t\t************************");
        String password = input.nextLine();
        return new User(user_name, password);
    }

    //超级管理员页面
    public static int SchoolView() {
        System.out.println("************************************************************************");
        System.out.println("***********************\t\t学校管理\t\t\t\t************************");
        System.out.println("***********************\t\t0：退出\t\t\t\t************************");
        System.out.println("***********************\t\t1：查询学生信息\t\t************************");
        System.out.println("***********************\t\t2：进阶查询\t\t************************");
        String CHOOSE = input.nextLine();
        try {
            int choose = Integer.parseInt(CHOOSE);
            if (choose > -1 && choose < 3)
                return choose;
            else {
                System.out.println("请按提示输入指令！");
                return SchoolView();
            }
        } catch (NumberFormatException e) {
            System.out.println("请按提示输入指令！");
            return SchoolView();
        }
    }

    //学院页面
    public static int CollegeView() {
        System.out.println("************************************************************************");
        System.out.println("***********************\t\t学院管理\t\t\t\t************************");
        System.out.println("***********************\t\t0：退出\t\t\t\t************************");
        System.out.println("***********************\t\t1：查询学生信息\t\t************************");
        System.out.println("***********************\t\t2：查询入校申请\t\t************************");
        System.out.println("***********************\t\t3：查询出校申请\t\t************************");
        System.out.println("***********************\t\t4：进阶查询\t\t\t************************");
        String CHOOSE = input.nextLine();
        try {
            int choose = Integer.parseInt(CHOOSE);
            if (choose > -1 && choose < 5)
                return choose;
            else {
                System.out.println("请按提示输入指令！");
                return CollegeView();
            }
        } catch (NumberFormatException e) {
            System.out.println("请按提示输入指令！");
            return CollegeView();
        }
    }

    //班级页面
    public static int ClassView() {
        System.out.println("************************************************************************");
        System.out.println("***********************\t\t班级管理\t\t\t\t************************");
        System.out.println("***********************\t\t0：退出\t\t\t\t************************");
        System.out.println("***********************\t\t1：查询学生信息\t\t************************");
        System.out.println("***********************\t\t2：查询入校申请\t\t************************");
        System.out.println("***********************\t\t3：查询出校申请\t\t************************");
        System.out.println("***********************\t\t4：进阶查询\t\t\t************************");
        String CHOOSE = input.nextLine();
        try {
            int choose = Integer.parseInt(CHOOSE);
            if (choose > -1 && choose < 6)
                return choose;
            else {
                System.out.println("请按提示输入指令！");
                return ClassView();
            }
        } catch (NumberFormatException e) {
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
        try{
            int choose = Integer.parseInt(CHOOSE);
            if(choose > -1 && choose < 7)
                return choose;
            else
            {
                System.out.println("请按提示输入指令！");
                return StudentView();
            }
        }catch (NumberFormatException e){
            System.out.println("请按提示输入指令！");
            return StudentView();
        }
    }

    // 进阶查询输出视图
    public static int DevelopSearchView(){
        System.out.println(
                "0) 退出进阶查询；\n" +
                "1) 过去 n 天尚未批准的入校申请和出校申请数量及详细信息；\n" +
                "2) 前 n 个提交入校申请最多的学生，支持按多级范围（全校、院系、班级）进行筛选；\n" +
                "3) 前 n 个平均离校时间最长的学生，支持按多级范围（全校、院系、班级）进行筛选；\n" +
                "4) 已出校但尚未返回校园（即离校状态）的学生数量、个人信息及各自的离校时间；\n" +
                "5) 未提交出校申请但离校状态超过 24h 的学生数量、个人信息；\n" +
                "6) 已提交出校申请但未离校的学生数量、个人信息；\n" +
                "7) 过去 n 天一直在校未曾出校的学生，支持按多级范围（全校、院系、班级）进行筛选；\n" +
                "8) 连续 n 天填写“健康日报”时间（精确到分钟）完全一致的学生数量，个人信息；\n" +
                "9) 过去 n 天每个院系学生产生最多出入校记录的校区。");
        String CHOOSE = input.nextLine();
        try{
            int choose = Integer.parseInt(CHOOSE);
            if(choose > -1 && choose < 10)
                return choose;
            else
            {
                System.out.println("请按提示输入指令！");
                return DevelopSearchView();
            }
        }catch (NumberFormatException e){
            System.out.println("请按提示输入指令！");
            return DevelopSearchView();
        }
    }

    // 查询学生输出视图
    public static int StuSearchView(){
        System.out.println(
                "0) 退出学生查询；\n" +
                        "1) 查询学生过去 n 天的每日填报信息；\n" +
                        "2) 查询学生的入校权限；\n" +
                        "3) 查询学生的入校申请、出校申请，支持按状态（待审核、已同意、已拒绝）进行筛选；\n" +
                        "4) 查询学生（从当天算起）过去一年的离校总时长。");
        String CHOOSE = input.nextLine();
        try{
            int choose = Integer.parseInt(CHOOSE);
            if(choose > -1 && choose < 5)
                return choose;
            else
            {
                System.out.println("请按提示输入指令！");
                return StuSearchView();
            }
        }catch (NumberFormatException e){
            System.out.println("请按提示输入指令！");
            return StuSearchView();
        }
    }
    // 查询学生范围输出视图
    // 返回*代表查找权限内所有
    public static String StuRangeSearchView(){
        System.out.println("输入要查询的学生学号；输入 * 可查询所有学生。");
        String stu_id;
        do{
            stu_id = input.nextLine();
        }while(stu_id.length() == 0);

        if (stu_id.contains("*"))
            return "*";
        else return stu_id;

    }
}
