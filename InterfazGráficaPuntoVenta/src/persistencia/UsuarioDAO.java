/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Usuario;
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
public class UsuarioDAO extends BaseDAO<Usuario>{
   EntityManager entityManager = getEntityManager();

    @Override
    public boolean insert(Usuario usuario) {
        //            INSERCIÓN
        entityManager.getTransaction().begin();
        if (usuario != null) {
            entityManager.persist(usuario);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Usuario usuario) {
        //       FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();
        Usuario update = entityManager.find(Usuario.class, usuario.getIdUsuario());
        if (update != null) {
            update.setNombre(usuario.getNombre());
            update.setContraseña(usuario.getContraseña());
            update.setAdministrador(usuario.getAdministrador());
            entityManager.persist(update);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean remove(Usuario usuario) {
        //      REMOVE
        entityManager.getTransaction().begin();
        Usuario remove = entityManager.find(Usuario.class, usuario.getIdUsuario());
        if (remove != null) {
            entityManager.remove(remove);
        } else {
            return false;
        }
        entityManager.getTransaction().commit();
        return true;
    }

   

    @Override
    public List<Usuario> find(String busqueda) {
        //          Consulta de datos
        entityManager.getTransaction().begin();
        if (busqueda == null || busqueda.isEmpty()) {

            //se crea el constructor de consultas
            CriteriaQuery criteria = entityManager.getCriteriaBuilder().createQuery();
            //se construye el objeto de consultas
            criteria.select(criteria.from(Usuario.class));
            //creación de la consulta lista para ejecutarse
            Query query = entityManager.createQuery(criteria);
            //ejecución del query y retorno de resultados
            List<Usuario> usuarios = query.getResultList();
            entityManager.getTransaction().commit();
            return usuarios;
        } else {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery criteria = builder.createQuery(Usuario.class);
            Root root = criteria.from(Usuario.class);
            criteria = criteria.select(root).where(builder.like(root.get("nombre"), "%"+busqueda+"%"));
            TypedQuery query = entityManager.createQuery(criteria);
            List<Usuario> usuarios = query.getResultList();
            entityManager.getTransaction().commit();
            return usuarios;
        }
    }

    

    @Override
    public Usuario findById(Object id) { 
        // FIND BY ID AND UPDATE
        entityManager.getTransaction().begin();
        
        Usuario find = entityManager.find(Usuario.class, id);
        if (find!= null){
            entityManager.getTransaction().commit();
            return find;
        }       
        entityManager.getTransaction().commit();
        return null;
        
        }

}
    
    
   

