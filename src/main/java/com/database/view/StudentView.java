package com.database.view;

import com.database.bean.DailyReport;
import com.database.bean.Student;
import com.database.testtime.Testtime;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.Scanner;

public class StudentView {
    private static Scanner input = new Scanner(System.in);
    //个人信息
    public static int PersonalInformationView(Student student){
        System.out.println("***********************\t\t个人信息页面\t\t\t************************");
        System.out.println("***********************\t\t基本信息\t\t\t\t************************");
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
        System.out.println("***********************\t\t权限信息\t\t\t\t************************");
        System.out.println("\t\t今日是否健康打卡："+(student.getIsreport()==1?"是":"否"));
        System.out.println("\t\t是否在校："+(student.getIscheck()==1?"是":"否"));
        System.out.println("\t\t进校权限："+student.getLimits());
        System.out.println("***********************\t\t操作\t\t\t\t\t************************");
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

    public static DailyReport DailyReportView(DailyReport dailyReport) throws ParseException {
        System.out.println("***********************\t\t健康日报\t\t\t\t************************");
        if(dailyReport.getDaily_report_id()!=-1) {
            System.out.println("\t\t学号：" + dailyReport.getStudent_id());
            System.out.println("\t\t日期：" + dailyReport.getDate().toString());
            System.out.println("\t\t时间：" + dailyReport.getTime().toString());
            System.out.println("\t\t是否感觉不适：" + (dailyReport.getHealth_condition() == 1 ? "否" : "是"));
            System.out.println("\t\t异常状况：" + dailyReport.getAbnormal_description());
            System.out.println("\t\t体温：" + dailyReport.getTemperature());
            System.out.println("\t\t位置：" + dailyReport.getLocation());
            System.out.println("***********************\t\t今日已填写健康日报!\t\t************************");
            System.out.println("***********************\t\t操作\t\t\t\t\t************************");
            System.out.println("***********************\t\t0：返回上一页面\t\t************************");
            String CHOOSE = input.nextLine();
            int choose = Integer.parseInt(CHOOSE);
            if (choose == 0) {
                return new DailyReport(0);
            } else {
                System.out.println("请按提示输入指令！");
                return DailyReportView(dailyReport);
            }
        }else{
            System.out.println("***********************\t\t今日未填写健康日报!\t\t************************");
            System.out.println("***********************\t\t操作\t\t\t\t\t************************");
            System.out.println("***********************\t\t0：返回上一页面\t\t************************");
            System.out.println("***********************\t\t1：填写健康日报\t\t************************");
            String CHOOSE = input.nextLine();
            int choose = Integer.parseInt(CHOOSE);

            if (choose == 0) {
                return new DailyReport(0);
            }else if(choose == 1){
                int health_condition = 1;
                String abnormal_description = "无";
                String temperature = "";
                String location = "";

                System.out.println("***********************\t\t是否感觉不适：\t\t\t************************");
                System.out.println("***********************\t\t0：是\t\t\t\t************************");
                System.out.println("***********************\t\t1：否\t\t\t\t************************");
                String CHOOSE2 = input.nextLine();
                health_condition = Integer.parseInt(CHOOSE2);
                if(health_condition == 0){
                    System.out.println("***********************\t\t请输入异常状况：\t\t************************");
                    abnormal_description = input.nextLine();
                }else if(health_condition != 1)
                {
                    System.out.println("请按提示输入指令！");
                    return DailyReportView(dailyReport);
                }
                System.out.println("***********************\t\t请输入体温：\t\t\t************************");
                temperature = input.nextLine();
                System.out.println("***********************\t\t请输入位置：\t\t\t************************");
                location = input.nextLine();
                System.out.println("***********************\t\t健康日报填写\t\t\t************************");
                System.out.println("\t\t是否感觉不适：" + (health_condition == 1 ? "否" : "是"));
                System.out.println("\t\t异常状况：" + abnormal_description);
                System.out.println("\t\t体温：" + temperature);
                System.out.println("\t\t位置：" + location);
                System.out.println("***********************\t\t是否确认提交：\t\t\t************************");
                System.out.println("***********************\t\t0：是\t\t\t\t************************");
                System.out.println("***********************\t\t1：否\t\t\t\t************************");
                String CHOOSE3 = input.nextLine();
                int choose3 = Integer.parseInt(CHOOSE3);

                //此处在真实应用中可更改为真实时间，为防止测试时与预先写入的数据冲突，使用测试时间
                Testtime testtime = new Testtime();
                LocalTime time = testtime.gettesttime();

                if(choose3 == 0) {
                    return new DailyReport(dailyReport.getStudent_id(),dailyReport.getDate(),time,health_condition,abnormal_description,temperature,location);
                }else if(choose3 == 1){
                    return DailyReportView(dailyReport);
                }else{
                    System.out.println("请按提示输入指令！");
                    return DailyReportView(dailyReport);
                }
            } else {
                System.out.println("请按提示输入指令！");
                return DailyReportView(dailyReport);
            }
        }
    }
}
