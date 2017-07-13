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
import modelos.Programa;
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
                try {
                    // Obtine la lista de centros en la BD
                    List<Centro> centros = Centro.consultarCentros();

                    // Establece el atributo "cent" con la lista de centros, dicho atributo se pasará 
                    // con el objeto request, del Servlet al JSP mediante el objeto view con el método
                    // forward(request, response), que previamente obtuvo el jsp que se invocara mediante
                    // el llamado de request.getRequestDispatcher("formularios/registro_area.jsp");              // Renvia la solicitud o petición del Servlet al JSP (Para que se desplegue la vista registro_regional.jsp)
                    request.setAttribute("cent", centros);
                    view = request.getRequestDispatcher("formularios/registro_area.jsp");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                break;
            case 4: // Caso 4 registrar usuario

                view = request.getRequestDispatcher("formularios/registro_usuario.jsp");

                break;
            case 5: // Registrar elemento
                break;

            case 6: // Registrar programa

                view = request.getRequestDispatcher("formularios/registro_programa.jsp");

                break;
                
            case 7: // Registrar Ficha
                
                try {
                    // Obtine la lista de regionales registradas en la BD
                    List<Programa> programas = Programa.consultarProgramas();

                    // pasa la lista de regionales al JSP que se invoca con el objeto RequestDispacher
                    request.setAttribute("prma", programas);
                    // Invoca de modo directo al recurso Web registro_centro.jsp
                    view = request.getRequestDispatcher("formularios/registro_ficha.jsp");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
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
        // almacena en el objeto String opcion
        String opcion = request.getParameter("boton_registro");

        // Si el valor de "opcion" es "Insertar Regional" procede a insertar los dados de la regional
        if (opcion.equals("Insertar Regional")) {

            // Obtiene el valor del atributo name="codigo" del JSP desde el que se invoco el método POST
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            // Obtiene el valor del atributo name="codigo" del JSP desde el que se invoco el método POST
            String nombre = request.getParameter("nombre");

            // String con instrucción para ejecutar posteriormente.
            String instruccion = "INSERT INTO regional VALUES(?, ?)";

            // Para guardar la cantidad de filas que se modificaron en la inserccion
            int resultado = 0;

            // Crea objeto ConexionBD para iniciar la inserción de los datos.
            ConexionBD conectar = new ConexionBD();

            try {
                // Establece la instrucción que se debe de ejecutar y realiza la conexión con la BD
                conectar.ConectarRegistro(instruccion);

                // Establece el dato que se debe de insertar en el primer ? en la instrucción.
                conectar.getInstruccionInsercion().setInt(1, codigo);

                // Establece el dato que se debe de insertar en el segundo ? en la instrucción.
                conectar.getInstruccionInsercion().setString(2, nombre);

                // Ejecuta la instrucción y se devuelve el número de filas que se se modificaron con la instrucción.
                resultado = conectar.getInstruccionInsercion().executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {

                // Objeto para invocar recurso Web desde
                RequestDispatcher view = request.getRequestDispatcher("formularios/registro_regional.jsp");

                // Renvia la solicitud o petición del Servlet al JSP (Para que se desplegue la vista registro_regional.jsp)
                view.forward(request, response);

                try {          
                    conectar.cerrarConexiones();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
            // Si el valor de "opcion" es "Insertar Centro" procede a insertar los dados del centro
        } else if (opcion.equals("Insertar Centro")) {

            // Obtiene el parametro "nombre" del JSP que invoco el método POST de este Servlet (registro_centro.jsp)
            String nombre = request.getParameter("nombre");

            // Obtiene el parametro "codigo" del JSP que invoco el método POST de este Servlet (registro_centro.jsp)
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            // Obtiene el parametro "codigo_regional" del JSP que invoco el método POST de este Servlet (registro_centro.jsp)
            int codigoR = Integer.parseInt(request.getParameter("codigo_regional"));
            
            // String con instrucción para ejecutar posteriormente.
            String instruccion = "INSERT INTO centro VALUES(?, ?, ?)";
            
            // Para guardar la cantidad de filas que se modificaron en la inserccion
            int resultado = 0;

            // Crea objeto ConexionBD para iniciar la inserción de los datos.
            ConexionBD conectar = new ConexionBD();

            try {
                // ArrayList de Regional para almacenar las regionales registradas
                List<Regional> regionales = Regional.consultarRegionales();
                
                // Establece la instrucción que se debe de ejecutar y realiza la conexión con la BD
                conectar.ConectarRegistro(instruccion);

                // Establece el dato que se debe de insertar en el primer ? en la instrucción.
                conectar.getInstruccionInsercion().setInt(1, codigo);

                // Establece el dato que se debe de insertar en el segundo ? en la instrucción.
                conectar.getInstruccionInsercion().setString(2, nombre);
                
                // Establece el dato que se debe de insertar en el tercer ? en la instrucción.
                conectar.getInstruccionInsercion().setInt(3, codigoR);

                // Ejecuta la instrucción y se devuelve el número de filas que se se modificaron con la instrucción.
                resultado = conectar.getInstruccionInsercion().executeUpdate();
                
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
                    conectar.cerrarConexiones();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        } else if (opcion.equals("Insertar Area")) {


            String nombre = request.getParameter("nombre");
            int codigoC = Integer.parseInt(request.getParameter("codigo_centro"));
            
            // String con instrucción para ejecutar posteriormente.
            String instruccion = "INSERT INTO area VALUES(?, ?)";
            
            // Para guardar la cantidad de filas que se modificaron en la inserccion
            int resultado = 0;

            // Crea objeto ConexionBD para iniciar la inserción de los datos.
            ConexionBD conectar = new ConexionBD();

            try {
                // ArrayList de Regional para almacenar las regionales registradas
                List<Centro> centros = Centro.consultarCentros();
                
                // Establece la instrucción que se debe de ejecutar y realiza la conexión con la BD
                conectar.ConectarRegistro(instruccion);

                // Establece el dato que se debe de insertar en el primer ? en la instrucción.
                conectar.getInstruccionInsercion().setString(1, nombre);

                // Establece el dato que se debe de insertar en el segundo ? en la instrucción.
                conectar.getInstruccionInsercion().setInt(2, codigoC);
               
                // Ejecuta la instrucción y se devuelve el número de filas que se se modificaron con la instrucción.
                resultado = conectar.getInstruccionInsercion().executeUpdate();
                
                request.setAttribute("cent", centros); // pasa la lista regionales al JSP que realizo la solicitud

            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                // Invoca al recurso Web registro_centro.jsp
                RequestDispatcher view = request.getRequestDispatcher("formularios/registro_area.jsp");
                view.forward(request, response);
                try {
                    conectar.cerrarConexiones();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } else if (opcion.equals("Insertar Programa")) {

            // Obtiene valor atributo name="codigo" del JSP desde el que se invoco el método POST
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            // Obtiene valor del atributo name="version" del JSP desde el que se invoco el método POST
            int version = Integer.parseInt(request.getParameter("version"));

            // Obtiene valor del atributo name="nombre" del JSP desde el que se invoco el método POST
            String programa = request.getParameter("nombre");

            // String con instrucción para ejecutar posteriormente.
            String instruccion = "INSERT INTO programa VALUES(?, ?, ?)";
            
            // Para guardar la cantidad de filas que se modificaron en la inserccion
            int resultado = 0;

            // Crea objeto ConexionBD para iniciar la inserción de los datos.
            ConexionBD conectar = new ConexionBD();
            
            try {
                // Establece la instrucción que se debe de ejecutar y realiza la conexión con la BD
                conectar.ConectarRegistro(instruccion);

                // Establece el dato que se debe de insertar en el primer ? en la instrucción.
                conectar.getInstruccionInsercion().setInt(1, codigo);

                // Establece el dato que se debe de insertar en el segundo ? en la instrucción.
                conectar.getInstruccionInsercion().setInt(2, version);
                
                // Establece el dato que se debe de insertar en el tercer ? en la instrucción.
                conectar.getInstruccionInsercion().setString(3, programa);

                // Ejecuta la instrucción y se devuelve el número de filas que se se modificaron con la instrucción.
                resultado = conectar.getInstruccionInsercion().executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {

                // Objeto para invocar recurso Web desde
                RequestDispatcher view = request.getRequestDispatcher("formularios/registro_programa.jsp");

                // Renvia la solicitud o petición del Servlet al JSP (Para que se desplegue la vista registro_regional.jsp)
                view.forward(request, response);

                try {          
                    conectar.cerrarConexiones();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if(opcion.equals("Insertar Ficha")){
            
            int numeroFicha = Integer.parseInt(request.getParameter("numero"));
            
            int codPrograma = Integer.parseInt(request.getParameter("codigo_programa"));
            
            // String con instrucción para ejecutar posteriormente.
            String instruccion = "INSERT INTO ficha VALUES(?, ?, ?)";
            
            // Para guardar la cantidad de filas que se modificaron en la inserccion
            int resultado = 0;

            // Crea objeto ConexionBD para iniciar la inserción de los datos.
            ConexionBD conectar = new ConexionBD();
            
            /*
            try {
                // ArrayList de Regional para almacenar las regionales registradas
                List<Regional> regionales = Regional.consultarRegionales();
                
                // Establece la instrucción que se debe de ejecutar y realiza la conexión con la BD
                conectar.ConectarRegistro(instruccion);

                // Establece el dato que se debe de insertar en el primer ? en la instrucción.
                conectar.getInstruccionInsercion().setInt(1, codigo);

                // Establece el dato que se debe de insertar en el segundo ? en la instrucción.
                conectar.getInstruccionInsercion().setString(2, nombre);
                
                // Establece el dato que se debe de insertar en el tercer ? en la instrucción.
                conectar.getInstruccionInsercion().setInt(3, codigoR);

                // Ejecuta la instrucción y se devuelve el número de filas que se se modificaron con la instrucción.
                resultado = conectar.getInstruccionInsercion().executeUpdate();
                
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
                    conectar.cerrarConexiones();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }*/
        
        }
    }
}
