package logica.Handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import exceptions.RVNoExisteExce;
import logica.Fabrica;
import logica.Datatypes.DataPaquete;
import logica.Datatypes.DataReserva;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataRutaVueloPaquete;
import logica.Datatypes.DataVuelo;
import logica.Datatypes.tipoAsiento;
import logica.Interfaces.IRutaVueloController;
import logica.clases.Paquete;
import logica.clases.RutaVuelo;
import logica.clases.Vuelo;
import logica.controllers.RutaVueloController;

public final class PaqueteHandler {
	private Map<String, Paquete> Paquetes;
	private static PaqueteHandler instancia = null;

	public PaqueteHandler() {
		Paquetes = new HashMap<String, Paquete>();
	}

	public static PaqueteHandler getInstance() {
		if (instancia == null)
			instancia = new PaqueteHandler();
		return instancia;
	}

	public Paquete obtenerPaquete(String nombre) {
		if (Paquetes.get(nombre) != null)
			return Paquetes.get(nombre);
		return null;

	}

	public ArrayList<DataPaquete> obtenerPaquetesDisponibles(){
		ArrayList<DataPaquete> dataPaquetesDisp = new ArrayList<DataPaquete>();
		for(Paquete p : Paquetes.values()) {
			DataPaquete dpaq = new DataPaquete(p.getNombre(),p.getDescripcion(),p.getValidezDias(),p.getDescuento(),p.getCosto(),p.getFechaAlta(),p.getRutasPaquete());
			dataPaquetesDisp.add(dpaq);
		}
		return dataPaquetesDisp;	
	}

	public void agregarPaquete(Paquete paquete) {
		if (!existePaqueteNombre(paquete.getNombre()))
			Paquetes.put(paquete.getNombre(), paquete);
	}

	public boolean existePaqueteNombre(String nombre) {
		for (Paquete p : Paquetes.values()) {
			if (p.getNombre().toLowerCase().equals(nombre.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	public void agregarRVaPaquete(Paquete paq,DataRutaVuelo dataRV, tipoAsiento asientoSelec, Integer cantRuta) throws RVNoExisteExce {
		DataRutaVueloPaquete dataRVaP = new DataRutaVueloPaquete(dataRV, cantRuta);
		boolean estaEnPaq = false;
		for(DataRutaVueloPaquete dataRVPaqAct : paq.getRutasPaquete()) {
			if(dataRVPaqAct.getDataRV().getNombre() == dataRVaP.getDataRV().getNombre())
				estaEnPaq = true;
				break;
		}
		if(!estaEnPaq) {
			float costo = 0;
			if(asientoSelec == tipoAsiento.Ejecutivo) {
				costo = dataRV.getCostoEjecutivo() * cantRuta * (paq.getDescuento()/100);
			}
			else 
				costo = dataRV.getCostoTurista() * cantRuta * (paq.getDescuento()/100);
		
			paq.setCosto(costo);
			paq.addRutaVuelo(dataRVaP, cantRuta);
		}
	}

}
