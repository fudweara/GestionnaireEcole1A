package objetStockage;


/**
 * Date de naissance qui possède une année, un mois et un jour
 */
public class DateDeNaissance {

    private final String annee;
    private final String mois;
    private final String jour;


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
     * @return Année de naissance
     */
    public String getAnnee() {
        return annee;
    }


    /**
     * Retourne le mois de naissance
     * @return Mois de naissance
     */
    public String getMois() {
        return mois;
    }


    /**
     *  Retourne le jour de naissance
     * @return Jour de naissance
     */
    public String getJour() {
        return jour;
    }

}
