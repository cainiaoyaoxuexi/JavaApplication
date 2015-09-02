/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projet.tache.ihm;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import projet.tache.mvc.Model;
import projet.tache.mvc.View;
import projet.tache.métier.*;

/**
 *
 * @author 21103205
 */
public class PanelAfficher extends javax.swing.JPanel {
    private Model model;
    private Periode p;
    private ActionsTableModel tableModel; 
    /**
     * Creates new form PanelAffichier
     */
    public PanelAfficher() {
        initComponents();
        
        model = new Model();
        tableModel = new ActionsTableModel();
        TblTDS.setDefaultRenderer(Tache.class, new ActionRenderer());
  
        
        setModel(model);
        txtSeuil.setText("");
        VeriSeuilListener nomplistener= new VeriSeuilListener();
        txtSeuil.getDocument().addDocumentListener(nomplistener);
        verifierSeuil();
    }

    public Model getModel() {
        return model;
    }
    
    public void setModel(Model model) {
        this.model = model;
        tableModel.fireTableStructureChanged();
                
        periodeview pv=new periodeview();
        model.addView(pv);
        pv.notify(model);
        
        VerifierSeuilView vsv=new VerifierSeuilView();
        model.addView(vsv);
        vsv.notify(model);
        
        model.addView(new View() {
            @Override
            public void notify(Model model) {
                tableModel.fireTableStructureChanged();
            }
        });     
              
    }
    private class periodeview implements View{

