package com.database.test;

import com.database.bean.User;
import com.database.view.View;
import org.junit.Assert.*;
import org.junit.Test;

public class ViewTest {
    @Test
    public void LoginTest(){
        User user = View.LoginView();
        System.out.println(user);
    }
    @Test
    public void SchoolTest(){
        int i = View.SchoolView();
        System.out.println(i);
    }
    @Test
    public void CollegeTest(){
        int i = View.CollegeView();
        System.out.println(i);
    }
    @Test
    public void ClassTest(){
        int i = View.ClassView();
        System.out.println(i);
    }
    @Test
    public void StudentTest(){
        int i = View.StudentView();
        System.out.println(i);
    }

}
