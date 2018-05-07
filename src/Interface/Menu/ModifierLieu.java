package Interface.Menu;

import Interface.Fenetre;
import Interface.FenetreLierLieuAcces;
import Interface.elementGraphique.FormulaireLieu;
import Interface.elementGraphique.RechercheLieu;
import objetStockage.Lieu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierLieu {

    private Fenetre fenetre;
    private RechercheLieu rechercheLieu;
    private FormulaireLieu formulaireLieu;

    private JButton bouttonAcces;
    private JButton bouttonModifier;

    private JPanel conteneurFormulaireEtBouttons;
    private JPanel conteneurBouttons;

    private FenetreLierLieuAcces fenetreLierLieuAcces;

    public ModifierLieu(Fenetre fenetre){

        this.fenetre=fenetre;

        fenetre.setTitle("Gestionnaire ESIGELEC - Modifier un lieu");

        bouttonAcces = new JButton("Gestion Acces");
        bouttonModifier = new JButton("Valider");

        formulaireLieu = new FormulaireLieu(bouttonModifier);
        rechercheLieu = new RechercheLieu(fenetre,formulaireLieu);
        conteneurFormulaireEtBouttons = new JPanel();
        conteneurBouttons = new JPanel();

        // Ajout composants graphiques
        conteneurBouttons.setLayout(new BoxLayout(conteneurBouttons, BoxLayout.LINE_AXIS));
        conteneurBouttons.add(bouttonAcces);
        conteneurBouttons.add( Box.createRigidArea(new Dimension(20, 0)));
        conteneurBouttons.add(bouttonModifier);

        conteneurFormulaireEtBouttons.setLayout(new BoxLayout(conteneurFormulaireEtBouttons, BoxLayout.PAGE_AXIS));
        conteneurFormulaireEtBouttons.add(formulaireLieu);
        conteneurFormulaireEtBouttons.add( Box.createRigidArea(new Dimension(0, 30)));
        conteneurFormulaireEtBouttons.add(conteneurBouttons);

        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        fenetre.getFenetre().add( rechercheLieu );
        fenetre.getFenetre().add( conteneurFormulaireEtBouttons );
        fenetre.updateAffichage();

        //Ajout fonction d'écoute pour les bouttons
        bouttonAcces.addActionListener( new ecouteBouttonAcces() );
        bouttonModifier.addActionListener( new ecouteBouttonValider() );
    }

    class ecouteBouttonAcces implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(formulaireLieu.getNombreAcces()== 0){
                JOptionPane.showMessageDialog(null, "Entrer un nombre d'accès", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else{
                fenetreLierLieuAcces = new FenetreLierLieuAcces( new Lieu(formulaireLieu.getEmplacement(), formulaireLieu.getHoraireOuverture(), formulaireLieu.getHoraireFermeture(), formulaireLieu.getNombreAcces()) );
                fenetreLierLieuAcces.fenetreVisible();
            }


        }
    }

    class ecouteBouttonValider implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
