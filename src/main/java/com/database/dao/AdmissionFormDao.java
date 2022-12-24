package com.database.dao;

import com.database.bean.AdmissionForm;
import com.database.bean.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AdmissionFormDao {
    boolean addAdmissionForm(AdmissionForm admissionForm) throws SQLException;
    boolean deleteAdmissionForm(int adform_id) throws SQLException;
    //这个方法仅用于获取单个学生最新的入校申请
    AdmissionForm getmyAdmissionForm(String student_id) throws SQLException;

    List<AdmissionForm> getAllAdmissionForm(String student_id) throws SQLException;
    List<AdmissionForm> getAllAdmissionFormAfter(User user, LocalDate date) throws SQLException;

    List<AdmissionForm> getAllAdmissionFormAfter(int perm, String range, LocalDate date) throws SQLException;

    /*
    * -1 for not found*/
    int getAdmissionFormNumber(String student_id)throws SQLException;
}
