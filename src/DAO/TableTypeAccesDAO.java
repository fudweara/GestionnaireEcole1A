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

    public int getID(String nomTypeAcces){

        int id=0;
        ResultSet rs;

        try {

            ps = ConnectionDAO.getInstance().prepareStatement("SELECT IDTYPEACCES FROM TYPEACCES WHERE NOM_TYPEACCES LIKE ?");
            ps.setString(1,nomTypeAcces);

            rs = ps.executeQuery();
            rs.next();

            id=rs.getInt("IDTYPEACCES");

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
        return id;
    }

    public String nomTypeEmplacement(int ID){

        String nom = "";
        ResultSet rs;

        try {

            ps = ConnectionDAO.getInstance().prepareStatement("SELECT NOM_TYPEACCES FROM TYPEACCES WHERE IDTYPEACCES = ?");
            ps.setInt(1,ID);

            rs = ps.executeQuery();
            rs.next();

            nom=rs.getString("NOM_TYPEACCES");

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

        return nom;
    }
}
