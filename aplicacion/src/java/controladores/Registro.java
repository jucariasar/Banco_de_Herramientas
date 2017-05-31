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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int opt = Integer.parseInt(request.getParameter("accion"));

        RequestDispatcher view;

        switch (opt) {
            case 1:
                view = request.getRequestDispatcher("formularios/registro_regional.jsp");
                break;
            case 2:
                view = request.getRequestDispatcher("formularios/registro_centro.jsp");
                break;
            case 3:
                view = request.getRequestDispatcher("formularios/registro_area.jsp");
                break;
            default:
                view = request.getRequestDispatcher("formularios/registros.jsp");
        }
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("boton_registro");

        if (opcion.equals("Insertar Regional")) 
        {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            String nombre = request.getParameter("nombre");
            //String insertarR = "INSERT INTO regional VALUES (5, 'Antioquia')";

            //Inicio de código probado satisfactoriamente
            int resultado = 0;
            Connection conexion = null;
            PreparedStatement instruccion = null;
            try {
                Class.forName(ConexionBD.CONTROLADOR);

                conexion
                        = DriverManager.getConnection(ConexionBD.URL_BASEDATOS,
                                ConexionBD.NOMBREUSUARIO, ConexionBD.PASSWORD);

                instruccion = conexion.prepareStatement("INSERT INTO regional "
                        + "(codigo, nombre_departamento) "
                        + "VALUES (?, ?)");

                instruccion.setInt(1, codigo);
                instruccion.setString(2, nombre);
                resultado = instruccion.executeUpdate();

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
            // Fin de código probado satisfactoriamente
        } 
        else if (opcion.equals("Insertar Centro")) 
        {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            String nombre = request.getParameter("nombre");
            int codigoR = Integer.parseInt(request.getParameter("codigo_regional"));

            //Inicio de código probado satisfactoriamente
            int resultado = 0;
            Connection conexion = null;
            PreparedStatement instruccion = null;
            try {
                Class.forName(ConexionBD.CONTROLADOR);

                conexion
                        = DriverManager.getConnection(ConexionBD.URL_BASEDATOS,
                                ConexionBD.NOMBREUSUARIO, ConexionBD.PASSWORD);

                instruccion = conexion.prepareStatement("INSERT INTO centro "
                        + "VALUES(?, ?, ?)");

                instruccion.setInt(1, codigo);
                instruccion.setString(2, nombre);
                instruccion.setInt(3, codigoR);
                resultado = instruccion.executeUpdate();

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

        // Inicio de código por depurar y consultar
        /*
        try{
            Class.forName(ConexionBD.CONTROLADOR);
                
            JdbcRowSet rowSet= new JdbcRowSetImpl();
            rowSet.setUrl(ConexionBD.URL_BASEDATOS);
            rowSet.setUsername(ConexionBD.NOMBREUSUARIO);
            rowSet.setPassword(ConexionBD.PASSWORD);
            rowSet.setCommand("INSERT INTO regional VALUES (68, 'Antioquia')");
            rowSet.execute();
            RequestDispatcher view = request.getRequestDispatcher("registro_regional.jsp");
            view.forward(request, response);
            //System.out.print("Dato Insertado Correctamente");
        }
        catch(SQLException exceptionSql){
                
            exceptionSql.printStackTrace();
            System.exit(1);
        }
        catch(ClassNotFoundException noEncontroClase){
            noEncontroClase.printStackTrace();
            System.exit(1);
        }*/
        //Fin de código por reparar 
    }
}
