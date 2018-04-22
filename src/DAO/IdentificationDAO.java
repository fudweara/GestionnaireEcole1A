package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IdentificationDAO {

    PreparedStatement ps = null;

    public IdentificationDAO(){
        ConnectionDAO.getInstance();
    }

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
