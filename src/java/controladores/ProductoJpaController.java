/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Usuario;
import modelo.DetallesMovimiento;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Producto;

/**
 *
 * @author edgar
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
        if (producto.getDetallesMovimientoCollection() == null) {
            producto.setDetallesMovimientoCollection(new ArrayList<DetallesMovimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioCreacion = producto.getUsuarioCreacion();
            if (usuarioCreacion != null) {
                usuarioCreacion = em.getReference(usuarioCreacion.getClass(), usuarioCreacion.getIdUsuario());
                producto.setUsuarioCreacion(usuarioCreacion);
            }
            Usuario usuarioModificacion = producto.getUsuarioModificacion();
            if (usuarioModificacion != null) {
                usuarioModificacion = em.getReference(usuarioModificacion.getClass(), usuarioModificacion.getIdUsuario());
                producto.setUsuarioModificacion(usuarioModificacion);
            }
            Collection<DetallesMovimiento> attachedDetallesMovimientoCollection = new ArrayList<DetallesMovimiento>();
            for (DetallesMovimiento detallesMovimientoCollectionDetallesMovimientoToAttach : producto.getDetallesMovimientoCollection()) {
                detallesMovimientoCollectionDetallesMovimientoToAttach = em.getReference(detallesMovimientoCollectionDetallesMovimientoToAttach.getClass(), detallesMovimientoCollectionDetallesMovimientoToAttach.getDetallesMovimientoPK());
                attachedDetallesMovimientoCollection.add(detallesMovimientoCollectionDetallesMovimientoToAttach);
            }
            producto.setDetallesMovimientoCollection(attachedDetallesMovimientoCollection);
            em.persist(producto);
            if (usuarioCreacion != null) {
                usuarioCreacion.getProductoCollection().add(producto);
                usuarioCreacion = em.merge(usuarioCreacion);
            }
            if (usuarioModificacion != null) {
                usuarioModificacion.getProductoCollection().add(producto);
                usuarioModificacion = em.merge(usuarioModificacion);
            }
            for (DetallesMovimiento detallesMovimientoCollectionDetallesMovimiento : producto.getDetallesMovimientoCollection()) {
                Producto oldProductoOfDetallesMovimientoCollectionDetallesMovimiento = detallesMovimientoCollectionDetallesMovimiento.getProducto();
                detallesMovimientoCollectionDetallesMovimiento.setProducto(producto);
                detallesMovimientoCollectionDetallesMovimiento = em.merge(detallesMovimientoCollectionDetallesMovimiento);
                if (oldProductoOfDetallesMovimientoCollectionDetallesMovimiento != null) {
                    oldProductoOfDetallesMovimientoCollectionDetallesMovimiento.getDetallesMovimientoCollection().remove(detallesMovimientoCollectionDetallesMovimiento);
                    oldProductoOfDetallesMovimientoCollectionDetallesMovimiento = em.merge(oldProductoOfDetallesMovimientoCollectionDetallesMovimiento);
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
            Usuario usuarioCreacionOld = persistentProducto.getUsuarioCreacion();
            Usuario usuarioCreacionNew = producto.getUsuarioCreacion();
            Usuario usuarioModificacionOld = persistentProducto.getUsuarioModificacion();
            Usuario usuarioModificacionNew = producto.getUsuarioModificacion();
            Collection<DetallesMovimiento> detallesMovimientoCollectionOld = persistentProducto.getDetallesMovimientoCollection();
            Collection<DetallesMovimiento> detallesMovimientoCollectionNew = producto.getDetallesMovimientoCollection();
            List<String> illegalOrphanMessages = null;
            for (DetallesMovimiento detallesMovimientoCollectionOldDetallesMovimiento : detallesMovimientoCollectionOld) {
                if (!detallesMovimientoCollectionNew.contains(detallesMovimientoCollectionOldDetallesMovimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetallesMovimiento " + detallesMovimientoCollectionOldDetallesMovimiento + " since its producto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioCreacionNew != null) {
                usuarioCreacionNew = em.getReference(usuarioCreacionNew.getClass(), usuarioCreacionNew.getIdUsuario());
                producto.setUsuarioCreacion(usuarioCreacionNew);
            }
            if (usuarioModificacionNew != null) {
                usuarioModificacionNew = em.getReference(usuarioModificacionNew.getClass(), usuarioModificacionNew.getIdUsuario());
                producto.setUsuarioModificacion(usuarioModificacionNew);
            }
            Collection<DetallesMovimiento> attachedDetallesMovimientoCollectionNew = new ArrayList<DetallesMovimiento>();
            for (DetallesMovimiento detallesMovimientoCollectionNewDetallesMovimientoToAttach : detallesMovimientoCollectionNew) {
                detallesMovimientoCollectionNewDetallesMovimientoToAttach = em.getReference(detallesMovimientoCollectionNewDetallesMovimientoToAttach.getClass(), detallesMovimientoCollectionNewDetallesMovimientoToAttach.getDetallesMovimientoPK());
                attachedDetallesMovimientoCollectionNew.add(detallesMovimientoCollectionNewDetallesMovimientoToAttach);
            }
            detallesMovimientoCollectionNew = attachedDetallesMovimientoCollectionNew;
            producto.setDetallesMovimientoCollection(detallesMovimientoCollectionNew);
            producto = em.merge(producto);
            if (usuarioCreacionOld != null && !usuarioCreacionOld.equals(usuarioCreacionNew)) {
                usuarioCreacionOld.getProductoCollection().remove(producto);
                usuarioCreacionOld = em.merge(usuarioCreacionOld);
            }
            if (usuarioCreacionNew != null && !usuarioCreacionNew.equals(usuarioCreacionOld)) {
                usuarioCreacionNew.getProductoCollection().add(producto);
                usuarioCreacionNew = em.merge(usuarioCreacionNew);
            }
            if (usuarioModificacionOld != null && !usuarioModificacionOld.equals(usuarioModificacionNew)) {
                usuarioModificacionOld.getProductoCollection().remove(producto);
                usuarioModificacionOld = em.merge(usuarioModificacionOld);
            }
            if (usuarioModificacionNew != null && !usuarioModificacionNew.equals(usuarioModificacionOld)) {
                usuarioModificacionNew.getProductoCollection().add(producto);
                usuarioModificacionNew = em.merge(usuarioModificacionNew);
            }
            for (DetallesMovimiento detallesMovimientoCollectionNewDetallesMovimiento : detallesMovimientoCollectionNew) {
                if (!detallesMovimientoCollectionOld.contains(detallesMovimientoCollectionNewDetallesMovimiento)) {
                    Producto oldProductoOfDetallesMovimientoCollectionNewDetallesMovimiento = detallesMovimientoCollectionNewDetallesMovimiento.getProducto();
                    detallesMovimientoCollectionNewDetallesMovimiento.setProducto(producto);
                    detallesMovimientoCollectionNewDetallesMovimiento = em.merge(detallesMovimientoCollectionNewDetallesMovimiento);
                    if (oldProductoOfDetallesMovimientoCollectionNewDetallesMovimiento != null && !oldProductoOfDetallesMovimientoCollectionNewDetallesMovimiento.equals(producto)) {
                        oldProductoOfDetallesMovimientoCollectionNewDetallesMovimiento.getDetallesMovimientoCollection().remove(detallesMovimientoCollectionNewDetallesMovimiento);
                        oldProductoOfDetallesMovimientoCollectionNewDetallesMovimiento = em.merge(oldProductoOfDetallesMovimientoCollectionNewDetallesMovimiento);
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
            Collection<DetallesMovimiento> detallesMovimientoCollectionOrphanCheck = producto.getDetallesMovimientoCollection();
            for (DetallesMovimiento detallesMovimientoCollectionOrphanCheckDetallesMovimiento : detallesMovimientoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the DetallesMovimiento " + detallesMovimientoCollectionOrphanCheckDetallesMovimiento + " in its detallesMovimientoCollection field has a non-nullable producto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuarioCreacion = producto.getUsuarioCreacion();
            if (usuarioCreacion != null) {
                usuarioCreacion.getProductoCollection().remove(producto);
                usuarioCreacion = em.merge(usuarioCreacion);
            }
            Usuario usuarioModificacion = producto.getUsuarioModificacion();
            if (usuarioModificacion != null) {
                usuarioModificacion.getProductoCollection().remove(producto);
                usuarioModificacion = em.merge(usuarioModificacion);
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
