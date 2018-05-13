package interfaces;

import javax.swing.*;

/**
 * Objet qui représente la  fenetre du programme
 */
public class Fenetre extends JFrame{

    private final JPanel fenetre;


    /**
     * Fenetre de taille 700 par 400  qui possède une barre de navigation
     */
    public Fenetre(){

        fenetre = new JPanel();
        BarreDeNavigation barreDeNavigation = new BarreDeNavigation(this);
        setJMenuBar(barreDeNavigation);
        setTitle("Gestionnaire ESIGELEC");
        setSize(700, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(fenetre);
        setVisible(true);
    }


    /**
     * Met à jour l'affichage
     */
    public void updateAffichage(){
        this.setContentPane(fenetre);
        this.setVisible(true);
    }

    /**
     * Recupère le JPanel qui est contenu dans la fenetre
     *
     * @return fenetre Fenetre du programme
     */
    public JPanel getFenetre() {
        return fenetre;
    }
}
