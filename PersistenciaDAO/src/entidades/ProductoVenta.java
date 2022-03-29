/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abrahan Barrios
 */
@Entity
@Table(name = "producto_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoVenta.findAll", query = "SELECT p FROM ProductoVenta p")
    , @NamedQuery(name = "ProductoVenta.findByIdProductoVenta", query = "SELECT p FROM ProductoVenta p WHERE p.idProductoVenta = :idProductoVenta")
    , @NamedQuery(name = "ProductoVenta.findByCantidad", query = "SELECT p FROM ProductoVenta p WHERE p.cantidad = :cantidad")
    , @NamedQuery(name = "ProductoVenta.findByPrecio", query = "SELECT p FROM ProductoVenta p WHERE p.precio = :precio")
    , @NamedQuery(name = "ProductoVenta.findByImporte", query = "SELECT p FROM ProductoVenta p WHERE p.importe = :importe")})
public class ProductoVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto_venta")
    private Integer idProductoVenta;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private float cantidad;
    @Basic(optional = false)
    @Column(name = "precio")
    private float precio;
    @Basic(optional = false)
    @Column(name = "importe")
    private float importe;
    @JoinColumn(name = "id_producto", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Producto idProducto;
    @JoinColumns({
        @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
        , @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")})
    @ManyToOne(optional = false)
    private Venta venta;

    public ProductoVenta() {
    }

    public ProductoVenta(Integer idProductoVenta) {
        this.idProductoVenta = idProductoVenta;
    }

    public ProductoVenta(Integer idProductoVenta, float cantidad, float precio, float importe) {
        this.idProductoVenta = idProductoVenta;
        this.cantidad = cantidad;
        this.precio = precio;
        this.importe = importe;
    }

    public Integer getIdProductoVenta() {
        return idProductoVenta;
    }

    public void setIdProductoVenta(Integer idProductoVenta) {
        this.idProductoVenta = idProductoVenta;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProductoVenta != null ? idProductoVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoVenta)) {
            return false;
        }
        ProductoVenta other = (ProductoVenta) object;
        if ((this.idProductoVenta == null && other.idProductoVenta != null) || (this.idProductoVenta != null && !this.idProductoVenta.equals(other.idProductoVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ProductoVenta[ idProductoVenta=" + idProductoVenta + " ]";
    }
    
}
