package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ksenia on 11.12.2016.
 */
public class MySQLdb {

    private static final String LOGIN_NAME = "Ksenia00";
    private static final String DOMAIN = "@tut.by";
    private static final String PASSWORD = "AutomationTest";
    private static Connection connection;
    private static Statement statement;
    private static List<String[]> accounts;

    public static void createMySQLdb() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts?autoReconnect=true&useSSL=false", "root", "root");
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE users(id int(3)AUTO_INCREMENT, email VARCHAR(50)NOT NULL, password VARCHAR(20)NOT NULL, PRIMARY KEY(id))");
            for (int i = 1; i < 11; i++) {
                String login = LOGIN_NAME + i + DOMAIN;
                String password = PASSWORD + i;
                statement.executeUpdate("INSERT INTO users (email, password) VALUES ('" + login + "','" + password + "')");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] readMySQLdb(int account) {
        try {
            accounts = new ArrayList<String[]>();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accounts?autoReconnect=true&useSSL=false", "root", "root");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            int i = 0;
            while (resultSet.next()) {
                accounts.add(new String[2]);
                accounts.get(i)[0] = resultSet.getString(2);
                accounts.get(i)[1] = resultSet.getString(3);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts.get(account);
    }

    public static String getLogin(int acc) {
        String[] accData = readMySQLdb(acc-1);
        return accData[0];
    }

    public static String getPassword(int acc) {
        String[] accData = readMySQLdb(acc-1);
        return accData[1];
    }
}
