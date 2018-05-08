package Interface.Menu;

import DAO.TableTypeAccesDAO;
import Interface.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Classe qui représente le menu créer un type d'accès
 */
public class CreationTypeAcces {

    private final Fenetre fenetre;
    private final JTextField champNomTypeAcces;


    /**
     * Constructeur du menu création type d'accès
     *
     * @param fenetre Fenetre qui contient le JPanel pour le menu pour ajouter un type d'accès
     */
    public CreationTypeAcces(Fenetre fenetre){

        this.fenetre = fenetre;
        System.out.println(" ");
        System.out.println("Affichage du menu création de type d'accès");


        JButton bouttonAjouterTypeAcces = new JButton("Ajouter");

        champNomTypeAcces = new JTextField("");
        champNomTypeAcces.setPreferredSize(new Dimension(150,50));

        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().add(champNomTypeAcces);
        fenetre.getFenetre().add(bouttonAjouterTypeAcces);
        fenetre.updateAffichage();

        bouttonAjouterTypeAcces.addActionListener(new actionBouttonAjouter());
    }


    /**
     * Ecoute du boutton Ajouter
     */
    class actionBouttonAjouter implements ActionListener{


        /**
         * Ajoute le type d'accès entré dans le champ  lors de l'appuie sur le boutton Ajouter
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {

            TableTypeAccesDAO tableTypeAccesDAO = new TableTypeAccesDAO();

            if(tableTypeAccesDAO.ajouter( champNomTypeAcces.getText() )){
                JOptionPane.showMessageDialog(null, "Ajout effectué !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                new CreationTypeAcces(fenetre);
            }
            else{
                JOptionPane.showMessageDialog(null, "Erreur avec la base de données", "Erreur",JOptionPane.ERROR_MESSAGE);

            }

        }
    }
}
