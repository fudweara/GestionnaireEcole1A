package objetStockage;

import DAO.TableLieuDAO;
import DAO.TableTypeAccesDAO;


/**
 * Représente un Accès qui contient le nom du type d'accès et le nom du lieu qui le caractérise
 */
public class Acces {

    private final String nomTypeAcces;
    private final String nomLieu;


    /**
     * Constructeur d'un accès, recherche le nom du lieu et le nom du type de d'accès qui correspondent aux id entré en paramètre
     *
     * @param idLieu ID du  lieu
     * @param idTypeAcces ID du type d'accès
     */
    public Acces( int idLieu, int idTypeAcces){

        TableLieuDAO tableLieuDAO = new TableLieuDAO();
        TableTypeAccesDAO tableTypeAccesDAO = new TableTypeAccesDAO();

        nomTypeAcces = tableTypeAccesDAO.nomTypeAcces(idTypeAcces);
        nomLieu = tableLieuDAO.getLieu(idLieu).getEmplacement();
    }


    /**
     * Retourne le nom du type d'accès de l'accès
     *
     * @return Nom type d'accès
     */
    public String getNomTypeAcces() {
        return nomTypeAcces;
    }


    /**
     * Retourne le nom du lieu de l'accès
     *
     * @return Nom du lieu
     */
    public String getNomLieu() {
        return nomLieu;
    }
}
