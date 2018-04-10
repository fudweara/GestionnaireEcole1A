package objetStockage;

public class DateDeNaissance {

    private int annee;
    private int mois;
    private int jour;

    /**
     * Constructeur d'une date de naissance qui prends en paramètre ses caractéristiques
     *
     * @param jour (int)
     * @param mois (int)
     * @param annee (int)
     */
    public DateDeNaissance(int jour, int mois, int annee){
        this.jour=jour;
        this.mois=mois;
        this.annee=annee;
    }


    /**
     *  Retourne l'année de naissance
     *
     * @return annnee (int)
     */
    public int getAnnee() {
        return annee;
    }


    /**
     *  Met à jour l'année de naissance
     * @param annee (int)
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }


    /**
     * Retourne le mois de naissance
     * @return mois (int)
     */
    public int getMois() {
        return mois;
    }


    /**
     * Met à jour le mois de naissance
     * @param mois (int)
     */
    public void setMois(int mois) {
        this.mois = mois;
    }


    /**
     *  Retourne le jour de naissance
     * @return jour (int)
     */
    public int getJour() {
        return jour;
    }

    /**
     * Met à jour le jour de naissance
     * @param jour (int)
     */
    public void setJour(int jour) {
        this.jour = jour;
    }



}
