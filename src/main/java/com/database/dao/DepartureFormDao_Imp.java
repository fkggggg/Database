package com.database.dao;

import com.database.bean.DailyReport;
import com.database.bean.DepartureForm;
import com.database.bean.Student;
import com.database.jdbc.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DepartureFormDao_Imp implements DepartureFormDao {
    private static final String ADD_DEPARTURE_FORM = "INSERT INTO `departure_form` VALUES (null,?, ?, ?, ?, ?, ?, ?, ?, ?,0,null)";
    private static final String SELECT_MY_DEPARTURE_FORM = "SELECT * from departure_form WHERE student_id=? AND state<>-3 AND estimated_date>=CURRENT_DATE";
    private static final String DELETE_DEPARTURE_FORM = "DELETE from `departure_form` WHERE deform_id=?";

    Connection connection = JDBCUtils.getConnection();

    public DepartureFormDao_Imp() throws SQLException {
    }

    @Override
    public boolean addDepartureForm(DepartureForm departureForm) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_DEPARTURE_FORM);
        preparedStatement.setString(1,departureForm.getApplication_date().toString());
        preparedStatement.setString(2,departureForm.getStudent_id());
        preparedStatement.setString(3,departureForm.getName());
        preparedStatement.setString(4,departureForm.getCollege_name());
        preparedStatement.setString(5,departureForm.getClass_name());
        preparedStatement.setString(6,departureForm.getReason());
        preparedStatement.setString(7,departureForm.getDestination());
        preparedStatement.setString(8,departureForm.getEstimated_date().toString());
        preparedStatement.setString(9,departureForm.getReturn_date().toString());
        int result = preparedStatement.executeUpdate();
        return result > 0;
    }

    @Override
    public boolean deleteDepartureForm(int deform_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEPARTURE_FORM);
        preparedStatement.setString(1,String.valueOf(deform_id));
        int result = preparedStatement.executeUpdate();
        return result > 0;
    }

    @Override
    public DepartureForm getmyDepartureForm(String student_id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MY_DEPARTURE_FORM);
        preparedStatement.setString(1,student_id);
        ResultSet result = preparedStatement.executeQuery();
        if(result.next())
        {
            int deform_id = result.getInt("deform_id");
            DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate application_date = LocalDate.parse(result.getString("application_date"),df1);
            String name = result.getString("name");
            String college_name = result.getString("college_name");
            String class_name = result.getString("class_name");
            String reason = result.getString("reason");
            String destination = result.getString("destination");
            LocalDate estinated_date = LocalDate.parse(result.getString("estimated_date"),df1);
            LocalDate return_date = LocalDate.parse(result.getString("return_date"),df1);
            int state = result.getInt("state");
            String reject_reason = result.getString("reject_reason");
            return new DepartureForm(deform_id, application_date, student_id, name, college_name, class_name,
                    reason, destination, estinated_date, return_date, state, reject_reason);
        }
        return new DepartureForm(-1);
    }

}
