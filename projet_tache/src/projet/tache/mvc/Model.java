/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projet.tache.mvc;

import projet.tache.NoyauFonctionnel.NoyauFonctionnel;
import java.util.ArrayList;
import projet.tache.métier.Transition;

/**
 *
 * @author 21103205
 */
public class Model extends NoyauFonctionnel{
    private ArrayList<View> lstViews;
    public Model(){
        this.lstViews=new ArrayList<>();
    }
    public void addView(View view){
        this.lstViews.add(view);
    }

    public ArrayList<View> getLstViews() {
        return lstViews;
    }
    
    public void notifyView(){
        for(View view:this.lstViews){
            view.notify(this);
        }
    }
    
//gérer la redo d'ajouter une période simple
    @Override
    public void ajouterPeriodeS(String libellé){
        super.ajouterPeriodeS(libellé);
        notifyView();
    }

//gérer la undo d'ajouter une période simple et contexte
    @Override
    public void supprimerPeriode(String libellé){
        super.supprimerPeriode(libellé);
        notifyView();
    }

//gérer la redo d'ajouter une période contexte
    @Override
    public void ajouterPeriodeContexte(String libellé){
        super.ajouterPeriodeContexte(libellé);
        notifyView();
    }

//gérer la redo d'ajouter une période simple
    @Override
    public void ajouterTacheS(String periode,String nomTache){
        super.ajouterTacheS(periode, nomTache);
        notifyView();       
    }

//gérer la undo d'ajouter une période simple
    @Override
    public void supprimerTacheS(String periode,String nomTache){
        super.supprimerTacheS(periode,nomTache);
        notifyView();
    }
    
//gérer la redo d'ajouter une période simple
    @Override
    public void ajouterTacheC(String periode,String nomTache){
        super.ajouterTacheC(periode, nomTache);
        notifyView();       
    }

//gérer la undo d'ajouter une période simple
    @Override
    public void supprimerTacheC(String periode,String nomTache){
        super.supprimerTacheC(periode,nomTache);
        notifyView();
    }

    @Override
    public void ModifFre(String period, Transition trs, Integer fre) {
        super.ModifFre(period, trs, fre); 
        notifyView();
    }

//gérer la redo d'ajouter de(s) valeur(s) de(s) attribut(s) pour une tâche d'une période
    @Override
    public void EnreTachelstMap(String Peri, String TacheC, ArrayList<String> lstAttri, ArrayList<String> lstVal) {
        super.EnreTachelstMap(Peri, TacheC, lstAttri, lstVal); 
        notifyView();
    }

//gérer la undo d'ajouter de(s) valeur(s) de(s) attribut(s) pour une tâche d'une période
    @Override
    public void SupprTachelstMap(String Peri, String TacheC, ArrayList<String> lstAttri, ArrayList<String> lstVal) {
        super.SupprTachelstMap(Peri, TacheC, lstAttri, lstVal);
        notifyView();
    }
    
    
    
    
    
}
