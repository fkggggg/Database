package com.database.service;

import com.database.bean.*;
import com.database.dao.*;
import com.database.dao.StudentDao.*;
import com.database.testtime.Testtime;
import jdk.incubator.vector.VectorOperators;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.database.view.View.StuRangeSearchView;
import static com.database.view.View.StuSearchView;

public class Search {
    private static Scanner input = new Scanner(System.in);
    /*************** 进阶查询 ****************/
//进阶需求:
//1) 过去 n 天尚未批准的入校申请和出校申请数量及详细信息；
    public static void DevSearch1(User user) throws ParseException, SQLException {
        System.out.println("过去 n 天尚未批准的入校申请和出校申请数量及详细信息");

        System.out.println("请输入参数n");
        int n = input.nextInt();

        AdmissionFormDao_Imp admissionFormDao_imp = new AdmissionFormDao_Imp();
        Testtime testtime = new Testtime();
        LocalDate localDate = testtime.gettestdate().minusDays(n);
        List<AdmissionForm> list = new ArrayList<>();
        List<AdmissionForm> rst = new ArrayList<>();
        switch (user.getPermission()){
            case 0:
            case 1:
                list = admissionFormDao_imp.getAllAdmissionFormAfter(user, localDate);
                rst = list.stream().map(a->{
                    if (a.getState() == 0 || a.getState() == 1)
                        return a;
                    else return null;
                }).collect(Collectors.toList());
                System.out.println("所管理范围内共查询到" + rst.size() + "条记录");
                for (int i = 0; i < rst.size(); i++) {
                    System.out.println(rst.get(i).toString());
                }

                break;
            case 2:
                list = admissionFormDao_imp.getAllAdmissionFormAfter(user, localDate);
                rst = list.stream().map(a->{
                    if (a.getState() == 0)
                        return a;
                    else return null;
                }).collect(Collectors.toList());
                System.out.println("所管理范围内共查询到" + rst.size() + "条记录");
                for (int i = 0; i < rst.size(); i++) {
                    System.out.println(rst.get(i).toString());
                }

                String range2 = Util.getCollegeNameByInstructor(user);
                int s = admissionFormDao_imp.getAllAdmissionFormAfter(1, range2, localDate).size();
                System.out.println("所在院系内共查询到" +s+"条记录");
                break;
            default:;
        }

    }
//2) 前 n 个提交入校申请最多的学生，支持按多级范围（全校、院系、班级）进行筛选；
    public static void DevSearch2(User user) throws SQLException {
        System.out.println("前 n 个提交入校申请最多的学生，支持按多级范围（全校、院系、班级）进行筛选");

        System.out.println("请输入参数n");
        int n = input.nextInt();
        ManagerDao managerDao = new ManagerDao();
        StudentDao_Imp studentDaoImp = new StudentDao_Imp();
        AdmissionFormDao_Imp admissionFormDao_imp = new AdmissionFormDao_Imp();
        System.out.println("请输入范围（0全校、1院系、2班级）");
        int m = input.nextInt();
        String range = null;
        switch (m){
            case 0:

            case 1:
                System.out.println("请输入院系名");
                range = input.next();
                if (managerDao.getCollegeAdministratorByName(range) == null){
                    System.out.println("无此名称！");
                }
                break;

            case 2:
                System.out.println("请输入班级名");
                range = input.next();
                if (managerDao.getClassInstructorByName(range) == null){
                    System.out.println("无此名称！");
                }
                break;

            default: return;
        }
        if (AuthCheck.checkRange(user, m, range) != AuthCheck.Auth.AUTH_DETAIL){
            System.out.println("无权限！");
            return;
        }
        List<Student> studentList = studentDaoImp.getAllStudent(m, range);
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            numList.add(admissionFormDao_imp.getAdmissionFormNumber(s.getStudent_id()));
        }
        for (int i = 0; i < n; i++) {
            int maxi = numList.indexOf(Collections.max(numList));
            System.out.println(studentList.get(maxi).toString());
            numList.remove(maxi);
            studentList.remove(maxi);

        }

    }
