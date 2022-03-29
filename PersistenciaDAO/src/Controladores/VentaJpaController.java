/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.exceptions.IllegalOrphanException;
import Controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entidades.Usuario;
import Entidades.ProductoVenta;
import Entidades.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abrahan Barrios
 */
public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venta venta) {
        if (venta.getProductoVentaList() == null) {
            venta.setProductoVentaList(new ArrayList<ProductoVenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario idUsuario = venta.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                venta.setIdUsuario(idUsuario);
            }
            List<ProductoVenta> attachedProductoVentaList = new ArrayList<ProductoVenta>();
            for (ProductoVenta productoVentaListProductoVentaToAttach : venta.getProductoVentaList()) {
                productoVentaListProductoVentaToAttach = em.getReference(productoVentaListProductoVentaToAttach.getClass(), productoVentaListProductoVentaToAttach.getIdProductoVenta());
                attachedProductoVentaList.add(productoVentaListProductoVentaToAttach);
            }
            venta.setProductoVentaList(attachedProductoVentaList);
            em.persist(venta);
            if (idUsuario != null) {
                idUsuario.getVentaList().add(venta);
                idUsuario = em.merge(idUsuario);
            }
            for (ProductoVenta productoVentaListProductoVenta : venta.getProductoVentaList()) {
                Venta oldVentaOfProductoVentaListProductoVenta = productoVentaListProductoVenta.getVenta();
                productoVentaListProductoVenta.setVenta(venta);
                productoVentaListProductoVenta = em.merge(productoVentaListProductoVenta);
                if (oldVentaOfProductoVentaListProductoVenta != null) {
                    oldVentaOfProductoVentaListProductoVenta.getProductoVentaList().remove(productoVentaListProductoVenta);
                    oldVentaOfProductoVentaListProductoVenta = em.merge(oldVentaOfProductoVentaListProductoVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getIdVenta());
            Usuario idUsuarioOld = persistentVenta.getIdUsuario();
            Usuario idUsuarioNew = venta.getIdUsuario();
            List<ProductoVenta> productoVentaListOld = persistentVenta.getProductoVentaList();
            List<ProductoVenta> productoVentaListNew = venta.getProductoVentaList();
            List<String> illegalOrphanMessages = null;
            for (ProductoVenta productoVentaListOldProductoVenta : productoVentaListOld) {
                if (!productoVentaListNew.contains(productoVentaListOldProductoVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductoVenta " + productoVentaListOldProductoVenta + " since its venta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                venta.setIdUsuario(idUsuarioNew);
            }
            List<ProductoVenta> attachedProductoVentaListNew = new ArrayList<ProductoVenta>();
            for (ProductoVenta productoVentaListNewProductoVentaToAttach : productoVentaListNew) {
                productoVentaListNewProductoVentaToAttach = em.getReference(productoVentaListNewProductoVentaToAttach.getClass(), productoVentaListNewProductoVentaToAttach.getIdProductoVenta());
                attachedProductoVentaListNew.add(productoVentaListNewProductoVentaToAttach);
            }
            productoVentaListNew = attachedProductoVentaListNew;
            venta.setProductoVentaList(productoVentaListNew);
            venta = em.merge(venta);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getVentaList().remove(venta);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getVentaList().add(venta);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (ProductoVenta productoVentaListNewProductoVenta : productoVentaListNew) {
                if (!productoVentaListOld.contains(productoVentaListNewProductoVenta)) {
                    Venta oldVentaOfProductoVentaListNewProductoVenta = productoVentaListNewProductoVenta.getVenta();
                    productoVentaListNewProductoVenta.setVenta(venta);
                    productoVentaListNewProductoVenta = em.merge(productoVentaListNewProductoVenta);
                    if (oldVentaOfProductoVentaListNewProductoVenta != null && !oldVentaOfProductoVentaListNewProductoVenta.equals(venta)) {
                        oldVentaOfProductoVentaListNewProductoVenta.getProductoVentaList().remove(productoVentaListNewProductoVenta);
                        oldVentaOfProductoVentaListNewProductoVenta = em.merge(oldVentaOfProductoVentaListNewProductoVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venta.getIdVenta();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getIdVenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductoVenta> productoVentaListOrphanCheck = venta.getProductoVentaList();
            for (ProductoVenta productoVentaListOrphanCheckProductoVenta : productoVentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Venta (" + venta + ") cannot be destroyed since the ProductoVenta " + productoVentaListOrphanCheckProductoVenta + " in its productoVentaList field has a non-nullable venta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario idUsuario = venta.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getVentaList().remove(venta);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
