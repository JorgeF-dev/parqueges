package com.sanvalero.parqueges.dao;
/**
 *
 * @author Jorge Fernandez <jorgefuli91@gmail.com>
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //declare instance variables
    //para mysql
    //private final String URL_CONEXION = "jdbc:mysql://localhost:3306/taller";
    //private final String URL_CONEXION = "jdbc:oracle:thin@localhost:1521:taller";
    private final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final String URL_CONNECTION = "jdbc:oracle:thin:@//localhost:1521/XE";
    private final String USER = "AAPARQUE";
    private final String PASSWORD = "1234";

    private Connection connection;

    public Conexion() {

    }

    public Connection getConexion() {
        return connection;
    }

    public void connect() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException cnfe) {
            cnfe.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

}
