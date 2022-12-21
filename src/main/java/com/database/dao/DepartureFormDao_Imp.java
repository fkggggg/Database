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
    private static final String ADD_DEPARTURE_FORM = "INSERT INTO `departure_form` VALUES (null,?, ?, ?, ?, ?, ?, ?, ?, ?, ?,0,null)";
    private static final String SELECT_MY_DEPARTURE_FORM = "SELECT * from departure_form WHERE student_id=? AND state<>-3 ORDER BY application_date DESC";

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
        preparedStatement.setString(9,departureForm.getEstimated_time().toString());
        preparedStatement.setString(10,departureForm.getReturn_date().toString());
        int result = preparedStatement.executeUpdate();
        return result > 0;
    }

    @Override
    public DepartureForm getmyDepartureForm(Student student) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MY_DEPARTURE_FORM);
        preparedStatement.setString(1,student.getStudent_id());
        ResultSet result = preparedStatement.executeQuery();
        if(result.next())
        {
            int deform_id = result.getInt("deform_id");
            DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter df2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDate application_date = LocalDate.parse(result.getString("application_date"),df1);
            String reason = result.getString("reason");
            String destination = result.getString("destination");
            LocalDate estinated_date = LocalDate.parse(result.getString("estimated_date"),df1);
            LocalTime estimated_time = LocalTime.parse(result.getString("estimated_time"),df2);
            LocalDate return_date = LocalDate.parse(result.getString("return_date"),df1);
            int state = result.getInt("state");
            String reject_reason = result.getString("reject_reason");
            return new DepartureForm(deform_id, application_date, student.getStudent_id(), student.getName(), student.getCollege_name(), student.getClass_name(),
                    reason, destination, estinated_date, estimated_time, return_date, state, reject_reason);
        }
        return new DepartureForm(-1);
    }

    @Override
    public boolean updatemyDepartureForm(DepartureForm departureReport) throws SQLException {
        return false;
    }
}
