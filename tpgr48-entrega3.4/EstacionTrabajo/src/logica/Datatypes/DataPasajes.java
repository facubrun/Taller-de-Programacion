package logica.Datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataPasajes", propOrder = { "nombre", "apellido", "asiento" })
public class DataPasajes {

	@XmlElement
	private String nombre;
	@XmlElement
	private String apellido;
	@XmlElement
	private Integer asiento;

	public DataPasajes(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.asiento = null;
	}

	public DataPasajes(String nombre, String apellido, Integer asiento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.asiento = asiento;
	}


	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return this.apellido;
	}
	
	public Integer getAsiento() {
		return this.asiento;
	}

	public void setAsiento(Integer asiento) {
		this.asiento = asiento;
	}
	@Override
	public String toString() {
		return this.nombre + " " + this.apellido + ". Asiento asignado: " + this.asiento.toString();
	}

}
