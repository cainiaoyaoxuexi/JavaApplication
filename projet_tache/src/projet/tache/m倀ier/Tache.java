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
public class Tache {
    private String nomTache;
    public Tache(String nomTache){
        this.nomTache=nomTache;
    }

    public String getNomTache() {
        return nomTache;
    }

    @Override
    public String toString() {
        return this.nomTache;
    }
   
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tache other = (Tache) obj;
        if (!Objects.equals(this.nomTache, other.nomTache)) {
            return false;
        }
        return true;
    }
    
}
