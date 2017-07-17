package controladores;

import com.sun.rowset.JdbcRowSetImpl;
import conexionBD.ConexionBD;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.JdbcRowSet;
import modelos.Regional;
import modelos.Usuario;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    // Se desplega la vista de login.jsp
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Invoca al recurso web Login.jsp
        RequestDispatcher view = request.getRequestDispatcher("login.jsp");

        // Reenvia la solicitud o petición del Sevlet al jsp
        view.forward(request, response);

    }

    // Se invoca el método POST desde Login.jsp
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // obtiene los parametros de la vista desde la cual se llama el método POST
        String correo = request.getParameter("correo"); // Obtiene el parametro "correo" de la vista Login.jsp
        String password = request.getParameter("passwd"); // Obtiene el parametro "passwd" de la vista Login.jsp

        // Almacena un String para posteriormente realizar una consulta en
        // la base de datos con el correo y la contraseña.
        String consulta="SELECT email, passwd FROM usuario WHERE email=\""
                + "" + correo + "\" AND passwd=SHA(\"" + password+ "\")";
        
        // Variable de referencia para invocar posteriormente a un recurso Web
        RequestDispatcher view = null;
        
        try {
            // Se invoca al método statico conectarConsulta el cual se encarga de realizar
            // la conexion con la BD y ejecutar la consulta que se quiera,
            // Y devuelve los resultados de la consulta.
            JdbcRowSet rowSet = ConexionBD.conectarConsulta(consulta);

                // Si la consulta realizada tiene alguna coincidencia con los datos
                // registrados en la base de datos se le da acceso al usuario.
                if (rowSet.next()) {
                    HttpSession session = request.getSession();
                    
                    // Guarda los datos en session por si son requeridos despues
                    // no tener que consularlos en la base de datos
                    session.setAttribute("usuario", correo);
                    session.setAttribute("passwd", password);
                    
                    // Invoca de modo directo al recurso Web registros.jsp
                    view = request.getRequestDispatcher("formularios/registros.jsp");
                } else { // De lo contrario
                    // Invoca al recurso web login.jsp
                    view = request.getRequestDispatcher("login.jsp");
                }
            
        } catch (SQLException ex) {
            // Si la consulta no es correcta lanza un SQLException
            // Invoca de modo directo al recurso login.jsp
            view = request.getRequestDispatcher("login.jsp");
            // Imprime la pila de la traza
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            // Si el controlador no es el correcto lanza un ClassNotFoundException
            // Invoca de modo directo al recurso login.jsp
            view = request.getRequestDispatcher("login.jsp");
            ex.printStackTrace();
        } finally {
            // Reenvia la petición del Servlet al recurso Web que se haya invocado
            view.forward(request, response);
        }
    }
}
