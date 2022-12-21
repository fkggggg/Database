package com.database.view;

import com.database.bean.*;
import com.database.dao.AdmissionFormDao;
import com.database.testtime.Testtime;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        try{
            int choose = Integer.parseInt(CHOOSE);
            if(choose > -1 && choose < 2)
                return choose;
            else
            {
                System.out.println("请按提示输入指令！");
                return PersonalInformationView(student);
            }
        }catch (NumberFormatException e){
            System.out.println("请按提示输入指令！");
            return PersonalInformationView(student);
        }
    }

    //修改个人信息
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
        try{
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
        }catch (NumberFormatException e){
            System.out.println("请按提示输入指令！");
            return EditPersonalInformationView(student);
        }
    }
    //健康日报
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
            try{
                int choose = Integer.parseInt(CHOOSE);
                if (choose == 0) {
                    return new DailyReport(0);
                } else {
                    System.out.println("请按提示输入指令！");
                    return DailyReportView(dailyReport);
                }
            }catch (NumberFormatException e){
                System.out.println("请按提示输入指令！");
                return DailyReportView(dailyReport);
            }
        }else{
            System.out.println("***********************\t\t今日未填写健康日报!\t\t************************");
            System.out.println("***********************\t\t操作\t\t\t\t\t************************");
            System.out.println("***********************\t\t0：返回上一页面\t\t************************");
            System.out.println("***********************\t\t1：填写健康日报\t\t************************");
            String CHOOSE = input.nextLine();
            try{
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

                    LocalTime time = LocalTime.now();

                    if(choose3 == 0) {
                        return new DailyReport(-1,dailyReport.getStudent_id(),dailyReport.getDate(),time,health_condition,abnormal_description,temperature,location);
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
            }catch (NumberFormatException e){
                System.out.println("请按提示输入指令！");
                return DailyReportView(dailyReport);
            }
        }
    }

    //打卡记录
    public static CheckReport CheckReportView(CheckReport checkReport){
        int state = checkReport.getState();
        System.out.println("***********************\t\t打卡记录\t\t\t\t************************");
        System.out.println("\t\t当前状态：" + (state==0?"离校":"在校"));
        if(checkReport.getState()==1)
            System.out.println("\t\t所在校区：" + checkReport.getCampus());
        System.out.println("***********************\t\t操作\t\t\t\t\t************************");
        System.out.println("***********************\t\t0：返回上一页面\t\t************************");
        System.out.println("***********************\t\t1："+(state==0?"入校":"出校")+"\t\t\t\t************************");
        String CHOOSE = input.nextLine();
        try{
            int choose = Integer.parseInt(CHOOSE);
            if (choose == 0) {
                return checkReport;
            }
            else if (choose == 1){
                if(state == 0){
                    System.out.println("***********************\t\t选择进入校区：\t\t\t************************");
                    System.out.println("***********************\t\t0：H\t\t\t\t************************");
                    System.out.println("***********************\t\t1：J\t\t\t\t************************");
                    System.out.println("***********************\t\t2：F\t\t\t\t************************");
                    System.out.println("***********************\t\t3：Z\t\t\t\t************************");
                    String CHOOSE2 = input.nextLine();
                    int choose2 = Integer.parseInt(CHOOSE2);
                    char campus;
                    switch (choose2) {
                        case 0 -> campus = 'H';
                        case 1 -> campus = 'J';
                        case 2 -> campus = 'F';
                        case 3 -> campus = 'Z';
                        default -> {
                            System.out.println("请按提示输入指令！");
                            return CheckReportView(checkReport);
                        }
                    }
                    //此处在真实应用中应更改为真实日期，为防止测试时因学生未填写健康日报导致失去权限，使用测试日期
                    Testtime testtime1 = new Testtime();
                    LocalDate date = testtime1.gettestdate();
                    LocalTime time = LocalTime.now();
                    return new CheckReport(-1,checkReport.getStudent_id(),date,time,1,campus);
                }else{
                    //此处在真实应用中应更改为真实日期，为防止测试时因学生未填写健康日报导致失去权限，使用测试日期
                    Testtime testtime2 = new Testtime();
                    LocalDate date = testtime2.gettestdate();
                    LocalTime time = LocalTime.now();
                    return new CheckReport(-1,checkReport.getStudent_id(),date,time,0,checkReport.getCampus());
                }
            }else {
                System.out.println("请按提示输入指令！");
                return CheckReportView(checkReport);
            }
        }catch (Exception e){
            System.out.println("请按提示输入指令！");
            return CheckReportView(checkReport);
        }
    }

    public static AdmissionForm AdmissionFormView(AdmissionForm admissionForm){
        System.out.println("***********************\t\t入校申请表\t\t\t************************");
        if(admissionForm.getAdform_id() == -1){
            System.out.println("***********************\t\t当前无待审核入校申请表\t************************");
            System.out.println("***********************\t\t操作\t\t\t\t\t************************");
            System.out.println("***********************\t\t0：返回上一页面\t\t************************");
            System.out.println("***********************\t\t1：提交入校申请表\t\t************************");
            String CHOOSE = input.nextLine();
            try{
                int choose = Integer.parseInt(CHOOSE);
                if (choose == 0) {
                    return new AdmissionForm(0);
                }else if(choose == 1){
                    DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    int check=0;
                    LocalDate estimated_date=null;

                    System.out.println("***********************\t\t入校理由：\t\t\t************************");
                    String reason = input.nextLine();
                    while(check == 0)
                    {
                        System.out.println("***********************\t\t请输入预计入校日期(格式:年-月-日，示例：2022-12-25)：");
                        estimated_date = LocalDate.parse(input.nextLine(),df1);
                        if(estimated_date.isBefore(LocalDate.now()))
                            System.out.println("入校日期不能早于今天日期！");
                        else
                            check = 1;
                    }
                    System.out.println("***********************\t\t入校申请表填写\t\t\t************************");
                    System.out.println("\t\t入校理由：" + reason);
                    System.out.println("\t\t预计入校日期：" + estimated_date.toString());
                    System.out.println("***********************\t\t是否确认提交：\t\t\t************************");
                    System.out.println("***********************\t\t0：是\t\t\t\t************************");
                    System.out.println("***********************\t\t1：否\t\t\t\t************************");
                    String CHOOSE3 = input.nextLine();
                    int choose3 = Integer.parseInt(CHOOSE3);
                    Testtime testtime = new Testtime();
                    if(choose3 == 0) {
                        return new AdmissionForm(-1,testtime.gettestdate(),admissionForm.getStudent_id(),admissionForm.getName(),
                                admissionForm.getCollege_name(),admissionForm.getClass_name(),reason,estimated_date);
                    }else if(choose3 == 1){
                        return AdmissionFormView(admissionForm);
                    }else{
                        System.out.println("请按提示输入指令！");
                        return AdmissionFormView(admissionForm);
                    }
                } else {
                    System.out.println("请按提示输入指令！");
                    return AdmissionFormView(admissionForm);
                }
            }catch (Exception e){
                System.out.println("请按提示输入指令！");
                return AdmissionFormView(admissionForm);
            }
        }else{
            String statestr="";
            int state = admissionForm.getState();
            switch (state) {
                case 0 -> statestr += "班级辅导员审核中";
                case 1 -> statestr += "院系管理员审核中";
                case 2 -> statestr += "已通过";
                case -1 -> statestr += "班级辅导员已拒绝";
                case -2 -> statestr += "院系管理员已拒绝";
            }
            System.out.println("***********************\t\t我的入校申请\t\t\t************************");
            System.out.println("\t\t申请日期：" + admissionForm.getApplication_date().toString());
            System.out.println("\t\t入校理由：" + admissionForm.getReason());
            System.out.println("\t\t预计入校日期：" + admissionForm.getEstimated_date().toString());
            System.out.println("\t\t状态：" + statestr);
            if(state == -1 || state == -2)
                System.out.println("\t\t拒绝理由：" + admissionForm.getReject_reason());
            System.out.println("***********************\t\t操作\t\t\t\t\t************************");
            System.out.println("***********************\t\t0：返回上一页面\t\t************************");
            System.out.println("***********************\t\t1：撤销离校申请表\t\t************************");
            String CHOOSE = input.nextLine();
            try{
                int choose = Integer.parseInt(CHOOSE);
                if (choose == 0) {
                    return admissionForm;
                }else if(choose == 1) {
                    System.out.println("确认要撤销吗？该操作不可逆！ 0:确认撤销 1：返回");
                    String CHOOSE2 = input.nextLine();
                    int choose2 = Integer.parseInt(CHOOSE2);
                    if(choose2==0){
                        return new AdmissionForm(admissionForm.getAdform_id());
                    }else if(choose2==1){
                        return AdmissionFormView(admissionForm);
                    }else{
                        System.out.println("请按提示输入指令！");
                        return AdmissionFormView(admissionForm);
                    }
                }
            }catch (Exception e){
                System.out.println("请按提示输入指令！");
                return AdmissionFormView(admissionForm);
            }
        }
        return new AdmissionForm(-3);
    }

    public static DepartureForm DepartureFormView(DepartureForm departureForm){
        System.out.println("***********************\t\t离校申请表\t\t\t************************");
        if(departureForm.getDeform_id() == -1){
            System.out.println("***********************\t\t当前无待审核离校申请表\t************************");
            System.out.println("***********************\t\t操作\t\t\t\t\t************************");
            System.out.println("***********************\t\t0：返回上一页面\t\t************************");
            System.out.println("***********************\t\t1：提交离校申请表\t\t************************");
            String CHOOSE = input.nextLine();
            try{
                int choose = Integer.parseInt(CHOOSE);
                if (choose == 0) {
                    return new DepartureForm(0);
                }else if(choose == 1){
                    DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    int check=0;
                    LocalDate estimated_date=null;
                    LocalDate return_date=null;

                    System.out.println("***********************\t\t离校理由：\t\t\t************************");
                    String reason = input.nextLine();
                    System.out.println("***********************\t\t请输入目的地：\t\t\t************************");
                    String destination = input.nextLine();
                    while(check == 0)
                    {
                        System.out.println("***********************\t\t请输入预计离校日期(格式:年-月-日，示例：2022-12-25)：");
                        estimated_date = LocalDate.parse(input.nextLine(),df1);
                        if(estimated_date.isBefore(LocalDate.now()))
                            System.out.println("离校日期不能早于今天日期！");
                        else
                            check = 1;
                    }
                    while(check == 1)
                    {
                        System.out.println("***********************\t\t请输入预计返校日期(格式:年-月-日，示例：2022-12-25)：\t************************");
                        return_date = LocalDate.parse(input.nextLine(),df1);
                        if(return_date.isBefore(estimated_date))
                            System.out.println("返校日期不能早于离校日期！");
                        else
                            check = 0;
                    }
                    System.out.println("***********************\t\t离校申请表填写\t\t\t************************");
                    System.out.println("\t\t离校理由：" + reason);
                    System.out.println("\t\t目的地：" + destination);
                    System.out.println("\t\t预计离校日期：" + estimated_date.toString());
                    System.out.println("\t\t预计返校日期：" + return_date.toString());
                    System.out.println("***********************\t\t是否确认提交：\t\t\t************************");
                    System.out.println("***********************\t\t0：是\t\t\t\t************************");
                    System.out.println("***********************\t\t1：否\t\t\t\t************************");
                    String CHOOSE3 = input.nextLine();
                    int choose3 = Integer.parseInt(CHOOSE3);
                    Testtime testtime = new Testtime();
                    if(choose3 == 0) {
                        return new DepartureForm(-1,testtime.gettestdate(),departureForm.getStudent_id(),departureForm.getName(),
                                departureForm.getCollege_name(),departureForm.getClass_name(),reason,destination,estimated_date,return_date);
                    }else if(choose3 == 1){
                        return DepartureFormView(departureForm);
                    }else{
                        System.out.println("请按提示输入指令！");
                        return DepartureFormView(departureForm);
                    }
                } else {
                    System.out.println("请按提示输入指令！");
                    return DepartureFormView(departureForm);
                }
            }catch (Exception e){
                System.out.println("请按提示输入指令！");
                return DepartureFormView(departureForm);
            }
        }else{
            String statestr="";
            int state = departureForm.getState();
            switch (state) {
                case 0 -> statestr += "班级辅导员审核中";
                case 1 -> statestr += "院系管理员审核中";
                case 2 -> statestr += "已通过";
                case -1 -> statestr += "班级辅导员已拒绝";
                case -2 -> statestr += "院系管理员已拒绝";
            }
            System.out.println("***********************\t\t我的离校申请\t\t\t************************");
            System.out.println("\t\t申请日期：" + departureForm.getApplication_date().toString());
            System.out.println("\t\t离校理由：" + departureForm.getReason());
            System.out.println("\t\t目的地：" + departureForm.getDestination());
            System.out.println("\t\t预计离校日期：" + departureForm.getEstimated_date().toString());
            System.out.println("\t\t预计返校日期：" + departureForm.getReturn_date().toString());
            System.out.println("\t\t状态：" + statestr);
            if(state == -1 || state == -2)
                System.out.println("\t\t拒绝理由：" + departureForm.getReject_reason());
            System.out.println("***********************\t\t操作\t\t\t\t\t************************");
            System.out.println("***********************\t\t0：返回上一页面\t\t************************");
            System.out.println("***********************\t\t1：撤销离校申请表\t\t************************");
            String CHOOSE = input.nextLine();
            try{
                int choose = Integer.parseInt(CHOOSE);
                if (choose == 0) {
                    return departureForm;
                }else if(choose == 1) {
                    System.out.println("确认要撤销吗？该操作不可逆！ 0:确认撤销 1：返回");
                    String CHOOSE2 = input.nextLine();
                    int choose2 = Integer.parseInt(CHOOSE2);
                    if(choose2==0){
                        return new DepartureForm(departureForm.getDeform_id());
                    }else if(choose2==1){
                        return DepartureFormView(departureForm);
                    }else{
                        System.out.println("请按提示输入指令！");
                        return DepartureFormView(departureForm);
                    }
                }
            }catch (Exception e){
                System.out.println("请按提示输入指令！");
                return DepartureFormView(departureForm);
            }
        }
        return new DepartureForm(-3);
    }
}
