/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author camilo
 */
public class Usuario {
    
    private int carne;
    private String nombres;
    private String apellidos;
    private String telefono_1;
    private String telefono_2;
    private String email;
    private String passwd;

    public Usuario(){
        this.carne = 0;
        this.nombres = null;
        this.apellidos = null;
        this.telefono_1 = null;
        this.telefono_2 = null;
        this.email = null;
        this.passwd = null;
    }
    
    public Usuario(int carne, String nombres, String apellidos, String telefono_1, String telefono_2, String email, String passwd) {
        this.carne = carne;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono_1 = telefono_1;
        this.telefono_2 = "";
        this.email = email;
        this.passwd = passwd;
    }

    public int getCarne() {
        return carne;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono_1() {
        return telefono_1;
    }

    public String getTelefono_2() {
        return telefono_2;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }
    
    

    public void setCarne(int carne) {
        this.carne = carne;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTelefono_1(String telefono_1) {
        this.telefono_1 = telefono_1;
    }

    public void setTelefono_2(String telefono_2) {
        this.telefono_2 = telefono_2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
   
}
