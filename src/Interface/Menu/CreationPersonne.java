package Interface.Menu;

import DAO.TablePersonneDAO;
import Interface.Fenetre;
import Interface.elementGraphique.FormulairePersonne;
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
     * @param fenetre (Fenetre
     */
    public CreationPersonne( Fenetre fenetre ){

        fenetre.setTitle("Gestionnaire ESIGELEC - Créer une personne");
        this.fenetre = fenetre;

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

        public void actionPerformed(ActionEvent arg0) {

            TablePersonneDAO tablePersonneDAO = new TablePersonneDAO();

            System.out.println("Appuie sur boutton validation");

            if( tablePersonneDAO.ajoutPersonne( new Personne(0, formulairePersonne.getNom(),formulairePersonne.getPrenom(), formulairePersonne.getDateDeNaissance(), formulairePersonne.getFonction()) )){
                JOptionPane.showMessageDialog(null, "Ajout effectué !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                new CreationLieu(fenetre);
            }
            else{
                JOptionPane.showMessageDialog(null, "Tous les champs doivent être complétés et la date correctement entrée", "Erreur",JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}