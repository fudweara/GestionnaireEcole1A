package Interface.Menu;

import DAO.TableCarteLeoDAO;
import Interface.Fenetre;
import Interface.elementGraphique.RecherchePersonne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qu représente le menu pour supprimer une carte Léo
 */

public class SupprimerCarteLeo {

    private final RecherchePersonne recherchePersonne;
    private final Fenetre fenetre;

    public SupprimerCarteLeo(Fenetre fenetre){

        JButton bouttonSupprimerCarte = new JButton("Supprimer");

        this.fenetre = fenetre;
        fenetre.setTitle("Gestionnaire ESIGELEC - Supprimer une carte Léo");

        recherchePersonne = new RecherchePersonne(fenetre, 1, null);

        //Ajout éléments graphiques
        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(15, 100, 15, 100));
        fenetre.getFenetre().add( recherchePersonne );
        fenetre.getFenetre().add( Box.createRigidArea(new Dimension(20, 0)));
        fenetre.getFenetre().add(bouttonSupprimerCarte);
        fenetre.updateAffichage();

        //Ajout fonction ecoute pour le boutton
        bouttonSupprimerCarte.addActionListener(new actionBouttonSupprimerCarte());
    }

    class actionBouttonSupprimerCarte implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            System.out.println("Appuie sur boutton supprimer");

            TableCarteLeoDAO tableCarteLeoDAO = new TableCarteLeoDAO();

            if(tableCarteLeoDAO.supprimer(recherchePersonne.getIdPersonneSelectionne()) ){
                System.out.println("Suppression effectuée");
                JOptionPane.showMessageDialog(null, "Suppression effectuée !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                new SupprimerCarteLeo(fenetre);
            }
            else{
                System.out.println("Selectionnez une personne");
                JOptionPane.showMessageDialog(null, "Selectionnez une personne", "Erreur",JOptionPane.ERROR_MESSAGE);

            }

        }
    }

}
