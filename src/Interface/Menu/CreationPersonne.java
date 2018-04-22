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
        fenetre.setTitle("Gestionnaire ESIGELEC - Créer une personne");

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

            TablePersonneDAO tablePersonneDAO = new TablePersonneDAO();

            System.out.println("Appuie sur boutton validation");

            if( tablePersonneDAO.ajoutPersonne( new Personne(0, formulairePersonne.getNom(),formulairePersonne.getPrenom(), formulairePersonne.getDateDeNaissance(), formulairePersonne.getFonction()) )){
                JOptionPane.showMessageDialog(null, "Ajout effectué !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Tous les champs doivent être complétés et la date correctement entrée", "Erreur",JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}