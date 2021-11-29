
package tienda.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author Matias Luca Soto
 */
public abstract class DAO {

    //ATRIBUTOS HEREDABLES
    protected Connection conexion = null;
    protected Statement sentencia = null;
    protected ResultSet resultado = null;
    
    //ATRIBUTOS PRIVADOS
    private final String USER = "root";
    private final String PASSWORD = "root";
    private final String URL = "jdbc:mysql://localhost:3306/tienda?useSSL=false";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    
    //MÉTODOS
    /**
     * Método que conecta el Driver JDBC y luego establece una conexión con el Objeto Connection usando la url, el usuario y la contraseña que esta Clase posee como atributos
     * @throws Exception 
     */
    protected void conectarBase() throws Exception {
        
        try {
            Class.forName(DRIVER); //ME CONECTO AL DRIVER
            conexion = DriverManager.getConnection(URL, USER, PASSWORD); // ESTABLEZCO LA CONEXION
            
        } catch (ClassNotFoundException | SQLException e) {
            throw e; 
        }
    }
    
    /**
     * Método que verifica si mis Objetos de Conexión, Sentencia y Resultado tienen valores asignados. Si es así, se los devuelve a un estado 'null'
     * @throws Exception 
     */
    protected void desconectarBase() throws Exception {
        try {
            if (conexion != null) {
                conexion.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (resultado != null) {
                resultado.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    
    /**
     * Método que se conecta a la base, prepara el Objeto Statement y ejecuta con un método la query pasada como argumento
     * @param sql Sentencia query nativa de tipo INSERT, UPDATE, DELETE que no devuelve ninguna tabla
     * @throws Exception 
     */
    protected void insertarModificarEliminar(String sql) throws Exception {
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            conexion.rollback();
            throw e;
        } finally {
            desconectarBase();
        }
    }
    
    /**
     * Método que se conecta a la base, prepara el Objeto Statement y luego guarda en el Objeto ResultSet las tablas devueltas al ejectuar la sentencia query que fue pasada como argumento
     * @param sql Sentencia query nativa de tipo consulta SELECT que devuelva una tabla 
     * @throws Exception 
     */
    protected void consultarBase(String sql) throws Exception {
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
