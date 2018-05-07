package Interface.Menu;

import DAO.TableAccesDAO;
import DAO.TableLieuDAO;
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

    private Lieu lieu;

    TableAccesDAO tableAccesDAO = new TableAccesDAO();

    public ModifierLieu(Fenetre fenetre){

        this.fenetre=fenetre;

        lieu = new Lieu();
        lieu.setIDLieu(-1);

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
                JOptionPane.showMessageDialog(null, "Entrez un nombre d'accès!", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else{

                lieu.setNombreAcces( formulaireLieu.getNombreAcces() );

                // Si l'id du lieu sélectionné a changé alors on recherche les accès
                if( lieu.getIDLieu() != rechercheLieu.lieuSelectionne().getIDLieu() ){
                    lieu.setIDLieu( rechercheLieu.lieuSelectionne().getIDLieu() );
                    lieu.setIDlisteAcces( tableAccesDAO.recupererListeAcces( lieu.getIDLieu() ));
                }


                fenetreLierLieuAcces = new FenetreLierLieuAcces( lieu );

                fenetreLierLieuAcces.fenetreVisible();
            }
        }
    }

    class ecouteBouttonValider implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            TableLieuDAO tableLieuDAO = new TableLieuDAO();

            // Si l'id du lieu sélectionné a changé alors on recherche les accès
            if( lieu.getIDLieu() != rechercheLieu.lieuSelectionne().getIDLieu() ){
                lieu.setIDLieu( rechercheLieu.lieuSelectionne().getIDLieu() );
                lieu.setIDlisteAcces( tableAccesDAO.recupererListeAcces( lieu.getIDLieu() ));
            }

            lieu.setEmplacement( formulaireLieu.getEmplacement() );
            lieu.setHoraireOuverture( formulaireLieu.getHoraireOuverture() );
            lieu.setHoraireFermeture( formulaireLieu.getHoraireFermeture() );
            lieu.setNombreAcces( formulaireLieu.getNombreAcces() );


            if(formulaireLieu.getNombreAcces()>= lieu.getListeAcces().size() ){

                tableLieuDAO.modifier(lieu);

                TableAccesDAO tableAccesDAO = new TableAccesDAO();
                tableAccesDAO.supprimer( lieu.getIDLieu() );
                tableAccesDAO.ajouter(lieu.getIDLieu(), lieu.getListeAcces());

                JOptionPane.showMessageDialog(null, "Lieu modifié !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                new ModifierLieu(fenetre);
            }
            else{
                System.out.println("Erreur Ajout accès");
                JOptionPane.showMessageDialog(null, "Le nombre d'accès défini est supérieur au nombre d'accès du lieu", "Erreur",JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
