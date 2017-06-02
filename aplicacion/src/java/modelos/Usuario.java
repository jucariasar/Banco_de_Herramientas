
package modelos;

public class Usuario {
    
    long carne;
    String nombres;
    String apellidos;
    String telefono1;
    String telefono2;
    String email;
    String passwd;
    String nombre_area;
    int codigo_centro;
    String tipo;

    public Usuario() {
        this.carne = 0;
        this.nombres = null;
        this.apellidos = null;
        this.telefono1 = null;
        this.telefono2 = null;
        this.email = null;
        this.passwd = null;
        this.nombre_area = null;
        this.codigo_centro = 0;
        this.tipo = null;
    }

    public long getCarne() {
        return carne;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getNombre_area() {
        return nombre_area;
    }

    public int getCodigo_centro() {
        return codigo_centro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setCarne(long carne) {
        this.carne = carne;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setNombre_area(String nombre_area) {
        this.nombre_area = nombre_area;
    }

    public void setCodigo_centro(int codigo_centro) {
        this.codigo_centro = codigo_centro;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
}
