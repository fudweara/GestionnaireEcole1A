package objetStockage;

import java.util.ArrayList;

public class Lieu {

    private int IDLieu;
    private String emplacement;
    private Horaire horaireOuverture;
    private Horaire horaireFermeture;
    private int nombreAcces;
    private ArrayList<Integer> IDlisteAcces;

    public Lieu() {

        IDlisteAcces = new ArrayList<>();

    }

    public Lieu(int IDLieu, String emplacement, Horaire horaireOuverture, Horaire horaireFermeture, int nombreAcces){
        this(emplacement,horaireOuverture, horaireFermeture, nombreAcces);
        this.IDLieu=IDLieu;
    }

    public Lieu(String emplacement, Horaire horaireOuverture, Horaire horaireFermeture, int nombreAcces) {
        this.emplacement = emplacement;
        this.horaireOuverture = horaireOuverture;
        this.horaireFermeture = horaireFermeture;
        this.nombreAcces = nombreAcces;

        IDlisteAcces = new ArrayList<>();
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

    public ArrayList<Integer> getListeAcces() {
        return IDlisteAcces;
    }

    public void ajoutAcces(int listeAcces) {
        this.IDlisteAcces.add(listeAcces);
    }

    public void supprimerAcces(int index){
        IDlisteAcces.remove(index);
    }

    public void setIDLieu(int ID){
        IDLieu=ID;
    }

    public void setIDlisteAcces(ArrayList<Integer> IDlisteAcces) {
        this.IDlisteAcces = IDlisteAcces;
    }

    public int getIDLieu(){
        return IDLieu;
    }
}
