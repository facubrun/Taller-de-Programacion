import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import servidor.DataRutaVueloArray;
import servidor.PublicadorRutasVuelo;
import servidor.PublicadorRutasVueloService;
import servidor.PublicadorUsuariosService;
import servidor.PublicadorVuelosService;
import servidor.UsuarioNoExisteExce_Exception;
import servidor.DataRutaVuelo;

@WebServlet("/rutas-fetch")
public class RutaAsinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PublicadorRutasVueloService publicadorRutasVueloService;
    private PublicadorRutasVuelo publicadorRutasVuelo;

    public RutaAsinServlet() {
        super();
        
        
        try {
        	publicadorRutasVueloService = new PublicadorRutasVueloService();
            publicadorRutasVuelo = publicadorRutasVueloService.getPublicadorRutasVueloPort();
			
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }
	
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

    	String aerolinea = request.getParameter("opcion");
    	DataRutaVueloArray vuelos = new DataRutaVueloArray();
		try {
			// Fetch routes based on airline
			if (aerolinea != null && !aerolinea.isEmpty()) {
				vuelos = publicadorRutasVuelo.listarRutasVueloPorAerolinea(aerolinea.trim());				
			}else {
				System.out.println("Error");
			}
			
		} catch (UsuarioNoExisteExce_Exception e) {
			System.out.println("Ocurrio un error inesperado.");
		}
		List<DataRutaVuelo> rutas = vuelos.getItem();
        	

        // Configuración de respuesta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < rutas.size(); i++) {
            DataRutaVuelo p = rutas.get(i);
            jsonBuilder.append("{")
                .append("\"Nombre\":").append("\"").append(p.getNombre()).append("\"")
                .append("}");
            if (i < rutas.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        
        out.print(jsonBuilder.toString());
        out.flush();
    }
}