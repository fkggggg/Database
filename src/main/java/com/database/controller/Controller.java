package com.database.controller;

import com.database.bean.User;
import com.database.dao.UserDao_Imp;
import com.database.view.StudentView;
import com.database.view.View;

import java.sql.SQLException;

public class Controller {
    public static void main() throws SQLException {
        while(true)
        {
            User user = View.LoginView();
            UserDao_Imp userDao_imp= new UserDao_Imp();
            int permission = userDao_imp.login(user);
            switch (permission) {
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
                    StudentServer();
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
    private static void StudentServer() {
        UserDao_Imp userDao_imp = new UserDao_Imp();
        while(true)
        {
            int choose = View.StudentView();
            switch (choose){
                case 0:
                    System.exit(-1);
                case 1:
                    int choose1 = StudentView.PersonalInformationView();
                    switch (choose1){
                        case 0:
                            break;
                        case 1:
                            String data = StudentView.EditPersonalInformationView();
                            System.out.println(data);
                    }
            }
        }
    }






}
