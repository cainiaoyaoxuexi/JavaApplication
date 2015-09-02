/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projet.tache.métier;

/**
 *
 * @author 21103205
 */
public class Projet_tache {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Créer les taches normaales
        Tache T1=new Tache("A");
        Tache T2=new Tache("B");
        Tache T3=new Tache("C");
        Tache T4=new Tache("A");
        
        //Créer les transitions normal
        Transition t1=new Transition(T1,T2);
        Transition t2=new Transition(T4,T2);
        
        Transition t3=new Transition(T2,T3);
        Transition t4=new Transition(T2,T1);
        Transition t5=new Transition(T1,T3);
        Transition t6=new Transition(T3,T1);
        Transition t7=new Transition(T3,T2);
        
                
        Periode P1=new Periode("periode1");
        //ajouter les transitions dans la période
        P1.enregistrer(t1);
        P1.enregistrer(t2);//on ne peut pas ajouter une transition qui est déjà existe dans cette période;
        P1.enregistrer(t3);
        P1.enregistrer(t4);
        P1.enregistrer(t5);
        P1.enregistrer(t6);
        P1.enregistrer(t7);        
        
        //modifire les fréquences des transitions 
        P1.modifierFre(t1, 280);             
        P1.modifierFre(t3, 200);
        P1.modifierFre(t4, 0);       
        P1.modifierFre(t5,42);
        P1.modifierFre(t6,0);
        P1.modifierFre(t7,25);
        System.out.println(P1.getPeriode()+": "+P1);        
        System.out.println("La tache départ de "+P1.getPeriode()+": "+P1.TacheDepart());              
        P1.TrouveTacheSuiv(T1);
        P1.TrouveTacheSuiv(T2);
        P1.Extraire();
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");  
        
        //créer une période dont la tache A est enrichiée par un contexte
        PeriodeContexte pc1=new PeriodeContexte("periode contexte 1"); 
        
        //créer les Attributes
        Attribut A1,A2;
        A1=new Attribut("Responsable");
        A2=new Attribut("Duree");
        
        //créer les contextes
        Contexte c1,c2;
        c1=new Contexte();
        c2=new Contexte();
        
        //ajouter les attributs dans les contextes
        c1.ajouterAttr(A1);
        c1.ajouterAttr(A2);
        
        c2.ajouterAttr(A1);
        
        
        TacheContexte tc1=new TacheContexte("A",c1);        
        TacheContexte tc2=new TacheContexte("B",null);
        TacheContexte tc3=new TacheContexte("C",null);
        //TacheContexte tc4=new TacheContexte("D",null);   
        
        //il faut d'abord ajouter lea valeurs avant enregistrer une transition contexte
        TransitionContexte tsc1=new TransitionContexte(tc1, tc2);              
        tsc1.ajouterValeur(A1, "riri");
        pc1.enregistrer(tsc1);        
        pc1.modifierFre(tsc1, 270);        
        
        System.out.println();
        
        TransitionContexte tsc2=new TransitionContexte(tc1, tc2);
        tsc2.ajouterValeur(A1, "loulou");
        pc1.enregistrer(tsc2);        
        pc1.modifierFre(tsc2, 10);
        
        TransitionContexte tsc3=new TransitionContexte(tc1, tc3);
        tsc3.ajouterValeur(A1, "riri");
        pc1.enregistrer(tsc3);       
        pc1.modifierFre(tsc3, 5);
        
        TransitionContexte tsc4=new TransitionContexte(tc1, tc3);
        tsc4.ajouterValeur(A1, "loulou");
        pc1.enregistrer(tsc4);        
        pc1.modifierFre(tsc4, 37);        
        
        TransitionContexte tsc5=new TransitionContexte(tc2,tc1);
        pc1.enregistrer(tsc5);
        pc1.modifierFre(tsc5, 0); 
        
        TransitionContexte tsc6=new TransitionContexte(tc2,tc3);
        pc1.enregistrer(tsc6);
        pc1.modifierFre(tsc6, 200); 
        
        TransitionContexte tsc7=new TransitionContexte(tc3,tc1);
        pc1.enregistrer(tsc7);
        pc1.modifierFre(tsc7, 0); 
        
        TransitionContexte tsc8=new TransitionContexte(tc3,tc2);
        pc1.enregistrer(tsc8);
        pc1.modifierFre(tsc8, 2);               
        
        System.out.println(pc1.getPeriode()+": "+pc1);
        System.out.println();
        System.out.println("La tache départ de "+pc1.getPeriode()+": "+pc1.TacheDepart());                
        System.out.println("La tache suivante de "+tc1+" est: "+((TransitionContexte)pc1.TrouveTransition(tc1)).getMapAttr()+pc1.TrouveTransition(tc1).getTacheSuivante());
        
