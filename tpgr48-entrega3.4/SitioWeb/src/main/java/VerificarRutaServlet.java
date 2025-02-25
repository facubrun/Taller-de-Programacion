

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.PublicadorRutasVuelo;
import servidor.PublicadorRutasVueloService;
import servidor.PublicadorUsuariosService;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Servlet implementation class VerificarRutaServlet
 */
@WebServlet("/verificarRuta")
public class VerificarRutaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private PublicadorRutasVueloService publicadorRutasVueloService;
    private PublicadorRutasVuelo publicadorRutasVuelo;

    public VerificarRutaServlet() {
        super();
        
        try {
        	publicadorRutasVueloService = new PublicadorRutasVueloService();
            publicadorRutasVuelo = publicadorRutasVueloService.getPublicadorRutasVueloPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreRuta = request.getParameter("nombre");
        boolean rutaExiste = false;

        try {
            List<servidor.DataRutaVuelo> rutas = publicadorRutasVuelo.listarRutasVuelo().getItem();
            for (servidor.DataRutaVuelo ruta : rutas) {
                if (ruta.getNombre().equalsIgnoreCase(nombreRuta)) { 
                    rutaExiste = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{\"existe\": " + rutaExiste + "}");
        out.flush();
    }

}
