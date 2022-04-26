/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import control.ControlProducto;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

/**
 *
 * @author Abrahan Barrios
 */
public class DlgGeneraradorCodigos extends javax.swing.JDialog {

    public String codigo;
    private BufferedImage imagenCodigo = null;
    ControlProducto CProducto = new ControlProducto();

    /**
     * Creates new form DlgGeneraradorCodigos
     */
    public DlgGeneraradorCodigos(boolean modal) {
        super((java.awt.Frame) null, true);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();
    }

    public BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {

        Barcode barcode = BarcodeFactory.createEAN13(barcodeText);
        //JOptionPane.showMessageDialog(null, "Codigo creado : "+barcode.getComponentCount());
        barcode.setBarHeight(42);
        barcode.setBarWidth(1);

        barcode.setFont(new Font("sans", Font.BOLD, 10));

        return BarcodeImageHandler.getImage(barcode);
    }

    /**
     * Genera un String que representa un codigo aleatorio de 12 numeros entre 0
     * y 10
     *
     * @return randomCode
     */
    public String generateRandomEANCode() {
        String randomCode = "";
        double random = 0;
        int number = 0;

        for (int i = 0; i < 12; i++) {
            random = Math.random() * 10;
            number = (int) Math.floor(random);
            if (number == 10) {
                number = 0;
            }
            randomCode += String.valueOf(number);
        }
        return randomCode;
    }

    private BufferedImage copy(BufferedImage img, int imageType) {
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImage = new BufferedImage(width, height, imageType);
        Graphics g = newImage.createGraphics();

        g.drawImage(img, 0, 0, null);

        g.dispose();

        return newImage;
    }

    /**
     * Cálculo del código de control
     */
    private int controlCodeCalculator(String firstTwelveDigits) {
        char[] charDigits = firstTwelveDigits.toCharArray();
        int[] ean13
                = {
                    1, 3
                };
        int sum = 0;
        for (int i = 0; i < charDigits.length; i++) {
            sum += Character.getNumericValue(charDigits[i]) * ean13[i % 2];
        }
        int checksum = 10 - sum % 10;

        if (checksum == 10) {
            checksum = 0;
        }

        return checksum;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblClave = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGenerar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lblImageCode = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 0));
        jPanel1.setForeground(new java.awt.Color(255, 102, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(506, 289));
        jPanel1.setMinimumSize(new java.awt.Dimension(506, 289));

        jLabel2.setFont(new java.awt.Font("Ebrima", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("GENERADOR DE CODIGOS DE BARRAS EAN ");

        lblClave.setForeground(new java.awt.Color(255, 153, 0));
        lblClave.setText("Codigo");

        btnGuardar.setBackground(new java.awt.Color(0, 102, 255));
        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(0, 102, 255));
        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGenerar.setBackground(new java.awt.Color(0, 204, 0));
        btnGenerar.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnGenerar.setForeground(new java.awt.Color(51, 51, 51));
        btnGenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/generate-barcode.png"))); // NOI18N
        btnGenerar.setText("Generar");
        btnGenerar.setFocusable(false);
        btnGenerar.setIconTextGap(8);
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        lblImageCode.setText("IMAGE");
        lblImageCode.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EAN-13", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        jScrollPane1.setViewportView(lblImageCode);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblClave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerar)
                .addGap(34, 34, 34))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(btnCancelar)
                        .addGap(73, 73, 73)
                        .addComponent(btnGuardar)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnGenerar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lblClave)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:

        if (imagenCodigo == null) {
            JOptionPane.showMessageDialog(null, "Genere el codigo del producto", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileFilter(new FileNameExtensionFilter("JPEG file", "jpg", "jpeg"));
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage oi = copy(imagenCodigo, BufferedImage.TYPE_INT_RGB);
                System.out.println("Esta instalado el plugin> " + ImageIO.write(oi, "jpeg", fileChooser.getSelectedFile()));
                this.dispose();
            } catch (IOException ex) {
                System.out.println("Failed to save image!");
            }
        } else {
            System.out.println("No file choosen!");
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        // TODO add your handling code here:

        try {
            codigo = generateRandomEANCode();
            
            while (CProducto.consultarPorCodigo(codigo) != null) {
                codigo = generateRandomEANCode();
            }
            
            imagenCodigo = generateEAN13BarcodeImage(codigo);
            ImageIcon icod = new ImageIcon(imagenCodigo.getScaledInstance(280, 100, BufferedImage.TYPE_INT_RGB));
            lblImageCode.setIcon(icod);
            lblImageCode.setText("");
            codigo= codigo+controlCodeCalculator(codigo);
        } catch (Exception ex) {
            Logger.getLogger(DlgGeneraradorCodigos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGenerarActionPerformed

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
            java.util.logging.Logger.getLogger(DlgGeneraradorCodigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DlgGeneraradorCodigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DlgGeneraradorCodigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DlgGeneraradorCodigos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgGeneraradorCodigos dialog = new DlgGeneraradorCodigos(true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblClave;
    private javax.swing.JLabel lblImageCode;
    // End of variables declaration//GEN-END:variables
}
