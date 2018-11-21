/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author edgar
 */
@Embeddable
public class DetallesMovimientoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_movimiento")
    private int idMovimiento;
    @Basic(optional = false)
    @Column(name = "id_producto")
    private int idProducto;

    public DetallesMovimientoPK() {
    }

    public DetallesMovimientoPK(int idMovimiento, int idProducto) {
        this.idMovimiento = idMovimiento;
        this.idProducto = idProducto;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idMovimiento;
        hash += (int) idProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesMovimientoPK)) {
            return false;
        }
        DetallesMovimientoPK other = (DetallesMovimientoPK) object;
        if (this.idMovimiento != other.idMovimiento) {
            return false;
        }
        if (this.idProducto != other.idProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetallesMovimientoPK[ idMovimiento=" + idMovimiento + ", idProducto=" + idProducto + " ]";
    }
    
}
