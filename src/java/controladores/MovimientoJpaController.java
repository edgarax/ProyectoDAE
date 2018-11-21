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
import modelo.Movimiento;

/**
 *
 * @author edgar
 */
public class MovimientoJpaController implements Serializable {

    public MovimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Movimiento movimiento) {
        if (movimiento.getDetallesMovimientoCollection() == null) {
            movimiento.setDetallesMovimientoCollection(new ArrayList<DetallesMovimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioModificacion = movimiento.getUsuarioModificacion();
            if (usuarioModificacion != null) {
                usuarioModificacion = em.getReference(usuarioModificacion.getClass(), usuarioModificacion.getIdUsuario());
                movimiento.setUsuarioModificacion(usuarioModificacion);
            }
            Collection<DetallesMovimiento> attachedDetallesMovimientoCollection = new ArrayList<DetallesMovimiento>();
            for (DetallesMovimiento detallesMovimientoCollectionDetallesMovimientoToAttach : movimiento.getDetallesMovimientoCollection()) {
                detallesMovimientoCollectionDetallesMovimientoToAttach = em.getReference(detallesMovimientoCollectionDetallesMovimientoToAttach.getClass(), detallesMovimientoCollectionDetallesMovimientoToAttach.getDetallesMovimientoPK());
                attachedDetallesMovimientoCollection.add(detallesMovimientoCollectionDetallesMovimientoToAttach);
            }
            movimiento.setDetallesMovimientoCollection(attachedDetallesMovimientoCollection);
            em.persist(movimiento);
            if (usuarioModificacion != null) {
                usuarioModificacion.getMovimientoCollection().add(movimiento);
                usuarioModificacion = em.merge(usuarioModificacion);
            }
            for (DetallesMovimiento detallesMovimientoCollectionDetallesMovimiento : movimiento.getDetallesMovimientoCollection()) {
                Movimiento oldMovimientoOfDetallesMovimientoCollectionDetallesMovimiento = detallesMovimientoCollectionDetallesMovimiento.getMovimiento();
                detallesMovimientoCollectionDetallesMovimiento.setMovimiento(movimiento);
                detallesMovimientoCollectionDetallesMovimiento = em.merge(detallesMovimientoCollectionDetallesMovimiento);
                if (oldMovimientoOfDetallesMovimientoCollectionDetallesMovimiento != null) {
                    oldMovimientoOfDetallesMovimientoCollectionDetallesMovimiento.getDetallesMovimientoCollection().remove(detallesMovimientoCollectionDetallesMovimiento);
                    oldMovimientoOfDetallesMovimientoCollectionDetallesMovimiento = em.merge(oldMovimientoOfDetallesMovimientoCollectionDetallesMovimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Movimiento movimiento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Movimiento persistentMovimiento = em.find(Movimiento.class, movimiento.getIdMovimiento());
            Usuario usuarioModificacionOld = persistentMovimiento.getUsuarioModificacion();
            Usuario usuarioModificacionNew = movimiento.getUsuarioModificacion();
            Collection<DetallesMovimiento> detallesMovimientoCollectionOld = persistentMovimiento.getDetallesMovimientoCollection();
            Collection<DetallesMovimiento> detallesMovimientoCollectionNew = movimiento.getDetallesMovimientoCollection();
            List<String> illegalOrphanMessages = null;
            for (DetallesMovimiento detallesMovimientoCollectionOldDetallesMovimiento : detallesMovimientoCollectionOld) {
                if (!detallesMovimientoCollectionNew.contains(detallesMovimientoCollectionOldDetallesMovimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DetallesMovimiento " + detallesMovimientoCollectionOldDetallesMovimiento + " since its movimiento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioModificacionNew != null) {
                usuarioModificacionNew = em.getReference(usuarioModificacionNew.getClass(), usuarioModificacionNew.getIdUsuario());
                movimiento.setUsuarioModificacion(usuarioModificacionNew);
            }
            Collection<DetallesMovimiento> attachedDetallesMovimientoCollectionNew = new ArrayList<DetallesMovimiento>();
            for (DetallesMovimiento detallesMovimientoCollectionNewDetallesMovimientoToAttach : detallesMovimientoCollectionNew) {
                detallesMovimientoCollectionNewDetallesMovimientoToAttach = em.getReference(detallesMovimientoCollectionNewDetallesMovimientoToAttach.getClass(), detallesMovimientoCollectionNewDetallesMovimientoToAttach.getDetallesMovimientoPK());
                attachedDetallesMovimientoCollectionNew.add(detallesMovimientoCollectionNewDetallesMovimientoToAttach);
            }
            detallesMovimientoCollectionNew = attachedDetallesMovimientoCollectionNew;
            movimiento.setDetallesMovimientoCollection(detallesMovimientoCollectionNew);
            movimiento = em.merge(movimiento);
            if (usuarioModificacionOld != null && !usuarioModificacionOld.equals(usuarioModificacionNew)) {
                usuarioModificacionOld.getMovimientoCollection().remove(movimiento);
                usuarioModificacionOld = em.merge(usuarioModificacionOld);
            }
            if (usuarioModificacionNew != null && !usuarioModificacionNew.equals(usuarioModificacionOld)) {
                usuarioModificacionNew.getMovimientoCollection().add(movimiento);
                usuarioModificacionNew = em.merge(usuarioModificacionNew);
            }
            for (DetallesMovimiento detallesMovimientoCollectionNewDetallesMovimiento : detallesMovimientoCollectionNew) {
                if (!detallesMovimientoCollectionOld.contains(detallesMovimientoCollectionNewDetallesMovimiento)) {
                    Movimiento oldMovimientoOfDetallesMovimientoCollectionNewDetallesMovimiento = detallesMovimientoCollectionNewDetallesMovimiento.getMovimiento();
                    detallesMovimientoCollectionNewDetallesMovimiento.setMovimiento(movimiento);
                    detallesMovimientoCollectionNewDetallesMovimiento = em.merge(detallesMovimientoCollectionNewDetallesMovimiento);
                    if (oldMovimientoOfDetallesMovimientoCollectionNewDetallesMovimiento != null && !oldMovimientoOfDetallesMovimientoCollectionNewDetallesMovimiento.equals(movimiento)) {
                        oldMovimientoOfDetallesMovimientoCollectionNewDetallesMovimiento.getDetallesMovimientoCollection().remove(detallesMovimientoCollectionNewDetallesMovimiento);
                        oldMovimientoOfDetallesMovimientoCollectionNewDetallesMovimiento = em.merge(oldMovimientoOfDetallesMovimientoCollectionNewDetallesMovimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = movimiento.getIdMovimiento();
                if (findMovimiento(id) == null) {
                    throw new NonexistentEntityException("The movimiento with id " + id + " no longer exists.");
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
            Movimiento movimiento;
            try {
                movimiento = em.getReference(Movimiento.class, id);
                movimiento.getIdMovimiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The movimiento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<DetallesMovimiento> detallesMovimientoCollectionOrphanCheck = movimiento.getDetallesMovimientoCollection();
            for (DetallesMovimiento detallesMovimientoCollectionOrphanCheckDetallesMovimiento : detallesMovimientoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Movimiento (" + movimiento + ") cannot be destroyed since the DetallesMovimiento " + detallesMovimientoCollectionOrphanCheckDetallesMovimiento + " in its detallesMovimientoCollection field has a non-nullable movimiento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuarioModificacion = movimiento.getUsuarioModificacion();
            if (usuarioModificacion != null) {
                usuarioModificacion.getMovimientoCollection().remove(movimiento);
                usuarioModificacion = em.merge(usuarioModificacion);
            }
            em.remove(movimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Movimiento> findMovimientoEntities() {
        return findMovimientoEntities(true, -1, -1);
    }

    public List<Movimiento> findMovimientoEntities(int maxResults, int firstResult) {
        return findMovimientoEntities(false, maxResults, firstResult);
    }

    private List<Movimiento> findMovimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Movimiento.class));
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

    public Movimiento findMovimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Movimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getMovimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Movimiento> rt = cq.from(Movimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
