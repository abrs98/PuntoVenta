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
import entidades.Proveedor;

/**
 *
 * @author Abrahan Barrios
 */
public class ProveedorDAO extends BaseDAO<Proveedor>{
   EntityManager entityManager = getEntityManager();

   @Override 
   public boolean insert(Proveedor proveedor) {
        //            INSERCIÓN
        entityManager.getTransaction().begin();
        if (proveedor != null) {
            entityManager.persist(proveedor);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }
@Override
    public boolean update(Proveedor proveedor) {
        //       FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();
        Proveedor update = entityManager.find(Proveedor.class, proveedor.getIdProveedor());
        if (update != null) {
            update.setNombre(proveedor.getNombre());
            update.setDireccion(proveedor.getDireccion());
            update.setTelefono(proveedor.getTelefono());
            update.setCorreo(proveedor.getCorreo());
            update.setDescripcion(proveedor.getDescripcion());
            update.setProductoList(proveedor.getProductoList());
            entityManager.persist(update);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }
    
    @Override
    public boolean remove(Proveedor proveedor) {
        //      REMOVE
        entityManager.getTransaction().begin();
        Proveedor remove = entityManager.find(Proveedor.class, proveedor.getIdProveedor());
        if (remove != null) {
            entityManager.remove(remove);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

    
   @Override
    public Proveedor findById(Object id) {
        //       FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();

        Proveedor update = entityManager.find(Proveedor.class, id);
        if (update != null) {
             entityManager.getTransaction().commit();
            return update;
            
        } 
       entityManager.getTransaction().commit();
       return null;
    }
    
   
    
    @Override
    public List<Proveedor> find(String busqueda) {
        //          Consulta de datos
        entityManager.getTransaction().begin();
        if (busqueda == null || busqueda.isEmpty()) {

            //se crea el constructor de consultas
            CriteriaQuery criteria = entityManager.getCriteriaBuilder().createQuery();
            //se construye el objeto de consultas
            criteria.select(criteria.from(Proveedor.class));
            //creación de la consulta lista para ejecutarse
            Query query = entityManager.createQuery(criteria);
            //ejecución del query y retorno de resultados
            List<Proveedor> proveedores = query.getResultList();
            entityManager.getTransaction().commit();
            return proveedores;
        } else {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(Proveedor.class);
            Root root = criteria.from(Proveedor.class);
            criteria = criteria.select(root).where(builder.like(root.get("nombre"), "%"+busqueda+"%"));
            TypedQuery query = entityManager.createQuery(criteria);
            List<Proveedor> proveedores = query.getResultList();
            entityManager.getTransaction().commit();
            return proveedores;
        }
    }

}
