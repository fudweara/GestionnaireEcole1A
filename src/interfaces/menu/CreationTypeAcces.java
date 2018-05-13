package interfaces.menu;

import dao.TypeAccesDAO;
import interfaces.Fenetre;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    private final JButton boutonAjouterTypeAcces;

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

        boutonAjouterTypeAcces = new JButton("Ajouter");
        boutonAjouterTypeAcces.setEnabled(false);

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

        TypeAccesDAO typeAccesDAO = new TypeAccesDAO();
        ArrayList<String> typeAcces = typeAccesDAO.recupereToutlesTypes();
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
        champNomTypeAcces.getDocument().addDocumentListener(new ecouteChampTexte());
    }


    /**
     * Ecoute du champ de texte pour activer/désactiver le bouton Ajouter si il y a du texte ou non
     */
    class ecouteChampTexte implements DocumentListener{


        /**
         * Active le bouton Ajouter  lorsque l'on rentre du texte
         *
         * @param e Evenement
         */
        public void insertUpdate(DocumentEvent e) {
            boutonAjouterTypeAcces.setEnabled(true);
        }


        /**
         * Si il n'y a plus de texte lors d'une suppression dans le champ d'entrée de texte alors désactive le bouton Ajouter
         *
         * @param e Evenement
         */
        public void removeUpdate(DocumentEvent e) {
            if(champNomTypeAcces.getText().equals("") ){
                boutonAjouterTypeAcces.setEnabled(false);
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
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

            TypeAccesDAO typeAccesDAO = new TypeAccesDAO();

            if(typeAccesDAO.ajouter( champNomTypeAcces.getText() )){
                JOptionPane.showMessageDialog(null, "Ajout effectué !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                new CreationTypeAcces(fenetre);
            }
            else{
                JOptionPane.showMessageDialog(null, "Erreur avec la base de données", "Erreur",JOptionPane.ERROR_MESSAGE);

            }

        }
    }
}
