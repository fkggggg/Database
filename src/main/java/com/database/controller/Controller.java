package com.database.controller;

import com.database.bean.Student;
import com.database.bean.User;
import com.database.dao.StudentDao_Imp;
import com.database.dao.UserDao_Imp;
import com.database.view.StudentView;
import com.database.view.View;

import java.sql.SQLException;

public class Controller {
    public static void main(String arg[]) throws SQLException {
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
    private static void StudentServer(User user) throws SQLException{
        UserDao_Imp userDao_imp = new UserDao_Imp();
        StudentDao_Imp studentDao_imp = new StudentDao_Imp();
        Student student = studentDao_imp.getStudent(user);
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
            }
        }
    }






}
