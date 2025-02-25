

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import servidor.*;


/**
 * Servlet implementation class ConsultaUsuarioServlet
 */
@WebServlet("/consulta-ruta-vuelo")
public class ConsultaRutaVueloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PublicadorRutasVueloService publicadorRutasVueloService;
	private PublicadorRutasVuelo publicadorRutasVuelo;
	
	private PublicadorUsuariosService publicadorUsuarioService;
	private PublicadorUsuarios publicadorUsuario;

    public ConsultaRutaVueloServlet() {
        super();
        
		try {
			publicadorRutasVueloService = new PublicadorRutasVueloService();
	        publicadorRutasVuelo = publicadorRutasVueloService.getPublicadorRutasVueloPort();
	        
	        publicadorUsuarioService = new PublicadorUsuariosService();
			publicadorUsuario = publicadorUsuarioService.getPublicadorUsuariosPort();
			
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        List<servidor.DataAerolinea> aerolineas = new ArrayList<>();
        
        try {
			aerolineas = publicadorUsuario.getAerolineas().getItem();
		} catch (UsuarioNoExisteExce_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        List<Categoria> categorias = publicadorRutasVuelo.listarCategorias().getItem();
	    ArrayList<String> nombresCategorias = new ArrayList<String>();
	    for (Categoria cat : categorias) {
	    	nombresCategorias.add(cat.getNombre());
	    }
	    request.setAttribute("nombresCategorias", nombresCategorias);
	    
        request.setAttribute("aerolineas", aerolineas);
        request.setAttribute("categorias", categorias);
        
        ArrayList<DataRutaVuelo> rutasConfirmadas = new ArrayList<>();
        try {
            List<servidor.DataRutaVuelo> rutasVuelo = publicadorRutasVuelo.listarRutasVuelo().getItem();
            for (DataRutaVuelo ruta : rutasVuelo) {
                if (ruta.getEstado() == servidor.EstadoRutaVuelo.Confirmada) {
                    rutasConfirmadas.add(ruta);
                }
            }
        } catch (RVNoExisteExce_Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        request.setAttribute("rutasConfirmadas", rutasConfirmadas);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/consulta-ruta-vuelo.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
