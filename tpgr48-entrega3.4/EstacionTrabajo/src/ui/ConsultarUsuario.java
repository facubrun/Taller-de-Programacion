package ui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;

import exceptions.UsuarioNoExisteExce;
import logica.Fabrica;
import logica.Datatypes.DataAerolinea;
import logica.Datatypes.DataCliente;
import logica.Datatypes.DataReserva;
import logica.Datatypes.DataRutaVuelo;
import logica.Interfaces.IUsuariosController;

public class ConsultarUsuario extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private IUsuariosController controlUsr;
	private JLabel lblIngresoCI;
	private JLabel lblNombre;
	private JLabel lblInfoUsuario;
	private JLabel lblEmail;
	private JLabel lblTipo;
	private JLabel lblDescripcion;
	private JLabel lblNacionalidad;
	private JLabel lblLink;
	private JLabel lblApellido;
	private JLabel lblNacimiento;
	private JLabel lblNickname;
	private JLabel lblSeleccioneUnCliente;
	private JLabel lblTipoDeUsuario;
	private JButton btnBuscarAerolinea;
	private JButton btnBuscarCliente;
	private JButton btnCerrar;
	private JComboBox comboBoxTipoUsuario;
	private JComboBox comboBoxCliente;
	private JComboBox comboBoxAerolinea;
	private JComboBox comboBoxAerolineaRV;
	private JComboBox<String> comboBoxReservasCliente;
	private JTextField textFieldEmail;
	private JTextField textFieldNombre;
	private JTextField textFieldNick;
	private JTextField textFieldTipo;
	private JTextField textFieldNacionalidad;
	private JTextField textFieldDescripcion;
	private JTextField textFieldLink;
	private JTextField textFieldApellido;
	private JTextField textFieldNacimiento;
	private JLabel lblCI;
	private JTextField textCI;
	
	private JLabel lblTIPODOC;
	private JTextField textTIPODOC;

	DataAerolinea[] aerolineas;
	DataAerolinea aerolinea;
	DataCliente[] clientes;
	DataCliente cliente;
	
	private JLabel lblReservaFecha;
	private JTextField textReservaFecha;
	private JLabel lblReservaTipoAsiento;
	private JTextField textReservaTipoAsiento;
	private JLabel lblReservaCantPasajes;
	private JTextField textReservaCantPasajes;
	private JLabel lblReservaCantExtra;
	private JTextField textReservaCantExtra;
	private JLabel lblReservaCostoTotal;
	private JTextField textReservaCostoTotal;
	
	private JLabel lblRutaDescripcion;
	private JTextField textRutaDescripcion;
	private JLabel lblRutaHora;
	private JTextField textRutaHora;
	private JLabel lblRutaFechaAlta;
	private JTextField textRutaFechaAlta;
	private JLabel lblRutaCiudadOrigen;
	private JTextField textRutaCiudadOrigen;
	private JLabel lblRutaCiudadDestino;
	private JTextField textRutaCiudadDestino;
	private JLabel lblRutaCostoEjecutivo;
	private JTextField textRutaCostoEjecutivo;
	private JLabel lblRutaCostoTurista;
	private JTextField textRutaCostoTurista;
	private JLabel lblRutaCostoExtra;
	private JTextField textRutaCostoExtra;
	private JLabel lblImagen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarUsuario frame = new ConsultarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		limpiarFormulario();
		setVisible(false);
	}
	
	/**
	 * Create the frame.
	 */
	public ConsultarUsuario() {

		Fabrica fabrica = Fabrica.getInstance();
		controlUsr = fabrica.getIControladorUsuario();

		try {
			aerolineas = controlUsr.getAerolineas();
		} catch (UsuarioNoExisteExce e) {
			aerolineas = new DataAerolinea[0];
		}
		try {
			clientes = controlUsr.getClientes();
		} catch (UsuarioNoExisteExce e) {
			clientes = new DataCliente[0];
		}

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
			public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
			}

			public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
				formInternalFrameClosing(evt);
			}

			@Override
			public void internalFrameActivated(InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameDeactivated(InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameDeiconified(InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameIconified(InternalFrameEvent arg0) {
			}

			@Override
			public void internalFrameOpened(InternalFrameEvent arg0) {
			}
		});

		setClosable(true);
		setTitle("Consultar un Usuario");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(785, 718);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 178, 190, 0, 190, 20, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 31, 0, 34, 30, 0, 0, 0, 0, 0, 0, 0, 0, 23, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		
		
		

		lblTipoDeUsuario = new JLabel("Tipo de usuario:");
		lblTipoDeUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblTipoDeUsuario = new GridBagConstraints();
		gbclblTipoDeUsuario.anchor = GridBagConstraints.EAST;
		gbclblTipoDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbclblTipoDeUsuario.gridx = 0;
		gbclblTipoDeUsuario.gridy = 1;
		getContentPane().add(lblTipoDeUsuario, gbclblTipoDeUsuario);

		comboBoxTipoUsuario = new JComboBox();
		comboBoxTipoUsuario.addItem("Seleccione...");
		comboBoxTipoUsuario.addItem("Cliente");
		comboBoxTipoUsuario.addItem("Aerolinea");
		comboBoxTipoUsuario.setMaximumRowCount(3);
		GridBagConstraints gbccomboBoxTipoUsuario = new GridBagConstraints();
		gbccomboBoxTipoUsuario.gridwidth = 2;
		gbccomboBoxTipoUsuario.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxTipoUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxTipoUsuario.gridx = 1;
		gbccomboBoxTipoUsuario.gridy = 1;
		comboBoxTipoUsuario.setSelectedIndex(0);
		getContentPane().add(comboBoxTipoUsuario, gbccomboBoxTipoUsuario);

		lblIngresoCI = new JLabel("Seleccione una aerolinea:");
		GridBagConstraints gbclblIngresoCI = new GridBagConstraints();
		gbclblIngresoCI.anchor = GridBagConstraints.EAST;
		gbclblIngresoCI.insets = new Insets(0, 0, 5, 5);
		gbclblIngresoCI.gridx = 0;
		gbclblIngresoCI.gridy = 2;
		lblIngresoCI.setVisible(false);
		getContentPane().add(lblIngresoCI, gbclblIngresoCI);

		btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				cmdBuscarClienteActionPerformed(event);
			}
		});
		comboBoxAerolinea = new JComboBox();
		GridBagConstraints gbccomboBoxAerolinea = new GridBagConstraints();
		gbccomboBoxAerolinea.gridwidth = 2;
		gbccomboBoxAerolinea.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxAerolinea.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxAerolinea.gridx = 1;
		gbccomboBoxAerolinea.gridy = 2;
		getContentPane().add(comboBoxAerolinea, gbccomboBoxAerolinea);
		GridBagConstraints gbcbtnBuscarCliente = new GridBagConstraints();
		gbcbtnBuscarCliente.fill = GridBagConstraints.VERTICAL;
		gbcbtnBuscarCliente.anchor = GridBagConstraints.WEST;
		gbcbtnBuscarCliente.insets = new Insets(0, 0, 5, 5);
		gbcbtnBuscarCliente.gridx = 3;
		gbcbtnBuscarCliente.gridy = 2;
		comboBoxAerolinea.setVisible(false);
		getContentPane().add(btnBuscarCliente, gbcbtnBuscarCliente);
		btnBuscarCliente.setVisible(false);

		lblSeleccioneUnCliente = new JLabel("Seleccione un cliente:");
		lblSeleccioneUnCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblSeleccioneUnCliente = new GridBagConstraints();
		gbclblSeleccioneUnCliente.anchor = GridBagConstraints.EAST;
		gbclblSeleccioneUnCliente.insets = new Insets(0, 0, 5, 5);
		gbclblSeleccioneUnCliente.gridx = 0;
		gbclblSeleccioneUnCliente.gridy = 3;
		lblSeleccioneUnCliente.setVisible(false);
		getContentPane().add(lblSeleccioneUnCliente, gbclblSeleccioneUnCliente);

		comboBoxCliente = new JComboBox();
		GridBagConstraints gbccomboBoxCliente = new GridBagConstraints();
		gbccomboBoxCliente.gridwidth = 2;
		gbccomboBoxCliente.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxCliente.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxCliente.gridx = 1;
		gbccomboBoxCliente.gridy = 3;
		comboBoxCliente.setVisible(false);
		getContentPane().add(comboBoxCliente, gbccomboBoxCliente);

		btnBuscarAerolinea = new JButton("Buscar");
		btnBuscarAerolinea.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscarAerolinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cmdBuscarAerolineaActionPerformed(event);
			}
		});
		GridBagConstraints gbcbtnBuscarAerolinea = new GridBagConstraints();
		gbcbtnBuscarAerolinea.fill = GridBagConstraints.VERTICAL;
		gbcbtnBuscarAerolinea.anchor = GridBagConstraints.WEST;
		gbcbtnBuscarAerolinea.insets = new Insets(0, 0, 5, 5);
		gbcbtnBuscarAerolinea.gridx = 3;
		gbcbtnBuscarAerolinea.gridy = 3;
		getContentPane().add(btnBuscarAerolinea, gbcbtnBuscarAerolinea);
		btnBuscarAerolinea.setVisible(false);

		lblInfoUsuario = new JLabel("Información del  usuario");
		GridBagConstraints gbclblInfoUsuario = new GridBagConstraints();
		gbclblInfoUsuario.insets = new Insets(0, 0, 5, 5);
		gbclblInfoUsuario.gridwidth = 2;
		gbclblInfoUsuario.gridx = 1;
		gbclblInfoUsuario.gridy = 4;
		getContentPane().add(lblInfoUsuario, gbclblInfoUsuario);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNombre = new GridBagConstraints();
		gbclblNombre.fill = GridBagConstraints.HORIZONTAL;
		gbclblNombre.insets = new Insets(0, 0, 5, 5);
		gbclblNombre.gridx = 0;
		gbclblNombre.gridy = 5;
		getContentPane().add(lblNombre, gbclblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		GridBagConstraints gbctextFieldNombre = new GridBagConstraints();
		gbctextFieldNombre.fill = GridBagConstraints.BOTH;
		gbctextFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNombre.gridx = 1;
		gbctextFieldNombre.gridy = 5;
		getContentPane().add(textFieldNombre, gbctextFieldNombre);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblApellido = new GridBagConstraints();
		gbclblApellido.anchor = GridBagConstraints.EAST;
		gbclblApellido.insets = new Insets(0, 0, 5, 5);
		gbclblApellido.gridx = 2;
		gbclblApellido.gridy = 5;
		getContentPane().add(lblApellido, gbclblApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.setEditable(false);
		GridBagConstraints gbctextFieldApellido = new GridBagConstraints();
		gbctextFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbctextFieldApellido.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldApellido.gridx = 3;
		gbctextFieldApellido.gridy = 5;
		getContentPane().add(textFieldApellido, gbctextFieldApellido);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});

		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblEmail = new GridBagConstraints();
		gbclblEmail.anchor = GridBagConstraints.EAST;
		gbclblEmail.insets = new Insets(0, 0, 5, 5);
		gbclblEmail.gridx = 0;
		gbclblEmail.gridy = 6;
		getContentPane().add(lblEmail, gbclblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		GridBagConstraints gbctextFieldEmail = new GridBagConstraints();
		gbctextFieldEmail.gridwidth = 3;
		gbctextFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbctextFieldEmail.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldEmail.gridx = 1;
		gbctextFieldEmail.gridy = 6;
		getContentPane().add(textFieldEmail, gbctextFieldEmail);
		textFieldEmail.setColumns(10);

		lblNickname = new JLabel("Nickname");
		GridBagConstraints gbclblNickname = new GridBagConstraints();
		gbclblNickname.insets = new Insets(0, 0, 5, 5);
		gbclblNickname.anchor = GridBagConstraints.EAST;
		gbclblNickname.gridx = 0;
		gbclblNickname.gridy = 7;
		getContentPane().add(lblNickname, gbclblNickname);

		textFieldNick = new JTextField();
		textFieldNick.setEditable(false);
		GridBagConstraints gbctextFieldNick = new GridBagConstraints();
		gbctextFieldNick.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNick.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldNick.gridx = 1;
		gbctextFieldNick.gridy = 7;
		getContentPane().add(textFieldNick, gbctextFieldNick);
		textFieldNick.setColumns(10);
		
		//ruta de vuelo
		lblRutaDescripcion = new JLabel("Descripción:");
		lblRutaDescripcion.setVisible(false);
		textRutaDescripcion = new JTextField();
		textRutaDescripcion.setEditable(false);
		textRutaDescripcion.setVisible(false);

		lblRutaHora = new JLabel("Hora:");
		lblRutaHora.setVisible(false);
		textRutaHora = new JTextField();
		textRutaHora.setEditable(false);
		textRutaHora.setVisible(false);

		lblRutaFechaAlta = new JLabel("Fecha de Alta:");
		lblRutaFechaAlta.setVisible(false);
		textRutaFechaAlta = new JTextField();
		textRutaFechaAlta.setEditable(false);
		textRutaFechaAlta.setVisible(false);

		lblRutaCiudadOrigen = new JLabel("Ciudad Origen:");
		lblRutaCiudadOrigen.setVisible(false);
		textRutaCiudadOrigen = new JTextField();
		textRutaCiudadOrigen.setEditable(false);
		textRutaCiudadOrigen.setVisible(false);

		lblRutaCiudadDestino = new JLabel("Ciudad Destino:");
		lblRutaCiudadDestino.setVisible(false);
		textRutaCiudadDestino = new JTextField();
		textRutaCiudadDestino.setEditable(false);
		textRutaCiudadDestino.setVisible(false);

		lblRutaCostoEjecutivo = new JLabel("Costo Ejecutivo:");
		lblRutaCostoEjecutivo.setVisible(false);
		textRutaCostoEjecutivo = new JTextField();
		textRutaCostoEjecutivo.setEditable(false);
		textRutaCostoEjecutivo.setVisible(false);

		lblRutaCostoTurista = new JLabel("Costo Turista:");
		lblRutaCostoTurista.setVisible(false);
		textRutaCostoTurista = new JTextField();
		textRutaCostoTurista.setEditable(false);
		textRutaCostoTurista.setVisible(false);

		lblRutaCostoExtra = new JLabel("Costo Extra:");
		lblRutaCostoExtra.setVisible(false);
		textRutaCostoExtra = new JTextField();
		textRutaCostoExtra.setEditable(false);
		textRutaCostoExtra.setVisible(false);
		
		
		
		
		lblImagen = new JLabel("");
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagen.gridx = 3;
		gbc_lblImagen.gridy = 12;
		getContentPane().add(lblImagen, gbc_lblImagen);

		// Agregar los campos de la ruta al layout
		GridBagConstraints gbc_lblRutaDescripcion = new GridBagConstraints();
		gbc_lblRutaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutaDescripcion.gridx = 0;
		gbc_lblRutaDescripcion.gridy = 18;
		getContentPane().add(lblRutaDescripcion, gbc_lblRutaDescripcion);

		GridBagConstraints gbc_textRutaDescripcion = new GridBagConstraints();
		gbc_textRutaDescripcion.gridwidth = 2;
		gbc_textRutaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textRutaDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRutaDescripcion.gridx = 1;
		gbc_textRutaDescripcion.gridy = 18;
		getContentPane().add(textRutaDescripcion, gbc_textRutaDescripcion);

		GridBagConstraints gbc_lblRutaHora = new GridBagConstraints();
		gbc_lblRutaHora.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutaHora.gridx = 0;
		gbc_lblRutaHora.gridy = 19;
		getContentPane().add(lblRutaHora, gbc_lblRutaHora);

		GridBagConstraints gbc_textRutaHora = new GridBagConstraints();
		gbc_textRutaHora.gridwidth = 2;
		gbc_textRutaHora.insets = new Insets(0, 0, 5, 5);
		gbc_textRutaHora.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRutaHora.gridx = 1;
		gbc_textRutaHora.gridy = 19;
		getContentPane().add(textRutaHora, gbc_textRutaHora);
		
		GridBagConstraints gbc_lblRutaFechaAlta = new GridBagConstraints();
		gbc_lblRutaFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutaFechaAlta.gridx = 0;
		gbc_lblRutaFechaAlta.gridy = 20;
		getContentPane().add(lblRutaFechaAlta, gbc_lblRutaFechaAlta);

		GridBagConstraints gbc_textRutaFechaAlta = new GridBagConstraints();
		gbc_textRutaFechaAlta.gridwidth = 2;
		gbc_textRutaFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_textRutaFechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRutaFechaAlta.gridx = 1;
		gbc_textRutaFechaAlta.gridy = 20;
		getContentPane().add(textRutaFechaAlta, gbc_textRutaFechaAlta);

		GridBagConstraints gbc_lblRutaCiudadOrigen = new GridBagConstraints();
		gbc_lblRutaCiudadOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutaCiudadOrigen.gridx = 0;
		gbc_lblRutaCiudadOrigen.gridy = 21;
		getContentPane().add(lblRutaCiudadOrigen, gbc_lblRutaCiudadOrigen);

		GridBagConstraints gbc_textRutaCiudadOrigen = new GridBagConstraints();
		gbc_textRutaCiudadOrigen.gridwidth = 2;
		gbc_textRutaCiudadOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_textRutaCiudadOrigen.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRutaCiudadOrigen.gridx = 1;
		gbc_textRutaCiudadOrigen.gridy = 21;
		getContentPane().add(textRutaCiudadOrigen, gbc_textRutaCiudadOrigen);

		GridBagConstraints gbc_lblRutaCiudadDestino = new GridBagConstraints();
		gbc_lblRutaCiudadDestino.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutaCiudadDestino.gridx = 0;
		gbc_lblRutaCiudadDestino.gridy = 22;
		getContentPane().add(lblRutaCiudadDestino, gbc_lblRutaCiudadDestino);

		GridBagConstraints gbc_textRutaCiudadDestino = new GridBagConstraints();
		gbc_textRutaCiudadDestino.gridwidth = 2;
		gbc_textRutaCiudadDestino.insets = new Insets(0, 0, 5, 5);
		gbc_textRutaCiudadDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRutaCiudadDestino.gridx = 1;
		gbc_textRutaCiudadDestino.gridy = 22;
		getContentPane().add(textRutaCiudadDestino, gbc_textRutaCiudadDestino);

		GridBagConstraints gbc_lblRutaCostoEjecutivo = new GridBagConstraints();
		gbc_lblRutaCostoEjecutivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutaCostoEjecutivo.gridx = 0;
		gbc_lblRutaCostoEjecutivo.gridy = 23;
		getContentPane().add(lblRutaCostoEjecutivo, gbc_lblRutaCostoEjecutivo);

		GridBagConstraints gbc_textRutaCostoEjecutivo = new GridBagConstraints();
		gbc_textRutaCostoEjecutivo.gridwidth = 2;
		gbc_textRutaCostoEjecutivo.insets = new Insets(0, 0, 5, 5);
		gbc_textRutaCostoEjecutivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRutaCostoEjecutivo.gridx = 1;
		gbc_textRutaCostoEjecutivo.gridy = 23;
		getContentPane().add(textRutaCostoEjecutivo, gbc_textRutaCostoEjecutivo);

		GridBagConstraints gbc_lblRutaCostoTurista = new GridBagConstraints();
		gbc_lblRutaCostoTurista.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutaCostoTurista.gridx = 0;
		gbc_lblRutaCostoTurista.gridy = 24;
		getContentPane().add(lblRutaCostoTurista, gbc_lblRutaCostoTurista);

		GridBagConstraints gbc_textRutaCostoTurista = new GridBagConstraints();
		gbc_textRutaCostoTurista.gridwidth = 2;
		gbc_textRutaCostoTurista.insets = new Insets(0, 0, 5, 5);
		gbc_textRutaCostoTurista.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRutaCostoTurista.gridx = 1;
		gbc_textRutaCostoTurista.gridy = 24;
		getContentPane().add(textRutaCostoTurista, gbc_textRutaCostoTurista);

		GridBagConstraints gbc_lblRutaCostoExtra = new GridBagConstraints();
		gbc_lblRutaCostoExtra.insets = new Insets(0, 0, 0, 5);
		gbc_lblRutaCostoExtra.gridx = 0;
		gbc_lblRutaCostoExtra.gridy = 25;
		getContentPane().add(lblRutaCostoExtra, gbc_lblRutaCostoExtra);

		GridBagConstraints gbc_textRutaCostoExtra = new GridBagConstraints();
		gbc_textRutaCostoExtra.gridwidth = 2;
		gbc_textRutaCostoExtra.insets = new Insets(0, 0, 0, 5);
		gbc_textRutaCostoExtra.fill = GridBagConstraints.HORIZONTAL;
		gbc_textRutaCostoExtra.gridx = 1;
		gbc_textRutaCostoExtra.gridy = 25;
		getContentPane().add(textRutaCostoExtra, gbc_textRutaCostoExtra);
		
		//ruta de vuelo
		
		//reserva
		lblReservaFecha = new JLabel("Fecha de Reserva:");
		lblReservaFecha.setVisible(false);
		textReservaFecha = new JTextField();
		textReservaFecha.setVisible(false);
		textReservaFecha.setEditable(false);
		lblReservaTipoAsiento = new JLabel("Tipo de Asiento:");
		lblReservaTipoAsiento.setVisible(false);
		textReservaTipoAsiento = new JTextField();
		textReservaTipoAsiento.setVisible(false);
		textReservaTipoAsiento.setEditable(false);
		textReservaTipoAsiento.setVisible(false);
		lblReservaCantPasajes = new JLabel("Cantidad de Pasajes:");
		lblReservaCantPasajes.setVisible(false);
		textReservaCantPasajes = new JTextField();
		textReservaCantPasajes.setVisible(false);
		textReservaCantPasajes.setEditable(false);
		lblReservaCantExtra = new JLabel("Cantidad de Equipaje Extra:");
		lblReservaCantExtra.setVisible(false);
		textReservaCantExtra = new JTextField();
		textReservaCantExtra.setVisible(false);
		textReservaCantExtra.setEditable(false);
		lblReservaCostoTotal = new JLabel("Costo Total:");
		lblReservaCostoTotal.setVisible(false);
		textReservaCostoTotal = new JTextField();
		textReservaCostoTotal.setVisible(false);
		textReservaCostoTotal.setEditable(false);
		
		GridBagConstraints gbc_lblReservaFecha = new GridBagConstraints();
		gbc_lblReservaFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblReservaFecha.gridx = 0;
		gbc_lblReservaFecha.gridy = 13;
		getContentPane().add(lblReservaFecha, gbc_lblReservaFecha);

		GridBagConstraints gbc_textReservaFecha = new GridBagConstraints();
		gbc_textReservaFecha.gridwidth = 2;
		gbc_textReservaFecha.insets = new Insets(0, 0, 5, 5);
		gbc_textReservaFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textReservaFecha.gridx = 1;
		gbc_textReservaFecha.gridy = 13;
		getContentPane().add(textReservaFecha, gbc_textReservaFecha);

		GridBagConstraints gbc_lblReservaTipoAsiento = new GridBagConstraints();
		gbc_lblReservaTipoAsiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblReservaTipoAsiento.gridx = 0;
		gbc_lblReservaTipoAsiento.gridy = 14;
		getContentPane().add(lblReservaTipoAsiento, gbc_lblReservaTipoAsiento);

		GridBagConstraints gbc_textReservaTipoAsiento = new GridBagConstraints();
		gbc_textReservaTipoAsiento.gridwidth = 2;
		gbc_textReservaTipoAsiento.insets = new Insets(0, 0, 5, 5);
		gbc_textReservaTipoAsiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textReservaTipoAsiento.gridx = 1;
		gbc_textReservaTipoAsiento.gridy = 14;
		getContentPane().add(textReservaTipoAsiento, gbc_textReservaTipoAsiento);
		
		GridBagConstraints gbc_lblReservaCantPasajes = new GridBagConstraints();
		gbc_lblReservaCantPasajes.insets = new Insets(0, 0, 5, 5);
		gbc_lblReservaCantPasajes.gridx = 0;
		gbc_lblReservaCantPasajes.gridy = 15;
		getContentPane().add(lblReservaCantPasajes, gbc_lblReservaCantPasajes);

		GridBagConstraints gbc_textReservaCantPasajes = new GridBagConstraints();
		gbc_textReservaCantPasajes.gridwidth = 2;
		gbc_textReservaCantPasajes.insets = new Insets(0, 0, 5, 5);
		gbc_textReservaCantPasajes.fill = GridBagConstraints.HORIZONTAL;
		gbc_textReservaCantPasajes.gridx = 1;
		gbc_textReservaCantPasajes.gridy = 15;
		getContentPane().add(textReservaCantPasajes, gbc_textReservaCantPasajes);

		GridBagConstraints gbc_lblReservaCantExtra = new GridBagConstraints();
		gbc_lblReservaCantExtra.insets = new Insets(0, 0, 5, 5);
		gbc_lblReservaCantExtra.gridx = 0;
		gbc_lblReservaCantExtra.gridy = 16;
		getContentPane().add(lblReservaCantExtra, gbc_lblReservaCantExtra);

		GridBagConstraints gbc_textReservaCantExtra = new GridBagConstraints();
		gbc_textReservaCantExtra.gridwidth = 2;
		gbc_textReservaCantExtra.insets = new Insets(0, 0, 5, 5);
		gbc_textReservaCantExtra.fill = GridBagConstraints.HORIZONTAL;
		gbc_textReservaCantExtra.gridx = 1;
		gbc_textReservaCantExtra.gridy = 16;
		getContentPane().add(textReservaCantExtra, gbc_textReservaCantExtra);

		GridBagConstraints gbc_lblReservaCostoTotal = new GridBagConstraints();
		gbc_lblReservaCostoTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblReservaCostoTotal.gridx = 0;
		gbc_lblReservaCostoTotal.gridy = 17;
		getContentPane().add(lblReservaCostoTotal, gbc_lblReservaCostoTotal);

		GridBagConstraints gbc_textReservaCostoTotal = new GridBagConstraints();
		gbc_textReservaCostoTotal.gridwidth = 2;
		gbc_textReservaCostoTotal.insets = new Insets(0, 0, 5, 5);
		gbc_textReservaCostoTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_textReservaCostoTotal.gridx = 1;
		gbc_textReservaCostoTotal.gridy = 17;
		getContentPane().add(textReservaCostoTotal, gbc_textReservaCostoTotal);
		//reserva
		
		lblNacimiento = new JLabel("Nacimiento:");
		lblNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNacimiento = new GridBagConstraints();
		gbclblNacimiento.anchor = GridBagConstraints.EAST;
		gbclblNacimiento.insets = new Insets(0, 0, 5, 5);
		gbclblNacimiento.gridx = 2;
		gbclblNacimiento.gridy = 7;
		lblNacimiento.setVisible(false);
		getContentPane().add(lblNacimiento, gbclblNacimiento);

		textFieldNacimiento = new JTextField();
		textFieldNacimiento.setEditable(false);
		GridBagConstraints gbctextFieldNacimiento = new GridBagConstraints();
		gbctextFieldNacimiento.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldNacimiento.gridx = 3;
		gbctextFieldNacimiento.gridy = 7;
		textFieldNacimiento.setVisible(false);
		getContentPane().add(textFieldNacimiento, gbctextFieldNacimiento);
		textFieldNacimiento.setColumns(10);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblTipo = new GridBagConstraints();
		gbclblTipo.anchor = GridBagConstraints.EAST;
		gbclblTipo.insets = new Insets(0, 0, 5, 5);
		gbclblTipo.gridx = 0;
		gbclblTipo.gridy = 8;
		getContentPane().add(lblTipo, gbclblTipo);

		textFieldTipo = new JTextField();
		textFieldTipo.setEditable(false);
		GridBagConstraints gbctextFieldTipo = new GridBagConstraints();
		gbctextFieldTipo.gridwidth = 3;
		gbctextFieldTipo.insets = new Insets(0, 0, 5, 5);
		gbctextFieldTipo.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldTipo.gridx = 1;
		gbctextFieldTipo.gridy = 8;
		getContentPane().add(textFieldTipo, gbctextFieldTipo);
		textFieldTipo.setColumns(10);

		lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNacionalidad = new GridBagConstraints();
		gbclblNacionalidad.anchor = GridBagConstraints.EAST;
		gbclblNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbclblNacionalidad.gridx = 0;
		gbclblNacionalidad.gridy = 9;
		lblNacionalidad.setVisible(false);
		getContentPane().add(lblNacionalidad, gbclblNacionalidad);

		textFieldNacionalidad = new JTextField();
		textFieldNacionalidad.setEditable(false);
		GridBagConstraints gbctextFieldNacionalidad = new GridBagConstraints();
		gbctextFieldNacionalidad.gridwidth = 3;
		gbctextFieldNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNacionalidad.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldNacionalidad.gridx = 1;
		gbctextFieldNacionalidad.gridy = 9;
		textFieldNacionalidad.setVisible(false);
		getContentPane().add(textFieldNacionalidad, gbctextFieldNacionalidad);
		textFieldNacionalidad.setColumns(10);

		lblCI = new JLabel("Doc:");
		lblCI.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblCI = new GridBagConstraints();
		gbclblCI.anchor = GridBagConstraints.EAST;
		gbclblCI.insets = new Insets(0, 0, 5, 5);
		gbclblCI.gridx = 0;
		gbclblCI.gridy = 10;
		lblCI.setVisible(false);
		getContentPane().add(lblCI, gbclblCI);

		textCI = new JTextField();
		textCI.setEditable(false);
		GridBagConstraints gbctextFieldCI = new GridBagConstraints();
		gbctextFieldCI.gridwidth = 3;
		gbctextFieldCI.insets = new Insets(0, 0, 5, 5);
		gbctextFieldCI.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldCI.gridx = 1;
		gbctextFieldCI.gridy = 10;
		textCI.setVisible(false);
		getContentPane().add(textCI, gbctextFieldCI);
		textCI.setColumns(10);
		
		lblTIPODOC = new JLabel("Tipo Doc:");
		lblTIPODOC.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblTIPODOC = new GridBagConstraints();
		gbclblTIPODOC.anchor = GridBagConstraints.EAST;
		gbclblTIPODOC.insets = new Insets(0, 0, 5, 5);
		gbclblTIPODOC.gridx = 2;
		gbclblTIPODOC.gridy = 10;
		lblTIPODOC.setVisible(false);
		getContentPane().add(lblTIPODOC, gbclblTIPODOC);
		
		textTIPODOC = new JTextField();
		textTIPODOC.setEditable(false);
		GridBagConstraints gbctextFieldTIPODOC = new GridBagConstraints();
		gbctextFieldTIPODOC.gridwidth = 3;
		gbctextFieldTIPODOC.insets = new Insets(0, 0, 5, 5);
		gbctextFieldTIPODOC.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldTIPODOC.gridx = 1;
		gbctextFieldTIPODOC.gridy = 11;
		textTIPODOC.setVisible(false);
		getContentPane().add(textTIPODOC, gbctextFieldTIPODOC);
		textTIPODOC.setColumns(10);

		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblDescripcion = new GridBagConstraints();
		gbclblDescripcion.anchor = GridBagConstraints.EAST;
		gbclblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbclblDescripcion.gridx = 0;
		gbclblDescripcion.gridy = 10;
		lblDescripcion.setVisible(false);
		getContentPane().add(lblDescripcion, gbclblDescripcion);

		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setEditable(false);
		GridBagConstraints gbctextFieldDescripcion = new GridBagConstraints();
		gbctextFieldDescripcion.gridwidth = 3;
		gbctextFieldDescripcion.insets = new Insets(0, 0, 5, 5);
		gbctextFieldDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldDescripcion.gridx = 1;
		gbctextFieldDescripcion.gridy = 10;
		getContentPane().add(textFieldDescripcion, gbctextFieldDescripcion);
		textFieldDescripcion.setColumns(10);
		textFieldDescripcion.setVisible(false);

		lblLink = new JLabel("Link:");
		lblLink.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblLink = new GridBagConstraints();
		gbclblLink.anchor = GridBagConstraints.EAST;
		gbclblLink.insets = new Insets(0, 0, 5, 5);
		gbclblLink.gridx = 0;
		gbclblLink.gridy = 11;
		lblLink.setVisible(false);
		getContentPane().add(lblLink, gbclblLink);

		textFieldLink = new JTextField();
		textFieldLink.setEditable(false);
		GridBagConstraints gbctextFieldLink = new GridBagConstraints();
		gbctextFieldLink.gridwidth = 3;
		gbctextFieldLink.insets = new Insets(0, 0, 5, 5);
		gbctextFieldLink.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldLink.gridx = 1;
		gbctextFieldLink.gridy = 11;
		textFieldLink.setVisible(false);
		getContentPane().add(textFieldLink, gbctextFieldLink);
		textFieldLink.setColumns(10);
		
		comboBoxAerolineaRV = new JComboBox();
		GridBagConstraints gbccomboBoxAerolineaRV = new GridBagConstraints();
		gbccomboBoxAerolineaRV.gridwidth = 2;
		gbccomboBoxAerolineaRV.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxAerolineaRV.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxAerolineaRV.gridx = 1;
		gbccomboBoxAerolineaRV.gridy = 12;
		getContentPane().add(comboBoxAerolineaRV, gbccomboBoxAerolineaRV);
		comboBoxAerolineaRV.setVisible(false);
		
		comboBoxAerolineaRV.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int selectedIndex = comboBoxAerolineaRV.getSelectedIndex();
		        if (selectedIndex >= 0 && aerolinea != null) {
		            List<DataRutaVuelo> rutas = aerolinea.getRutasVuelo();
		            if (rutas != null && selectedIndex < rutas.size()) {
		                DataRutaVuelo rutaSeleccionada = rutas.get(selectedIndex);

		                textRutaDescripcion.setText(rutaSeleccionada.getDescripcion());
		                textRutaHora.setText(rutaSeleccionada.getHora().toString());
		                textRutaFechaAlta.setText(rutaSeleccionada.getFechaAlta().toString());
		                textRutaCiudadOrigen.setText(rutaSeleccionada.getCiudadOrigen());
		                textRutaCiudadDestino.setText(rutaSeleccionada.getCiudadDestino());
		                textRutaCostoEjecutivo.setText(String.valueOf(rutaSeleccionada.getCostoEjecutivo()));
		                textRutaCostoTurista.setText(String.valueOf(rutaSeleccionada.getCostoTurista()));
		                textRutaCostoExtra.setText(String.valueOf(rutaSeleccionada.getCostoExtra()));
		               
		            }
		        }
		    }
		});
		
		comboBoxReservasCliente = new JComboBox();
		GridBagConstraints gbccomboBoxReservasCliente = new GridBagConstraints();
		gbccomboBoxReservasCliente.gridwidth = 2;
		gbccomboBoxReservasCliente.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxReservasCliente.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxReservasCliente.gridx = 1;
		gbccomboBoxReservasCliente.gridy = 12;
		getContentPane().add(comboBoxReservasCliente, gbccomboBoxReservasCliente);
		comboBoxReservasCliente.setVisible(false);
		
		comboBoxReservasCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = comboBoxReservasCliente.getSelectedIndex();
				if (selectedIndex >= 0 && cliente != null) {
					List<DataReserva> reservas = cliente.getReservas();
					if (reservas != null && selectedIndex < reservas.size()) {
						DataReserva reservaSeleccionada = reservas.get(selectedIndex);
						textReservaFecha.setText(reservaSeleccionada.getFechaReserva().toString());
						textReservaTipoAsiento.setText(reservaSeleccionada.gettipoAsiento().toString());
						textReservaCantPasajes.setText(reservaSeleccionada.getCantPasajes().toString());
						textReservaCantExtra.setText(reservaSeleccionada.getCantExtra().toString());
						textReservaCostoTotal.setText(String.valueOf(reservaSeleccionada.getCostoTotal()));
					}
				}
			}
		});
		
		
		comboBoxTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				inicializarVisibilidadCampos();
				
				if(comboBoxTipoUsuario.getSelectedIndex() == 0) {
					limpiarFormulario();
				}
				
				if (comboBoxTipoUsuario.getSelectedIndex() == 1) {
					comboBoxCliente.removeAllItems();
					limpiarFormulario();
		
					comboBoxTipoUsuario.setSelectedIndex(1);
					
					try {
						clientes = controlUsr.getClientes();
						for (int i = 0; i < clientes.length; i++) {
							comboBoxCliente.addItem(clientes[i].toString());
						}
					} catch (UsuarioNoExisteExce e2) {

					}
					comboBoxCliente.setSelectedIndex(-1);
					comboBoxCliente.setVisible(true);
		            lblSeleccioneUnCliente.setVisible(true);
		            btnBuscarAerolinea.setVisible(true);
		            comboBoxAerolinea.setVisible(false);
		            comboBoxAerolineaRV.setVisible(false);  // Ocultar rutas de vuelo
		            textFieldDescripcion.setVisible(false);
		            textFieldLink.setVisible(true);
		            btnBuscarCliente.setVisible(false);
		            lblIngresoCI.setVisible(false);
		            lblNacionalidad.setVisible(true);
		            textFieldNacionalidad.setVisible(true);
		            lblCI.setVisible(true);
		            textCI.setVisible(true);
		            lblDescripcion.setVisible(false);
		            lblNacimiento.setVisible(true);
		            textFieldNacimiento.setVisible(true);

		            // Mostrar campos de reserva
		            comboBoxReservasCliente.setVisible(true);
		            lblReservaFecha.setVisible(true);
		            textReservaFecha.setVisible(true);
		            lblReservaTipoAsiento.setVisible(true);
		            textReservaTipoAsiento.setVisible(true);
		            lblReservaCantPasajes.setVisible(true);
		            textReservaCantPasajes.setVisible(true);
		            lblReservaCantExtra.setVisible(true);
		            textReservaCantExtra.setVisible(true);
		            lblReservaCostoTotal.setVisible(true);
		            textReservaCostoTotal.setVisible(true);
		            	

		            // Ocultar campos de ruta de vuelo
		            comboBoxAerolineaRV.setVisible(false);
		            lblRutaDescripcion.setVisible(false);
		            textRutaDescripcion.setVisible(false);
		            lblRutaHora.setVisible(false);
		            textRutaHora.setVisible(false);
		            lblRutaFechaAlta.setVisible(false);
		            textRutaFechaAlta.setVisible(false);
		            lblRutaCiudadOrigen.setVisible(false);
		            textRutaCiudadOrigen.setVisible(false);
		            lblRutaCiudadDestino.setVisible(false);
		            textRutaCiudadDestino.setVisible(false);
		            lblRutaCostoEjecutivo.setVisible(false);
		            textRutaCostoEjecutivo.setVisible(false);
		            lblRutaCostoTurista.setVisible(false);
		            textRutaCostoTurista.setVisible(false);
		            lblRutaCostoExtra.setVisible(false);
		            textRutaCostoExtra.setVisible(false);
		            lblTIPODOC.setVisible(true);
		            textTIPODOC.setVisible(true);
				} else if (comboBoxTipoUsuario.getSelectedIndex() == 2) {
					try {
						limpiarFormulario();
						comboBoxTipoUsuario.setSelectedIndex(2);
						aerolineas = controlUsr.getAerolineas();
						for (int i = 0; i < aerolineas.length; i++) {
							comboBoxAerolinea.addItem(aerolineas[i].toString());
						}
					} catch (UsuarioNoExisteExce e1) {

					}
					comboBoxAerolinea.setSelectedIndex(-1);
					comboBoxCliente.setVisible(false);
		            lblSeleccioneUnCliente.setVisible(false);
		            btnBuscarAerolinea.setVisible(false);
		            comboBoxAerolinea.setVisible(true);
		            comboBoxAerolineaRV.setVisible(true);  // Mostrar rutas de vuelo
		            textFieldDescripcion.setVisible(true);
		            textFieldLink.setVisible(true);
		            btnBuscarCliente.setVisible(true);
		            lblIngresoCI.setVisible(true);
		            lblNacionalidad.setVisible(false);
		            textFieldNacionalidad.setVisible(false);
		            lblCI.setVisible(false);
		            textCI.setVisible(false);
		            lblDescripcion.setVisible(true);
		            lblNacimiento.setVisible(false);
		            textFieldNacimiento.setVisible(false);
		            lblTIPODOC.setVisible(false);
		            textTIPODOC.setVisible(false);
		            lblApellido.setVisible(false);
		            textFieldApellido.setVisible(false);

		            // Ocultar campos de reserva
		            comboBoxReservasCliente.setVisible(false);
		            lblReservaFecha.setVisible(false);
		            textReservaFecha.setVisible(false);
		            lblReservaTipoAsiento.setVisible(false);
		            textReservaTipoAsiento.setVisible(false);
		            lblReservaCantPasajes.setVisible(false);
		            textReservaCantPasajes.setVisible(false);
		            lblReservaCantExtra.setVisible(false);
		            textReservaCantExtra.setVisible(false);
		            lblReservaCostoTotal.setVisible(false);
		            textReservaCostoTotal.setVisible(false);

		            // Mostrar campos de ruta de vuelo
		            comboBoxAerolineaRV.setVisible(true);
		            lblRutaDescripcion.setVisible(true);
		            textRutaDescripcion.setVisible(true);
		            lblRutaHora.setVisible(true);
		            textRutaHora.setVisible(true);
		            lblRutaFechaAlta.setVisible(true);
		            textRutaFechaAlta.setVisible(true);
		            lblRutaCiudadOrigen.setVisible(true);
		            textRutaCiudadOrigen.setVisible(true);
		            lblRutaCiudadDestino.setVisible(true);
		            textRutaCiudadDestino.setVisible(true);
		            lblRutaCostoEjecutivo.setVisible(true);
		            textRutaCostoEjecutivo.setVisible(true);
		            lblRutaCostoTurista.setVisible(true);
		            textRutaCostoTurista.setVisible(true);
		            lblRutaCostoExtra.setVisible(true);
		            textRutaCostoExtra.setVisible(true);
				} else {
					comboBoxReservasCliente.setVisible(false);
		            lblReservaFecha.setVisible(false);
		            textReservaFecha.setVisible(false);
		            lblReservaTipoAsiento.setVisible(false);
		            textReservaTipoAsiento.setVisible(false);
		            lblReservaCantPasajes.setVisible(false);
		            textReservaCantPasajes.setVisible(false);
		            lblReservaCantExtra.setVisible(false);
		            textReservaCantExtra.setVisible(false);
		            lblReservaCostoTotal.setVisible(false);
		            textReservaCostoTotal.setVisible(false);

		            // Ocultar todos los campos de ruta de vuelo
		            comboBoxAerolineaRV.setVisible(false);
		            lblRutaDescripcion.setVisible(false);
		            textRutaDescripcion.setVisible(false);
		            lblRutaHora.setVisible(false);
		            textRutaHora.setVisible(false);
		            lblRutaFechaAlta.setVisible(false);
		            textRutaFechaAlta.setVisible(false);
		            lblRutaCiudadOrigen.setVisible(false);
		            textRutaCiudadOrigen.setVisible(false);
		            lblRutaCiudadDestino.setVisible(false);
		            textRutaCiudadDestino.setVisible(false);
		            lblRutaCostoEjecutivo.setVisible(false);
		            textRutaCostoEjecutivo.setVisible(false);
		            lblRutaCostoTurista.setVisible(false);
		            textRutaCostoTurista.setVisible(false);
		            lblRutaCostoExtra.setVisible(false);
		            textRutaCostoExtra.setVisible(false);
		            lblTIPODOC.setVisible(false);
		            textTIPODOC.setVisible(false);
				}
			}
		});

	}

	protected void cmdBuscarClienteActionPerformed(ActionEvent event) {
		if (comboBoxAerolinea.getSelectedItem() != null) {
			try {
				String aer = this.comboBoxAerolinea.getSelectedItem().toString();
				aer = aer.split(" ", 2)[0];
				aerolinea = controlUsr.verInfoAerolinea(aer);
				textFieldNombre.setText(aerolinea.getNombre());
				textFieldEmail.setText(aerolinea.getEmail());
				textFieldNick.setText(aerolinea.getNickname());
				textFieldTipo.setText("Aerolinea");
				textFieldDescripcion.setText(aerolinea.getDescripcion());
				textFieldLink.setText(aerolinea.getWeb());
				
				comboBoxAerolineaRV.removeAllItems();
				List<DataRutaVuelo> rvuelo = aerolinea.getRutasVuelo();
				for (DataRutaVuelo datarv: rvuelo) {
					comboBoxAerolineaRV.addItem(datarv.getNombre().toString());
				}
//				mostrarImagenAerolinea(aer);
				
			} catch (UsuarioNoExisteExce e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Buscar usuario", JOptionPane.ERROR_MESSAGE);
				limpiarFormulario();
			}

		} else {
			JOptionPane.showMessageDialog(this, "No hay ningun usuario seleccionado", "Buscar usuario",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void cmdBuscarAerolineaActionPerformed(ActionEvent event) {
		if (comboBoxCliente.getSelectedItem() != null) {
			try {
				String cli = this.comboBoxCliente.getSelectedItem().toString();
				cli = cli.split(" ", 2)[0];
				cliente = controlUsr.verInfoCliente(cli);
				
				comboBoxReservasCliente.removeAllItems();
	            textReservaFecha.setText("");
	            textReservaTipoAsiento.setText("");
	            textReservaCantPasajes.setText("");
	            textReservaCantExtra.setText("");
	            textReservaCostoTotal.setText("");
	            
				textFieldNombre.setText(cliente.getNombre());
				textFieldApellido.setText(cliente.getApellido());
				textFieldEmail.setText(cliente.getEmail());
				textFieldNick.setText(cliente.getNickname());
				textFieldTipo.setText("cliente");
				textFieldNacionalidad.setText(cliente.getTipoNacionalidad());
				textFieldNacimiento.setText(cliente.getNacimiento().toString());
				textTIPODOC.setText(cliente.getDocumento());
	            textCI.setText(cliente.getTipoDocumento()); //estan definidos al reves
				
				comboBoxReservasCliente.removeAllItems();
				List<DataReserva> reservas = cliente.getReservas();
				if(reservas != null && !reservas.isEmpty()) {
					for (DataReserva reserva : reservas) {
		                comboBoxReservasCliente.addItem(reserva.getNomV() + " - " + reserva.getFechaReserva());
		            }
				} else {
					comboBoxReservasCliente.addItem("No hay reservas");
				}
				comboBoxReservasCliente.setVisible(true);
//				mostrarImagenCliente(cli);
			} catch (UsuarioNoExisteExce e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Buscar usuario", JOptionPane.ERROR_MESSAGE);
				limpiarFormulario();
			}

		} else {
			JOptionPane.showMessageDialog(this, "No hay ningun usuario seleccionado", "Buscar usuario",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void inicializarVisibilidadCampos() {
	    // Ocultar campos de reserva
	    comboBoxReservasCliente.setVisible(false);
	    lblReservaFecha.setVisible(false);
	    textReservaFecha.setVisible(false);
	    lblReservaTipoAsiento.setVisible(false);
	    textReservaTipoAsiento.setVisible(false);
	    lblReservaCantPasajes.setVisible(false);
	    textReservaCantPasajes.setVisible(false);
	    lblReservaCantExtra.setVisible(false);
	    textReservaCantExtra.setVisible(false);
	    lblReservaCostoTotal.setVisible(false);
	    textReservaCostoTotal.setVisible(false);

	    // Ocultar campos de ruta de vuelo
	    comboBoxAerolineaRV.setVisible(false);
	    lblRutaDescripcion.setVisible(false);
	    textRutaDescripcion.setVisible(false);
	    lblRutaHora.setVisible(false);
	    textRutaHora.setVisible(false);
	    lblRutaFechaAlta.setVisible(false);
	    textRutaFechaAlta.setVisible(false);
	    lblRutaCiudadOrigen.setVisible(false);
	    textRutaCiudadOrigen.setVisible(false);
	    lblRutaCiudadDestino.setVisible(false);
	    textRutaCiudadDestino.setVisible(false);
	    lblRutaCostoEjecutivo.setVisible(false);
	    textRutaCostoEjecutivo.setVisible(false);
	    lblRutaCostoTurista.setVisible(false);
	    textRutaCostoTurista.setVisible(false);
	    lblRutaCostoExtra.setVisible(false);
	    textRutaCostoExtra.setVisible(false);
	}


	private void limpiarFormulario() {
		comboBoxTipoUsuario.setSelectedIndex(0);
		textFieldNacimiento.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldEmail.setText("");
		textFieldNick.setText("");
		textFieldTipo.setText("");
		textCI.setText("");
		textTIPODOC.setText("");
		textFieldNacionalidad.setText("");
		textFieldDescripcion.setText("");
		textFieldLink.setText("");
		textFieldEmail.setText("");
		textReservaFecha.setText("");
		textReservaTipoAsiento.setText("");
		textReservaCantPasajes.setText("");
		textReservaCantExtra.setText("");
		textReservaCostoTotal.setText("");
	}
	
//	private void mostrarImagenCliente(String cliente) {
//		DataCliente cli;
//		try {
//			cli = controlUsr.verInfoCliente(cliente);
//			ImageIcon imagen = new ImageIcon(cli.getImagen());
//			lblImagen.setIcon(imagen);
//		} catch (UsuarioNoExisteExce e) {
//			e.printStackTrace();
//		}
//	}
	
//	private void mostrarImagenAerolinea(String aerolinea) {
//		DataAerolinea aer;
//		try {
//			aer = controlUsr.verInfoAerolinea(aerolinea);
//			ImageIcon imagen = new ImageIcon(aer.getImagen());
//			lblImagen.setIcon(imagen);
//		} catch (UsuarioNoExisteExce e) {
//			e.printStackTrace();
//		}
//	}

}
