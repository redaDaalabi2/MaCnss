package DossierPackage;

import java.sql.PreparedStatement;

import static DB.Database.connection;
import static Helpers.GlobalHelpers.Print;

public class Visite {
    //Declaration des attributes
    private static String visit_id;
    private static String medecinType;
    private static String code_dossier;

    ////function qui permet de setter toutes les attributes
    public Visite (String visit_id, String medecinType, String code_dossier) {
        this.visit_id = visit_id;
        this.medecinType = medecinType;
        this.code_dossier = code_dossier;
    }

    //Getters
    public String getVisit_id() {
        return visit_id;
    }
    public String getCode_dossier() {
        return code_dossier;
    }
    public String getMedecinType() {
        return medecinType;
    }


    //Setters
    public void setId_visite(String visit_id) {
        this.visit_id = visit_id;
    }
    public void setCode_dossier(String code_dossier) {
        this.code_dossier = code_dossier;
    }
    public void setMedecinType(String medecinType) {
        this.medecinType = medecinType;
    }


    public static Boolean AddVisite(){
        boolean result = true;
        try{
            connection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO scan_visit (visit_id, medecinType, code_dossier) VALUES (?,?,?)");
            connection.setAutoCommit(false);
            ps.setString(1, visit_id);
            ps.setString(2, medecinType);
            ps.setString(3, code_dossier);
            result = ps.execute();
            connection.commit();
//            Print("I am in visite");
            ps.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    return result;
    }
}
