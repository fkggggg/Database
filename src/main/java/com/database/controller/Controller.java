package com.database.controller;

import com.database.bean.*;
import com.database.dao.*;
import com.database.service.Search;
import com.database.testtime.Testtime;
import com.database.view.StudentView;
import com.database.view.View;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

import static com.database.service.Handle.handleAdmission;
import static com.database.service.Handle.handleDeparture;
import static com.database.service.Search.DevelopSearch;
import static com.database.service.Search.StuSearch;
import static com.database.view.View.*;

public class Controller {
    public static void main(String arg[]) throws Exception {
        Testtime testtime=new Testtime();
        testtime.changedate();
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
                    SchoolServer(user);
                }
                case 1 -> {
                    System.out.println("院系管理员登录！");
                    CollegeServer(user);
                }
                case 2 -> {
                    System.out.println("班级辅导员登录！");
                    ClassServer(user);
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

    private static void SchoolServer(User user) throws Exception {
        /*
        学校管理
        0：退出
        1：查询学生信息
        2：进阶查询
        */
        while(true){

            int opt = SchoolView();
            switch (opt){
                case 0: return;

                case 1:
                    StuSearch(user);
                    break;

                case 2:
                    int dev_opt = DevelopSearchView();
                    DevelopSearch(user, dev_opt);
                    break;
                default:
                    System.out.println("error!");
            }
        }
    }
    private static void CollegeServer(User user) throws Exception {
        /* 学院管理
        0：退出
        1：查询学生信息
        2：查询入校申请
        3：查询出校申请
        4：进阶查询
        */
        while (true){

            int opt = CollegeView();
            switch (opt){
                case 0: return;

                case 1:
                    StuSearch(user);
                    break;

                case 2:
                    handleAdmission(user);
                    break;

                case 3:
                    handleDeparture(user);
                    break;

                case 4:
                    int dev_opt = DevelopSearchView();
                    DevelopSearch(user, dev_opt);
                    break;

                default:
                    System.out.println("error!");
            }
        }
    }
    private static void ClassServer(User user) throws Exception {
        /*班级管理
        0：退出
        1：查询学生信息
        2：查询并处理入校申请
        3：查询出校申请
        4：进阶查询
        */

        while(true){

            int opt = ClassView();
            switch (opt){
                case 0: return;

                case 1:
                    StuSearch(user);
                    break;

                case 2:
                    handleAdmission(user);
                    break;

                case 3:
                    handleDeparture(user);
                    break;

                case 4:
                    int dev_opt = DevelopSearchView();
                    DevelopSearch(user, dev_opt);
                    break;

                default:
                    System.out.println("error!");
            }
        }
    }
    private static void StudentServer(User user) throws Exception {
        UserDao_Imp userDao_imp = new UserDao_Imp();
        StudentDao_Imp studentDao_imp = new StudentDao_Imp();
        DailyReportDao_Imp dailyReportDao_imp = new DailyReportDao_Imp();
        CheckReportDao_Imp checkReportDao_imp = new CheckReportDao_Imp();
        DepartureFormDao_Imp departureFormDao_imp = new DepartureFormDao_Imp();
        AdmissionFormDao_Imp admissionFormDao_imp = new AdmissionFormDao_Imp();
        int exitstudent=1;

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

        //获取该学生最新入校申请表
        AdmissionForm admissionForm = admissionFormDao_imp.getmyAdmissionForm(student.getStudent_id());
        //若无待审核的入校申请表，表单中预先写入信息
        if(admissionForm.getAdform_id() == -1) {
            admissionForm.setStudent_id(student.getStudent_id());
            admissionForm.setName(student.getName());
            admissionForm.setCollege_name(student.getCollege_name());
            admissionForm.setClass_name(student.getClass_name());
        }

        //获取该学生最新离校申请表
        DepartureForm departureForm = departureFormDao_imp.getmyDepartureForm(student.getStudent_id());
        //若无待审核的离校申请表，表单中预先写入信息
        if(departureForm.getDeform_id() == -1) {
            departureForm.setStudent_id(student.getStudent_id());
            departureForm.setName(student.getName());
            departureForm.setCollege_name(student.getCollege_name());
            departureForm.setClass_name(student.getClass_name());
        }

        while(exitstudent==1)
        {
            int choose = View.StudentView();
            switch (choose) {
                case 0 -> exitstudent = 0;
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
                        }else{
                            System.out.println("填写失败！");
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
                case 4 ->{
                    boolean exit = false;
                    while (!exit) {
                        AdmissionForm newadmissionform = StudentView.AdmissionFormView(admissionForm);
                        if(newadmissionform.getAdform_id() == admissionForm.getAdform_id() && newadmissionform.getStudent_id() == null)
                        {
                            boolean delete = admissionFormDao_imp.deleteAdmissionForm(newadmissionform.getAdform_id());
                            if(delete){
                                System.out.println("撤销成功！");
                                admissionForm = admissionFormDao_imp.getmyAdmissionForm(student.getStudent_id());
                            }else{
                                System.out.println("撤销失败！");
                            }
                        }else if(newadmissionform.getAdform_id() == -1){
                            boolean add = admissionFormDao_imp.addAdmissionForm(newadmissionform);
                            if(add){
                                System.out.println("申请成功！");
                                admissionForm = admissionFormDao_imp.getmyAdmissionForm(student.getStudent_id());
                            }else{
                                System.out.println("申请失败！");
                            }
                        }else{
                            exit = true;
                        }
                    }
                }
                case 5 ->{
                    boolean exit = false;
                    while (!exit) {
                        DepartureForm newdepartureform = StudentView.DepartureFormView(departureForm);
                        if(newdepartureform.getDeform_id() == departureForm.getDeform_id() && newdepartureform.getStudent_id() == null)
                        {
                            boolean delete = departureFormDao_imp.deleteDepartureForm(newdepartureform.getDeform_id());
                            if(delete){
                                System.out.println("撤销成功！");
                                departureForm = departureFormDao_imp.getmyDepartureForm(student.getStudent_id());
                                if(departureForm.getDeform_id() == -1) {
                                    departureForm.setStudent_id(student.getStudent_id());
                                    departureForm.setName(student.getName());
                                    departureForm.setCollege_name(student.getCollege_name());
                                    departureForm.setClass_name(student.getClass_name());
                                }
                            }else{
                                System.out.println("撤销失败！");
                            }
                        }else if(newdepartureform.getDeform_id() == -1){
                            boolean add = departureFormDao_imp.addDepartureForm(newdepartureform);
                            if(add){
                                System.out.println("申请成功！");
                                departureForm = departureFormDao_imp.getmyDepartureForm(student.getStudent_id());
                            }else{
                                System.out.println("申请失败！");
                            }
                        }else{
                            exit = true;
                        }
                    }
                }
                case 6 ->{
                    int dev_opt = DevelopSearchView();
                    DevelopSearch(user, dev_opt);
                }
            }
        }
    }






}
