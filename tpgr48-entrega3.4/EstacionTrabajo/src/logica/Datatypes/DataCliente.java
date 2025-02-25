package logica.Datatypes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;

public class DataCliente extends DataUsuario {

	private String documento;
	private String apellido;
	private LocalDate nacimiento;
	private String tipoDocumento;
	private String nacionalidad;
	private List<DataReserva> reservas;
	private List<DataCheckIn> checkIns;

	public DataCliente(String nickname, String nombre, String email, String apellido, LocalDate nacimiento,
			String nacionalidad, String tipoDocumento, String documento, List<DataReserva> reservas, String password, String imagen,
			ArrayList<String> seguidos, ArrayList<String> seguidores) {
		super(nickname, nombre, email, password, imagen, seguidos, seguidores);
		this.documento = documento;
		this.apellido = apellido;
		this.nacimiento = nacimiento;
		this.tipoDocumento = tipoDocumento;
		this.nacionalidad = nacionalidad;
		this.checkIns = new ArrayList<DataCheckIn>();
		this.reservas = reservas;
	}

	public DataCliente(String nickname, String nombre, String email, String apellido, LocalDate nacimiento,
			String nacionalidad, String tipoDocumento, String documento, List<DataReserva> reservas, String password, String imagen, List<DataCheckIn> checkIns) {
		super(nickname, nombre, email, password, imagen, new ArrayList<>(), new ArrayList<>());
		this.documento = documento;
		this.apellido = apellido;
		this.nacimiento = nacimiento;
		this.tipoDocumento = tipoDocumento;
		this.nacionalidad = nacionalidad;
		this.checkIns = checkIns;
		this.reservas = reservas;
	}


	public String getDocumento() {
		return this.documento;
	}

	@Override
	public String getNombre() {
		return super.getNombre();
	}

	@Override
	public String getNickname() {
		return super.getNickname();
	}

	@Override
	public String getEmail() {
		return super.getEmail();
	}
	
	@Override
	public String getPassword() {
		return super.getPassword();
	}

	public String getApellido() {
		return this.apellido;
	}

	public LocalDate getNacimiento() {
		return this.nacimiento;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public String getTipoNacionalidad() {
		return this.nacionalidad;
	}
	
	public List<DataReserva> getReservas() {
        return reservas;
    }

	public List<DataCheckIn> getCheckIns() {
		return checkIns;
	}

	public void addCheckIn(DataCheckIn checkIn) {
		this.checkIns.add(checkIn);
	}
	
	@XmlElement
	public String getNacimientoFormatted() {
	    return nacimiento != null ? nacimiento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) : "Fecha no disponible";
	}

	
//	@Override
//	public String getImagen() {
//		return this.imagen;
//	}
	
//	public void setImagen(String imagen) {
//		this.imagen = imagen;
//	}

	@Override
	public String toString() {
		return getNickname() + " - " + getNombre() + " " + getApellido();
	}

}
