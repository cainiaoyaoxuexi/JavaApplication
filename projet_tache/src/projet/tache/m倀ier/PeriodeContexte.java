/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.tache.métier;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author 乔
 */
public class PeriodeContexte extends Periode{
    //Attribut
    private double seuil;
    //Constructeur
    public PeriodeContexte(String periode){
        super(periode);
    }

    public double getSeuil() {
        return seuil;
    }

    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }
    
   
    //Trouver une tache suivente pour une tache donnée
    @Override
    public void TrouveTacheSuiv(Tache t) {
        System.out.print(((TransitionContexte)this.TrouveTransition(t)).getMapAttr().toString());
        super.TrouveTacheSuiv(t); 
    }

    @Override
    public void modifierFre(Transition t, int Frequence) {
        super.modifierFre((TransitionContexte)t, Frequence); 
    }
    
    
    //Trouver la liste des taches suiventes pour la tache dans le même contexte(les même attributs) 
    
    public ArrayList<TransitionContexte> TacheSuivParValeur(TacheContexte tc){
        ArrayList<TransitionContexte> lsttransitionMax=new ArrayList();
        TransitionContexte transitionmax=null;
        ArrayList<Map> lstmap=new ArrayList();
        //si la tache contient un contexte
        if(tc.getCont()!=null){
        //Ici on ajoute les différentes maps<attributs valeur> dans une liste
            for(Entry <Transition,Integer> entry:this.getMapFr().entrySet()){
                if(entry.getKey().getTache().equals(tc)&&!lstmap.contains(((TransitionContexte)(entry.getKey())).getMapAttr())){
                    lstmap.add(((TransitionContexte)(entry.getKey())).getMapAttr());
                }
            }        
            //Pour chaque map dans la liste
            for(int i=0;i<lstmap.size();i++){
            //on trouve la transition contient tache suivente qui avec la même tache depart et les même valeurs dans ce attribut map
                Map<TransitionContexte,Integer> mapmax=new HashMap();
                for(Entry<Transition,Integer>entry:this.getMapFr().entrySet()){
                    if((entry.getKey().getTache().equals(tc)&&((TransitionContexte)(entry.getKey())).getMapAttr().equals(lstmap.get(i)))){
                        mapmax.put(((TransitionContexte)(entry.getKey())),entry.getValue());
                    }
                }
                for(Entry<TransitionContexte,Integer> tsm:mapmax.entrySet()){
                    int freMax=tsm.getValue();
                    transitionmax=tsm.getKey();
                    for(TransitionContexte tsc:mapmax.keySet()){
                        if(mapmax.get(tsc)>=freMax){
                            transitionmax=tsc;
                            freMax=mapmax.get(tsc);
                        }
                    }                
                break;               
                }
                lsttransitionMax.add(transitionmax);
            }
        }else{
        //si la tache ne contient pas le contexte 
            transitionmax=(TransitionContexte)super.TrouveTransition(tc);
            lsttransitionMax.add(transitionmax);
        }        
        return lsttransitionMax;        
    }     
    //calcul le nombre total de transition ayant pour origine la tache
    public int NbTotal(TacheContexte tc){
        int sum=0;
        //on trouve le somme de fréquence de la tache donnée
        for(Entry<Transition,Integer> entry : this.getMapFr().entrySet()){
            if(((TransitionContexte)(entry.getKey())).getTache().equals(tc)){
                sum+=entry.getValue();
            }
        }
        return sum;
    }
   
    //Extraire du processus type
    @Override
    public String Extraire(){
        TacheContexte TacheDepart=(TacheContexte)this.TacheDepart();         
        TransitionContexte ts =(TransitionContexte)this.TrouveTransition(TacheDepart);        
        String processus=TacheDepart+"";
        //ArrayList<TacheContexte>
        //si la tache suivente n'est pas contenu dans le processus précédent
        while(!processus.contains(ts.getTacheSuivante().getNomTache()+"-->")){
            if(!ts.getMapAttr().isEmpty()){
                //quand la tache est enrichiée avec un contexte, on affichie la tache suivente et le contexte
                processus=processus+"-->(cxt:"+((TransitionContexte)this.TrouveTransition(TacheDepart)).getMapAttr()+")"+this.TrouveTransition(TacheDepart).getTacheSuivante();
            }else{
                //quand la tache sans contexte
                processus=processus+"-->"+this.TrouveTransition(TacheDepart).getTacheSuivante();
            }            
            TacheDepart=(TacheContexte)ts.getTacheSuivante();           
            ts=(TransitionContexte)this.TrouveTransition(TacheDepart);           
        }        
       return processus;
    }
    //Trouver la fréquence pour une transition donnée
     public int trouveFre(TransitionContexte tsc){
        int fre=0;
        for(Entry<Transition, Integer> entry: this.getMapFr().entrySet()){
            TransitionContexte trc=(TransitionContexte)entry.getKey();
                if(trc.getTache().getNomTache()==tsc.getTache().getNomTache()
                        &&trc.getTacheSuivante().getNomTache()==tsc.getTacheSuivante().getNomTache()
                        &&trc.getMapAttr().equals(tsc.getMapAttr())){
                    fre=entry.getValue();
                }
        }
        return fre;
    }
    // Extraire avec la condition de seuil
    public ArrayList<String> Extraireavecseuil(){  
        ArrayList<String> lstProcess=new ArrayList();
        double s= this.getSeuil();
        TacheContexte TacheDepart=(TacheContexte)this.TacheDepart();   
        DecimalFormat df=new DecimalFormat(".##%");
        TacheContexte TD=TacheDepart;
        ArrayList<TransitionContexte> lsttrs= this.TacheSuivParValeur(TD);
        double pourcentage=0;
        String Proces=TacheDepart.getNomTache();
        //quand la tache depart contient un contexte        
        for(int i=0;i<lsttrs.size();i++) {             
            Proces=TacheDepart.getNomTache();
            pourcentage =(double)(this.trouveFre(lsttrs.get(i)))/(this.NbTotal(TacheDepart));
            //si le pourcentage est supérieur à la seuile défini
            if (pourcentage>s){
                //si le valeur d'attribute dans le HashMap mapAttr n'est pas vide, on afficher le contexte
                if(!lsttrs.get(i).getMapAttr().isEmpty()){
                    Proces+="-->(si"+lsttrs.get(i).getMapAttr()+" "+df.format(pourcentage)+")"+lsttrs.get(i).getTacheSuivante().getNomTache();
                    TD=(TacheContexte)lsttrs.get(i).getTacheSuivante();
                    trouveBoucle(TD,Proces,lstProcess);
                //si non affiche pas le contexte
                }else{
                    Proces=TacheDepart.getNomTache();            
                    Proces+="-->"+lsttrs.get(i).getTacheSuivante().getNomTache();
                    TD=(TacheContexte)lsttrs.get(i).getTacheSuivante();
                    trouveBoucle(TD,Proces,lstProcess);  
                }
            }
        } 
        return lstProcess;
    }
    //processus récursif 
     public void trouveBoucle(TacheContexte TD,String Proces,ArrayList<String> lst){        
        double s= this.getSeuil();        
        DecimalFormat df=new DecimalFormat(".##%");
        ArrayList<TransitionContexte> lsttrs= this.TacheSuivParValeur(TD);
        String pdepart=Proces;
        int total=this.NbTotal(TD);
        double pourcentage=0; 
        //pour chaque transitionContexte trouvées
        for (int i=0;i<lsttrs.size();i++) {
            pdepart=Proces; 
            //si le processus contient pas la tachesuivante
            if(!pdepart.contains(lsttrs.get(i).getTacheSuivante().getNomTache()+"-->")){
                pourcentage =(double)(this.trouveFre(lsttrs.get(i)))/(total);  
                //si le pourcentage est supérieur à la seuile défini 
                if(pourcentage>s){
                    //si le valeur d'attribute dans le HashMap mapAttr n'est pas vide, on afficher le contexte
                    if(!lsttrs.get(i).getMapAttr().isEmpty()){
                        pdepart+="-->(si"+lsttrs.get(i).getMapAttr()+" "+df.format(pourcentage)+")"+lsttrs.get(i).getTacheSuivante().getNomTache();            
                        TD=(TacheContexte)lsttrs.get(i).getTacheSuivante();
                        trouveBoucle(TD,pdepart,lst);
                    //si non affiche pas le contexte
                    }else{           
                        pdepart+="-->"+lsttrs.get(i).getTacheSuivante().getNomTache();
                        TD=(TacheContexte)lsttrs.get(i).getTacheSuivante();
                        trouveBoucle(TD,pdepart,lst);      
                    }
                }else{
                    //System.out.println(pdepart+lsttrs.get(i).getMapAttr());
                    lst.add(pdepart+lsttrs.get(i).getMapAttr());
                }
            //la condition pour sortir le processus récursif 
            }else{              
                TD=(TacheContexte)lsttrs.get(i).getTache();
                if(!lsttrs.get(i).getMapAttr().isEmpty()){
                   //System.out.println(pdepart+lsttrs.get(i).getMapAttr());
                   lst.add(pdepart+lsttrs.get(i).getMapAttr());
                }else{
                   //System.out.println(pdepart);
                   lst.add(pdepart);
                }
            }           
        }
    }
   
}
