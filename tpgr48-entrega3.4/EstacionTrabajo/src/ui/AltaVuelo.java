package ui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;

import exceptions.NombreVRepetidoExce;
import exceptions.RVNoExisteExce;
import exceptions.UsuarioNoExisteExce;
import logica.Fabrica;
import logica.Datatypes.DataAerolinea;
import logica.Datatypes.DataRutaVuelo;
import logica.Interfaces.IVuelosController;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;

import javax.swing.JPanel;

import logica.clases.RutaVuelo;

public class AltaVuelo extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private IVuelosController controlV;
	private IRutaVueloController controlRV;
	private IUsuariosController controlU;

	private JLabel lblAerolinea;
	private JLabel lblNombre;
	private JLabel lblRutadeVuelo;
	private JLabel lblDuracion;
	private JLabel lblFecha;
	private JLabel lblTurista;
	private JLabel lblEjecutivo;
	private JLabel lblFechaAlta;
	private JComboBox<String> comboBoxAerolinea;
	private JComboBox<String> comboBoxRutaVuelo;
	private JTextField textFieldNombre;
	private JPanel panel_1;
	private JTextField textFieldDia;
	private JTextField textFieldMes;
	private JTextField textFieldAnio;
	private JTextField textFieldTurista;
	private JTextField textFieldEjecutivo;
	private JPanel panel_2;
	private JTextField textFieldDiaAlta;
	private JTextField textFieldMesAlta;
	private JTextField textFieldAnioAlta;
	private JPanel panel_3;
	private JButton btnAceptar;
	private JButton btnCancelar;

	DataAerolinea[] aerolineas;
	ArrayList<DataRutaVuelo> rutas;
	private JPanel panel_4;
	private JTextField textFieldHora;
	private JTextField textFieldMin;

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
					AltaVuelo frame = new AltaVuelo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AltaVuelo() {
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

		Fabrica fabrica = Fabrica.getInstance();
		controlV = fabrica.getIControladorVuelos();
		controlRV = fabrica.getIControladorRutaVuelo();
		controlU = fabrica.getIControladorUsuario();

		try {
			aerolineas = controlU.getAerolineas();
		} catch (UsuarioNoExisteExce e) {
			aerolineas = new DataAerolinea[0];
		}

		setTitle("Registrar un Vuelo");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 2, myScreen.getScreenSize().height / 2);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150, 180 };
		gridBagLayout.rowHeights = new int[] { 40, 40, 10, 40, 40, 40, 40, 40, 40, 40 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0 };
		getContentPane().setLayout(gridBagLayout);

		lblAerolinea = new JLabel("Aerolinea:");
		GridBagConstraints gbc_lblAerolinea = new GridBagConstraints();
		gbc_lblAerolinea.anchor = GridBagConstraints.EAST;
		gbc_lblAerolinea.insets = new Insets(0, 0, 5, 5);
		gbc_lblAerolinea.gridx = 0;
		gbc_lblAerolinea.gridy = 0;
		getContentPane().add(lblAerolinea, gbc_lblAerolinea);

		comboBoxAerolinea = new JComboBox<>();
		GridBagConstraints gbc_comboBoxAerolinea = new GridBagConstraints();
		gbc_comboBoxAerolinea.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxAerolinea.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAerolinea.gridx = 1;
		gbc_comboBoxAerolinea.gridy = 0;
		getContentPane().add(comboBoxAerolinea, gbc_comboBoxAerolinea);

		lblRutadeVuelo = new JLabel("Ruta de Vuelo:");
		GridBagConstraints gbc_lblRutadeVuelo = new GridBagConstraints();
		gbc_lblRutadeVuelo.anchor = GridBagConstraints.EAST;
		gbc_lblRutadeVuelo.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutadeVuelo.gridx = 0;
		gbc_lblRutadeVuelo.gridy = 1;
		lblRutadeVuelo.setVisible(false);
		getContentPane().add(lblRutadeVuelo, gbc_lblRutadeVuelo);

		comboBoxRutaVuelo = new JComboBox<>();
		GridBagConstraints gbc_comboBoxRutaVuelo = new GridBagConstraints();
		gbc_comboBoxRutaVuelo.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxRutaVuelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxRutaVuelo.gridx = 1;
		gbc_comboBoxRutaVuelo.gridy = 1;
		comboBoxRutaVuelo.setVisible(false);
		comboBoxRutaVuelo.setMaximumRowCount(3);
		getContentPane().add(comboBoxRutaVuelo, gbc_comboBoxRutaVuelo);

		lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 3;
		getContentPane().add(lblNombre, gbc_lblNombre);

		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 3;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		lblFecha = new JLabel("Fecha:(dd/MM/yyyy):");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 4;
		getContentPane().add(lblFecha, gbc_lblFecha);

		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 4;
		getContentPane().add(panel_1, gbc_panel_1);

		textFieldDia = new JTextField();
		panel_1.add(textFieldDia);
		textFieldDia.setColumns(10);

		textFieldMes = new JTextField();
		panel_1.add(textFieldMes);
		textFieldMes.setColumns(10);

		textFieldAnio = new JTextField();
		panel_1.add(textFieldAnio);
		textFieldAnio.setColumns(10);

		lblDuracion = new JLabel("Duracion: (hh/mm)");
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
		gbc_lblDuracion.anchor = GridBagConstraints.EAST;
		gbc_lblDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracion.gridx = 0;
		gbc_lblDuracion.gridy = 5;
		getContentPane().add(lblDuracion, gbc_lblDuracion);

		panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 1;
		gbc_panel_4.gridy = 5;
		getContentPane().add(panel_4, gbc_panel_4);

		textFieldHora = new JTextField();
		panel_4.add(textFieldHora);
		textFieldHora.setColumns(10);

		textFieldMin = new JTextField();
		panel_4.add(textFieldMin);
		textFieldMin.setColumns(10);

		lblTurista = new JLabel("Cantidad máxima de asientos turista:");
		GridBagConstraints gbc_lblTurista = new GridBagConstraints();
		gbc_lblTurista.anchor = GridBagConstraints.EAST;
		gbc_lblTurista.insets = new Insets(0, 0, 5, 5);
		gbc_lblTurista.gridx = 0;
		gbc_lblTurista.gridy = 6;
		getContentPane().add(lblTurista, gbc_lblTurista);

		textFieldTurista = new JTextField();
		GridBagConstraints gbc_textFieldTurista = new GridBagConstraints();
		gbc_textFieldTurista.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldTurista.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTurista.gridx = 1;
		gbc_textFieldTurista.gridy = 6;
		getContentPane().add(textFieldTurista, gbc_textFieldTurista);
		textFieldTurista.setColumns(10);

		lblEjecutivo = new JLabel("Cantidad máxima de asientos ejecutivo:");
		GridBagConstraints gbc_lblEjecutivo = new GridBagConstraints();
		gbc_lblEjecutivo.anchor = GridBagConstraints.EAST;
		gbc_lblEjecutivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblEjecutivo.gridx = 0;
		gbc_lblEjecutivo.gridy = 7;
		getContentPane().add(lblEjecutivo, gbc_lblEjecutivo);

		textFieldEjecutivo = new JTextField();
		GridBagConstraints gbc_textFieldEjecutivo = new GridBagConstraints();
		gbc_textFieldEjecutivo.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldEjecutivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEjecutivo.gridx = 1;
		gbc_textFieldEjecutivo.gridy = 7;
		getContentPane().add(textFieldEjecutivo, gbc_textFieldEjecutivo);
		textFieldEjecutivo.setColumns(10);

		lblFechaAlta = new JLabel("Fecha de Alta: (dd/M/yyyy))");
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.gridx = 0;
		gbc_lblFechaAlta.gridy = 8;
		getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);

		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 8;
		getContentPane().add(panel_2, gbc_panel_2);

		textFieldDiaAlta = new JTextField();
		panel_2.add(textFieldDiaAlta);
		textFieldDiaAlta.setColumns(10);

		textFieldMesAlta = new JTextField();
		panel_2.add(textFieldMesAlta);
		textFieldMesAlta.setColumns(10);

		textFieldAnioAlta = new JTextField();
		panel_2.add(textFieldAnioAlta);
		textFieldAnioAlta.setColumns(10);

		panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 9;
		getContentPane().add(panel_3, gbc_panel_3);

		btnAceptar = new JButton("Aceptar");
		panel_3.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				registrarDatosVuelo(event);
			}
		});

		btnCancelar = new JButton("Cancelar");
		panel_3.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event1) {
				limpiarFormulario();
				setVisible(false);
			}
		});

		comboBoxAerolinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxAerolinea.getSelectedItem() != null) {
					String aero = comboBoxAerolinea.getSelectedItem().toString();
					try {
						aero = aero.split(" ", 2)[0];
						comboBoxRutaVuelo.removeAllItems();
						rutas = controlRV.listarRutasVueloPorAerolinea(aero);
						for (DataRutaVuelo ruta : rutas) {
							comboBoxRutaVuelo.addItem(ruta.toString());
						}
						lblRutadeVuelo.setVisible(true);
						comboBoxRutaVuelo.setVisible(true);
					} catch (UsuarioNoExisteExce e1) {

					}
				}
			}
		});

	}

	protected void actualizarAerolineas() {
		try {
			comboBoxAerolinea.removeAllItems();
			aerolineas = controlU.getAerolineas();
			for (int i = 0; i < aerolineas.length; i++) {
				comboBoxAerolinea.addItem(aerolineas[i].toString());
			}
		} catch (UsuarioNoExisteExce e) {
		}
	}

	protected void registrarDatosVuelo(ActionEvent event) {
		if (this.comboBoxAerolinea.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "No hay ninguna aerolinea seleccionada");
		} else if (this.comboBoxRutaVuelo.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "No hay ninguna ruta de vuelo seleccionada");
		} else {
			String nombre = this.textFieldNombre.getText().trim();
			String dia = this.textFieldDia.getText().trim();
			String mes = this.textFieldMes.getText().trim();
			String anio = this.textFieldAnio.getText().trim();
			String diaAlta = this.textFieldDiaAlta.getText().trim();
			String mesAlta = this.textFieldMesAlta.getText().trim();
			String anioAlta = this.textFieldAnioAlta.getText().trim();
			String hora = this.textFieldHora.getText().trim();
			String min = this.textFieldMin.getText().trim();
			String turista = this.textFieldTurista.getText().trim();
			String ejecutivo = this.textFieldEjecutivo.getText().trim();
			String ruta = this.comboBoxRutaVuelo.getSelectedItem().toString();
			ruta = ruta.split(" ", 2)[0];
			RutaVuelo rutaInstancia = controlRV.obtenerRutaVuelo(ruta);

			if (nombre.isEmpty() || dia.isEmpty() || mes.isEmpty() || anio.isEmpty() || diaAlta.isEmpty()
					|| mesAlta.isEmpty() || anioAlta.isEmpty() || min.isEmpty() || hora.isEmpty() || turista.isEmpty()
					|| ejecutivo.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta de Ciudad",
						JOptionPane.ERROR_MESSAGE);
			} else {
				try {
					try {
						LocalDate.of(Integer.parseInt(anio), Integer.parseInt(mes), Integer.parseInt(dia));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(this,
								"Por favor, ingresa valores numéricos válidos para la fecha.");
					} catch (DateTimeException e) {
						JOptionPane.showMessageDialog(this,
								"Por favor, ingresa valores numéricos válidos para la fecha.");
					}
					try {
						LocalDate.of(Integer.parseInt(anioAlta), Integer.parseInt(mesAlta), Integer.parseInt(diaAlta));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(this,
								"Por favor, ingresa valores numéricos válidos para la fecha.");
					} catch (DateTimeException e) {
						JOptionPane.showMessageDialog(this,
								"Por favor, ingresa valores numéricos válidos para la fecha.");
					}
					try {
						LocalTime.of(Integer.parseInt(hora), Integer.parseInt(min));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(this,
								"Por favor, ingresa valores numéricos válidos para la duracion.");
					} catch(DateTimeException e) {
						JOptionPane.showMessageDialog(this,
								"Por favor, ingresa valores numéricos válidos para la duracion.");
					}
					try {
						Integer.parseInt(turista);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(this,
								"Por favor, ingresa valores numéricos válidos para la cantidad maxima de asientos turista.");
					}
					try {
						Integer.parseInt(ejecutivo);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(this,
								"Por favor, ingresa valores numéricos válidos para la cantidad maxima de asientos ejecutivo.");
					}
					LocalDate fecha = LocalDate.of(Integer.parseInt(anio), Integer.parseInt(mes),
							Integer.parseInt(dia));
					LocalDate fechaAlta = LocalDate.of(Integer.parseInt(anioAlta), Integer.parseInt(mesAlta),
							Integer.parseInt(diaAlta));
					LocalTime duracion = LocalTime.of(Integer.parseInt(hora), Integer.parseInt(min));
					int tur = Integer.parseInt(turista);
					int eje = Integer.parseInt(ejecutivo);
					if ( fecha.isBefore(fechaAlta)) {
						JOptionPane.showMessageDialog(this, "La fecha del vuelo no es valida");
					} else if (fechaAlta.isBefore(rutaInstancia.getFechaAlta())) {
						JOptionPane.showMessageDialog(this, "La fecha de alta del vuelo es anterior a la fecha de alta de la ruta");
					} else if (!nombre.matches("^[a-zA-Z0-9]+$")) {
						JOptionPane.showMessageDialog(this, "El nombre del vuelo es invalido");
					}else if(tur<0 || eje<0){
						JOptionPane.showMessageDialog(this,
								"Por favor, ingresa valores numéricos válidos para la cantidad maxima de asientos ejecutivo y turistas.");
					} else {
						controlV.ingresarDatosVuelo(ruta, nombre, fecha, duracion, tur, eje, fechaAlta);
						JOptionPane.showMessageDialog(this, "El alta de Vuelo se ejecuto con exito", "Alta de Vuelo",
								JOptionPane.INFORMATION_MESSAGE);
						limpiarFormulario();
						setVisible(false);
					}
				} catch (NombreVRepetidoExce e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Vuelo", JOptionPane.ERROR_MESSAGE);
					textFieldNombre.setText("");
				} catch (RVNoExisteExce e) {
					JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Vuelo", JOptionPane.ERROR_MESSAGE);
					textFieldNombre.setText("");
				}
			}
		}
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldDia.setText("");
		textFieldMes.setText("");
		textFieldAnio.setText("");
		textFieldDiaAlta.setText("");
		textFieldMesAlta.setText("");
		textFieldAnioAlta.setText("");
		textFieldHora.setText("");
		textFieldMin.setText("");
		textFieldTurista.setText("");
		textFieldEjecutivo.setText("");
		comboBoxAerolinea.setSelectedIndex(-1);
		comboBoxRutaVuelo.setSelectedIndex(-1);
		lblRutadeVuelo.setVisible(false);
		comboBoxRutaVuelo.setVisible(false);
	}
}
