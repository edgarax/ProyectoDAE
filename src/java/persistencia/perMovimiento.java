package persistencia;

import controladores.UsuarioJpaController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Movimiento;
import modelo.Usuario;

/**
 *
 * @author edgar
 */
public class perMovimiento {
    
static Conectar baseD = new Conectar();
static EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyecto_daePU");
static UsuarioJpaController ujpa = new UsuarioJpaController(emf);

public static LinkedList<Movimiento> consultarMovimientos() throws SQLException{
    LinkedList<Movimiento> listaMovimientos = new LinkedList<Movimiento>();
    Statement st;
    ResultSet rs;
    Connection c;
    String sql = "SELECT * FROM consolas";
    c = baseD.abreBD();
    st = c.createStatement();
    rs = st.executeQuery(sql);
    
    while(rs.next()){
        Movimiento movimiento = new Movimiento();
        movimiento.setDescripcion(rs.getString("descripcion"));
        movimiento.setFecha(rs.getTimestamp("fecha"));
        movimiento.setTipo(rs.getString("tipo"));
        Usuario usuario = ujpa.findUsuario(rs.getInt("id_usuario"));
        movimiento.setIdUsuario(usuario);
        Usuario cliente = ujpa.findUsuario(rs.getInt("id_cliente"));
        movimiento.setIdCliente(cliente);
        movimiento.setOrigenDestino(rs.getString("origenDestino"));
        movimiento.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
        Usuario usu = ujpa.findUsuario(rs.getInt("usuarioModificacion"));
        movimiento.setUsuarioModificacion(usu);
    }
    c.close();
    return listaMovimientos;
}
}
