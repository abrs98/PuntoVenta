package entidades;

import entidades.Producto;
import entidades.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-29T06:24:32")
@StaticMetamodel(ProductoVenta.class)
public class ProductoVenta_ { 

    public static volatile SingularAttribute<ProductoVenta, Float> precio;
    public static volatile SingularAttribute<ProductoVenta, Venta> venta;
    public static volatile SingularAttribute<ProductoVenta, Float> cantidad;
    public static volatile SingularAttribute<ProductoVenta, Producto> idProducto;
    public static volatile SingularAttribute<ProductoVenta, Integer> idProductoVenta;
    public static volatile SingularAttribute<ProductoVenta, Float> importe;

}