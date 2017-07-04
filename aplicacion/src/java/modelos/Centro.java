package modelos;

import com.sun.rowset.JdbcRowSetImpl;
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

public class Centro implements JSONStreamAware {

    private int codigo; // Identificador único para instancias de la clase
    private String nombre; // El nombre del Centro
    private int codigo_regional; // Codigo de la regional a la que esta asociada
    // Clave foranea a Regional en BD

    public Centro() {
        this.codigo = 0;
        this.nombre = null;
        this.codigo_regional = 0;
    }

    public Centro(int codigo, String nombre, int codigo_regional) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codigo_regional = codigo_regional;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo_regional() {
        return codigo_regional;
    }

    public void setCodigo_regional(int codigo_regional) {
        this.codigo_regional = codigo_regional;
    }

    public static List<Centro> consultarCentros()
            throws ClassNotFoundException, SQLException {

        // String para enviar como consulta a la base de datos.
        String consulta = "SELECT * FROM centro ORDER BY codigo_regional";

        // Lista para almacenar los centros de la base de datos.
        List<Centro> centros = new ArrayList<>();


        // Se invoca al método conectarConsulta el cual se encarga de realizar
        // la conexion con la BD y ejecutar la consulta que se quiera,
        // y despues devuelve el resultado de la consulta.
        JdbcRowSet rowSet = ConexionBD.conectarConsulta(consulta);

        ResultSetMetaData metaDatos = rowSet.getMetaData(); // Obtine los datos del esquema de la BD
        int numeroDeColumnas = metaDatos.getColumnCount(); // Obtiene el el numero de columnas de la BD

        while (rowSet.next()) {
            Centro ctemp = new Centro();
            for (int i = 1; i <= numeroDeColumnas; i++) {
                if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                    ctemp.setCodigo((int) rowSet.getObject(i)); // Establece el código en un objeto Regional
                } else if (i == 2) { // optiene los datos de la segunda columna columna en cada recorrido de filas
                    ctemp.setNombre((String) rowSet.getObject(i)); // Establece el nombre en un objeto Regional
                } else {
                    ctemp.setCodigo_regional((int) rowSet.getObject(i));
                }
            }
            centros.add(ctemp); // Guarda el objeto creado en una lista de objetos Regional
        }

        return centros;
    }

    public String getNombreRegional() 
            throws ClassNotFoundException, SQLException {

        String nombreR = ""; // String para almacenar el nombre de la regional en la consulta
        
        // String para almacenar la instrucción de la consulta a ejecutar
        String consulta = "SELECT nombre_departamento FROM regional WHERE codigo=" + this.getCodigo_regional(); 
        
        // Se invoca al método conectarConsulta el cual se encarga de realizar
        // la conexion con la BD y ejecutar la consulta enviada, 
        // y despues devuelve el resultado de la consulta.
        JdbcRowSet rowSet = ConexionBD.conectarConsulta(consulta);      
        
            if (rowSet.next()) 
                nombreR = (String) rowSet.getObject(1);
            else
                return "Consulta no encontrada";
                    
        return nombreR;
    }

    @Override
    public void writeJSONString(Writer out) throws IOException {
        LinkedHashMap obj = new LinkedHashMap();
        obj.put("codigo", String.valueOf(this.codigo));
        obj.put("nombre", this.nombre);
        obj.put("codigo_regional", String.valueOf(this.codigo_regional));
        JSONValue.writeJSONString(obj, out);
    }
}
