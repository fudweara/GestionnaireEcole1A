package interfaces.menu;

import dao.PersonneDAO;
import interfaces.Fenetre;
import interfaces.elementGraphique.FormulairePersonne;
import objetStockage.Personne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Classe qui crée et a en attribut le JPanel du menu création personne personne
 */
public class CreationPersonne {

    private final FormulairePersonne formulairePersonne;
    private final Fenetre fenetre;


    /**
     * Constructeur du menu création fenetre
     *
     * @param fenetre Fenetre qui va contenir le JPanel du menu pour créer une personne
     */
    public CreationPersonne( Fenetre fenetre ){

        fenetre.setTitle("Gestionnaire ESIGELEC - Créer une personne");
        this.fenetre = fenetre;

        System.out.println(" ");
        System.out.println("Affichage du menu création de personnes");

        JButton bouttonValiderCreation = new JButton("Valider");
        formulairePersonne = new FormulairePersonne(bouttonValiderCreation);

        //Ajout éléments graphiques
        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(50, 50, 20, 50));
        fenetre.getFenetre().add( formulairePersonne);
        fenetre.getFenetre().add( Box.createRigidArea(new Dimension(20, 0)));
        fenetre.getFenetre().add(bouttonValiderCreation);
        fenetre.updateAffichage();

        //Ajout listener
        bouttonValiderCreation.addActionListener(new ecouteValiderCreation());
    }


    /**
     * Fonction d'écoute pour le boutton valider
     */
    class ecouteValiderCreation implements ActionListener{


        /**
         * Ajoute la personne entré dans le formulaire lors de l'appuie sur le boutton
         *
         * @param arg0 Action Evenement
         */
        public void actionPerformed(ActionEvent arg0) {

            PersonneDAO personneDAO = new PersonneDAO();

            System.out.println("Appuie sur boutton validation");

            if( personneDAO.ajout( new Personne(0, formulairePersonne.getNom(),formulairePersonne.getPrenom(), formulairePersonne.getDateDeNaissance(), formulairePersonne.getFonction()) )){
                JOptionPane.showMessageDialog(null, "Ajout effectué !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                new CreationPersonne(fenetre);
            }
            else{
                JOptionPane.showMessageDialog(null, "Tous les champs doivent être complétés et la date correctement entrée", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}