package ui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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
import logica.Datatypes.DataCliente;
import logica.Interfaces.IUsuariosController;
import logica.Fabrica;
import logica.Datatypes.DataAerolinea;
import javax.swing.JPasswordField;

public class ModificarUsuario extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private IUsuariosController controlUsr;

	private JComboBox comboBoxCliente;
	private JButton btnCerrar;
	private JLabel lblSeleccioneUnUsuario;
	private JButton btnSeleccionarCliente;
	private JButton btnConfirmar;
	private JLabel lblNickname;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblEmail;
	private JLabel lblNacimiento;
	private JLabel lblTipo;
	private JLabel lblNacionalidad;
	private JLabel lblDescripcion;
	private JLabel lblLink;
	private JTextField textFieldNickname;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldTipo;
	private JTextField textFieldNacionalidad;
	private JTextField textFieldDescripcion;
	private JTextField textFieldLink;
	private JLabel lblTipoDeUsuario;
	private JComboBox comboBoxTipoUsuario;
	private JLabel lblSeleccioneUnaAerolinea;
	private JComboBox comboBoxAerolinea;
	private JButton btnSeleccionarAerolinea;
	private JLabel lblIngreseNacimiento;
	private JLabel lblDia;
	private JLabel lblMes;
	private JLabel lblAnio;
	private JTextField textDia;
	private JTextField textMes;
	private JTextField textAnio;

	private DataAerolinea[] aerolineas;
	private DataAerolinea aerolinea;
	private DataCliente[] clientes;
	private DataCliente cliente;
	private JLabel lblContraseña;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public ModificarUsuario() {

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Modificar datos de usuario");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(683, 433);
		setLocation(0, 0);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 172, 288, 105, 71, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 0, 0, 25, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		Fabrica fabrica = Fabrica.getInstance();
		controlUsr = fabrica.getIControladorUsuario();

		lblTipoDeUsuario = new JLabel("Tipo de usuario:");
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
		comboBoxTipoUsuario.setSelectedIndex(0);
		comboBoxTipoUsuario.setMaximumRowCount(3);
		GridBagConstraints gbccomboBoxTipoUsuario = new GridBagConstraints();
		gbccomboBoxTipoUsuario.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxTipoUsuario.fill = GridBagConstraints.BOTH;
		gbccomboBoxTipoUsuario.gridx = 1;
		gbccomboBoxTipoUsuario.gridy = 1;
		getContentPane().add(comboBoxTipoUsuario, gbccomboBoxTipoUsuario);

		lblSeleccioneUnUsuario = new JLabel("Seleccione un Cliente:");
		lblSeleccioneUnUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblSeleccioneUnUsuario = new GridBagConstraints();
		gbclblSeleccioneUnUsuario.insets = new Insets(0, 0, 5, 5);
		gbclblSeleccioneUnUsuario.anchor = GridBagConstraints.EAST;
		gbclblSeleccioneUnUsuario.gridx = 0;
		gbclblSeleccioneUnUsuario.gridy = 2;
		lblSeleccioneUnUsuario.setVisible(false);
		getContentPane().add(lblSeleccioneUnUsuario, gbclblSeleccioneUnUsuario);

		comboBoxCliente = new JComboBox();
		GridBagConstraints gbccomboBoxCliente = new GridBagConstraints();
		gbccomboBoxCliente.fill = GridBagConstraints.BOTH;
		gbccomboBoxCliente.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxCliente.gridx = 1;
		gbccomboBoxCliente.gridy = 2;
		comboBoxCliente.setVisible(false);
		getContentPane().add(comboBoxCliente, gbccomboBoxCliente);

		btnCerrar = new JButton("Cancelar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});

		btnSeleccionarCliente = new JButton("Seleccionar");
		btnSeleccionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					cliente = controlUsr.verInfoCliente(comboBoxCliente.getSelectedItem().toString().split(" ", 2)[0]);
					textFieldNickname.setText(cliente.getNickname());
					textFieldNombre.setText(cliente.getNombre());
					textFieldApellido.setText(cliente.getApellido());
					textFieldEmail.setText(cliente.getEmail());
					textFieldNacionalidad.setText(cliente.getTipoNacionalidad());

					LocalDate nacimiento = cliente.getNacimiento();
					String dia = String.valueOf(nacimiento.getDayOfMonth());
					String mes = String.valueOf(nacimiento.getMonthValue());
					String anio = String.valueOf(nacimiento.getYear());
					textDia.setText(dia);
					textMes.setText(mes);
					textAnio.setText(anio);

				} catch (UsuarioNoExisteExce e3) {

				}

			}
		});
		GridBagConstraints gbcbtnSeleccionarCliente = new GridBagConstraints();
		gbcbtnSeleccionarCliente.insets = new Insets(0, 0, 5, 5);
		gbcbtnSeleccionarCliente.gridx = 2;
		gbcbtnSeleccionarCliente.gridy = 2;
		btnSeleccionarCliente.setVisible(false);
		getContentPane().add(btnSeleccionarCliente, gbcbtnSeleccionarCliente);

		lblSeleccioneUnaAerolinea = new JLabel("Seleccione un Aerolinea:");
		GridBagConstraints gbclblSeleccioneUnAerolinea = new GridBagConstraints();
		gbclblSeleccioneUnAerolinea.anchor = GridBagConstraints.EAST;
		gbclblSeleccioneUnAerolinea.insets = new Insets(0, 0, 5, 5);
		gbclblSeleccioneUnAerolinea.gridx = 0;
		gbclblSeleccioneUnAerolinea.gridy = 3;
		lblSeleccioneUnaAerolinea.setVisible(false);
		getContentPane().add(lblSeleccioneUnaAerolinea, gbclblSeleccioneUnAerolinea);

		comboBoxAerolinea = new JComboBox();
		GridBagConstraints gbccomboBoxAerolinea = new GridBagConstraints();
		gbccomboBoxAerolinea.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxAerolinea.fill = GridBagConstraints.BOTH;
		gbccomboBoxAerolinea.gridx = 1;
		gbccomboBoxAerolinea.gridy = 3;
		comboBoxAerolinea.setVisible(false);
		getContentPane().add(comboBoxAerolinea, gbccomboBoxAerolinea);

		btnSeleccionarAerolinea = new JButton("Seleccionar");
		GridBagConstraints gbcbtnSeleccionarAerolinea = new GridBagConstraints();
		gbcbtnSeleccionarAerolinea.insets = new Insets(0, 0, 5, 5);
		gbcbtnSeleccionarAerolinea.gridx = 2;
		gbcbtnSeleccionarAerolinea.gridy = 3;
		btnSeleccionarAerolinea.setVisible(false);
		btnSeleccionarAerolinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					aerolinea = controlUsr
							.verInfoAerolinea(comboBoxAerolinea.getSelectedItem().toString().split(" ", 2)[0]);
					textFieldNickname.setText(aerolinea.getNickname());
					textFieldNombre.setText(aerolinea.getNombre());
					textFieldEmail.setText(aerolinea.getEmail());
					textFieldDescripcion.setText(aerolinea.getDescripcion());
					textFieldLink.setText(aerolinea.getWeb());

				} catch (UsuarioNoExisteExce e4) {

				}
			}
		});
		getContentPane().add(btnSeleccionarAerolinea, gbcbtnSeleccionarAerolinea);

		lblNickname = new JLabel("Nickname:");
		lblNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNickname = new GridBagConstraints();
		gbclblNickname.anchor = GridBagConstraints.EAST;
		gbclblNickname.insets = new Insets(0, 0, 5, 5);
		gbclblNickname.gridx = 0;
		gbclblNickname.gridy = 4;
		getContentPane().add(lblNickname, gbclblNickname);

		textFieldNickname = new JTextField();
		textFieldNickname.setEditable(false);
		GridBagConstraints gbctextFieldNickname = new GridBagConstraints();
		gbctextFieldNickname.gridwidth = 2;
		gbctextFieldNickname.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNickname.fill = GridBagConstraints.BOTH;
		gbctextFieldNickname.gridx = 1;
		gbctextFieldNickname.gridy = 4;
		getContentPane().add(textFieldNickname, gbctextFieldNickname);
		textFieldNickname.setColumns(10);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNombre = new GridBagConstraints();
		gbclblNombre.anchor = GridBagConstraints.EAST;
		gbclblNombre.insets = new Insets(0, 0, 5, 5);
		gbclblNombre.gridx = 0;
		gbclblNombre.gridy = 5;
		getContentPane().add(lblNombre, gbclblNombre);

		textFieldNombre = new JTextField();
		GridBagConstraints gbctextFieldNombre = new GridBagConstraints();
		gbctextFieldNombre.gridwidth = 2;
		gbctextFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNombre.fill = GridBagConstraints.BOTH;
		gbctextFieldNombre.gridx = 1;
		gbctextFieldNombre.gridy = 5;
		getContentPane().add(textFieldNombre, gbctextFieldNombre);
		textFieldNombre.setColumns(10);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblApellido = new GridBagConstraints();
		gbclblApellido.anchor = GridBagConstraints.EAST;
		gbclblApellido.insets = new Insets(0, 0, 5, 5);
		gbclblApellido.gridx = 0;
		gbclblApellido.gridy = 6;
		getContentPane().add(lblApellido, gbclblApellido);

		lblIngreseNacimiento = new JLabel("Fecha de nacimiento:");
