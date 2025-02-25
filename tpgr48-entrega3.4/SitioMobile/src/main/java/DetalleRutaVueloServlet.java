import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import logica.Fabrica;
//import logica.Datatypes.DataRutaVuelo;
import jakarta.servlet.http.HttpSession;
import servidor.*;

@WebServlet(description = "Detalle de una Ruta de Vuelo", urlPatterns = { "/detalle-ruta-vuelo" })
public class DetalleRutaVueloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private PublicadorRutasVueloService publicadorRutasVueloService;
    private PublicadorRutasVuelo publicadorRutasVuelo;

    public DetalleRutaVueloServlet() {
        super();
        
        
        try {
        	publicadorRutasVueloService = new PublicadorRutasVueloService();
            publicadorRutasVuelo = publicadorRutasVueloService.getPublicadorRutasVueloPort();
			
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nicknameUsuarioSesion = (String) session.getAttribute("usuario");
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");

        String ruta = request.getParameter("ruta");
        servidor.DataRutaVuelo infoRuta = null;
        boolean puedeFinalizar = false;

        try {
            infoRuta = publicadorRutasVuelo.verInfoRutaVuelo(ruta);

            if ("aerolinea".equals(tipoUsuario) && nicknameUsuarioSesion.equals(infoRuta.getAerolinea()) &&
                infoRuta.getEstado() == EstadoRutaVuelo.Confirmada) {
                puedeFinalizar = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("ruta", infoRuta);
        request.setAttribute("puedeFinalizar", puedeFinalizar);
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/detalle-ruta-vuelo.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nicknameUsuarioSesion = (String) session.getAttribute("usuario");
        String tipoUsuario = (String) session.getAttribute("tipoUsuario");

        String ruta = request.getParameter("ruta-finalizar");
        servidor.DataRutaVuelo infoRuta = null;

        try {
            infoRuta = publicadorRutasVuelo.verInfoRutaVuelo(ruta);

            if ("aerolinea".equals(tipoUsuario) && nicknameUsuarioSesion.equals(infoRuta.getAerolinea()) &&
                infoRuta.getEstado() == EstadoRutaVuelo.Confirmada) {
                
                publicadorRutasVuelo.finalizarRutaVuelo(ruta);
                request.setAttribute("mensaje", "La ruta de vuelo ha sido finalizada exitosamente.");
                
                infoRuta = publicadorRutasVuelo.verInfoRutaVuelo(ruta);
            } else {
                request.setAttribute("error", "No tiene permiso para finalizar esta ruta de vuelo.");
            }

            request.setAttribute("ruta", infoRuta);
            request.setAttribute("puedeFinalizar", false);
            
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/detalle-ruta-vuelo.jsp");
            dispatcher.forward(request, response);

        } catch (RVNoExisteExce_Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "La ruta de vuelo especificada no existe.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error al finalizar la ruta de vuelo.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
}