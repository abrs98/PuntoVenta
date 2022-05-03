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
import entidades.Venta;

/**
 *
 * @author Abrahan Barrios
 */
public class VentaDAO extends BaseDAO<Venta>{
   EntityManager entityManager = getEntityManager();

    @Override
    public boolean insert(Venta venta) {
        //            INSERCIÓN
        entityManager.getTransaction().begin();
        if (venta != null) {
            entityManager.persist(venta);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Venta venta) {
        //       FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();
        Venta update = entityManager.find(Venta.class, venta.getIdVenta());
        if (update != null) {
            update.setFecha(venta.getFecha());
            update.setIdUsuario(venta.getIdUsuario());
            update.setTotal(venta.getTotal());
            entityManager.persist(update);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean remove(Venta venta) {
        //      REMOVE
        entityManager.getTransaction().begin();
        Venta remove = entityManager.find(Venta.class, venta.getIdUsuario());
        if (remove != null) {
            entityManager.remove(remove);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

   

    @Override
    public List<Venta> find(String busqueda) {
        //          Consulta de datos
        entityManager.getTransaction().begin();
        if (busqueda == null || busqueda.isEmpty()) {

            //se crea el constructor de consultas
            CriteriaQuery criteria = entityManager.getCriteriaBuilder().createQuery();
            //se construye el objeto de consultas
            criteria.select(criteria.from(Venta.class));
            //creación de la consulta lista para ejecutarse
            Query query = entityManager.createQuery(criteria);
            //ejecución del query y retorno de resultados
            List<Venta> ventas = query.getResultList();
            entityManager.getTransaction().commit();
            return ventas;
        } else {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(Venta.class);
            Root root = criteria.from(Venta.class);
            criteria = criteria.select(root).where(builder.like(root.get("nombre"), "%"+busqueda+"%"));
            TypedQuery query = entityManager.createQuery(criteria);
            List<Venta> ventas = query.getResultList();
            entityManager.getTransaction().commit();
            return ventas;
        }
    }

    

    @Override
    public Venta findById(Object id) { 
        // FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();
        
        Venta find = entityManager.find(Venta.class, id);
        if (find!= null){
            entityManager.getTransaction().commit();
            return find;
        }       
        entityManager.getTransaction().commit();
        return null;
        
        }

}
    
    
   

