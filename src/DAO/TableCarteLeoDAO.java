package DAO;

import objetStockage.Personne;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe regroupant toutes les méthodes utiles pour la gestion des cartes Léo dans la base de données
 */
public class TableCarteLeoDAO {

    PreparedStatement ps = null;

    /**
     * Constructeur qui fait la connection avec la base de données
     */
    public TableCarteLeoDAO(){ ConnectionDAO.getInstance(); }

    /**
     * Permet de savoir si une personne possède une carte Leo ou non
     * @param personne (Personne)
     * @return PossessionDuneCarte (Boolean)
     */
    public boolean connaitrePossession(Personne personne){
        boolean possession = false;

        ResultSet rs;

        try {
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT * FROM carteleo WHERE IDCARTE_PERSONNE = ?");
            ps.setInt(1, personne.getId());

            rs = ps.executeQuery();

            while( rs.next() ){
                possession = true;
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

    /**
     * Ajoute une carte Leo associée à une personne dans la base de données
     * @param idPersonne (Integer)
     * @return Verification (Boolean)
     */
    public boolean ajouter(int idPersonne){
        boolean retour=false;

        try {
            ps = ConnectionDAO.getInstance().prepareStatement("INSERT INTO CARTELEO VALUES (?,?)");
            ps.setInt(1, idPersonne);
            ps.setString(2,"TEST");

            ps.executeQuery();
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
}
