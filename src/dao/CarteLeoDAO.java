package dao;

import objetStockage.Personne;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Classe regroupant toutes les méthodes utiles pour la gestion des cartes Léo dans la base de données
 */
public class CarteLeoDAO {

    private PreparedStatement ps = null;


    /**
     * Constructeur qui fait la connection avec la base de données
     */
    public CarteLeoDAO(){ ConnectionDAO.getInstance(); }


    /**
     * Permet de savoir si une personne possède une carte Leo ou non
     *
     * @param personne Personne pour laquel on veut connaitre si elle possède une carte
     * @return Possession de la carte ( oui ou non )
     */
    public boolean connaitrePossession(Personne personne){
        boolean possession = false;

        ResultSet rs;

        try {
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT * FROM carteleo WHERE IDCARTE_PERSONNE = ?");
            ps.setInt(1, personne.getId());

            rs = ps.executeQuery();

            while( rs.next() )
                possession = true;


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
        }

        return possession;
    }


    /**
     * Ajoute une carte Leo associée à une personne dans la base de données
     *
     * @param idPersonne ID de la personne à ajouter
     * @return Retour sur l'ajout de la personne ( ok ou non )
     */
    public boolean ajouter(int idPersonne, String numeroDeBadge){

        boolean retour = false;

        try {
            ps = ConnectionDAO.getInstance().prepareStatement("INSERT INTO CARTELEO VALUES (?,?)");
            ps.setInt(1, idPersonne);
            ps.setString(2,numeroDeBadge);

            ps.executeQuery();
            retour = true;
            System.out.println("-----");
            System.out.println("Badge ajouté :  IDpersonne : "+idPersonne+" IDbadge : "+numeroDeBadge);

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
        }

        return retour;
    }


    /**
     * Supprime une carte Léo de la base de données
     *
     * @param idPersonne ID de la personne à supprimer
     * @return Retour sur la suppression de la personne ( ok ou non )
     */
    public boolean supprimer(int idPersonne){

        boolean retour = false;

        try {

            ps = ConnectionDAO.getInstance().prepareStatement("DELETE FROM carteleo WHERE idcarte_personne = ?");
            ps.setInt(1, idPersonne );

            System.out.println("-----");
            System.out.println("Carte pour la IDpersonne "+idPersonne+" supprimé");

            ps.executeUpdate();
            retour = true;

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

        }

        return retour;
    }


    /**
     * Connaitre si le numéro de badge est déjà présent dans la base de données
     *
     * @param numeroBadge Numero de badge à tester
     * @return Présente du badge dans la base de données ( oui ou non )
     */
    public boolean presenceNumeroBadge( String numeroBadge){

        boolean possession = true;
        ResultSet rs;

        try {
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT COUNT(IDCARTE_PERSONNE) FROM carteleo WHERE NUMEROBADGE = ?");
            ps.setString(1, numeroBadge);

            rs = ps.executeQuery();

            rs.next();
            System.out.println("-----");
            if( rs.getInt( "COUNT(IDCARTE_PERSONNE)") == 0 ){
                possession = false;
                System.out.println("Numéro non présent ");
            }
            else{
                System.out.println("Numéro présent");
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
        }

        return possession;
    }
}
