package Interface.Menu;

import DAO.TableCarteLeoDAO;
import Interface.Fenetre;
import Interface.elementGraphique.RecherchePersonne;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreationCarteLeo {

    private Fenetre fenetre;

    private RecherchePersonne recherchePersonne;

    private JButton bouttonAjouterCarte;
    private JPanel panelBouttonAjouterCarte;

    public CreationCarteLeo(Fenetre fenetre){

        fenetre.setTitle("Gestionnaire ESIGELEC - Ajouter une carte Léo");

        recherchePersonne = new RecherchePersonne(true, fenetre);

        fenetre.setNewJPanel( recherchePersonne.getJPanel() );
        bouttonAjouterCarte = new JButton("Créer");
        panelBouttonAjouterCarte = new JPanel();

        panelBouttonAjouterCarte.add(bouttonAjouterCarte);
        bouttonAjouterCarte.addActionListener(new actionBouttonAjouterCarte());

        fenetre.addJPanel(panelBouttonAjouterCarte,"");

    }

    class actionBouttonAjouterCarte implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            TableCarteLeoDAO tableCarteLeoDAO = new TableCarteLeoDAO();
            System.out.println("Appuie sur le boutton Créer carte Léo");
            tableCarteLeoDAO.ajouter( recherchePersonne.getIdPersonneSelectionne());

        }
    }
}
