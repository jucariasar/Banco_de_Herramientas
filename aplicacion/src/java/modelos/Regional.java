
package modelos;


public class Regional {
    
    private int codigo;  // Identificador único para instancias de la clase
    private String nombre;

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
    
    
    
    
}
