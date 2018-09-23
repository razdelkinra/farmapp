package ru.farmnet.app.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static DataSource dataSource;
    private ComboPooledDataSource comboPooledDataSource;
    static final String JDBC_DRIVER = "org.firebirdsql.jdbc.FBDriver";
    static final String DB_URL = "jdbc:firebirdsql:localhost/3050:";
    static final String USER = "SYSDBA";
    static final String PASSWORD = "masterkey";

    private DataSource() {
        try {
            comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setDriverClass(JDBC_DRIVER);
            comboPooledDataSource.setJdbcUrl(getURL());
            comboPooledDataSource.setUser(USER);
            comboPooledDataSource.setPassword(PASSWORD);
        } catch (PropertyVetoException ex1) {
            ex1.printStackTrace();
        }
    }

    private String getURL() {
        String abspath = new File("resources/bd/FAR.FDB").getAbsolutePath().replace("\\","/");
        String path = new File("resources/bd/FAR.FDB").getPath().replace("\\","/");
        return DB_URL + abspath.substring(0, abspath.indexOf(path)) + "farmapp/src/main/" + path;
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
