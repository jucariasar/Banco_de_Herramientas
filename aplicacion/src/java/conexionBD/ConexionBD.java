package conexionBD;

import com.sun.rowset.JdbcRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;

public class ConexionBD {

    public static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    public static final String URL_BASEDATOS = "jdbc:mysql://localhost/banco_herramientas";
    public static final String NOMBREUSUARIO = "root";
    public static final String PASSWORD = "5824247";
    public static final String NOMBREBD = "banco_herramientas";
    
    private PreparedStatement instruccionInsercion;
    private Connection conexion;

    public PreparedStatement getInstruccionInsercion() {
        return instruccionInsercion;
    }

    public void setInstruccionInsercion(PreparedStatement instruccionInsercion) {
        this.instruccionInsercion = instruccionInsercion;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    
    
    public static JdbcRowSet conectarConsulta(String consulta)
            throws ClassNotFoundException, SQLException {

        JdbcRowSet rowSet = new JdbcRowSetImpl();

        Class.forName(CONTROLADOR);
        rowSet.setUrl(URL_BASEDATOS); // Establece la URL de la base de datos
        rowSet.setUsername(NOMBREUSUARIO); // Establece el nombre del usuario en la BD
        rowSet.setPassword(PASSWORD); // Establece el password de la BD
        rowSet.setCommand(consulta); // Establece la consulta
        rowSet.execute(); // Ejecuta la consulta
        
        return rowSet;
    }
       
    public void ConectarRegistro(String instruccion) throws ClassNotFoundException, SQLException{
        
        Class.forName(ConexionBD.CONTROLADOR);
        
        this.conexion = DriverManager.getConnection(URL_BASEDATOS, NOMBREUSUARIO, PASSWORD);
        
        instruccionInsercion = conexion.prepareStatement(instruccion);
        
    };
    
    public void cerrarConexiones() throws SQLException{
        this.getInstruccionInsercion().close();
        this.getConexion().close();
    }

}
