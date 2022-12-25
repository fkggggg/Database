package com.database.dao;

import com.database.bean.AdmissionForm;
import com.database.bean.User;
import com.database.jdbc.JDBCUtils;
import com.database.service.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdmissionFormDao_Imp implements AdmissionFormDao{
    private static final String ADD_ADMISSION_FORM = "INSERT INTO `admission_form` VALUES (null,?, ?, ?, ?, ?, ?, ?, 0,null)";
    private static final String SELECT_MY_ADMISSION_FORM = "SELECT * from admission_form WHERE student_id=? AND state<>-3 AND estimated_date>=CURRENT_DATE";
    private static final String DELETE_ADMISSION_FORM = "DELETE from `admission_form` WHERE adform_id=?";

    Connection connection = JDBCUtils.getConnection();

    public AdmissionFormDao_Imp() throws SQLException {
    }


    @Override
    public boolean addAdmissionForm(AdmissionForm admissionForm) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_ADMISSION_FORM);
        preparedStatement.setString(1,admissionForm.getApplication_date().toString());
        preparedStatement.setString(2,admissionForm.getStudent_id());
        preparedStatement.setString(3,admissionForm.getName());
        preparedStatement.setString(4,admissionForm.getCollege_name());
        preparedStatement.setString(5,admissionForm.getClass_name());
        preparedStatement.setString(6,admissionForm.getReason());
        preparedStatement.setString(7,admissionForm.getEstimated_date().toString());
        int result = preparedStatement.executeUpdate();
        return result > 0;
    }

    @Override
    public boolean deleteAdmissionForm(int adform_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMISSION_FORM);
        preparedStatement.setString(1,String.valueOf(adform_id));
        int result = preparedStatement.executeUpdate();
        return result > 0;
    }

    public boolean updateAdmissionFormState(int adform_id, int newState, String reason) throws SQLException {
        String sql = "UPDATE database.admission_form SET `state`=?, reject_reason=? WHERE adform_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, newState);
        preparedStatement.setString(2,reason);
        preparedStatement.setInt(3,adform_id);
        int result = preparedStatement.executeUpdate();
        return result > 0;
    }

    public AdmissionForm getAdmissionFormById(int adform_id) throws SQLException{
        String sql = "SELECT * from database.admission_form where adform_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,adform_id);
        ResultSet result = preparedStatement.executeQuery();

        if(result.next())
        {
            String student_id = result.getString("student_id");
            int deform_id = result.getInt("adform_id");
            DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate application_date = LocalDate.parse(result.getString("application_date"),df1);
            String name = result.getString("name");
            String college_name = result.getString("college_name");
            String class_name = result.getString("class_name");
            String reason = result.getString("reason");
            LocalDate estinated_date = LocalDate.parse(result.getString("estimated_date"),df1);
            int state = result.getInt("state");
            String reject_reason = result.getString("reject_reason");
            return new AdmissionForm(deform_id, application_date, student_id, name, college_name, class_name,
                    reason, estinated_date, state, reject_reason);
        }
        return new AdmissionForm(-1);
    }

    @Override
    public AdmissionForm getmyAdmissionForm(String student_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MY_ADMISSION_FORM);
        preparedStatement.setString(1,student_id);
        ResultSet result = preparedStatement.executeQuery();
        if(result.next())
        {
            int deform_id = result.getInt("adform_id");
            DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate application_date = LocalDate.parse(result.getString("application_date"),df1);
            String name = result.getString("name");
            String college_name = result.getString("college_name");
            String class_name = result.getString("class_name");
            String reason = result.getString("reason");
            LocalDate estinated_date = LocalDate.parse(result.getString("estimated_date"),df1);
            int state = result.getInt("state");
            String reject_reason = result.getString("reject_reason");
            return new AdmissionForm(deform_id, application_date, student_id, name, college_name, class_name,
                    reason, estinated_date, state, reject_reason);
        }
        return new AdmissionForm(-1);
    }

    @Override
    public List<AdmissionForm> getAllAdmissionForm(String student_id) throws SQLException {
        String sql = "SELECT * from database.admission_form WHERE student_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,student_id);
        ResultSet result = preparedStatement.executeQuery();
        List<AdmissionForm> admissionFormList = new ArrayList<>();
        while(result.next())
        {
            int deform_id = result.getInt("adform_id");
            DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate application_date = LocalDate.parse(result.getString("application_date"),df1);
            String name = result.getString("name");
            String college_name = result.getString("college_name");
            String class_name = result.getString("class_name");
            String reason = result.getString("reason");
            LocalDate estinated_date = LocalDate.parse(result.getString("estimated_date"),df1);
            int state = result.getInt("state");
            String reject_reason = result.getString("reject_reason");
            admissionFormList.add( new AdmissionForm(deform_id, application_date, student_id, name, college_name, class_name,
                    reason, estinated_date, state, reject_reason));
        }
        return admissionFormList;
    }

    @Override
    public List<AdmissionForm> getAllAdmissionFormAfter(User user, LocalDate date) throws SQLException {
        String sql;
        PreparedStatement preparedStatement;
        String range;
        switch (user.getPermission()){
            case 0:
                sql = "SELECT * from admission_form ORDER BY application_date DESC";
                 preparedStatement = connection.prepareStatement(sql);
                break;
            case 1:
                range = Util.getRangeNameByUser(user);
                sql = "SELECT * from admission_form WHERE college_name=? ORDER BY application_date DESC";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, range);
                break;
            case 2:
                range = Util.getRangeNameByUser(user);
                sql = "SELECT * from admission_form WHERE class_name=? ORDER BY application_date DESC";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, range);
                break;
            default:return null;
        }

        ResultSet result = preparedStatement.executeQuery();
        List<AdmissionForm> admissionFormList = new ArrayList<>();

        // localdate 转 date
        Date date1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        while(result.next())
        {
            if (result.getDate("application_date").before(date1) )
                break;
            int deform_id = result.getInt("adform_id");
            DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate application_date = LocalDate.parse(result.getString("application_date"),df1);
            String student_id = result.getString("student_id");
            String name = result.getString("name");
            String college_name = result.getString("college_name");
            String class_name = result.getString("class_name");
            String reason = result.getString("reason");
            LocalDate estinated_date = LocalDate.parse(result.getString("estimated_date"),df1);
            int state = result.getInt("state");
            String reject_reason = result.getString("reject_reason");
            admissionFormList.add( new AdmissionForm(deform_id, application_date, student_id, name, college_name, class_name,
                    reason, estinated_date, state, reject_reason));
        }
        return admissionFormList;
    }

    @Override
    public List<AdmissionForm> getAllAdmissionFormAfter(int perm, String range, LocalDate date) throws SQLException {
        String sql;
        PreparedStatement preparedStatement;
        switch (perm){
            case 0:
                sql = "SELECT * from admission_form ORDER BY application_date DESC";
                preparedStatement = connection.prepareStatement(sql);
                break;
            case 1:
                sql = "SELECT * from admission_form WHERE college_name=? ORDER BY application_date DESC";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, range);
                break;
            case 2:
                sql = "SELECT * from admission_form WHERE class_name=? ORDER BY application_date DESC";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, range);
                break;
            default:return null;
        }

        ResultSet result = preparedStatement.executeQuery();
        List<AdmissionForm> admissionFormList = new ArrayList<>();

        // localdate 转 date
        Date date1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        while(result.next())
        {
            if (result.getDate("application_date").before(date1) )
                break;
            int deform_id = result.getInt("adform_id");
            DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate application_date = LocalDate.parse(result.getString("application_date"),df1);
            String student_id = result.getString("student_id");
            String name = result.getString("name");
            String college_name = result.getString("college_name");
            String class_name = result.getString("class_name");
            String reason = result.getString("reason");
            LocalDate estinated_date = LocalDate.parse(result.getString("estimated_date"),df1);
            int state = result.getInt("state");
            String reject_reason = result.getString("reject_reason");
            admissionFormList.add( new AdmissionForm(deform_id, application_date, student_id, name, college_name, class_name,
                    reason, estinated_date, state, reject_reason));
        }
        return admissionFormList;
    }

    @Override
    public int getAdmissionFormNumber(String student_id) throws SQLException {
        String sql = "SELECT count(*) from admission_form WHERE student_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,student_id);
        ResultSet result = preparedStatement.executeQuery();
        if(result.next())
        {
            return result.getInt(0);
        }
        else return  -1;

    }

}
