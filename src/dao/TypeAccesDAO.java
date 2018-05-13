package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 * Classe regroupant les fonctions pour la gestion des type d'accès dans la base de données
 */
public class TypeAccesDAO {

    private PreparedStatement ps = null;


    /**
     * Constructeur qui fait la connexion avec la base de données
     */
    public TypeAccesDAO() {
        ConnectionDAO.getInstance();
    }


    /**
     * Ajoute un type d'accès dans la base de données
     *
     * @param typeAcces Nom du type d'accès à ajouter
     * @return Vérification sur l'ajout du type d'accès dans la base de données
     */
    public boolean ajouter(String typeAcces){

        boolean retour = false;

        try{
            ps = ConnectionDAO.getInstance().prepareStatement("INSERT INTO typeacces VALUES ( null, ?)");
            ps.setString(1,typeAcces);

            ps.executeUpdate();
            retour=true;
            System.out.println("-----");
            System.out.println("Type d'accès ajouté : "+typeAcces);

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


    /**
     * Retourne tous les types d'accès
     *
     * @return Liste de chaine de caractères de tous les accès
     */
    public ArrayList<String> recupereToutlesTypes(){

        ArrayList<String> typesAcces = new ArrayList<>();
        ResultSet rs;

        try {
            ps = ConnectionDAO.getInstance().prepareStatement("SELECT * FROM TYPEACCES");

            rs = ps.executeQuery();

            System.out.println("-----");
            System.out.println("Tous les types d'accès :");
            while (rs.next() ){
                typesAcces.add( rs.getString("NOM_TYPEACCES") );
                System.out.print( rs.getString("NOM_TYPEACCES") );
            }
            System.out.println(" ");

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


    /**
     * Récupére l'ID d'un type accès pour lequel on a le nom
     *
     * @param nomTypeAcces Nom du type d'accès pour lequel on veut connaitre l'ID
     * @return ID du type d'accès
     */
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


    /**
     * Retourne le nom du type d'accès pour lequel on a l'ID
     *
     * @param ID ID pour lequel on veut connaitre le nom du type d'accès
     * @return Nom du type d'accès
     */
    public String nomTypeAcces(int ID){

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
