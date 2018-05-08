package objetStockage;

import java.util.ArrayList;


/**
 * Lieu qui possède un emplacement, un ID, des horaires d'ouverture/fermeture, un nombre d'accès et un liste d'accès
 */
public class Lieu {

    private int IDLieu;
    private String emplacement;
    private Horaire horaireOuverture;
    private Horaire horaireFermeture;
    private int nombreAcces;
    private ArrayList<Integer> IDlisteAcces;


    /**
     * Constructeur d'un lieu sans valeur
     */
    public Lieu() {

        IDlisteAcces = new ArrayList<>();

    }


    /**
     * Constructeur d'un lieu dont on connait toutes les caractéristiques
     *
     * @param IDLieu ID du lieu
     * @param emplacement Emplacement du lieu
     * @param horaireOuverture Horaire d'ouverture du lieu
     * @param horaireFermeture Horaire de fermeture du lieu
     * @param nombreAcces Nombre d'accès du lieu
     */
    public Lieu(int IDLieu, String emplacement, Horaire horaireOuverture, Horaire horaireFermeture, int nombreAcces){
        this(emplacement,horaireOuverture, horaireFermeture, nombreAcces);
        this.IDLieu=IDLieu;
    }


    /**
     * Constructeur d'un lieu dont on connait toutes les caractéristiques sauf son ID
     *
     * @param emplacement Emplacement du lieu
     * @param horaireOuverture Horaire d'ouverture du lieu
     * @param horaireFermeture Horaire de fermeture du lieu
     * @param nombreAcces Nombre d'accès du lieu
     */
    public Lieu(String emplacement, Horaire horaireOuverture, Horaire horaireFermeture, int nombreAcces) {
        this.emplacement = emplacement;
        this.horaireOuverture = horaireOuverture;
        this.horaireFermeture = horaireFermeture;
        this.nombreAcces = nombreAcces;

        IDlisteAcces = new ArrayList<>();
    }


    /**
     * Retourne l'emplacement du lieu
     *
     * @return Emplacement
     */
    public String getEmplacement() {
        return emplacement;
    }


    /**
     * Retourne l'horaire d'ouverture du lieu
     *
     * @return Horaire d'ouverture
     */
    public Horaire getHoraireOuverture() {
        return horaireOuverture;
    }


    /**
     * Retourne l'Horaire de fermeture du lieu
     *
     * @return Horaire de fermeture
     */
    public Horaire getHoraireFermeture() {
        return horaireFermeture;
    }


    /**
     * Retourne nombre d'accès du lieu
     *
     * @return Nombre d'accès
     */
    public int getNombreAcces() {
        return nombreAcces;
    }


    /**
     * Met un nom d'emplacement donné en paramètre au lieu
     *
     * @param emplacement Nom emplacement voulu
     */
    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }


    /**
     * Met un horaire d'ouverture donné en paramètre au lieu
     *
     * @param horaireOuverture Horaire d'ouverture voulu
     */
    public void setHoraireOuverture(Horaire horaireOuverture) {
        this.horaireOuverture = horaireOuverture;
    }


    /**
     * Met un horaire de fermeture donné en paramètre au lieu
     *
     * @param horaireFermeture Horaire de fermeture voulu
     */
    public void setHoraireFermeture(Horaire horaireFermeture) {
        this.horaireFermeture = horaireFermeture;
    }


    /**
     * Met un nombre d'accès donné en paramètre au lieu
     *
     * @param nombreAcces Nombre d'accès voulu
     */
    public void setNombreAcces(int nombreAcces) {
        this.nombreAcces = nombreAcces;
    }


    /**
     * Retourne la liste des accès du lieu
     *
     * @return Liste des accès
     */
    public ArrayList<Integer> getListeAcces() {
        return IDlisteAcces;
    }


    /**
     * Ajoute un accès au lieu
     *
     * @param listeAcces Accès à ajouter
     */
    public void ajoutAcces(int listeAcces) {
        this.IDlisteAcces.add(listeAcces);
    }


    /**
     * Supprime un accès au lieu
     *
     * @param index Index du lieu à supprimer
     */
    public void supprimerAcces(int index){
        IDlisteAcces.remove(index);
    }


    /**
     * Met un ID au lieu
     *
     * @param ID ID à mettre
     */
    public void setIDLieu(int ID){
        IDLieu=ID;
    }


    /**
     *  Défini la liste des accès du lieu
     *
     * @param IDlisteAcces Liste des Accès
     */
    public void setIDlisteAcces(ArrayList<Integer> IDlisteAcces) {
        this.IDlisteAcces = IDlisteAcces;
    }

    /**
     * Retourne l'ID du lieu
     *
     * @return ID
     */
    public int getIDLieu(){
        return IDLieu;
    }
}
