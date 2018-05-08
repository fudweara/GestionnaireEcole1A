package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Classe regroupant toutes les méthodes utiles pour l'identification
 */
public class IdentificationDAO {

    private PreparedStatement ps = null;


    /**
     * Constructeur de l'idnetification qui prends la connection
     */
    public IdentificationDAO(){
        ConnectionDAO.getInstance();
    }


    /**
     * Permet de récupérer le mot de passe dans la base de données grâce à l'identifiant
     *
     * @param identifiant identifiant pour lequel on veut récupérer le mot de passe
     * @return le mot de passe de l'identifiant sous la forme 'sel$hash'
     */
    public String recuperationMotDePasse(String identifiant){

        ResultSet rs;
        String motDePasse = null;

        try{
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT * FROM IDENTIFICATION WHERE IDENTIFIANT LIKE ?");
            ps.setString(1, identifiant);

            rs = ps.executeQuery();

            if(rs.next())
                motDePasse = rs.getString("MOTDEPASSE");

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
