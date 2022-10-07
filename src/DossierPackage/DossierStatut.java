package DossierPackage;

public class DossierStatut {
    protected String series;
    protected String statut;
    protected String response;
    protected String matricule;

    public DossierStatut(String series, String statut, String response, String matricule) {
        this.series = series;
        this.statut = statut;
        this.response = response;
        this.matricule = matricule;
    }
}
