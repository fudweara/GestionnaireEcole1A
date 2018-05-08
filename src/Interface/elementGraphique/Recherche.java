package Interface.elementGraphique;

import Interface.Fenetre;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Interface d'un champ de recherche
 */
class Recherche extends JPanel implements ListSelectionListener {

    final Fenetre fenetre;

    final JTextField champDeRecherche;
    final JButton bouttonRechercher;

    JList<String> jListe;
    DefaultListModel<String> listeModele;


    /**
     * Constructeur de l'interface d'une zone de recherche
     *
     * @param fenetre Fenetre qui va contenir le champ
     */
    Recherche(Fenetre fenetre){

        this.fenetre = fenetre;

        bouttonRechercher = new JButton("Rechercher");
        champDeRecherche = new JTextField();
    }


    /**
     * Crée l'interface pour la recherche
     */
    void creationInterface(){

        setPreferredSize(new Dimension(300,300));
        setBackground(Color.lightGray);


        // On dimensionne la barre de recherche
        champDeRecherche.setPreferredSize(new Dimension(249, 30));
        champDeRecherche.setForeground(Color.BLACK);

        //Creation de la liste et paramètrage.
        listeModele = new DefaultListModel<>();
        jListe = new JList<>(listeModele);
        jListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListe.addListSelectionListener(this);
        jListe.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(jListe);
        listScrollPane.setPreferredSize(new Dimension(300, 230));


        //Ajout des éléments à la JFrame
        add(champDeRecherche,BorderLayout.PAGE_START);
        add(bouttonRechercher,BorderLayout.PAGE_START);
        add(listScrollPane, BorderLayout.CENTER);
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

}
