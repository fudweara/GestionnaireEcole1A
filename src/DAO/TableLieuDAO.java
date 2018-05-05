package DAO;

import objetStockage.Lieu;

import java.sql.PreparedStatement;

public class TableLieuDAO {

    PreparedStatement ps = null;

    public TableLieuDAO(){
        ConnectionDAO.getInstance();
    }

    public boolean ajouter(Lieu lieu){
        boolean retour = false;

        try {
            ps = ConnectionDAO.getInstance().prepareStatement("INSERT INTO LIEU VALUES (null,?,TO_DATE(?,'HH24-MI'),TO_DATE(?,'HH24-MI'),?)");
            ps.setString(1, lieu.getEmplacement());
            ps.setString(2,lieu.getHoraireOuverture().getHeures()+"-"+lieu.getHoraireOuverture().getMinutes() );
            ps.setString(3,lieu.getHoraireFermeture().getHeures()+"-"+lieu.getHoraireFermeture().getMinutes() );
            ps.setInt(4,lieu.getNombreAcces() );

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
