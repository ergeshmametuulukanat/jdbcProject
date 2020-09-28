package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection connect() {
        Connection conn = null;
        try {
            String userName = "root";
            String password = "subanovkt";
            String url = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            System.err.println(e.getMessage());
        }
        return conn;
    }
}
