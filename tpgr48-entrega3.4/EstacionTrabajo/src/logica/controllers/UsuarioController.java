package logica.controllers;

import exceptions.ClientePasajeroRepetidoExce;
import exceptions.EmailRepetidoExce;
import exceptions.NicknameRepetidoExce;
import exceptions.UsuarioNoExisteExce;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import logica.Datatypes.DataAerolinea;
import logica.Datatypes.DataCheckIn;
import logica.Datatypes.DataCliente;
import logica.Datatypes.DataPasajes;
import logica.Datatypes.DataReserva;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataVuelo;
import logica.Datatypes.tipoAsiento;
import logica.Handlers.UsuariosHandler;
import logica.Handlers.VuelosHandler;
import logica.Interfaces.IUsuariosController;
import logica.clases.Aerolinea;
import logica.clases.Categoria;
import logica.clases.CheckIn;
import logica.clases.Cliente;
import logica.clases.Pasaje;
import logica.clases.Reserva;
import logica.clases.RutaVuelo;
import logica.clases.Vuelo;

public class UsuarioController implements IUsuariosController {

	public void crearCliente(String nickname, String nombre, String email, String apellido, LocalDate nacimiento,
			String nacionalidad, String tipoDocumento, String documento, String password, String imagen, ArrayList<String> seguidos,
			ArrayList<String> seguidores)
			throws EmailRepetidoExce, NicknameRepetidoExce {

		UsuariosHandler user = UsuariosHandler.getinstance();
		if (user.existeAerolineaNick(nickname) || user.existeClienteNick(nickname)) {
			throw new NicknameRepetidoExce("El nickname " + nickname + " ya esta en uso");
		} else if (user.existeAerolineaEmail(email) || user.existeClienteEmail(email)) {
			throw new EmailRepetidoExce("El email " + email + " ya esta en uso");
		} else {
			Cliente cliente = new Cliente(nickname, nombre, email, apellido, nacimiento, nacionalidad, tipoDocumento,
					documento, password, null, imagen, seguidos, seguidores);
			user.addCliente(cliente);
		}

	}

	public void crearAerolinea(String nickname, String nombre, String email, String descripcion, String web, String password, String imagen,
			ArrayList<String> seguidos, ArrayList<String> seguidores)
			throws EmailRepetidoExce, NicknameRepetidoExce {

		UsuariosHandler user = UsuariosHandler.getinstance();
		if (user.existeAerolineaNick(nickname) || user.existeClienteNick(nickname)) {
			throw new NicknameRepetidoExce("El nickname " + nickname + " ya esta en uso");
		} else if (user.existeAerolineaEmail(email) || user.existeClienteEmail(email)) {
			throw new EmailRepetidoExce("El email " + email + " ya esta en uso");
		} else {
			Aerolinea aerolinea = new Aerolinea(nickname, nombre, email, descripcion, web, password, imagen, seguidos, seguidores);
			user.addAerolinea(aerolinea);
		}

	}

	public DataCliente verInfoCliente(String nickname) throws UsuarioNoExisteExce {
		UsuariosHandler user = UsuariosHandler.getinstance();
		Cliente cliente = user.obtenerCliente(nickname);
		if (cliente != null) {
			ArrayList<DataReserva> reservasCliente = new ArrayList<>();
			for (Reserva reserva : cliente.getReservas()) {
				ArrayList<DataPasajes> pasajes = new ArrayList<>();
				for (Pasaje p : reserva.getPasajes()) {
					DataPasajes dp = new DataPasajes(p.getNombre(), p.getApellido(), p.getAsientoAsig());
					pasajes.add(dp);
				}

	            DataReserva dataReserva = new DataReserva(
	                reserva.getCliente().getNickname(),
	                reserva.getVuelo().getNombre(),
	                reserva.getFechaReserva(),
	                reserva.getAsientoSeleccionado(),
	                reserva.getCantidadPasajes(),
	                reserva.getCantidadExtraEquipaje(),
	                reserva.getCostoTotal(),
					pasajes,
					reserva.getCheckIn()
	            );
	            reservasCliente.add(dataReserva);
	        }
			
			return new DataCliente(cliente.getNickname(), cliente.getNombre(), cliente.getEmail(), 
                    cliente.getApellido(), cliente.getNacimiento(), cliente.getNacionalidad(), 
                    cliente.getTipoDocumento(), cliente.getDocumento(), reservasCliente, cliente.getPassword(), cliente.getImagen(),
                    cliente.getSeguidos(), cliente.getSeguidores());
		} else {
			throw new UsuarioNoExisteExce("El cliente " + nickname + " no existe");
		}
	}