        System.out.println();
        //extraire sans considering le contexte de la tache        
        pc1.Extraire(); 
        System.out.println();
        //si la seuile est 0.1:
        pc1.setSeuil(0.1);
        System.out.println("Le processus type avec le seuil "+pc1.getSeuil()+": ");
        System.out.println(pc1.Extraireavecseuil().toString());
        System.out.println();
        
        //si la seuile est 0.5
        pc1.setSeuil(0.5);
        System.out.println("Le processus type avec le seuil "+pc1.getSeuil()+": ");
        pc1.Extraireavecseuil();
        
        
        System.out.println("------------------------------------------------------------------------------------------------------------------------------"); 
              
        PeriodeContexte pc2=new PeriodeContexte("periode contexte 2"); 
        //Créer les tachecontextes si A et B avec contextes
        TacheContexte tc5=new TacheContexte("A",c2);        
        TacheContexte tc6=new TacheContexte("B",c1);
        TacheContexte tc7=new TacheContexte("C",null);
        TacheContexte tc8=new TacheContexte("D",null);   

        //Enregistrement des transitions
        //les transition de A au B avec les attributs diffirents
        TransitionContexte tscAB1=new TransitionContexte(tc5, tc6);
        tscAB1.ajouterValeur(A1, "riri");
        pc2.enregistrer(tscAB1);        
        TransitionContexte tscAB2=new TransitionContexte(tc5, tc6);
        tscAB2.ajouterValeur(A1, "loulou");
        pc2.enregistrer(tscAB2);        
        TransitionContexte tscAB3=new TransitionContexte(tc5, tc6); 
        tscAB3.ajouterValeur(A1, "fifi");
        pc2.enregistrer(tscAB3);
        
        //les transition de A au C avec les attributs diffirents
        TransitionContexte tscAC1=new TransitionContexte(tc5, tc7); 
        tscAC1.ajouterValeur(A1, "riri");
        pc2.enregistrer(tscAC1);        
        TransitionContexte tscAC2=new TransitionContexte(tc5, tc7); 
        tscAC2.ajouterValeur(A1, "loulou");
        pc2.enregistrer(tscAC2);        
        TransitionContexte tscAC3=new TransitionContexte(tc5, tc7); 
        tscAC3.ajouterValeur(A1, "fifi");
        pc2.enregistrer(tscAC3);
        
        //les transition de A au D avec les attributs diffirents
        TransitionContexte tscAD1=new TransitionContexte(tc5, tc8); 
        tscAD1.ajouterValeur(A1, "riri");
        pc2.enregistrer(tscAD1);        
        TransitionContexte tscAD2=new TransitionContexte(tc5, tc8); 
        tscAD2.ajouterValeur(A1, "loulou");
        pc2.enregistrer(tscAD2);        
        TransitionContexte tscAD3=new TransitionContexte(tc5, tc8); 
        tscAD3.ajouterValeur(A1, "fifi");
        pc2.enregistrer(tscAD3);
        
       //les transition de B au A avec les attributs diffirents
        TransitionContexte tscBA1=new TransitionContexte(tc6, tc5);
        tscBA1.ajouterValeur(A1, "loulou");
        tscBA1.ajouterValeur(A2, "10");
        pc2.enregistrer(tscBA1);                
        TransitionContexte tscBA2=new TransitionContexte(tc6, tc5);  
        tscBA2.ajouterValeur(A1, "riri");
        tscBA2.ajouterValeur(A2, "10");
        pc2.enregistrer(tscBA2);        
        TransitionContexte tscBA3=new TransitionContexte(tc6, tc5);
        tscBA3.ajouterValeur(A1, "fifi");
        tscBA3.ajouterValeur(A2, "15");
        pc2.enregistrer(tscBA3);
        
        //les transition de B au C avec les attributs diffirents
        TransitionContexte tscBC1=new TransitionContexte(tc6, tc7);
        tscBC1.ajouterValeur(A1, "loulou");
        tscBC1.ajouterValeur(A2, "10");
        pc2.enregistrer(tscBC1);        
        TransitionContexte tscBC2=new TransitionContexte(tc6, tc7); 
        tscBC2.ajouterValeur(A1, "riri");
        tscBC2.ajouterValeur(A2, "10");
        pc2.enregistrer(tscBC2);       
        TransitionContexte tscBC3=new TransitionContexte(tc6, tc7); 
        tscBC3.ajouterValeur(A1, "fifi");
        tscBC3.ajouterValeur(A2, "15");
        pc2.enregistrer(tscBC3);
        
