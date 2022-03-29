/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Felipe Torres
 */
public class Venta extends Usuario{
    private int id;
    private ArrayList<DetalleVenta> ventas;
    private Date fecha;
    private float total;
    private Usuario usuario;

    public Venta() {
    }
    
    public Venta(int id, ArrayList<DetalleVenta> ventas, Date fecha, float total, Usuario usuario) {
        this.id = id;
        this.ventas = ventas;
        this.fecha = fecha;
        this.total = total;
        this.usuario = usuario;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<DetalleVenta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<DetalleVenta> ventas) {
        this.ventas = ventas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float calcularTota() {
        int total = 0;
        
        for (int i = 0; i < this.ventas.size(); i++) {
            
            total += ventas.get(i).getPrecio();
        }
        
        return total;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venta other = (Venta) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id=" + id + ", ventas=" + ventas + ", fecha=" + fecha + ", total=" + total + ", usuario=" + usuario.getNombre();
    }
    
    
    
}
