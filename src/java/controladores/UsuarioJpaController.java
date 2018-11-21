/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Movimiento;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Producto;
import modelo.Usuario;

/**
 *
 * @author edgar
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getMovimientoCollection() == null) {
            usuario.setMovimientoCollection(new ArrayList<Movimiento>());
        }
        if (usuario.getProductoCollection() == null) {
            usuario.setProductoCollection(new ArrayList<Producto>());
        }
        if (usuario.getProductoCollection1() == null) {
            usuario.setProductoCollection1(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Movimiento> attachedMovimientoCollection = new ArrayList<Movimiento>();
            for (Movimiento movimientoCollectionMovimientoToAttach : usuario.getMovimientoCollection()) {
                movimientoCollectionMovimientoToAttach = em.getReference(movimientoCollectionMovimientoToAttach.getClass(), movimientoCollectionMovimientoToAttach.getIdMovimiento());
                attachedMovimientoCollection.add(movimientoCollectionMovimientoToAttach);
            }
            usuario.setMovimientoCollection(attachedMovimientoCollection);
            Collection<Producto> attachedProductoCollection = new ArrayList<Producto>();
            for (Producto productoCollectionProductoToAttach : usuario.getProductoCollection()) {
                productoCollectionProductoToAttach = em.getReference(productoCollectionProductoToAttach.getClass(), productoCollectionProductoToAttach.getIdProducto());
                attachedProductoCollection.add(productoCollectionProductoToAttach);
            }
            usuario.setProductoCollection(attachedProductoCollection);
            Collection<Producto> attachedProductoCollection1 = new ArrayList<Producto>();
            for (Producto productoCollection1ProductoToAttach : usuario.getProductoCollection1()) {
                productoCollection1ProductoToAttach = em.getReference(productoCollection1ProductoToAttach.getClass(), productoCollection1ProductoToAttach.getIdProducto());
                attachedProductoCollection1.add(productoCollection1ProductoToAttach);
            }
            usuario.setProductoCollection1(attachedProductoCollection1);
            em.persist(usuario);
            for (Movimiento movimientoCollectionMovimiento : usuario.getMovimientoCollection()) {
                Usuario oldUsuarioModificacionOfMovimientoCollectionMovimiento = movimientoCollectionMovimiento.getUsuarioModificacion();
                movimientoCollectionMovimiento.setUsuarioModificacion(usuario);
                movimientoCollectionMovimiento = em.merge(movimientoCollectionMovimiento);
                if (oldUsuarioModificacionOfMovimientoCollectionMovimiento != null) {
                    oldUsuarioModificacionOfMovimientoCollectionMovimiento.getMovimientoCollection().remove(movimientoCollectionMovimiento);
                    oldUsuarioModificacionOfMovimientoCollectionMovimiento = em.merge(oldUsuarioModificacionOfMovimientoCollectionMovimiento);
                }
            }
            for (Producto productoCollectionProducto : usuario.getProductoCollection()) {
                Usuario oldUsuarioCreacionOfProductoCollectionProducto = productoCollectionProducto.getUsuarioCreacion();
                productoCollectionProducto.setUsuarioCreacion(usuario);
                productoCollectionProducto = em.merge(productoCollectionProducto);
                if (oldUsuarioCreacionOfProductoCollectionProducto != null) {
                    oldUsuarioCreacionOfProductoCollectionProducto.getProductoCollection().remove(productoCollectionProducto);
                    oldUsuarioCreacionOfProductoCollectionProducto = em.merge(oldUsuarioCreacionOfProductoCollectionProducto);
                }
            }
            for (Producto productoCollection1Producto : usuario.getProductoCollection1()) {
                Usuario oldUsuarioModificacionOfProductoCollection1Producto = productoCollection1Producto.getUsuarioModificacion();
                productoCollection1Producto.setUsuarioModificacion(usuario);
                productoCollection1Producto = em.merge(productoCollection1Producto);
                if (oldUsuarioModificacionOfProductoCollection1Producto != null) {
                    oldUsuarioModificacionOfProductoCollection1Producto.getProductoCollection1().remove(productoCollection1Producto);
                    oldUsuarioModificacionOfProductoCollection1Producto = em.merge(oldUsuarioModificacionOfProductoCollection1Producto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Collection<Movimiento> movimientoCollectionOld = persistentUsuario.getMovimientoCollection();
            Collection<Movimiento> movimientoCollectionNew = usuario.getMovimientoCollection();
            Collection<Producto> productoCollectionOld = persistentUsuario.getProductoCollection();
            Collection<Producto> productoCollectionNew = usuario.getProductoCollection();
            Collection<Producto> productoCollection1Old = persistentUsuario.getProductoCollection1();
            Collection<Producto> productoCollection1New = usuario.getProductoCollection1();
            Collection<Movimiento> attachedMovimientoCollectionNew = new ArrayList<Movimiento>();
            for (Movimiento movimientoCollectionNewMovimientoToAttach : movimientoCollectionNew) {
                movimientoCollectionNewMovimientoToAttach = em.getReference(movimientoCollectionNewMovimientoToAttach.getClass(), movimientoCollectionNewMovimientoToAttach.getIdMovimiento());
                attachedMovimientoCollectionNew.add(movimientoCollectionNewMovimientoToAttach);
            }
            movimientoCollectionNew = attachedMovimientoCollectionNew;
            usuario.setMovimientoCollection(movimientoCollectionNew);
            Collection<Producto> attachedProductoCollectionNew = new ArrayList<Producto>();
            for (Producto productoCollectionNewProductoToAttach : productoCollectionNew) {
                productoCollectionNewProductoToAttach = em.getReference(productoCollectionNewProductoToAttach.getClass(), productoCollectionNewProductoToAttach.getIdProducto());
                attachedProductoCollectionNew.add(productoCollectionNewProductoToAttach);
            }
            productoCollectionNew = attachedProductoCollectionNew;
            usuario.setProductoCollection(productoCollectionNew);
            Collection<Producto> attachedProductoCollection1New = new ArrayList<Producto>();
            for (Producto productoCollection1NewProductoToAttach : productoCollection1New) {
                productoCollection1NewProductoToAttach = em.getReference(productoCollection1NewProductoToAttach.getClass(), productoCollection1NewProductoToAttach.getIdProducto());
                attachedProductoCollection1New.add(productoCollection1NewProductoToAttach);
            }
            productoCollection1New = attachedProductoCollection1New;
            usuario.setProductoCollection1(productoCollection1New);
            usuario = em.merge(usuario);
            for (Movimiento movimientoCollectionOldMovimiento : movimientoCollectionOld) {
                if (!movimientoCollectionNew.contains(movimientoCollectionOldMovimiento)) {
                    movimientoCollectionOldMovimiento.setUsuarioModificacion(null);
                    movimientoCollectionOldMovimiento = em.merge(movimientoCollectionOldMovimiento);
                }
            }
            for (Movimiento movimientoCollectionNewMovimiento : movimientoCollectionNew) {
                if (!movimientoCollectionOld.contains(movimientoCollectionNewMovimiento)) {
                    Usuario oldUsuarioModificacionOfMovimientoCollectionNewMovimiento = movimientoCollectionNewMovimiento.getUsuarioModificacion();
                    movimientoCollectionNewMovimiento.setUsuarioModificacion(usuario);
                    movimientoCollectionNewMovimiento = em.merge(movimientoCollectionNewMovimiento);
                    if (oldUsuarioModificacionOfMovimientoCollectionNewMovimiento != null && !oldUsuarioModificacionOfMovimientoCollectionNewMovimiento.equals(usuario)) {
                        oldUsuarioModificacionOfMovimientoCollectionNewMovimiento.getMovimientoCollection().remove(movimientoCollectionNewMovimiento);
                        oldUsuarioModificacionOfMovimientoCollectionNewMovimiento = em.merge(oldUsuarioModificacionOfMovimientoCollectionNewMovimiento);
                    }
                }
            }
            for (Producto productoCollectionOldProducto : productoCollectionOld) {
                if (!productoCollectionNew.contains(productoCollectionOldProducto)) {
                    productoCollectionOldProducto.setUsuarioCreacion(null);
                    productoCollectionOldProducto = em.merge(productoCollectionOldProducto);
                }
            }
            for (Producto productoCollectionNewProducto : productoCollectionNew) {
                if (!productoCollectionOld.contains(productoCollectionNewProducto)) {
                    Usuario oldUsuarioCreacionOfProductoCollectionNewProducto = productoCollectionNewProducto.getUsuarioCreacion();
                    productoCollectionNewProducto.setUsuarioCreacion(usuario);
                    productoCollectionNewProducto = em.merge(productoCollectionNewProducto);
                    if (oldUsuarioCreacionOfProductoCollectionNewProducto != null && !oldUsuarioCreacionOfProductoCollectionNewProducto.equals(usuario)) {
                        oldUsuarioCreacionOfProductoCollectionNewProducto.getProductoCollection().remove(productoCollectionNewProducto);
                        oldUsuarioCreacionOfProductoCollectionNewProducto = em.merge(oldUsuarioCreacionOfProductoCollectionNewProducto);
                    }
                }
            }
            for (Producto productoCollection1OldProducto : productoCollection1Old) {
                if (!productoCollection1New.contains(productoCollection1OldProducto)) {
                    productoCollection1OldProducto.setUsuarioModificacion(null);
                    productoCollection1OldProducto = em.merge(productoCollection1OldProducto);
                }
            }
            for (Producto productoCollection1NewProducto : productoCollection1New) {
                if (!productoCollection1Old.contains(productoCollection1NewProducto)) {
                    Usuario oldUsuarioModificacionOfProductoCollection1NewProducto = productoCollection1NewProducto.getUsuarioModificacion();
                    productoCollection1NewProducto.setUsuarioModificacion(usuario);
                    productoCollection1NewProducto = em.merge(productoCollection1NewProducto);
                    if (oldUsuarioModificacionOfProductoCollection1NewProducto != null && !oldUsuarioModificacionOfProductoCollection1NewProducto.equals(usuario)) {
                        oldUsuarioModificacionOfProductoCollection1NewProducto.getProductoCollection1().remove(productoCollection1NewProducto);
                        oldUsuarioModificacionOfProductoCollection1NewProducto = em.merge(oldUsuarioModificacionOfProductoCollection1NewProducto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Collection<Movimiento> movimientoCollection = usuario.getMovimientoCollection();
            for (Movimiento movimientoCollectionMovimiento : movimientoCollection) {
                movimientoCollectionMovimiento.setUsuarioModificacion(null);
                movimientoCollectionMovimiento = em.merge(movimientoCollectionMovimiento);
            }
            Collection<Producto> productoCollection = usuario.getProductoCollection();
            for (Producto productoCollectionProducto : productoCollection) {
                productoCollectionProducto.setUsuarioCreacion(null);
                productoCollectionProducto = em.merge(productoCollectionProducto);
            }
            Collection<Producto> productoCollection1 = usuario.getProductoCollection1();
            for (Producto productoCollection1Producto : productoCollection1) {
                productoCollection1Producto.setUsuarioModificacion(null);
                productoCollection1Producto = em.merge(productoCollection1Producto);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
