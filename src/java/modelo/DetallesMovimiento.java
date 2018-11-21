/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author edgar
 */
@Entity
@Table(name = "detallesMovimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallesMovimiento.findAll", query = "SELECT d FROM DetallesMovimiento d")
    , @NamedQuery(name = "DetallesMovimiento.findByIdMovimiento", query = "SELECT d FROM DetallesMovimiento d WHERE d.detallesMovimientoPK.idMovimiento = :idMovimiento")
    , @NamedQuery(name = "DetallesMovimiento.findByIdProducto", query = "SELECT d FROM DetallesMovimiento d WHERE d.detallesMovimientoPK.idProducto = :idProducto")
    , @NamedQuery(name = "DetallesMovimiento.findByCantidad", query = "SELECT d FROM DetallesMovimiento d WHERE d.cantidad = :cantidad")})
public class DetallesMovimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallesMovimientoPK detallesMovimientoPK;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "id_movimiento", referencedColumnName = "id_movimiento", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Movimiento movimiento;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;

    public DetallesMovimiento() {
    }

    public DetallesMovimiento(DetallesMovimientoPK detallesMovimientoPK) {
        this.detallesMovimientoPK = detallesMovimientoPK;
    }

    public DetallesMovimiento(int idMovimiento, int idProducto) {
        this.detallesMovimientoPK = new DetallesMovimientoPK(idMovimiento, idProducto);
    }

    public DetallesMovimientoPK getDetallesMovimientoPK() {
        return detallesMovimientoPK;
    }

    public void setDetallesMovimientoPK(DetallesMovimientoPK detallesMovimientoPK) {
        this.detallesMovimientoPK = detallesMovimientoPK;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallesMovimientoPK != null ? detallesMovimientoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesMovimiento)) {
            return false;
        }
        DetallesMovimiento other = (DetallesMovimiento) object;
        if ((this.detallesMovimientoPK == null && other.detallesMovimientoPK != null) || (this.detallesMovimientoPK != null && !this.detallesMovimientoPK.equals(other.detallesMovimientoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetallesMovimiento[ detallesMovimientoPK=" + detallesMovimientoPK + " ]";
    }
    
}
