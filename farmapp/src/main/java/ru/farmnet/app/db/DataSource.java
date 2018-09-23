package ru.farmnet.app.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static DataSource dataSource;
    private ComboPooledDataSource comboPooledDataSource;
    static final String JDBC_DRIVER = "org.firebirdsql.jdbc.FBDriver";
    static final String DB_URL = "jdbc:firebirdsql:localhost/3050:C:/firebird/FAR.FDB";
    static final String USER = "SYSDBA";
    static final String PASSWORD = "masterkey";

    private DataSource() {
        try {
            comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setDriverClass(JDBC_DRIVER);
            comboPooledDataSource.setJdbcUrl(DB_URL);
            comboPooledDataSource.setUser(USER);
            comboPooledDataSource.setPassword(PASSWORD);
        } catch (PropertyVetoException ex1) {
            ex1.printStackTrace();
        }
    }

    public static DataSource getInstance() {
        if (dataSource == null)
            dataSource = new DataSource();
        return dataSource;
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
