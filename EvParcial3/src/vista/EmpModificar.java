package vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.Departamento;

public class EmpModificar extends javax.swing.JDialog {

    private final Empleado empleado;
    
    /**
     * Creates new form EmpModificar
     * @param parent
     * @param modal
     * @param e
     */
    public EmpModificar(java.awt.Frame parent, boolean modal, Empleado e) {
        super(parent, modal);
        initComponents();
        this.empleado = e;
        this.inicializar();
        this.setearCliente();
    }
    
    private void inicializar() {
        Departamento dep = new Departamento();
        ArrayList departamentos;
        try {
            departamentos = dep.read();
            this.cargarDeptos(departamentos);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EmpAgregar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EmpAgregar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void cargarDeptos(ArrayList departamentos) {
        cb_depto.removeAllItems();

        Iterator iterador = departamentos.iterator();
        while (iterador.hasNext()) {
            Object unObject = iterador.next();
            Departamento d = (Departamento) unObject;
            
            cb_depto.addItem(d.getNombreDept());
        }

        cb_depto.setSelectedIndex(0);
    }
    
    private void setearCliente() {
        /* Setea controles de la vista usando el estado del objeto */
        this.nombre.setText(this.empleado.getNombre());
        this.sueldo.setText(String.valueOf(this.empleado.getSueldo()));
        this.posicion.setText(this.empleado.getPosicion());
        
        try {
            this.cb_depto.setSelectedItem(this.empleado.getDeptById(this.empleado.getId()));
        } catch (Exception ex) {
            Logger.getLogger(EmpModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     private void update() {
        Departamento departamento = (Departamento) this.cb_depto.getSelectedItem();

        /* Setea el estado del objeto */
        this.empleado.setNombre((String) this.nombre.getText());
        this.empleado.setSueldo((Integer.parseInt(this.sueldo.getText())));
        this.empleado.setPosicion((String) this.posicion.getText());
        this.empleado.setDepartamento(departamento);
        
        /* Actualzia el objeto */
        try {
            this.empleado.update(this.empleado);
            JOptionPane.showMessageDialog(null, "Registro modificado exitosamente.");
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EmpModificar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(EmpModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        posicion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ModBtn = new javax.swing.JButton();
        nombre = new javax.swing.JTextField();
        CleanBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        ExitBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        sueldo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cb_depto = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Posición:");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        ModBtn.setText("Modificar");
        ModBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModBtnActionPerformed(evt);
            }
        });

        CleanBtn.setText("Limpiar");
        CleanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CleanBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Modificar Empleado");

        ExitBtn.setText("Chao pescao");
        ExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Sueldo:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Departamento:");

        cb_depto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_deptoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(100, 100, 100))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(posicion, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CleanBtn)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(ModBtn)
                                .addGap(37, 37, 37)
                                .addComponent(ExitBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(44, 44, 44)
                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_depto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(posicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cb_depto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(CleanBtn)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ModBtn)
                    .addComponent(ExitBtn))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_deptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_deptoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_deptoActionPerformed

    private void ExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBtnActionPerformed
        dispose();
    }//GEN-LAST:event_ExitBtnActionPerformed

    private void ModBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModBtnActionPerformed
        try {
            // Modifica el registro
            this.update();
        } catch (Exception ex) {
            Logger.getLogger(EmpModificar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ModBtnActionPerformed

    private void CleanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CleanBtnActionPerformed
        this.nombre.setText("");
        this.sueldo.setText("");
        this.posicion.setText("");
    }//GEN-LAST:event_CleanBtnActionPerformed

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
            java.util.logging.Logger.getLogger(EmpModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmpModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmpModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmpModificar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new EmpModificar().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CleanBtn;
    private javax.swing.JButton ExitBtn;
    private javax.swing.JButton ModBtn;
    private javax.swing.JComboBox<String> cb_depto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField posicion;
    private javax.swing.JTextField sueldo;
    // End of variables declaration//GEN-END:variables
}
