package objetStockage;

public class DateDeNaissance {

    private String annee;
    private String mois;
    private String jour;

    /**
     * Constructeur d'une date de naissance qui prends en paramètre ses caractéristiques
     *
     * @param jour (int)
     * @param mois (int)
     * @param annee (int)
     */
    public DateDeNaissance(String jour, String mois, String annee){
        this.jour=jour;
        this.mois=mois;
        this.annee=annee;
    }


    /**
     *  Retourne l'année de naissance
     *
     * @return annnee (String)
     */
    public String getAnnee() {
        return annee;
    }


    /**
     *  Met à jour l'année de naissance
     * @param annee (String)
     */
    public void setAnnee(String annee) {
        this.annee = annee;
    }


    /**
     * Retourne le mois de naissance
     * @return mois (String)
     */
    public String getMois() {
        return mois;
    }


    /**
     * Met à jour le mois de naissance
     * @param mois (String)
     */
    public void setMois(String mois) {
        this.mois = mois;
    }


    /**
     *  Retourne le jour de naissance
     * @return jour (String)
     */
    public String getJour() {
        return jour;
    }

    /**
     * Met à jour le jour de naissance
     * @param jour (String)
     */
    public void setJour(String jour) {
        this.jour = jour;
    }



}
