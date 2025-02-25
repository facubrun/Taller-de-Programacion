import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servidor.DataVuelo;
import servidor.DataVueloArray;
import servidor.PublicadorVuelos;
import servidor.PublicadorVuelosService;
import servidor.RVNoExisteExce_Exception;


@WebServlet("/vuelos-fetch")
public class VuelosAsincServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    PublicadorVuelosService publicadorVuelosService;
    PublicadorVuelos publicadorVuelos;

    public VuelosAsincServlet() {
        super();

        
        
        try {
        	publicadorVuelosService = new PublicadorVuelosService();
            publicadorVuelos = publicadorVuelosService.getPublicadorVuelosPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
    }
	
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String ruta = request.getParameter("opcion");
        
		DataVueloArray vuelos_resp = new DataVueloArray();;
		
		try {
			// Fetch routes based on airline
			if (ruta != null && !ruta.isEmpty()) {
				vuelos_resp = publicadorVuelos.listarVuelosPorRutaVuelo(ruta.trim());
			}
			
		} catch (RVNoExisteExce_Exception e) {
			System.out.println("Ocurrio un error inesperado.");
		}
		
		List<DataVuelo> vuelos = vuelos_resp.getItem();

        // Configuración de respuesta
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < vuelos.size(); i++) {
            DataVuelo p = vuelos.get(i);
            jsonBuilder.append("{")
                .append("\"Nombre\":").append("\"").append(p.getNombre()).append("\"")
                .append("}");
            if (i < vuelos.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        
        out.print(jsonBuilder.toString());
        out.flush();
    }
}