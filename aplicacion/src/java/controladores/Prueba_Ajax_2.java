
package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Persona;

@WebServlet(name = "Prueba_Ajax_2", urlPatterns = {"/Prueba_Ajax_2"})
public class Prueba_Ajax_2 extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ArrayList<Persona> personas = new ArrayList<>();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     * protected void processRequest(HttpServletRequest request,
     * HttpServletResponse response) throws ServletException, IOException {
     * response.setContentType("text/html;charset=UTF-8"); try (PrintWriter out
     * = response.getWriter()) { out.println("<!DOCTYPE html>");
     * out.println("<html>"); out.println("<head>"); out.println("<title>Servlet
     * Prueba_Ajax_2</title>"); out.println("</head>"); out.println("<body>");
     * out.println("<h1>Servlet Prueba_Ajax_2 at " + request.getContextPath() +
     * "</h1>"); out.println("</body>"); out.println("</html>"); } }
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher view = request.getRequestDispatcher("prueba_ajax_2.jsp");

        // Reenvia la solicitud o petición del Servlet al jsp
        view.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=iso-8859-1");
        PrintWriter out = response.getWriter();

        // Obtengo los datos de la peticion
        String nombre = request.getParameter("nombre"); // Lo que se envia desde el pos del JavaScript
        String apellido = request.getParameter("apellido"); // Lo que se envia desde el pos del JavaScript
        String edad = request.getParameter("edad"); // Lo que se envia desde el pos del JavaScript

        // Compruebo que los campos del formulario tienen datos para añadir a la tabla
        if (!nombre.equals("") && !apellido.equals("") && !edad.equals("")) {
            // Creo el objeto persona y lo añado al arrayList
            Persona persona = new Persona(nombre, apellido, edad);
            personas.add(persona);
        }

        out.println("<table style= cellspacing=\"1\" bgcolor=\"#0099cc\">");
        out.println("<tr>");
        out.println("<td style= rowspan=\"7\" align=\"center\" bgcolor=\"#f8f8f8\"> NOMBRE </td>");
        out.println("<td style= rowspan=\"7\" align=\"center\" bgcolor=\"#f8f8f8\">APELLIDO</td>");
        out.println("<td style= rowspan=\"7\" align=\"center\" bgcolor=\"#f8f8f8\">EDAD</td>");
        out.println("</tr>");
        for (int i = 0; i < personas.size(); i++) {
            Persona persona = personas.get(i);
            out.println("<tr>");
            out.println("<td style= rowspan=\"7\" align=\"center\" bgcolor=\"#f8f8f8\">" + persona.getNombre() + "</td>");
            out.println("<td style= rowspan=\"7\" align=\"center\" bgcolor=\"#f8f8f8\">" + persona.getApellido() + "</td>");
            out.println("<td style= rowspan=\"7\" align=\"center\" bgcolor=\"#f8f8f8\">" + persona.getEdad() + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
    }
}
