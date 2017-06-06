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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(); // Para que se crea el objeto session acá ??

        RequestDispatcher view = request.getRequestDispatcher("login.jsp");
        view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String password = request.getParameter("passwd");
        String consulta = "SELECT passwd FROM usuario WHERE email=\"" + correo + "\"";
        RequestDispatcher view = null;
        try {
            Class.forName(ConexionBD.CONTROLADOR); //Carga el controlador a la clase

            // Se especifican las propiedades del objeto JdbcRowSet
            JdbcRowSet rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(ConexionBD.URL_BASEDATOS); // Establece la URL de la base de datos
            rowSet.setUsername(ConexionBD.NOMBREUSUARIO); // Establece el nombre del usuario en la BD
            rowSet.setPassword(ConexionBD.PASSWORD); // Establece el password de la BD
            rowSet.setCommand(consulta); // Establece la consulta
            rowSet.execute(); // Ejecuta la consulta
            while(rowSet.next()){
            if (password.equals(rowSet.getObject(1))) {
                view = request.getRequestDispatcher("formularios/registros.jsp");
            } else {
                view = request.getRequestDispatcher("login.jsp");
            }
            }

        } catch (SQLException ex) {
            view = request.getRequestDispatcher("login.jsp");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            view = request.getRequestDispatcher("login.jsp");
            ex.printStackTrace();
        }
        finally{
            view.forward(request, response);
        }

    }

}
