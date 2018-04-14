package Interface.Menu;

import Interface.Fenetre;
import Interface.elementGraphique.FormulairePersonne;
import Interface.elementGraphique.RecherchePersonne;


public class ModifierPersonne {

    private Fenetre fenetre;
    RecherchePersonne recherchePersonne;
    FormulairePersonne formulairePersonne;

    public ModifierPersonne( Fenetre fenetre ){

        this.fenetre=fenetre;
        formulairePersonne = new FormulairePersonne();
        recherchePersonne = new RecherchePersonne(fenetre,formulairePersonne);

        fenetre.setNewJPanel( recherchePersonne.getJPanel() );
        fenetre.addJPanel(formulairePersonne.getJPanel(),"");


    }

}
