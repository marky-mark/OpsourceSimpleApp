package com.dimensiondata.hibernate;

import com.dimensiondata.hibernate.server.HibernateServerServiceIT;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.FileSystemResourceAccessor;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class BaseDatabaseIT {

    public static final String JDBC_H2_TARGET_SERVERS = "jdbc:h2:./target/servers";
    public static final String ORG_H2_DRIVER = "org.h2.Driver";
    private static Connection h2Connection;

    private static Liquibase liquibase;

    @BeforeClass
    public static void setupDb() throws Exception {
        Class.forName(ORG_H2_DRIVER);
        h2Connection = DriverManager.getConnection(JDBC_H2_TARGET_SERVERS);
        applySchema();
    }

    private static void applySchema() throws Exception {
        Connection connection = DriverManager.getConnection(JDBC_H2_TARGET_SERVERS);
        JdbcConnection jdbcConnection = new JdbcConnection(connection);

        Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(jdbcConnection);

        String schema = HibernateServerServiceIT.class.getClassLoader()
                .getResource("./com/dimensiondata/liquibase/changelog-master.xml").getFile();
        liquibase = new Liquibase(schema, new FileSystemResourceAccessor("."), database);
        liquibase.update((String) null);
    }

    @AfterClass
    public static void tearDownDatabase() throws Exception {
        liquibase.dropAll();
        h2Connection.close();
    }

}
