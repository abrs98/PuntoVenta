/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import GUI.*;
import control.*;
import entidades.Categoria;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import net.sourceforge.barbecue.BarcodeFactory;

public class Pruebas {

    public static void main(String args[]) throws Exception {

        DlgGeneraradorCodigos codigos = new DlgGeneraradorCodigos(true);

        FrmConsultarReporte consultarReporte = new FrmConsultarReporte();
        FrmRegistroProducto registroProducto = new FrmRegistroProducto();

        String codigo = "";
        int checksum = 0;

        for (int i = 0; i < 20; i++) {
            codigo = codigos.generateRandomEANCode();
            System.out.println(codigo);
            checksum = codigos.controlCodeCalculator(codigo);
            System.out.println(checksum);

            BufferedImage imagenCodigo;
            try {

                imagenCodigo = codigos.generateEAN13BarcodeImage(codigo);
                ImageIcon icod = new ImageIcon(imagenCodigo.getScaledInstance(280, 100, BufferedImage.TYPE_INT_RGB));

                codigo = codigo + checksum;
            } catch (Exception ex) {
                Logger.getLogger(DlgGeneraradorCodigos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
