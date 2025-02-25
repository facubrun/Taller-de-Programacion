package logica.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.Datatypes.DataPasajes;


public class Cliente extends Usuario {
	private String documento;
    private String apellido;
    private LocalDate nacimiento;
    private String tipoDocumento;
    private String nacionalidad;
    private ArrayList<DataPasajes> pasajes;
    private ArrayList<Reserva> reservas;
    private ArrayList<CheckIn> checkIns;
    private ArrayList<Paquete> paquetes;

    public Cliente(String nickname, String nombre, String email, String apellido, LocalDate nacimiento,
    		String nacionalidad, String tipoDocumento, String documento, String password, ArrayList<DataPasajes> pasajes, String imagen,
            ArrayList<String> seguidos, ArrayList<String> seguidores) {
 super(nickname, nombre, email, password, imagen,seguidos, seguidores);

        this.documento = documento;
        this.apellido = apellido;
        this.nacimiento = nacimiento;
        this.tipoDocumento = tipoDocumento;
        this.nacionalidad = nacionalidad;
        this.pasajes = pasajes;
        this.reservas = new ArrayList<>();
        this.checkIns = new ArrayList<>();
        this.paquetes = new ArrayList<>();
//        this.password = password;
    }

    // Getters y Setters
    public String getDocumento() {
        return this.documento;
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

    public String getApellido() {
        return this.apellido;
    }

    public LocalDate getNacimiento() {
        return this.nacimiento;
    }

    public String getTipoDocumento() {
        return this.tipoDocumento;
    }

    public String getNacionalidad() {
        return this.nacionalidad;
    }

    public ArrayList<DataPasajes> getDataPasajes() {
        return this.pasajes;
    }

    public ArrayList<Paquete> getPaquetes() {
        return this.paquetes;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setDataPasajes(ArrayList<DataPasajes> pasajes) {
        this.pasajes = pasajes;
    }

    public void setCheckIns(ArrayList<CheckIn> checkIns) {
        this.checkIns = checkIns;
    }

    public void setPaquetes(ArrayList<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public List<Reserva> getReservas() {
        return this.reservas;
    }
    
    public ArrayList<CheckIn> getCheckIns() {
        return this.checkIns;
    }

    public void addReserva(Reserva reserva) {
        this.reservas.add(reserva);
    }
    
    public void agregarCheckIn(CheckIn check) {
        this.checkIns.add(check);
    }

    public void agregarPaquete(Paquete paq){
        this.paquetes.add(paq);
    }
//    public String getImagen() {
//    	return this.imagen;
//    }
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
