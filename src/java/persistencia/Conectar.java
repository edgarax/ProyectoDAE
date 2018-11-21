package persistencia;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edgar
 */
public class Conectar {
    private String url = "jdbc:mysql://localhost:3306/almacen";
    private String usuarioBD = "root";
    private String contrasenaBD = "root";
    
    /**
     * Get the value of contrasenaBD
     *
     * @return the value of contrasenaBD
     */
    public String getContrasenaBD() {
        return contrasenaBD;
    }

    /**
     * Set the value of contrasenaBD
     *
     * @param contrasenaBD new value of contrasenaBD
     */
    public void setContrasenaBD(String contrasenaBD) {
        this.contrasenaBD = contrasenaBD;
    }


    /**
     * Get the value of usuarioBD
     *
     * @return the value of usuarioBD
     */
    public String getUsuarioBD() {
        return usuarioBD;
    }

    /**
     * Set the value of usuarioBD
     *
     * @param usuarioBD new value of usuarioBD
     */
    public void setUsuarioBD(String usuarioBD) {
        this.usuarioBD = usuarioBD;
    }

    /**
     * Get the value of url
     *
     * @return the value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the value of url
     *
     * @param url new value of url
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    public Conectar(){}
    
    public Conectar(String url, String usuarioBD, String contrasenaBD){
        this.url = url;
        this.usuarioBD = usuarioBD;
        this.contrasenaBD = contrasenaBD;
    }
    
    public Connection abreBD() throws SQLException{
        Connection conexion = null;
        try {
            // create a mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            Class.forName(myDriver);
            conexion = DriverManager.getConnection(url, usuarioBD, contrasenaBD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conectar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conexion;
    }
    public void cieraBD(Connection conexion) throws SQLException{
        conexion.close();
    }
}
