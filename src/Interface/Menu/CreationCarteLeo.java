package Interface.Menu;

import DAO.TableCarteLeoDAO;
import Interface.Fenetre;
import Interface.elementGraphique.RecherchePersonne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui représente le menu créer une carte Leo
 */
public class CreationCarteLeo {

    private final Fenetre fenetre;
    private final RecherchePersonne recherchePersonne;

    /**
     * Constructeur de la classe CreationCarteLeo
     * @param fenetre (Fenetre)
     */
    public CreationCarteLeo(Fenetre fenetre){

        this.fenetre = fenetre;
        fenetre.setTitle("Gestionnaire ESIGELEC - Ajouter une carte Léo");

        JButton bouttonAjouterCarte = new JButton("Créer");
        recherchePersonne = new RecherchePersonne(fenetre, 2, null);

        //Ajout composants graphiques
        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(15, 100, 15, 100));
        fenetre.getFenetre().add( recherchePersonne );
        fenetre.getFenetre().add( Box.createRigidArea(new Dimension(20, 0)));
        fenetre.getFenetre().add(bouttonAjouterCarte);
        fenetre.updateAffichage();

        //Ajout fonction ecoute pour le boutton ajouter
        bouttonAjouterCarte.addActionListener(new actionBouttonAjouterCarte());
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
