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

import servidor.DataPasajes;
import servidor.DataReserva;
import servidor.DataVuelo;
import servidor.PublicadorVuelosService;
import servidor.PublicadorVuelos;
import servidor.VueloNoExisteExce;
import servidor.VueloNoExisteExce_Exception;


@WebServlet("/reservas-fetch")
public class ReservasAsincServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private PublicadorVuelosService publicadorVuelosService;
    private PublicadorVuelos publicadorVuelos;

    public ReservasAsincServlet() {
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
    	
    	
        String vuelo = request.getParameter("opcion");
        
        ArrayList<DataReserva> reservas = new ArrayList<>();
		
		try {
			// Fetch routes based on airline
			if (vuelo != null && !vuelo.isEmpty()) {
	        	servidor.DataVuelo dataVuelo  = publicadorVuelos.verInfoVuelo(vuelo.trim());
	        	reservas = (ArrayList<DataReserva>) dataVuelo.getReservas();
			}
			
		} catch (VueloNoExisteExce_Exception e) {
			System.out.println("Ocurrio un error inesperado.");
		}
        // Configuración de respuesta
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        for (int i = 0; i < reservas.size(); i++) {
            DataReserva p = reservas.get(i);
            jsonBuilder.append("{")
                .append("\"NickC\":\"").append(p.getNickC()).append("\",")
                .append("\"NomV\":\"").append(p.getNomV()).append("\",")
                .append("\"tipoAsiento\":\"").append(p.getTipoAsiento()).append("\",")
                .append("\"FechaReserva\":\"").append(p.getFechaReservaFormatted()).append("\",")
                .append("\"CantPasajes\":").append(p.getCantPasajes()).append(",")
                .append("\"CantExtra\":").append(p.getCantExtra()).append(",")
                .append("\"CostoTotal\":").append(p.getCostoTotal()).append(",")
                .append("\"pasajeros\":[");

            // Procesar lista de pasajeros
            List<servidor.DataPasajes> lista = p.getPasajes();
            for (int j = 0; j < lista.size(); j++) {
                DataPasajes pasajero = lista.get(j);
                jsonBuilder.append("{")
                    .append("\"apellido\":\"").append(pasajero.getApellido()).append("\",")
                    .append("\"nombre\":\"").append(pasajero.getNombre()).append("\"")
                    .append("}");
                if (j < lista.size() - 1) {
                    jsonBuilder.append(",");
                }
            }

            jsonBuilder.append("]").append("}");
            if (i < reservas.size() - 1) {
                jsonBuilder.append(",");
            }
        }
        jsonBuilder.append("]");
        out.print(jsonBuilder.toString());
        out.flush();
    }
    
   
}