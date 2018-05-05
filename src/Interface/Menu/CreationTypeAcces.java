package Interface.Menu;

import DAO.TableTypeAccesDAO;
import Interface.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreationTypeAcces {

    private Fenetre fenetre;
    private JButton bouttonAjouterTypeAcces;
    private JTextField champNomTypeAcces;

    private JPanel conteneur;

    public CreationTypeAcces(Fenetre fenetre){
        this.fenetre = fenetre;

        conteneur = new JPanel();
        bouttonAjouterTypeAcces = new JButton("Ajouter");
        champNomTypeAcces = new JTextField("");

        bouttonAjouterTypeAcces.addActionListener(new actionBouttonAjouterTypeAcces());

        champNomTypeAcces.setPreferredSize(new Dimension(150,50));



        conteneur.add(champNomTypeAcces);
        conteneur.add(bouttonAjouterTypeAcces);

        fenetre.setNewJPanel(conteneur);

    }

    class actionBouttonAjouterTypeAcces implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            TableTypeAccesDAO tableTypeAccesDAO = new TableTypeAccesDAO();

            tableTypeAccesDAO.ajout( champNomTypeAcces.getText() );
        }
    }
}
