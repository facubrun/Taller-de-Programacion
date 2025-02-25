package ui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;

import exceptions.EmailRepetidoExce;
import exceptions.NicknameRepetidoExce;
import logica.Fabrica;
import logica.Interfaces.IUsuariosController;

public class CrearUsuario extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private IUsuariosController controlUsr;

	// comunes
	private JTextField textFieldNombre;
	private JLabel lblIngreseNombre;
	private JTextField textFieldEmail;
	private JLabel lblNickname;
	private JTextField textFieldNickname;

	// cliente
	private JTextField textFieldApellido;
	private JLabel lblIngreseApellido;
	private JLabel lblIngreseCi;
	private JLabel lblNacimiento;
	private JLabel lblTipo;
	private JLabel lblNacionalidad;
	private JTextField textFieldNacionalidad;

	private JLabel lblIngreseNacimiento;
	private JLabel lblDia;
	private JLabel lblMes;
	private JLabel lblAnio;
	private JTextField textDia;
	private JTextField textMes;
	private JTextField textAnio;

	private JLabel lblCI;
	private JTextField textCI;

	// aerolinea
	private JLabel lblDescripcion;
	private JTextField textFieldDescripcion;
	private JTextField textFieldLink;
	private JLabel lblLink;

	private JButton btnAceptar;
	private JButton btnCancelar;
	private JComboBox comboBox;
	private LocalDate fecha;
	
	private JComboBox tipodocu;
	private JLabel lblImagen;
	private JLabel lblContraseña;
	private JTextField textFieldContraseña;
	private JButton btnImagen;
	private JTextField textFieldRutaImagen;

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		limpiarFormulario();
		setVisible(false);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearUsuario frame = new CrearUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CrearUsuario() {
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
		setTitle("Registrar un Usuario");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(584, 502);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);

		Fabrica fabrica = Fabrica.getInstance();
		controlUsr = fabrica.getIControladorUsuario();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 120, 215, 215, 40, 0 };
		gridBagLayout.rowHeights = new int[] { 20, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		lblNickname = new JLabel("Nickname:");
		lblNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNickname = new GridBagConstraints();
		gbclblNickname.anchor = GridBagConstraints.EAST;
		gbclblNickname.insets = new Insets(0, 0, 5, 5);
		gbclblNickname.gridx = 0;
		gbclblNickname.gridy = 1;
		getContentPane().add(lblNickname, gbclblNickname);

		textFieldNickname = new JTextField();
		GridBagConstraints gbctextFieldNickname = new GridBagConstraints();
		gbctextFieldNickname.gridwidth = 2;
		gbctextFieldNickname.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNickname.fill = GridBagConstraints.BOTH;
		gbctextFieldNickname.gridx = 1;
		gbctextFieldNickname.gridy = 1;
		getContentPane().add(textFieldNickname, gbctextFieldNickname);
		textFieldNickname.setColumns(10);

		lblIngreseNombre = new JLabel("Nombre:");
		lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblIngreseNombre = new GridBagConstraints();
		gbclblIngreseNombre.fill = GridBagConstraints.BOTH;
		gbclblIngreseNombre.insets = new Insets(0, 0, 5, 5);
		gbclblIngreseNombre.gridx = 0;
		gbclblIngreseNombre.gridy = 2;
		getContentPane().add(lblIngreseNombre, gbclblIngreseNombre);

		textFieldNombre = new JTextField();
		GridBagConstraints gbctextFieldNombre = new GridBagConstraints();
		gbctextFieldNombre.gridwidth = 2;
		gbctextFieldNombre.fill = GridBagConstraints.BOTH;
		gbctextFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNombre.gridx = 1;
		gbctextFieldNombre.gridy = 2;
		getContentPane().add(textFieldNombre, gbctextFieldNombre);
		textFieldNombre.setColumns(10);

		lblIngreseCi = new JLabel("Email:");
		lblIngreseCi.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblIngreseCi = new GridBagConstraints();
		gbclblIngreseCi.fill = GridBagConstraints.BOTH;
		gbclblIngreseCi.insets = new Insets(0, 0, 5, 5);
		gbclblIngreseCi.gridx = 0;
		gbclblIngreseCi.gridy = 4;
		getContentPane().add(lblIngreseCi, gbclblIngreseCi);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdRegistroUsuarioActionPerformed(arg0);
			}
		});

		textFieldEmail = new JTextField();
		textFieldEmail.setToolTipText("Ingrese un número sin puntos ni guiones");
		textFieldEmail.setColumns(10);
		GridBagConstraints gbctextFieldEmail = new GridBagConstraints();
		gbctextFieldEmail.gridwidth = 2;
		gbctextFieldEmail.fill = GridBagConstraints.BOTH;
		gbctextFieldEmail.insets = new Insets(0, 0, 5, 5);
		gbctextFieldEmail.gridx = 1;
		gbctextFieldEmail.gridy = 4;
		getContentPane().add(textFieldEmail, gbctextFieldEmail);
		
		lblContraseña = new JLabel("Contraseña:");
		GridBagConstraints gbc_lblContraseña = new GridBagConstraints();
		gbc_lblContraseña.anchor = GridBagConstraints.EAST;
		gbc_lblContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_lblContraseña.gridx = 0;
		gbc_lblContraseña.gridy = 5;
		getContentPane().add(lblContraseña, gbc_lblContraseña);
		
		textFieldContraseña = new JPasswordField();
		GridBagConstraints gbc_textFieldContraseña = new GridBagConstraints();
		gbc_textFieldContraseña.gridwidth = 2;
		gbc_textFieldContraseña.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldContraseña.fill = GridBagConstraints.BOTH;
		gbc_textFieldContraseña.gridx = 1;
		gbc_textFieldContraseña.gridy = 5;
		getContentPane().add(textFieldContraseña, gbc_textFieldContraseña);
		textFieldContraseña.setColumns(10);
		
		lblImagen = new JLabel("Imagen:");
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.anchor = GridBagConstraints.EAST;
		gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagen.gridx = 0;
		gbc_lblImagen.gridy = 6;
		getContentPane().add(lblImagen, gbc_lblImagen);
		
		btnImagen = new JButton("Seleccionar imagen");
		GridBagConstraints gbc_btnImagen = new GridBagConstraints();
		gbc_btnImagen.fill = GridBagConstraints.BOTH;
		gbc_btnImagen.gridwidth = 2;
		gbc_btnImagen.insets = new Insets(0, 0, 5, 5);
		gbc_btnImagen.gridx = 1;
		gbc_btnImagen.gridy = 6;
		getContentPane().add(btnImagen, gbc_btnImagen);
		btnImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionarImagen();
			}
		});
		
		textFieldRutaImagen = new JTextField();
		textFieldRutaImagen.setEditable(false);
		GridBagConstraints gbc_textFieldRutaImagen = new GridBagConstraints();
		gbc_textFieldRutaImagen.gridwidth = 2;
		gbc_textFieldRutaImagen.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldRutaImagen.fill = GridBagConstraints.BOTH;
		gbc_textFieldRutaImagen.gridx = 1;
		gbc_textFieldRutaImagen.gridy = 7;
		getContentPane().add(textFieldRutaImagen, gbc_textFieldRutaImagen);
		textFieldRutaImagen.setColumns(10);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblTipo = new GridBagConstraints();
		gbclblTipo.anchor = GridBagConstraints.EAST;
		gbclblTipo.insets = new Insets(0, 0, 5, 5);
		gbclblTipo.gridx = 0;
		gbclblTipo.gridy = 8;
		getContentPane().add(lblTipo, gbclblTipo);

		comboBox = new JComboBox();
		comboBox.addItem("Seleccione...");
		comboBox.addItem("Cliente");
		comboBox.addItem("Aerolinea");
		comboBox.setSelectedIndex(0);
		comboBox.setMaximumRowCount(3);
		GridBagConstraints gbccomboBox = new GridBagConstraints();
		gbccomboBox.gridwidth = 2;
		gbccomboBox.insets = new Insets(0, 0, 5, 5);
		gbccomboBox.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBox.gridx = 1;
		gbccomboBox.gridy = 8;
		getContentPane().add(comboBox, gbccomboBox);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedIndex() == 1) {
					lblNacionalidad.setVisible(true);
					textFieldNacionalidad.setVisible(true);
					lblIngreseApellido.setVisible(true);
					textFieldApellido.setVisible(true);
					lblIngreseNacimiento.setVisible(true);
					textDia.setVisible(true);
					textMes.setVisible(true);
					textAnio.setVisible(true);
					lblCI.setVisible(true);
					textCI.setVisible(true);
					tipodocu.setVisible(true);

					lblDescripcion.setVisible(false);
					textFieldDescripcion.setVisible(false);
					lblLink.setVisible(false);
					textFieldLink.setVisible(false);
				} else if (comboBox.getSelectedIndex() == 2) {
					lblNacionalidad.setVisible(false);
					textFieldNacionalidad.setVisible(false);
					lblIngreseApellido.setVisible(false);
					textFieldApellido.setVisible(false);
					lblIngreseNacimiento.setVisible(false);
					textDia.setVisible(false);
					textMes.setVisible(false);
					textAnio.setVisible(false);
					lblDescripcion.setVisible(true);
					textFieldDescripcion.setVisible(true);
					lblLink.setVisible(true);
					textFieldLink.setVisible(true);
					lblCI.setVisible(false);
					textCI.setVisible(false);
					tipodocu.setVisible(false);
				} else {
					lblNacionalidad.setVisible(false);
					textFieldNacionalidad.setVisible(false);
					lblDescripcion.setVisible(false);
					textFieldDescripcion.setVisible(false);
					lblLink.setVisible(false);
					textFieldLink.setVisible(false);
					lblCI.setVisible(false);
					textCI.setVisible(false);
					lblIngreseApellido.setVisible(false);
					textDia.setVisible(false);
					textMes.setVisible(false);
					textAnio.setVisible(false);
					lblIngreseNacimiento.setVisible(false);
					tipodocu.setVisible(false);
				}
			}
		});

		lblIngreseApellido = new JLabel("Apellido:");
		lblIngreseApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblApellido = new GridBagConstraints();
		gbclblApellido.anchor = GridBagConstraints.EAST;
		gbclblApellido.insets = new Insets(0, 0, 5, 5);
		gbclblApellido.gridx = 0;
		gbclblApellido.gridy = 9;
		lblIngreseApellido.setVisible(false);
		getContentPane().add(lblIngreseApellido, gbclblApellido);

		textFieldApellido = new JTextField();
		GridBagConstraints gbctextFieldApellido = new GridBagConstraints();
		gbctextFieldApellido.gridwidth = 2;
		gbctextFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbctextFieldApellido.fill = GridBagConstraints.BOTH;
		gbctextFieldApellido.gridx = 1;
		gbctextFieldApellido.gridy = 9;
		textFieldApellido.setVisible(false);
		getContentPane().add(textFieldApellido, gbctextFieldApellido);
		textFieldApellido.setColumns(10);

		lblIngreseNacimiento = new JLabel("Fecha de nacimiento:");
