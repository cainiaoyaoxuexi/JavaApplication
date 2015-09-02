/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.tache.command.undoredo;

import java.util.ArrayList;
import projet.tache.mvc.Model;

/**
 *
 * @author 乔
 */
public class CommandEnricherTC implements Command{

    private Model model;
    private String periode;
    private String tache;
    private ArrayList<String> lstAtt;
    private ArrayList<String> lstVal;
    
     public CommandEnricherTC(Model model, String periode, String tache,ArrayList<String> lstAtt,ArrayList<String> lstVal) {
        this.model = model;
        this.periode = periode;
        this.tache=tache;
        this.lstAtt=lstAtt;
        this.lstVal=lstVal;
    }
     
     @Override
    public void execute() {
        model.EnreTachelstMap(periode,tache,lstAtt,lstVal);
    }

    @Override
    public void undo() {
        model.SupprTachelstMap(periode, tache, lstAtt, lstVal);
    }
    
}
