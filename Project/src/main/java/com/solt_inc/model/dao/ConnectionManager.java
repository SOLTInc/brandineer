package com.solt_inc.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static ConnectionManager instance;
    private static Connection connection;
    
    private static String URL       = "jdbc:mysql://localhost:3306/project_db";
    private static String USER      = "root";
    private static String PASSWORD = "root";
    
    private ConnectionManager() {
    }
    
    public static synchronized ConnectionManager getInstance() {
        
        if(instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        
        String driver = "com.mysql.jdbc.Driver";
        
        try {
            Class.forName(driver);
        } catch(ClassNotFoundException e) {
            System.out.println("ドライバーがありません" + e.getMessage());
        }
        try {
            if(connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            connection = null;
            throw e;
        }
        return connection;
    }
    
    public void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }
}