//		lblIngreseNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNacimiento = new GridBagConstraints();
		gbclblNacimiento.insets = new Insets(0, 0, 5, 5);
//		gbclblNacimiento.anchor = GridBagConstraints.EAST;
//		gbclblNacimiento.insets = new Insets(0, 0, 5, 5);
		gbclblNacimiento.gridx = 0;
		gbclblNacimiento.gridy = 11;
		lblIngreseNacimiento.setVisible(false);
		getContentPane().add(lblIngreseNacimiento, gbclblNacimiento);

		textDia = new JTextField();
		GridBagConstraints gbctextFieldDia = new GridBagConstraints();
		gbctextFieldDia.gridwidth = 10;
		gbctextFieldDia.insets = new Insets(0, 0, 5, 5);
		gbctextFieldDia.fill = GridBagConstraints.EAST;
		gbctextFieldDia.gridx = 1;
		gbctextFieldDia.gridy = 11;
		textDia.setVisible(false);
		getContentPane().add(textDia, gbctextFieldDia);
		textDia.setColumns(3);

		textMes = new JTextField();
		GridBagConstraints gbctextFieldMes = new GridBagConstraints();
		gbctextFieldMes.gridwidth = 10;
		gbctextFieldMes.insets = new Insets(0, 0, 5, 5);
		gbctextFieldMes.fill = GridBagConstraints.EAST;
		gbctextFieldMes.gridx = 2;
		gbctextFieldMes.gridy = 11;
		textMes.setVisible(false);
		getContentPane().add(textMes, gbctextFieldMes);
		textMes.setColumns(3);

		textAnio = new JTextField();
		GridBagConstraints gbctextFieldAnio = new GridBagConstraints();
		gbctextFieldAnio.gridwidth = 10;
		gbctextFieldAnio.insets = new Insets(0, 0, 5, 0);
		gbctextFieldAnio.fill = GridBagConstraints.EAST;
		gbctextFieldAnio.gridx = 3;
		gbctextFieldAnio.gridy = 11;
		textAnio.setVisible(false);
		getContentPane().add(textAnio, gbctextFieldAnio);
		textAnio.setColumns(3);

		lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblNacionalidad = new GridBagConstraints();
		gbclblNacionalidad.anchor = GridBagConstraints.EAST;
		gbclblNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbclblNacionalidad.gridx = 0;
		gbclblNacionalidad.gridy = 12;
		lblNacionalidad.setVisible(false);
		getContentPane().add(lblNacionalidad, gbclblNacionalidad);

		textFieldNacionalidad = new JTextField();
		GridBagConstraints gbctextFieldNacionalidad = new GridBagConstraints();
		gbctextFieldNacionalidad.gridwidth = 2;
		gbctextFieldNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbctextFieldNacionalidad.fill = GridBagConstraints.BOTH;
		gbctextFieldNacionalidad.gridx = 1;
		gbctextFieldNacionalidad.gridy = 12;
		textFieldNacionalidad.setVisible(false);
		getContentPane().add(textFieldNacionalidad, gbctextFieldNacionalidad);
		textFieldNacionalidad.setColumns(10);

		tipodocu = new JComboBox();
		tipodocu.addItem("Pasaporte");
		tipodocu.addItem("CI");
		tipodocu.setSelectedIndex(0);
		tipodocu.setMaximumRowCount(3);
		GridBagConstraints gbccomboBoxtipodocu = new GridBagConstraints();
		gbccomboBoxtipodocu.gridwidth = 2;
		gbccomboBoxtipodocu.insets = new Insets(0, 0, 5, 5);
		gbccomboBoxtipodocu.fill = GridBagConstraints.HORIZONTAL;
		gbccomboBoxtipodocu.gridx = 0;
		gbccomboBoxtipodocu.gridy = 10;
		tipodocu.setVisible(false);
		getContentPane().add(tipodocu, gbccomboBoxtipodocu);
		
		lblCI = new JLabel("Nro. doc:");
		lblCI.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblCI = new GridBagConstraints();
		gbclblCI.anchor = GridBagConstraints.EAST;
		gbclblCI.insets = new Insets(0, 0, 5, 5);
		gbclblCI.gridx = 1;
		gbclblCI.gridy = 10;
		lblCI.setVisible(false);
		getContentPane().add(lblCI, gbclblCI);

		textCI = new JTextField();
		textCI.setEditable(true);
		GridBagConstraints gbctextFieldCI = new GridBagConstraints();
		gbctextFieldCI.gridwidth = 3;
		gbctextFieldCI.insets = new Insets(0, 0, 5, 5);
		gbctextFieldCI.fill = GridBagConstraints.HORIZONTAL;
		gbctextFieldCI.gridx = 2;
		gbctextFieldCI.gridy = 10;
		textCI.setVisible(false);
		getContentPane().add(textCI, gbctextFieldCI);
		textCI.setColumns(10);

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
		GridBagConstraints gbctextFieldDescripcion = new GridBagConstraints();
		gbctextFieldDescripcion.gridwidth = 2;
		gbctextFieldDescripcion.insets = new Insets(0, 0, 5, 5);
		gbctextFieldDescripcion.fill = GridBagConstraints.BOTH;
		gbctextFieldDescripcion.gridx = 1;
		gbctextFieldDescripcion.gridy = 10;
		textFieldDescripcion.setVisible(false);
		getContentPane().add(textFieldDescripcion, gbctextFieldDescripcion);
		textFieldDescripcion.setColumns(10);

		lblLink = new JLabel("Link:");
		lblLink.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbclblLink = new GridBagConstraints();
		gbclblLink.insets = new Insets(0, 0, 5, 5);
		gbclblLink.anchor = GridBagConstraints.EAST;
		gbclblLink.gridx = 0;
		gbclblLink.gridy = 11;
		lblLink.setVisible(false);
		getContentPane().add(lblLink, gbclblLink);

		textFieldLink = new JTextField();
		GridBagConstraints gbctextFieldLink = new GridBagConstraints();
		gbctextFieldLink.gridwidth = 2;
		gbctextFieldLink.insets = new Insets(0, 0, 5, 5);
		gbctextFieldLink.fill = GridBagConstraints.BOTH;
		gbctextFieldLink.gridx = 1;
		gbctextFieldLink.gridy = 11;
		textFieldLink.setVisible(false);
		getContentPane().add(textFieldLink, gbctextFieldLink);
		textFieldLink.setColumns(10);

		GridBagConstraints gbcbtnAceptar = new GridBagConstraints();
		gbcbtnAceptar.fill = GridBagConstraints.BOTH;
		gbcbtnAceptar.insets = new Insets(0, 0, 0, 5);
		gbcbtnAceptar.gridx = 1;
		gbcbtnAceptar.gridy = 14;
		getContentPane().add(btnAceptar, gbcbtnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		GridBagConstraints gbcbtnCancelar = new GridBagConstraints();
		gbcbtnCancelar.insets = new Insets(0, 0, 0, 5);
		gbcbtnCancelar.fill = GridBagConstraints.BOTH;
		gbcbtnCancelar.gridx = 2;
		gbcbtnCancelar.gridy = 14;
		getContentPane().add(btnCancelar, gbcbtnCancelar);
	}

	protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) {
		String nicknameU = this.textFieldNickname.getText().trim();
		String nombreU = this.textFieldNombre.getText().trim();
		String apellidoU = this.textFieldApellido.getText().trim();
		String emailU = this.textFieldEmail.getText().trim();
		String passwordU = this.textFieldContraseña.getText().trim();
		String imagenU = this.textFieldRutaImagen.getText().trim();
		@SuppressWarnings("deprecation")
		LocalDate nacimientoU = null;

		if (nicknameU.isEmpty() || nombreU.isEmpty() || emailU.isEmpty() || passwordU.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar usuario",
					JOptionPane.ERROR_MESSAGE);
		} else {
			if (comboBox.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this, "Debe indicar qué tipo de usuario desea registrar",
						"Registrar Usuario", JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					if (comboBox.getSelectedIndex() == 1) {
						String nacionalidadT = this.textFieldNacionalidad.getText();
						try {
							String diaStr = this.textDia.getText().trim();
							String mesStr = this.textMes.getText().trim();
							String anioStr = this.textAnio.getText().trim();

							int dia = Integer.parseInt(diaStr);
							int mes = Integer.parseInt(mesStr);
							int anio = Integer.parseInt(anioStr);

							nacimientoU = LocalDate.of(anio, mes, dia);
							
							
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(this,
									"Por favor, ingresa valores numéricos válidos para la fecha.");
						}
						if (nacionalidadT.isEmpty()) {
							JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar usuario",
									JOptionPane.ERROR_MESSAGE);
						} if(!emailU.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
							JOptionPane.showMessageDialog(this, "Correo invalido", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
						}else {
							if (nacimientoU != null) {
								String documentoU = null;
								if (textCI.getText().trim() != null) {
									documentoU = textCI.getText().trim();
								} else {
									JOptionPane.showMessageDialog(this, "Documento inválida", "Registrar usuario",
											JOptionPane.ERROR_MESSAGE);
								}
								String aer = this.tipodocu.getSelectedItem().toString();
								aer = aer.split(" ", 2)[0];
								
								
								controlUsr.crearCliente(nicknameU, nombreU, emailU, apellidoU, nacimientoU,
										nacionalidadT, aer, documentoU, passwordU, imagenU, new ArrayList<>(), new ArrayList<>());

								JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
										"Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
								limpiarFormulario();
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(this, "Fecha de nacimiento inválida", "Registrar usuario",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					} else if (comboBox.getSelectedIndex() == 2) {
						String descripcionP = this.textFieldDescripcion.getText();
						String linkP = this.textFieldLink.getText();
						if (descripcionP.isEmpty() || linkP.isEmpty()) {
							JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar usuario",
									JOptionPane.ERROR_MESSAGE);
						} if(!emailU.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
							JOptionPane.showMessageDialog(this, "Correo invalido");
						} else if(!linkP.matches("^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-])\\/?$")) {
							JOptionPane.showMessageDialog(this, "Sitio Web invalido", "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
						}	else {
							
							controlUsr.crearAerolinea(nicknameU, nombreU, emailU, descripcionP, linkP, passwordU, imagenU,new ArrayList<>(), new ArrayList<>());
							JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
									"Registrar Usuario", JOptionPane.INFORMATION_MESSAGE);
							limpiarFormulario();
							setVisible(false);
						}
					}

				} catch (EmailRepetidoExce e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
					textFieldEmail.setText("");
				} catch (NicknameRepetidoExce e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
					textFieldNickname.setText("");
				}
			}
		}

	}
	
	protected void seleccionarImagen() {
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}
				String extension = obtenerExtension(f);
                return extension != null && (extension.equals("png") || extension.equals("jpg") || extension.equals("jpeg") || extension.equals("gif"));
			}
			
			public String getDescription() {
                return "Imágenes (.png, .jpg, .jpeg, .gif)";
            }
			
			
		});
		
		int resultado = fileChooser.showOpenDialog(this);
        
        // Si el usuario seleccionó una imagen
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            String rutaImagen = archivoSeleccionado.getAbsolutePath();
            textFieldRutaImagen.setText(rutaImagen);
        }
	}
	
	private String obtenerExtension(File f) {
        String nombreArchivo = f.getName();
        int i = nombreArchivo.lastIndexOf('.');
        if (i > 0 && i < nombreArchivo.length() - 1) {
            return nombreArchivo.substring(i + 1).toLowerCase();
        }
        return null;
    }

	private void limpiarFormulario() {
		textFieldNickname.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldEmail.setText("");
		comboBox.setSelectedIndex(-1);
		textFieldNacionalidad.setText("");
		textFieldDescripcion.setText("");
		textFieldLink.setText("");
	}

}
