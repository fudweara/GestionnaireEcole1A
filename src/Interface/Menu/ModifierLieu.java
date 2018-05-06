package Interface.Menu;

import Interface.Fenetre;
import Interface.FenetreLierLieuAcces;
import Interface.elementGraphique.FormulaireLieu;
import Interface.elementGraphique.RechercheLieu;
import objetStockage.Lieu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierLieu {

    private Fenetre fenetre;
    private RechercheLieu rechercheLieu;
    private FormulaireLieu formulaireLieu;

    private JButton bouttonAcces;
    private JButton bouttonModifier;

    private FenetreLierLieuAcces fenetreLierLieuAcces;

    public ModifierLieu(Fenetre fenetre){

        this.fenetre=fenetre;

        fenetre.setTitle("Gestionnaire ESIGELEC - Modifier un lieu");

        bouttonAcces = new JButton("Gestion Acces");
        bouttonModifier = new JButton("Valider");

        formulaireLieu = new FormulaireLieu(bouttonModifier);
        rechercheLieu = new RechercheLieu(fenetre,formulaireLieu);

        fenetre.setNewJPanel( rechercheLieu.getJPanel());
        fenetre.addJPanel( formulaireLieu.getJPanel(),"" );

        bouttonAcces.addActionListener( new ecouteBouttonAcces() );
        bouttonModifier.addActionListener( new ecouteBouttonValider() );
        fenetre.addJPanel( bouttonAcces,"" );
        fenetre.addJPanel( bouttonModifier,"" );


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
