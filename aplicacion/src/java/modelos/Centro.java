
package modelos;


public class Centro {
    
    private int codigo; // Identificador único para instancias de la clase
    private String nombre; // El nombre del Centro
    private int codigo_regional; // Codigo de la regional a la que esta asociada
                             // Clave foranea a Regional en BD

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
}
