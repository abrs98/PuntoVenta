/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazgráficapuntoventa;

import javax.swing.JOptionPane;
import GUI.FrmInicioSesion;
import GUI.FrmRegistroAdmin;
import control.ControlUsuario;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

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

        try {
            ControlUsuario CUsuario = new ControlUsuario();
            List<Usuario> usuarios = CUsuario.consultarUsuarios(null);
            System.out.println("Usuarios size -> " + usuarios.size());

            if (usuarios.isEmpty()) {
                FrmRegistroAdmin ad = new FrmRegistroAdmin();
                ad.setLocationRelativeTo(null);
                ad.setVisible(true);
            } else {
                FrmInicioSesion ad = new FrmInicioSesion();
                ad.setLocationRelativeTo(null);
                ad.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

    }

}