        @Override
        public void notify(Model model) {
            inicbxperi();
            inicbxlsttache();
        }
        
    }
    private void inicbxperi(){
        cbxPeri.removeAllItems();
        for (Periode p : model.getMapPeriod().keySet()) {
            cbxPeri.addItem(p.getPeriode());
        }
    }
    private void inicbxlsttache(){
        if(cbxPeri.getItemCount()>0){
        cbxlsttache.removeAllItems();
        for(Tache t:model.getTache(cbxPeri.getSelectedItem().toString())){
            cbxlsttache.addItem(t.toString());
        }}
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        PanelPeri = new javax.swing.JPanel();
        lblPeri = new javax.swing.JLabel();
        cbxPeri = new javax.swing.JComboBox();
        PanelTache = new javax.swing.JPanel();
        PanelTD = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblTD = new javax.swing.JLabel();
        PanelTacheChoisi = new javax.swing.JPanel();
        cbxlsttache = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        PanelTSuivi = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblTachesuivante = new javax.swing.JLabel();
        PanelPro = new javax.swing.JPanel();
        PanelProSim = new javax.swing.JPanel();
        lblProSimp = new javax.swing.JLabel();
        lblResultat = new javax.swing.JLabel();
        PanelProCont = new javax.swing.JPanel();
        lblSeuil = new javax.swing.JLabel();
        txtSeuil = new javax.swing.JTextField();
        lstProContexte = new javax.swing.JScrollPane();
        lstprosessusC = new javax.swing.JList();
        btnConf = new javax.swing.JButton();
        lblSeuilWarning = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        PanelTDS = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblTDS = new javax.swing.JTable();

        jLabel10.setText("jLabel10");

        jPanel1.setPreferredSize(new java.awt.Dimension(410, 521));

        PanelPeri.setBorder(javax.swing.BorderFactory.createTitledBorder("Période"));

        lblPeri.setText("Période");

        cbxPeri.setPreferredSize(new java.awt.Dimension(56, 25));
        cbxPeri.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxPeriItemStateChanged(evt);
            }
        });
        cbxPeri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxPeriMouseClicked(evt);
            }
        });
        cbxPeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPeriActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelPeriLayout = new javax.swing.GroupLayout(PanelPeri);
        PanelPeri.setLayout(PanelPeriLayout);
        PanelPeriLayout.setHorizontalGroup(
            PanelPeriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPeriLayout.createSequentialGroup()
                .addComponent(lblPeri, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxPeri, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelPeriLayout.setVerticalGroup(
            PanelPeriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPeriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblPeri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbxPeri, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
        );

        PanelTache.setBorder(javax.swing.BorderFactory.createTitledBorder("Tâche"));

        jLabel2.setText("Tâche Départ :");

        lblTD.setForeground(new java.awt.Color(255, 102, 51));

        javax.swing.GroupLayout PanelTDLayout = new javax.swing.GroupLayout(PanelTD);
        PanelTD.setLayout(PanelTDLayout);
        PanelTDLayout.setHorizontalGroup(
            PanelTDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTDLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelTDLayout.setVerticalGroup(
            PanelTDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
            .addComponent(lblTD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        cbxlsttache.setMinimumSize(new java.awt.Dimension(56, 25));
        cbxlsttache.setPreferredSize(new java.awt.Dimension(56, 25));
        cbxlsttache.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxlsttacheItemStateChanged(evt);
            }
        });
        cbxlsttache.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxlsttacheMouseClicked(evt);
            }
        });
        cbxlsttache.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxlsttacheActionPerformed(evt);
            }
        });

        jLabel4.setText("Choisir une tâche: ");
        jLabel4.setPreferredSize(new java.awt.Dimension(90, 25));

        javax.swing.GroupLayout PanelTacheChoisiLayout = new javax.swing.GroupLayout(PanelTacheChoisi);
        PanelTacheChoisi.setLayout(PanelTacheChoisiLayout);
        PanelTacheChoisiLayout.setHorizontalGroup(
            PanelTacheChoisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTacheChoisiLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxlsttache, 0, 239, Short.MAX_VALUE))
        );
        PanelTacheChoisiLayout.setVerticalGroup(
            PanelTacheChoisiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
            .addComponent(cbxlsttache, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel5.setText("Tâche suivante :");

        lblTachesuivante.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout PanelTSuiviLayout = new javax.swing.GroupLayout(PanelTSuivi);
        PanelTSuivi.setLayout(PanelTSuiviLayout);
        PanelTSuiviLayout.setHorizontalGroup(
            PanelTSuiviLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTSuiviLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTachesuivante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelTSuiviLayout.setVerticalGroup(
            PanelTSuiviLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
            .addComponent(lblTachesuivante, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelTacheLayout = new javax.swing.GroupLayout(PanelTache);
        PanelTache.setLayout(PanelTacheLayout);
        PanelTacheLayout.setHorizontalGroup(
            PanelTacheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelTD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelTacheChoisi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelTSuivi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelTacheLayout.setVerticalGroup(
            PanelTacheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTacheLayout.createSequentialGroup()
                .addComponent(PanelTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTacheChoisi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTSuivi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelPro.setBorder(javax.swing.BorderFactory.createTitledBorder("Processus"));

        lblProSimp.setText("Processus Simple: ");

        lblResultat.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout PanelProSimLayout = new javax.swing.GroupLayout(PanelProSim);
        PanelProSim.setLayout(PanelProSimLayout);
        PanelProSimLayout.setHorizontalGroup(
            PanelProSimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProSimLayout.createSequentialGroup()
                .addComponent(lblProSimp, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblResultat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelProSimLayout.setVerticalGroup(
            PanelProSimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblProSimp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
            .addComponent(lblResultat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblSeuil.setText("Set Seuil: ");

        txtSeuil.setPreferredSize(new java.awt.Dimension(59, 25));
        txtSeuil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSeuilActionPerformed(evt);
            }
        });

        lstProContexte.setViewportView(lstprosessusC);

        btnConf.setText("Confirmer");
        btnConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfActionPerformed(evt);
            }
        });

        lblSeuilWarning.setForeground(new java.awt.Color(0, 102, 255));
        lblSeuilWarning.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeuilWarning.setText("Saissez un seuil validé");

        javax.swing.GroupLayout PanelProContLayout = new javax.swing.GroupLayout(PanelProCont);
        PanelProCont.setLayout(PanelProContLayout);
        PanelProContLayout.setHorizontalGroup(
            PanelProContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProContLayout.createSequentialGroup()
                .addComponent(lblSeuil, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSeuil, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConf, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lstProContexte)
            .addComponent(lblSeuilWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelProContLayout.setVerticalGroup(
            PanelProContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProContLayout.createSequentialGroup()
                .addGroup(PanelProContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelProContLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblSeuil, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelProContLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSeuil, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnConf, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSeuilWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lstProContexte))
        );

        javax.swing.GroupLayout PanelProLayout = new javax.swing.GroupLayout(PanelPro);
        PanelPro.setLayout(PanelProLayout);
        PanelProLayout.setHorizontalGroup(
            PanelProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelProSim, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelProCont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PanelProLayout.setVerticalGroup(
            PanelProLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelProLayout.createSequentialGroup()
                .addComponent(PanelProSim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelProCont, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPeri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelTache, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PanelPeri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelTache, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelPro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        PanelTDS.setBorder(javax.swing.BorderFactory.createTitledBorder("Tableau de suivi"));

        TblTDS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TblTDS);

        javax.swing.GroupLayout PanelTDSLayout = new javax.swing.GroupLayout(PanelTDS);
        PanelTDS.setLayout(PanelTDSLayout);
        PanelTDSLayout.setHorizontalGroup(
            PanelTDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTDSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
        );
        PanelTDSLayout.setVerticalGroup(
            PanelTDSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTDSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
        );

        jPanel2.add(PanelTDS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSeuilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSeuilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeuilActionPerformed

    private void cbxPeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPeriActionPerformed
        
    }//GEN-LAST:event_cbxPeriActionPerformed

    private void cbxPeriItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxPeriItemStateChanged
        try{
            if(cbxPeri.getItemCount()>0){
            tableModel = new ActionsTableModel();
            TblTDS.setModel(tableModel);
            inicbxlsttache();
            if(model.getPeri(cbxPeri.getSelectedItem().toString()).getClass()==Periode.class){
                PanelProCont.setVisible(false);
                if(model.getTache(cbxPeri.getSelectedItem().toString()).size()>1){
                    if(cbxlsttache.getSelectedItem()==null){
                        return;
                    }
                lblTD.setText(model.getPeri(cbxPeri.getSelectedItem().toString()).TacheDepart().toString());
                lblResultat.setText(model.getPeri(cbxPeri.getSelectedItem().toString()).Extraire());
                }else{
                Object[] as={};
                lstprosessusC.setListData(as);
                lblTD.setText("");
                lblResultat.setText(""); 
                lblTachesuivante.setText("");
                }
            }else{
                PanelProCont.setVisible(true);

                if(model.getTache(cbxPeri.getSelectedItem().toString()).size()>1){
                    if(cbxlsttache.getSelectedItem()==null){
                        return;
                    }
                Object[] as={};
                lstprosessusC.setListData(as);
                lblTD.setText(model.getPeri(cbxPeri.getSelectedItem().toString()).TacheDepart().toString());
                lblResultat.setText(model.getPeri(cbxPeri.getSelectedItem().toString()).Extraire());
                }else{
                    Object[] as={};
                lstprosessusC.setListData(as);
                lblTD.setText("");
                lblResultat.setText(""); 
                lblTachesuivante.setText("");
                }
            }
            }
        }catch(Exception e){
            
        }
    }//GEN-LAST:event_cbxPeriItemStateChanged

    private void cbxlsttacheItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxlsttacheItemStateChanged
       try{
            if(model.getTache(cbxPeri.getSelectedItem().toString()).size()>1){
             if(cbxlsttache.getSelectedItem()==null){
                         return;
                     }
             lblTachesuivante.setText(model.getPeri(cbxPeri.getSelectedItem().toString()).TrouveTransition(model.getTacheSim(cbxlsttache.getSelectedItem().toString(),cbxPeri.getSelectedItem().toString())).getTacheSuivante().toString());         
            }
       }catch(Exception e){
           
       }
    }//GEN-LAST:event_cbxlsttacheItemStateChanged

    private void cbxlsttacheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxlsttacheActionPerformed
//        if(model.getTache(cbxPeri.getSelectedItem().toString()).size()>1){
////            lblTacheSuiv.setText(model.getPeri(cbxPeri.getSelectedItem().toString()).TrouveTransition(model.getTacheSim(cbxlsttache.getSelectedItem().toString(),cbxPeri.getSelectedItem().toString())).getTacheSuivante().toString());
//           lblTachesuivante.setText(cbxlsttache.getSelectedItem().toString());
//       }
    }//GEN-LAST:event_cbxlsttacheActionPerformed

    private void cbxPeriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxPeriMouseClicked

    }//GEN-LAST:event_cbxPeriMouseClicked

    private void cbxlsttacheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxlsttacheMouseClicked

    }//GEN-LAST:event_cbxlsttacheMouseClicked

    private void btnConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfActionPerformed
            verifierSeuil();
            ProcessView pv=new ProcessView();
            model.addView(pv);
            pv.notify(model); 
    }//GEN-LAST:event_btnConfActionPerformed
    private class ProcessView implements View{
             @Override
            public void notify(Model model) {
                try{
                    PeriodeContexte pc=(PeriodeContexte)model.getPeri(cbxPeri.getSelectedItem().toString());
                    pc.setSeuil(Float.parseFloat(txtSeuil.getText()));
                    ArrayList<String> lstPro=pc.Extraireavecseuil();
                    Object[] tabTS = lstPro.toArray();   
                    lstprosessusC.setListData(tabTS); 
                }catch(Exception e){
                    
                }
            }
        }
    
    private class VerifierSeuilView implements View{
        @Override
        public void notify(Model model){
            verifierSeuil();
        }
    }
    private void verifierSeuil(){
        if(cbxPeri.getItemCount()>0){
        boolean isSeuilOK=model.isSeuilValide(txtSeuil.getText());   
        btnConf.setEnabled(isSeuilOK);
        lblSeuilWarning.setVisible(!isSeuilOK);
        String message=model.messageSeuil(txtSeuil.getText());
        lblSeuilWarning.setText(message);
        }
    }
private class VeriSeuilListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            verifierSeuil();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            verifierSeuil();        
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            verifierSeuil();
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPeri;
    private javax.swing.JPanel PanelPro;
    private javax.swing.JPanel PanelProCont;
    private javax.swing.JPanel PanelProSim;
    private javax.swing.JPanel PanelTD;
    private javax.swing.JPanel PanelTDS;
    private javax.swing.JPanel PanelTSuivi;
    private javax.swing.JPanel PanelTache;
    private javax.swing.JPanel PanelTacheChoisi;
    private javax.swing.JTable TblTDS;
    private javax.swing.JButton btnConf;
    private javax.swing.JComboBox cbxPeri;
    private javax.swing.JComboBox cbxlsttache;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPeri;
    private javax.swing.JLabel lblProSimp;
    private javax.swing.JLabel lblResultat;
    private javax.swing.JLabel lblSeuil;
    private javax.swing.JLabel lblSeuilWarning;
    private javax.swing.JLabel lblTD;
    private javax.swing.JLabel lblTachesuivante;
    private javax.swing.JScrollPane lstProContexte;
    private javax.swing.JList lstprosessusC;
    private javax.swing.JTextField txtSeuil;
    // End of variables declaration//GEN-END:variables
 private class ActionsTableModel extends AbstractTableModel{

        @Override
        public int getRowCount() {
            int number=0;
            if(cbxPeri.getItemCount()>0){
                if(model.getPeri(cbxPeri.getSelectedItem().toString()).getClass()==Periode.class){
                    number=model.getTache(cbxPeri.getSelectedItem().toString()).size()*(model.getTache(cbxPeri.getSelectedItem().toString()).size()-1);
                }else{
                    number=model.getTran(cbxPeri.getSelectedItem().toString()).size();
                }
            }
            return number;
        }

        @Override
        public int getColumnCount() {
            return model.getPeri(cbxPeri.getSelectedItem().toString()).getClass()==Periode.class ? 3:4;
        }   
        
        @Override
        public String getColumnName(int column) {
            String nomcol=null;
            if (column == 0) {
                nomcol="Tâche";
            }
            if (column == 1) {
                nomcol= "Tâche Suivante";
            }
            if (column == 2) {
                nomcol= "Frequence";
            }
            if (column == 3) {
                nomcol= "Contexte";
            }
            return nomcol;            
        }
         @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0||columnIndex==1) {
                return Tache.class;
            } else if (columnIndex == 2) {
                return Integer.class;
            } else {
                return String.class;
            }
        }
         @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
           if(cbxPeri.getItemCount()>0){
                if(model.getPeri(cbxPeri.getSelectedItem().toString()).getClass()==Periode.class){
                    ArrayList<Transition> lstTran=model.getTran(cbxPeri.getSelectedItem().toString());
                    ArrayList<Integer> lstFre=model.getFre(cbxPeri.getSelectedItem().toString());
                    if(columnIndex==0){                        
                        return lstTran.get(rowIndex).getTache();                        
                    }else if(columnIndex==1){
                        return lstTran.get(rowIndex).getTacheSuivante();
                    }else{
                        return lstFre.get(rowIndex);
                    }
                }else{
                        ArrayList<TransitionContexte> lstTranCont=model.getTranCont(cbxPeri.getSelectedItem().toString());
                    ArrayList<Integer> lstFre=model.getFre(cbxPeri.getSelectedItem().toString());
                    if(columnIndex==0){                        
                        return lstTranCont.get(rowIndex).getTache();                        
                    }else if(columnIndex==1){
                        return lstTranCont.get(rowIndex).getTacheSuivante();
                    }else if(columnIndex==2){
                        return lstFre.get(rowIndex);
                    }else{
                        if(!lstTranCont.get(rowIndex).getMapAttr().isEmpty()){
                            return lstTranCont.get(rowIndex).getMapAttr().toString();
                        }else{
                            return "";
                        }
                    }
                }
           }else{
               return "what?";
           }
        }
        
         @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
              if(cbxPeri.getItemCount()>0){
                    if(model.getPeri(cbxPeri.getSelectedItem().toString()).getClass()==Periode.class){
                        Transition trs=model.getTran(cbxPeri.getSelectedItem().toString()).get(rowIndex);
                        model.ModifFre(cbxPeri.getSelectedItem().toString(), trs, (Integer)aValue);                        
                        fireTableDataChanged();                             
                    }
              }
        }
        
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
 
               return false;

        }       
    }
    private class ActionRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            DefaultTableCellRenderer defaultCR = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            defaultCR.setOpaque(true);
            defaultCR.setBackground(value instanceof Tache ?Color.gray:Color.white);
            return defaultCR;
        }
    }
}
