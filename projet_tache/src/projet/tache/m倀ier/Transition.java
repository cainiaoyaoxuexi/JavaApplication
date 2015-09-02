/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projet.tache.métier;

import java.util.Objects;

/**
 *
 * @author 21103205
 */
public class Transition {
    
    private Tache tache;
    private Tache TacheSuivante;

    public Transition(Tache tache, Tache tacheSuivante) {
        if (tache.getNomTache()!=tacheSuivante.getNomTache()){
        this.tache = tache;
        this.TacheSuivante = tacheSuivante;
        }else{
        System.out.println("Tâche doit être différente de tâche suivante");
        }
    }  

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.tache);
        hash = 59 * hash + Objects.hashCode(this.TacheSuivante);
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
        final Transition other = (Transition) obj;
        if (!Objects.equals(this.tache, other.tache)) {
            return false;
        }
        if (!Objects.equals(this.TacheSuivante, other.TacheSuivante)) {
            return false;
        }
        return true;
    }
    
    public Tache getTache() {
        return tache;
    }    

    public Tache getTacheSuivante() {
        return TacheSuivante;
    }
    
    public String toString(){        
        return this.tache+"-->"+this.TacheSuivante;
    }   
}
