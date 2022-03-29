/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Abrahan Barrios
 */
public class FrmAdministrador extends javax.swing.JFrame {

    String usuario = "Administrador";
    int idAdmin;

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Creates new form FrmAdministrador
     */
    public FrmAdministrador() {
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

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        escritorio = new javax.swing.JDesktopPane();
        menuBar = new javax.swing.JMenuBar();
        mnuAdm = new javax.swing.JMenu();
        opcMnuAcc = new javax.swing.JMenuItem();
        opcMnuSalir = new javax.swing.JMenuItem();
        mnuVen = new javax.swing.JMenu();
        opcMnuNuevaV = new javax.swing.JMenuItem();
        opcMnuConsultarV = new javax.swing.JMenuItem();
        mnuCajero = new javax.swing.JMenu();
        opcMnuNuevoA = new javax.swing.JMenuItem();
        opcMnuNuevoC = new javax.swing.JMenuItem();
        mnuProducto = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        opcMnuRegistrarProducto = new javax.swing.JMenuItem();
        opcMnuActualizarProducto = new javax.swing.JMenuItem();
        opcMnuEliminarProducto = new javax.swing.JMenuItem();
        opcMnuBuscarProducto = new javax.swing.JMenuItem();
        opcMnuEdicionCategoria = new javax.swing.JMenuItem();
        opcMnuEdicionProveedor = new javax.swing.JMenuItem();
        opcMnuEdicionCategoria1 = new javax.swing.JMenuItem();
        mnuAyuda = new javax.swing.JMenu();
        opcMnuAcerca = new javax.swing.JMenuItem();

        jMenu2.setText("File");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(300, 60));

