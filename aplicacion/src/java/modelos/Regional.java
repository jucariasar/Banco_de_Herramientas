
package modelos;

import com.sun.rowset.JdbcRowSetImpl;
import conexionBD.ConexionBD;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.JdbcRowSet;


public class Regional {
    
    private int codigo;  // Identificador único para instancias de la clase
    private String nombre;
    
    public Regional()
    {
        this.codigo = 0;
        this.nombre = null;
    }
    
    public Regional(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    
    
    public int getCodigo() {
        return codigo;
    }
 
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getNombre_departamento() {
        return nombre;
    }

    public void setNombre_departamento(String nombre) {
        this.nombre = nombre;
    }
    
    public static List<Regional> consultarRegionales() 
            throws ClassNotFoundException, SQLException {

        //String para realizar la consulta en la Base de Datos
        String consulta = "SELECT * FROM regional";

        // Lista de objetos regional que se asocian a cada centro
        List<Regional> regionales = new ArrayList<>();

        // Objeto ConexionBD para gestionar la comunicacion con la base de datos
        ConexionBD conexionQuery = new ConexionBD();

        // Objeto JdbcRowSet para manejar con el resultado de la consulta.
        JdbcRowSet rowSet = new JdbcRowSetImpl();

        // Se invoca al método conectarConsulta el cual se encarga de realizar
        // la conexion con la BD y ejecutar la consulta que se quiera.
        conexionQuery.conectarConsulta(rowSet, consulta);

        // Obtiene los datos del esquema de la BD (Nombre de las columnas)
        ResultSetMetaData metaDatos = rowSet.getMetaData();

        // Obtiene el el numero de columnas de la BD
        int numeroDeColumnas = metaDatos.getColumnCount();

        // Recorre cada fila de la consulta de la relacion regional
        while (rowSet.next()) {

            // Crea objeto Regional temporal para despues guardarlo en el ArrayList regionales
            Regional rtemp = new Regional();

            // For para recorrer cada columna en la fila
            for (int i = 1; i <= numeroDeColumnas; i++) {

                // Si es la primera columna
                if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                    rtemp.setCodigo((int) rowSet.getObject(i)); // Establece el código en un objeto Regional
                } else { // optiene los datos de la segunda columna columna en cada recorrido de filas
                    rtemp.setNombre_departamento((String) rowSet.getObject(i)); // Establece el nombre en un objeto Regional
                }
            } // Fin del for
            regionales.add(rtemp); // Guarda el objeto creado en una lista de objetos Regional
        } // Fin del while
        
        return regionales;
    }
    
    
}
