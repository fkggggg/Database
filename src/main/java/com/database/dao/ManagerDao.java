package com.database.dao;

import com.database.jdbc.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerDao {
    Connection connection = JDBCUtils.getConnection();

    public ManagerDao() throws SQLException {
    }


    /**
     * return null if can't find;
     * else return map with attrs and values
     * */
    public Map<String, Object> getCollegeAdministratorByUserId(int userid) throws SQLException {
        String sql = "SELECT * FROM database.college_administrator WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userid);
        ResultSet result = preparedStatement.executeQuery();

        Map<String, Object> map = new HashMap<>();
        if (result.next()){
            map.put("user_id", result.getInt("user_id"));
            map.put("teacher_id", result.getString("teacher_id"));
            map.put("name", result.getString("name"));
            map.put("college_name", result.getString("college_name"));
            return map;
        }
        else
            return null;
    }

    public Map<String, Object> getCollegeAdministratorByName(String rangeName) throws SQLException {
        String sql = "SELECT * FROM database.college_administrator WHERE college_name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, rangeName);
        ResultSet result = preparedStatement.executeQuery();

        Map<String, Object> map = new HashMap<>();
        if (result.next()){
            map.put("user_id", result.getInt("user_id"));
            map.put("teacher_id", result.getString("teacher_id"));
            map.put("name", result.getString("name"));
            map.put("college_name", result.getString("college_name"));
            return map;
        }
        else
            return null;
    }

    /**
     * return null if can't find;
     * else return map with attrs and values
     * */
    public Map<String, Object> getClassInstructorByUserId(int userid) throws SQLException {
        String sql = "SELECT * FROM database.class_instructor WHERE user_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, userid);
        ResultSet result = preparedStatement.executeQuery();

        Map<String, Object> map = new HashMap<>();
        if (result.next()){
            map.put("user_id", result.getInt("user_id"));
            map.put("teacher_id", result.getString("teacher_id"));
            map.put("name", result.getString("name"));
            map.put("college_name", result.getString("college_name"));
            map.put("class_name", result.getString("class_name"));
            return map;
        }
        else
            return null;
    }
    public Map<String, Object> getClassInstructorByName(String Name) throws SQLException {
        String sql = "SELECT * FROM database.class_instructor WHERE class_name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, Name);
        ResultSet result = preparedStatement.executeQuery();

        Map<String, Object> map = new HashMap<>();
        if (result.next()){
            map.put("user_id", result.getInt("user_id"));
            map.put("teacher_id", result.getString("teacher_id"));
            map.put("name", result.getString("name"));
            map.put("college_name", result.getString("college_name"));
            map.put("class_name", result.getString("class_name"));
            return map;
        }
        else
            return null;
    }

    public List<String> getAllCollegeNames() throws SQLException {

        String sql = "SELECT * FROM database.college_administrator";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet result = preparedStatement.executeQuery();

        List<String> list = new ArrayList<>();
        while (result.next()){
            list.add( result.getString("college_name"));
        }
        return  list;
    }
}
