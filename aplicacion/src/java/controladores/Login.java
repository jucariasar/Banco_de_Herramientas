package controladores;

import com.sun.rowset.JdbcRowSetImpl;
import conexionBD.ConexionBD;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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

        //Almacena un String para posteriormente realizar una consulta a la base de datos con el correo.
        String consulta = "SELECT passwd FROM usuario WHERE email=\"" + correo + "\"";

        // Variable de referencia para invocar posteriormente a un recurso Web
        RequestDispatcher view = null;
        try {
            // Carga el controlador a la clase
            Class.forName(ConexionBD.CONTROLADOR);

            // Se especifican las propiedades del objeto JdbcRowSet
            JdbcRowSet rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(ConexionBD.URL_BASEDATOS); // Establece la URL de la base de datos
            rowSet.setUsername(ConexionBD.NOMBREUSUARIO); // Establece el nombre del usuario en la BD
            rowSet.setPassword(ConexionBD.PASSWORD); // Establece el password de la BD
            rowSet.setCommand(consulta); // Establece la consulta
            rowSet.execute(); // Ejecuta la consulta

            // Recorre las filas de la consulta
            while (rowSet.next()) {

                // Si el password ingresado por el usuario al cual pertenece el correo
                // es igual al password en la base de datos
                if (password.equals(rowSet.getObject(1))) {
                    // Invoca de modo directo al recurso Web registros.jsp
                    view = request.getRequestDispatcher("formularios/registros.jsp");
                } else { // De lo contrario
                    // Invoca al recurso web login.jsp
                    view = request.getRequestDispatcher("login.jsp");
                }
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
