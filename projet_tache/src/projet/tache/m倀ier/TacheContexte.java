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
public class TacheContexte extends Tache{
    private Contexte cont;

    public TacheContexte(String nomTache, Contexte contexte) {
        super(nomTache);
        this.cont=contexte;        
    }
    public TacheContexte(String nomTache){
        super(nomTache);
    }

    public void setCont(Contexte cont) {
        this.cont = cont;
    }
    public void removeCont(){
        this.cont=null;
    }
    public Contexte getCont() {
        if(cont!=null){
        return cont;}else{
        return null;
        }
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.cont);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
    if(super.equals(obj)){
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TacheContexte other = (TacheContexte) obj;
        if(this.cont==null&&other.cont==null){
            return true;
        }
        if (!Objects.equals(this.cont, other.cont)) {
            return false;
        }
        return true;
    }else{
            return false;
        }
    }  
    
}
