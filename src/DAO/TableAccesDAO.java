package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TableAccesDAO {

    PreparedStatement ps = null;

    public TableAccesDAO(){ ConnectionDAO.getInstance(); }

    public boolean ajouterListeAcces(int idLieu, ArrayList<Integer> listeIdTypeAcces ){
        boolean retour = false;

        try {
            for(int i=0;i<listeIdTypeAcces.size();i++){
                ps = ConnectionDAO.getInstance().prepareStatement("INSERT INTO ACCES VALUES (null, ?,?)");
                ps.setInt(1, idLieu);
                ps.setInt(2, listeIdTypeAcces.get(i));

                ps.executeQuery();
            }

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

    public ArrayList<Integer> recupererListeAcces (int IDlieu){
        ArrayList<Integer> listeAcces = new ArrayList<>();

        ResultSet rs;
        try {
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT * FROM ACCES WHERE (  IDLIEU = ?)");
            ps.setInt(1, IDlieu);

            rs = ps.executeQuery();

            while( rs.next() ){
                listeAcces.add( rs.getInt("IDTYPEACCES"));
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
        return listeAcces;
    }

}
