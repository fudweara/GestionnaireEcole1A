package objetStockage;


/**
 * Personne qui possède un ID, un nom, un prénom, une date de naissance et une fonction
 */
public class Personne {

    private final int id;
    private final String nom;
    private final String prenom;
    private final DateDeNaissance dateNaissance;
    private final String fonction;


    /**
     * Constructeur de Personne qui prends pour paramètre toutes ses caractéristiques
     *
     * @param nom Nom de la personne
     * @param prenom Prénom de personne
     * @param dateDeNaissance Date de naissance de la personne
     * @param fonction Fonction de la personne
     */
    public Personne(int id,String nom, String prenom, DateDeNaissance dateDeNaissance, String fonction){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.dateNaissance=dateDeNaissance;
        this.fonction=fonction;
    }


    /**
     * Retourne le nom de la Personne
     *
     * @return Nom
     */
    public String getNom(){
        return nom;
    }


    /**
     *  Retourne le prénom de la personne
     *
     * @return Prenom
     */
    public String getPrenom(){
        return prenom;
    }


    /**
     * Retourne la date de naissance de la personne
     *
     * @return Date de naissance
     */
    public DateDeNaissance getDateNaissance(){
        return dateNaissance;
    }


    /**
     * Retourne la date de naissance de la personne
     *
     * @return Fonction
     */
    public String getFonction(){
        return fonction;
    }


    /**
     * Retourne l'ID de la personne
     * @return ID
     */
    public int getId() {
        return id;
    }
}
