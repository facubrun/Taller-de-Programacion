package logica.Datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.ArrayList;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataUsuario", propOrder = { "nickname", "nombre", "email", "password", "imagen", "seguidos", "seguidores" })
public abstract class DataUsuario {

    @XmlElement
    private String nickname;

    @XmlElement
    private String nombre;

    @XmlElement
    private String email;

    @XmlElement
    private String password;

    @XmlElement
    private String imagen;
    
    @XmlElement
    protected ArrayList<String> seguidos;
    @XmlElement
    protected ArrayList<String> seguidores;


    public DataUsuario() {
    }

    public DataUsuario(String nickname, String nombre, String email, String password, String imagen, ArrayList<String> seguidos, ArrayList<String> seguidores) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.imagen = imagen;
        this.seguidos = seguidos;
		this.seguidores = seguidores;

    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    
    protected ArrayList<String> getSeguidos() {
		return seguidos;
	}
	
	protected ArrayList<String> getSeguidores() {
		return seguidores;
	}


    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
