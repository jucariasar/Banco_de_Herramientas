
package modelos;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.List;
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
    
    /*
    public static List<Area> consultarAreas()
    {
     
        
    }*/
    
    @Override
    public void writeJSONString(Writer out) throws IOException {
        LinkedHashMap obj = new LinkedHashMap();
        obj.put("nombre", this.nombre);
        obj.put("codigo_centro", String.valueOf(this.codigo_centro));
        JSONValue.writeJSONString(obj, out);
    }
       
}
