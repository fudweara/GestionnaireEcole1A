package objetStockage;

public class Personne {

    private String nom;
    private String prenom;
    private DateDeNaissance dateNaissance;
    private String fonction;

    /**
     * Constructeur de Personne qui prends pour paramètre toutes ses caractéristiques
     *
     * @param nom (String)
     * @param prenom (String)
     * @param dateDeNaissance (DateDeNaissance)
     * @param fonction (String)
     */
    public Personne(String nom, String prenom, DateDeNaissance dateDeNaissance, String fonction){
        this.nom=nom;
        this.prenom=prenom;
        this.dateNaissance=dateDeNaissance;
        this.fonction=fonction;
    }


    /**
     * Retourne le nom de la Personne
     *
     * @return Nom (String)
     */
    public String getNom(){
        return nom;
    }


    /**
     *  Retourne le prénom de la personne
     *
     * @return Prenom (String)
     */
    public String getPrenom(){
        return prenom;
    }


    /**
     * Retourne la date de naissance de la personne
     *
     * @return Date de naissance (DateDeNaissance)
     */
    public DateDeNaissance getDateNaissance(){
        return dateNaissance;
    }


    /**
     * Retourne la date de naissance de la personne
     *
     * @return Fonction (String)
     */
    public String getFonction(){
        return fonction;
    }


    /**
     * Met à jour la date de naissancce de la personne
     *
     * @param dateNaissance (DateDeNaissance)
     */
    public void setDateNaissance(DateDeNaissance dateNaissance) {
        this.dateNaissance = dateNaissance;
    }


    /**
     * Met  à jour la fonction de la personne
     *
     * @param fonction (String)
     */
    public void setFonction(String fonction) {
        this.fonction = fonction;
    }


    /**
     * Met à jour le nom de la personne
     *
     * @param nom (String)
     */
    public void setNom(String nom) {
        this.nom = nom;
    }


    /**
     * Met à jour le prenom de la personne
     *
     * @param prenom (String)
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


}