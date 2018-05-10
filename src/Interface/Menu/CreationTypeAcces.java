package Interface.Menu;

import DAO.TableTypeAccesDAO;
import Interface.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


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

        fenetre.setTitle("Gestionnaire ESIGELEC - Créer un type d'accès");
        this.fenetre = fenetre;

        System.out.println(" ");
        System.out.println("Affichage du menu création de type d'accès");

        JButton boutonAjouterTypeAcces = new JButton("Ajouter");

        champNomTypeAcces = new JTextField("");
        champNomTypeAcces.setPreferredSize(new Dimension(150,50));

        //Creation de la liste et paramètrage.
        JPanel panelListe = new JPanel();
        panelListe.setPreferredSize(new Dimension(300,300));
        DefaultListModel<String> listeModele = new DefaultListModel<>();
        JList<String> jListe = new JList<>(listeModele);
        jListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListe.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(jListe);
        listScrollPane.setPreferredSize(new Dimension(200, 230));

        TableTypeAccesDAO tableTypeAccesDAO = new TableTypeAccesDAO();
        ArrayList<String> typeAcces = tableTypeAccesDAO.recupereToutlesTypes();
        for (String typeAcce : typeAcces) {
            listeModele.addElement(typeAcce);
        }

        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(40, 50, 20, 50));
        fenetre.getFenetre().add(champNomTypeAcces);
        fenetre.getFenetre().add(boutonAjouterTypeAcces);
        fenetre.getFenetre().add( Box.createRigidArea(new Dimension(30, 0)));
        fenetre.getFenetre().add(listScrollPane);
        fenetre.updateAffichage();

        boutonAjouterTypeAcces.addActionListener(new actionBoutonAjouter());
    }


    /**
     * Ecoute du bouton Ajouter
     */
    class actionBoutonAjouter implements ActionListener{


        /**
         * Ajoute le type d'accès entré dans le champ  lors de l'appuie sur le bouton Ajouter
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
