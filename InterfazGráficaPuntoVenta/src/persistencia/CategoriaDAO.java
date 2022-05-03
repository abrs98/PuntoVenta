/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Abrahan Barrios
 */
public class CategoriaDAO extends BaseDAO<Categoria>{
   EntityManager entityManager = getEntityManager();

    @Override
    public boolean insert(Categoria categoria) {
        //            INSERCIÓN
        entityManager.getTransaction().begin();
        if (categoria != null) {
            entityManager.persist(categoria);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }
@Override
    public boolean update(Categoria categoria) {
        //       FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();
        Categoria update = entityManager.find(Categoria.class, categoria.getIdCategoria());
        if (update != null) {
            update.setNombre(categoria.getNombre());
            update.setDescripcion(categoria.getDescripcion());
            update.setProductoList(categoria.getProductoList());
            entityManager.persist(update);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean remove(Categoria categoria) {
        //      REMOVE
        entityManager.getTransaction().begin();
        Categoria remove = entityManager.find(Categoria.class, categoria.getIdCategoria());
        if (remove != null) {
            entityManager.remove(remove);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Categoria findById(Object id) {
        //       FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();
        Categoria update = entityManager.find(Categoria.class, id);
        if (update != null) {
             entityManager.getTransaction().commit();
            return update;
        } 
       entityManager.getTransaction().commit();
       return null;
    }
    
    public Categoria findByName(String nombre) {
        //       FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();
        Categoria update = entityManager.find(Categoria.class, nombre);
        if (update != null) {
             entityManager.getTransaction().commit();
            return update;
        } 
       entityManager.getTransaction().commit();
       return null;
    }

    @Override
    public List<Categoria> find(String busqueda) {
        //          Consulta de datos
        entityManager.getTransaction().begin();
        if (busqueda == null || busqueda.isEmpty()) {

            //se crea el constructor de consultas
            CriteriaQuery criteria = entityManager.getCriteriaBuilder().createQuery();
            //se construye el objeto de consultas
            criteria.select(criteria.from(Categoria.class));
            //creación de la consulta lista para ejecutarse
            Query query = entityManager.createQuery(criteria);
            //ejecución del query y retorno de resultados
            List<Categoria> categorias = query.getResultList();
            entityManager.getTransaction().commit();
            return categorias;
        } else {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(Categoria.class);
            Root root = criteria.from(Categoria.class);
            criteria = criteria.select(root).where(builder.like(root.get("nombre"), "%"+busqueda+"%"));
            TypedQuery query = entityManager.createQuery(criteria);
            List<Categoria> categorias = query.getResultList();
            entityManager.getTransaction().commit();
            return categorias;
        }
    }
}
