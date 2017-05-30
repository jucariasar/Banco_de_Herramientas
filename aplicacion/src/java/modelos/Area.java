
package modelos;


public class Area {
    
    // Identificador unico compuesto por nombre y codigo_centro
    
    private String nombre; // Parte del identificador único de instancias de la clase
    private int codigo_centro; // Codigo del Centro al que esta asociado

    
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
       
}
