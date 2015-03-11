package com.dimensiondata;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestRawSqlConnection {

    public static final String JDBC_H2_TARGET_SERVERS = "jdbc:h2:./target/servers";
    public static final String ORG_H2_DRIVER = "org.h2.Driver";
    private static Connection h2Connection;

    @Before
    public void before() throws Exception {
        Class.forName(ORG_H2_DRIVER);
        h2Connection = DriverManager.getConnection(JDBC_H2_TARGET_SERVERS);

        String sql = IOUtils.toString(TestRawSqlConnection.class.getClassLoader()
                .getResourceAsStream("test.sql"), "UTF-8");

        Statement statement  = h2Connection.createStatement();
        statement.execute(sql);
    }

    @Test
    public void testName() throws Exception {

        Statement stmt = h2Connection.createStatement();
        stmt.executeQuery("SELECT * FROM Persons");
        ResultSet rs = stmt.getResultSet();
        rs.next();
        assertThat(rs.getInt("PersonID"), is(1));
        assertThat(rs.getString("LastName"), is("foo"));
    }

    @After
    public void tearDown() throws Exception {
        Statement stmt = h2Connection.createStatement();
        stmt.execute("drop table Persons");
        h2Connection.close();
    }
}
