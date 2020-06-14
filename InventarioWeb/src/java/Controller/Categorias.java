
package Controller;


import DAO.CategoriaDAO;
import DAO.CategoriaDAOImplementar;
import Model.Categoria;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ruben
 */
public class Categorias extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           /* out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Categorias</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Categorias at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
    }

   //Agregar metodo listaCategorias
    protected void listaCategrias(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        
        //Instancia a categoria DAO
        CategoriaDAO categoria = new CategoriaDAOImplementar();
        //crear instacia de session; true para iniciar session
        HttpSession sesion =request.getSession(true);
        sesion.setAttribute("lista", categoria.Listar());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Vistas-Categorias/listarCategorias.jsp");
        dispatcher.forward(request, response);
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String parametro = request.getParameter("opcion");
        if(parametro.equals("crear")){
            String pagina = "/Vistas-Categorias/crearCategoria.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
            dispatcher.forward(request, response);
        }
        
        else {
            this.listaCategrias(request, response);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
        Categoria categoria = new Categoria ();
        //se efectua el casting o conversion de datos porque lo ingresado en el formulario es texto
        categoria.setId_categoria(Integer.parseInt(request.getParameter("id_categoria")));
        categoria.setNom_categoria(request.getParameter("txtNomCategoria"));
        categoria.setEstado_categoria(Integer.parseInt(request.getParameter("txtEstadoCategoria")));
        
        
        CategoriaDAO guardaCategoria = new CategoriaDAOImplementar();
        guardaCategoria.guardarCat(categoria);
        this.listaCategrias(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
