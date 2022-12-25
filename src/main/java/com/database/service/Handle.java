package com.database.service;

import com.database.bean.AdmissionForm;
import com.database.bean.DepartureForm;
import com.database.bean.User;
import com.database.dao.AdmissionFormDao_Imp;
import com.database.dao.DepartureFormDao_Imp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Handle {
    private static Scanner input = new Scanner(System.in);
    public static void handleAdmission(User user) throws SQLException {

        AdmissionFormDao_Imp admissionFormDao_imp = new AdmissionFormDao_Imp();

        List<AdmissionForm> list = new ArrayList<>();
        List<AdmissionForm> rst = new ArrayList<>();
        list = admissionFormDao_imp.getAllAdmissionFormAfter(user, LocalDate.EPOCH);
        int state_to_handle;
        if (user.getPermission() == 1)
            state_to_handle = 1;
        else
            state_to_handle = 0;
        rst = list.stream().map(a->{
            if (a.getState() == state_to_handle)
                return a;
            else return null;
        }).collect(Collectors.toList());
        System.out.println("一共" + rst.size() + "条记录待处理");
        for (int i = 0; i < rst.size(); i++) {
            System.out.println(rst.get(i).toString());
        }
        if (rst.size()==0)
            return;

        // update
        System.out.println("输入要处理的编号");
        int no = input.nextInt();

        System.out.println("输入处理结果：0通过，1拒绝");
        int rstno = input.nextInt();
        int newstate;
        String reason = "";
        if (rstno == 1){
            System.out.println("输入拒绝理由");
            reason = input.next();
            newstate = (user.getPermission() == 1)?-2:-1;
        }
        else{
            newstate = (user.getPermission() == 1)?2:1;
        }
        boolean a = admissionFormDao_imp.updateAdmissionFormState(no, newstate, reason);

        if (a){
            System.out.println("更新成功！");
        }
        else{
            System.out.println("更新失败！");
        }

    }

    public static void handleDeparture(User user) throws SQLException {

        DepartureFormDao_Imp departureFormDao_imp = new DepartureFormDao_Imp();

        List<DepartureForm> list = new ArrayList<>();
        List<DepartureForm> rst = new ArrayList<>();
        list = departureFormDao_imp.getAllDepartureFormAfter(user.getPermission(), Util.getRangeNameByUser(user), LocalDate.EPOCH);
        int state_to_handle;
        if (user.getPermission() == 1)
            state_to_handle = 1;
        else
            state_to_handle = 0;
        rst = list.stream().map(a->{
            if (a.getState() == state_to_handle)
                return a;
            else return null;
        }).collect(Collectors.toList());
        System.out.println("一共" + rst.size() + "条记录待处理");
        for (int i = 0; i < rst.size(); i++) {
            System.out.println(rst.get(i).toString());
        }
        if (rst.size()==0)
            return;

        // update
        System.out.println("输入要处理的编号");
        int no = input.nextInt();

        System.out.println("输入处理结果：0通过，1拒绝");
        int rstno = input.nextInt();
        int newstate;
        String reason = "";
        if (rstno == 1){
            System.out.println("输入拒绝理由");
            reason = input.next();
            newstate = (user.getPermission() == 1)?-2:-1;
        }
        else{
            newstate = (user.getPermission() == 1)?2:1;
        }
        boolean a = departureFormDao_imp.updateDepartureFormState(no, newstate, reason);

        if (a){
            System.out.println("更新成功！");
        }
        else{
            System.out.println("更新失败！");
        }


    }

}
