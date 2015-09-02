/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.tache.métier;

import java.util.Objects;

/**
 *
 * @author Tony
 */
public class Valeur {
    private String val;

    public Valeur(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.val);
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
        final Valeur other = (Valeur) obj;
        if (!Objects.equals(this.val, other.val)) {
            return false;
        }
        return true;
    }
    
    public String toString(){
        return this.val.toString();
    }
}