	public DataAerolinea verInfoAerolinea(String nickname) throws UsuarioNoExisteExce {
		UsuariosHandler user = UsuariosHandler.getinstance();
		Aerolinea aerolinea = user.obtenerAerolinea(nickname);
		ArrayList<DataRutaVuelo> datosRutasvuelo = new ArrayList<DataRutaVuelo>();
		DataAerolinea datosAerolinea = null;

		if (aerolinea != null) {
			ArrayList<RutaVuelo> rutasVuelo = aerolinea.getRutasVuelo();

			for (RutaVuelo rv : rutasVuelo) {
				ArrayList<String> listaNombresCategorias = new ArrayList<>();
				for (Categoria cat : rv.getCategorias()) {
					listaNombresCategorias.add(cat.getNombre());
				}
				ArrayList<DataVuelo> listaDataVuelo = new ArrayList<>();
				for (Vuelo vuelo : rv.getVuelos()) {

					ArrayList<DataReserva> listaDataReserva = new ArrayList<>();
					for (Reserva res : vuelo.getReservas()) {
						DataReserva dtReserva = new DataReserva(res.getCliente().getNickname(), vuelo.getNombre(),
								res.getFechaReserva(), res.getAsientoSeleccionado(), res.getCantidadPasajes(),
								res.getCantidadExtraEquipaje(), res.getCostoTotal());
						listaDataReserva.add(dtReserva);
					}

					DataVuelo dtvuelo = new DataVuelo(vuelo.getNombre(), vuelo.getFecha(), vuelo.getDuracion(),
							vuelo.getMaxTurista(), vuelo.getMaxEjecutivo(), vuelo.getFechaAlta(), listaDataReserva);
					listaDataVuelo.add(dtvuelo);
					listaDataReserva.clear();
				}
				DataRutaVuelo drv = new DataRutaVuelo(rv.getNombre(), rv.getDescripcion(), rv.getHora(),
						rv.getFechaAlta(), rv.getCiudadOrigen(), rv.getCiudadDestino(), rv.getCostoEjecutivo(),
						rv.getCostoTurista(), rv.getCostoExtra(), listaNombresCategorias, listaDataVuelo,
						rv.getAerolinea().getNombre(), rv.getImagen(), rv.getDescCorta(), rv.getEstado(), rv.getVideo());
				datosRutasvuelo.add(drv);
			}

			datosAerolinea = new DataAerolinea(nickname, aerolinea.getNombre(), aerolinea.getEmail(),
					aerolinea.getDescripcion(), aerolinea.getWeb(), datosRutasvuelo, aerolinea.getPassword(), aerolinea.getImagen(),
					aerolinea.getSeguidos(), aerolinea.getSeguidores());
		} else {
			throw new UsuarioNoExisteExce("La aerolinea " + nickname + " no existe");
		}

		return datosAerolinea;
	}

	public void modificarCliente(String nickname, String nombre, String email, String apellido, LocalDate nacimiento,
			String nacionalidad, String tipoDocumento, String Documento, String password, String imagen) throws UsuarioNoExisteExce {

		UsuariosHandler user = UsuariosHandler.getinstance();
		Cliente cliente = user.obtenerCliente(nickname);
		if (cliente == null) {
			throw new UsuarioNoExisteExce("El cliente " + nickname + " no existe");
		} else {
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setNacimiento(nacimiento);
			cliente.setNickname(nickname);
			cliente.setEmail(email);
			cliente.setNacionalidad(nacionalidad);
			cliente.setPassword(password);
			cliente.setImagen(imagen);
		}

	}

