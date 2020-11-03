/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import proyectofinal.funciones.BaseDatos;
import proyectofinal.funciones.Cuenta;


/**
 *
 * @author FRAND
 */
public class LayoutPrincipal extends javax.swing.JFrame{

    /**
     * Creates new form LayoutPrincipal
     */
    /*OBJETOS PANELES*/
    Gui_Dashboard dash = new Gui_Dashboard();
    Gui_Transacciones gui_transacciones = new Gui_Transacciones();
    Gui_Transferencia gui_transferencia = new Gui_Transferencia();
    Gui_PagoServicios gui_pago = new Gui_PagoServicios();
    Gui_SaldoCuenta gui_saldo = new Gui_SaldoCuenta();
    Gui_Depositos gui_deposito = new Gui_Depositos();
    
    public static Cuenta cuenta;
    
    public static void cargarCuenta(String nroCuenta){
        BaseDatos bs = new BaseDatos();
        cuenta = bs.getAllDataCuenta(nroCuenta);
    }
    
    public void verificarCuenta(){
        if (cuenta == null){
            Gui_inicio.main(null);
            this.dispose();
        }
    }
    
    public void quitarPaneles(){
        gui_transacciones.setVisible(false);
        gui_pago.setVisible(false);
        gui_saldo.setVisible(false);
        gui_deposito.setVisible(false);
        gui_transferencia.setVisible(false);
        dash.setVisible(false);
    }
    
    public LayoutPrincipal() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/proyectofinal/iconos/iconDash.png")).getImage());
        this.setLocationRelativeTo(null);
        this.setTitle("Simulador WEB BANKING");
        setearLayout();
        
        contenedor.add(dash);  
        pack();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        btnDash = new javax.swing.JButton();
        btnPagoServicios = new javax.swing.JButton();
        btnDepositos = new javax.swing.JButton();
        btnTransacciones = new javax.swing.JButton();
        btnSaldo = new javax.swing.JButton();
        btnTransferencias = new javax.swing.JButton();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        lblNroCuenta = new javax.swing.JLabel();
        contenedor = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        menu.setBackground(new java.awt.Color(4, 8, 95));

        btnDash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectofinal/iconos/icons8-persona-de-sexo-masculino-30.png"))); // NOI18N
        btnDash.setText("Dashboard");
        btnDash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashActionPerformed(evt);
            }
        });

        btnPagoServicios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectofinal/iconos/icons8-pago-en-línea-30.png"))); // NOI18N
        btnPagoServicios.setText("Pago de Servicios");
        btnPagoServicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoServiciosActionPerformed(evt);
            }
        });

        btnDepositos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectofinal/iconos/icons8-dinero-30.png"))); // NOI18N
        btnDepositos.setText("Realizar Deposito");
        btnDepositos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDepositosActionPerformed(evt);
            }
        });

        btnTransacciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectofinal/iconos/icons8-cuenta-30.png"))); // NOI18N
        btnTransacciones.setText("Mis Transacciones");
        btnTransacciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaccionesActionPerformed(evt);
            }
        });

        btnSaldo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectofinal/iconos/icons8-caja-de-dinero-30.png"))); // NOI18N
        btnSaldo.setText("Saldo de Cuenta");
        btnSaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaldoActionPerformed(evt);
            }
        });

        btnTransferencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectofinal/iconos/icons8-intercambio-de-tarjetas-30.png"))); // NOI18N
        btnTransferencias.setText("Realizar Transferencia");
        btnTransferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferenciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPagoServicios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDepositos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTransacciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTransferencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDash, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPagoServicios, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDepositos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTransferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        getContentPane().add(menu, java.awt.BorderLayout.LINE_START);

        header.setBackground(new java.awt.Color(80, 125, 188));

        jLabel1.setFont(new java.awt.Font("HP Simplified", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Simulador de WebBanking");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        lblNombre.setFont(new java.awt.Font("HP Simplified", 1, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre.setText("Usuario");

        btnClose.setText("Cerrar Sesión");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        lblNroCuenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNroCuenta.setForeground(new java.awt.Color(255, 255, 255));
        lblNroCuenta.setText("Nro Cuenta");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 519, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(lblNroCuenta))
                .addGap(43, 43, 43)
                .addComponent(btnClose)
                .addGap(36, 36, 36))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnClose))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(headerLayout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNroCuenta)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        contenedor.setBackground(new java.awt.Color(240, 255, 251));
        contenedor.setLayout(new java.awt.BorderLayout());
        getContentPane().add(contenedor, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPagoServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoServiciosActionPerformed
        quitarPaneles();
        gui_pago.setVisible(true);
        contenedor.add(gui_pago);
    }//GEN-LAST:event_btnPagoServiciosActionPerformed

    private void btnDepositosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDepositosActionPerformed
        quitarPaneles();
        gui_deposito.setVisible(true);
        contenedor.add(gui_deposito);
    }//GEN-LAST:event_btnDepositosActionPerformed

    private void btnTransaccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaccionesActionPerformed
        quitarPaneles();
        gui_transacciones.setVisible(true);
        gui_transacciones.cargarTabla();
        contenedor.add(gui_transacciones);
    }//GEN-LAST:event_btnTransaccionesActionPerformed

    private void btnSaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaldoActionPerformed
        quitarPaneles();
        gui_saldo.setVisible(true);
        gui_saldo.limpiarSaldo();
        contenedor.add(gui_saldo);
        
    }//GEN-LAST:event_btnSaldoActionPerformed

    private void btnTransferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferenciasActionPerformed
        quitarPaneles();
        gui_transferencia.setVisible(true);
        contenedor.add(gui_transferencia);
    }//GEN-LAST:event_btnTransferenciasActionPerformed

    private void btnDashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashActionPerformed
        quitarPaneles();
        dash.setVisible(true);
        contenedor.add(dash);
    }//GEN-LAST:event_btnDashActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        JOptionPane.showMessageDialog(null, "Trabajo Realizado por el grupo 3 LP2 2020");
    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        cuenta=null;
        Gui_inicio.main(null);
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    public void setearLayout(){
        lblNombre.setText(cuenta.getCliente().getNombre()+" "+cuenta.getCliente().getApellido());
        lblNroCuenta.setText(lblNroCuenta.getText()+" "+cuenta.getNroCuenta());
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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LayoutPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LayoutPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LayoutPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LayoutPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        try{
            cargarCuenta(args[0]);
            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new LayoutPrincipal().setVisible(true);
                }
            });
        }catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Logueate para acceder");
            Gui_inicio.main(null);
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDash;
    private javax.swing.JButton btnDepositos;
    private javax.swing.JButton btnPagoServicios;
    private javax.swing.JButton btnSaldo;
    private javax.swing.JButton btnTransacciones;
    private javax.swing.JButton btnTransferencias;
    public javax.swing.JPanel contenedor;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNroCuenta;
    private javax.swing.JPanel menu;
    // End of variables declaration//GEN-END:variables


}
