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
public class CommandPeriodeS implements Command{
    private Model model;
    private String name;
    
     public CommandPeriodeS(Model model, String name) {
        this.model = model;
        this.name = name;
    }
     
     @Override
    public void execute() {
        model.ajouterPeriodeS(name);
    }

    @Override
    public void undo() {
        model.supprimerPeriode(name);
    }
}
