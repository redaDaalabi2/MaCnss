package DossierPackage;

import java.sql.PreparedStatement;

import static DB.Database.connection;
import static Helpers.GlobalHelpers.Print;

public class Medicamment {
    //Declaration des attributes
    private String medicament_id;
    private String code_dossier;
    private String name;
    private Double taux;

    ////function qui permet de setter toutes les attributes
    public Medicamment(String medicament_id, String code_dossier) {
        this.medicament_id = medicament_id;
        this.code_dossier = code_dossier;
    }

    //Getters
    public String getMedicament_id() {
        return medicament_id;
    }

    public void setId_medicamment(String medicament_id) {
        this.medicament_id = medicament_id;
    }

    public String getCode_dossier_id() {
        return code_dossier;
    }

    public void setCode_dossier(String code_dossier) {
        this.code_dossier = code_dossier;
    }

    //Setters

    public Boolean AddMedicamment() {

        boolean result = true;
        try {
            connection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO scan_medicament (medicament_id, code_dossier) VALUES (?,?)");
            connection.setAutoCommit(false);
            ps.setString(1, this.medicament_id);
            ps.setString(2, this.code_dossier);
            result = ps.execute();
            connection.commit();
//            Print("I am in medicament");
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
