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

    // Se llama el método GET de este Servlet desde el jsp "registros.jsp"
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Optiene mediante la URL de la petición por método GET el parametro "accion" enviado de registros.jsp
        // y lo convierte a un dato de tipo entero con el método parseInt de la clase Integer
        // para luego poder procesalo en un switch case
        int opt = Integer.parseInt(request.getParameter("accion"));

        // Variable de referencia para invocar de modo directo al recurso Web (la vista que se va a desplegar)
        RequestDispatcher view = null;

        // De acuerso al valor de "accion" que se almacena en la variable de tipo entero opt
        // se debe desplegar un formulario diferente
        switch (opt) {
            case 1: // Caso 1 Registrar Regional
                // Invoca al recurso Web registro_regional.jsp)
                view = request.getRequestDispatcher("formularios/registro_regional.jsp");
                break;
            case 2:  // Caso 2 Registrar Centro
                try {
                    // Obtine la lista de regionales registradas en la BD
                    List<Regional> regionales = Regional.consultarRegionales();

                    // pasa la lista de regionales al JSP que se invoca con el objeto RequestDispacher
                    request.setAttribute("rgnl", regionales);
                    // Invoca de modo directo al recurso Web registro_centro.jsp
                    view = request.getRequestDispatcher("formularios/registro_centro.jsp");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } 

                break;
            case 3: // Caso 3 Registrar Area

                // ArrayList de Centros para almacenar los centros actualmente en la BD
                List<Centro> centros = new ArrayList<>();

                try {
                    Class.forName(ConexionBD.CONTROLADOR); //Carga el controlador a la clase

                    // Se especifican las propiedades del objeto JdbcRowSet
                    JdbcRowSet rowSet = new JdbcRowSetImpl();
                    rowSet.setUrl(ConexionBD.URL_BASEDATOS); // Establece la URL de la base de datos
                    rowSet.setUsername(ConexionBD.NOMBREUSUARIO); // Establece el nombre del usuario en la BD
                    rowSet.setPassword(ConexionBD.PASSWORD); // Establece el password de la BD
                    rowSet.setCommand("SELECT * FROM centro ORDER BY codigo_regional"); // Establece la consulta
                    rowSet.execute(); // Ejecuta la consulta

                    ResultSetMetaData metaDatos = rowSet.getMetaData(); // Obtine los datos del esquema de la BD
                    int numeroDeColumnas = metaDatos.getColumnCount(); // Obtiene el el numero de columnas de la BD

                    // Recorre cada fila
                    while (rowSet.next()) {
                        Centro ctemp = new Centro();
                        for (int i = 1; i <= numeroDeColumnas; i++) {
                            if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                                ctemp.setCodigo((int) rowSet.getObject(i)); // Establece el código en un objeto Regional
                            } else if (i == 2) { // optiene los datos de la segunda columna columna en cada recorrido de filas
                                ctemp.setNombre((String) rowSet.getObject(i)); // Establece el nombre en un objeto Regional
                            } else {
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
            case 4: // Caso 4 registrar usuario

                // Lista para almacenar las regionales
                List<Regional> rles = new ArrayList<>();

                // Lista para almacenar los centros
                List<Centro> cts = new ArrayList<>();

                try {
                    //Carga el controlador de la clase
                    Class.forName(ConexionBD.CONTROLADOR);

                    // Se especifican las propiedades del objeto JdbcRowSet
                    JdbcRowSet rowSet = new JdbcRowSetImpl(); // Crea objetos rowSet para manejar las consultas
                    rowSet.setUrl(ConexionBD.URL_BASEDATOS); // Establece la URL de la base de datos
                    rowSet.setUsername(ConexionBD.NOMBREUSUARIO); // Establece el nombre del usuario en la BD
                    rowSet.setPassword(ConexionBD.PASSWORD); // Establece el password de la BD
                    rowSet.setCommand("SELECT * FROM regional ORDER BY codigo"); // Establece la consulta de regional
                    rowSet.execute(); // Ejecuta la consulta de regional

                    // Obtiene los datos del esquema de la tabla regional (Nombre de las columnas)
                    ResultSetMetaData metaDatos = rowSet.getMetaData();

                    // Obtiene el numero de columnas de la tabla regional
                    int numeroDeColumnas = metaDatos.getColumnCount();

                    // Recorre cada fila de la consulta de la tabla regional
                    while (rowSet.next()) {

                        // Crea objeto Regional temporal para despues guardarlo en el ArrayList regionales
                        Regional rtemp = new Regional();

                        // for para recorrer cada columna en la fila
                        for (int i = 1; i <= numeroDeColumnas; i++) {

                            // Si es la primera columna
                            if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                                rtemp.setCodigo((int) rowSet.getObject(i)); // Establece el código en un objeto Regional
                            } else { // optiene los datos de la segunda columna columna en cada recorrido de filas
                                rtemp.setNombre_departamento((String) rowSet.getObject(i)); // Establece el nombre en un objeto Regional
                            }
                        } // Fin del for
                        rles.add(rtemp); // Guarda el objeto creado en una lista de objetos Regional
                    } // Fin del while

                    // pasa la lista regionales al JSP que se invoca con el objeto RequestDispacher
                    request.setAttribute("rgnl", rles);

                    /*
                    int codigoR;
                    codigoR = Integer.parseInt(request.getParameter("codigo_regional"));
                    
                    // Establece la consulta de los centros asociados a la regional seleccionada
                    rowSet.setCommand(("SELECT * FROM centro WHERE codigo_regional=" + codigoR));
                    rowSet.execute(); // Ejecuta la consulta de centros de la regional seleccionada

                    // Obtiene los datos del esquema de la tabla centros (Nombre de las columnas)
                    metaDatos = rowSet.getMetaData();

                    // Obtiene el el numero de columnas de la tabla centro
                    numeroDeColumnas = metaDatos.getColumnCount();

                    // Recorre cada fila
                    while (rowSet.next()) {
                        Centro ctemp = new Centro();
                        for (int i = 1; i <= numeroDeColumnas; i++) {
                            if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                                ctemp.setCodigo((int) rowSet.getObject(i)); // Establece el código en un objeto Regional
                            } else if (i == 2) { // optiene los datos de la segunda columna columna en cada recorrido de filas
                                ctemp.setNombre((String) rowSet.getObject(i)); // Establece el nombre en un objeto Regional
                            } else {
                                ctemp.setCodigo_regional((int) rowSet.getObject(i));
                            }
                        }
                        cts.add(ctemp); // Guarda el objeto creado en una lista de objetos Regional
                    }
                    request.setAttribute("cent", cts); // pasa la lista regionales a un JSP como rgnl
                    //view = request.getRequestDispatcher("formularios/registro_usuario.jsp");
                     */
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } finally {
                    // Invoca de modo directo al recurso Web registro_centro.jsp
                    view = request.getRequestDispatcher("formularios/registro_usuario.jsp");
                }

                break;
            default:
                view = request.getRequestDispatcher("formularios/registros.jsp");
        }

        // Reenvia la solicitud o petición del Servlet al jsp
        view.forward(request, response);
    }

    // Método POST que se llama en cada formulario de registro cuando se preciona el boton del formulario
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtiene el valor (value) de la petición del objeto llamado boton_registro y la
        // alamcena en el objeto String opcion
        String opcion = request.getParameter("boton_registro");

        // Si el valor de "opcion" es "Insertar Regional" procede a insertar los dados de la regional
        if (opcion.equals("Insertar Regional")) {
            // Obtiene el parametro "codigo" del JSP
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            // Obtiene el parametro "nombre" del JSP
            String nombre = request.getParameter("nombre");

            int resultado = 0; // Para guardar la cantidad de filas que se modificaron en la inserccion 
            Connection conexion = null; // Para manejar la conexion a la base de datos
            PreparedStatement instruccion = null; // Maneja la instruccion  de inserccion de SQL

            try {
                // Carga el controlador de la base de datos
                Class.forName(ConexionBD.CONTROLADOR);

                // Conexion a la base de datos mediante los respectivos parametros
                // de direccion, usuario y contraseña
                conexion
                        = DriverManager.getConnection(ConexionBD.URL_BASEDATOS,
                                ConexionBD.NOMBREUSUARIO, ConexionBD.PASSWORD);

                // Maneja una instrucción de inserccion en la base de datos
                instruccion = conexion.prepareStatement("INSERT INTO regional "
                        + "(codigo, nombre_departamento) "
                        + "VALUES (?, ?)");

                instruccion.setInt(1, codigo); // Inserta dato de tipo int en el primer ? de instruccion
                instruccion.setString(2, nombre); // Inserta dato de tipo String en el segundo ? de instruccion
                resultado = instruccion.executeUpdate(); // Ejecuta la instrucción y devuelve cuantas filas fueron
                // modificacdas con la instrucción

            } catch (SQLException ex) {
                // Lanza excepcion SQLException si la insercción no es exitosa.
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                // Lanza excepcion ClassNotFoundException si el controlador no se carga correctamente
                ex.printStackTrace();
            } finally {

                // Objeto para invocar recurso Web desde
                RequestDispatcher view = request.getRequestDispatcher("formularios/registro_regional.jsp");

                // Renvia la solicitud o petición del Servlet al JSP (Para que se desplegue la vista registro_regional.jsp)
                view.forward(request, response);

                try {
                    //Cierra la instrucción
                    instruccion.close();

                    // Cierra la conexion con la BD
                    conexion.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            // Si el valor de "opcion" es "Insertar Centro" procede a insertar los dados del centro
        } else if (opcion.equals("Insertar Centro")) {

            // ArrayList de Regional para almacenar las regionales registradas
            List<Regional> regionales = new ArrayList<>();

            // Obtiene el parametro "nombre" del JSP que invoco el método POST de este Servlet (registro_centro.jsp)
            String nombre = request.getParameter("nombre");

            // Obtiene el parametro "codigo" del JSP que invoco el método POST de este Servlet (registro_centro.jsp)
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            // Obtiene el parametro "codigo_regional" del JSP que invoco el método POST de este Servlet (registro_centro.jsp)
            int codigoR = Integer.parseInt(request.getParameter("codigo_regional"));

            int resultado = 0; // Para guardar cuantas filas fueron modificadas
            Connection conexion = null; // Para manejar la conexión con la BD
            PreparedStatement instruccion = null; // Instruccion para manejar insreciones
            Statement instruccionQuery = null; // Instrucción para manejar consultas
            ResultSet conjuntoResultados = null; // Objeto para guardar el resultado de una consulta

            try {
                // Carga el controlador de la base de datos
                Class.forName(ConexionBD.CONTROLADOR);

                // Realiza la conexion a la BD envianto la URL, el nombre de usuario y el password
                conexion
                        = DriverManager.getConnection(ConexionBD.URL_BASEDATOS,
                                ConexionBD.NOMBREUSUARIO, ConexionBD.PASSWORD);

                // Instruccion para insertar valores en la base de datos
                instruccion = conexion.prepareStatement("INSERT INTO centro "
                        + "VALUES(?, ?, ?)"); //

                instruccion.setInt(1, codigo); // Establece dato entero del objeto prepareStatement en el primer ?
                instruccion.setString(2, nombre); // Establece dato String del objeto prepareStatement en el segundo ?
                instruccion.setInt(3, codigoR); // Establece dato entero del objeto prepareStatement en el tercer ?
                resultado = instruccion.executeUpdate(); // Ejecuta la instrucción de insersion guardada previamente

                // Crea objeto Statement para consultar la base de datos
                instruccionQuery = conexion.createStatement();

                // Consulta la base de datos y almacena la consulta en conjuntoResultados
                conjuntoResultados = instruccionQuery.executeQuery("SELECT * FROM regional");

                // Obtiene el esquema de la base de datos (nombres de columnas)
                ResultSetMetaData metaDatos = conjuntoResultados.getMetaData();

                // Obtiene el numero de columnas del esquema.
                int numeroDeColumnas = metaDatos.getColumnCount();

                // recorre la consulta por filas
                while (conjuntoResultados.next()) {

                    // Objeto Regional temporal para almacenar los datos de cada fila en la consulta
                    Regional rtemp = new Regional();

                    // Recorre cada campo de la fila
                    for (int i = 1; i <= numeroDeColumnas; i++) {

                        // Si es la primera columna de la consulta (columna código)
                        if (i == 1) { // optiene los datos de la primera columna en cada recorrido de filas
                            rtemp.setCodigo((int) conjuntoResultados.getObject(i)); // Establece el código en un objeto Regional
                        } else { // optiene los datos de la segunda columna columna en cada recorrido de filas
                            rtemp.setNombre_departamento((String) conjuntoResultados.getObject(i)); // Establece el nombre en un objeto Regional
                        }
                    }
                    regionales.add(rtemp); // Guarda el objeto creado en una lista de objetos Regional
                }
                request.setAttribute("rgnl", regionales); // pasa la lista regionales al JSP que realizo la solicitud

            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                // Invoca al recurso Web registro_centro.jsp
                RequestDispatcher view = request.getRequestDispatcher("formularios/registro_centro.jsp");
                view.forward(request, response);
                try {
                    instruccion.close();
                    conexion.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        } else if (opcion.equals("Insertar Area")) {

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
                        } else if (i == 2) { // optiene los datos de la segunda columna columna en cada recorrido de filas
                            ctemp.setNombre((String) conjuntoResultados.getObject(i)); // Establece el nombre en un objeto Regional
                        } else {
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
