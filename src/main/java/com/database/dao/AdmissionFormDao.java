package com.database.dao;

import com.database.bean.AdmissionForm;

import java.sql.SQLException;

public interface AdmissionFormDao {
    boolean addAdmissionForm(AdmissionForm admissionForm) throws SQLException;
    boolean deleteAdmissionForm(int adform_id) throws SQLException;
    //这个方法仅用于获取单个学生最新的入校申请
    AdmissionForm getmyAdmissionForm(String student_id) throws SQLException;
}
