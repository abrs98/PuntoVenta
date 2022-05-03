/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import control.ControlProducto;
import control.ControlProductoVenta;
import control.ControlVenta;
import entidades.Producto;
import entidades.ProductoVenta;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Michelle Naffate
 */
public class FrmConsultarReporte extends javax.swing.JInternalFrame {

    ControlProducto CProducto = new ControlProducto();
    ControlVenta CVenta = new ControlVenta();
    ControlProductoVenta CProductoventa = new ControlProductoVenta();

    /**
     * Creates new form ConsultarReporte
     */
    public FrmConsultarReporte() {
        initComponents();
        CrearModelo();
    }

    DefaultTableModel modelo2;

    private void CrearModelo() {
        try {
            modelo2 = (new DefaultTableModel(
                    null, new String[]{
                        "Fecha", "ID Venta",
                        "Producto", "Cantidad", "Precio", "Importe"}) {
                Class[] types = new Class[]{
                    java.lang.String.class, //aqui es el tipo de columna, dejemoslo en STRING
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,
                    java.lang.String.class,};
                boolean[] canEdit = new boolean[]{ //aquí decimos si podrán ser editables o no.
                    false, false, false, false, false, false
                };

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int rowIndex, int colIndex) {
                    return canEdit[colIndex];
                }
            });
            TblListaVenta.setModel(modelo2);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error");
        }
    }

    private void Cargar_Informacion(String nombre, Date di, Date df) { //sirve para cargar la información de la base de datos y mostrarla en el JFrame (tabla).
        try {
            System.out.println("si se está ejecutando"); //mensaje para saber si se está ejecutando el método.

            Object O[] = null;

            List<ProductoVenta> list = CProductoventa.consultarProductosVentas(null);

            List<ProductoVenta> listPV = new ArrayList<>();

            //Busqueda de productos venta de acuerdo a parametros
            ProductoVenta pv;
            Date dv;
            
            if (nombre == null) {
                for (int i = 0; i < list.size(); i++) {
                    pv = list.get(i);
                    dv = pv.getVenta().getFecha();
                    if (dv.after(di) && dv.before(df)) {
                        listPV.add(pv);
                    }
                }
            } else {

                for (int i = 0; i < list.size(); i++) {
                    pv = list.get(i);
                    dv = pv.getVenta().getFecha();
                    if (dv.after(di) && dv.before(df) && pv.getIdProducto().getNombre().equals(nombre)) {
                        listPV.add(pv);
                    }
                }
            }

            if(listPV.isEmpty()){
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
            }
            float importe = 0.0f;
            //para buscar personas usaremos un ciclo for.
            for (int i = 0; i < listPV.size(); i++) {
                System.out.println("" + listPV.get(i).getIdProducto().getNombre()); //hasta este paso, solo sale la info en consola, aun no se muestra en la tabla.
                modelo2.addRow(O); //aquí pediremos que se agregue la informacion del arreglo,
                modelo2.setValueAt(listPV.get(i).getVenta().getFecha(), i, 0); //el numero al final 0 indica el lugar donde se mostrará en la tabla la información..
                modelo2.setValueAt(listPV.get(i).getVenta().getIdVenta(), i, 1);
                modelo2.setValueAt(listPV.get(i).getIdProducto().getNombre(), i, 2);
                modelo2.setValueAt(listPV.get(i).getCantidad(), i, 3);
                modelo2.setValueAt(listPV.get(i).getPrecio(), i, 4);
                modelo2.setValueAt(listPV.get(i).getImporte(), i, 5);

                importe += listPV.get(i).getImporte();
            }

            String imp = String.format("%.2f", importe);
            jTextField1.setText(imp);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

//     private void seleccionar() {
//         
//        String id = tabla.getValueAt(tabla.getSelectedRow(), 0).toString();
//        String nombre = tabla.getValueAt(tabla.getSelectedRow(), 1).toString();
//        String precio = tabla.getValueAt(tabla.getSelectedRow(), 2).toString();
//        String cantidad = tabla.getValueAt(tabla.getSelectedRow(), 3).toString();
//        String categoria = tabla.getValueAt(tabla.getSelectedRow(), 4).toString();
//        String proveedor = tabla.getValueAt(tabla.getSelectedRow(), 5).toString();
//        
//
//        lblIdTexto.setText(id);
//        txtNombre.setText(nombre);
//        txtPrecio.setText(precio);
//        txtCantidad.setText(cantidad);
//        
//         for (int i = 0; i < cbxCategoria.getItemCount(); i++) {
//             if(cbxCategoria.getItemAt(i).equalsIgnoreCase(categoria))
//                 cbxCategoria.setSelectedIndex(i);
//         }
//         
//         for (int i = 0; i < cbxProveedor.getItemCount(); i++) {
//             if(cbxProveedor.getItemAt(i).equalsIgnoreCase(proveedor))
//                 cbxProveedor.setSelectedIndex(i);
//         }
//        
//    }
    private void fillComboProductos() {

        List<Producto> productos = CProducto.consultarProductos(null);

        for (int i = 0; i < cbxProducto.getItemCount(); i++) {
            cbxProducto.removeItemAt(i);
            cbxProducto.removeAllItems();

        }

        try {
            cbxProducto.addItem("Todos");

            for (int i = 0; i < productos.size(); i++) {
                String formato = productos.get(i).getNombre();
                cbxProducto.addItem(formato);
            }

        } catch (PersistenceException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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

        escritorio = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblListaVenta = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jFechaInicial = new com.toedter.calendar.JDateChooser();
        cbxProducto = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        txtActualizarProducto1 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jFechaFinal = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Consultar Reporte de Venta");
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        escritorio.setBackground(new java.awt.Color(151, 157, 172));

        TblListaVenta.setBackground(new java.awt.Color(151, 157, 172));
        TblListaVenta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        TblListaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Nombre de Producto", "Precio Unitario"
            }
        ));
        jScrollPane1.setViewportView(TblListaVenta);

        jPanel3.setBackground(new java.awt.Color(151, 157, 172));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Generar Reporte"));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Producto:");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Fecha  inicial:");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        cbxProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccionar-" }));
        cbxProducto.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                cbxProductoAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        btnBuscar.setBackground(new java.awt.Color(3, 83, 164));
        btnBuscar.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Generar");
        btnBuscar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnBuscar.setIconTextGap(10);
        btnBuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtActualizarProducto1.setBackground(new java.awt.Color(3, 83, 164));
        txtActualizarProducto1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        txtActualizarProducto1.setForeground(new java.awt.Color(255, 255, 255));
        txtActualizarProducto1.setText("Limpiar");
        txtActualizarProducto1.setIconTextGap(10);
        txtActualizarProducto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActualizarProducto1ActionPerformed(evt);
            }
        });

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Fecha final:");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(5, 5, 5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(15, 15, 15)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jFechaFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(jFechaInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtActualizarProducto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel17))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(cbxProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(txtActualizarProducto1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setText("Total Venta:");

        jTextField1.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 18, 51));
        jLabel2.setText("Reporte");

        jLabel3.setBackground(new java.awt.Color(0, 40, 85));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Reporte de ventas");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setOpaque(true);

        escritorio.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        escritorio.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escritorioLayout.createSequentialGroup()
                        .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(escritorioLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escritorioLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(304, 304, 304))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escritorioLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(228, 228, 228))))
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escritorioLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        getAccessibleContext().setAccessibleName("Consaultar Reporte");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Seccion para consultar reporte de ventas");
    }//GEN-LAST:event_formAncestorAdded

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (jFechaInicial.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar una fecha inicial!");
            return;
        } else if (jFechaFinal.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe proporcionar una fecha final!");
            return;
        } else if (jFechaInicial.getDate().after(jFechaFinal.getDate())) {
            JOptionPane.showMessageDialog(null, "Las fechas no fueron ingresadas correctamente!");
            jFechaFinal.setDate(null);
            jFechaInicial.setDate(null);
            return;
        }

        String nombreP;
        if (cbxProducto.getSelectedIndex() != 0) {
            nombreP = cbxProducto.getSelectedItem().toString();
            //JOptionPane.showMessageDialog(null, "El nombre del producto es "+nombreP);
        } else {
            nombreP = null;
        }
        CrearModelo();
        Date di = jFechaInicial.getDate();
        Date df = jFechaFinal.getDate();
        Cargar_Informacion(nombreP, di, df);

    }//GEN-LAST:event_btnBuscarActionPerformed
    private void limpiarCajas() {
        jFechaInicial.setDate(null);
        jFechaFinal.setDate(null);
        cbxProducto.setSelectedIndex(0);
    }
    private void txtActualizarProducto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActualizarProducto1ActionPerformed
        limpiarCajas();
    }//GEN-LAST:event_txtActualizarProducto1ActionPerformed

    private void cbxProductoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cbxProductoAncestorAdded
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            fillComboProductos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_cbxProductoAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TblListaVenta;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JComboBox<String> cbxProducto;
    private javax.swing.JDesktopPane escritorio;
    private com.toedter.calendar.JDateChooser jFechaFinal;
    private com.toedter.calendar.JDateChooser jFechaInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton txtActualizarProducto1;
    // End of variables declaration//GEN-END:variables
}
