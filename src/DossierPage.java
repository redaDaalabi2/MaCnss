import DB.SystemControll;
import DossierPackage.Dossier;
import DossierPackage.Medicamment;
import DossierPackage.Visite;
import static Helpers.GlobalHelpers.*;
import Helpers.ConsoleBackground;
import Helpers.ConsoleForeground;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DossierPage {

    ArrayList<String> medicaments = new ArrayList<String>();
    ArrayList<String> visites = new ArrayList<String>();
    //Declaration des attributes

    public Boolean AddDossier(String matricule, ArrayList<String> medicaments,ArrayList<String> visites){
        ArrayList<Medicamment> medicamentsList = new ArrayList<>();
        ArrayList<Visite> visitesList = new ArrayList<>();
        String code = genereteMatricule();
        Dossier dossier = new Dossier(code,"En Attente","En Attente",matricule);
        for (String Visite:visites) {
            Visite visite = new Visite(genereteMatricule(),Visite,dossier.getSerie());
            visitesList.add(visite);
        }
        for (String Medicamment:medicaments) {
            Medicamment medicamment = new Medicamment(Medicamment,dossier.getSerie());
            medicamentsList.add(medicamment);
        }
        Boolean DossierCreated = dossier.CreateDossier(medicamentsList,visitesList);
        if (!DossierCreated){
//            Print("Dossier non crée", ConsoleForeground.RED);
            SystemControll systemControll = new SystemControll();
            systemControll.checkDossier(code);  //Supprimer le dossier si il n'est pas crée
        }
        return DossierCreated;
    }
    public int addDossier(){
        while(true){
            Print("Veuillez valider les données du patient", ConsoleForeground.GREEN, ConsoleBackground.BLACK);
            String message = "1: Ajouter une visite /---/ "+this.visites.size();
            if(visites.size() == 0){
                Print(message,ConsoleForeground.GREEN,ConsoleBackground.BLACK);
                }else{
                Print(message,ConsoleForeground.RED,ConsoleBackground.BLACK);
                }
            message = "2: Ajouter un medicamment /---/ "+this.medicaments.size();
            if(medicaments.size() == 0){
                Print(message,ConsoleForeground.GREEN,ConsoleBackground.BLACK);
                }else{
                Print(message,ConsoleForeground.RED,ConsoleBackground.BLACK);
                }
            Print("3: Enregistrer le dossier",ConsoleForeground.GREEN,ConsoleBackground.BLACK);
            Print("4: Exit",ConsoleForeground.RED,ConsoleBackground.BLACK);
            Scanner choice = new Scanner(System.in);
            int choixDossier = choice.nextInt();
            switch (choixDossier){
                case 1:
                    Print("Veuillez saisir le type de la visite", ConsoleForeground.GREEN, ConsoleBackground.BLACK);
                    Scanner visite = new Scanner(System.in);
                    visites.add(visite.nextLine());
//                    Print(String.valueOf(visites));
                    continue;
                case 2:
                    Print("Veuillez saisir le code du medicamment", ConsoleForeground.GREEN, ConsoleBackground.BLACK);
                    Scanner medicamment = new Scanner(System.in);
                    medicaments.add(medicamment.nextLine());
//                    Print(String.valueOf(medicaments));
                    continue;
                case 3:
                    Print("Veuillez saisir le matricule du patient", ConsoleForeground.GREEN, ConsoleBackground.BLACK);
                    Scanner scanner = new Scanner(System.in);
                    String matriculePatient = scanner.nextLine();
                    DossierPage dossierPage = new DossierPage();
                    Boolean result = dossierPage.AddDossier(matriculePatient,medicaments,visites);
                    if (!result){
                        Print("Dossier created successfully", ConsoleForeground.GREEN, ConsoleBackground.BLACK);
//                        Print(matriculePatient);
//                        Print(medicaments.toString());
//                        Print(String.valueOf(visites));
                    }else{
                        Print("Dossier creation failed", ConsoleForeground.RED, ConsoleBackground.BLACK);
                    }
                    return 1;
                    case 4:
                        return 0;
                default:
                    Print("Veuillez saisir un choix valide", ConsoleForeground.RED, ConsoleBackground.BLACK);
            }
            }
        }
// Language: java
// Path: src\DossierPackage\Dossier.java

    public static void AddDossier() throws SQLException {
        AgentPage agentPage = new AgentPage();
        agentPage.newDossier();
    }

}