//		lblIngreseNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNacimiento = new GridBagConstraints();
		gbclblNacimiento.insets = new Insets(0, 0, 5, 5);
//		gbclblNacimiento.anchor = GridBagConstraints.EAST;
//		gbclblNacimiento.insets = new Insets(0, 0, 5, 5);
		gbclblNacimiento.gridx = 0;
		gbclblNacimiento.gridy = 13;
		lblIngreseNacimiento.setVisible(false);
		
		lblContraseña = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblContraseña = new GridBagConstraints();
		gbc_lblContraseña.anchor = GridBagConstraints.EAST;
		gbc_lblContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_lblContraseña.gridx = 0;
		gbc_lblContraseña.gridy = 8;
		getContentPane().add(lblContraseña, gbc_lblContraseña);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.gridwidth = 2;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 8;
		getContentPane().add(passwordField, gbc_passwordField);
		getContentPane().add(lblIngreseNacimiento, gbclblNacimiento);

		textDia = new JTextField();
		GridBagConstraints gbctextFieldDia = new GridBagConstraints();
		gbctextFieldDia.gridwidth = 10;
		gbctextFieldDia.insets = new Insets(0, 0, 5, 5);
		gbctextFieldDia.fill = GridBagConstraints.EAST;
		gbctextFieldDia.gridx = 1;
		gbctextFieldDia.gridy = 13;
		textDia.setVisible(true);
		getContentPane().add(textDia, gbctextFieldDia);
		textDia.setColumns(3);

		textMes = new JTextField();
		GridBagConstraints gbctextFieldMes = new GridBagConstraints();
		gbctextFieldMes.gridwidth = 10;
		gbctextFieldMes.insets = new Insets(0, 0, 5, 5);
		gbctextFieldMes.fill = GridBagConstraints.EAST;
		gbctextFieldMes.gridx = 2;
		gbctextFieldMes.gridy = 13;
		textMes.setVisible(true);
		getContentPane().add(textMes, gbctextFieldMes);
		textMes.setColumns(3);

		textAnio = new JTextField();
		GridBagConstraints gbctextFieldAnio = new GridBagConstraints();
		gbctextFieldAnio.gridwidth = 10;
		gbctextFieldAnio.insets = new Insets(0, 0, 5, 0);
		gbctextFieldAnio.fill = GridBagConstraints.EAST;
		gbctextFieldAnio.gridx = 3;
		gbctextFieldAnio.gridy = 13;
		textAnio.setVisible(true);
		getContentPane().add(textAnio, gbctextFieldAnio);
		textAnio.setColumns(3);

		textFieldApellido = new JTextField();
		GridBagConstraints gbctextFieldApellido = new GridBagConstraints();
		gbctextFieldApellido.gridwidth = 2;
		gbctextFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbctextFieldApellido.fill = GridBagConstraints.BOTH;
		gbctextFieldApellido.gridx = 1;
		gbctextFieldApellido.gridy = 6;
		getContentPane().add(textFieldApellido, gbctextFieldApellido);
		textFieldApellido.setColumns(10);

		lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblEmail = new GridBagConstraints();
		gbclblEmail.anchor = GridBagConstraints.EAST;
		gbclblEmail.insets = new Insets(0, 0, 5, 5);
		gbclblEmail.gridx = 0;
		gbclblEmail.gridy = 7;
		getContentPane().add(lblEmail, gbclblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		GridBagConstraints gbctextFieldEmail = new GridBagConstraints();
		gbctextFieldEmail.gridwidth = 2;
		gbctextFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbctextFieldEmail.fill = GridBagConstraints.BOTH;
		gbctextFieldEmail.gridx = 1;
		gbctextFieldEmail.gridy = 7;
		getContentPane().add(textFieldEmail, gbctextFieldEmail);
		textFieldEmail.setColumns(10);

		lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbclblTipo = new GridBagConstraints();
		gbclblTipo.anchor = GridBagConstraints.EAST;
		gbclblTipo.insets = new Insets(0, 0, 5, 5);
		gbclblTipo.gridx = 0;
		gbclblTipo.gridy = 9;
		getContentPane().add(lblTipo, gbclblTipo);

		textFieldTipo = new JTextField();
		textFieldTipo.setEditable(false);
		GridBagConstraints gbctextFieldTipo = new GridBagConstraints();
		gbctextFieldTipo.gridwidth = 2;
		gbctextFieldTipo.insets = new Insets(0, 0, 5, 5);
		gbctextFieldTipo.fill = GridBagConstraints.BOTH;
		gbctextFieldTipo.gridx = 1;
		gbctextFieldTipo.gridy = 9;
		getContentPane().add(textFieldTipo, gbctextFieldTipo);
		textFieldTipo.setColumns(10);

		lblNacionalidad = new JLabel("Nacionalidad");
		GridBagConstraints gbclblNacionalidad = new GridBagConstraints();
		gbclblNacionalidad.anchor = GridBagConstraints.EAST;
		gbclblNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbclblNacionalidad.gridx = 0;
		gbclblNacionalidad.gridy = 10;
		lblNacionalidad.setVisible(false);
		getContentPane().add(lblNacionalidad, gbclblNacionalidad);

		textFieldNacionalidad = new JTextField();
		GridBagConstraints gbctextFieldNacionalidad = new GridBagConstraints();
		gbctextFieldNacionalidad.gridwidth = 2;
		gbctextFieldNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNacionalidad.fill = GridBagConstraints.BOTH;
		gbctextFieldNacionalidad.gridx = 1;
		gbctextFieldNacionalidad.gridy = 10;
		textFieldNacionalidad.setVisible(false);
		getContentPane().add(textFieldNacionalidad, gbctextFieldNacionalidad);
		textFieldNacionalidad.setColumns(10);

		lblDescripcion = new JLabel("Descripcion:");
		GridBagConstraints gbclblDescripcion = new GridBagConstraints();
		gbclblDescripcion.anchor = GridBagConstraints.EAST;
		gbclblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbclblDescripcion.gridx = 0;
		gbclblDescripcion.gridy = 11;
		lblDescripcion.setVisible(false);
		getContentPane().add(lblDescripcion, gbclblDescripcion);

		textFieldDescripcion = new JTextField();
		GridBagConstraints gbctextFieldDescripcion = new GridBagConstraints();
		gbctextFieldDescripcion.gridwidth = 2;
		gbctextFieldDescripcion.insets = new Insets(0, 0, 5, 5);
		gbctextFieldDescripcion.fill = GridBagConstraints.BOTH;
		gbctextFieldDescripcion.gridx = 1;
		gbctextFieldDescripcion.gridy = 11;
		lblDescripcion.setVisible(false);
		getContentPane().add(textFieldDescripcion, gbctextFieldDescripcion);
		textFieldDescripcion.setColumns(10);

		lblLink = new JLabel("Link:");
		GridBagConstraints gbclblLink = new GridBagConstraints();
		gbclblLink.anchor = GridBagConstraints.EAST;
		gbclblLink.insets = new Insets(0, 0, 5, 5);
		gbclblLink.gridx = 0;
		gbclblLink.gridy = 12;
		lblLink.setVisible(false);
		getContentPane().add(lblLink, gbclblLink);

		textFieldLink = new JTextField();
		GridBagConstraints gbctextFieldLink = new GridBagConstraints();
		gbctextFieldLink.gridwidth = 2;
		gbctextFieldLink.insets = new Insets(0, 0, 5, 5);
		gbctextFieldLink.fill = GridBagConstraints.BOTH;
		gbctextFieldLink.gridx = 1;
		gbctextFieldLink.gridy = 12;
		textFieldLink.setVisible(false);
		getContentPane().add(textFieldLink, gbctextFieldLink);
		textFieldLink.setColumns(10);

		GridBagConstraints gbcbtnCerrar = new GridBagConstraints();
		gbcbtnCerrar.fill = GridBagConstraints.VERTICAL;
		gbcbtnCerrar.insets = new Insets(0, 0, 0, 5);
		gbcbtnCerrar.gridx = 1;
		gbcbtnCerrar.gridy = 15;
		getContentPane().add(btnCerrar, gbcbtnCerrar);

		btnConfirmar = new JButton("Confirmar");
		GridBagConstraints gbcbtnConfirmar = new GridBagConstraints();
		gbcbtnConfirmar.fill = GridBagConstraints.BOTH;
		gbcbtnConfirmar.insets = new Insets(0, 0, 0, 5);
		gbcbtnConfirmar.gridx = 2;
		gbcbtnConfirmar.gridy = 15;
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkFormulario()) {
					String diaStr = textDia.getText().trim();
					String mesStr = textMes.getText().trim();
					String anioStr = textAnio.getText().trim();

					int dia = Integer.parseInt(diaStr);
					int mes = Integer.parseInt(mesStr);
					int anio = Integer.parseInt(anioStr);

					LocalDate nacimientoU = LocalDate.of(anio, mes, dia);
					if (textFieldTipo.getText().equals("Cliente")) {
						try {
							controlUsr.modificarCliente(textFieldNickname.getText().trim(),
									textFieldNombre.getText().trim(), textFieldEmail.getText().trim(),
									textFieldApellido.getText().trim(), nacimientoU,
									textFieldNacionalidad.getText().trim(), "1", "1", passwordField.getPassword().toString(), "");
							JOptionPane.showMessageDialog(btnConfirmar, "El Usuario se ha modificado con éxito",
									"Modificar Usuario", JOptionPane.INFORMATION_MESSAGE);
						} catch (UsuarioNoExisteExce e5) {
							JOptionPane.showMessageDialog(btnConfirmar, e5.getMessage(), "Buscar usuario",
									JOptionPane.ERROR_MESSAGE);

						}

					} else if (textFieldTipo.getText().equals("Aerolinea")) {
						try {
							controlUsr.modificarAerolinea(textFieldNickname.getText().trim(),
									textFieldNombre.getText().trim(), textFieldEmail.getText().trim(),
									textFieldDescripcion.getText().trim(), textFieldLink.getText().trim(),passwordField.getPassword().toString(),"");
						} catch (UsuarioNoExisteExce e6) {
							JOptionPane.showMessageDialog(btnConfirmar, e6.getMessage(), "Buscar usuario",
									JOptionPane.ERROR_MESSAGE);

						}
					}

					limpiarFormulario();
					setVisible(false);
					JOptionPane.showMessageDialog(btnConfirmar, "Los datos se han modificado con éxito",
							"Modificar datos Usuario", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		getContentPane().add(btnConfirmar, gbcbtnConfirmar);

		comboBoxTipoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxTipoUsuario.getSelectedIndex() == 2) {
					try {
						limpiarFormulario();
						comboBoxTipoUsuario.setSelectedIndex(2);
						comboBoxAerolinea.removeAllItems();
						aerolineas = controlUsr.getAerolineas();
						for (int i = 0; i < aerolineas.length; i++) {
							comboBoxAerolinea.addItem(aerolineas[i].toString());
						}
						comboBoxAerolinea.setSelectedIndex(-1);
					} catch (UsuarioNoExisteExce e1) {

					}
					lblSeleccioneUnUsuario.setVisible(false);
					lblApellido.setVisible(false);
					textFieldApellido.setVisible(false);
					comboBoxCliente.setVisible(false);
					btnSeleccionarCliente.setVisible(false);
					lblSeleccioneUnaAerolinea.setVisible(true);
					comboBoxAerolinea.setVisible(true);
					btnSeleccionarAerolinea.setVisible(true);
					textFieldTipo.setText("Aerolinea");
					lblNacionalidad.setVisible(false);
					textFieldNacionalidad.setVisible(false);
					lblDescripcion.setVisible(true);
					textFieldDescripcion.setVisible(true);
					lblLink.setVisible(true);
					textFieldLink.setVisible(true);
					lblIngreseNacimiento.setVisible(false);
					textDia.setVisible(false);
					textMes.setVisible(false);
					textAnio.setVisible(false);

				} else if (comboBoxTipoUsuario.getSelectedIndex() == 1) {
					
					try {
						limpiarFormulario();
						comboBoxTipoUsuario.setSelectedIndex(1);
						comboBoxCliente.removeAllItems();
						clientes = controlUsr.getClientes();
						for (int i = 0; i < clientes.length; i++) {
							comboBoxCliente.addItem(clientes[i].toString());
						}
						comboBoxCliente.setSelectedIndex(-1);
					} catch (UsuarioNoExisteExce e1) {

					}
					lblSeleccioneUnUsuario.setVisible(true);
					comboBoxCliente.setVisible(true);
					btnSeleccionarCliente.setVisible(true);
					lblSeleccioneUnaAerolinea.setVisible(false);
					comboBoxAerolinea.setVisible(false);
					btnSeleccionarAerolinea.setVisible(false);
					textFieldTipo.setText("Cliente");
					lblNacionalidad.setVisible(true);

					textFieldNacionalidad.setVisible(true);
					lblDescripcion.setVisible(false);
					textFieldDescripcion.setVisible(false);
					lblLink.setVisible(false);
					textFieldLink.setVisible(false);

					lblIngreseNacimiento.setVisible(true);
					textDia.setVisible(true);
					textMes.setVisible(true);
					textAnio.setVisible(true);

				} else {
					lblSeleccioneUnUsuario.setVisible(false);
					comboBoxCliente.setVisible(false);
					btnSeleccionarCliente.setVisible(false);
					lblSeleccioneUnaAerolinea.setVisible(false);
					comboBoxAerolinea.setVisible(false);
					btnSeleccionarAerolinea.setVisible(false);
					lblNacionalidad.setVisible(false);
					textFieldNacionalidad.setVisible(false);
					lblDescripcion.setVisible(false);
					textFieldDescripcion.setVisible(false);
					lblLink.setVisible(false);
					textFieldLink.setVisible(false);

					lblIngreseNacimiento.setVisible(true);
					lblDia.setVisible(true);
					lblMes.setVisible(true);
					lblAnio.setVisible(true);
					textDia.setVisible(true);
					textMes.setVisible(true);
					textAnio.setVisible(true);
				}
			}
		});
	}

	private void limpiarFormulario() {
		comboBoxTipoUsuario.setSelectedIndex(0);
		textFieldNickname.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldEmail.setText("");
		textFieldTipo.setText("");
		textFieldNacionalidad.setText("");
		textFieldDescripcion.setText("");
		textFieldLink.setText("");
		textDia.setText("");
		textMes.setText("");
		textAnio.setText("");
	}

	private boolean checkFormulario() {
		if (textFieldNombre.getText() == "" || textFieldApellido.getText() == "") {
			JOptionPane.showMessageDialog(this, "Faltan datos basicos del usuario", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxTipoUsuario.getSelectedIndex() == 1
				&& (textFieldDescripcion.getText() == "" || textFieldLink.getText() == "")) {
			JOptionPane.showMessageDialog(this, "Faltan datos basicos del Aerolinea", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxTipoUsuario.getSelectedIndex() == 2 && textFieldNacionalidad.getText() == "") {
			JOptionPane.showMessageDialog(this, "Faltan datos basicos del Cliente", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxTipoUsuario.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Debe indicar el tipo de usuario", "Registrar Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarUsuario frame = new ModificarUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
