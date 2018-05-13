package dao;

import objetStockage.Horaire;
import objetStockage.Lieu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static dao.ConnectionDAO.getInstance;


/***
 * Classe pour les lieux dans la base de données
 */
public class LieuDAO {

    private PreparedStatement ps = null;

    /**
     * Constructeur qui fait la connection avec la base de données
     */
    public LieuDAO(){
        getInstance();
    }


    /**
     * Ajoute un lieu dans la base de données
     *
     * @param lieu Lieu que l'on veut ajouter
     * @return Vérification sur l'ajout du lieu dans la base de donnée ( ok ou non )
     */
    public boolean ajouter(Lieu lieu){

        boolean retour = false;

        try {
            ps = getInstance().prepareStatement("INSERT INTO LIEU VALUES (null,?,TO_DATE(?,'HH24-MI'),TO_DATE(?,'HH24-MI'),?)");
            ps.setString(1, lieu.getEmplacement());
            ps.setString(2,lieu.getHoraireOuverture().getHeures()+"-"+lieu.getHoraireOuverture().getMinutes() );
            ps.setString(3,lieu.getHoraireFermeture().getHeures()+"-"+lieu.getHoraireFermeture().getMinutes() );
            ps.setInt(4,lieu.getNombreAcces() );

            ps.executeQuery();
            retour = true;
            System.out.println("-----");
            System.out.println("Ajout du lieu : "+lieu.getEmplacement()+" "+lieu.getHoraireOuverture().getHeures()+"-"+lieu.getHoraireOuverture().getMinutes()+" "+lieu.getHoraireFermeture().getHeures()+"-"+lieu.getHoraireFermeture().getMinutes()+" "+lieu.getNombreAcces());

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


    /**
     * Modifie le lieu en paramètre dans la base de données
     * @param lieu Lieu qui veut être modifié
     * @return Vérification sur la modification du lieu ( ok ou non )
     */
    public boolean modifier(Lieu lieu){
        boolean retour = false;

        try {
            ps = getInstance().prepareStatement("UPDATE LIEU SET EMPLACEMENT_LIEU = ?, HORAIRE_OUVERTURE = TO_DATE(?,'HH24-MI'), HORAIRE_FERMETURE = TO_DATE(?,'HH24-MI'),NB_ACCES = ? WHERE IDLIEU=?");
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


    /**
     * Retourne l'id pour un lieu dont on connait l'emplacement
     *
     * @param lieu Lieu dont on veut récupéré l'ID, nécéssite attribut 'emplacement' non null
     * @return ID du lieu
     */
    public int getID(Lieu lieu){

        int id=0;
        ResultSet rs;

        try {

            ps = getInstance().prepareStatement("SELECT IDLIEU FROM LIEU WHERE EMPLACEMENT_LIEU LIKE ?");
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


    /**
     *Vérifie la présente d'un lieu dans la base de données
     *
     * @param lieu Lieu que l'on veut vérifier
     * @return Présence du lieu dans la base de données ( oui ou non )
     */
    public boolean presenceDuLieu(Lieu lieu){

       boolean retour=true;
       ResultSet rs;

        try {
            ps = getInstance().prepareStatement("SELECT COUNT(IDLIEU) FROM LIEU WHERE EMPLACEMENT_LIEU LIKE ?");
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


    /**
     * Recherche les lieux qui ont la chaine de caractères dans leur d'emplacement
     *
     * @param nom Nom utilisé pour la recherche
     * @return Liste de lieu qui correspondent à la recherche
     */
    public ArrayList<Lieu> rechercherLieu(String nom){

        ArrayList<Lieu> listeLieu = new ArrayList<>();
        ResultSet rs;

        try {
            ps = getInstance().prepareStatement("SELECT * FROM LIEU WHERE ( ( LOWER(EMPLACEMENT_LIEU) LIKE LOWER(?) ) OR ( LOWER(EMPLACEMENT_LIEU) LIKE LOWER(?) ) OR ( LOWER(EMPLACEMENT_LIEU) LIKE LOWER(?) ) OR ( LOWER(EMPLACEMENT_LIEU) LIKE LOWER(?) ) )");
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


    /**
     * Récupére le lieu pour lequel on connait l'emplacement
     *
     * @param emplacement Nom de l'emplacement qui va être utilisé pour la recherche
     * @return Lieu qui possède le nom d'emplacement demandé
     */
    public Lieu getLieu(String emplacement){

        Lieu lieu = new Lieu();
        ResultSet rs;

        try {
            ps = getInstance().prepareStatement("SELECT * FROM LIEU WHERE EMPLACEMENT_LIEU LIKE ?");
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


    /**
     * Retorune le lieu correspondant à l'id donné
     *
     * @param id ID du lieu
     * @return Lieu
     */
    public Lieu getLieu(int id){
        Lieu lieu = new Lieu();
        ResultSet rs;

        try {
            ps = getInstance().prepareStatement("SELECT * FROM LIEU WHERE IDLIEU = ?");
            ps.setInt(1, id);

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
