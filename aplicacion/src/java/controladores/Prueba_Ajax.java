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
import java.io.StringWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.JdbcRowSet;
import modelos.Area;
import modelos.Centro;
import modelos.Regional;
import org.json.simple.JSONArray;

@WebServlet(name = "Prueba_Ajax", urlPatterns = {"/Prueba_Ajax"})
public class Prueba_Ajax extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest solicitud, HttpServletResponse respuesta)
            throws ServletException, IOException {

        try {
            // Lista para almacenar las regionales
            List<Regional> regionales = Regional.consultarRegionales();

            // Establebe una variable para enviar al JSP con la lista de regionales
            solicitud.setAttribute("rgnl", regionales);

            // Invoca de modo directo al recurso Web registro_centro.jsp
            RequestDispatcher view = solicitud.getRequestDispatcher("prueba_ajax.jsp");

            // Reenvia la solicitud o petición del Servlet al jsp
            view.forward(solicitud, respuesta);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
        response.setContentType("text/html; charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        int codigo = Integer.parseInt(request.getParameter("codR"));
        out.println("Una prueba mas " + codigo);*/

        response.setContentType("text/html; charset=UTF-8");//
        PrintWriter out = response.getWriter();

        //Lista de objetos en formato JSON para enviarle al JavaScript
        JSONArray array = new JSONArray();

        int opt = Integer.parseInt(request.getParameter("opcion"));

        if (opt == 1) {
            int codigoR = Integer.parseInt(request.getParameter("codR"));

            String consulta = "SELECT * FROM centro WHERE codigo_regional=" + codigoR + " ORDER BY codigo";
            System.out.printf("%d", codigoR);
            try {
                // Ejecuta la consulta con el String consulta
                JdbcRowSet rowSet = ConexionBD.conectarConsulta(consulta);

                // Obtiene los datos del esquema de la tabla centros (Nombre de las columnas)
                ResultSetMetaData metaDatos = rowSet.getMetaData();

                // Obtiene el numero de columnas de la tabla centros
                int numeroDeColumnas = metaDatos.getColumnCount();

                // Recorre cada fila
                while (rowSet.next()) {
                    Centro ctemp = new Centro();
                    // Recorre las columnas
                    for (int i = 1; i <= numeroDeColumnas; i++) {
                        if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                            ctemp.setCodigo((int) rowSet.getObject(i)); // Establece el código en un objeto Regional
                        } else if (i == 2) { // optiene los datos de la segunda columna columna en cada recorrido de filas
                            ctemp.setNombre((String) rowSet.getObject(i)); // Establece el nombre en un objeto Regional
                        } else {
                            ctemp.setCodigo_regional((int) rowSet.getObject(i));
                        }
                    }
                    array.add(ctemp);
                }

                StringWriter o = new StringWriter();
                try {
                    array.writeJSONString(o);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.println(o);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prueba_Ajax.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prueba_Ajax.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(opt == 2){
            
            int codigoC = Integer.parseInt(request.getParameter("codC"));

            String consulta = "SELECT * FROM area WHERE codigo_centro=" + codigoC + " ORDER BY codigo_centro";
            System.out.print(codigoC);
            try {
                // Ejecuta la consulta con el String consulta
                JdbcRowSet rowSet = ConexionBD.conectarConsulta(consulta);
                
                // Obtiene los datos del esquema de la tabla centros (Nombre de las columnas)
                ResultSetMetaData metaDatos = rowSet.getMetaData();
                
                // Obtiene el numero de columnas de la tabla areas
                int numeroDeColumnas = metaDatos.getColumnCount();
                
                // Recorre cada fila
                while (rowSet.next()) {
                    Area atemp = new Area();
                    
                    // Recorre las columnas
                    for (int i = 1; i <= numeroDeColumnas; i++) {
                        if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                            atemp.setNombre((String) rowSet.getObject(i)); // Establece el código en un objeto Regional
                        } else {
                            atemp.setCodigo_centro((int) rowSet.getObject(i));
                        }
                    }
                    array.add(atemp);
                }
                StringWriter o = new StringWriter();
                try {
                    array.writeJSONString(o);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out.println(o);
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Prueba_Ajax.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prueba_Ajax.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        }
    }

}
