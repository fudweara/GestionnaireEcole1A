package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TableTypeAccesDAO {

    PreparedStatement ps = null;

    public TableTypeAccesDAO() {
        ConnectionDAO.getInstance();
    }

    public boolean ajout(String typeAcces){

        boolean retour = false;

        try{
            ps = ConnectionDAO.getInstance().prepareStatement("INSERT INTO typeacces VALUES ( null, ?)");
            ps.setString(1,typeAcces);

            ps.executeUpdate();
            retour=true;

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
        }
        return retour;
    }

    public ArrayList<String> recupereToutlesTypes(){
        ArrayList<String> typesAcces = new ArrayList<>();

        ResultSet rs;
        try {
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT * FROM TYPEACCES");

            rs = ps.executeQuery();
            while (rs.next() ){
                typesAcces.add( rs.getString("NOM_TYPEACCES") );
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


        return typesAcces;
    }
}
