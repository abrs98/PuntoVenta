package entidades;

import entidades.Categoria;
import entidades.ProductoVenta;
import entidades.Proveedor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-29T06:24:32")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> codigo;
    public static volatile SingularAttribute<Producto, Float> precio;
    public static volatile ListAttribute<Producto, ProductoVenta> productoVentaList;
    public static volatile SingularAttribute<Producto, String> unidadMedida;
    public static volatile SingularAttribute<Producto, Categoria> categoria;
    public static volatile SingularAttribute<Producto, Proveedor> proveedor;
    public static volatile SingularAttribute<Producto, Float> stock;
    public static volatile SingularAttribute<Producto, String> nombre;

}