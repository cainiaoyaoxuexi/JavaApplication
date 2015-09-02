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
public class Attribut {
    //Attributs
    private String libelle;
    private ArrayList<Valeur> lstValeur;

    public Attribut(String libelle) {
        this.libelle = libelle;
        this.lstValeur=new ArrayList<>();
    }

    
    public String getLibelle() {
        return libelle;
    }      

    public ArrayList<Valeur> getLstValeur() {
        return lstValeur;
    }
//Ajouter une valeur dans la liste de valeur
    public void ajouterValeur(Valeur valeur) {
        if(!this.lstValeur.contains(valeur)){
            this.lstValeur.add(valeur);
        }
    }    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.libelle);
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
        final Attribut other = (Attribut) obj;
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        return true;
    }
    
    public String toString(){
        return this.libelle.toString();
    }
    
}
