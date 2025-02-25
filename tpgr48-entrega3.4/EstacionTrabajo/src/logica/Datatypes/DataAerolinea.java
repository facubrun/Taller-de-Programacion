package logica.Datatypes;

import java.util.ArrayList;

public class DataAerolinea extends DataUsuario {

	private String descripcion;
	private String web;
	private ArrayList<DataRutaVuelo> rutasVuelo;

	public DataAerolinea(String nickname, String nombre, String email, String descripcion, String web,
			ArrayList<DataRutaVuelo> rutasVuelo, String password, String imagen, ArrayList<String> seguidos,
			ArrayList<String> seguidores) {
		super(nickname, nombre, email, password, imagen, seguidos, seguidores);

		this.descripcion = descripcion;
		this.web = web;
		this.rutasVuelo = rutasVuelo;
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

	public String getDescripcion() {
		return this.descripcion;
	}

	public String getWeb() {
		return this.web;
	}

	public ArrayList<DataRutaVuelo> getRutasVuelo() {
		return this.rutasVuelo;
	}

//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}
//
//	public void setNickname(String nickname) {
//		this.nickname = nickname;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public void setRutasVuelo(ArrayList<DataRutaVuelo> rutasVuelo) {
		this.rutasVuelo = rutasVuelo;
	}
	
//	@Override
//	public String getImagen() {
//		return this.imagen;
//	}
//	
//	public void setImagen(String imagen) {
//		this.imagen = imagen;
//	}

	@Override
	public String toString() {
		return getNickname() + " - " + getNombre() + " " + this.descripcion;
	}

}
