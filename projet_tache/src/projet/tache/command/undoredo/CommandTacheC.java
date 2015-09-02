/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.tache.command.undoredo;

import projet.tache.mvc.Model;

/**
 *
 * @author 乔
 */
public class CommandTacheC implements Command{
     private Model model;
    private String periode;
    private String tache;
    
     public CommandTacheC(Model model, String periode, String tache) {
        this.model = model;
        this.periode = periode;
        this.tache=tache;
    }
     
     @Override
    public void execute() {
        model.ajouterTacheC(periode,tache);
    }

    @Override
    public void undo() {
        model.supprimerTacheC(periode,tache);
    }
}
