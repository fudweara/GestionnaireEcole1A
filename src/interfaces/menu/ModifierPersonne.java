package interfaces.menu;

import dao.PersonneDAO;
import interfaces.Fenetre;
import interfaces.elementGraphique.FormulairePersonne;
import interfaces.elementGraphique.RecherchePersonne;
import objetStockage.Personne;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Classe qui crée et a en attribut le JPanel du menu modifier personne
 */
public class ModifierPersonne {

    private final RecherchePersonne recherchePersonne;
    private final FormulairePersonne formulairePersonne;

    private final Fenetre fenetre;


    /**
     * Constructeur du menu modifier personne qui place les élements graphiques et les fonctions d'écoute
     *
     * @param fenetre Fenetre qui contenir le JPanel pour le menu pour modifier une personne
     */
    public ModifierPersonne( Fenetre fenetre ){

        JButton bouttonModifier = new JButton("Modifier");

        this.fenetre = fenetre;

        fenetre.setTitle("Gestionnaire ESIGELEC - Modifier une personne");

        System.out.println(" ");
        System.out.println("Affichage du menu modifier une personne");

        formulairePersonne = new FormulairePersonne(bouttonModifier);
        recherchePersonne = new RecherchePersonne(fenetre,0,formulairePersonne);

        // Ajout éléments graphiques
        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        fenetre.getFenetre().add( recherchePersonne );
        fenetre.getFenetre().add(formulairePersonne);
        fenetre.getFenetre().add(bouttonModifier);
        fenetre.updateAffichage();

        // Ajout écoute pour des actions
        bouttonModifier.addActionListener(new actionButtonValider());
    }


    /**
     * Fonction d'écoute du boutton valider
     */
    class actionButtonValider implements ActionListener{


        /**
         * Modifie la personne dans la base de données lors de l'appuie sur le boutton valider
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {

            Personne personneMiseAJour = new Personne ( recherchePersonne.getIdPersonneSelectionne(), formulairePersonne.getNom(), formulairePersonne.getPrenom(),formulairePersonne.getDateDeNaissance(),formulairePersonne.getFonction());
            PersonneDAO personneDAO = new PersonneDAO();

            if( personneDAO.modifier(personneMiseAJour) ){
                JOptionPane.showMessageDialog(null, "Modification effectuée !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                new ModifierPersonne(fenetre);
            }
            else{
                JOptionPane.showMessageDialog(null, "Tous les champs doivent être complétés et la date correctement entrée", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
