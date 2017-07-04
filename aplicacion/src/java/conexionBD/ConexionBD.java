package conexionBD;

import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;

public class ConexionBD {

    private JdbcRowSet rowSet;

    public static final String CONTROLADOR = "com.mysql.jdbc.Driver";
    public static final String URL_BASEDATOS = "jdbc:mysql://localhost/banco_herramientas";
    public static final String NOMBREUSUARIO = "root";
    public static final String PASSWORD = "5824247";
    public static final String NOMBREBD = "banco_herramientas";

    public void conectarConsulta(JdbcRowSet row, String consulta)
            throws ClassNotFoundException, SQLException {

        this.rowSet = row;

        Class.forName(CONTROLADOR);
        rowSet.setUrl(URL_BASEDATOS); // Establece la URL de la base de datos
        rowSet.setUsername(NOMBREUSUARIO); // Establece el nombre del usuario en la BD
        rowSet.setPassword(PASSWORD); // Establece el password de la BD
        rowSet.setCommand(consulta); // Establece la consulta
        rowSet.execute(); // Ejecuta la consulta
    }
    
    public static void ConectarRegistro(){
    
    }

    public JdbcRowSet getRowSet() {
        return rowSet;
    }

    public void setRowSet(JdbcRowSet rowSet) {
        this.rowSet = rowSet;
    }

}