	public void modificarAerolinea(String nickname, String nombre, String email, String descripcion, String web, String password, String imagen)
			throws UsuarioNoExisteExce {
		UsuariosHandler user = UsuariosHandler.getinstance();
		Aerolinea aerolinea = user.obtenerAerolinea(nickname);
		if (aerolinea == null) {
			throw new UsuarioNoExisteExce("La aerolinea " + nickname + " no existe");
		} else {
			aerolinea.setNombre(nombre);
			aerolinea.setNickname(nickname);
			aerolinea.setEmail(email);
			aerolinea.setDescripcion(descripcion);
			aerolinea.setWeb(web);
			aerolinea.setPassword(password);
			aerolinea.setImagen(imagen);
		}

	}

	public DataCliente[] getClientes() throws UsuarioNoExisteExce {
		UsuariosHandler user = UsuariosHandler.getinstance();
		if (user.getCliente() == null || user.getCliente().isEmpty()) {
			throw new UsuarioNoExisteExce("No hay clientes en el sistema");
		} else {
			int cont = 0;
			DataCliente[] dts = new DataCliente[user.getCliente().size()];
			for (Cliente cli : user.getCliente().values()) {
				DataCliente dcli = new DataCliente(cli.getNickname(), cli.getNombre(), cli.getEmail(),
						cli.getApellido(), cli.getNacimiento(), cli.getNacionalidad(), cli.getTipoDocumento(),
						cli.getDocumento(), null, cli.getPassword(), cli.getImagen(), cli.getSeguidos(), cli.getSeguidores());
				dts[cont] = dcli;
				cont++;
			}
			return dts;
		}
	}

	public DataAerolinea[] getAerolineas() throws UsuarioNoExisteExce {
		UsuariosHandler user = UsuariosHandler.getinstance();
		if (user.getAerolinea() == null || user.getAerolinea().isEmpty()) {
			throw new UsuarioNoExisteExce("No hay aerolineas en el sistema");
		} else {
			int cont = 0;
			DataAerolinea[] dts = new DataAerolinea[user.getAerolinea().size()];
			for (Aerolinea cli : user.getAerolinea().values()) {
				ArrayList<DataRutaVuelo> datosRutasvuelo = new ArrayList<DataRutaVuelo>();
				for (RutaVuelo rv : cli.getRutasVuelo()) {

					ArrayList<String> listaNombresCategorias = new ArrayList<>();
					for (Categoria cat : rv.getCategorias()) {
						listaNombresCategorias.add(cat.getNombre());
					}

					ArrayList<DataVuelo> listaDataVuelo = new ArrayList<>();
					for (Vuelo vuelo : rv.getVuelos()) {

						ArrayList<DataReserva> listaDataReserva = new ArrayList<>();
						for (Reserva res : vuelo.getReservas()) {
							DataReserva dtReserva = new DataReserva(res.getCliente().getNickname(), vuelo.getNombre(),
									res.getFechaReserva(), res.getAsientoSeleccionado(), res.getCantidadPasajes(),
									res.getCantidadExtraEquipaje(), res.getCostoTotal());
							listaDataReserva.add(dtReserva);
						}

						DataVuelo dtvuelo = new DataVuelo(vuelo.getNombre(), vuelo.getFecha(), vuelo.getDuracion(),
								vuelo.getMaxTurista(), vuelo.getMaxEjecutivo(), vuelo.getFechaAlta(), listaDataReserva);
						listaDataVuelo.add(dtvuelo);
						listaDataReserva.clear();
					}

					DataRutaVuelo drv = new DataRutaVuelo(rv.getNombre(), rv.getDescripcion(), rv.getHora(),
							rv.getFechaAlta(), rv.getCiudadOrigen(), rv.getCiudadDestino(),
							rv.getCostoEjecutivo(), rv.getCostoTurista(), rv.getCostoExtra(), listaNombresCategorias,
							listaDataVuelo, cli.getNickname(), rv.getImagen(), rv.getDescCorta(), rv.getEstado(), rv.getVideo());
					datosRutasvuelo.add(drv);
				}

				DataAerolinea dcli = new DataAerolinea(cli.getNickname(), cli.getNombre(), cli.getEmail(),
						cli.getDescripcion(), cli.getWeb(), datosRutasvuelo, cli.getPassword(), cli.getImagen(),
						cli.getSeguidos(), cli.getSeguidores());
				dts[cont] = dcli;
				cont++;
			}
			return dts;
		}
	}

