/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.tache;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import projet.tache.ihm.FenetreTache;

/**
 *
 * @author 乔
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final FenetreTache f1 = new FenetreTache();
        final FenetreTache f2 = new FenetreTache();
        
        f1.setTitle("F1");
        f2.setTitle("F2");
        
        f1.setVisible(true);
        f2.setVisible(true);
        
        JFrame f = new JFrame();
        JButton b = new JButton("Exchange!");
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                f2.setModel(f1.getModel());
            }
        });
        f.getContentPane().add(b);
        f.pack();
        f.setVisible(true);
    }
    
}
