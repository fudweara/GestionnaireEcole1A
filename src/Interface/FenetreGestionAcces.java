package Interface;

import DAO.TableTypeAccesDAO;
import objetStockage.Lieu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Feetre pour l'ajout d'accès qui sont liés à des lieux
 */
public class FenetreGestionAcces extends JFrame implements ListSelectionListener{

    private final JPanel panel;
    private final JList<String> listeAcces;
    private final DefaultListModel<String> listeModele;

    private final JButton boutonSupprimerSelection;
    private final JButton boutonAjouter;
    private final JComboBox<String> listeDeroulanteTypeAcces;

    private final TableTypeAccesDAO tableTypeAccesDAO;
    private Lieu lieu;


    /**
     * Constructeur pour la fenetre pour gérer les accés
     *
     * @param lieu Lieu associé aux accès
     */
    public FenetreGestionAcces(Lieu lieu){

        this.lieu = new Lieu();
        this.lieu=lieu;


        //Paramètres de la fenetre
        setTitle("Définir les accès du lieu");
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);


        // Connexion DAO
        tableTypeAccesDAO = new TableTypeAccesDAO();

        panel = new JPanel( new BorderLayout() );
        listeModele = new DefaultListModel<>();


        // On met les accès du lieu (si il y en a) dans la liste
        for(int i = 0 ; i<lieu.getListeAcces().size() ; i++ ){
            listeModele.addElement( tableTypeAccesDAO.nomTypeAcces( lieu.getListeAcces().get(i) ) );
        }
        listeAcces = new JList<>(listeModele);


        //Creation de la liste et paramètrage.
        listeAcces.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listeAcces.setSelectedIndex(0);
        listeAcces.addListSelectionListener(this);
        listeAcces.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(listeAcces);


        // Création du bouton Ajouter
        boutonAjouter = new JButton("Ajouter");
        if(lieu.getListeAcces().size() == lieu.getNombreAcces() && lieu.getListeAcces().size() != 0 ){
            boutonAjouter.setEnabled(false);
        }


        // Création du bouton Supprimer
        boutonSupprimerSelection = new JButton("Supprimer sélection");
        if(lieu.getListeAcces().size() == 0){
            boutonSupprimerSelection.setEnabled(false);
        }


        // Ajout de la liste qui contient les type d'accès que l'on peut ajouter
        listeDeroulanteTypeAcces  = new JComboBox<>();
        listeDeroulanteTypeAcces.setPreferredSize(new Dimension(100, 20));

        for (String aNomType : tableTypeAccesDAO.recupereToutlesTypes()) {
            listeDeroulanteTypeAcces.addItem(aNomType);
        }


        //Créer le JPanel qui regroupe les boutons et le champ
        JPanel bouttonPanel = new JPanel();
        bouttonPanel.setLayout(new BoxLayout(bouttonPanel, BoxLayout.LINE_AXIS));

        bouttonPanel.add(boutonSupprimerSelection);
        bouttonPanel.add(Box.createHorizontalStrut(7));
        bouttonPanel.add(new JSeparator(SwingConstants.VERTICAL));
        bouttonPanel.add(Box.createHorizontalStrut(7));
        bouttonPanel.add(listeDeroulanteTypeAcces);
        bouttonPanel.add(boutonAjouter);
        bouttonPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));


        // Ajout des conposants  sur le panel qui est affiché dans la JFrame
        panel.add(listScrollPane, BorderLayout.CENTER);
        panel.add(bouttonPanel, BorderLayout.PAGE_END);


        // Fonctions d'écoute
        boutonAjouter.addActionListener(new EcouteBoutonAjouter());
        boutonSupprimerSelection.addActionListener(new actionBoutonSupprimer());

        setContentPane(panel);
        setVisible(false);
    }


    /**
     * Ecoute boutton supprimer
     */
    class actionBoutonSupprimer implements ActionListener {

        /**
         * Supprime l'accès selectionné dans la liste et désactive le boutton supprimer s'il n'y a plus d'accès dans la liste
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {

            int index = listeAcces.getSelectedIndex();
            listeModele.remove(index);
            lieu.supprimerAcces(index);

            int tailleListe = listeModele.getSize();

            if (tailleListe == 0) { // Si la taille de l'index qui reste est nul => On disable la suppression
                boutonSupprimerSelection.setEnabled(false);

            } else { // si il reste des selections possibles
                if (index == listeModele.getSize()) {
                    index--;
                }
                listeAcces.setSelectedIndex(index);
                listeAcces.ensureIndexIsVisible(index); // permet d'éviter les problèmes d'affichage lors qu'il y a possibilité de scroll
            }
            if( listeAcces.getModel().getSize() >= lieu.getNombreAcces()){
                boutonAjouter.setEnabled(false);
            }
            else{
                boutonAjouter.setEnabled(true);
            }
        }
    }


    /**
     * Ecoute bouton Ajouter
     */
    class EcouteBoutonAjouter implements ActionListener {

        /**
         * Ajoute l'accès selectionné dans la liste déroulante dans la liste et désative le boutton Ajouter si le nombre d'accès est égale au nombre d'accès défini
         *
         * @param e Evenement
         */
        public void actionPerformed(ActionEvent e) {
            String nomAjout = (String) listeDeroulanteTypeAcces.getSelectedItem();

            listeModele.addElement(nomAjout);

            lieu.ajoutAcces(tableTypeAccesDAO.getID(nomAjout));

            int index = listeAcces.getSelectedIndex();
            index++;
            listeAcces.setSelectedIndex(index);
            listeAcces.ensureIndexIsVisible(index); // On selectionne le nouvel item ajouté

            if( listeAcces.getModel().getSize() >=lieu.getNombreAcces()){
                boutonAjouter.setEnabled(false);
            }
        }
    }


    /**
     * Desactive le boutton supprimer si il n'y a plus d'élements dans la liste lors de qu'il y un changement dans la liste. Il peut également le réactiver
     * @param e Evenement
     */
    public void valueChanged(ListSelectionEvent e) {
        if ( !e.getValueIsAdjusting() ) {

            if (listeAcces.getSelectedIndex() == -1) {
                //-1 => Pas de selection : on désactive le boutton
                boutonSupprimerSelection.setEnabled(false);

            } else {
                boutonSupprimerSelection.setEnabled(true);
            }
        }
    }


    /**
     * Met la fenetre gestion Acces visible
     */
    public void fenetreVisible(){
        setContentPane(panel);
        setVisible(true);

        if(listeAcces.getModel().getSize()>=lieu.getNombreAcces()){
            boutonAjouter.setEnabled(false);
        }
    }


    /**
     * Retourne le nombre d'accès qui a été selectionné dans le fenetre
     *
     * @return Nombre d'accès définis
     */
    public int nombreAccesDefini(){
        return listeAcces.getModel().getSize();
    }
}
