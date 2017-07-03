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

    public String getNombreRegional() {

        String nombreR = ""; // String para almacenar el nombre de la regional en la consulta
        String consulta = ""; // String para almacenar la instrucción de la consulta a ejecutar
        try {
            //Carga el controlador de la clase
            Class.forName(ConexionBD.CONTROLADOR);

            // Almacena el string necesario para realizar la consulta
            consulta = "SELECT nombre_departamento FROM regional WHERE codigo=" + this.getCodigo_regional();

            // Se especifican las propiedades del objeto JdbcRowSet
            JdbcRowSet rowSet = new JdbcRowSetImpl(); // Crea objetos rowSet para manejar las consultas
            rowSet.setUrl(ConexionBD.URL_BASEDATOS); // Establece la URL de la base de datos
            rowSet.setUsername(ConexionBD.NOMBREUSUARIO); // Establece el nombre del usuario en la BD
            rowSet.setPassword(ConexionBD.PASSWORD); // Establece el password de la BD
            rowSet.setCommand(consulta); // Establece la consulta de regional
            rowSet.execute(); // Ejecuta la consulta de regional

            if (rowSet.next()) {
                nombreR = (String) rowSet.getObject(1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Consulta no encontrada";
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return "Error en la conexion en la BD";
        }

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
