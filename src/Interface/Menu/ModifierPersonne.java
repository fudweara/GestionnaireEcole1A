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

    private final RecherchePersonne recherchePersonne;
    private final FormulairePersonne formulairePersonne;

    private final Fenetre fenetre;

    /**
     * Constructeur du menu modifier personne qui place les élements graphiques et les fonctions d'écoute
     * @param fenetre (Fenetre)
     */
    public ModifierPersonne( Fenetre fenetre ){

        JButton bouttonModifier = new JButton("Modifier");

        this.fenetre = fenetre;

        fenetre.setTitle("Gestionnaire ESIGELEC - Modifier une personne");

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

        public void actionPerformed(ActionEvent e) {

            Personne personneMiseAJour = new Personne ( recherchePersonne.getIdPersonneSelectionne(), formulairePersonne.getNom(), formulairePersonne.getPrenom(),formulairePersonne.getDateDeNaissance(),formulairePersonne.getFonction());
            TablePersonneDAO tablePersonneDAO = new TablePersonneDAO();

            if( tablePersonneDAO.modifier(personneMiseAJour) ){
                JOptionPane.showMessageDialog(null, "Modification effectuée !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                new ModifierPersonne(fenetre);
            }
            else{
                JOptionPane.showMessageDialog(null, "Tous les champs doivent être complétés et la date correctement entrée", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
