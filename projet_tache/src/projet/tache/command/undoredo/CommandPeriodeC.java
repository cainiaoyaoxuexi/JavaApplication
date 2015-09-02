package projet.tache.command.undoredo;

import projet.tache.mvc.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 乔
 */
public class CommandPeriodeC implements Command{
    private Model model;
    private String name;
    
     public CommandPeriodeC(Model model, String name) {
        this.model = model;
        this.name = name;
    }
     
     @Override
    public void execute() {
        model.ajouterPeriodeContexte(name);
    }

    @Override
    public void undo() {
        model.supprimerPeriode(name);
    }
}
