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
import entidades.Producto;

/**
 *
 * @author Abrahan Barrios
 */
public class ProductoDAO extends BaseDAO<Producto>{
   EntityManager entityManager = getEntityManager();

    @Override
    public boolean insert(Producto producto) {
        //            INSERCIÓN
        entityManager.getTransaction().begin();
        if (producto != null) {
            entityManager.persist(producto);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Producto producto) {
        //       FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();
        Producto update = entityManager.find(Producto.class, producto.getCodigo());
        if (update != null) {
            update.setNombre(producto.getNombre());
            update.setPrecio(producto.getPrecio());
            update.setStock(producto.getStock());
            update.setCategoria(producto.getCategoria());
            update.setProveedor(producto.getProveedor());
            entityManager.persist(update);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean remove(Producto producto) {
        //      REMOVE
        entityManager.getTransaction().begin();
        Producto remove = entityManager.find(Producto.class, producto.getCodigo());
        if (remove != null) {
            entityManager.remove(remove);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

   

    @Override
    public List<Producto> find(String busqueda) {
        //          Consulta de datos
        entityManager.getTransaction().begin();
        if (busqueda == null || busqueda.isEmpty()) {

            //se crea el constructor de consultas
            CriteriaQuery criteria = entityManager.getCriteriaBuilder().createQuery();
            //se construye el objeto de consultas
            criteria.select(criteria.from(Producto.class));
            //creación de la consulta lista para ejecutarse
            Query query = entityManager.createQuery(criteria);
            //ejecución del query y retorno de resultados
            List<Producto> productos = query.getResultList();
            entityManager.getTransaction().commit();
            return productos;
        } else {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(Producto.class);
            Root root = criteria.from(Producto.class);
            criteria = criteria.select(root).where(builder.like(root.get("nombre"), "%"+busqueda+"%"));
            TypedQuery query = entityManager.createQuery(criteria);
            List<Producto> productos = query.getResultList();
            entityManager.getTransaction().commit();
            return productos;
        }
    }

    

    @Override
    public Producto findById(Object id) { 
        // FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();
        
        Producto find = entityManager.find(Producto.class, id);
        if (find!= null){
            entityManager.getTransaction().commit();
            return find;
        }       
        entityManager.getTransaction().commit();
        return null;
        
        }

}
    
    
   

