package DB;

import java.sql.*;
//import java.util.Scanner;

public class Database {
    public static Statement statement;
    public static Connection connection;
    public static ResultSet resultSet;

    public static Database connection() {

        try {
            connection = (DriverManager.getConnection("jdbc:mysql://localhost:3306/cnss_jdbc", "root", "redaDaalabi2"));
            statement = connection.createStatement();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}



