/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazgráficapuntoventa;

import javax.swing.JOptionPane;
import GUI.FrmInicioSesion;
import GUI.FrmRegistroAdmin;

/**
 *
 * @author Abrahan Barrios
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        FrmRegistroAdmin ad = new FrmRegistroAdmin();
        ad.setLocationRelativeTo(null);
        ad.setVisible(true);

    }

}