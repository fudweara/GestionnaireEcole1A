package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe regroupant toutes les méthodes utiles pour l'identification
 */
public class IdentificationDAO {

    PreparedStatement ps = null;

    /**
     * Constructeur de l'idnetification qui prends la connection
     */
    public IdentificationDAO(){
        ConnectionDAO.getInstance();
    }

    /**
     * Methode qui permet de récupérer le mot de passe dans la base de données grâce à l'idnetifiant
     * @param identifiant (Chaine de caractères)
     * @return motDePasse (Chaine de caractères)
     */
    public String recuperationMotDePasse(String identifiant){

        ResultSet rs = null;
        String motDePasse = null;

        try{
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT * FROM IDENTIFICATION WHERE IDENTIFIANT LIKE ?");
            ps.setString(1, identifiant);

            rs = ps.executeQuery();

            if(rs.next()){
                motDePasse = rs.getString("MOTDEPASSE");

            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur");
        }finally {
            // fermeture du preparedStatement et de la connexion
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception ignore) {
            }
        }
        return motDePasse;
    }
}
