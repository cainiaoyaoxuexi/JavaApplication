/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.tache.métier;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Tony
 */
public class Contexte {
    //Lien
    private ArrayList<Attribut> lstAttr; 
    //Contstructeur
    public Contexte(){
        this.lstAttr=new ArrayList<>();
    }

    public ArrayList<Attribut> getLstAttr() {
        return lstAttr;
    }
    //Ajouter un attribut dans la liste de la contexte
    public void ajouterAttr(Attribut attr) {
        if(!lstAttr.contains(attr)){
            this.lstAttr.add(attr);
        }
    }   
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.lstAttr);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contexte other = (Contexte) obj;
        if (!Objects.equals(this.lstAttr, other.lstAttr)) {
            return false;
        }
        return true;
    }
    
    public String toString(){
        return this.lstAttr.toString();
    }
    
    
    
    
}
