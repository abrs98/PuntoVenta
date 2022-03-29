/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import entidades.Producto;
import persistencia.ProductoDAO;

/**
 *
 * @author Abrahan Barrios
 */
public class ControlProducto {
    ProductoDAO producto=new ProductoDAO();
    
    
    public boolean guardarProducto(Producto producto) {
        return this.producto.insert(producto);
    }

    public boolean actualizarProducto(Producto producto) {
        return this.producto.update(producto);
    }

    public boolean eliminarProducto(Producto producto) {
        return this.producto.remove(producto);
    }

    public Producto consultarPorCodigo(String codigo) {
        return this.producto.findById(codigo);
    }

    public List<Producto> consultarProductos(String busqueda) {
        return this.producto.find(busqueda);
    }
    

}

