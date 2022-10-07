import DB.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import static DB.Database.*;
import static Helpers.GlobalHelpers.ReadInt;
import static Helpers.GlobalHelpers.randomAlphaNumeric;

public class AdminPage {
    public static Boolean insertAgent() {
        boolean status = true;
        try {
            Database.connection();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the first name: ");
            String first_name = scanner.nextLine();
            System.out.println("Enter the last name: ");
            String last_name = scanner.nextLine();
            System.out.println("Enter the email: ");
            String email = scanner.nextLine();
            System.out.println("Enter the password: ");
            String password = scanner.nextLine();

            PreparedStatement ps = connection.prepareStatement("INSERT INTO agent (first_name,last_name,email,password) VALUES (?,?,?,?)");
            connection.setAutoCommit(false);
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, email);
            ps.setString(4, password);
            status = ps.execute();
            connection.commit();
            ps.close();
            connection.close();
            if (!status) {
                System.out.println("Agent added successfully");
            } else {
                System.out.println("Agent not added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    public static void adminMenu() {
        int choice = -1;
        do {
            System.out.println("---------- Admin Menu ----------");
            System.out.println("1: Add a new agent");
            System.out.println("2: Quit");
            choice = ReadInt("Please choose an option : ");
            switch (choice) {
                case 1 -> insertAgent();
            }
            if (choice < 1 || choice > 2) {
                System.out.println("Invalid choice");
            }
        } while (choice != 2);
    }

    public void loginAdmin() throws SQLException {
        Database.connection();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        resultSet = statement.executeQuery("SELECT email,password FROM admin WHERE email = '" + email + "'");
        if (resultSet.next()) {
            if (resultSet.getString("password").equals(password)) {
                adminMenu();
            } else {
                System.out.println("invalid password");
            }
        } else {
            System.out.println("invalid email");
        }
    }
}

