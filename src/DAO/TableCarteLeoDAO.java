package DAO;

import objetStockage.Personne;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TableCarteLeoDAO {

    PreparedStatement ps = null;

    public TableCarteLeoDAO(){ ConnectionDAO.getInstance(); }

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
