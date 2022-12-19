package com.database.test;

import com.database.jdbc.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtilTest {
    @Test
    public void main() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
    }
}