        //les transition de B au D avec les attributs diffirents
        TransitionContexte tscBD1=new TransitionContexte(tc6, tc8);
        tscBD1.ajouterValeur(A1, "loulou");
        tscBD1.ajouterValeur(A2, "10");
        pc2.enregistrer(tscBD1);        
        TransitionContexte tscBD2=new TransitionContexte(tc6, tc8); 
        tscBD2.ajouterValeur(A1, "riri");
        tscBD2.ajouterValeur(A2, "10");
        pc2.enregistrer(tscBD2);  
        TransitionContexte tscBD3=new TransitionContexte(tc6, tc8); 
        tscBD3.ajouterValeur(A1, "fifi");
        tscBD3.ajouterValeur(A2, "15");
        pc2.enregistrer(tscBD3);
        
        //les transition de C au A, B et D sans valeur
        TransitionContexte tscCA=new TransitionContexte(tc7, tc5);  
        pc2.enregistrer(tscCA);
        TransitionContexte tscCB=new TransitionContexte(tc7, tc6); 
        pc2.enregistrer(tscCB);
        TransitionContexte tscCD=new TransitionContexte(tc7, tc8); 
        pc2.enregistrer(tscCD);
        
        //les transition de D au A, B et C sans valeur
        TransitionContexte tscDA=new TransitionContexte(tc8, tc5); 
        pc2.enregistrer(tscDA);        
        TransitionContexte tscDB=new TransitionContexte(tc8, tc6); 
        pc2.enregistrer(tscDB);
        TransitionContexte tscDC=new TransitionContexte(tc8, tc7); 
        pc2.enregistrer(tscDC);
        
        
       
        //Modifier les fréquences 
        pc2.modifierFre(tscAB1, 230);
        pc2.modifierFre(tscAB2, 50);
        pc2.modifierFre(tscAB3, 18);
        pc2.modifierFre(tscAC1, 80);
        pc2.modifierFre(tscAC2, 10);
        pc2.modifierFre(tscAC3, 130);
        pc2.modifierFre(tscAD1, 55);
        pc2.modifierFre(tscAD2, 30);
        pc2.modifierFre(tscAD3, 10);
        
        pc2.modifierFre(tscBA1, 0);
        pc2.modifierFre(tscBA2, 0);
        pc2.modifierFre(tscBA3, 0);
        pc2.modifierFre(tscBC1, 90);
        pc2.modifierFre(tscBC2, 110);
        pc2.modifierFre(tscBC3, 10);
        pc2.modifierFre(tscBD1, 30);
        pc2.modifierFre(tscBD2, 15);
        pc2.modifierFre(tscBD3, 22);
        
        pc2.modifierFre(tscCA, 0);
        pc2.modifierFre(tscCB, 5);
        pc2.modifierFre(tscCD, 35);
        
        pc2.modifierFre(tscDA, 0);
        pc2.modifierFre(tscDB, 0);
        pc2.modifierFre(tscDC, 5);
        
        
               
        System.out.println(pc2.getPeriode()+": "+pc2);
        System.out.println();
        System.out.println("La tache départ de "+pc2.getPeriode()+": "+pc2.TacheDepart());                
                      
        System.out.println("La tache suivante de "+tc6+" est: "+((TransitionContexte)pc2.TrouveTransition(tc6)).getMapAttr()+pc2.TrouveTransition(tc6).getTacheSuivante());
        
        System.out.println();
        //extraire sans considerin le contexte
        pc2.Extraire(); 
        System.out.println();
        // si la seuile est 0
        pc2.setSeuil(0.0);       
        System.out.println("Le processus type avec le seuil "+pc2.getSeuil()+": ");  
        pc2.Extraireavecseuil();
        System.out.println();
        
        // si la seuile est 0.1
        pc2.setSeuil(0.1);
        System.out.println("Le processus type avec le seuil "+pc2.getSeuil()+": ");               
        System.out.println(pc2.Extraireavecseuil().toString());
        System.out.println();
        
        // si la seuile est 0.2
        pc2.setSeuil(0.2);
        System.out.println("Le processus type avec le seuil "+pc2.getSeuil()+": ");
        pc2.Extraireavecseuil();
        System.out.println();
        
        // si la seuile est 0.3
        pc2.setSeuil(0.3);
        System.out.println("Le processus type avec le seuil "+pc2.getSeuil()+": ");
        pc2.Extraireavecseuil();
        System.out.println();
        
        // si la seuile est 0.35
        pc2.setSeuil(0.35);
        System.out.println("Le processus type avec le seuil "+pc2.getSeuil()+": ");
        pc2.Extraireavecseuil();
        System.out.println();
        
        //si la seuile est 0.5, aucune processus type est bon
        pc2.setSeuil(0.5);
        System.out.println("Le processus type avec le seuil "+pc2.getSeuil()+": ");
        System.out.println(pc2.Extraireavecseuil());
        
    }
    
}
