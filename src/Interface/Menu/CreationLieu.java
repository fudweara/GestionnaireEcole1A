package Interface.Menu;

import Interface.FenetreLierLieuAcces;
import DAO.TableLieuDAO;
import Interface.Fenetre;
import Interface.elementGraphique.FormulaireLieu;
import objetStockage.Lieu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreationLieu {

    private Fenetre fenetre;
    private FormulaireLieu formulaireLieu;

    private JButton bouttonAjouterLieu;
    private JPanel panelBouttonAjouterLieu;

    private JButton bouttonAjouterAcces;
    private JPanel panelBouttonAjouterAcces;

    private Lieu lieu;

    private FenetreLierLieuAcces fenetreLierLieuAcces;

    public CreationLieu(Fenetre fenetre){

        lieu = new Lieu();

        this.fenetre = fenetre;
        fenetre.setTitle("Gestionnaire ESIGELEC - Ajouter un lieu");



        bouttonAjouterLieu = new JButton("Créer");
        bouttonAjouterLieu.setEnabled(false);
        panelBouttonAjouterLieu = new JPanel();

        bouttonAjouterAcces = new JButton("Accès");
        panelBouttonAjouterAcces = new JPanel();

        formulaireLieu = new FormulaireLieu(bouttonAjouterLieu);
        fenetre.setNewJPanel( formulaireLieu.getJPanel() );

        panelBouttonAjouterLieu.add(bouttonAjouterLieu);
        bouttonAjouterLieu.addActionListener( new actionBouttonAjouterLieu() );

        panelBouttonAjouterAcces.add(bouttonAjouterAcces);
        bouttonAjouterAcces.addActionListener( new actionBouttonAjouterAcces() );

        fenetre.addJPanel(panelBouttonAjouterAcces,"");

        fenetre.addJPanel(panelBouttonAjouterLieu,"");

    }

    class actionBouttonAjouterLieu implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            TableLieuDAO tableLieuDAO = new TableLieuDAO();
            System.out.println("Appuie sur le boutton ajouter Lieu");

            lieu.setEmplacement( formulaireLieu.getEmplacement() );
            lieu.setHoraireOuverture( formulaireLieu.getHoraireOuverture() );
            lieu.setHoraireFermeture( formulaireLieu.getHoraireFermeture() );
            lieu.setNombreAcces( formulaireLieu.getNombreAcces() );

            if ( tableLieuDAO.ajouter( lieu ) ){
                System.out.println("Lieu créee");
                JOptionPane.showMessageDialog(null, "Lieu crée !", "Message de confirmation",JOptionPane.INFORMATION_MESSAGE);
                new CreationLieu(fenetre);
            }
            else{
                JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement du lieu", "Erreur",JOptionPane.ERROR_MESSAGE);
            }



        }
    }

    class actionBouttonAjouterAcces implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            lieu.setNombreAcces(formulaireLieu.getNombreAcces());

            if(lieu.getNombreAcces()== 0){
                JOptionPane.showMessageDialog(null, "Entrer un nombre d'accès", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
            else{
                fenetreLierLieuAcces = new FenetreLierLieuAcces(lieu);
                fenetreLierLieuAcces.fenetreVisible();

            }

        }
    }
}
