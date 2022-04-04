/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.ProductoVenta;
import entidades.Usuario;
import java.util.List;
import persistencia.UsuarioDAO;

/**
 *
 * @author Abrahan Barrios
 */
public class ControlUsuario {
    UsuarioDAO usuario =new UsuarioDAO();
    
    
    public boolean guardarUsuario(Usuario usuario) {
        return this.usuario.insert(usuario);
    }

    public boolean actualizarUsuario(Usuario usuario) {
        return this.usuario.update(usuario);
    }

    public boolean eliminarUsuario(Usuario usuario) {
        return this.usuario.remove(usuario);
    }

    public Usuario consultarPorIdUsuario(Integer id) {
        return this.usuario.findById(id);
    }

    public List<Usuario> consultarUsuarios(String busqueda) {
        return this.usuario.find(busqueda);
    }
    

}

