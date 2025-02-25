package logica.Datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import logica.Handlers.VuelosHandler;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataReserva", propOrder = { "nickC", "nomV", "fechaReserva", "tipoAsiento", "cantPasajes", "cantExtra", "costoTotal", "pasajes", "checkIn", "fechaReservaFormatted", "rutaVueloV", "ciudadOrigenV", "ciudadDestinoV", "horaV", "fechaV"})
public class DataReserva {

	@XmlElement
	private String nickC;
	@XmlElement
	private String nomV;
	@XmlElement
	private LocalDate fechaReserva;
	@XmlElement
	private tipoAsiento tipoAsiento;
	@XmlElement
	private Integer cantPasajes;
	@XmlElement
	private Integer cantExtra;
	@XmlElement
	private float costoTotal;
	@XmlElement
	private ArrayList<DataPasajes> pasajes;
	@XmlElement
	private boolean checkIn;
	@XmlElement
	private String rutaVueloV;
	@XmlElement
	private String ciudadOrigenV;
	@XmlElement
	private String ciudadDestinoV;
	@XmlElement
	private String horaV;
	@XmlElement
	private String fechaV;

	public DataReserva() {
	}

	public DataReserva(String nickC, String nomV, LocalDate fechaReserva, tipoAsiento tipoAsiento, Integer cantPasajes,
			Integer cantExtra, float costoTotal) {
		this.nickC = nickC;
		this.nomV = nomV;
		this.fechaReserva = fechaReserva;
		this.tipoAsiento = tipoAsiento;
		this.cantPasajes = cantPasajes;
		this.cantExtra = cantExtra;
		this.costoTotal = costoTotal;
		this.checkIn = false;

		VuelosHandler vueloH = VuelosHandler.getinstance();

		this.rutaVueloV = vueloH.obtenerVuelo(nomV).getRutaVuelo().getNombre();
		this.ciudadOrigenV = vueloH.obtenerVuelo(nomV).getRutaVuelo().getCiudadOrigen();
		this.ciudadDestinoV = vueloH.obtenerVuelo(nomV).getRutaVuelo().getCiudadDestino();
		this.horaV = vueloH.obtenerVuelo(nomV).getRutaVuelo().getHora().toString();
		this.fechaV = vueloH.obtenerVuelo(nomV).getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public DataReserva(String nickC, String nomV, LocalDate fechaReserva, tipoAsiento tipoAsiento, Integer cantPasajes,
			Integer cantExtra, float costoTotal, ArrayList<DataPasajes> pasajes) {
		this.nickC = nickC;
		this.nomV = nomV;
		this.fechaReserva = fechaReserva;
		this.tipoAsiento = tipoAsiento;
		this.cantPasajes = cantPasajes;
		this.cantExtra = cantExtra;
		this.costoTotal = costoTotal;
		this.pasajes = pasajes;
		this.checkIn = false;

		VuelosHandler vueloH = VuelosHandler.getinstance();

		this.rutaVueloV = vueloH.obtenerVuelo(nomV).getRutaVuelo().getNombre();
		this.ciudadOrigenV = vueloH.obtenerVuelo(nomV).getRutaVuelo().getCiudadOrigen();
		this.ciudadDestinoV = vueloH.obtenerVuelo(nomV).getRutaVuelo().getCiudadDestino();
		this.horaV = vueloH.obtenerVuelo(nomV).getRutaVuelo().getHora().toString();
		this.fechaV = vueloH.obtenerVuelo(nomV).getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public DataReserva(String nickC, String nomV, LocalDate fechaReserva, tipoAsiento tipoAsiento, Integer cantPasajes,
			Integer cantExtra, float costoTotal, ArrayList<DataPasajes> pasajes, boolean checkIn) {
		this.nickC = nickC;
		this.nomV = nomV;
		this.fechaReserva = fechaReserva;
		this.tipoAsiento = tipoAsiento;
		this.cantPasajes = cantPasajes;
		this.cantExtra = cantExtra;
		this.costoTotal = costoTotal;
		this.pasajes = pasajes;
		this.checkIn = checkIn;

		VuelosHandler vueloH = VuelosHandler.getinstance();

		this.rutaVueloV = vueloH.obtenerVuelo(nomV).getRutaVuelo().getNombre();
		this.ciudadOrigenV = vueloH.obtenerVuelo(nomV).getRutaVuelo().getCiudadOrigen();
		this.ciudadDestinoV = vueloH.obtenerVuelo(nomV).getRutaVuelo().getCiudadDestino();
		this.horaV = vueloH.obtenerVuelo(nomV).getRutaVuelo().getHora().toString();
		this.fechaV = vueloH.obtenerVuelo(nomV).getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	

	public String getnickC() {
		return this.nickC;
	}

	public String getNomV() {
		return this.nomV;
	}

	public LocalDate getFechaReserva() {
		return this.fechaReserva;
	}

	@XmlElement
	public String getFechaReservaFormatted() {
		return fechaReserva != null ? fechaReserva.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Fecha no disponible";
	}

	public tipoAsiento gettipoAsiento() {
		return this.tipoAsiento;
	}

	public Integer getCantPasajes() {
		return this.cantPasajes;
	}

	public Integer getCantExtra() {
		return this.cantExtra;
	}

	public float getCostoTotal() {
		return this.costoTotal;
	}

	public ArrayList<DataPasajes> getPasajes() {
		return this.pasajes;
	}

	public boolean getCheckIn() {
		return this.checkIn;
	}

	public String getRutaVueloV() {
		return this.rutaVueloV;
	}

	public String getCiudadOrigenV() {
		return this.ciudadOrigenV;
	}

	public String getCiudadDestinoV() {
		return this.ciudadDestinoV;
	}

	public String getHoraV() {
		return this.horaV;
	}

	public String getFechaV() {
		return this.fechaV;
	}

	public void setnickC(String nickC) {
		this.nickC = nickC;
	}

	public void setNomV(String nomV) {
		this.nomV = nomV;
	}

	public void setFechaReserva(LocalDate fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public void settipoAsiento(tipoAsiento tipoAsiento) {
		this.tipoAsiento = tipoAsiento;
	}

	public void setCantPasajes(Integer cantPasajes) {
		this.cantPasajes = cantPasajes;
	}

	public void setCantExtra(Integer cantExtra) {
		this.cantExtra = cantExtra;
	}

	public void setCostoTotal(float costoTotal) {
		this.costoTotal = costoTotal;
	}

	public void setPasajes(ArrayList<DataPasajes> pasajes) {
		this.pasajes = pasajes;
	}

	public void setCheckIn() {
		this.checkIn = true;
	}

	@Override
	public String toString() {
		return "La reserva es de " + this.nickC + " al vuelo " + this.nomV + ", con asientos tipo " + this.tipoAsiento
				+ ", con una cantidad " + this.cantPasajes + " de pasajes y " + this.cantExtra
				+ "unidades de equipaje extra";
	}

}