//3) 前 n 个平均离校时间最长的学生，支持按多级范围（全校、院系、班级）进行筛选；
    public static void DevSearch3(User user) throws SQLException {
        System.out.println("前 n 个平均离校时间最长的学生，支持按多级范围（全校、院系、班级）进行筛选");

        System.out.println("请输入参数n");
        int n = input.nextInt();
        ManagerDao managerDao = new ManagerDao();
        StudentDao_Imp studentDaoImp = new StudentDao_Imp();
        AdmissionFormDao_Imp admissionFormDao_imp = new AdmissionFormDao_Imp();
        System.out.println("请输入范围（0全校、1院系、2班级）");
        int m = input.nextInt();
        String range = null;
        switch (m){
            case 0:

            case 1:
                System.out.println("请输入院系名");
                range = input.next();
                if (managerDao.getCollegeAdministratorByName(range) == null){
                    System.out.println("无此名称！");
                }
                break;

            case 2:
                System.out.println("请输入班级名");
                range = input.next();
                if (managerDao.getClassInstructorByName(range) == null){
                    System.out.println("无此名称！");
                }
                break;

            default: return;
        }
        if (AuthCheck.checkRange(user, m, range) != AuthCheck.Auth.AUTH_DETAIL){
            System.out.println("无权限！");
            return;
        }
        List<Student> studentList = studentDaoImp.getAllStudent(m, range);
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            numList.add(admissionFormDao_imp.getAdmissionFormNumber(s.getStudent_id()));
        }
        for (int i = 0; i < n; i++) {
            int maxi = numList.indexOf(Collections.max(numList));
            System.out.println(studentList.get(maxi).toString());
            numList.remove(maxi);
            studentList.remove(maxi);

        }

    }
//4) 已出校但尚未返回校园（即离校状态）的学生数量、个人信息及各自的离校时间；
    public static void DevSearch4(User user) throws SQLException {
        System.out.println("已出校但尚未返回校园（即离校状态）的学生数量、个人信息及各自的离校时间");
        StudentDao_Imp studentDaoImp = new StudentDao_Imp();
        CheckReportDao_Imp checkReportDao_imp = new CheckReportDao_Imp();
        List<Student> studentList;
        List<Student> studentList2 = new ArrayList<>();
        List<CheckReport> checkReportList = new ArrayList<>();
        switch (user.getPermission()){
            case 0:
            case 1:
            case 2:
                studentList = studentDaoImp.getAllStudent(user.getPermission(), Util.getRangeNameByUser(user));
                for (int i = 0; i < studentList.size(); i++) {
                    Student s = studentList.get(i);
                    CheckReport checkReport = checkReportDao_imp.getlatestCheckReport(s.getStudent_id());
                    if(checkReport.getState() == 0){
                        studentList2.add(s);
                        checkReportList.add(checkReport);
                    }
                }

                System.out.println("所管理范围内共查询到" + studentList2.size() + "条记录");
                for (int i = 0; i < studentList2.size(); i++) {
                    System.out.println("学生信息");
                    System.out.println(studentList2.get(i).toString());
                    System.out.println("离校时间");
                    System.out.println(checkReportList.get(i).getDate().toString() +
                            checkReportList.get(i).getTime().toString());
                }
                break;
            default:;
        }
        if (user.getPermission() == 2){

            String range2 = Util.getCollegeNameByInstructor(user);
            studentList = studentDaoImp.getAllStudent(1, range2);
            int n = 0;
            for (Student s : studentList) {
                CheckReport checkReport = checkReportDao_imp.getlatestCheckReport(s.getStudent_id());
                if (checkReport.getState() == 0) {
                    n++;
                }
            }
            System.out.println("所在院系内共查询到" +n+"条记录");
        }
    }
