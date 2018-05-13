package interfaces.menu;

import dao.AccesDAO;
import dao.LieuDAO;
import dao.TypeAccesDAO;
import interfaces.Fenetre;
import interfaces.FenetreGestionAcces;
import interfaces.elementGraphique.FormulaireLieu;
import interfaces.elementGraphique.RechercheLieu;
import objetStockage.Lieu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Classe qui représente le menu modifier un lieu
 */
public class ModifierLieu {

    private final Fenetre fenetre;
    private final RechercheLieu rechercheLieu;
    private final FormulaireLieu formulaireLieu;

    private final Lieu lieu;

    private final AccesDAO accesDAO = new AccesDAO();


    /**
     * Constructeur pour le menu pour modifier un lieu
     *
     * @param fenetre Fenetre qui contient le JPanel pour le menu modifier un lieu
     */
    public ModifierLieu(Fenetre fenetre){

        this.fenetre=fenetre;

        lieu = new Lieu();
        lieu.setIDLieu(-1);

        fenetre.setTitle("Gestionnaire ESIGELEC - Modifier un lieu");

        System.out.println(" ");
        System.out.println("Affichage du menu modifier un lieu");

        JButton bouttonAcces = new JButton("Gestion Acces");
        JButton bouttonModifier = new JButton("Valider");

        formulaireLieu = new FormulaireLieu(bouttonModifier);
        rechercheLieu = new RechercheLieu(fenetre,formulaireLieu);


        // Ajout composants graphiques
        JPanel conteneurBouttons = new JPanel();
        conteneurBouttons.setLayout(new BoxLayout(conteneurBouttons, BoxLayout.LINE_AXIS));
        conteneurBouttons.add(bouttonAcces);
        conteneurBouttons.add( Box.createRigidArea(new Dimension(20, 0)));
        conteneurBouttons.add(bouttonModifier);

        JPanel conteneurFormulaireEtBouttons = new JPanel();
        conteneurFormulaireEtBouttons.setLayout(new BoxLayout(conteneurFormulaireEtBouttons, BoxLayout.PAGE_AXIS));
        conteneurFormulaireEtBouttons.add(formulaireLieu);
        conteneurFormulaireEtBouttons.add( Box.createRigidArea(new Dimension(0, 30)));
        conteneurFormulaireEtBouttons.add(conteneurBouttons);

        fenetre.getFenetre().removeAll();
        fenetre.getFenetre().setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        fenetre.getFenetre().add( rechercheLieu );
        fenetre.getFenetre().add(conteneurFormulaireEtBouttons);
        fenetre.updateAffichage();

        //Ajout fonction d'écoute pour les bouttons
        bouttonAcces.addActionListener( new ecouteBoutonGestionAcces() );
        bouttonModifier.addActionListener( new ecouteBoutonValider() );
    }


    /**
     * Ecoute du bouton Gestion Acces
     */
    class ecouteBoutonGestionAcces implements ActionListener{


        /**
         * Affiche la fenetre pour ajouter un accès si un lieu est selectionné et que le nombre d'accès entré est valide
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {

            if(formulaireLieu.getNombreAcces()== 0){
                JOptionPane.showMessageDialog(null, "Entrez un nombre d'accès!", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else{

                TypeAccesDAO typeAccesDAO = new TypeAccesDAO();

                if(typeAccesDAO.recupereToutlesTypes().size() == 0){
                    JOptionPane.showMessageDialog(null, "Aucun type d'accès défini !", "Erreur",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    lieu.setNombreAcces( formulaireLieu.getNombreAcces() );

                    // Si l'id du lieu sélectionné a changé alors on recherche les accès
                    if( lieu.getIDLieu() != rechercheLieu.lieuSelectionne().getIDLieu() ){
                        lieu.setIDLieu( rechercheLieu.lieuSelectionne().getIDLieu() );
                        lieu.setIDlisteAcces( accesDAO.recupererListeAcces( lieu.getIDLieu() ));
                    }


                    FenetreGestionAcces fenetreGestionAcces = new FenetreGestionAcces(lieu);

                    fenetreGestionAcces.fenetreVisible();
                }

            }
        }
    }


    /**
     * Ecoute du bouton Valider
     */
    class ecouteBoutonValider implements ActionListener{

        /**
         * Ajoute dans la base de données la modification du lieu + ces accès lors de l'appuie sur le boutton valider
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {

            LieuDAO lieuDAO = new LieuDAO();


            // Si l'id du lieu sélectionné a changé alors on recherche les accès
            if( lieu.getIDLieu() != rechercheLieu.lieuSelectionne().getIDLieu() ){
                lieu.setIDLieu( rechercheLieu.lieuSelectionne().getIDLieu() );
                lieu.setIDlisteAcces( accesDAO.recupererListeAcces( lieu.getIDLieu() ));
            }

            lieu.setEmplacement( formulaireLieu.getEmplacement() );
            lieu.setHoraireOuverture( formulaireLieu.getHoraireOuverture() );
            lieu.setHoraireFermeture( formulaireLieu.getHoraireFermeture() );
            lieu.setNombreAcces( formulaireLieu.getNombreAcces() );

            if(lieu.getIDLieu() == -1){
                JOptionPane.showMessageDialog(null, "Selectionnez un lieu à modifier dans la recherche!", "Erreur",JOptionPane.ERROR_MESSAGE);

            }
            else{
                if(formulaireLieu.getNombreAcces()>= lieu.getListeAcces().size() ){

                    // On compare si l'heure d'ouverture est plus petite que l'heure de fermeture
                    if( Integer.parseInt( formulaireLieu.getHoraireOuverture().getHeures()+formulaireLieu.getHoraireOuverture().getMinutes() ) <  Integer.parseInt( formulaireLieu.getHoraireFermeture().getHeures()+formulaireLieu.getHoraireFermeture().getMinutes() )){

                        AccesDAO accesDAO = new AccesDAO();

                        if(lieuDAO.modifier(lieu) && accesDAO.supprimer( lieu.getIDLieu() ) && accesDAO.ajouter(lieuDAO.getID(lieu), lieu.getListeAcces())){
                            JOptionPane.showMessageDialog(null, "Lieu modifié !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                            new ModifierLieu(fenetre);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Erreur avec la base de données", "Erreur",JOptionPane.ERROR_MESSAGE);
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "L'heure d'ouverture est supérieur ou égal à l'heure de fermeture!", "Erreur",JOptionPane.ERROR_MESSAGE);
                    }

                }
                else{
                    System.out.println("Erreur Ajout accès");
                    JOptionPane.showMessageDialog(null, "Le nombre d'accès défini est supérieur au nombre d'accès du lieu", "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
