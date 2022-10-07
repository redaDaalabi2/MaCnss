package DB;

import DossierPackage.Dossier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DB.Database.connection;

public class SystemControll {
    protected static Database database = connection();

    public static Double getTotalPriceByTable(String CodeDossier, String TableCnss, String TableDonner, String id_ordonnonce, String id_ordonnonce_scan) {
        try {
            String sql =
                    "SELECT SUM(taux)" + "FROM " + TableCnss + " WHERE " + id_ordonnonce + " =(" + " SELECT " + id_ordonnonce_scan +
                            " FROM " + TableDonner + " WHERE " + " code_dossier " + " = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            statement.setString(1, CodeDossier);    //code_dossier
            ResultSet rs = statement.executeQuery();    //execute the query
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void checkDossier(String code) {
//        System.out.println("I am in checkDossier");
        Double MedicamentTotalPrice = getTotalPriceByTable(code, "medicamment", "scan_medicament", "id_medicamment", "medicament_id");
        Double VisiteTotalPrice = getTotalPriceByTable(code, "visit", "scan_visit", "doc_type", "medecinType");
        Double TotalPrice = MedicamentTotalPrice + VisiteTotalPrice;
//        System.out.println(TotalPrice);//total price
        String response = "";
        String statut = "";
        if (TotalPrice > 1) {
            response = "Accepted";
            statut = "Accepted";
        } else {
            response = "Rejected";
            statut = "Rejected";
        }
        Dossier.updateDossier(response,statut, String.valueOf(TotalPrice), code);  //update the dossier
    }
}



