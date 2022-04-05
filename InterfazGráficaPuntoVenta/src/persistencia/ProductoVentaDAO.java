/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.ProductoVenta;

/**
 *
 * @author Abrahan Barrios
 */
public class ProductoVentaDAO extends BaseDAO<ProductoVenta>{
   EntityManager entityManager = getEntityManager();

    @Override
    public boolean insert(ProductoVenta productoVenta) {
        //            INSERCIÓN
        entityManager.getTransaction().begin();
        if (productoVenta != null) {
            entityManager.persist(productoVenta);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(ProductoVenta productoVenta) {
        //       FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();
        ProductoVenta update = entityManager.find(ProductoVenta.class, productoVenta.getIdProductoVenta());
        if (update != null) {
            update.setVenta(productoVenta.getVenta());
            update.setCantidad(productoVenta.getCantidad());
            update.setPrecio(productoVenta.getPrecio());
            update.setImporte(productoVenta.getImporte());
            update.setIdProducto(productoVenta.getIdProducto());
            
            entityManager.persist(update);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean remove(ProductoVenta productoVenta) {
        //      REMOVE
        entityManager.getTransaction().begin();
        ProductoVenta remove = entityManager.find(ProductoVenta.class, productoVenta.getIdProductoVenta());
        if (remove != null) {
            entityManager.remove(remove);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

   

    @Override
    public List<ProductoVenta> find(String busqueda) {
        //          Consulta de datos
        entityManager.getTransaction().begin();
        if (busqueda == null || busqueda.isEmpty()) {

            //se crea el constructor de consultas
            CriteriaQuery criteria = entityManager.getCriteriaBuilder().createQuery();
            //se construye el objeto de consultas
            criteria.select(criteria.from(ProductoVenta.class));
            //creación de la consulta lista para ejecutarse
            Query query = entityManager.createQuery(criteria);
            //ejecución del query y retorno de resultados
            List<ProductoVenta> productosVenta = query.getResultList();
            entityManager.getTransaction().commit();
            return productosVenta;
        } else {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(ProductoVenta.class);
            Root root = criteria.from(ProductoVenta.class);
            criteria = criteria.select(root).where(builder.like(root.join("id_producto").get("nombre"), "%"+busqueda+"%"));
            TypedQuery query = entityManager.createQuery(criteria);
            List<ProductoVenta> productosVenta = query.getResultList();
            entityManager.getTransaction().commit();
            return productosVenta;
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public ProductoVenta findById(Object id) { 
        // FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();
        
        ProductoVenta find = entityManager.find(ProductoVenta.class, id);
        if (find!= null){
            entityManager.getTransaction().commit();
            return find;
        }       
        entityManager.getTransaction().commit();
        return null;
        
        }

}
    
    
   

