package Interface;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{

    private final JPanel fenetre;
    private BarreDeNavigation barreDeNavigation;


    /**
     * Fenetre de taille 900 par 700  qui possède une barre de navigation
     */
    public Fenetre(){

        fenetre = new JPanel();
        barreDeNavigation = new BarreDeNavigation(this);

        this.setLayout(new BorderLayout());
        this.setJMenuBar( barreDeNavigation );
        this.setTitle("Gestionnaire ESIGELEC");
        this.setSize(900, 700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(fenetre);
        this.setVisible(true);
    }

    /**
     * Supprime l'ancien JPanel affiché et le remplace par le JPanel en paramètre
     * @param container (JPanel)
     */
    public void setNewJPanel(Component container){

        fenetre.removeAll();
        fenetre.add(container, BorderLayout.CENTER);

        this.setContentPane(fenetre);
        this.setVisible(true);
    }

    public void updateWIndows(){
        this.setContentPane(fenetre);
        this.setVisible(true);
    }



    /**
     * Ajoute un JPanel sur la fenetre sur la même colonnne que les autres pannels
     *
     * @param jPanel (JPanel)
     */
    public void addJPanel(JPanel jPanel, String typeEmplacement){

        if(typeEmplacement.compareTo("")==0){
            fenetre.add(jPanel);
        }
        else{
            fenetre.add(jPanel, typeEmplacement);
        }

        this.setContentPane(fenetre);
        this.setVisible(true);
        
    }

}
