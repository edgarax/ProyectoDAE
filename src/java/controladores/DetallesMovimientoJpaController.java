/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.DetallesMovimiento;
import modelo.DetallesMovimientoPK;
import modelo.Movimiento;
import modelo.Producto;

/**
 *
 * @author edgar
 */
public class DetallesMovimientoJpaController implements Serializable {

    public DetallesMovimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetallesMovimiento detallesMovimiento) throws PreexistingEntityException, Exception {
        if (detallesMovimiento.getDetallesMovimientoPK() == null) {
            detallesMovimiento.setDetallesMovimientoPK(new DetallesMovimientoPK());
        }
        detallesMovimiento.getDetallesMovimientoPK().setIdProducto(detallesMovimiento.getProducto().getIdProducto());
        detallesMovimiento.getDetallesMovimientoPK().setIdMovimiento(detallesMovimiento.getMovimiento().getIdMovimiento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimiento movimiento = detallesMovimiento.getMovimiento();
            if (movimiento != null) {
                movimiento = em.getReference(movimiento.getClass(), movimiento.getIdMovimiento());
                detallesMovimiento.setMovimiento(movimiento);
            }
            Producto producto = detallesMovimiento.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdProducto());
                detallesMovimiento.setProducto(producto);
            }
            em.persist(detallesMovimiento);
            if (movimiento != null) {
                movimiento.getDetallesMovimientoCollection().add(detallesMovimiento);
                movimiento = em.merge(movimiento);
            }
            if (producto != null) {
                producto.getDetallesMovimientoCollection().add(detallesMovimiento);
                producto = em.merge(producto);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallesMovimiento(detallesMovimiento.getDetallesMovimientoPK()) != null) {
                throw new PreexistingEntityException("DetallesMovimiento " + detallesMovimiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetallesMovimiento detallesMovimiento) throws NonexistentEntityException, Exception {
        detallesMovimiento.getDetallesMovimientoPK().setIdProducto(detallesMovimiento.getProducto().getIdProducto());
        detallesMovimiento.getDetallesMovimientoPK().setIdMovimiento(detallesMovimiento.getMovimiento().getIdMovimiento());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetallesMovimiento persistentDetallesMovimiento = em.find(DetallesMovimiento.class, detallesMovimiento.getDetallesMovimientoPK());
            Movimiento movimientoOld = persistentDetallesMovimiento.getMovimiento();
            Movimiento movimientoNew = detallesMovimiento.getMovimiento();
            Producto productoOld = persistentDetallesMovimiento.getProducto();
            Producto productoNew = detallesMovimiento.getProducto();
            if (movimientoNew != null) {
                movimientoNew = em.getReference(movimientoNew.getClass(), movimientoNew.getIdMovimiento());
                detallesMovimiento.setMovimiento(movimientoNew);
            }
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdProducto());
                detallesMovimiento.setProducto(productoNew);
            }
            detallesMovimiento = em.merge(detallesMovimiento);
            if (movimientoOld != null && !movimientoOld.equals(movimientoNew)) {
                movimientoOld.getDetallesMovimientoCollection().remove(detallesMovimiento);
                movimientoOld = em.merge(movimientoOld);
            }
            if (movimientoNew != null && !movimientoNew.equals(movimientoOld)) {
                movimientoNew.getDetallesMovimientoCollection().add(detallesMovimiento);
                movimientoNew = em.merge(movimientoNew);
            }
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getDetallesMovimientoCollection().remove(detallesMovimiento);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getDetallesMovimientoCollection().add(detallesMovimiento);
                productoNew = em.merge(productoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallesMovimientoPK id = detallesMovimiento.getDetallesMovimientoPK();
                if (findDetallesMovimiento(id) == null) {
                    throw new NonexistentEntityException("The detallesMovimiento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallesMovimientoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DetallesMovimiento detallesMovimiento;
            try {
                detallesMovimiento = em.getReference(DetallesMovimiento.class, id);
                detallesMovimiento.getDetallesMovimientoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallesMovimiento with id " + id + " no longer exists.", enfe);
            }
            Movimiento movimiento = detallesMovimiento.getMovimiento();
            if (movimiento != null) {
                movimiento.getDetallesMovimientoCollection().remove(detallesMovimiento);
                movimiento = em.merge(movimiento);
            }
            Producto producto = detallesMovimiento.getProducto();
            if (producto != null) {
                producto.getDetallesMovimientoCollection().remove(detallesMovimiento);
                producto = em.merge(producto);
            }
            em.remove(detallesMovimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetallesMovimiento> findDetallesMovimientoEntities() {
        return findDetallesMovimientoEntities(true, -1, -1);
    }

    public List<DetallesMovimiento> findDetallesMovimientoEntities(int maxResults, int firstResult) {
        return findDetallesMovimientoEntities(false, maxResults, firstResult);
    }

    private List<DetallesMovimiento> findDetallesMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetallesMovimiento.class));
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

    public DetallesMovimiento findDetallesMovimiento(DetallesMovimientoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetallesMovimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallesMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetallesMovimiento> rt = cq.from(DetallesMovimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
