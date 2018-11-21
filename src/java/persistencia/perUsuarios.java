/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import controladores.UsuarioJpaController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Usuario;
import modelo.Usuario;
import persistencia.perUsuarios;

/**
 *
 * @author Leonel
 */
public class perUsuarios {
    
    static Conectar baseD = new Conectar();
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("proyecto_daePU");
    static UsuarioJpaController ujpa = new UsuarioJpaController(emf);
    
    public static LinkedList<Usuario> consultarUsuarios() throws SQLException{
    LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();
    Statement st;
    ResultSet rs;
    Connection c;
    String sql = "SELECT * FROM consolas";
    c = baseD.abreBD();
    st = c.createStatement();
    rs = st.executeQuery(sql);
    
    while(rs.next()){
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellidos(rs.getString("apellidos"));
        usuario.setEmail(rs.getString("email"));
        usuario.setFechaCreacion(rs.getTimestamp("fechaCreacion"));
        usuario.setFechaModificacion(rs.getTimestamp("fechaModificacion"));
        usuario.setRol(rs.getString("rol"));
        usuario.setPass(rs.getString("contraseña"));
        
        
        
    }
    c.close();
    return listaUsuarios;
}
    
}
