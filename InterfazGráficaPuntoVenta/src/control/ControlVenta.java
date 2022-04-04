/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import entidades.Venta;
import persistencia.VentaDAO;

/**
 *
 * @author Abrahan Barrios
 */
public class ControlVenta {
    VentaDAO venta =new VentaDAO();
    
    
    public boolean guardarVenta(Venta venta) {
        return this.venta.insert(venta);
    }

    public boolean actualizarVenta(Venta venta) {
        return this.venta.update(venta);
    }

    public boolean eliminarVenta(Venta venta) {
        return this.venta.remove(venta);
    }

    public Venta consultarPorIdVenta(Integer id) {
        return this.venta.findById(id);
    }

    public List<Venta> consultarVentas(String busqueda) {
        return this.venta.find(busqueda);
    }
    

}

