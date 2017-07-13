
package modelos;

import conexionBD.ConexionBD;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.JdbcRowSet;


public class Programa {
    
    int codigo;
    int version;
    String nombre;
    
    public Programa(){
        this.codigo = 0;
        this.version = 0;
        this.nombre = null;
    }
    
    public Programa(int codigo, int version, String nombre) {
        this.codigo = codigo;
        this.version = version;
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public int getVersion() {
        return version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public static List<Programa> consultarProgramas() throws ClassNotFoundException, SQLException
    {
        //String para realizar la consulta en la Base de Datos
        String consulta = "SELECT * FROM programa ORDER BY nombre";

        // Lista de objetos regional que se asocian a cada centro
        List<Programa> programas = new ArrayList<>();

   
        // Se invoca al método conectarConsulta el cual se encarga de realizar
        // la conexion con la BD y ejecutar la consulta enviada, 
        // y despues devuelve el resultado de la consulta.
        JdbcRowSet rowSet = ConexionBD.conectarConsulta(consulta);

        // Obtiene los datos del esquema de la BD (Nombre de las columnas)
        ResultSetMetaData metaDatos = rowSet.getMetaData();

        // Obtiene el el numero de columnas de la BD
        int numeroDeColumnas = metaDatos.getColumnCount();

        // Recorre cada fila de la consulta de la relacion regional
        while (rowSet.next()) {

            // Crea objeto Regional temporal para despues guardarlo en el ArrayList regionales
            Programa ptemp = new Programa();

            // For para recorrer cada columna en la fila
            for (int i = 1; i <= numeroDeColumnas; i++) {

                // Si es la primera columna
                if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                    ptemp.setCodigo((int) rowSet.getObject(i)); // Establece el código en un objeto Regional
                }
                else if(i == 2){
                    ptemp.setVersion((int) rowSet.getObject(i));
                }
                else { // optiene los datos de la segunda columna columna en cada recorrido de filas
                    ptemp.setNombre((String) rowSet.getObject(i)); // Establece el nombre en un objeto Regional
                }
            } // Fin del for
            programas.add(ptemp); // Guarda el objeto creado en una lista de objetos Regional
        } // Fin del while
        
        return programas;
    }
    
    
}