        escritorio.setBackground(new java.awt.Color(4, 102, 200));
        escritorio.setBorder(new javax.swing.border.MatteBorder(null));
        escritorio.setPreferredSize(new java.awt.Dimension(720, 615));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 718, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 613, Short.MAX_VALUE)
        );

        mnuAdm.setMnemonic('f');
        mnuAdm.setText("Administrador");

        opcMnuAcc.setText("Accesos");
        opcMnuAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcMnuAccActionPerformed(evt);
            }
        });
        mnuAdm.add(opcMnuAcc);

        opcMnuSalir.setMnemonic('x');
        opcMnuSalir.setText("Salir");
        opcMnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcMnuSalirActionPerformed(evt);
            }
        });
        mnuAdm.add(opcMnuSalir);

        menuBar.add(mnuAdm);

        mnuVen.setMnemonic('h');
        mnuVen.setText("Venta");

        opcMnuNuevaV.setMnemonic('o');
        opcMnuNuevaV.setText("Nueva");
        opcMnuNuevaV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcMnuNuevaVActionPerformed(evt);
            }
        });
        mnuVen.add(opcMnuNuevaV);

        opcMnuConsultarV.setText("Editar");
        opcMnuConsultarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcMnuConsultarVActionPerformed(evt);
            }
        });
        mnuVen.add(opcMnuConsultarV);

        menuBar.add(mnuVen);

        mnuCajero.setMnemonic('e');
        mnuCajero.setText("Usuarios");

        opcMnuNuevoA.setMnemonic('o');
        opcMnuNuevoA.setText("Administrador");
        opcMnuNuevoA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcMnuNuevoAActionPerformed(evt);
            }
        });
        mnuCajero.add(opcMnuNuevoA);

        opcMnuNuevoC.setMnemonic('o');
        opcMnuNuevoC.setText("Cajero");
        opcMnuNuevoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcMnuNuevoCActionPerformed(evt);
            }
        });
        mnuCajero.add(opcMnuNuevoC);

        menuBar.add(mnuCajero);

        mnuProducto.setText("Inventario");

        jMenu1.setText("Producto");

        opcMnuRegistrarProducto.setText("Registrar");
        opcMnuRegistrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcMnuRegistrarProductoActionPerformed(evt);
            }
        });
        jMenu1.add(opcMnuRegistrarProducto);

        opcMnuActualizarProducto.setText("Actualizar");
        jMenu1.add(opcMnuActualizarProducto);

        opcMnuEliminarProducto.setText("Eliminar");
        opcMnuEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcMnuEliminarProductoActionPerformed(evt);
            }
        });
        jMenu1.add(opcMnuEliminarProducto);

        opcMnuBuscarProducto.setText("Buscar");
        jMenu1.add(opcMnuBuscarProducto);

        mnuProducto.add(jMenu1);

        opcMnuEdicionCategoria.setText(" Categoria");
        opcMnuEdicionCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcMnuEdicionCategoriaActionPerformed(evt);
            }
        });
        mnuProducto.add(opcMnuEdicionCategoria);

        opcMnuEdicionProveedor.setText(" Proveedor");
        opcMnuEdicionProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcMnuEdicionProveedorActionPerformed(evt);
            }
        });
        mnuProducto.add(opcMnuEdicionProveedor);

        opcMnuEdicionCategoria1.setText("Consultar reporte de venta");
        opcMnuEdicionCategoria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcMnuEdicionCategoria1ActionPerformed(evt);
            }
        });
        mnuProducto.add(opcMnuEdicionCategoria1);

        menuBar.add(mnuProducto);

        mnuAyuda.setText("Ayuda");

        opcMnuAcerca.setText("Acerca De");
        opcMnuAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcMnuAcercaActionPerformed(evt);
            }
        });
        mnuAyuda.add(opcMnuAcerca);

        menuBar.add(mnuAyuda);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(escritorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void opcMnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcMnuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_opcMnuSalirActionPerformed

    private void opcMnuNuevoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcMnuNuevoCActionPerformed
        // TODO add your handling code here:
   
    }//GEN-LAST:event_opcMnuNuevoCActionPerformed

    private void opcMnuNuevoAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcMnuNuevoAActionPerformed
        // TODO add your handling code here:
        


    }//GEN-LAST:event_opcMnuNuevoAActionPerformed

    private void opcMnuNuevaVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcMnuNuevaVActionPerformed
        // TODO add your handling code here:
        FrmRegistroVentas venta = new FrmRegistroVentas();
        venta.setTipoUsuario(usuario);
        venta.setIdAdmin(idAdmin);
        escritorio.add(venta).setVisible(true);
    }//GEN-LAST:event_opcMnuNuevaVActionPerformed

    private void opcMnuConsultarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcMnuConsultarVActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_opcMnuConsultarVActionPerformed

    private void opcMnuAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcMnuAcercaActionPerformed
        // TODO add your handling code here:
        FrmAyuda ayuda = new FrmAyuda();
        escritorio.add(ayuda).setVisible(true);
    }//GEN-LAST:event_opcMnuAcercaActionPerformed

    private void opcMnuEdicionProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcMnuEdicionProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcMnuEdicionProveedorActionPerformed

    private void opcMnuEdicionCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcMnuEdicionCategoriaActionPerformed

    }//GEN-LAST:event_opcMnuEdicionCategoriaActionPerformed

    private void opcMnuAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcMnuAccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcMnuAccActionPerformed

    private void opcMnuEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcMnuEliminarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcMnuEliminarProductoActionPerformed

    private void opcMnuRegistrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcMnuRegistrarProductoActionPerformed
        try {
            FrmRegistroProductoB p = new FrmRegistroProductoB();
            escritorio.add(p).setVisible(true);
            p.setLocation(escritorio.getWidth()/5, escritorio.getHeight()/7);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
    }//GEN-LAST:event_opcMnuRegistrarProductoActionPerformed

    private void opcMnuEdicionCategoria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcMnuEdicionCategoria1ActionPerformed
        FrmConsultarReporte reporte = new FrmConsultarReporte();
        
        escritorio.add(reporte).setVisible(true);
    }//GEN-LAST:event_opcMnuEdicionCategoria1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnuAdm;
    private javax.swing.JMenu mnuAyuda;
    private javax.swing.JMenu mnuCajero;
    private javax.swing.JMenu mnuProducto;
    private javax.swing.JMenu mnuVen;
    private javax.swing.JMenuItem opcMnuAcc;
    private javax.swing.JMenuItem opcMnuAcerca;
    private javax.swing.JMenuItem opcMnuActualizarProducto;
    private javax.swing.JMenuItem opcMnuBuscarProducto;
    private javax.swing.JMenuItem opcMnuConsultarV;
    private javax.swing.JMenuItem opcMnuEdicionCategoria;
    private javax.swing.JMenuItem opcMnuEdicionCategoria1;
    javax.swing.JMenuItem opcMnuEdicionProveedor;
    private javax.swing.JMenuItem opcMnuEliminarProducto;
    private javax.swing.JMenuItem opcMnuNuevaV;
    private javax.swing.JMenuItem opcMnuNuevoA;
    private javax.swing.JMenuItem opcMnuNuevoC;
    private javax.swing.JMenuItem opcMnuRegistrarProducto;
    private javax.swing.JMenuItem opcMnuSalir;
    // End of variables declaration//GEN-END:variables

}
