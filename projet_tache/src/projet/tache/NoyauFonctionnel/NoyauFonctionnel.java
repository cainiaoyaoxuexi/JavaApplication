/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projet.tache.NoyauFonctionnel;
import projet.tache.métier.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import projet.tache.métier.TacheContexte;
/**
 *
 * @author 21204707
 */
public class NoyauFonctionnel {  
    
//attributs privés
    private Map<Periode, ArrayList<Tache>> mapPeriod;  
    private Map<Attribut,Valeur> mapAttri;    
    private Map<TacheContexte,ArrayList<Map<Attribut,Valeur>>> mapTacheContexte; 
    private Map<Attribut,ArrayList<Valeur>> mapAttriTotale;
    private ArrayList<Valeur> lstval;
    
    public NoyauFonctionnel(){        
        mapPeriod = new LinkedHashMap<>();     
    }
    
    public Map<Periode, ArrayList<Tache>> getMapPeriod() {
        return mapPeriod;
    } 
    
//la méthode pour créer une période simple
    public void ajouterPeriodeS(String libellé){
        Periode ps = new Periode(libellé);        
        mapPeriod.put(ps, new ArrayList());       
    }   
    
//la méthode pour créer une période des tâches enricheées avec un contexte et initialise les maps relatives
    public void ajouterPeriodeContexte(String libellé){
        PeriodeContexte pc = new PeriodeContexte(libellé);
        mapPeriod.put(pc, new ArrayList());
        mapTacheContexte=new LinkedHashMap<>();
        lstval = new ArrayList();
        mapAttriTotale=new LinkedHashMap<>();
    }    

//Trouver le période par le nom période
    public Periode getPeri(String Libelle){
        Periode peri=null;
        for(Periode p:mapPeriod.keySet()){
            if(p.getPeriode().equals(Libelle)){
                peri=p;
            }
        }
        return peri;
    }

//Trouver la tâche par le nom de la tâche et le nom période
    public Tache getTacheSim(String NomT,String Peri){
        Tache ts=null;
        for (Tache t : this.getTache(Peri)) {
            if(t.getNomTache().equals(NomT)){
                ts=t;
            }
        }
        return ts;
    }

//Trouver le list des valeur d'attribut par son libelle
    public ArrayList<Valeur> getLstValeur(String attri){
        ArrayList<Valeur> lstVal=null;
        for(Attribut at:this.mapAttriTotale.keySet()){
            if(at.getLibelle().equals(attri)){
                lstVal=this.mapAttriTotale.get(at);
            }
        }
        return lstVal;
    }

//Obtenir le list des périodes simples 
    public ArrayList<Periode> getLstPeriodSimple(){
        ArrayList<Periode> lstPeriod=new ArrayList();
        for (Periode p : this.mapPeriod.keySet()) {
            if(p.getClass()==Periode.class){
                lstPeriod.add(p);
            }            
        }
        return lstPeriod;
    }

//Obtenir le list des périodes Contextes
    public ArrayList<PeriodeContexte> getLstPeriodContexte(){
         ArrayList<PeriodeContexte> lstPeriodCont=new ArrayList();
        for (Periode p : this.mapPeriod.keySet()) {
            if(p.getClass()==PeriodeContexte.class){
                lstPeriodCont.add((PeriodeContexte)p);
            }            
        }
        return lstPeriodCont;
    }

//le méthode pour supprimer une période par son libellé
    public void supprimerPeriode(String libellé){
        this.mapPeriod.remove(getPeri(libellé));
    }

//le méthode pour ajouter une tâche simple pour une période dans le mapPeriod 
    public void ajouterTacheS(String periode,String nomTache){
        Periode p=this.getPeri(periode);
        Tache t=new Tache(nomTache);
        this.mapPeriod.get(p).add(t);
        this.GenererMapPS(periode);//chaque fois quand on ajoute une tâche simple, appelle la procédure pour générer tous les transitions automatiquement entre les tâches!
    }

//le méthode pour supprimer une tâche contexte pour une période dans le mapPeriod 
    public void supprimerTacheS(String periode,String nomTache){
        Periode p=this.getPeri(periode);
        Tache t=new Tache(nomTache);
        this.mapPeriod.get(p).remove(t);
        this.GenererMapPS(periode);//chaque fois quand on supprime une tâche simple, appelle la procédure pour générer tous les transitions automatiquement entre les tâches!
    }
    
//le méthode pour ajouter une tâche contexte pour une période Contexte dans le mapPeriod 
    public void ajouterTacheC(String periode,String nomTache){
        PeriodeContexte p=(PeriodeContexte)this.getPeri(periode);
        TacheContexte tc=new TacheContexte(nomTache);        
        this.mapTacheContexte.put(tc, new ArrayList());
        this.mapPeriod.get(p).add(tc);
        this.GenererMapPC(periode);//chaque fois quand on ajoute une tâche contexte, appelle la procédure pour générer tous les transitions automatiquement entre les tâches contextes!        
    }
    
//le méthode pour supprimer une tâche contexte pour une période Contexte dans le mapPeriod
    public void supprimerTacheC(String periode,String nomTache){
        Periode p=this.getPeri(periode);
        TacheContexte tc=new TacheContexte(nomTache);
        this.mapPeriod.get(p).remove(tc); 
        this.mapTacheContexte.remove(tc);
        this.GenererMapPC(periode);//chaque fois quand on ajoute une tâche contexte, appelle la procédure pour générer tous les transitions automatiquement entre les tâches contextes!
    }

//Obtenir le list des tâches d'une période par son libellé
    public ArrayList<Tache> getTache(String periode){
        Periode p=this.getPeri(periode);
        return this.mapPeriod.get(p);
    }

//Le methode pour vérifier le nom de période, si le nom déjà utilisé, return false
    public boolean isNomPValide(String nomP) {
        if (nomP.trim().isEmpty()) {  //si le string est null ou contient que les spaces(vide)
            return false;
        }else{
            Periode ps = new Periode(nomP);
            PeriodeContexte pc = new PeriodeContexte(nomP);
            return !(getLstPeriodSimple().contains(ps) || getLstPeriodContexte().contains(pc));
        }
    }
//
    public String messagePeri(String nomP){
        if (nomP.trim().isEmpty()) {  //si le string est null ou contient que les spaces(vide)
            return "Saisissez une période.";
        }else{
            Periode ps = new Periode(nomP);
            PeriodeContexte pc = new PeriodeContexte(nomP);
            if(getLstPeriodSimple().contains(ps) || getLstPeriodContexte().contains(pc)){
                return "Cette période est déjà existe.";
            }else{
                return "";
            }
        }
    }
//Le methode pour vérifier le nom de tâche, si le nom déjà utilisé dans une même période, return false
    public boolean isNomTSValide(String Peri,String NomT){
        if (NomT.trim().isEmpty()||Peri.isEmpty()) { //si le string est null ou contient que les spaces(vide)
            return false;
        } else {            
            Tache t=new Tache(NomT);
            TacheContexte tc=new TacheContexte(NomT);
            return !(this.getTache(Peri).contains(t)||this.getTache(Peri).contains(tc));
        }
    }
    
