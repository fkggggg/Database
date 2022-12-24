package com.database.service;

import com.database.bean.User;
import com.database.dao.ManagerDao;

import java.sql.SQLException;
import java.util.Map;

public class Util {
    /*
    * only apply to userperm == 1 or 2(means college or class administrator) */
    public static String getRangeNameByUser(User user) throws SQLException {
        ManagerDao managerDao = new ManagerDao();
        switch (user.getPermission()){
            case 0:
                return "1";
            case 1:
                Map<String, Object> college = managerDao.getCollegeAdministratorByUserId(user.getUser_id());
                return (String) college.get("college_name");
            case 2:
                Map<String, Object> class_ = managerDao.getClassInstructorByUserId(user.getUser_id());
                return (String) class_.get("class_name");

            default: return null;

        }
    }

    /*
     * only apply to userperm == 2(class administrator)
     * return the college name or null(userperm is not 2) */
    public static String getCollegeNameByInstructor(User user) throws SQLException {
        ManagerDao managerDao = new ManagerDao();
        if (user.getPermission() == 2){
            Map<String, Object> college = managerDao.getClassInstructorByUserId(user.getUser_id());
            return (String) college.get("college_name");
        }
        else return null;
    }

}
