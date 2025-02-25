package logica;

import logica.Interfaces.IPaqueteController;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.Interfaces.IVuelosController;
import logica.controllers.PaqueteController;
import logica.controllers.RutaVueloController;
import logica.controllers.UsuarioController;
import logica.controllers.VuelosController;

public final class Fabrica {

	private static Fabrica instancia;

	private Fabrica() {
	}

	public static Fabrica getInstance() {
		if (instancia == null) {
			instancia = new Fabrica();
		}
		return instancia;
	}

	public IUsuariosController getIControladorUsuario() {
		return new UsuarioController();
	}

	public IVuelosController getIControladorVuelos() {
		return new VuelosController();
	}

	public IRutaVueloController getIControladorRutaVuelo() {
		return new RutaVueloController();
	}

	public IPaqueteController getIControladorPaquete() {
		return new PaqueteController();
	}
}
