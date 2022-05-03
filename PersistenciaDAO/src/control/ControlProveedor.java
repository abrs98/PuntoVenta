/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import entidades.Proveedor;
import persistencia.ProveedorDAO;

/**
 *
 * @author Abrahan Barrios
 */
public class ControlProveedor {
    ProveedorDAO proveedor=new ProveedorDAO();
    
    public boolean guardarProveedor(Proveedor proveedor) {
        return this.proveedor.insert(proveedor);
    }

    public boolean actualizarProveedor(Proveedor proveedor) {
        return this.proveedor.update(proveedor);
    }

    public boolean eliminarProveedor(Proveedor proveedor) {
        return this.proveedor.remove(proveedor);
    }

    public Proveedor consultarPorIdProveedor(Integer id) {
        return this.proveedor.findById(id);
    }

    public List<Proveedor> consultarProveedores(String busqueda) {
        return this.proveedor.find(busqueda);
    }
}