//5) 未提交出校申请但离校状态超过 24h 的学生数量、个人信息；
    public static void DevSearch5(User user) throws Exception {
        System.out.println("未提交出校申请但离校状态超过 24h 的学生数量、个人信息");
        StudentDao_Imp studentDaoImp = new StudentDao_Imp();
        CheckReportDao_Imp checkReportDao_imp = new CheckReportDao_Imp();
        DepartureFormDao_Imp departureFormDao_imp = new DepartureFormDao_Imp();
        List<Student> studentList;
        List<Student> studentList2 = new ArrayList<>();
        switch (user.getPermission()){
            case 0:
            case 1:
            case 2:
                studentList = studentDaoImp.getAllStudent(user.getPermission(), Util.getRangeNameByUser(user));
                Testtime testtime = new Testtime();
                LocalDateTime dateTime = LocalDateTime.of(testtime.gettestdate().minusDays(1), testtime.gettesttime());
                for (int i = 0; i < studentList.size(); i++) {
                    Student s = studentList.get(i);
                    CheckReport checkReport = checkReportDao_imp.getlatestCheckReport(s.getStudent_id());
                    LocalDateTime last_out = LocalDateTime.of(checkReport.getDate(), checkReport.getTime());
                    if(checkReport.getState() == 0 && last_out.isBefore(dateTime)){
                        DepartureForm departureForm = departureFormDao_imp.getmyDepartureForm(s.getStudent_id());
                        if (departureForm.getReturn_date().isBefore(dateTime.toLocalDate())){
                            studentList2.add(s);
                        }
                    }
                }

                System.out.println("所管理范围内共查询到" + studentList2.size() + "条记录");
                for (int i = 0; i < studentList2.size(); i++) {
                    System.out.println("学生信息");
                    System.out.println(studentList2.get(i).toString());
                }
                break;
            default:;
        }
        if (user.getPermission() == 2){
            int m=0;
            String range2 = Util.getCollegeNameByInstructor(user);
            studentList = studentDaoImp.getAllStudent(1, range2);
            Testtime testtime = new Testtime();
            LocalDateTime dateTime = LocalDateTime.of(testtime.gettestdate().minusDays(1), testtime.gettesttime());
            for (int i = 0; i < studentList.size(); i++) {
                Student s = studentList.get(i);
                CheckReport checkReport = checkReportDao_imp.getlatestCheckReport(s.getStudent_id());
                LocalDateTime last_out = LocalDateTime.of(checkReport.getDate(), checkReport.getTime());
                if(checkReport.getState() == 0 && last_out.isBefore(dateTime)){
                    DepartureForm departureForm = departureFormDao_imp.getmyDepartureForm(s.getStudent_id());
                    if (departureForm.getReturn_date().isBefore(dateTime.toLocalDate())){
                        m++;
                    }
                }
            }

            System.out.println("所在院系内共查询到" +m+"条记录");
        }
    }
//6) 已提交出校申请但未离校的学生数量、个人信息；
    public static void DevSearch6(User user) throws Exception {
        System.out.println("已提交出校申请但未离校的学生数量、个人信息");
        StudentDao_Imp studentDaoImp = new StudentDao_Imp();
        CheckReportDao_Imp checkReportDao_imp = new CheckReportDao_Imp();
        DepartureFormDao_Imp departureFormDao_imp = new DepartureFormDao_Imp();
        List<Student> studentList;
        List<Student> studentList2 = new ArrayList<>();
        switch (user.getPermission()){
            case 0:
            case 1:
            case 2:
                studentList = studentDaoImp.getAllStudent(user.getPermission(), Util.getRangeNameByUser(user));
                Testtime testtime = new Testtime();
                LocalDateTime dateTime = LocalDateTime.of(testtime.gettestdate(), testtime.gettesttime());
                for (int i = 0; i < studentList.size(); i++) {
                    Student s = studentList.get(i);
                    CheckReport checkReport = checkReportDao_imp.getlatestCheckReport(s.getStudent_id());
                    if(checkReport.getState() == 1){
                        DepartureForm departureForm = departureFormDao_imp.getmyDepartureForm(s.getStudent_id());
                        if (departureForm.getReturn_date().isAfter(dateTime.toLocalDate())){
                            studentList2.add(s);
                        }
                    }
                }

                System.out.println("所管理范围内共查询到" + studentList2.size() + "条记录");
                for (int i = 0; i < studentList2.size(); i++) {
                    System.out.println("学生信息");
                    System.out.println(studentList2.get(i).toString());
                }
                break;
            default:;
        }
        if (user.getPermission() == 2){
            int m=0;
            String range2 = Util.getCollegeNameByInstructor(user);
            studentList = studentDaoImp.getAllStudent(1, range2);
            Testtime testtime = new Testtime();
            LocalDateTime dateTime = LocalDateTime.of(testtime.gettestdate().minusDays(1), testtime.gettesttime());
            for (int i = 0; i < studentList.size(); i++) {
                Student s = studentList.get(i);
                CheckReport checkReport = checkReportDao_imp.getlatestCheckReport(s.getStudent_id());
                if(checkReport.getState() == 1){
                    DepartureForm departureForm = departureFormDao_imp.getmyDepartureForm(s.getStudent_id());
                    if (departureForm.getReturn_date().isAfter(dateTime.toLocalDate())){
                        m++;
                    }
                }
            }

            System.out.println("所在院系内共查询到" +m+"条记录");
        }
    }