    public String messageTache(String Peri,String NomT){
        if (NomT.trim().isEmpty()||Peri.isEmpty()) { //si le string est null ou contient que les spaces(vide)
            return "Saisissez une tâche.";
        } else {            
            Tache t=new Tache(NomT);
            TacheContexte tc=new TacheContexte(NomT);
            if(this.getTache(Peri).contains(t)||this.getTache(Peri).contains(tc)){
                return "Cette tâche est déjà existe.";
            }else{
                return "";
            }
        }
    }

//Le methode pour vérifier le seuil, si le seuil est une float entre 0 et 1, return true
    public boolean isSeuilValide(String seuile){
        try{
            if(seuile.trim().isEmpty()||Float.parseFloat(seuile)>1||Float.parseFloat(seuile)<0){
                return false;
            }else{
                return true;
            }
        }catch(Exception e){
            return false;
        }
    }
    public String messageSeuil(String seuile){
        try{
            if(seuile.trim().isEmpty()){
                return "Saisissez un seuil.";
            }else if(Float.parseFloat(seuile)>1||Float.parseFloat(seuile)<0){
                return "Saisissez un seuil entre 0 et 1";
            }else{
                return "";
            }
        }catch(Exception e){
            return "Saisissez un seuil de type float";
        }
    }
//L'algorithme pour générer tous les transitions automatiquement entre les tâches simples!
    public void GenererMapPS(String Peri){      
        Periode p=this.getPeri(Peri);        
        for(Tache t1:this.getTache(Peri)){
            for(Tache t2:this.getTache(Peri)){
                if(!t1.equals(t2)){
                    Transition trs=new Transition(t1,t2);
                    if(!p.containsKey(trs)){
                        p.enregistrer(trs); 
                    }
                }
            }
        }       
    }
    
//le méthode pour enregistrer les tâches contexte avec ses attributs et valeurs dans la Map<TacheContexte,ArrayList<Map<Attribut,Valeur>>> mapTacheContexte
    public void EnreTachelstMap(String Peri,String TacheC,ArrayList<String> lstAttri,ArrayList<String> lstVal){               
        this.mapAttri=new HashMap();
        Contexte Cont=new Contexte();               //créer un nouveau contexte
        for(int i=0;i<lstAttri.size();i++){
            Attribut attri=new Attribut(lstAttri.get(i));
            Valeur Val=new Valeur(lstVal.get(i));
            Cont.ajouterAttr(attri);                //ajouter l'attribut dans ce contexte
            this.mapAttri.put(attri, Val);           
            if(!this.mapAttriTotale.containsKey(attri)){            
                this.lstval=new ArrayList();
                this.lstval.add(Val);
                this.mapAttriTotale.put(attri, lstval);      //s'occupe les valeurs pour les attributes
            }else{               
                this.mapAttriTotale.get(attri).add(Val);            
           }
        }
        
        TacheContexte tc= (TacheContexte)this.getTacheSim(TacheC,Peri); 
        if(tc.getCont()==null){
            this.mapTacheContexte.remove(tc);  //si la tâche pour cette période n'est enrichée pas avec contexte, on l'a supprimer d'abord
        }        
        tc.setCont(Cont);                      //on enriche la tâche avec cette contexte
        if(!this.mapTacheContexte.containsKey(tc)){                
            this.mapTacheContexte.put(tc,new ArrayList()); 
        }    
        this.mapTacheContexte.get(tc).add(mapAttri);    //enregistrer les  Attributs et valeurs  dans la map pour une tâche contexte!
        GenererMapPC(Peri);                    //appelle la procédure pour générer tous les transitions automatiquement entre les tâches contextes!
    }
    
//le méthode pour gérer le command "undo", c'est à l'opposite de le méthode -- EnreTachelstMap(String Peri,String TacheC,ArrayList<String> lstAttri,ArrayList<String> lstVal)
    public void SupprTachelstMap(String Peri,String TacheC,ArrayList<String> lstAttri,ArrayList<String> lstVal){
        this.mapAttri=new HashMap();
        Contexte Cont=new Contexte();
        for(int i=0;i<lstAttri.size();i++){
            Attribut attri=new Attribut(lstAttri.get(i));
            Valeur Val=new Valeur(lstVal.get(i));
            Cont.ajouterAttr(attri);            
            if(!this.mapAttri.containsKey(attri)){
                this.mapAttri.put(attri, Val);
            }
        } 
        
        TacheContexte tc= (TacheContexte)this.getTacheSim(TacheC,Peri);
        
        if(this.mapTacheContexte.get(tc).size()==1){            //si le size du list des Map<Attribut,Valeur> est 1  
            this.mapTacheContexte.remove(tc);                   //supprimer la tâche de la map d'abord
            tc.removeCont();                                    //on remove le contexte de cette tâche
            this.mapTacheContexte.put(tc, new ArrayList());     //on rajoute le tâche dans la map      
        }   
 
        for(int i=0;i<this.mapTacheContexte.get(tc).size();i++){
            if(this.mapTacheContexte.get(tc).get(i).equals(mapAttri)){      //si le size du list des Map<Attribut,Valeur> supérieur à 1
                this.mapTacheContexte.get(tc).remove(i);                    //on supprime le dernier Map<Attribut,Valeur> que on a ajouté la précidente fois
            }
        } 
         
        GenererMapPC(Peri);      //appelle la procédure pour générer tous les transitions automatiquement entre les tâches contextes!
    }
    
//L'algorithme pour générer tous les transitions contextes automatiquement entre les tâches contextes!
    public void GenererMapPC(String Peri){
        PeriodeContexte pc=(PeriodeContexte)this.getPeri(Peri);
        pc.iniMapFre();   

        for (TacheContexte tc:this.mapTacheContexte.keySet()) {            
            if(!this.mapTacheContexte.get(tc).isEmpty()){
                for (int i=0;i<this.mapTacheContexte.get(tc).size();i++) {
                    for(TacheContexte tcs:this.mapTacheContexte.keySet()){
                        if(!tc.getNomTache().equals(tcs.getNomTache())){
                            TransitionContexte tsc=new TransitionContexte(tc,tcs);
                            tsc.setMap(this.mapTacheContexte.get(tc).get(i));
                            pc.enregistrer(tsc);
                        }
                    }
                }
            }else{
                for(TacheContexte tcs:this.mapTacheContexte.keySet()){
                    if(!tc.getNomTache().equals(tcs.getNomTache())){
                       TransitionContexte tsc=new TransitionContexte(tc,tcs);                         
                       pc.enregistrer(tsc);
                    }
                }
            }
        }        
    }
    
//Obtenir le list des transitions d'une période par la libellé de la période
    public ArrayList<Transition> getTran(String Peri){
        ArrayList<Transition> lstTran=new ArrayList();
        for(Transition trs:this.getPeri(Peri).getMapFr().keySet()){
            lstTran.add(trs);
        }
        return lstTran;
    }

//Obtenir le list des transitions contextes d'une période par la libellé de la période
    public ArrayList<TransitionContexte> getTranCont(String Peri){
        ArrayList<TransitionContexte> lstTran=new ArrayList();
        for(Transition trs:((PeriodeContexte)this.getPeri(Peri)).getMapFr().keySet()){
            lstTran.add((TransitionContexte)trs);
        }
        return lstTran;
    }

//Obtenir le list des fréquences d'une période par la libellé de la période
    public ArrayList<Integer> getFre(String Peri){
        ArrayList<Integer> lstFre=new ArrayList();
        for(Integer fre:this.getPeri(Peri).getMapFr().values()){
            lstFre.add(fre);
        }
        return lstFre;
    }
    
//Modifier la fréqence d'une transition(contexte) d'une période par la libellé de la période  
    public void ModifFre(String period,Transition trs, Integer fre){
        this.getPeri(period).modifierFre(trs, fre);
        System.out.println(this.mapTacheContexte);
        System.out.println(this.getPeri(period).getMapFr().toString());
    }
    
//Vérifier si les Attributs et ses valeurs sont bons ou non
    public boolean isMapAttValide(String Peri,String TacheC,ArrayList<String> lstAttri,ArrayList<String> lstVal){
        this.mapAttri=new HashMap();
        boolean isOK=true;
        Contexte Cont=new Contexte();
        for(int i=0;i<lstAttri.size();i++){
            Attribut attri=new Attribut(lstAttri.get(i));
            Valeur Val=new Valeur(lstVal.get(i));
            this.mapAttri.put(attri, Val);
            }
        TacheContexte tc= (TacheContexte)this.getTacheSim(TacheC,Peri); 
        for(int i=0;i<this.mapTacheContexte.get(tc).size();i++){
            if(this.mapAttri.equals(this.mapTacheContexte.get(tc).get(i))){  //si le map<Attribute, Valeur> est déjà existé dans le list ,return false
                isOK=false;
            }
        }      
        return isOK;
    }
}

