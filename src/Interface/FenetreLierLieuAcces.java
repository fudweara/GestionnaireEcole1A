package Interface;

import DAO.TableAccesDAO;
import DAO.TableLieuDAO;
import DAO.TableTypeAccesDAO;
import objetStockage.Lieu;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;


public class FenetreLierLieuAcces extends JFrame implements ListSelectionListener{

    private JPanel panel;
    private JList listeAcces;
    private DefaultListModel listeModele;

    private JButton buttonSupprimerSelection;
    private JButton bouttonAjouter;
    private JComboBox listeDeroulanteTypeAcces;

    private TableTypeAccesDAO tableTypeAccesDAO;
    private TableLieuDAO tableLieuDAO;
    private TableAccesDAO tableAccesDAO;
    private Lieu lieu;

    public FenetreLierLieuAcces(Lieu lieu){

        this.lieu = new Lieu();
        this.lieu=lieu;

        this.setTitle("Définir les accès du lieu");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        panel = new JPanel( new BorderLayout() );


        listeModele = new DefaultListModel();

        tableLieuDAO = new TableLieuDAO();
        tableAccesDAO = new TableAccesDAO();
        tableTypeAccesDAO = new TableTypeAccesDAO();

        lieu.setIDLieu( tableLieuDAO.getID( lieu ));
        lieu.setIDlisteAcces( tableAccesDAO.recupererListeAcces( lieu.getIDLieu()));

        for(int i = 0 ; i<lieu.getListeAcces().size() ; i++ ){
            listeModele.addElement( tableTypeAccesDAO.nomTypeEmplacement( lieu.getListeAcces().get(i) ) );

        }

        listeAcces = new JList(listeModele);


        //Creation de la liste et paramètrage.

        listeAcces.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listeAcces.setSelectedIndex(0);
        listeAcces.addListSelectionListener(this);
        listeAcces.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(listeAcces);

        bouttonAjouter = new JButton("Ajouter");
        EcouteBouttonAjouter ecouteBouttonAjouter = new EcouteBouttonAjouter();
        bouttonAjouter.setActionCommand("Ajouter");
        bouttonAjouter.addActionListener(ecouteBouttonAjouter);
        if(lieu.getListeAcces().size() == lieu.getNombreAcces() && lieu.getListeAcces().size() != 0 ){
            bouttonAjouter.setEnabled(false);
        }

        buttonSupprimerSelection = new JButton("Supprimer sélection");
        buttonSupprimerSelection.setActionCommand("Supprimer sélection");
        buttonSupprimerSelection.addActionListener(new actionBouttonSupprimer());
        if(lieu.getListeAcces().size() == 0){
            buttonSupprimerSelection.setEnabled(false);
        }

        listeDeroulanteTypeAcces  = new JComboBox();
        listeDeroulanteTypeAcces.setPreferredSize(new Dimension(100, 20));
        tableTypeAccesDAO = new TableTypeAccesDAO();
        ArrayList<String> nomType = tableTypeAccesDAO.recupereToutlesTypes();
        for(int i=0 ; i<nomType.size() ; i++){
            listeDeroulanteTypeAcces.addItem(nomType.get(i));
        }


        //Créer le JPanel qui regroupe les bouttons et le champ
        JPanel bouttonPanel = new JPanel();
        bouttonPanel.setLayout(new BoxLayout(bouttonPanel, BoxLayout.LINE_AXIS));

        bouttonPanel.add(buttonSupprimerSelection);
        bouttonPanel.add(Box.createHorizontalStrut(7));
        bouttonPanel.add(new JSeparator(SwingConstants.VERTICAL));
        bouttonPanel.add(Box.createHorizontalStrut(7));
        bouttonPanel.add(listeDeroulanteTypeAcces);
        bouttonPanel.add(bouttonAjouter);

        bouttonPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(bouttonPanel, BorderLayout.PAGE_END);

        this.setContentPane(panel);
        this.setVisible(false);

    }

    class actionBouttonSupprimer implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            int index = listeAcces.getSelectedIndex();
            listeModele.remove(index);
            lieu.supprimerAcces(index);

            int tailleListe = listeModele.getSize();

            if (tailleListe == 0) { // Si la taille de l'index qui reste est nul => On disable la suppression
                buttonSupprimerSelection.setEnabled(false);

            } else { // si il reste des selections possibles
                if (index == listeModele.getSize()) {
                    index--;
                }
                listeAcces.setSelectedIndex(index);
                listeAcces.ensureIndexIsVisible(index); // permet d'éviter les problèmes d'affichage lors qu'il y a possibilité de scroll
            }
            if( listeAcces.getModel().getSize() >= lieu.getNombreAcces()){
                bouttonAjouter.setEnabled(false);
            }
            else{
                bouttonAjouter.setEnabled(true);
            }
        }
    }

    class EcouteBouttonAjouter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            String nomAjout = (String) listeDeroulanteTypeAcces.getSelectedItem();

            listeModele.addElement(nomAjout);

            lieu.ajoutAcces(tableTypeAccesDAO.getID(nomAjout));

            int index = listeAcces.getSelectedIndex();
            index++;
            listeAcces.setSelectedIndex(index);
            listeAcces.ensureIndexIsVisible(index); // On selectionne le nouvel item ajouté

            if( listeAcces.getModel().getSize() >=lieu.getNombreAcces()){
                bouttonAjouter.setEnabled(false);
            }

        }


    }

    public void valueChanged(ListSelectionEvent e) {
        if ( !e.getValueIsAdjusting() ) {

            if (listeAcces.getSelectedIndex() == -1) {
                //-1 => Pas de selection : on désactive le boutton
                buttonSupprimerSelection.setEnabled(false);

            } else {
                buttonSupprimerSelection.setEnabled(true);
            }
        }
    }

    public void fenetreVisible(){
        this.setContentPane(panel);
        this.setVisible(true);

        if(listeAcces.getModel().getSize()>=lieu.getNombreAcces()){
            bouttonAjouter.setEnabled(false);
        }
    }

    public int nombreAccesDefini(){
        return listeAcces.getModel().getSize();
    }

}
