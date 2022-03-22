
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Felipe Torres
 */
public abstract class MovimientoInventario{
    
    private Producto producto;
    private float cantidad;

    public MovimientoInventario() {
    }

    public MovimientoInventario(Producto producto, float cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return  "producto=" + producto.getNombre() + ", cantidad=" + cantidad;
    }
    
    
}
