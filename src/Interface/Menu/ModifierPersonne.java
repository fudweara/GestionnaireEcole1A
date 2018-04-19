package Interface.Menu;

import DAO.TablePersonneDAO;
import Interface.Fenetre;
import Interface.elementGraphique.FormulairePersonne;
import Interface.elementGraphique.RecherchePersonne;
import objetStockage.Personne;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe qui crée et a en attribut le JPanel du menu modifier personne
 */
public class ModifierPersonne {

    private RecherchePersonne recherchePersonne;
    private FormulairePersonne formulairePersonne;

    private JButton bouttonModifier;
    private JPanel panelButtonModifier;

    /**
     * Constructeur du menu modifier personne qui place les élements graphiques et les fonctions d'écoute
     * @param fenetre (Fenetre)
     */
    public ModifierPersonne( Fenetre fenetre ){

        fenetre.setTitle("Gestionnaire ESIGELEC - Modifier une personne");

        formulairePersonne = new FormulairePersonne();
        recherchePersonne = new RecherchePersonne(fenetre,formulairePersonne);
        bouttonModifier = new JButton("Modifier");
        panelButtonModifier = new JPanel();

        fenetre.setNewJPanel( recherchePersonne.getJPanel() );
        fenetre.addJPanel(formulairePersonne.getJPanel(),"");

        panelButtonModifier.add(bouttonModifier);
        bouttonModifier.addActionListener(new actionButtonValider());

        fenetre.addJPanel(panelButtonModifier,"");
    }

    /**
     * Fonction d'écoute du boutton valider
     */
    class actionButtonValider implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            Personne personneMiseAJour = new Personne ( recherchePersonne.getIdPersonneSelectionne(), formulairePersonne.getNom(), formulairePersonne.getPrenom(),formulairePersonne.getDateDeNaissance(),formulairePersonne.getFonction());
            TablePersonneDAO tablePersonneDAO = new TablePersonneDAO();

            if( tablePersonneDAO.miseAJourPersonne(personneMiseAJour) == 1){
                JOptionPane.showMessageDialog(null, "Modification effectuée !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Tous les champs doivent être complétés et la date correctement entrée", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
