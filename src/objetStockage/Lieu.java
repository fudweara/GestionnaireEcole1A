package objetStockage;

import java.util.ArrayList;

public class Lieu {

    private String emplacement;
    private Horaire horaireOuverture;
    private Horaire horaireFermeture;
    private int nombreAcces;
    private ArrayList<String> listeAcces;

    public Lieu() {

        listeAcces = new ArrayList<>();

    }

    public Lieu(String emplacement, Horaire horaireOuverture, Horaire horaireFermeture, int nombreAcces) {
        this.emplacement = emplacement;
        this.horaireOuverture = horaireOuverture;
        this.horaireFermeture = horaireFermeture;
        this.nombreAcces = nombreAcces;

        listeAcces = new ArrayList<String>();
    }

    public String getEmplacement() {
        return emplacement;
    }

    public Horaire getHoraireOuverture() {
        return horaireOuverture;
    }

    public Horaire getHoraireFermeture() {
        return horaireFermeture;
    }

    public int getNombreAcces() {
        return nombreAcces;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public void setHoraireOuverture(Horaire horaireOuverture) {
        this.horaireOuverture = horaireOuverture;
    }

    public void setHoraireFermeture(Horaire horaireFermeture) {
        this.horaireFermeture = horaireFermeture;
    }

    public void setNombreAcces(int nombreAcces) {
        this.nombreAcces = nombreAcces;
    }

    public ArrayList<String> getListeAcces() {
        return listeAcces;
    }

    public void ajoutAcces(String listeAcces) {
        this.listeAcces.add(listeAcces);
    }

    public void supprimerAcces(int index){
        listeAcces.remove(index);
    }
}
