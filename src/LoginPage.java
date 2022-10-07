import DB.Database;

import java.sql.SQLException;
import java.util.Scanner;

import static DB.Database.statement;
import static DB.Database.resultSet;

public class LoginPage {
    public void adminLogin() throws SQLException {
        AdminPage adminPage = new AdminPage();
        adminPage.loginAdmin();

    }
    public void agentLogin()  throws SQLException {
        AgentPage agentPage = new AgentPage();
        agentPage.loginAgent();
    }

    public void patientLogin() throws SQLException {
        PatientPage patientPage = new PatientPage();
        patientPage.loginPatient();
    }
}
