package Interface.Menu;

import DAO.TablePersonneDAO;
import Interface.Fenetre;
import Interface.elementGraphique.FormulairePersonne;
import Interface.elementGraphique.RecherchePersonne;
import objetStockage.Personne;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ModifierPersonne {

    private Fenetre fenetre;
    RecherchePersonne recherchePersonne;
    FormulairePersonne formulairePersonne;

    JButton bouttonModifier;
    JPanel panelButtonModifier;

    public ModifierPersonne( Fenetre fenetre ){

        this.fenetre=fenetre;
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

    class actionButtonValider implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            Personne personneMiseAJour = new Personne ( recherchePersonne.getIdPersonneSelectionne(), formulairePersonne.getNom(), formulairePersonne.getPrenom(),formulairePersonne.getDateDeNaissance(),formulairePersonne.getFonction());
            TablePersonneDAO tablePersonneDAO = new TablePersonneDAO();

            tablePersonneDAO.miseAJourPersonne(personneMiseAJour);
        }
    }

}
