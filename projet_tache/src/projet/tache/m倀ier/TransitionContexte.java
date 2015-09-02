/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.tache.métier;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 *
 * @author 乔
 */

public class TransitionContexte extends Transition{
    
    private Map<Attribut,Valeur> mapAttr;
    public TransitionContexte(TacheContexte tache, TacheContexte tacheSuivante) {
        super(tache, tacheSuivante);
        this.mapAttr=new HashMap<>();        
    }

    public void ajouterValeur(Attribut Attr, String val){
        if(!this.mapAttr.containsKey(Attr)&((TacheContexte)this.getTache()).getCont().getLstAttr().contains(Attr)){
            Valeur valeur=new Valeur(val);
            int i=((TacheContexte)this.getTache()).getCont().getLstAttr().indexOf(Attr);
            ((TacheContexte)this.getTache()).getCont().getLstAttr().get(i).ajouterValeur(valeur);
            this.mapAttr.put(Attr,valeur);
        }
    }
    @Override
    public int hashCode() {
    return super.hashCode()+this.mapAttr.hashCode();
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
        final TransitionContexte other = (TransitionContexte) obj;
        if (!Objects.equals(this.mapAttr, other.mapAttr)) {
            return false;
        }
        return true;
        }else{
            return false;
        }
    }
    
    
    
    
    public String toString(){
        if(!this.mapAttr.isEmpty()){
            return super.getTache()+this.mapAttr.toString()+"-->"+super.getTacheSuivante();
        }else{
         return super.getTache()+"-->"+super.getTacheSuivante();
        }
    }
     
    public Map<Attribut, Valeur> getMapAttr() {
        return mapAttr;
    } 
    public void setMap(Map<Attribut, Valeur> map){
        this.mapAttr=map;
    }
}

