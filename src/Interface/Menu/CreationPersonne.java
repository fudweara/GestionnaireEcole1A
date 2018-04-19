package Interface.Menu;

import Interface.Fenetre;
import Interface.elementGraphique.FormulairePersonne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui crée et a en attribut le JPanel du menu création personne personne
 */
public class CreationPersonne {

    private Fenetre fenetre;

    private JLabel texteCreerUnePersonne;
    private JPanel JPanelTexteCreerUnePersonne;
    private JPanel espaceVide;
    private FormulairePersonne formulairePersonne;
    private JButton validerCreation;
    private JPanel JPanelValiderCreation;
    private Font police;

    /**
     * Constructeur du menu création fenetre
     * @param fenetre (Fenetre
     */
    public CreationPersonne( Fenetre fenetre ){

        this.fenetre=fenetre;

        police = new Font("Arial", Font.BOLD, 30);
        formulairePersonne = new FormulairePersonne();
        texteCreerUnePersonne = new JLabel("Création d'une personne");
        JPanelTexteCreerUnePersonne =new JPanel();
        espaceVide = new JPanel();
        validerCreation = new JButton("Valider");
        JPanelValiderCreation = new JPanel();


        ajoutComposantsGraphiques();

        validerCreation.addActionListener(new ecouteValiderCreation());

    }

    /**
     * Ajout des éléments graphiques sur la fenetre pour la création de personne
     */
    private void ajoutComposantsGraphiques(){

        texteCreerUnePersonne.setFont(police);

        JPanelTexteCreerUnePersonne.add(texteCreerUnePersonne);

        fenetre.setNewJPanel( espaceVide);

        fenetre.addJPanel( formulairePersonne.getJPanel(), BorderLayout.CENTER);

        JPanelValiderCreation.add(validerCreation);
        fenetre.addJPanel(JPanelValiderCreation,BorderLayout.SOUTH);


    }

    /**
     * Fonction d'écoute pour le boutton valider
     */
    class ecouteValiderCreation implements ActionListener{
        public void actionPerformed(ActionEvent arg0) {
            System.out.println("Appuie sur boutton validation");
        }
    }
}