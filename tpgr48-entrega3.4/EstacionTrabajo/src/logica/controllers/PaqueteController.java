package logica.controllers;

import exceptions.PaqueteExisteExce;
import exceptions.RVNoExisteExce;
import exceptions.VueloNoExisteExce;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Handler;

import logica.Fabrica;
import logica.Datatypes.DataPaquete;
import logica.Datatypes.DataReserva;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataRutaVueloPaquete;
import logica.Datatypes.DataVuelo;
import logica.Datatypes.tipoAsiento;
import logica.Handlers.PaqueteHandler;
import logica.Handlers.UsuariosHandler;
import logica.Handlers.VuelosHandler;
import logica.Interfaces.IPaqueteController;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.Interfaces.IVuelosController;
import logica.clases.Paquete;
import logica.clases.Reserva;
import logica.clases.RutaVuelo;
import logica.clases.Vuelo;
import logica.clases.Cliente;

public class PaqueteController implements IPaqueteController {
	public void crearPaquete(String nombre, String descripcion, int validezDias, float descuento, float costo, LocalDate fechaAlta, ArrayList<DataRutaVueloPaquete> rutasPaquete)
			throws PaqueteExisteExce {
		PaqueteHandler handler = PaqueteHandler.getInstance();
		if (handler.existePaqueteNombre(nombre)) {
			throw new PaqueteExisteExce("El paquete ya existe");
		}

		Paquete paquete = new Paquete(nombre, descripcion, validezDias, descuento, costo, fechaAlta, rutasPaquete);
		handler.agregarPaquete(paquete);
	}

	@Override
	public ArrayList<DataPaquete> listarPaquetesDisponibles() {
		ArrayList<DataPaquete> paquetesDisp = new ArrayList<DataPaquete>();
		try {
		PaqueteHandler handler = PaqueteHandler.getInstance();
		paquetesDisp = handler.obtenerPaquetesDisponibles();
		} catch (Exception e) {}
		return paquetesDisp;
	}
	
	public void agregarRVaPaquete(String nomP, String rutaP, tipoAsiento asientoSelec, Integer cantRutas) throws RVNoExisteExce {
		PaqueteHandler handler = PaqueteHandler.getInstance();
		IRutaVueloController ctrlRV = Fabrica.getInstance().getIControladorRutaVuelo();
		DataRutaVuelo dataRV = ctrlRV.verInfoRutaVuelo(rutaP);
		Paquete paq = handler.obtenerPaquete(nomP);
		handler.agregarRVaPaquete(paq, dataRV, asientoSelec, cantRutas);
	}
	
	public DataPaquete verInfoPaquete(String nombrePaq) throws VueloNoExisteExce{
		PaqueteHandler paq = PaqueteHandler.getInstance();
		if (paq.existePaqueteNombre(nombrePaq)) {
			Paquete p = paq.obtenerPaquete(nombrePaq);

			return new DataPaquete(p.getNombre(), p.getDescripcion(), p.getValidezDias(), p.getDescuento(), p.getCosto(), p.getFechaAlta(), p.getRutasPaquete());
		} else
			throw new VueloNoExisteExce("El Paquete " + nombrePaq + " no existe");
	}

	public void comprarPaquete(String nickC, String nombrePaq, LocalDate fechaCompra, LocalDate fechaVencimiento) throws PaqueteExisteExce {
		PaqueteHandler paq = PaqueteHandler.getInstance();
		Cliente cliente = UsuariosHandler.getinstance().obtenerCliente(nickC);
		Paquete paquete = paq.obtenerPaquete(nombrePaq);
		paquete.addCompraCliente(cliente);
		ArrayList<Paquete> paquetesComprados = new ArrayList<>();
		paquetesComprados = cliente.getPaquetes();
		for(Paquete paqueteComprado: paquetesComprados) {
			if(nombrePaq == paqueteComprado.getNombre())
				throw new PaqueteExisteExce("El Paquete " + nombrePaq + " ya fue comprado por el cliente");
		}
		cliente.agregarPaquete(paquete);
	}
}
