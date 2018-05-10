package Interface.elementGraphique;

import DAO.TableAccesDAO;
import objetStockage.Acces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Liste les accès
 */
public class ListeAcces extends JPanel {

    private final DefaultTableModel tableModel ;
    private final ArrayList<Acces> listeAcces;

    /**
     * Constructeur qui crée et affiche ube liste de tous les accès
     */
    public ListeAcces(){


        tableModel = new DefaultTableModel(0,2);
        tableModel.setColumnIdentifiers(new Object[]{"Lieu","Type Accès"});
        JTable table = new JTable(tableModel);


        TableAccesDAO tableAccesDAO = new TableAccesDAO();
        listeAcces = tableAccesDAO.recupererToutLesAcces();

        affichageTousLesAcces();

        JScrollPane scrollPane = new JScrollPane(table);
        setSize(300, 150);

        scrollPane.setPreferredSize(new Dimension(300, 230));
        add(scrollPane);
    }


    /**
     * Filtre les accès pour un lieu donné
     *
     * @param nomLieu Nom du lieu qui possède les accès
     */
    public void filtragePourUnLieu(String nomLieu){

        tableModel.setRowCount(0);

        for (Acces listeAcce : listeAcces) {

            if (nomLieu.equals(listeAcce.getNomLieu())) {
                tableModel.addRow(new Object[]{listeAcce.getNomLieu(), listeAcce.getNomTypeAcces()});
            }
        }

    }


    /**
     * Filtre les accès pour un type d'accès donné
     *
     * @param nomTypeAcces Nom du type d'accès qui carractérise les accès
     */
    public void filtragePourUnTypeAcces(String nomTypeAcces){

        tableModel.setRowCount(0);

        for (Acces listeAcce : listeAcces) {

            if (nomTypeAcces.equals(listeAcce.getNomTypeAcces())) {
                tableModel.addRow(new Object[]{listeAcce.getNomLieu(), listeAcce.getNomTypeAcces()});
            }
        }
    }


    /**
     * Affiche tous les accès dans la liste
     */
    public void affichageTousLesAcces(){

        tableModel.setRowCount(0);

        for (Acces listeAcce : listeAcces) {
            Object[] data = {listeAcce.getNomLieu(), listeAcce.getNomTypeAcces()};

            tableModel.addRow(data);
        }
    }

}
