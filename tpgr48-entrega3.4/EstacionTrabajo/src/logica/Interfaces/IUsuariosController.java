package logica.Interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


import exceptions.ClientePasajeroRepetidoExce;
import exceptions.EmailRepetidoExce;
import exceptions.NicknameRepetidoExce;
import exceptions.UsuarioNoExisteExce;
import logica.Datatypes.DataAerolinea;
import logica.Datatypes.DataCheckIn;
import logica.Datatypes.DataCliente;
import logica.Datatypes.DataReserva;
import logica.clases.Reserva;

public interface IUsuariosController {

	public abstract void crearCliente(String nickname, String nombre, String email, String apellido,
			LocalDate nacimiento, String nacionalidad, String tipoDocumento, String Documento, String password, String imagen,
			ArrayList<String> seguidos, ArrayList<String> seguidores)
			throws EmailRepetidoExce, NicknameRepetidoExce;

	public abstract void crearAerolinea(String nickname, String nombre, String email, String descripcion, String web, String password, String imagen,
			ArrayList<String> seuidos, ArrayList<String> seguidores)
			throws EmailRepetidoExce, NicknameRepetidoExce;

	public abstract DataCliente verInfoCliente(String nickname) throws UsuarioNoExisteExce;

	public abstract DataAerolinea verInfoAerolinea(String nickname) throws UsuarioNoExisteExce;

	public abstract void modificarCliente(String nickname, String nombre, String email, String apellido,
			LocalDate nacimiento, String nacionalidad, String tipoDocumento, String Documento, String password, String imagen)
			throws UsuarioNoExisteExce;

	public abstract void modificarAerolinea(String nickname, String nombre, String email, String descripcion,
			String web, String password, String imagen) throws UsuarioNoExisteExce;

	public abstract DataCliente[] getClientes() throws UsuarioNoExisteExce;

	public abstract DataAerolinea[] getAerolineas() throws UsuarioNoExisteExce;

	public List<DataReserva> getReservasSinCheckIn(String nickCliente) throws UsuarioNoExisteExce;
	
	public void realizarCheckIn(String nickC, String nickV) throws ClientePasajeroRepetidoExce;
	
	public DataCheckIn[] getCheckIns(String nickC) throws UsuarioNoExisteExce;
	
	public abstract void seguir(String nickSeguidor, String nickSeguido) throws UsuarioNoExisteExce;
	
	public abstract void dejarSeguir(String nickSeguidor, String nickSeguido) throws UsuarioNoExisteExce;


}
