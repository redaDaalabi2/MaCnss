
import DossierPackage.Dossier;
import Helpers.ConsoleForeground;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import static Helpers.GlobalHelpers.Print;
import java.time.LocalTime;
public class AgentPage {
    private static int tryCount = 0;
    private static int tryCountVerifyCode = 0;
    private final static String TABLE = "agent";
    public static boolean isLogin = false;
    private final static Scanner scan = new Scanner(System.in);
    //Declaration des properties
    private int id_agent;
    private String first_name;
    private String last_name;
    private String email;
    private String password;

    //menu de l'agent
    public static void menuagent() throws SQLException {
        int choice;
        do {
            Print("<1> - Ajouter un dossier");
            Print("<2> - Gerer un dossier");
            Print("<3> - retour au menu principal");
            Print("<4> - pour quitter l'application");
            Print("Choisir svp : ");
            choice = scan.nextInt();
            switch (choice) {
                case 1 -> newDossier();
                case 2 ->  {
                    AgentPage dossier = new AgentPage();
                    dossier.getAllDossiers();
                }

                case 3 -> Main.menu();
                case 4 -> {
                    System.out.println("A bientôt !!");
                    System.exit(0);
                }
                default -> System.out.println("plz choisir une autre fois");
            }
        }
        while (true);
    }

    //function qui permet de setter toutes les attributes
    public void NewAgent(int id_agent, String first_name, String last_name, String email, String password) {
        this.id_agent = id_agent;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    //Getters
    public int getId_agent() {
        return id_agent;
    }

    //Setters
    public void setId_agent(int id_agent) {
        this.id_agent = id_agent;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //function qui permet login de l'agent
    public static void loginAgent() throws SQLException {
        if (tryCount < 3) {
            try {
                Boolean resultSet = Authentification.isAuthentificated(TABLE);

                if (Authentification.islogin) {
                    //String Email = Authentification.getInformation(resultSet,"Email");
                    System.out.println("Welcome to the system");
                    System.out.println("Your Email is: "+Authentification.Email);
                    String code = generateCode();
                    String message = "Your code is: "+code;
                    String subject = "Verification code";
                    System.out.println("Check your email for the code You have 5 minutes to enter the code");
                    if (Email.sendMail(message,subject,"daalabir@gmail.com")) {
                        Boolean isCodeValid = verifyCode(code);
                        Boolean isNotExpired = checkCodeExpiration(LocalTime.now());
                        if (isNotExpired) {
                            while (tryCountVerifyCode < 2) {
                                if (isCodeValid) {
                                    System.out.println("Code is valid");
                                    isLogin = true;
                                    menuagent();
                                    break;
                                } else {
                                    System.out.println("Code is not valid");
                                    tryCountVerifyCode++;
                                    isCodeValid = verifyCode(code);
                                }
                            }
                            if (tryCountVerifyCode == 2) {
                                wait30Seconds();
                            }

                        }
                        else {
                            System.out.println("Code expired");
                            System.out.println("Do you want to resend the code? (y/n)");
                            Scanner scanner = new Scanner(System.in);
                            String choice = scanner.nextLine();
                            if (choice.equals("y")) loginAgent();
                            else {
                                System.out.println("Goodbye");
                                System.exit(0);
                            }
                        }

                    } else System.out.println("Error sending email");

                } else {
                    System.out.println("Login failed");
                    tryCount++;
                    loginAgent();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("You have exceeded the number of attempts");
            wait30Seconds();

        }

    }



    public void getAllDossiers(){
        ArrayList<Dossier> folders;
//        scan the matricule entered by the agent and get all the dossiers with the same matricule
        Print("Enter le matricule du patient : ", ConsoleForeground.CYAN);
        String matricule = scan.next();
        folders = Dossier.getAllDossiersByMatricule(matricule);
        for (int i = 0; i < folders.size(); i+=3) {
            Dossier d = folders.get(i);
            Dossier d1 = folders.get(i);
            Dossier d2 = folders.get(i);
            System.out.println((i+1) + "- Dossier code : "+ d.getMatricule() + " | response : "+ d.getResponse() +"\t" +
                    (i+2) + "- Dossier code : "+ d1.getMatricule() + " | response : "+ d1.getResponse() +"\t"+
                    (i+3) + "- Dossier code : "+ d2.getMatricule() + " | response : "+ d2.getResponse() +"\t");
        }
    }
    //nouveau dossier
    public static void newDossier() throws SQLException {
        while (true){
           Print("Make a choice: ", ConsoleForeground.CYAN);
           Print("1: Add a Dossier", ConsoleForeground.CYAN);
              Print("2: Back to the menu", ConsoleForeground.CYAN);
           Scanner scannChoice = new Scanner(System.in);
              int choice = scannChoice.nextInt();
                switch (choice){
                    case 1 -> {
                        DossierPage dossierPage = new DossierPage();
                        int dossierAdded = dossierPage.addDossier();
                        if (dossierAdded == 0){
                            continue;
                        }else{
                            Print("Dossier added successfully", ConsoleForeground.GREEN);
                            break;
                        }
                    }
                    case 2 -> menuagent();
                    default -> {
                        Print("Invalid choice", ConsoleForeground.RED);
                        Print("Please try again", ConsoleForeground.RED);

                    }
                }

            }
    }

    // generate random code to send it to the user email to verify his account and expire after 10 minutes
    public static String generateCode() {
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += (int) (Math.random() * 10);
        }
        return code;
    }


    public static boolean verifyCode(String code) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the code: ");
        String codeInput = scanner.nextLine();
        //Boolean isNotExpired = checkCodeExpiration(
        if (codeInput.equals(code)) {
            return true;
        }
        return false;
    }

    // check if the code is expired or not
    public static boolean checkCodeExpiration(LocalTime date) {
        LocalTime now = LocalTime.now();
        if (now.isBefore(date.plusMinutes(5))) {
            return true;
        }
        return false;
    }

    public static void wait30Seconds(){
        try {
            System.out.println("Wait 30 seconds");
            Thread.sleep(30000);
            tryCount = 0;
            tryCountVerifyCode = 0;
            loginAgent();
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void logout(){
        isLogin = false;
    }
}

