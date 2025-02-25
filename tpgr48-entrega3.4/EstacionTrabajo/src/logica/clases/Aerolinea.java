package logica.clases;

import java.util.ArrayList;
import logica.Datatypes.DataUsuario;

public class Aerolinea extends Usuario {
	private String descripcion;
	private String web;
	private ArrayList<RutaVuelo> rutasVuelo;

	public Aerolinea(String nickname, String nombre, String email, String descripcion, String web, String password, String imagen,
			ArrayList<String> seguidos, ArrayList<String> seguidores) {
		super(nickname, nombre, email, password, imagen, seguidos, seguidores);

		this.descripcion = descripcion;
		this.web = web;
		this.rutasVuelo = new ArrayList<RutaVuelo>();
	}

	public String getNombre() {
        return super.getNombre();
    }

    public String getNickname() {
        return super.getNickname();
    }

    public String getEmail() {
        return super.getEmail();
    }
	
	public String getPassword() {
		return super.getPassword();
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public String getWeb() {
		return this.web;
	}

	public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    public void setNickname(String nickname) {
        super.setNickname(nickname);
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public ArrayList<RutaVuelo> getRutasVuelo() {
		return rutasVuelo;
	}

	public void addRutaVuelo(RutaVuelo ruta) {
		this.rutasVuelo.add(ruta);
	}
	
//	public String getImagen() {
//		return this.imagen;
//	}
//	
	public void setImagen(String imagen) {
        super.setImagen(imagen);
    }
	
	public ArrayList<String> getSeguidores() {
    	return this.seguidores;
    }
    
    public ArrayList<String> getSeguidos() {
    	return this.seguidos;
    }
    
    public void addSeguido(String nickSeguido) {
    	this.seguidos.add(nickSeguido);
    }
    
    public void addSeguidor(String nickSeguidor) {
    	this.seguidores.add(nickSeguidor);
    }


}
