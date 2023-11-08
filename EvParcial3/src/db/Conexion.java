package db;

import java.sql.*;

public class Conexion {
    public static Connection getConnection() {
        Connection connection = null;
        
        try {
            String bbdd_mysql = "employeedb";
            String user_mysql = "root";
            String pass_mysql = "";
            String driverClassName = "com.mysql.jdbc.Driver";
            String driverUrl="jdbc:mysql://localhost/" + bbdd_mysql;
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(driverUrl, user_mysql,pass_mysql);
            System.out.println("Conexión exitosa");
        } catch (Exception e) {
            e.getStackTrace();
        }
        return connection;
    }
}
