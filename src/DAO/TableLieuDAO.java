package DAO;

import objetStockage.Horaire;
import objetStockage.Lieu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

    public boolean modifier(Lieu lieu){
        boolean retour = false;

        try {
            ps = ConnectionDAO.getInstance().prepareStatement("UPDATE LIEU SET EMPLACEMENT_LIEU = ?, HORAIRE_OUVERTURE = TO_DATE(?,'HH24-MI'), HORAIRE_FERMETURE = TO_DATE(?,'HH24-MI'),NB_ACCES = ? WHERE IDLIEU=?");
            ps.setString(1, lieu.getEmplacement());
            ps.setString(2, lieu.getHoraireOuverture().getHeures()+"-"+lieu.getHoraireOuverture().getMinutes());
            ps.setString(3, lieu.getHoraireFermeture().getHeures()+"-"+lieu.getHoraireFermeture().getMinutes() );
            ps.setInt(4, lieu.getNombreAcces());
            ps.setInt(5, lieu.getIDLieu() );

            System.out.println("----");
            System.out.println("Lieu modifié : ");
            System.out.println("Emplacement : "+ lieu.getEmplacement() );
            System.out.println("Heure ouverture : "+lieu.getHoraireOuverture().getHeures()+"-"+lieu.getHoraireOuverture().getMinutes() );
            System.out.println("Heure fermeture : "+lieu.getHoraireFermeture().getHeures()+"-"+lieu.getHoraireFermeture().getMinutes() );
            System.out.println("Nombre accès : "+lieu.getNombreAcces());
            System.out.println("ID : "+lieu.getIDLieu());
            ps.executeQuery();
            retour = true;


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur");
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception ignore) {
            }
        }

        return retour;
    }

    public int getID(Lieu lieu){
        int id=0;
        ResultSet rs;

        try {

            ps = ConnectionDAO.getInstance().prepareStatement("SELECT IDLIEU FROM LIEU WHERE EMPLACEMENT_LIEU LIKE ?");
            ps.setString(1,lieu.getEmplacement());

            rs = ps.executeQuery();
            rs.next();

            id=rs.getInt("IDLIEU");

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

    public boolean presenceDuLieu(Lieu lieu){
       boolean retour=true;
        ResultSet rs;

        try {
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT COUNT(IDLIEU) FROM LIEU WHERE EMPLACEMENT_LIEU LIKE ?");
            ps.setString(1,lieu.getEmplacement());

            rs = ps.executeQuery();
            rs.next();
            if(rs.getInt("COUNT(IDLIEU)") == 0 ){
                retour=false;
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

        return retour;
    }

    public ArrayList<Lieu> rechercherLieu(String nom){

        ArrayList<Lieu> listeLieu = new ArrayList<>();

        ResultSet rs;
        try {
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT * FROM LIEU WHERE ( ( LOWER(EMPLACEMENT_LIEU) LIKE LOWER(?) ) OR ( LOWER(EMPLACEMENT_LIEU) LIKE LOWER(?) ) OR ( LOWER(EMPLACEMENT_LIEU) LIKE LOWER(?) ) OR ( LOWER(EMPLACEMENT_LIEU) LIKE LOWER(?) ) )");
            ps.setString(1, nom);
            ps.setString(2, "%"+nom+"%");
            ps.setString(3, nom+"%");
            ps.setString(4, "%"+nom);

            rs = ps.executeQuery();

            String heure;
            String[] separationHeureOuverture;
            String[] separation1HeureFermeture;

            while( rs.next() ){

                heure = rs.getString("HORAIRE_OUVERTURE");
                separationHeureOuverture = heure.split("\\s+");
                separationHeureOuverture = separationHeureOuverture[1].split(":");

                heure = rs.getString("HORAIRE_FERMETURE");
                separation1HeureFermeture = heure.split("\\s+");
                separation1HeureFermeture = separation1HeureFermeture[1].split(":");

                listeLieu.add( new Lieu(rs.getInt("IDLIEU"), rs.getString("EMPLACEMENT_LIEU"), new Horaire(separationHeureOuverture[0],separationHeureOuverture[1]), new Horaire(separation1HeureFermeture[0],separation1HeureFermeture[1]),rs.getInt("NB_ACCES")) );
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
        return listeLieu;

    }

    public Lieu getLieu(String emplacement){

        Lieu lieu = new Lieu();
        ResultSet rs;
        try {
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT * FROM LIEU WHERE EMPLACEMENT_LIEU LIKE ?");
            ps.setString(1, emplacement);

            rs = ps.executeQuery();

            String heure;
            String[] separationHeureOuverture;
            String[] separation1HeureFermeture;

            rs.next();

            heure = rs.getString("HORAIRE_OUVERTURE");
            separationHeureOuverture = heure.split("\\s+");
            separationHeureOuverture = separationHeureOuverture[1].split(":");

            heure = rs.getString("HORAIRE_FERMETURE");
            separation1HeureFermeture = heure.split("\\s+");
            separation1HeureFermeture = separation1HeureFermeture[1].split(":");

            lieu = new Lieu(rs.getString("EMPLACEMENT_LIEU"), new Horaire(separationHeureOuverture[0],separationHeureOuverture[1]), new Horaire(separation1HeureFermeture[0],separation1HeureFermeture[1]),rs.getInt("NB_ACCES"));


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
        return lieu;
    }
}
