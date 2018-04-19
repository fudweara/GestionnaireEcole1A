package DAO;

import objetStockage.DateDeNaissance;
import objetStockage.Personne;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Classe qui regroupe les méthodes pour gérer des personnes dans la base de données
 */
public class TablePersonneDAO extends ConnectionDAO {

    /**
     * Constructeur qui permet la connection avec la base de données
     */
    public TablePersonneDAO(){
        super();
    }



    public int ajoutPersonne(Personne personne){

        ResultSet rs;
        int retour = 0;

        try{
            ps = connection.prepareStatement("INSERT INTO personne VALUES ( null, ?,?, TO_DATE(?,'YYYY-MM-DD'),?)");
            ps.setString(1, personne.getNom());
            ps.setString(2, personne.getPrenom());
            ps.setString(3, personne.getDateNaissance().getAnnee()+"-"+personne.getDateNaissance().getMois()+"-"+personne.getDateNaissance().getJour());
            ps.setString(4, personne.getFonction());

            ps.executeUpdate();
            retour=1;

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Erreur");
        }finally {
            // fermeture du preparedStatement et de la connexion
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception ignore) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception ignore) {
            }
        }
        return retour;
    }


    /**
     * Recherche dans la base de données toutes les personnes qui ont le nom envoyé en paramètre
     * @param nom (String)
     * @return ArrayList<Personne>
     */
    public ArrayList<Personne> rechercherPersonne(String nom){

        ArrayList<Personne> personne = new ArrayList<>();

        ResultSet rs;

        try {
            ps = connection.prepareStatement("SELECT * FROM personne WHERE NOM_PERSONNE LIKE ?");
            ps.setString(1, nom);

            rs = ps.executeQuery();


            String date;
            String[] separation;
            String[] separationJour;

            while (rs.next() ){

                date = rs.getString("DATENAISSANCE");

                separationJour = date.split("\\s+");
                separation = separationJour[0].split("-");

                personne.add( new Personne(rs.getInt("IDPERSONNE"), rs.getString("NOM_PERSONNE"), rs .getString("PRENOM_PERSONNE"), new DateDeNaissance( separation[2], separation[1], separation[0]) , rs.getString("FONCTION")));

                System.out.print(rs.getString("NOM_PERSONNE")+" "+ rs .getString("PRENOM_PERSONNE")+" "+ rs.getString("FONCTION")+" ");
                System.out.println( separation[2]+" "+separation[1]+" "+separation[0]+" " );


            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur");
        } finally {
            // fermeture du preparedStatement et de la connexion
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception ignore) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception ignore) {
            }
        }
        return personne;
    }

    /**
     * Remplace une personne dans la BDD par la personne mise en paramètre
     * @param personne (Personnne)
     * @return Integer
     */
    public int miseAJourPersonne(Personne personne){

        int retour = 0;

        try {
            ps = connection.prepareStatement("UPDATE PERSONNE SET NOM_PERSONNE = ?, PRENOM_PERSONNE = ?, DATENAISSANCE = TO_DATE(?,'YYYY-MM-DD'),FONCTION = ? WHERE IDPERSONNE=?");
            ps.setString(1, personne.getNom());
            ps.setString(2, personne.getPrenom());
            ps.setString(3, personne.getDateNaissance().getAnnee()+"-"+personne.getDateNaissance().getMois()+"-"+personne.getDateNaissance().getJour());
            ps.setString(4, personne.getFonction());
            ps.setInt(5, personne.getId() );

            ps.executeQuery();
            retour = 1;

            System.out.println("Modification effectuée !");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur");
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception ignore) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (Exception ignore) {
            }
        }
        return retour;

    }

}
