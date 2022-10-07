//import the db package
//import DB.Database;


import java.sql.SQLException;
import java.util.Timer;

import static Helpers.GlobalHelpers.ReadInt;
//import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {
        menu();
    }

    public static void menu() throws SQLException {
        LoginPage loginAdmin = new LoginPage();
        LoginPage loginAgent = new LoginPage();
        LoginPage loginPatient = new LoginPage();
        Timer timer = new Timer(true);
        int choice = -1;
        do {
            System.out.println("---------- Main Menu ----------");
            System.out.println("1: Connect as an admin");
            System.out.println("2: Connect as a agent");
            System.out.println("3: Connect as a patient");
//            System.out.println("4: dsdsdsd");
            System.out.println("4: Quit");
            choice = ReadInt("Please choose an option : ");
            switch (choice) {
                case 1 -> loginAdmin.adminLogin();
                case 2 -> loginAgent.agentLogin();
                case 3 -> loginPatient.patientLogin();
//                case 4 -> AgentPage.newDossier();
                case 4 -> System.out.println("A bientot !");
            }
        } while (choice != 4);
        timer.cancel();
    }
}