    @Override
	public List<DataReserva> getReservasSinCheckIn(String nickCliente) throws UsuarioNoExisteExce {
		DataCliente cliente = verInfoCliente(nickCliente);
		List<DataReserva> reservas = cliente.getReservas();
		List<DataReserva> reservasSinCheckIn = new ArrayList<DataReserva>();

		for (DataReserva res : reservas) {
			System.out.println(res.getCheckIn());
			if (!res.getCheckIn()) {
				reservasSinCheckIn.add(res);
			}
		}
		
		return reservas;
	}
	
    @Override
	public void realizarCheckIn(String nickC, String nickV) throws ClientePasajeroRepetidoExce {
		UsuariosHandler usuariosHandler = UsuariosHandler.getinstance();
		Cliente cliente = usuariosHandler.obtenerCliente(nickC);
		Vuelo vuelo = VuelosHandler.getinstance().obtenerVuelo(nickV);
		if (cliente == null) {
			throw new ClientePasajeroRepetidoExce("El cliente especificado no existe.");
		}
		Reserva reserva = null;
		List<Reserva> reservasCliente = cliente.getReservas();
		List<Reserva> reservasVuelo = vuelo.getReservas();

		for (Reserva res : reservasCliente) {
			if (nickV.equals(res.getVuelo().getNombre())) {
				res.setCheckIn();
				reserva = res;
			}
		}

		if (reserva == null) {
			throw new ClientePasajeroRepetidoExce("La reserva no existe");
		}

		for (Reserva res : reservasVuelo) {
			if (nickC.equals(res.getCliente().getNickname())) {
				res.setCheckIn();
			}
		}

		usuariosHandler.CheckInCliente(cliente,reserva);
	}

	@Override
	public DataCheckIn[] getCheckIns(String nickC) throws UsuarioNoExisteExce {
		UsuariosHandler user = UsuariosHandler.getinstance();
		Cliente cli = user.obtenerCliente(nickC);
		ArrayList<CheckIn> checkin = cli.getCheckIns();
		if (checkin == null || checkin.isEmpty()) {
			throw new UsuarioNoExisteExce("El cliente no tiene checkIns");
		} else {
			int cont = 0;
			DataCheckIn[] checkIns = new DataCheckIn[checkin.size()];
			for (CheckIn checkIn : checkin) {
				System.out.print("entre1");
				Reserva r = checkIn.getReserva();

				ArrayList<DataPasajes> pasajes = new ArrayList<DataPasajes>();
				ArrayList<Integer> asientos = checkIn.getAsientosAsignados();
				Integer i = 0;
				System.out.println("entre2");
				System.out.println(r.getPasajes());
				for (Pasaje p : r.getPasajes()) {
					System.out.println("entre al for");
					System.out.println(p.getNombre());
					System.out.println(p.getApellido());
					System.out.println(asientos.get(i));
					DataPasajes dp = new DataPasajes(p.getNombre(), p.getApellido(), asientos.get(i));
					pasajes.add(dp);
					i ++;
				}
				System.out.print("entre3");

				DataReserva reservaData = new DataReserva(r.getCliente().getNickname(), r.getVuelo().getNombre(),
				r.getFechaReserva(), r.getAsientoSeleccionado(), r.getCantidadPasajes(),
				r.getCantidadExtraEquipaje(), r.getCostoTotal(), pasajes);

				DataCheckIn dataCheck = new DataCheckIn(reservaData, checkIn.getAsientosAsignados(), 
						checkIn.getHoraEmbarque(), checkIn.getFechaActual(), checkIn.getCliente().getNickname());
				checkIns[cont] = dataCheck;
				cont++;
			}
			return checkIns;
		}
	}
	
