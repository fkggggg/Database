package com.database.service;

import com.database.bean.Student;
import com.database.bean.User;
import com.database.dao.ManagerDao;
import com.database.dao.StudentDao_Imp;

import java.sql.SQLException;
import java.util.Map;

public class AuthCheck {
    public enum Auth{
        AUTH_DETAIL,
        AUTH_STAT,
        NO_AUTH
    }

    public enum Range{
        SCHOOL,
        COLLEGE,
        CLASS,
        STUDENT
    }

    // 检查user对于rangeToCheck对应范围内的权限
    public static Auth check(User user, User rangeToCheck){
        return Auth.NO_AUTH;
    }

    public static Auth checkStu(User user, String stu_id) throws SQLException {
        switch (user.getPermission()){
            case 0: return Auth.AUTH_DETAIL;
            case 1: case 2: break;
            default: return Auth.NO_AUTH;
        }

        StudentDao_Imp studentDaoImp = new StudentDao_Imp();
        Student stu = studentDaoImp.getStudentByStuid(stu_id);
        if (user.getPermission() == 1){
            if (stu.getCollege_name().equals(Util.getRangeNameByUser(user)))
                return Auth.AUTH_DETAIL;
            else return Auth.NO_AUTH;
        }
        else{
            if (stu.getClass_name().equals(Util.getRangeNameByUser(user)))
                return Auth.AUTH_DETAIL;
            else if (stu.getCollege_name().equals(Util.getCollegeNameByInstructor(user)))
                return Auth.AUTH_STAT;
            else return Auth.NO_AUTH;
        }
    }

    /*
    * perm==1 or 2
    * range=college_name or class_name
    * */
    public static Auth checkRange(User user, int perm, String range) throws SQLException {
        switch (user.getPermission()){
            case 0: return Auth.AUTH_DETAIL;
            case 1: case 2: break;
            default: return Auth.NO_AUTH;
        }
        if (perm == 0){
            return Auth.NO_AUTH;
        }
        ManagerDao managerDao = new ManagerDao();
        if (user.getPermission()==1){
            if (perm==1){
                if(range.equals(Util.getRangeNameByUser(user)))
                    return Auth.AUTH_DETAIL;
                else return Auth.NO_AUTH;
            }
            else{
                Map<String, Object> map = managerDao.getClassInstructorByName(range);
                if (range.equals(map.get("college_name")))
                    return Auth.AUTH_DETAIL;
                else return Auth.NO_AUTH;
            }
        }
        else{
            if (perm == 1){
                if(Util.getCollegeNameByInstructor(user).equals(range))
                    return Auth.AUTH_STAT;
                else return Auth.NO_AUTH;
            }
            else{
                if (Util.getRangeNameByUser(user).equals(range))
                    return Auth.AUTH_DETAIL;
                else{
                    Map<String, Object> map = managerDao.getClassInstructorByName(range);
                    if (Util.getCollegeNameByInstructor(user).equals(map.get("college_name")))
                        return Auth.AUTH_STAT;
                    else
                        return Auth.NO_AUTH;
                }
            }
        }
    }
}
