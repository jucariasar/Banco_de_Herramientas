/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.sun.rowset.JdbcRowSetImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import conexionBD.ConexionBD;

@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int opt = Integer.parseInt(request.getParameter("accion"));
        
        RequestDispatcher view ;
        
        switch(opt){
            case 1:
                view = request.getRequestDispatcher("registro_regional.jsp");
                System.out.print("Ingreseo a opcion 1");
                break;
            case 2:
                view = request.getRequestDispatcher("registro_centro.jsp");
                System.out.print("Ingreseo a opcion 2");
                break;
            case 3:
                view = request.getRequestDispatcher("registro_area.jsp");
                System.out.print("Ingreseo a opcion 1");
                break;
            default:
                view = request.getRequestDispatcher("registro_regional.jsp");          
        }
        view.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.print("Prueba de Conexion");
        
       
        //String valor = request.getParameter("r_regional");
        int codigo;
        String nombre;
        
        
        //if(valor.equals("Insertar Regional"))
        //{
            codigo = Integer.parseInt(request.getParameter("codigo"));
            nombre = request.getParameter("nombre");
            
            String insertarR = "INSERT INTO " + "regional" + " VALUES (" + codigo + ", " + nombre+")";
            try{
                Class.forName(ConexionBD.CONTROLADOR);
                
                JdbcRowSet rowSet= new JdbcRowSetImpl();
                rowSet.setUrl(ConexionBD.URL_BASEDATOS);
                rowSet.setUsername(ConexionBD.NOMBREUSUARIO);
                rowSet.setPassword(ConexionBD.PASSWORD);
                rowSet.setCommand(insertarR);
                rowSet.execute();
                System.out.print("Dato Insertado Correctamente");
            }
            catch(SQLException exceptionSql){
                
                exceptionSql.printStackTrace();
                System.exit(1);
            }
            catch(ClassNotFoundException noEncontroClase){
                noEncontroClase.printStackTrace();
                System.exit(1);
            }
        //           
    }

}
