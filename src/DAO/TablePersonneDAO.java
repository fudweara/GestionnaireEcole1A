package DAO;

import objetStockage.Personne;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TablePersonneDAO extends ConnectionDAO {

    public TablePersonneDAO(){
        super();
    }

    public ArrayList<Personne> rechercherPersonne(String nom){

        ArrayList<Personne> personne = new ArrayList<Personne>();

        ResultSet rs = null;

        try {
            ps = connection.prepareStatement("SELECT * FROM personne WHERE NOM_PERSONNE LIKE ?");
            ps.setString(1, nom);

            rs = ps.executeQuery();

            while (rs.next() ){

                personne.add( new Personne( rs.getString("NOM_PERSONNE"), rs .getString("PRENOM_PERSONNE"), rs.getDate("DATENAISSANCE"), rs.getString("FONCTION")));
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur aaaaa");
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

}