//7) 过去 n 天一直在校未曾出校的学生，支持按多级范围（全校、院系、班级）进行筛选；
    public static void DevSearch7(){

    }
//8) 连续 n 天填写“健康日报”时间（精确到分钟）完全一致的学生数量，个人信息；
    public static void DevSearch8(User user) throws Exception {
        System.out.println("连续 n 天填写“健康日报”时间（精确到分钟）完全一致的学生数量，个人信息");
        System.out.println("请输入参数n");
        int n = input.nextInt();

        StudentDao_Imp studentDaoImp = new StudentDao_Imp();
        DailyReportDao_Imp dailyReportDao_imp = new DailyReportDao_Imp();

        List<Student> studentList = studentDaoImp.getAllStudent(user.getPermission(), Util.getRangeNameByUser(user));
        List<Student> studentList2 = new ArrayList<>();

        Testtime testtime = new Testtime();
        LocalDateTime localDateTime = LocalDateTime.of(testtime.gettestdate(), testtime.gettesttime());
        LocalTime last = LocalTime.now();
        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            int j = 0;
            for (; j < n; j++) {
                DailyReport dailyReport = dailyReportDao_imp.getDailyReport(s.getStudent_id(), localDateTime.toLocalDate().minusDays(j));
                if (j!=0){
                    if (dailyReport.getTime().getHour() == last.getHour() &&
                            dailyReport.getTime().getMinute() == last.getMinute())
                        ;
                    else break;
                }
                last = dailyReport.getTime();
            }
            if (j == n){
                studentList2.add(s);
            }
        }

        System.out.println("所管理范围内共查询到" + studentList2.size() + "条记录");
        for (int i = 0; i < studentList2.size(); i++) {
            System.out.println("学生信息");
            System.out.println(studentList2.get(i).toString());
        }

        if (user.getPermission() == 2){
            int m=0;
            String range2 = Util.getCollegeNameByInstructor(user);
            studentList = studentDaoImp.getAllStudent(1, range2);
            for (int i = 0; i < studentList.size(); i++) {
                Student s = studentList.get(i);
                int j = 0;
                for (; j < n; j++) {
                    DailyReport dailyReport = dailyReportDao_imp.getDailyReport(s.getStudent_id(), localDateTime.toLocalDate().minusDays(j));
                    if (j!=0){
                        if (dailyReport.getTime().getHour() == last.getHour() &&
                                dailyReport.getTime().getMinute() == last.getMinute())
                            ;
                        else break;
                    }
                    last = dailyReport.getTime();
                }
                if (j == n){
                    studentList2.add(s);
                }
            }

            System.out.println("所在院系内共查询到" +studentList2.size()+"条记录");
        }
    }
//9) 过去 n 天每个院系学生产生最多出入校记录的校区。
    public static void DevSearch9(){

    }

    //调用者需保证opt在1~9之间
    public static void DevelopSearch(User user, int opt){

    }

    /*************** 学生信息 ****************/

