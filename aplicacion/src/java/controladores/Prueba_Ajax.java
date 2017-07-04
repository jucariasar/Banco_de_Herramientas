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
import modelos.Centro;
import modelos.Regional;
import org.json.simple.JSONArray;

@WebServlet(name = "Prueba_Ajax", urlPatterns = {"/Prueba_Ajax"})
public class Prueba_Ajax extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Lista para almacenar las regionales
            List<Regional> regionales = Regional.consultarRegionales();
            request.setAttribute("rgnl", regionales);
            // Invoca de modo directo al recurso Web registro_centro.jsp
            RequestDispatcher view = request.getRequestDispatcher("prueba_ajax.jsp");
            // Reenvia la solicitud o petición del Servlet al jsp
            view.forward(request, response);
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

        response.setContentType("text/html; charset=iso-8859-1");//
        PrintWriter out = response.getWriter();

        // Lista para almacenar los centros
        List<Centro> cts = new ArrayList<>();

        //Lista de objetos en formato JSON para enviarle al JavaScript
        JSONArray array = new JSONArray();

        int codigoR = Integer.parseInt(request.getParameter("codR"));

        try {
            //Carga el controlador de la clase
            Class.forName(ConexionBD.CONTROLADOR);

            JdbcRowSet rowSet = new JdbcRowSetImpl(); // Crea objetos rowSet para manejar las consultas
            rowSet.setUrl(ConexionBD.URL_BASEDATOS); // Establece la URL de la base de datos
            rowSet.setUsername(ConexionBD.NOMBREUSUARIO); // Establece el nombre del usuario en la BD
            rowSet.setPassword(ConexionBD.PASSWORD); // Establece el password de la BD
            rowSet.setCommand(("SELECT * FROM centro WHERE codigo_regional=" + codigoR + " ORDER BY codigo"));
            rowSet.execute(); // Ejecuta la consulta de centros de la regional seleccionada

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
                //cts.add(ctemp); // Guarda el objeto creado en una lista de objetos Regional
            }
            StringWriter o = new StringWriter();
            try {
                array.writeJSONString(o);
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println(o);

            // pasa la lista regionales a un JSP como rgnl
            //RequestDispatcher view = request.getRequestDispatcher("prueba_ajax.jsp");
            //request.setAttribute("cent", cts);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
