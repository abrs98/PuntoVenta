package entidades;

import entidades.ProductoVenta;
import entidades.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-29T06:24:32")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Date> fecha;
    public static volatile SingularAttribute<Venta, Float> total;
    public static volatile ListAttribute<Venta, ProductoVenta> productoVentaList;
    public static volatile SingularAttribute<Venta, Usuario> idUsuario;
    public static volatile SingularAttribute<Venta, Integer> idVenta;

}