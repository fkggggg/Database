package com.database.controller;

import com.database.bean.CheckReport;
import com.database.bean.DailyReport;
import com.database.bean.Student;
import com.database.bean.User;
import com.database.dao.CheckReportDao_Imp;
import com.database.dao.DailyReportDao_Imp;
import com.database.dao.StudentDao_Imp;
import com.database.dao.UserDao_Imp;
import com.database.testtime.Testtime;
import com.database.view.StudentView;
import com.database.view.View;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

public class Controller {
    public static void main(String arg[]) throws SQLException, ParseException {

        while(true)
        {
            User loginuser = View.LoginView();
            UserDao_Imp userDao_imp= new UserDao_Imp();
            User user = userDao_imp.login(loginuser);
            switch (user.getPermission()) {
                case -2 -> System.out.println("该用户名不存在，请重新输入！");
                case -1 -> System.out.println("用户名与密码不匹配，请重新输入！");
                case 0 -> {
                    System.out.println("超级管理员登录！");
                    SchoolServer();
                }
                case 1 -> {
                    System.out.println("院系管理员登录！");
                    CollegeServer();
                }
                case 2 -> {
                    System.out.println("班级辅导员登录！");
                    ClassServer();
                }
                case 3 -> {
                    System.out.println("学生登录！");
                    StudentServer(user);
                }
                default -> {
                }
            }
        }
    }

    private static void SchoolServer() {
    }
    private static void CollegeServer() {
    }
    private static void ClassServer() {
    }
    private static void StudentServer(User user) throws SQLException, ParseException {
        UserDao_Imp userDao_imp = new UserDao_Imp();
        StudentDao_Imp studentDao_imp = new StudentDao_Imp();
        DailyReportDao_Imp dailyReportDao_imp = new DailyReportDao_Imp();
        CheckReportDao_Imp checkReportDao_imp = new CheckReportDao_Imp();

        //获取学生信息
        Student student = studentDao_imp.getStudent(user);

        //获取该学生当天健康日报
        LocalDate today = null;
        try {
            Testtime testtime = new Testtime();
            today = testtime.gettestdate();
        } catch (Exception ignored) {
        }
        DailyReport todayDailyReport = dailyReportDao_imp.getDailyReport(student.getStudent_id(),today);
        if(todayDailyReport.getDaily_report_id() != -1)
            student.setIsreport(1);
        //学生当天未填写健康日报，预先将学号等信息写入健康日报
        else{
            student.setIsreport(0);
            todayDailyReport.setStudent_id(student.getStudent_id());
            todayDailyReport.setDate(today);
        }

        //获取该学生最新打卡记录
        CheckReport checkReport = checkReportDao_imp.getlatestCheckReport(student.getStudent_id());
        student.setIscheck(checkReport.getState());
        //对于从未打卡过的学生（新生入学？），预先将学号信息写入打卡记录
        checkReport.setStudent_id(student.getStudent_id());

        while(true)
        {
            int choose = View.StudentView();
            switch (choose) {
                case 0 -> System.exit(-1);
                case 1 -> {
                    boolean exit = false;
                    while (!exit) {
                        int choose1 = StudentView.PersonalInformationView(student);
                        switch (choose1) {
                            case 0 -> exit = true;
                            case 1 -> {
                                Student editstudent = null;
                                try {
                                    editstudent = (Student) student.clone();
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                StudentView.EditPersonalInformationView(editstudent);
                                if (editstudent.getUser_id() != -1) {
                                    boolean update = studentDao_imp.updateStudent(editstudent);
                                    if (update) {
                                        System.out.println("修改成功！");
                                        student = editstudent;
                                    } else
                                        System.out.println("修改失败！");
                                }
                            }

                        }
                    }
                }
                case 2 ->{
                    DailyReport adddailyreport = StudentView.DailyReportView(todayDailyReport);
                    if(adddailyreport.getDaily_report_id() == -1)
                    {
                        boolean add = dailyReportDao_imp.addDailyReport(adddailyreport);
                        if(add){
                            System.out.println("填写成功！");
                            todayDailyReport = dailyReportDao_imp.getDailyReport(student.getStudent_id(),today);
                            student.setIsreport(1);
                            adddailyreport = StudentView.DailyReportView(todayDailyReport);
                        }else{
                            System.out.println("填写失败！");
                            adddailyreport = StudentView.DailyReportView(todayDailyReport);
                        }
                    }
                }
                case 3 ->{
                    boolean exit = false;
                    while (!exit) {
                        CheckReport newcheckreport = StudentView.CheckReportView(checkReport);
                        if(newcheckreport.getCheck_report_id() == -1)
                        {
                            int add = checkReportDao_imp.addCheckReport(newcheckreport,student);
                            if(add == 1){
                                System.out.println("打卡成功！");
                                checkReport = checkReportDao_imp.getlatestCheckReport(student.getStudent_id());
                                student.setIscheck(checkReport.getState());
                            }else if(add == -1){
                                System.out.println("打卡失败！");
                            }else if(add == 0){
                                System.out.println("没有入校权限！打卡失败！");
                            }
                        }else{
                            exit = true;
                        }
                    }
                }

            }
        }
    }






}
