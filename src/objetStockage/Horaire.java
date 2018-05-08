package objetStockage;


/**
 * Horaire qui possède une heure et des minutes
 */
public class Horaire {

    private final String heures;
    private final String minutes;


    /**
     * Constructeur d'une heure
     *
     * @param heures Heure de l'horaire
     * @param minutes Minutes de l'horaire
     */
    public Horaire(String heures, String minutes){
        this.heures=heures;
        this.minutes=minutes;
    }


    /**
     * Retourne l'heure de l'horaire
     *
     * @return Heure
     */
    public String getHeures() {
        return heures;
    }


    /**
     * Retourne les minutes de l'horaire
     *
     * @return Minutes
     */
    public String getMinutes() {
        return minutes;
    }

}
