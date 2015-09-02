/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projet.tache.métier;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 *
 * @author 21103205
 */
public class Periode {
    //Attribut de periode
    private String periode;
    //Lien
    private Map<Transition,Integer> mapFr; 
    //Constructeur d'une période
    public Periode(String periode){
        this.periode=periode;
        this.mapFr=new LinkedHashMap();       
    }
     public void iniMapFre(){
        this.mapFr=new LinkedHashMap();
}
    public String getPeriode() {
        return this.periode;
    }
    public Map<Transition, Integer> getMapFr() {
        return mapFr;
    }     
    public boolean containsKey(Transition t){
        boolean trouve=false;
        for(Transition tr: this.mapFr.keySet()){
            if(tr.equals(t)){
                trouve=true;
            }
        }
        return trouve;
    }
    //enregistrer une transition avec la fréquence 0 par défalut
    public void enregistrer(Transition t){
       
        if(!this.containsKey(t)){
            this.mapFr.put(t, 0);
        }else{
            //System.out.println("Cette transition "+t+" est déjà enregistrée dans "+this.getPeriode());
        }
    }
    // Ajouter ou modifier la fréquence d'une transition dans cette période
    public void modifierFre(Transition t, int Frequence){        
        this.mapFr.put(t, Frequence);    
    }    
    //Déterminer la tâche de départ dans cette période
    public Tache TacheDepart(){
        Tache tache=null;
        int total=0;
        //on créer une map pour stocker les taches et les frequence total des taches
        Map<Tache,Integer> MapTotal=new HashMap();        
        for(Entry<Transition,Integer> entry:this.mapFr.entrySet()){
            int sum=0;
            for(Transition transition:this.mapFr.keySet()){
                if(entry.getKey().getTacheSuivante().equals(transition.getTacheSuivante())){
                    sum+=this.mapFr.get(transition);
                }
            }  
            //ici on supprime les même taches dans le MapTotal
            if(!MapTotal.containsKey(entry.getKey().getTacheSuivante())){
                 MapTotal.put(entry.getKey().getTacheSuivante(), sum);                 
            }
        }  
        //ensuite on vas chercher la tâche qui a la total fréquece la minimume
        for(Entry<Tache,Integer> entry:MapTotal.entrySet()){           
            //on s'occupe le premier entry dans le MapTotal;
            total=entry.getValue();
            tache=entry.getKey();                        
            for(Tache tachesuiv:MapTotal.keySet()){                
                if (MapTotal.get(tachesuiv)<=total){
                    tache=tachesuiv;
                    total=MapTotal.get(tachesuiv);                    
                }
            }            
            break; //quand on trouve le plus moins on sortir la boucle;           
        }
        //retour la tâche que on a trouvé comme la tâche départ de cette période;
        return tache;     
    }
    //Trouver la transition d'une tâche déterminée
    public Transition TrouveTransition(Tache t){
        Transition ts=null;
        //on créer une map pour stocker les transition qui ont les même tache départ d'une tache donnée
        Map<Transition,Integer> MapMax=new HashMap();
        for(Transition transition:this.mapFr.keySet()){
            if(transition.getTache().equals(t)){               
                MapMax.put(transition, this.mapFr.get(transition));       
            }            
        } 
        //ensuite on vas chercher la transition qui a la valeur la plus grande
        for(Entry<Transition,Integer> entry:MapMax.entrySet()){
            //on s'occupe le premier entry dans le MapMax;
            int fre=entry.getValue();
            ts=entry.getKey();
            for(Transition tr:MapMax.keySet()){
                if (fre<MapMax.get(tr)){
                    ts=tr;
                    fre=MapMax.get(tr);
                }
            }
            break;//quand on trouve le plus grande on sortir la boucle;
        }  
        //retour la transition que on a trouvé
        return ts;
    }
    //Trouver la tache de la tache déterminée
    public void TrouveTacheSuiv(Tache t){
        System.out.println("La tache suivante de "+t+" est: "+this.TrouveTransition(t).getTacheSuivante());
    }
    
    //Extraire le processus type pour un tableau de suivi.
    public String Extraire(){
        //le processus commence par la tâche départ 
        Tache TacheDepart=this.TacheDepart(); 
        Transition ts = this.TrouveTransition(TacheDepart); 
        String processus=TacheDepart+"";  
        //tant que le processus contient pas la tâche suivante que on a trouvé, on l'ajoute dans ce processus
        while(!processus.contains(this.TrouveTransition(TacheDepart).getTacheSuivante().getNomTache()+"-->")){
            processus=processus+"-->"+ts.getTacheSuivante();            
            TacheDepart=ts.getTacheSuivante();
            ts=this.TrouveTransition(TacheDepart);
        }       
        return processus;
    }    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.periode);
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
        final Periode other = (Periode) obj;
        if (!Objects.equals(this.periode, other.periode)) {
            return false;
        }
        return true;
    }
    
    
    @Override
    public String toString() {
        return this.mapFr.toString();
    }
    
}
