package logica.clases;

import java.util.ArrayList;

import logica.Datatypes.DataUsuario;


public abstract class Usuario {
	private String nickname;
	private String nombre;
	private String email;
	private String password;
	private String imagen;
	protected ArrayList<String> seguidos;
	protected ArrayList<String> seguidores;


	protected Usuario(String nickname, String nombre, String email, String password, String imagen, ArrayList<String> seguidos,
			ArrayList<String> seguidores) {

		this.nickname = nickname;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.imagen = imagen;
		this.seguidos = seguidos;
		this.seguidores = seguidores;

	}

	public String getNombre() {
        return nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getImagen() {
        return imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public abstract ArrayList<String> getSeguidos();

	public abstract ArrayList<String> getSeguidores();


}
