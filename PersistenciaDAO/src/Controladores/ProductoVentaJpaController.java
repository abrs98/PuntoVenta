/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Producto;
import Entidades.ProductoVenta;
import Entidades.Venta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abrahan Barrios
 */
public class ProductoVentaJpaController implements Serializable {

    public ProductoVentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductoVenta productoVenta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto idProducto = productoVenta.getIdProducto();
            if (idProducto != null) {
                idProducto = em.getReference(idProducto.getClass(), idProducto.getIdProducto());
                productoVenta.setIdProducto(idProducto);
            }
            Venta venta = productoVenta.getVenta();
            if (venta != null) {
                venta = em.getReference(venta.getClass(), venta.getIdVenta());
                productoVenta.setVenta(venta);
            }
            em.persist(productoVenta);
            if (idProducto != null) {
                idProducto.getProductoVentaList().add(productoVenta);
                idProducto = em.merge(idProducto);
            }
            if (venta != null) {
                venta.getProductoVentaList().add(productoVenta);
                venta = em.merge(venta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductoVenta productoVenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoVenta persistentProductoVenta = em.find(ProductoVenta.class, productoVenta.getIdProductoVenta());
            Producto idProductoOld = persistentProductoVenta.getIdProducto();
            Producto idProductoNew = productoVenta.getIdProducto();
            Venta ventaOld = persistentProductoVenta.getVenta();
            Venta ventaNew = productoVenta.getVenta();
            if (idProductoNew != null) {
                idProductoNew = em.getReference(idProductoNew.getClass(), idProductoNew.getIdProducto());
                productoVenta.setIdProducto(idProductoNew);
            }
            if (ventaNew != null) {
                ventaNew = em.getReference(ventaNew.getClass(), ventaNew.getIdVenta());
                productoVenta.setVenta(ventaNew);
            }
            productoVenta = em.merge(productoVenta);
            if (idProductoOld != null && !idProductoOld.equals(idProductoNew)) {
                idProductoOld.getProductoVentaList().remove(productoVenta);
                idProductoOld = em.merge(idProductoOld);
            }
            if (idProductoNew != null && !idProductoNew.equals(idProductoOld)) {
                idProductoNew.getProductoVentaList().add(productoVenta);
                idProductoNew = em.merge(idProductoNew);
            }
            if (ventaOld != null && !ventaOld.equals(ventaNew)) {
                ventaOld.getProductoVentaList().remove(productoVenta);
                ventaOld = em.merge(ventaOld);
            }
            if (ventaNew != null && !ventaNew.equals(ventaOld)) {
                ventaNew.getProductoVentaList().add(productoVenta);
                ventaNew = em.merge(ventaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productoVenta.getIdProductoVenta();
                if (findProductoVenta(id) == null) {
                    throw new NonexistentEntityException("The productoVenta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoVenta productoVenta;
            try {
                productoVenta = em.getReference(ProductoVenta.class, id);
                productoVenta.getIdProductoVenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoVenta with id " + id + " no longer exists.", enfe);
            }
            Producto idProducto = productoVenta.getIdProducto();
            if (idProducto != null) {
                idProducto.getProductoVentaList().remove(productoVenta);
                idProducto = em.merge(idProducto);
            }
            Venta venta = productoVenta.getVenta();
            if (venta != null) {
                venta.getProductoVentaList().remove(productoVenta);
                venta = em.merge(venta);
            }
            em.remove(productoVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductoVenta> findProductoVentaEntities() {
        return findProductoVentaEntities(true, -1, -1);
    }

    public List<ProductoVenta> findProductoVentaEntities(int maxResults, int firstResult) {
        return findProductoVentaEntities(false, maxResults, firstResult);
    }

    private List<ProductoVenta> findProductoVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductoVenta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProductoVenta findProductoVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductoVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductoVenta> rt = cq.from(ProductoVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
