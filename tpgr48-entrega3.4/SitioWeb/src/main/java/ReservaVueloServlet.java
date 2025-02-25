import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.java.dev.jaxb.array.StringArray;
import servidor.DataAerolinea;
import servidor.DataCliente;
import servidor.DataUsuario;
import servidor.PublicadorUsuarios;
import servidor.PublicadorUsuariosService;
import servidor.UsuarioNoExisteExce;
import servidor.UsuarioNoExisteExce_Exception;
import servidor.DataVuelo;
import servidor.TipoAsiento;
import servidor.PublicadorVuelosService;
import servidor.PublicadorVuelos;

@WebServlet(description = "Reserva de Vuelo", urlPatterns = { "/reserva-vuelo" })

public class ReservaVueloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PublicadorUsuariosService publicadorUsuariosService;
	PublicadorUsuarios publicadorUsuarios;

	PublicadorVuelosService publicadorVuelosService;
	PublicadorVuelos publicadorVuelos;

	public ReservaVueloServlet() {
		super();

		

		try {
			publicadorUsuariosService = new PublicadorUsuariosService();
			publicadorUsuarios = publicadorUsuariosService.getPublicadorUsuariosPort();

			publicadorVuelosService = new PublicadorVuelosService();
			publicadorVuelos = publicadorVuelosService.getPublicadorVuelosPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al inicializar el servicio PublicadorVuelos", e);
        }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String usuario = (String) session.getAttribute("usuario");
		String tipoUsuario = (String) session.getAttribute("tipoUsuario");
		if (usuario == null || !("cliente".equals(tipoUsuario))) {
			request.setAttribute("error", "Usuario no logueado");
			ServletContext context = getServletContext();
			RequestDispatcher dispatcher = context.getRequestDispatcher("/reserva-vuelo.jsp");
			dispatcher.forward(request, response);
			return;
		}

		List<DataAerolinea> aerolineas;
		try {
			aerolineas = publicadorUsuarios.getAerolineas().getItem();
			request.setAttribute("aerolineas", aerolineas);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}

		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/reserva-vuelo.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		String nickUsuario = (String) session.getAttribute("usuario");

		String vuelo = (String) request.getParameter("vuelo");
		String aerolinea = (String) request.getParameter("aerolinea");
		String cantPasajesStr = (String) request.getParameter("cantidad-pasajes");
		String tipoAsientoSeleccionado = (String) request.getParameter("tipo-asiento");
		String cantEquipaje = (String) request.getParameter("cantidad-equipaje");
		String listaPasajeros = (String) request.getParameter("nombres-pasajeros");

		ArrayList<String> pasajeros = new ArrayList<String>();
		if (listaPasajeros != null && !listaPasajeros.equals("[]")) {
			listaPasajeros = listaPasajeros.substring(2, listaPasajeros.length() - 2);
			pasajeros = new ArrayList<>(List.of(listaPasajeros.split(",")));
			for (int i = 0; i < pasajeros.size(); i++) {
				pasajeros.set(i, pasajeros.get(i).replace("\"", ""));
			}
		}
		
		StringArray pasajeros_parced = new StringArray();
		pasajeros_parced.setItem(pasajeros);


		DataVuelo infoVuelo;
		try {
			if (cantPasajesStr == null) {
				infoVuelo = publicadorVuelos.verInfoVuelo(vuelo);
				request.setAttribute("infoVuelo", infoVuelo);
			} else {
				TipoAsiento tipoAsientoEnum = (tipoAsientoSeleccionado.equalsIgnoreCase("turista"))
						? servidor.TipoAsiento.TURISTA
						: servidor.TipoAsiento.EJECUTIVO;
				int cantPasajes = Integer.parseInt(cantPasajesStr) + 1;
				publicadorVuelos.reservarVuelo(vuelo, nickUsuario, tipoAsientoEnum, cantPasajes,
						Integer.parseInt(cantEquipaje), LocalDate.now().toString(), pasajeros_parced);
				request.setAttribute("infoVuelo", null);
				request.setAttribute("success", "La reserva ha sido ingresada correctamente");
			}
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}

		List<DataAerolinea> aerolineas = null;
		try {
			aerolineas = publicadorUsuarios.getAerolineas().getItem();
		} catch (UsuarioNoExisteExce_Exception e) {
			request.setAttribute("error", e.getMessage());
		}

		request.setAttribute("aerolineas", aerolineas);
		request.setAttribute("aerolinea", aerolinea);
		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/reserva-vuelo.jsp");
		dispatcher.forward(request, response);
	}
}
