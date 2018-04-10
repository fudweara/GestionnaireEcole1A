package Interface.Menu;

import Interface.Fenetre;
import Interface.elementGraphique.RecherchePersonne;


public class ModifierPersonne {

    private Fenetre fenetre;
    RecherchePersonne recherchePersonne;

    public ModifierPersonne( Fenetre fenetre ){

        this.fenetre=fenetre;
        recherchePersonne = new RecherchePersonne(fenetre);

        fenetre.setNewJPanel( recherchePersonne.getJPanel() );


    }

}
