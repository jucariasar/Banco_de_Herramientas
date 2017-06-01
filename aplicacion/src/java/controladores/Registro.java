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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Centro;
import modelos.Regional;

@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int opt = Integer.parseInt(request.getParameter("accion"));

        RequestDispatcher view = null; // Para invocar al recurso web

        // De acuerso al valor de accion se debe desplegar un formulario diferente
        switch (opt) {
            case 1:
                view = request.getRequestDispatcher("formularios/registro_regional.jsp");
                break;
            case 2:
                List<Regional> regionales = new ArrayList<>(); // Arreglo para guardar los
                //objetos regional que se asocian a cada centro

                try {
                    Class.forName(ConexionBD.CONTROLADOR); //Carga el controlador a la clase

                    // Se especifican las propiedades del objeto JdbcRowSet
                    JdbcRowSet rowSet = new JdbcRowSetImpl();
                    rowSet.setUrl(ConexionBD.URL_BASEDATOS); // Establece la URL de la base de datos
                    rowSet.setUsername(ConexionBD.NOMBREUSUARIO); // Establece el nombre del usuario en la BD
                    rowSet.setPassword(ConexionBD.PASSWORD); // Establece el password de la BD
                    rowSet.setCommand("SELECT * FROM regional"); // Establece la consulta
                    rowSet.execute(); // Ejecuta la consulta

                    ResultSetMetaData metaDatos = rowSet.getMetaData(); // Obtine los datos del esquema de la BD
                    int numeroDeColumnas = metaDatos.getColumnCount(); // Ontiene el el numero de columnas de la BD

                    // Recorre cada fila
                    while (rowSet.next()) {
                        Regional rtemp = new Regional();
                        for (int i = 1; i <= numeroDeColumnas; i++) {
                            if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                                rtemp.setCodigo((int) rowSet.getObject(i)); // Establece el código en un objeto Regional
                            } else { // optiene los datos de la segunda columna columna en cada recorrido de filas
                                rtemp.setNombre_departamento((String) rowSet.getObject(i)); // Establece el nombre en un objeto Regional
                            }
                        }
                        regionales.add(rtemp); // Guarda el objeto creado en una lista de objetos Regional
                    }
                    request.setAttribute("rgnl", regionales); // pasa la lista regionales a un JSP como rgnl

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    view = request.getRequestDispatcher("formularios/registro_centro.jsp");
                }
                break;
            case 3:
                List<Centro> centros = new ArrayList<>(); // Arreglo para guardar los
                //objetos centros que se asocian a cada centro

                try {
                    Class.forName(ConexionBD.CONTROLADOR); //Carga el controlador a la clase

                    // Se especifican las propiedades del objeto JdbcRowSet
                    JdbcRowSet rowSet = new JdbcRowSetImpl();
                    rowSet.setUrl(ConexionBD.URL_BASEDATOS); // Establece la URL de la base de datos
                    rowSet.setUsername(ConexionBD.NOMBREUSUARIO); // Establece el nombre del usuario en la BD
                    rowSet.setPassword(ConexionBD.PASSWORD); // Establece el password de la BD
                    rowSet.setCommand("SELECT * FROM centro"); // Establece la consulta
                    rowSet.execute(); // Ejecuta la consulta

                    ResultSetMetaData metaDatos = rowSet.getMetaData(); // Obtine los datos del esquema de la BD
                    int numeroDeColumnas = metaDatos.getColumnCount(); // Ontiene el el numero de columnas de la BD

                    // Recorre cada fila
                    while (rowSet.next()) {
                        Centro ctemp = new Centro();
                        for (int i = 1; i <= numeroDeColumnas; i++) {
                            if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                                ctemp.setCodigo((int) rowSet.getObject(i)); // Establece el código en un objeto Regional
                            } else if(i == 2){ // optiene los datos de la segunda columna columna en cada recorrido de filas
                                ctemp.setNombre((String) rowSet.getObject(i)); // Establece el nombre en un objeto Regional
                            }
                            else{
                                ctemp.setCodigo_regional((int) rowSet.getObject(i));
                            }
                        }
                        centros.add(ctemp); // Guarda el objeto creado en una lista de objetos Regional
                    }
                    request.setAttribute("cent", centros); // pasa la lista regionales a un JSP como rgnl

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    view = request.getRequestDispatcher("formularios/registro_area.jsp");
                }
                break;

            default:
                view = request.getRequestDispatcher("formularios/area.jsp");
        }
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("boton_registro");

        if (opcion.equals("Insertar Regional")) // Inserta datos de la Regional
        {

            int codigo = Integer.parseInt(request.getParameter("codigo"));
            String nombre = request.getParameter("nombre");

            int resultado = 0; //Guarda la cantidad de filas que se modificaron en la inserccion 
            Connection conexion = null; // Para manejar la conexion a la base de datos
            PreparedStatement instruccion = null; // Maneja la instruccion  de inserccion de sql

            try {
                Class.forName(ConexionBD.CONTROLADOR); // Carga el controlador

                // Conexion a la base de datos mediante los respectivos parametros
                // de direccion, usuario y contraseña
                conexion
                        = DriverManager.getConnection(ConexionBD.URL_BASEDATOS,
                                ConexionBD.NOMBREUSUARIO, ConexionBD.PASSWORD);

                // Maneja una instrucción de inserccion en la base de datos
                instruccion = conexion.prepareStatement("INSERT INTO regional "
                        + "(codigo, nombre_departamento) "
                        + "VALUES (?, ?)");

                instruccion.setInt(1, codigo); // Inserta dato de tipo int en el primer ? de instrucción
                instruccion.setString(2, nombre); // Inserta dato de tipo String en el segundo ? de instrucción
                resultado = instruccion.executeUpdate(); // Ejecuta la instrucción y devuelve cuantas filas fueron
                // modificacdas con la instrucción

            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                RequestDispatcher view = request.getRequestDispatcher("formularios/registro_regional.jsp");
                view.forward(request, response);
                try {
                    instruccion.close();
                    conexion.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

        } else if (opcion.equals("Insertar Centro")) {

            List<Regional> regionales = new ArrayList<>();

            String nombre = request.getParameter("nombre");
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            int codigoR = Integer.parseInt(request.getParameter("codigo_regional"));

            int resultado = 0; // Para guardar cuantas filas fueron modificadas
            Connection conexion = null; // Para manejar la conexión con la BD
            PreparedStatement instruccion = null; // Instruccion para manejar insesiones
            Statement instruccionQuery = null; // Instrucción para manejar consultas
            ResultSet conjuntoResultados = null; // Objeto para guardar el resultado de una consulta

            try {
                Class.forName(ConexionBD.CONTROLADOR);

                conexion
                        = DriverManager.getConnection(ConexionBD.URL_BASEDATOS,
                                ConexionBD.NOMBREUSUARIO, ConexionBD.PASSWORD);

                instruccion = conexion.prepareStatement("INSERT INTO centro "
                        + "VALUES(?, ?, ?)"); //

                instruccion.setInt(1, codigo); // Establece dato entero del objeto prepareStatement en el primer ? en la linea 168
                instruccion.setString(2, nombre); // Establece dato String del objeto prepareStatement en el segundo ? en la linea 168
                instruccion.setInt(3, codigoR); // Establece dato entero del objeto prepareStatement en el tercer ? en la linea 168
                resultado = instruccion.executeUpdate(); // Ejecuta la instrucción de insersion guardada previamente

                // Crea objeto Statement para consultar la base de datos
                instruccionQuery = conexion.createStatement();

                // Consulta la base de datos
                conjuntoResultados = instruccionQuery.executeQuery("SELECT * FROM regional");

                // Procesa los resultados de la consulta
                ResultSetMetaData metaDatos = conjuntoResultados.getMetaData();
                int numeroDeColumnas = metaDatos.getColumnCount();

                while (conjuntoResultados.next()) {
                    Regional rtemp = new Regional();
                    for (int i = 1; i <= numeroDeColumnas; i++) {
                        if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                            rtemp.setCodigo((int) conjuntoResultados.getObject(i)); // Establece el código en un objeto Regional
                        } else { // optiene los datos de la segunda columna columna en cada recorrido de filas
                            rtemp.setNombre_departamento((String) conjuntoResultados.getObject(i)); // Establece el nombre en un objeto Regional
                        }
                    }
                    regionales.add(rtemp); // Guarda el objeto creado en una lista de objetos Regional
                }
                request.setAttribute("rgnl", regionales); // pasa la lista regionales a un JSP como rgnl

            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {

                RequestDispatcher view = request.getRequestDispatcher("formularios/registro_centro.jsp");
                view.forward(request, response);
                try {
                    instruccion.close();
                    conexion.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
        else if(opcion.equals("Insertar Area")){
            
            List<Centro> centros = new ArrayList<>();

            String nombre = request.getParameter("nombre");
            int codigoC = Integer.parseInt(request.getParameter("codigo_centro"));
 
            int resultado = 0; // Para guardar cuantas filas fueron modificadas
            Connection conexion = null; // Para manejar la conexión con la BD
            PreparedStatement instruccion = null; // Instruccion para manejar insersiones
            Statement instruccionQuery = null; // Instrucción para manejar consultas
            ResultSet conjuntoResultados = null; // Objeto para guardar el resultado de una consulta

            try {
                Class.forName(ConexionBD.CONTROLADOR);

                conexion
                        = DriverManager.getConnection(ConexionBD.URL_BASEDATOS,
                                ConexionBD.NOMBREUSUARIO, ConexionBD.PASSWORD);

                instruccion = conexion.prepareStatement("INSERT INTO area "
                        + "VALUES(?, ?)"); //

               // instruccion.setInt(1, codigo); // Establece dato entero del objeto prepareStatement en el primer ? en la linea 168
                instruccion.setString(1, nombre); // Establece dato String del objeto prepareStatement en el segundo ? en la linea 168
                instruccion.setInt(2, codigoC); // Establece dato entero del objeto prepareStatement en el tercer ? en la linea 168
                resultado = instruccion.executeUpdate(); // Ejecuta la instrucción de insersion guardada previamente

                // Crea objeto Statement para consultar la base de datos
                instruccionQuery = conexion.createStatement();

                // Consulta la base de datos
                conjuntoResultados = instruccionQuery.executeQuery("SELECT * FROM centro");

                // Procesa los resultados de la consulta
                ResultSetMetaData metaDatos = conjuntoResultados.getMetaData();
                int numeroDeColumnas = metaDatos.getColumnCount();

                while (conjuntoResultados.next()) {
                    Centro ctemp = new Centro();
                    for (int i = 1; i <= numeroDeColumnas; i++) {
                        if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                            ctemp.setCodigo((int) conjuntoResultados.getObject(i)); // Establece el código en un objeto Regional
                        } else if(i == 2){ // optiene los datos de la segunda columna columna en cada recorrido de filas
                            ctemp.setNombre((String) conjuntoResultados.getObject(i)); // Establece el nombre en un objeto Regional
                        }else{
                            ctemp.setCodigo_regional((int) conjuntoResultados.getObject(i));
                        }
                    }
                    centros.add(ctemp); // Guarda el objeto creado en una lista de objetos Centro
                }
                request.setAttribute("cent", centros); // pasa la lista regionales a un JSP como rgnl

            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {

                RequestDispatcher view = request.getRequestDispatcher("formularios/registro_area.jsp");
                view.forward(request, response);
                try {
                    instruccion.close();
                    conexion.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
            
            
        }
    }
}
