/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Vítor
 */
public class JanelaConsulta extends javax.swing.JFrame {

    /**
     * Creates new form janelaConsulta
     */
    public JanelaConsulta() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lModelo = new javax.swing.JLabel();
        lPlaca = new javax.swing.JLabel();
        tPlaca = new javax.swing.JTextField();
        lMarca = new javax.swing.JLabel();
        tMarca = new javax.swing.JTextField();
        lValor = new javax.swing.JLabel();
        tValor = new javax.swing.JTextField();
        btnRegistrarInteresse = new javax.swing.JButton();
        btnLocar = new javax.swing.JButton();
        lStatus = new javax.swing.JLabel();
        tStatus = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lModelo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lModelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lModelo.setText("Modelo do carro");

        lPlaca.setText("Placa");

        tPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tPlacaActionPerformed(evt);
            }
        });

        lMarca.setText("Marca");

        tMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tMarcaActionPerformed(evt);
            }
        });

        lValor.setText("Valor da diária");

        btnRegistrarInteresse.setText("Registrar Interesse");

        btnLocar.setText("Locar");

        lStatus.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lModelo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lPlaca)
                                    .addComponent(lMarca))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tPlaca)
                                    .addComponent(tMarca, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(72, 72, 72)
                                        .addComponent(btnLocar))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lValor)
                                            .addComponent(lStatus))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tValor, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                            .addComponent(tStatus)))))
                            .addComponent(btnRegistrarInteresse))
                        .addGap(0, 116, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPlaca)
                    .addComponent(tPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lValor)
                    .addComponent(tValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lMarca)
                    .addComponent(tMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lStatus)
                    .addComponent(tStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarInteresse)
                    .addComponent(btnLocar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tPlacaActionPerformed

    private void tMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tMarcaActionPerformed

    public JTextField gettStatus() {
        return tStatus;
    }

    public void settStatus(JTextField tStatus) {
        this.tStatus = tStatus;
    }

    public JButton getBtnLocar() {
        return btnLocar;
    }

    public void setBtnLocar(JButton btnLocar) {
        this.btnLocar = btnLocar;
    }

    public JButton getBtnRegistrarInteresse() {
        return btnRegistrarInteresse;
    }

    public void setBtnRegistrarInteresse(JButton btnRegistrarInteresse) {
        this.btnRegistrarInteresse = btnRegistrarInteresse;
    }

    public JLabel getlMarca() {
        return lMarca;
    }

    public void setlMarca(JLabel lMarca) {
        this.lMarca = lMarca;
    }

    public JLabel getlModelo() {
        return lModelo;
    }

    public void setlModelo(JLabel lModelo) {
        this.lModelo = lModelo;
    }

    public JLabel getlPlaca() {
        return lPlaca;
    }

    public void setlPlaca(JLabel lPlaca) {
        this.lPlaca = lPlaca;
    }

    public JLabel getlValor() {
        return lValor;
    }

    public void setlValor(JLabel lValor) {
        this.lValor = lValor;
    }

    public JTextField gettMarca() {
        return tMarca;
    }

    public void settMarca(JTextField tMarca) {
        this.tMarca = tMarca;
    }

    public JTextField gettPlaca() {
        return tPlaca;
    }

    public void settPlaca(JTextField tPlaca) {
        this.tPlaca = tPlaca;
    }

    public JTextField gettValor() {
        return tValor;
    }

    public void settValor(JTextField tValor) {
        this.tValor = tValor;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaConsulta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLocar;
    private javax.swing.JButton btnRegistrarInteresse;
    private javax.swing.JLabel lMarca;
    private javax.swing.JLabel lModelo;
    private javax.swing.JLabel lPlaca;
    private javax.swing.JLabel lStatus;
    private javax.swing.JLabel lValor;
    private javax.swing.JTextField tMarca;
    private javax.swing.JTextField tPlaca;
    private javax.swing.JTextField tStatus;
    private javax.swing.JTextField tValor;
    // End of variables declaration//GEN-END:variables
}