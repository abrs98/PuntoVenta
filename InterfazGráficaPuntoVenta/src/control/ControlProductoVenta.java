/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entidades.ProductoVenta;
import java.util.List;
import persistencia.ProductoVentaDAO;

/**
 *
 * @author Abrahan Barrios
 */
public class ControlProductoVenta {
    ProductoVentaDAO productoVenta =new ProductoVentaDAO();
    
    
    public boolean guardarProductoVenta(ProductoVenta productoVenta) {
        return this.productoVenta.insert(productoVenta);
    }

    public boolean actualizarProductoVenta(ProductoVenta productoVenta) {
        return this.productoVenta.update(productoVenta);
    }

    public boolean eliminarProductoVenta(ProductoVenta productoVenta) {
        return this.productoVenta.remove(productoVenta);
    }

    public ProductoVenta consultarPorIdProductoVenta(Integer id) {
        return this.productoVenta.findById(id);
    }

    public List<ProductoVenta> consultarProductosVentas(String busqueda) {
        return this.productoVenta.find(busqueda);
    }
    

}

