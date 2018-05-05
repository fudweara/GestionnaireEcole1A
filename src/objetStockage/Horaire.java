package objetStockage;

public class Horaire {
    private String heures;
    private String minutes;

    public Horaire(String heures, String minutes){
        this.heures=heures;
        this.minutes=minutes;
    }

    public String getHeures() {
        return heures;
    }

    public void setHeures(String heures) {
        this.heures = heures;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }
}