	public void seguir(String nickSeguidor, String nickSeguido) throws UsuarioNoExisteExce {
	    UsuariosHandler users = UsuariosHandler.getinstance();
	    
	    if (!users.existeNickname(nickSeguidor) || !users.existeNickname(nickSeguido)) {
	        throw new UsuarioNoExisteExce("No existe ningún usuario con el nickname " + nickSeguidor + " o " + nickSeguido);
	    }
	    
	    // Si el seguidor es un cliente
	    if (users.existeClienteNick(nickSeguidor)) {
	        Cliente seguidor = users.obtenerCliente(nickSeguidor);
	        if (!seguidor.getSeguidos().contains(nickSeguido)) {
	            seguidor.addSeguido(nickSeguido); // Agrega el seguido al cliente

	            if (users.existeClienteNick(nickSeguido)) {
	                users.obtenerCliente(nickSeguido).addSeguidor(nickSeguidor);
	            } else if (users.existeAerolineaNick(nickSeguido)) {
	                users.obtenerAerolinea(nickSeguido).addSeguidor(nickSeguidor);
	            }
	        }
	    } 
	    else if (users.existeAerolineaNick(nickSeguidor)) {
	        Aerolinea seguidor = users.obtenerAerolinea(nickSeguidor);
	        if (!seguidor.getSeguidos().contains(nickSeguido)) {
	            seguidor.addSeguido(nickSeguido); 

	            if (users.existeClienteNick(nickSeguido)) {
	                users.obtenerCliente(nickSeguido).addSeguidor(nickSeguidor);
	            } else if (users.existeAerolineaNick(nickSeguido)) {
	                users.obtenerAerolinea(nickSeguido).addSeguidor(nickSeguidor);
	            }
	        }
	    }
	}
	
	public void dejarSeguir(String nickSeguidor, String nickSeguido) throws UsuarioNoExisteExce {
	    UsuariosHandler users = UsuariosHandler.getinstance();
	    
	    if (!users.existeNickname(nickSeguidor) || !users.existeNickname(nickSeguido)) {
	        throw new UsuarioNoExisteExce("No existe ningún usuario con el nickname " + nickSeguidor + " o " + nickSeguido);
	    }
	    
	    // Si el seguidor es un cliente
	    if (users.existeClienteNick(nickSeguidor)) {
	        Cliente seguidor = users.obtenerCliente(nickSeguidor);
	        if (seguidor.getSeguidos().contains(nickSeguido)) {
	            seguidor.getSeguidos().remove(nickSeguido); // Elimina el seguido del cliente

	            if (users.existeClienteNick(nickSeguido)) {
	                users.obtenerCliente(nickSeguido).getSeguidores().remove(nickSeguidor);
	            } else if (users.existeAerolineaNick(nickSeguido)) {
	                users.obtenerAerolinea(nickSeguido).getSeguidores().remove(nickSeguidor);
	            }
	        }
	    } 
	    else if (users.existeAerolineaNick(nickSeguidor)) {
	        Aerolinea seguidor = users.obtenerAerolinea(nickSeguidor);
	        if (seguidor.getSeguidos().contains(nickSeguido)) {
	            seguidor.getSeguidos().remove(nickSeguido); 

	            if (users.existeClienteNick(nickSeguido)) {
	                users.obtenerCliente(nickSeguido).getSeguidores().remove(nickSeguidor);
	            } else if (users.existeAerolineaNick(nickSeguido)) {
	                users.obtenerAerolinea(nickSeguido).getSeguidores().remove(nickSeguidor);
	            }
	        }
	    }
	}
}