//    1) 查询学生过去 n 天的每日填报信息；
    public static void BasSearch1(User user, String stu_id) throws SQLException {
        AuthCheck.Auth auth = AuthCheck.checkStu(user, stu_id);
        System.out.println("查询学生过去 n 天的每日填报信息");
        System.out.println("请输入n的值");
        int n = input.nextInt();
        Testtime testtime1 = null;
        LocalDate date = null;
        try {
            testtime1 = new Testtime();
             date = testtime1.gettestdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (auth){
            case AUTH_DETAIL -> {
                DailyReport report = new DailyReport();
                DailyReportDao_Imp dailyReportDao_imp = new DailyReportDao_Imp();
                for (int i=0; i<n; i++){
                    report = dailyReportDao_imp.getDailyReport(stu_id, date.minusDays(i));
                    System.out.println(report.toString());
                }

            }
            default -> {
                System.out.println("对不起，您无权限查看！");
            }
        }
    }
//    2) 查询学生的入校权限；
    public static void BasSearch2(User user, String stu_id) throws SQLException {
        AuthCheck.Auth auth = AuthCheck.checkStu(user, stu_id);
        System.out.println("查询学生的入校权限");
        switch (auth){
            case AUTH_DETAIL -> {
                StudentDao_Imp studentDaoImp = new StudentDao_Imp();
                Student student = studentDaoImp.getStudentByStuid(stu_id);
                System.out.println(student.getLimits());
            }
            default -> {
                System.out.println("对不起，您无权限查看！");
            }
        }
    }
//    3) 查询学生的入校申请、出校申请，支持按状态（待审核、已同意、已拒绝）进行筛选；
    public static void BasSearch3(User user, String stu_id) throws SQLException {
        AuthCheck.Auth auth = AuthCheck.checkStu(user, stu_id);
        System.out.println("查询学生的入校申请、出校申请");
        switch (auth){
            case AUTH_DETAIL -> {
                System.out.println("选择要查看的申请类型：0 所有、1 待审核、2 已同意、3 已拒绝");
                String str = input.nextLine();
                AdmissionFormDao_Imp admissionFormDao_imp = new AdmissionFormDao_Imp();
                DepartureFormDao_Imp departureFormDao_imp = new DepartureFormDao_Imp();
                List<AdmissionForm> admissionForms = admissionFormDao_imp.getAllAdmissionForm(stu_id);
                List<DepartureForm> departureForms = departureFormDao_imp.getAllDepartureForm(stu_id);
                if (str.contains("0")){
                    System.out.println("*********入校申请*********");
                    for (int i = 0; i < admissionForms.size(); i++) {
                        System.out.println(admissionForms.get(i).toString());
                    }
                    System.out.println("*********出校申请*********");
                    for (int i = 0; i < departureForms.size(); i++) {
                        System.out.println(departureForms.get(i).toString());
                    }
                }else{
                    List<Integer> integersAllowed = new ArrayList<>();
                    if (str.contains("1")){// 待审核
                        if(user.getPermission() == 0 || user.getPermission() == 1){
                            integersAllowed.add(0);
                            integersAllowed.add(1);
                        }
                        else{ // 2
                            integersAllowed.add(0);
                        }
                        List<AdmissionForm>  alst = admissionForms.stream().map(a->{
                            if (integersAllowed.contains(a.getState()))
                                return a;
                            else
                                return null;
                        }).collect(Collectors.toList());
                        List<DepartureForm> dlst = departureForms.stream().map(d->{
                            if (integersAllowed.contains(d.getState()))
                                return d;
                            else return null;
                        }).collect(Collectors.toList());
                        System.out.println("*********入校申请*********");
                        for (int i = 0; i < alst.size(); i++) {
                            System.out.println(alst.get(i).toString());
                        }
                        System.out.println("*********出校申请*********");
                        for (int i = 0; i < dlst.size(); i++) {
                            System.out.println(dlst.get(i).toString());
                        }
                    }
                    if (str.contains("2")){// 已同意
                        if(user.getPermission() == 0 || user.getPermission() == 1){
                            integersAllowed.add(2);
                        }
                        else{ // 2
                            integersAllowed.add(2);
                            integersAllowed.add(1);
                        }
                        List<AdmissionForm>  alst = admissionForms.stream().map(a->{
                            if (integersAllowed.contains(a.getState()))
                                return a;
                            else
                                return null;
                        }).collect(Collectors.toList());
                        List<DepartureForm> dlst = departureForms.stream().map(d->{
                            if (integersAllowed.contains(d.getState()))
                                return d;
                            else return null;
                        }).collect(Collectors.toList());
                        System.out.println("*********入校申请*********");
                        for (int i = 0; i < alst.size(); i++) {
                            System.out.println(alst.get(i).toString());
                        }
                        System.out.println("*********出校申请*********");
                        for (int i = 0; i < dlst.size(); i++) {
                            System.out.println(dlst.get(i).toString());
                        }
                    }
                    if (str.contains("3")){// 已拒绝

                        List<AdmissionForm>  alst = admissionForms.stream().map(a->{
                            if (a.getState() == -1 || a.getState() == -2)
                                return a;
                            else
                                return null;
                        }).collect(Collectors.toList());
                        List<DepartureForm> dlst = departureForms.stream().map(d->{
                            if (d.getState() == -1 || d.getState() == -2)
                                return d;
                            else return null;
                        }).collect(Collectors.toList());
                        System.out.println("*********入校申请*********");
                        for (int i = 0; i < alst.size(); i++) {
                            System.out.println(alst.get(i).toString());
                        }
                        System.out.println("*********出校申请*********");
                        for (int i = 0; i < dlst.size(); i++) {
                            System.out.println(dlst.get(i).toString());
                        }
                    }
                }

            }
            default -> {
                System.out.println("对不起，您无权限查看！");
            }
        }
    }
//    4) 查询学生（从当天算起）过去一年的离校总时长。
    public static void BasSearch4(User user, String stu_id) throws SQLException, ParseException {
        AuthCheck.Auth auth = AuthCheck.checkStu(user, stu_id);
        System.out.println("查询学生过去一年的离校总时长");
        switch (auth){
            case AUTH_DETAIL ->{
                CheckReportDao_Imp checkReportDao_imp = new CheckReportDao_Imp();
                Testtime testtime = new Testtime();
                LocalDate dDate = testtime.gettestdate().minusYears(1);
                LocalTime dTime = testtime.gettesttime();

                List<CheckReport> list = checkReportDao_imp.getAllCheckReport(stu_id);

                LocalDate latest_inDate = null;
                LocalTime latest_inTime = null;
                int time_checking = 0;
                int date_count = 0, time_count = 0;

                for (int i = 0; i < list.size(); i++) {
                    CheckReport report = list.get(i);
                    if (report.getDate().isBefore(dDate) ||
                            (report.getDate().isEqual(dDate) && report.getTime().isBefore(dTime)))
                        break;

                    if (report.getState() == 1 && time_checking == 0){// 入校
                        latest_inDate = report.getDate();
                        latest_inTime = report.getTime();
                        time_checking = 1;
                    }
                    else if(report.getState() == 0 && time_checking == 1){
                        date_count -= report.getDate().compareTo(latest_inDate);
                        time_count -= report.getTime().compareTo(latest_inTime);
                        time_checking = 0;
                    }
                }
                time_count += date_count * 86400;

                System.out.println("该生出校总时长为" + (time_count / 3600) + "小时"
                    + ((time_count % 3600) / 60) +"分钟" +time_count % 60 +"秒");

            }default -> {
                System.out.println("对不起，您无权限查看！");
            }
        }
    }

    // 查询学生信息
    public static void StuSearch(User user) throws SQLException, ParseException {
        String stu_id = StuRangeSearchView();
//        if (stu_id.equals("*")){
//
//        }
//        else{
            StudentDao_Imp studentDaoImp = new StudentDao_Imp();
            if(studentDaoImp.getStudentByStuid(stu_id) != null){
                int opt = StuSearchView();
                switch (opt){
                    case 0: return;
                    case 1:
                        BasSearch1(user, stu_id);
                        break;
                    case 2:
                        BasSearch2(user, stu_id);
                        break;
                    case 3:
                        BasSearch3(user, stu_id);
                        break;
                    case 4:
                        BasSearch4(user, stu_id);
                        break;
                    default:
                        System.out.println("error");

                }
            }
            else{
                System.out.println("无此学生！");
            }

//        }
    }


    /*************** 申请表 ****************/
    public static void searchDeparture(){

    }

    public static void searchAdmission(){

    }

}
