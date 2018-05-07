package Interface.elementGraphique;

import DAO.TableLieuDAO;
import Interface.Fenetre;
import objetStockage.Lieu;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RechercheLieu extends JPanel implements ListSelectionListener {

    private Fenetre fenetre;
    private FormulaireLieu formulaireLieu;

    private JTextField champDeRechercheNom;
    private JButton boutton;
    private JPanel JPanelRechercheEtBOuton;

    private JList jlisteLieu;
    private DefaultListModel listeModele;

    private ArrayList<Lieu> listeLieu;

    private TableLieuDAO tableLieuDAO = new TableLieuDAO();


    public RechercheLieu(Fenetre fenetre, FormulaireLieu formulaireLieu){
        this.fenetre = fenetre;
        this.formulaireLieu = formulaireLieu;

        jlisteLieu = new JList();
        boutton = new JButton("Ok");
        JPanelRechercheEtBOuton = new JPanel();
        champDeRechercheNom = new JTextField();
        listeLieu = new ArrayList<>();

        creationInterface();

        boutton.addActionListener(new actionBoutton() );

    }
    private void creationInterface(){
        setPreferredSize(new Dimension(300,300));

        champDeRechercheNom.setPreferredSize(new Dimension(249, 30));
        champDeRechercheNom.setForeground(Color.BLACK);
        add(champDeRechercheNom,BorderLayout.PAGE_START);

        JPanelRechercheEtBOuton.setPreferredSize(new Dimension(249, 30));
        JPanelRechercheEtBOuton.add(boutton,BorderLayout.CENTER);
        add(JPanelRechercheEtBOuton,BorderLayout.PAGE_START);

        //Creation de la liste et paramètrage.

        listeModele = new DefaultListModel();
        jlisteLieu = new JList(listeModele);
        jlisteLieu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlisteLieu.addListSelectionListener(this);
        jlisteLieu.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(jlisteLieu);
        listScrollPane.setPreferredSize(new Dimension(300, 240));
        add(listScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        formulaireLieu.setAtributs(tableLieuDAO.getLieu( jlisteLieu.getSelectedValue().toString()));

    }

    class actionBoutton implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            System.out.println("Appuie sur boutton recherche");

            removeAll();
            creationInterface();

            listeLieu = tableLieuDAO.rechercherLieu( champDeRechercheNom.getText() );

            for(int i=0 ; i< listeLieu.size() ; i++){
                listeModele.addElement(listeLieu.get(i).getEmplacement());
            }

            fenetre.updateAffichage();

        }
    }
}
