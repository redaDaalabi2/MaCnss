import DB.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import static DB.Database.resultSet;
import static DB.Database.statement;

public class Authentification {

    public static String Email;
    public static int id_agent;
    public static Boolean islogin = false;
    public static Boolean isAuthentificated(String table) throws SQLException {
        Database.connection();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your email : ");
        String email = scanner.nextLine();
        System.out.print("Enter your password : ");
        String password = scanner.nextLine();
        resultSet = statement.executeQuery("SELECT * FROM agent WHERE email = '" + email + "' AND password = '" + password + "'");
        if (resultSet.next()) {
            Email = resultSet.getString("email");
            id_agent = resultSet.getInt("id_agent");
            islogin = true;
        }
        return islogin;
    }

    public static String getInformation(ResultSet resultSet,String choice) throws SQLException {
        return resultSet.getString(choice);
    }

}