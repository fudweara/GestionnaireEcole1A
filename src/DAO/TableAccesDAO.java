package DAO;

import objetStockage.Acces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 * Regroupe les méthodes pour les Accès dans la DAO
 */
public class TableAccesDAO {

    private PreparedStatement ps = null;


    /**
     * Constructeur qui fait la connection avec la base de données
     */
    public TableAccesDAO(){ ConnectionDAO.getInstance(); }


    /**
     * Ajoute une liste d'accès dans la base de données
     *
     * @param idLieu ID du lieu à ajouter
     * @param listeIdTypeAcces Liste des accès du lieu à ajouter
     * @return Etat de l'ajout dans la base de données ( Reussi ou non )
     */
    public boolean ajouter(int idLieu, ArrayList<Integer> listeIdTypeAcces ){

        boolean retour = false;

        try {
            System.out.println("-----");
            for (Integer listeIdTypeAcce : listeIdTypeAcces) {
                ps = ConnectionDAO.getInstance().prepareStatement("INSERT INTO ACCES VALUES (null, ?,?)");
                ps.setInt(1, idLieu);
                ps.setInt(2, listeIdTypeAcce);

                ps.executeQuery();
                System.out.println("Ajout de : ID lieu "+ idLieu+"/ ID type accès : "+listeIdTypeAcce);
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


    /**
     * Supprime tous les accès présents dans la base de données qui sont associés à un lieu
     *
     * @param idLieu ID du lieu qui possède les accès
     * @return Etat de la suppression dans la base de données ( Réussi ou non )
     */
    public boolean supprimer(int idLieu){

        boolean retour = false;

        try {
            ps = ConnectionDAO.getInstance().prepareStatement("DELETE FROM acces WHERE IDLIEU = ?");
            ps.setInt(1, idLieu );

            ps.executeUpdate();
            System.out.println("-----");
            System.out.println("Accès "+idLieu+" supprimé");
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


    /**
     * Récupére une liste des accès associées à un ID de lieu
     *
     * @param IDlieu ID du lieu pour lequel on veut les accès
     * @return Liste des accès associés au lieu ( liste d'entiers quui sont les types d'accès )
     */
    public ArrayList<Integer> recupererListeAcces (int IDlieu){

        ArrayList<Integer> listeAcces = new ArrayList<>();
        ResultSet rs;

        try {
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT * FROM ACCES WHERE (  IDLIEU = ?)");
            ps.setInt(1, IDlieu);


            rs = ps.executeQuery();
            System.out.println("-----");
            System.out.print("Type accès récupérés : ");

            while( rs.next() ){
                listeAcces.add( rs.getInt("IDTYPEACCES"));
                System.out.print(rs.getInt("IDTYPEACCES")+" ");
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


    /**
     * Retourne la liste de tous les accès
     *
     * @return Liste des accès
     */
    public ArrayList<Acces> recupererToutLesAcces(){

        ArrayList<Acces> listeAcces = new ArrayList<>();
        ResultSet rs;

        try {
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT * FROM ACCES");

            rs = ps.executeQuery();
            System.out.println("-----");
            System.out.print("Type accès récupérés : ");

            while( rs.next() ){

                listeAcces.add( new Acces(rs.getInt("IDLIEU"),rs.getInt("IDTYPEACCES")) );
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
