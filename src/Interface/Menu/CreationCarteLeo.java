package Interface.Menu;

import DAO.TableCarteLeoDAO;
import Interface.Fenetre;
import Interface.elementGraphique.RecherchePersonne;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui représente le menu créer une carte Leo
 */
public class CreationCarteLeo {

    private Fenetre fenetre;
    private RecherchePersonne recherchePersonne;

    private JButton bouttonAjouterCarte;
    private JPanel panelBouttonAjouterCarte;

    /**
     * Constructeur de la classe CreationCarteLeo
     * @param fenetre (Fenetre)
     */
    public CreationCarteLeo(Fenetre fenetre){

        this.fenetre = fenetre;
        fenetre.setTitle("Gestionnaire ESIGELEC - Ajouter une carte Léo");

        recherchePersonne = new RecherchePersonne(fenetre, 2, null);

        fenetre.setNewJPanel( recherchePersonne.getJPanel() );
        bouttonAjouterCarte = new JButton("Créer");
        panelBouttonAjouterCarte = new JPanel();

        panelBouttonAjouterCarte.add(bouttonAjouterCarte);
        bouttonAjouterCarte.addActionListener(new actionBouttonAjouterCarte());

        fenetre.addJPanel(panelBouttonAjouterCarte,"");

    }

    /**
     * Fonction d'écoute pour le bouton Ajouter
     */
    class actionBouttonAjouterCarte implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            TableCarteLeoDAO tableCarteLeoDAO = new TableCarteLeoDAO();
            System.out.println("Appuie sur le boutton Créer carte Léo");

            if(tableCarteLeoDAO.ajouter( recherchePersonne.getIdPersonneSelectionne())){
                System.out.println("Carte Leo créee");
                JOptionPane.showMessageDialog(null, "Carte Léo créee !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                new CreationCarteLeo(fenetre);
            }
            else{
                JOptionPane.showMessageDialog(null, "Selectionnez une personne !", "Erreur",JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
