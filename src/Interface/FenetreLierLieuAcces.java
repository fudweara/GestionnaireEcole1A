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

    private final JPanel panel;
    private final JList listeAcces;
    private final DefaultListModel listeModele;

    private final JButton buttonSupprimerSelection;
    private final JButton bouttonAjouter;
    private final JComboBox listeDeroulanteTypeAcces;

    private TableTypeAccesDAO tableTypeAccesDAO;
    private final TableAccesDAO tableAccesDAO;
    private Lieu lieu;

    public FenetreLierLieuAcces(Lieu lieu){

        this.lieu = new Lieu();
        this.lieu=lieu;


        //Paramètres de la fenetre
        setTitle("Définir les accès du lieu");
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);


        // Connexion DAO
        tableAccesDAO = new TableAccesDAO();
        tableTypeAccesDAO = new TableTypeAccesDAO();

        panel = new JPanel( new BorderLayout() );
        listeModele = new DefaultListModel();


        // On met les accès du lieu (si il y en a) dans la liste
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


        // Création du boutton Ajouter
        bouttonAjouter = new JButton("Ajouter");
        if(lieu.getListeAcces().size() == lieu.getNombreAcces() && lieu.getListeAcces().size() != 0 ){
            bouttonAjouter.setEnabled(false);
        }


        // Création du boutton Supprimer
        buttonSupprimerSelection = new JButton("Supprimer sélection");
        if(lieu.getListeAcces().size() == 0){
            buttonSupprimerSelection.setEnabled(false);
        }


        // Ajout de la liste qui contient les type d'accès que l'on peut ajouter
        listeDeroulanteTypeAcces  = new JComboBox();
        listeDeroulanteTypeAcces.setPreferredSize(new Dimension(100, 20));
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


        // Ajout des conposants  sur le panel qui est affiché dans la JFrame
        panel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(bouttonPanel, BorderLayout.PAGE_END);


        // Fonctions d'écoute
        bouttonAjouter.addActionListener(new EcouteBouttonAjouter());
        buttonSupprimerSelection.addActionListener(new actionBouttonSupprimer());

        setContentPane(panel);
        setVisible(false);
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
        setContentPane(panel);
        setVisible(true);

        if(listeAcces.getModel().getSize()>=lieu.getNombreAcces()){
            bouttonAjouter.setEnabled(false);
        }
    }

    public int nombreAccesDefini(){
        return listeAcces.getModel().getSize();
    }
}
