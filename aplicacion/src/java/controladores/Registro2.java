/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import com.sun.rowset.JdbcRowSetImpl;
import conexionBD.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.JdbcRowSet;
import modelos.Centro;
import modelos.Regional;

@WebServlet(name = "Registro2", urlPatterns = {"/Registro2"})
public class Registro2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Usuario user = new Usuario();
        
        user.setCarne(Integer.parseInt(request.getParameter("documento")));
        user.setNombres(request.getParameter("nombres"));
        user.setApellidos(request.getParameter("apellidos"));
        user.setTelefono_1(request.getParameter("telefono1"));
        user.setTelefono_2(request.getParameter("telefono2"));
        user.setPasswd(request.getParameter("passwd"));
        
        int codigoR = Integer.parseInt(request.getParameter("codigo_regional"));
        
        RequestDispatcher view = null;
        
        // Lista para almacenar la lista de centros de la regional seleccinada
        List<Centro> cts = new ArrayList<>();
        
        // Obtiene el código de la regional seleccionada
        
        
        try {
                    //Carga el controlador de la clase
                    Class.forName(ConexionBD.CONTROLADOR);

                    // Se especifican las propiedades del objeto JdbcRowSet
                    JdbcRowSet rowSet = new JdbcRowSetImpl(); // Crea objetos rowSet para manejar las consultas
                    rowSet.setUrl(ConexionBD.URL_BASEDATOS); // Establece la URL de la base de datos
                    rowSet.setUsername(ConexionBD.NOMBREUSUARIO); // Establece el nombre del usuario en la BD
                    rowSet.setPassword(ConexionBD.PASSWORD); // Establece el password de la BD
                    rowSet.setCommand(("SELECT * FROM centro WHERE codigo_regional=" + codigoR)); // Establece la consulta de regional
                    rowSet.execute(); // Ejecuta la consulta de regional
                    
                    // Obtiene los datos del esquema de la tabla regional (Nombre de las columnas)
                    ResultSetMetaData metaDatos = rowSet.getMetaData();
                    
                    // Obtiene el numero de columnas de la tabla regional
                    int numeroDeColumnas = metaDatos.getColumnCount();
                    
                    // Obtiene los datos del esquema de la tabla centros (Nombre de las columnas)
                    metaDatos = rowSet.getMetaData();

                    // Obtiene el el numero de columnas de la tabla centro
                    numeroDeColumnas = metaDatos.getColumnCount();

                    // Recorre cada fila
                    while (rowSet.next()) {
                        Centro ctemp = new Centro();
                        for (int i = 1; i <= numeroDeColumnas; i++) {
                            if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                                ctemp.setCodigo((int) rowSet.getObject(i)); // Establece el código en un objeto Centro
                            } else if (i == 2) { // optiene los datos de la segunda columna columna en cada recorrido de filas
                                ctemp.setNombre((String) rowSet.getObject(i)); // Establece el nombre en un objeto Centro
                            } else {
                                ctemp.setCodigo_regional((int) rowSet.getObject(i));
                            }
                        }
                        cts.add(ctemp); // Guarda el objeto creado en una lista de objetos Regional
                    }
                    request.setAttribute("cent", cts); // pasa la lista regionales a un JSP como rgnl
                    //view = request.getRequestDispatcher("formularios/registro_usuario.jsp");
                   
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    
            
            
            
                    // Invoca de modo directo al recurso Web registro_usuario.jsp
                    view = request.getRequestDispatcher("formularios/registro_usuario.jsp");
                }
                view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
