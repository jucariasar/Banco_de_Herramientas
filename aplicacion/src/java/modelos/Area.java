
package modelos;

import conexionBD.ConexionBD;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.sql.rowset.JdbcRowSet;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;


public class Area implements JSONStreamAware{
    
    // Identificador unico compuesto por nombre y codigo_centro
    
    private String nombre; // Parte del identificador único de instancias de la clase
    private int codigo_centro; // Codigo del Centro al que esta asociado

    public Area(){
        this.nombre = null;
        this.codigo_centro = 0;
    }
    
    public Area(String nombre, int codigo_centro) {
        this.nombre = nombre;
        this.codigo_centro = codigo_centro;
    }

    
    public String getNombre() {
        return nombre;
    }
    
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public int getCodigo_centro() {
        return codigo_centro;
    }

    
    public void setCodigo_centro(int codigo_centro) {
        this.codigo_centro = codigo_centro;
    }
    
   
    public static List<Area> consultarAreas() throws ClassNotFoundException, SQLException
    {
        // String para enviar como consulta a la base de datos.
        String consulta = "SELECT * FROM area ORDER BY nombre";

        // Lista para almacenar los centros de la base de datos.
        List<Area> areas = new ArrayList<>();


        // Se invoca al método conectarConsulta el cual se encarga de realizar
        // la conexion con la BD y ejecutar la consulta que se quiera,
        // y despues devuelve el resultado de la consulta.
        JdbcRowSet rowSet = ConexionBD.conectarConsulta(consulta);

        ResultSetMetaData metaDatos = rowSet.getMetaData(); // Obtine los datos del esquema de la BD
        int numeroDeColumnas = metaDatos.getColumnCount(); // Obtiene el el numero de columnas de la BD

        while (rowSet.next()) {
            Area atemp = new Area();
            for (int i = 1; i <= numeroDeColumnas; i++) {
                if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                    atemp.setNombre((String) rowSet.getObject(i)); // Establece el código en un objeto Regional
                } else {
                    atemp.setCodigo_centro((int) rowSet.getObject(i));
                }
            }
            areas.add(atemp); // Guarda el objeto creado en una lista de objetos Regional
        }

        return areas;
    }
    
    @Override
    public void writeJSONString(Writer out) throws IOException {
        LinkedHashMap obj = new LinkedHashMap();
        obj.put("nombre", this.nombre);
        obj.put("codigo_centro", String.valueOf(this.codigo_centro));
        JSONValue.writeJSONString(obj, out);
    }
       
}
