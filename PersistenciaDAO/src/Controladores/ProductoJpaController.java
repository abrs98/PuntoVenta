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
import Entidades.Categoria;
import Entidades.Producto;
import Entidades.Proveedor;
import Entidades.ProductoVenta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Abrahan Barrios
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getProductoVentaList() == null) {
            producto.setProductoVentaList(new ArrayList<ProductoVenta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoria = producto.getCategoria();
            if (categoria != null) {
                categoria = em.getReference(categoria.getClass(), categoria.getIdCategoria());
                producto.setCategoria(categoria);
            }
            Proveedor proveedor = producto.getProveedor();
            if (proveedor != null) {
                proveedor = em.getReference(proveedor.getClass(), proveedor.getIdProveedor());
                producto.setProveedor(proveedor);
            }
            List<ProductoVenta> attachedProductoVentaList = new ArrayList<ProductoVenta>();
            for (ProductoVenta productoVentaListProductoVentaToAttach : producto.getProductoVentaList()) {
                productoVentaListProductoVentaToAttach = em.getReference(productoVentaListProductoVentaToAttach.getClass(), productoVentaListProductoVentaToAttach.getIdProductoVenta());
                attachedProductoVentaList.add(productoVentaListProductoVentaToAttach);
            }
            producto.setProductoVentaList(attachedProductoVentaList);
            em.persist(producto);
            if (categoria != null) {
                categoria.getProductoList().add(producto);
                categoria = em.merge(categoria);
            }
            if (proveedor != null) {
                proveedor.getProductoList().add(producto);
                proveedor = em.merge(proveedor);
            }
            for (ProductoVenta productoVentaListProductoVenta : producto.getProductoVentaList()) {
                Producto oldIdProductoOfProductoVentaListProductoVenta = productoVentaListProductoVenta.getIdProducto();
                productoVentaListProductoVenta.setIdProducto(producto);
                productoVentaListProductoVenta = em.merge(productoVentaListProductoVenta);
                if (oldIdProductoOfProductoVentaListProductoVenta != null) {
                    oldIdProductoOfProductoVentaListProductoVenta.getProductoVentaList().remove(productoVentaListProductoVenta);
                    oldIdProductoOfProductoVentaListProductoVenta = em.merge(oldIdProductoOfProductoVentaListProductoVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getIdProducto());
            Categoria categoriaOld = persistentProducto.getCategoria();
            Categoria categoriaNew = producto.getCategoria();
            Proveedor proveedorOld = persistentProducto.getProveedor();
            Proveedor proveedorNew = producto.getProveedor();
            List<ProductoVenta> productoVentaListOld = persistentProducto.getProductoVentaList();
            List<ProductoVenta> productoVentaListNew = producto.getProductoVentaList();
            List<String> illegalOrphanMessages = null;
            for (ProductoVenta productoVentaListOldProductoVenta : productoVentaListOld) {
                if (!productoVentaListNew.contains(productoVentaListOldProductoVenta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductoVenta " + productoVentaListOldProductoVenta + " since its idProducto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (categoriaNew != null) {
                categoriaNew = em.getReference(categoriaNew.getClass(), categoriaNew.getIdCategoria());
                producto.setCategoria(categoriaNew);
            }
            if (proveedorNew != null) {
                proveedorNew = em.getReference(proveedorNew.getClass(), proveedorNew.getIdProveedor());
                producto.setProveedor(proveedorNew);
            }
            List<ProductoVenta> attachedProductoVentaListNew = new ArrayList<ProductoVenta>();
            for (ProductoVenta productoVentaListNewProductoVentaToAttach : productoVentaListNew) {
                productoVentaListNewProductoVentaToAttach = em.getReference(productoVentaListNewProductoVentaToAttach.getClass(), productoVentaListNewProductoVentaToAttach.getIdProductoVenta());
                attachedProductoVentaListNew.add(productoVentaListNewProductoVentaToAttach);
            }
            productoVentaListNew = attachedProductoVentaListNew;
            producto.setProductoVentaList(productoVentaListNew);
            producto = em.merge(producto);
            if (categoriaOld != null && !categoriaOld.equals(categoriaNew)) {
                categoriaOld.getProductoList().remove(producto);
                categoriaOld = em.merge(categoriaOld);
            }
            if (categoriaNew != null && !categoriaNew.equals(categoriaOld)) {
                categoriaNew.getProductoList().add(producto);
                categoriaNew = em.merge(categoriaNew);
            }
            if (proveedorOld != null && !proveedorOld.equals(proveedorNew)) {
                proveedorOld.getProductoList().remove(producto);
                proveedorOld = em.merge(proveedorOld);
            }
            if (proveedorNew != null && !proveedorNew.equals(proveedorOld)) {
                proveedorNew.getProductoList().add(producto);
                proveedorNew = em.merge(proveedorNew);
            }
            for (ProductoVenta productoVentaListNewProductoVenta : productoVentaListNew) {
                if (!productoVentaListOld.contains(productoVentaListNewProductoVenta)) {
                    Producto oldIdProductoOfProductoVentaListNewProductoVenta = productoVentaListNewProductoVenta.getIdProducto();
                    productoVentaListNewProductoVenta.setIdProducto(producto);
                    productoVentaListNewProductoVenta = em.merge(productoVentaListNewProductoVenta);
                    if (oldIdProductoOfProductoVentaListNewProductoVenta != null && !oldIdProductoOfProductoVentaListNewProductoVenta.equals(producto)) {
                        oldIdProductoOfProductoVentaListNewProductoVenta.getProductoVentaList().remove(productoVentaListNewProductoVenta);
                        oldIdProductoOfProductoVentaListNewProductoVenta = em.merge(oldIdProductoOfProductoVentaListNewProductoVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getIdProducto();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
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
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getIdProducto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductoVenta> productoVentaListOrphanCheck = producto.getProductoVentaList();
            for (ProductoVenta productoVentaListOrphanCheckProductoVenta : productoVentaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the ProductoVenta " + productoVentaListOrphanCheckProductoVenta + " in its productoVentaList field has a non-nullable idProducto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoria categoria = producto.getCategoria();
            if (categoria != null) {
                categoria.getProductoList().remove(producto);
                categoria = em.merge(categoria);
            }
            Proveedor proveedor = producto.getProveedor();
            if (proveedor != null) {
                proveedor.getProductoList().remove(producto);
                proveedor = em.merge(proveedor);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
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

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
