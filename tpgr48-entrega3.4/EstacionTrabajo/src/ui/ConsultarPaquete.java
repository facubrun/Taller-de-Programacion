package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptions.UsuarioNoExisteExce;
import exceptions.RVNoExisteExce;
import exceptions.VueloNoExisteExce;
import java.util.ArrayList;
import logica.Fabrica;
import logica.Datatypes.DataAerolinea;
import logica.Datatypes.DataPaquete;
import logica.Datatypes.DataReserva;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataRutaVueloPaquete;
import logica.Datatypes.DataVuelo;
import logica.Datatypes.tipoAsiento;
import logica.Interfaces.IPaqueteController;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.Interfaces.IVuelosController;
import logica.clases.Paquete;
import logica.clases.RutaVuelo;

public class ConsultarPaquete extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JComboBox comboPaquetes;
	private JComboBox comboBoxRutasVuelo;

	Fabrica fabrica = Fabrica.getInstance();
	IPaqueteController ctrlPaquete = fabrica.getIControladorPaquete();
	IRutaVueloController ctrlRutaVuelo = fabrica.getIControladorRutaVuelo();
	ArrayList<DataPaquete> paquetes = new ArrayList<>();
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	
	private JLabel lblAerolinea;
	private JLabel lblRutasVuelo;
	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JLabel lblDescripcion;
	private JTextField textFieldDescripcion;
	private JLabel lblHora;
	private JTextField textFieldHora;
	private JLabel lblFechaAlta;
	private JTextField textFieldFechaAlta;
	private JLabel lblCiudadOrigen;
	private JTextField textFieldCiudadOrigen;
	private JLabel lblCiudadDestino;
	private JTextField textFieldCiudadDestino;

	private JLabel lblCostoEjec;
	private JTextField textFieldCostoEjec;
	private JLabel lblCostoTurista;
	private JTextField textFieldCostoTurista;
	private JLabel lblCostoExt;
	private JTextField textFieldCostoExt;
	private JLabel lblCategorias;
	private JTextField textFieldCategorias;
	private JLabel lblEstado;
	private JTextField textFieldEstado;
	private JLabel lblDescCorta;
	private JTextField textFieldDescCorta;
	private JLabel lblVuelos;
	private JButton btnCancelar;
	
	private JLabel lblNombreVuelo;
	private JTextField nombreVuelo;
	private JLabel lblFechaVuelo;
	private JTextField fechaVuelo;
	private JLabel lblDuracionVuelo;
	private JTextField duracionVuelo;
	private JLabel lblCapTurVuelo;
	private JTextField capacidadTVuelo;
	private JLabel lblCapEjecVuelo;
	private JTextField capacidadEVuelo;
	private JLabel lblFechaAltaVuelo;
	private JTextField fechaAltaVuelo;
	private JLabel lblReservasVuelo;
	private JList reservasVuelo;
	private JButton btnCancelar2;
	private JComboBox comboBoxVuelos;
	private JButton btnVuelos;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarPaquete frame = new ConsultarPaquete();
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
	public ConsultarPaquete() {

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		setClosable(true);
		setTitle("Consulta Paquete");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(571, 414);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);

		setBounds(100, 100, 550, 400);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Paquetes disponibles");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 2;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		comboPaquetes = new JComboBox();
		GridBagConstraints gbc_comboPaquetes = new GridBagConstraints();
		gbc_comboPaquetes.insets = new Insets(0, 0, 5, 5);
		gbc_comboPaquetes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboPaquetes.gridx = 3;
		gbc_comboPaquetes.gridy = 2;
		getContentPane().add(comboPaquetes, gbc_comboPaquetes);
		comboPaquetes.addItem("Seleccionar paquete");

		comboPaquetes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					paquetes = ctrlPaquete.listarPaquetesDisponibles();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "No hay paquetes disponibles", JOptionPane.ERROR_MESSAGE);
				}
				for (DataPaquete p : paquetes) {
					if(comboPaquetes.getSelectedIndex() == 0) {
						comboPaquetes.addItem(p.getNombre());
					}
				}
				if(comboPaquetes.getSelectedIndex() != 0) {
					comboBoxRutasVuelo.removeAllItems();
					actualizarRutasVuelo();
				}
			}
		});

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String PaqueteSelec = (String) comboPaquetes.getSelectedItem();
				try {
					DataPaquete dataPaq = ctrlPaquete.verInfoPaquete(PaqueteSelec);
					textField.setText(dataPaq.getNombre());
					textField_2.setText(String.valueOf(dataPaq.getValidezDias()));;
					textField_1.setText(dataPaq.getDescripcion());
					textField_6.setText(String.valueOf(dataPaq.getDescuento()));
					textField_7.setText(String.valueOf(dataPaq.getCosto()));
					textField_3.setText(String.valueOf(dataPaq.getFechaAlta().getDayOfMonth()));
					textField_4.setText(String.valueOf(dataPaq.getFechaAlta().getMonthValue()));
					textField_5.setText(String.valueOf(dataPaq.getFechaAlta().getYear()));
				}catch(Exception e3) {}
			}
		});

		GridBagConstraints gbc_btnConsultar = new GridBagConstraints();
		gbc_btnConsultar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultar.gridx = 4;
		gbc_btnConsultar.gridy = 2;
		getContentPane().add(btnConsultar, gbc_btnConsultar);

		JButton btnCancelar = new JButton("Cancelar");

		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 5;
		gbc_btnCancelar.gridy = 2;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				limpiarFormulario();
			}
		});

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 3;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Validez");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 4;
		gbc_lblNewLabel_3.gridy = 3;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 5;
		gbc_textField_2.gridy = 3;
		getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 4;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridheight = 2;
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 4;
		getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Descuento");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 6;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 3;
		gbc_textField_6.gridy = 6;
		getContentPane().add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Costo");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 4;
		gbc_lblNewLabel_5.gridy = 6;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);

		textField_7 = new JTextField();
		textField_7.setEditable(false);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 5, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 5;
		gbc_textField_7.gridy = 6;
		getContentPane().add(textField_7, gbc_textField_7);
		textField_7.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Fecha de alta(dd/mm/yyyyyy)");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 7;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 7;
		getContentPane().add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridx = 4;
		gbc_textField_4.gridy = 7;
		getContentPane().add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.gridx = 5;
		gbc_textField_5.gridy = 7;
		getContentPane().add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Rutas asociadas y cantidades");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 8;
		getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
		
				comboBoxRutasVuelo = new JComboBox();
				GridBagConstraints gbc_comboBoxRutasVuelo = new GridBagConstraints();
				gbc_comboBoxRutasVuelo.fill = GridBagConstraints.HORIZONTAL;
				gbc_comboBoxRutasVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_comboBoxRutasVuelo.gridx = 3;
				gbc_comboBoxRutasVuelo.gridy = 8;
				getContentPane().add(comboBoxRutasVuelo, gbc_comboBoxRutasVuelo);
				comboBoxRutasVuelo.setVisible(true);
				comboBoxRutasVuelo.addItem("Seleccionar ruta de vuelo");
			
				JButton btnNewButton = new JButton("Consultar ruta seleccionada");
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton.gridx = 5;
				gbc_btnNewButton.gridy = 8;
				getContentPane().add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboPaquetes.setVisible(false);
				comboBoxRutasVuelo.setVisible(false);
				textField.setVisible(false);
				textField_2.setVisible(false);
				textField_1.setVisible(false);
				textField_6.setVisible(false);
				textField_7.setVisible(false);
				textField_3.setVisible(false);
				textField_4.setVisible(false);
				textField_5.setVisible(false);
				btnConsultar.setVisible(false);
				btnCancelar.setVisible(false);
				lblNewLabel.setVisible(false);
				lblNewLabel_1.setVisible(false);
				lblNewLabel_2.setVisible(false);
				lblNewLabel_3.setVisible(false);
				lblNewLabel_4.setVisible(false);
				lblNewLabel_5.setVisible(false);
				lblNewLabel_6.setVisible(false);
				lblNewLabel_7.setVisible(false);
				btnNewButton.setVisible(false);
				
				lblNombre = new JLabel("Nombre:");
				lblNombre.setVisible(false);
				GridBagConstraints gbc_lblNombre = new GridBagConstraints();
				gbc_lblNombre.anchor = GridBagConstraints.EAST;
				gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
				gbc_lblNombre.gridx = 0;
				gbc_lblNombre.gridy = 2;
				getContentPane().add(lblNombre, gbc_lblNombre);
				
						if (comboBoxRutasVuelo.getSelectedIndex() != 0 && comboBoxRutasVuelo.getSelectedIndex() != -1) {
							lblNombre.setVisible(true);
							textFieldNombre.setVisible(true);
							lblDescripcion.setVisible(true);
							textFieldDescripcion.setVisible(true);
							lblHora.setVisible(true);
							textFieldHora.setVisible(true);
							lblFechaAlta.setVisible(true);
							textFieldFechaAlta.setVisible(true);
							lblCostoEjec.setVisible(true);
							textFieldCostoEjec.setVisible(true);
							lblCostoTurista.setVisible(true);
							textFieldCostoTurista.setVisible(true);
							lblCostoExt.setVisible(true);
							textFieldCostoExt.setVisible(true);
							lblCategorias.setVisible(true);
							lblEstado.setVisible(true);
							lblDescCorta.setVisible(true);
							textFieldEstado.setVisible(true);
							textFieldDescCorta.setVisible(true);
							textFieldCategorias.setVisible(true);
							lblVuelos.setVisible(true);
							comboBoxVuelos.setVisible(true);
							btnVuelos.setVisible(true);
							String rv = comboBoxRutasVuelo.getSelectedItem().toString();
							rv = rv.split(" - ", 2)[0];
							DataRutaVuelo rutaVuelo;
							try {
								rutaVuelo = ctrlRutaVuelo.verInfoRutaVuelo(rv);
								comboBoxVuelos.removeAllItems();
								comboBoxVuelos.addItem("Seleccionar vuelo");
								comboBoxVuelos.setSelectedIndex(0);
								textFieldNombre.setText(rutaVuelo.getNombre());
								textFieldDescripcion.setText(rutaVuelo.getDescripcion());
								textFieldHora.setText(String.valueOf(rutaVuelo.getHora()));
								textFieldFechaAlta.setText(String.valueOf(rutaVuelo.getFechaAlta()));
								textFieldCiudadOrigen.setText(rutaVuelo.getCiudadOrigen());
								textFieldCiudadDestino.setText(rutaVuelo.getCiudadDestino());
								textFieldCostoEjec.setText(String.valueOf(rutaVuelo.getCostoEjecutivo()));
								textFieldCostoTurista.setText(String.valueOf(rutaVuelo.getCostoTurista()));
								textFieldCostoExt.setText(String.valueOf(rutaVuelo.getCostoExtra()));
								textFieldCategorias.setText(rutaVuelo.getCategorias().toString());
								textFieldDescCorta.setText(rutaVuelo.getDescCorta());
								textFieldEstado.setText(String.valueOf(rutaVuelo.getEstado()));
								for (DataVuelo dataVuelo : rutaVuelo.getVuelos())
									comboBoxVuelos.addItem(dataVuelo.toString());
							} catch (RVNoExisteExce e3) {
								e3.printStackTrace();
							}
						}
					}
				});

				lblDescripcion = new JLabel("Descripcion:");
				lblDescripcion.setVisible(false);
				
						textFieldNombre = new JTextField();
						textFieldNombre.setVisible(false);
						textFieldNombre.setEditable(false);
						GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
						gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldNombre.gridx = 1;
						gbc_textFieldNombre.gridy = 2;
						getContentPane().add(textFieldNombre, gbc_textFieldNombre);
				GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
				gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
				gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
				gbc_lblDescripcion.gridx = 0;
				gbc_lblDescripcion.gridy = 3;
				getContentPane().add(lblDescripcion, gbc_lblDescripcion);

				lblHora = new JLabel("Hora:");
				lblHora.setVisible(false);
				
						textFieldDescripcion = new JTextField();
						textFieldDescripcion.setVisible(false);
						textFieldDescripcion.setEditable(false);
						GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
						gbc_textFieldDescripcion.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldDescripcion.gridx = 1;
						gbc_textFieldDescripcion.gridy = 3;
						getContentPane().add(textFieldDescripcion, gbc_textFieldDescripcion);
				GridBagConstraints gbc_lblHora = new GridBagConstraints();
				gbc_lblHora.anchor = GridBagConstraints.EAST;
				gbc_lblHora.insets = new Insets(0, 0, 5, 5);
				gbc_lblHora.gridx = 0;
				gbc_lblHora.gridy = 4;
				getContentPane().add(lblHora, gbc_lblHora);

				lblFechaAlta = new JLabel("Fecha de alta:");
				lblFechaAlta.setVisible(false);
				
						textFieldHora = new JTextField();
						textFieldHora.setVisible(false);
						textFieldHora.setEditable(false);
						GridBagConstraints gbc_textFieldHora = new GridBagConstraints();
						gbc_textFieldHora.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldHora.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldHora.gridx = 1;
						gbc_textFieldHora.gridy = 4;
						getContentPane().add(textFieldHora, gbc_textFieldHora);
				GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
				gbc_lblFechaAlta.anchor = GridBagConstraints.EAST;
				gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
				gbc_lblFechaAlta.gridx = 0;
				gbc_lblFechaAlta.gridy = 5;
				getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);

				lblCostoTurista = new JLabel("Costo turista:");
				lblCostoTurista.setVisible(false);
				
						textFieldFechaAlta = new JTextField();
						textFieldFechaAlta.setVisible(false);
						textFieldFechaAlta.setEditable(false);
						GridBagConstraints gbc_textFieldFechaAlta = new GridBagConstraints();
						gbc_textFieldFechaAlta.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldFechaAlta.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldFechaAlta.gridx = 1;
						gbc_textFieldFechaAlta.gridy = 5;
						getContentPane().add(textFieldFechaAlta, gbc_textFieldFechaAlta);
				GridBagConstraints gbc_lblCostoTurista = new GridBagConstraints();
				gbc_lblCostoTurista.anchor = GridBagConstraints.EAST;
				gbc_lblCostoTurista.insets = new Insets(0, 0, 5, 5);
				gbc_lblCostoTurista.gridx = 0;
				gbc_lblCostoTurista.gridy = 6;
				getContentPane().add(lblCostoTurista, gbc_lblCostoTurista);

				lblCostoEjec = new JLabel("Costo ejecutivo:");
				lblCostoEjec.setVisible(false);
				
						textFieldCostoTurista = new JTextField();
						textFieldCostoTurista.setVisible(false);
						textFieldCostoTurista.setEditable(false);
						GridBagConstraints gbc_textFieldCostoTurista = new GridBagConstraints();
						gbc_textFieldCostoTurista.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldCostoTurista.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldCostoTurista.gridx = 1;
						gbc_textFieldCostoTurista.gridy = 6;
						getContentPane().add(textFieldCostoTurista, gbc_textFieldCostoTurista);
				GridBagConstraints gbc_lblCostoEjec = new GridBagConstraints();
				gbc_lblCostoEjec.anchor = GridBagConstraints.EAST;
				gbc_lblCostoEjec.insets = new Insets(0, 0, 5, 5);
				gbc_lblCostoEjec.gridx = 0;
				gbc_lblCostoEjec.gridy = 7;
				getContentPane().add(lblCostoEjec, gbc_lblCostoEjec);

				lblCostoExt = new JLabel("Costo equipaje extra:");
				lblCostoExt.setVisible(false);
				
						textFieldCostoEjec = new JTextField();
						textFieldCostoEjec.setVisible(false);
						textFieldCostoEjec.setEditable(false);
						GridBagConstraints gbc_textFieldCostoEjec = new GridBagConstraints();
						gbc_textFieldCostoEjec.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldCostoEjec.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldCostoEjec.gridx = 1;
						gbc_textFieldCostoEjec.gridy = 7;
						getContentPane().add(textFieldCostoEjec, gbc_textFieldCostoEjec);
				GridBagConstraints gbc_lblCostoExt = new GridBagConstraints();
				gbc_lblCostoExt.anchor = GridBagConstraints.EAST;
				gbc_lblCostoExt.insets = new Insets(0, 0, 5, 5);
				gbc_lblCostoExt.gridx = 0;
				gbc_lblCostoExt.gridy = 8;
				getContentPane().add(lblCostoExt, gbc_lblCostoExt);

				lblCiudadOrigen = new JLabel("Ciudad de origen:");
				lblCiudadOrigen.setVisible(false);
				
						textFieldCostoExt = new JTextField();
						textFieldCostoExt.setVisible(false);
						textFieldCostoExt.setEditable(false);
						GridBagConstraints gbc_textFieldCostoExt = new GridBagConstraints();
						gbc_textFieldCostoExt.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldCostoExt.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldCostoExt.gridx = 1;
						gbc_textFieldCostoExt.gridy = 8;
						getContentPane().add(textFieldCostoExt, gbc_textFieldCostoExt);
				GridBagConstraints gbc_lblCiudadOrigen = new GridBagConstraints();
				gbc_lblCiudadOrigen.anchor = GridBagConstraints.EAST;
				gbc_lblCiudadOrigen.insets = new Insets(0, 0, 5, 5);
				gbc_lblCiudadOrigen.gridx = 0;
				gbc_lblCiudadOrigen.gridy = 9;
				getContentPane().add(lblCiudadOrigen, gbc_lblCiudadOrigen);

				lblCiudadDestino = new JLabel("Ciudad de destino:");
				lblCiudadDestino.setVisible(false);
				
						textFieldCiudadOrigen = new JTextField();
						textFieldCiudadOrigen.setVisible(false);
						textFieldCiudadOrigen.setEditable(false);
						GridBagConstraints gbc_textFieldCiudadOrigen = new GridBagConstraints();
						gbc_textFieldCiudadOrigen.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldCiudadOrigen.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldCiudadOrigen.gridx = 1;
						gbc_textFieldCiudadOrigen.gridy = 9;
						getContentPane().add(textFieldCiudadOrigen, gbc_textFieldCiudadOrigen);
				GridBagConstraints gbc_lblCiudadDestino = new GridBagConstraints();
				gbc_lblCiudadDestino.anchor = GridBagConstraints.EAST;
				gbc_lblCiudadDestino.insets = new Insets(0, 0, 5, 5);
				gbc_lblCiudadDestino.gridx = 0;
				gbc_lblCiudadDestino.gridy = 10;
				getContentPane().add(lblCiudadDestino, gbc_lblCiudadDestino);
				
						textFieldCiudadDestino = new JTextField();
						textFieldCiudadDestino.setVisible(false);
						textFieldCiudadDestino.setEditable(false);
						GridBagConstraints gbc_textFieldCiudadDestino = new GridBagConstraints();
						gbc_textFieldCiudadDestino.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldCiudadDestino.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldCiudadDestino.gridx = 1;
						gbc_textFieldCiudadDestino.gridy = 10;
						getContentPane().add(textFieldCiudadDestino, gbc_textFieldCiudadDestino);
				
				
						textFieldCategorias = new JTextField();
						textFieldCategorias.setVisible(false);
						
								lblCategorias = new JLabel("Categorias:");
								lblCategorias.setVisible(false);
								GridBagConstraints gbc_lblCategorias = new GridBagConstraints();
								gbc_lblCategorias.anchor = GridBagConstraints.EAST;
								gbc_lblCategorias.insets = new Insets(0, 0, 5, 5);
								gbc_lblCategorias.gridx = 0;
								gbc_lblCategorias.gridy = 11;
								getContentPane().add(lblCategorias, gbc_lblCategorias);
						textFieldCategorias.setEditable(false);
						GridBagConstraints gbc_textFieldCategorias = new GridBagConstraints();
						gbc_textFieldCategorias.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldCategorias.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldCategorias.gridx = 1;
						gbc_textFieldCategorias.gridy = 11;
						getContentPane().add(textFieldCategorias, gbc_textFieldCategorias);
						
						textFieldDescCorta = new JTextField();
						textFieldDescCorta.setVisible(false);
						
								lblDescCorta = new JLabel("Descripcion corta:");
								lblDescCorta.setVisible(false);
								GridBagConstraints gbc_lblDescCorta = new GridBagConstraints();
								gbc_lblDescCorta.anchor = GridBagConstraints.EAST;
								gbc_lblDescCorta.insets = new Insets(0, 0, 5, 5);
								gbc_lblDescCorta.gridx = 0;
								gbc_lblDescCorta.gridy = 12;
								getContentPane().add(lblDescCorta, gbc_lblDescCorta);
						textFieldDescCorta.setEditable(false);
						GridBagConstraints gbc_textFieldDescCorta = new GridBagConstraints();
						gbc_textFieldDescCorta.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldDescCorta.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldDescCorta.gridx = 1;
						gbc_textFieldDescCorta.gridy = 12;
						getContentPane().add(textFieldDescCorta, gbc_textFieldDescCorta);
						
						
						textFieldEstado = new JTextField();
						textFieldEstado.setVisible(false);
						
								lblEstado = new JLabel("Estado:");
								lblEstado.setVisible(false);
								GridBagConstraints gbc_lblEstado = new GridBagConstraints();
								gbc_lblEstado.anchor = GridBagConstraints.EAST;
								gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
								gbc_lblEstado.gridx = 0;
								gbc_lblEstado.gridy = 13;
								getContentPane().add(lblEstado, gbc_lblEstado);
						textFieldEstado.setEditable(false);
						GridBagConstraints gbc_textFieldEstado = new GridBagConstraints();
						gbc_textFieldEstado.fill = GridBagConstraints.HORIZONTAL;
						gbc_textFieldEstado.insets = new Insets(0, 0, 5, 5);
						gbc_textFieldEstado.gridx = 1;
						gbc_textFieldEstado.gridy = 13;
						getContentPane().add(textFieldEstado, gbc_textFieldEstado);
								
										lblVuelos = new JLabel("Vuelos:");
										lblVuelos.setVisible(false);
										GridBagConstraints gbc_lblVuelos = new GridBagConstraints();
										gbc_lblVuelos.anchor = GridBagConstraints.EAST;
										gbc_lblVuelos.insets = new Insets(0, 0, 5, 5);
										gbc_lblVuelos.gridx = 0;
										gbc_lblVuelos.gridy = 14;
										getContentPane().add(lblVuelos, gbc_lblVuelos);
						
								comboBoxVuelos = new JComboBox();
								GridBagConstraints gbc_comboBoxVuelos = new GridBagConstraints();
								gbc_comboBoxVuelos.insets = new Insets(0, 0, 5, 5);
								gbc_comboBoxVuelos.fill = GridBagConstraints.HORIZONTAL;
								gbc_comboBoxVuelos.gridx = 1;
								gbc_comboBoxVuelos.gridy = 14;
								getContentPane().add(comboBoxVuelos, gbc_comboBoxVuelos);
								comboBoxVuelos.addItem("Seleccionar vuelo");
								comboBoxVuelos.setSelectedIndex(0);
								comboBoxVuelos.setVisible(false);
						
						btnVuelos = new JButton("Buscar");
						btnVuelos.setVisible(false);
						GridBagConstraints gbc_btnVuelos = new GridBagConstraints();
						gbc_btnVuelos.anchor = GridBagConstraints.WEST;
						gbc_btnVuelos.insets = new Insets(0, 0, 5, 0);
						gbc_btnVuelos.gridx = 2;
						gbc_btnVuelos.gridy = 14;
						getContentPane().add(btnVuelos, gbc_btnVuelos);
						
				lblNombreVuelo = new JLabel("Nombre del vuelo:");
				lblNombreVuelo.setVisible(false);
				GridBagConstraints gbc_lblNombreVuelo = new GridBagConstraints();
				gbc_lblNombreVuelo.anchor = GridBagConstraints.EAST;
				gbc_lblNombreVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_lblNombreVuelo.gridx = 1;
				gbc_lblNombreVuelo.gridy = 15;
				getContentPane().add(lblNombreVuelo, gbc_lblNombreVuelo);
				
				nombreVuelo = new JTextField();
				nombreVuelo.setVisible(false);
				GridBagConstraints gbc_nombreVuelo = new GridBagConstraints();
				gbc_nombreVuelo.fill = GridBagConstraints.HORIZONTAL;
				gbc_nombreVuelo.insets = new Insets(0, 0, 5, 0);
				gbc_nombreVuelo.gridx = 2;
				gbc_nombreVuelo.gridy = 15;
				getContentPane().add(nombreVuelo, gbc_nombreVuelo);
				
				lblFechaVuelo = new JLabel("Fecha del vuelo:");
				lblFechaVuelo.setVisible(false);
				GridBagConstraints gbc_lblFechaVuelo = new GridBagConstraints();
				gbc_lblFechaVuelo.anchor = GridBagConstraints.EAST;
				gbc_lblFechaVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_lblFechaVuelo.gridx = 1;
				gbc_lblFechaVuelo.gridy = 16;
				getContentPane().add(lblFechaVuelo, gbc_lblFechaVuelo);
				
				fechaVuelo = new JTextField();
				fechaVuelo.setVisible(false);
				GridBagConstraints gbc_fechaVuelo = new GridBagConstraints();
				gbc_fechaVuelo.fill = GridBagConstraints.HORIZONTAL;
				gbc_fechaVuelo.insets = new Insets(0, 0, 5, 0);
				gbc_fechaVuelo.gridx = 2;
				gbc_fechaVuelo.gridy = 16;
				getContentPane().add(fechaVuelo, gbc_fechaVuelo);
				
				lblDuracionVuelo = new JLabel("Duración del vuelo:");
				lblDuracionVuelo.setVisible(false);
				GridBagConstraints gbc_lblDuracionVuelo = new GridBagConstraints();
				gbc_lblDuracionVuelo.anchor = GridBagConstraints.EAST;
				gbc_lblDuracionVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_lblDuracionVuelo.gridx = 1;
				gbc_lblDuracionVuelo.gridy = 17;
				getContentPane().add(lblDuracionVuelo, gbc_lblDuracionVuelo);
				
				duracionVuelo = new JTextField();
				duracionVuelo.setVisible(false);
				GridBagConstraints gbc_duracionVuelo = new GridBagConstraints();
				gbc_duracionVuelo.fill = GridBagConstraints.HORIZONTAL;
				gbc_duracionVuelo.insets = new Insets(0, 0, 5, 0);
				gbc_duracionVuelo.gridx = 2;
				gbc_duracionVuelo.gridy = 17;
				getContentPane().add(duracionVuelo, gbc_duracionVuelo);
				
				lblCapTurVuelo = new JLabel("Capacidad turista:");
				lblCapTurVuelo.setVisible(false);
				GridBagConstraints gbc_lblCapTurVuelo = new GridBagConstraints();
				gbc_lblCapTurVuelo.anchor = GridBagConstraints.EAST;
				gbc_lblCapTurVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_lblCapTurVuelo.gridx = 1;
				gbc_lblCapTurVuelo.gridy = 18;
				getContentPane().add(lblCapTurVuelo, gbc_lblCapTurVuelo);
				
				capacidadTVuelo = new JTextField();
				capacidadTVuelo.setVisible(false);
				GridBagConstraints gbc_capacidadTVuelo = new GridBagConstraints();
				gbc_capacidadTVuelo.fill = GridBagConstraints.HORIZONTAL;
				gbc_capacidadTVuelo.insets = new Insets(0, 0, 5, 0);
				gbc_capacidadTVuelo.gridx = 2;
				gbc_capacidadTVuelo.gridy = 18;
				getContentPane().add(capacidadTVuelo, gbc_capacidadTVuelo);
				
				lblCapEjecVuelo = new JLabel("Capacidad ejecutivo:");
				lblCapEjecVuelo.setVisible(false);
				GridBagConstraints gbc_lblCapEjecVuelo = new GridBagConstraints();
				gbc_lblCapEjecVuelo.anchor = GridBagConstraints.EAST;
				gbc_lblCapEjecVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_lblCapEjecVuelo.gridx = 1;
				gbc_lblCapEjecVuelo.gridy = 19;
				getContentPane().add(lblCapEjecVuelo, gbc_lblCapEjecVuelo);
				
				capacidadEVuelo = new JTextField();
				capacidadEVuelo.setVisible(false);
				GridBagConstraints gbc_capacidadEVuelo = new GridBagConstraints();
				gbc_capacidadEVuelo.fill = GridBagConstraints.HORIZONTAL;
				gbc_capacidadEVuelo.insets = new Insets(0, 0, 5, 0);
				gbc_capacidadEVuelo.gridx = 2;
				gbc_capacidadEVuelo.gridy = 19;
				getContentPane().add(capacidadEVuelo, gbc_capacidadEVuelo);
				
				lblFechaAltaVuelo = new JLabel("Fecha de alta del vuelo:");
				lblFechaAltaVuelo.setVisible(false);
				GridBagConstraints gbc_lblFechaAltaVuelo = new GridBagConstraints();
				gbc_lblFechaAltaVuelo.anchor = GridBagConstraints.EAST;
				gbc_lblFechaAltaVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_lblFechaAltaVuelo.gridx = 1;
				gbc_lblFechaAltaVuelo.gridy = 20;
				getContentPane().add(lblFechaAltaVuelo, gbc_lblFechaAltaVuelo);
				
				fechaAltaVuelo = new JTextField();
				fechaAltaVuelo.setVisible(false);
				GridBagConstraints gbc_fechaAltaVuelo = new GridBagConstraints();
				gbc_fechaAltaVuelo.fill = GridBagConstraints.HORIZONTAL;
				gbc_fechaAltaVuelo.insets = new Insets(0, 0, 5, 0);
				gbc_fechaAltaVuelo.gridx = 2;
				gbc_fechaAltaVuelo.gridy = 20;
				getContentPane().add(fechaAltaVuelo, gbc_fechaAltaVuelo);
				
				lblReservasVuelo = new JLabel("Reservas:");
				lblReservasVuelo.setVisible(false);
				GridBagConstraints gbc_lblReservasVuelo = new GridBagConstraints();
				gbc_lblReservasVuelo.anchor = GridBagConstraints.EAST;
				gbc_lblReservasVuelo.insets = new Insets(0, 0, 5, 5);
				gbc_lblReservasVuelo.gridx = 1;
				gbc_lblReservasVuelo.gridy = 21;
				getContentPane().add(lblReservasVuelo, gbc_lblReservasVuelo);
				
				reservasVuelo = new JList();
				reservasVuelo.setVisible(false);
				GridBagConstraints gbc_reservasVuelo = new GridBagConstraints();
				gbc_reservasVuelo.fill = GridBagConstraints.HORIZONTAL;
				gbc_reservasVuelo.insets = new Insets(0, 0, 5, 0);
				gbc_reservasVuelo.gridx = 2;
				gbc_reservasVuelo.gridy = 21;
				getContentPane().add(reservasVuelo, gbc_reservasVuelo);

				
				
				btnCancelar2 = new JButton("Cancelar");
				GridBagConstraints gbc_btnCancelar2 = new GridBagConstraints();
				gbc_btnCancelar2.anchor = GridBagConstraints.WEST;
				gbc_btnCancelar2.gridx = 2;
				gbc_btnCancelar2.gridy = 22;
				getContentPane().add(btnCancelar2, gbc_btnCancelar2);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						setVisible(false);
						limpiarFormularioLocal();
					}
				});
				
				
				btnVuelos.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						reservasVuelo.removeAll();

						String nombreVueloSeleccionado = comboBoxVuelos.getSelectedItem().toString().split(" , ")[0];
						IVuelosController ctrlVuelo = fabrica.getIControladorVuelos();
						DataVuelo vuelo = new DataVuelo();

						try {
							vuelo = ctrlVuelo.verInfoVuelo(nombreVueloSeleccionado);
						} catch (VueloNoExisteExce e1) {
							e1.printStackTrace();
						}

						nombreVuelo.setText(vuelo.getNombre());
						fechaVuelo.setText(vuelo.getFecha().toString());
						duracionVuelo.setText(vuelo.getDuracion().toString());
						capacidadTVuelo.setText(vuelo.getMaxTurista().toString());
						capacidadEVuelo.setText(vuelo.getMaxEjecutivo().toString());
						fechaAltaVuelo.setText(vuelo.getFechaAlta().toString());
						

						ArrayList<DataReserva> reservas = vuelo.getReservas();

						ArrayList<String> reservasString = new ArrayList<>(); 

						for (DataReserva reserva : reservas) {
							reservasString.add(reserva.getnickC() + " - " + reserva.getFechaReserva() + " - " + reserva.gettipoAsiento() + " - " + reserva.getCantPasajes() + " - " + reserva.getCantExtra() + " - " + reserva.getCostoTotal());
						}

						reservasVuelo.setListData(reservasString.toArray());
						lblNombreVuelo.setVisible(true);
						nombreVuelo.setVisible(true);
						lblFechaVuelo.setVisible(true);
						fechaVuelo.setVisible(true);
						lblDuracionVuelo.setVisible(true);
						duracionVuelo.setVisible(true);
						lblCapTurVuelo.setVisible(true);
						capacidadTVuelo.setVisible(true);
						lblCapEjecVuelo.setVisible(true);
						capacidadEVuelo.setVisible(true);
						lblFechaAltaVuelo.setVisible(true);
						fechaAltaVuelo.setVisible(true);
						lblReservasVuelo.setVisible(true);
						reservasVuelo.setVisible(true);
					}
		});

		actualizarPaquetes();
	}

	protected void actualizarPaquetes() {
		try {
			paquetes = ctrlPaquete.listarPaquetesDisponibles();
			for (DataPaquete paq : paquetes) {
				if(comboPaquetes.getSelectedIndex()== 0)
					comboPaquetes.addItem(paq.getNombre());
			}
		} catch (Exception e) {
		}

	}
	protected void actualizarRutasVuelo() {
		ArrayList<DataRutaVueloPaquete> rutasVueloPaquete;
		try {
			String nomPaquete = (String)comboPaquetes.getSelectedItem();
			if(nomPaquete != null) {
				DataPaquete paq = ctrlPaquete.verInfoPaquete(nomPaquete);
				rutasVueloPaquete = paq.getRutasPaquete();
				if((!rutasVueloPaquete.isEmpty()) || rutasVueloPaquete != null) {
					for (DataRutaVueloPaquete datarvPAQ : rutasVueloPaquete) {
						String cantidadRuta = String.valueOf(datarvPAQ.getCantidad());
						comboBoxRutasVuelo.addItem(datarvPAQ.getDataRV().getNombre() + " - " + cantidadRuta); 
					}	
				}
			}
			} catch (VueloNoExisteExce e) {
			e.printStackTrace();
		}
	}


	private void limpiarFormulario() {
		comboPaquetes.setSelectedIndex(0);
		comboBoxRutasVuelo.setSelectedIndex(0);
		textField.setText("");
		textField_2.setText("");
		textField_1.setText("");
		textField_6.setText("");
		textField_7.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textField_5.setText("");
	};
	
	protected void limpiarFormularioLocal() {
		textFieldNombre.setText("");
		textFieldDescripcion.setText("");
		textFieldHora.setText("");
		textFieldFechaAlta.setText("");
		textFieldCiudadOrigen.setText("");
		textFieldCiudadDestino.setText("");
		textFieldCostoEjec.setText("");
		textFieldCostoTurista.setText("");
		textFieldCostoExt.setText("");
		textFieldCategorias.setText("");
		textFieldEstado.setText("");
		textFieldDescCorta.setText("");
		lblNombre.setVisible(false);
		textFieldNombre.setVisible(false);
		lblDescripcion.setVisible(false);
		textFieldDescripcion.setVisible(false);
		lblHora.setVisible(false);
		textFieldHora.setVisible(false);
		lblFechaAlta.setVisible(false);
		textFieldFechaAlta.setVisible(false);
		lblCostoEjec.setVisible(false);
		textFieldCostoEjec.setVisible(false);
		lblCostoTurista.setVisible(false);
		textFieldCostoTurista.setVisible(false);
		lblCostoExt.setVisible(false);
		textFieldCostoExt.setVisible(false);
		lblCategorias.setVisible(false);
		lblEstado.setVisible(false);
		textFieldEstado.setVisible(false);
		lblDescCorta.setVisible(false);
		textFieldDescCorta.setVisible(false);
		textFieldCategorias.setVisible(false);
		lblVuelos.setVisible(false);
		comboBoxRutasVuelo.setVisible(false);
		comboBoxVuelos.setVisible(false);
		lblRutasVuelo.setVisible(false);
		lblNombreVuelo.setVisible(false);
		lblFechaVuelo.setVisible(false);
		lblDuracionVuelo.setVisible(false);
		lblCapTurVuelo.setVisible(false);
		lblCapEjecVuelo.setVisible(false);
		lblFechaAltaVuelo.setVisible(false);
		lblReservasVuelo.setVisible(false);
		nombreVuelo.setVisible(false);
		fechaVuelo.setVisible(false);
		duracionVuelo.setVisible(false);
		capacidadTVuelo.setVisible(false);
		capacidadEVuelo.setVisible(false);
		fechaAltaVuelo.setVisible(false);
		reservasVuelo.setVisible(false);
		btnVuelos.setVisible(false);
	}
}
