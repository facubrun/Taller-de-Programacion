package ui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import exceptions.NombreRVRepetidoExce;
import exceptions.UsuarioNoExisteExce;
import logica.Fabrica;
import logica.Datatypes.DataAerolinea;
import logica.Datatypes.DataCategoria;
import logica.Datatypes.estadoRutaVuelo;
import logica.Handlers.RutasVueloHandler;
import logica.Handlers.UsuariosHandler;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.clases.Aerolinea;
import logica.clases.Categoria;
import logica.controllers.RutaVueloController;

public class AltaRutaVuelo extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblAerolinea;
	private JComboBox<String> comboBoxAerolinea;
	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JLabel lblDescripcion;
	private JTextArea textAreaDescripcion;
	private JLabel lblDescCorta;
	private JTextArea textAreaDescCorta;
	private JLabel lblHora;
	private JTextField textFieldHoras;
	private JLabel sepHsMin;
	private JTextField textFieldMinutos;
	private JLabel lblCostoT;
	private JTextField textFieldCostoT;
	private JLabel lblCostoE;
	private JTextField textFieldCostoE;
	private JLabel lblCostoEquipExt;
	private JTextField textFieldCostoEquipExt;
	private JLabel lblCiudadO;
	private JTextField textFieldCiudadO;
	private JLabel lblCiudadD;
	private JTextField textFieldCiudadD;
	private JLabel lblFechaAlta;
	private JTextField textFieldDiaFechaAlta;
	private JLabel lblCategorias;
	private JList listCategorias;
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	private JComboBox<String> comboBoxCiudadO;
	private JComboBox<String> comboBoxCiudadD;

	private IRutaVueloController controlRV;
	private IUsuariosController controlUsr;
	private UsuariosHandler handUsr = UsuariosHandler.getinstance();
	private RutasVueloHandler handRV = RutasVueloHandler.getInstance();

	DataAerolinea[] aerolineas;
	private JTextField textFieldMesFechaAlta;
	private JTextField textFieldAnioFechaAlta;
	private JComboBox comboBoxCategorias;

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
					AltaRutaVuelo frame = new AltaRutaVuelo();
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
	public AltaRutaVuelo() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Fabrica fabrica = Fabrica.getInstance();
		controlRV = fabrica.getIControladorRutaVuelo();
		controlUsr = fabrica.getIControladorUsuario();

		try {
			aerolineas = controlUsr.getAerolineas();
		} catch (UsuarioNoExisteExce e) {
			aerolineas = new DataAerolinea[0];
		}

		setBounds(100, 100, 861, 563);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 120, 200, 200, 200, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		lblAerolinea = new JLabel("Aerolinea:");
		GridBagConstraints gbc_lblAerolinea = new GridBagConstraints();
		gbc_lblAerolinea.anchor = GridBagConstraints.EAST;
		gbc_lblAerolinea.insets = new Insets(0, 0, 5, 5);
		gbc_lblAerolinea.gridx = 0;
		gbc_lblAerolinea.gridy = 1;
		getContentPane().add(lblAerolinea, gbc_lblAerolinea);

		comboBoxAerolinea = new JComboBox();
		GridBagConstraints gbc_comboBoxNombre = new GridBagConstraints();
		gbc_comboBoxNombre.gridwidth = 2;
		gbc_comboBoxNombre.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxNombre.gridx = 1;
		gbc_comboBoxNombre.gridy = 1;
		getContentPane().add(comboBoxAerolinea, gbc_comboBoxNombre);
		comboBoxAerolinea.addItem("Seleccione...");
		comboBoxAerolinea.setSelectedIndex(0);

		lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		getContentPane().add(lblNombre, gbc_lblNombre);

		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 2;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		lblDescripcion = new JLabel("Descripción:");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 0;
		gbc_lblDescripcion.gridy = 3;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		textAreaDescripcion.setAutoscrolls(false);
		textAreaDescripcion.setLineWrap(true);
		GridBagConstraints gbc_textAreaDescripcion = new GridBagConstraints();
		gbc_textAreaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAreaDescripcion.gridx = 1;
		gbc_textAreaDescripcion.gridy = 3;
		getContentPane().add(textAreaDescripcion, gbc_textAreaDescripcion);
		
		lblDescCorta = new JLabel("Descripción corta:");
		GridBagConstraints gbc_lblDescCorta = new GridBagConstraints();
		gbc_lblDescCorta.anchor = GridBagConstraints.EAST;
		gbc_lblDescCorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescCorta.gridx = 2;
		gbc_lblDescCorta.gridy = 3;
		getContentPane().add(lblDescCorta, gbc_lblDescCorta);

		textAreaDescCorta = new JTextArea();
		textAreaDescCorta.setBorder(new LineBorder(UIManager.getColor("Button.shadow")));
		textAreaDescCorta.setAutoscrolls(false);
		textAreaDescCorta.setLineWrap(true);
		GridBagConstraints gbc_textAreaDescCorta = new GridBagConstraints();
		gbc_textAreaDescCorta.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaDescCorta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAreaDescCorta.gridx = 3;
		gbc_textAreaDescCorta.gridy = 3;
		getContentPane().add(textAreaDescCorta, gbc_textAreaDescCorta);
		
		lblHora = new JLabel("Hora:");
		GridBagConstraints gbc_lblHora = new GridBagConstraints();
		gbc_lblHora.anchor = GridBagConstraints.EAST;
		gbc_lblHora.insets = new Insets(0, 0, 5, 5);
		gbc_lblHora.gridx = 0;
		gbc_lblHora.gridy = 4;
		getContentPane().add(lblHora, gbc_lblHora);
		gbc_lblHora.anchor = GridBagConstraints.EAST;
		gbc_lblHora.insets = new Insets(0, 0, 5, 5);
		gbc_lblHora.gridx = 1;
		gbc_lblHora.gridy = 4;

		textFieldHoras = new JTextField();
		GridBagConstraints gbc_textFieldHoras = new GridBagConstraints();
		gbc_textFieldHoras.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHoras.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHoras.gridx = 1;
		gbc_textFieldHoras.gridy = 4;
		getContentPane().add(textFieldHoras, gbc_textFieldHoras);
		textFieldHoras.setText(String.valueOf(LocalTime.now().getHour()));

		sepHsMin = new JLabel(":");
		GridBagConstraints gbc_sepHsMin = new GridBagConstraints();
		gbc_sepHsMin.fill = GridBagConstraints.VERTICAL;
		gbc_sepHsMin.insets = new Insets(0, 0, 5, 5);
		gbc_sepHsMin.gridx = 2;
		gbc_sepHsMin.gridy = 4;
		getContentPane().add(sepHsMin, gbc_sepHsMin);
		gbc_sepHsMin.fill = GridBagConstraints.VERTICAL;
		gbc_sepHsMin.insets = new Insets(0, 0, 5, 5);
		gbc_sepHsMin.gridx = 3;
		gbc_sepHsMin.gridy = 4;

		textFieldMinutos = new JTextField();
		GridBagConstraints gbc_textFieldMinutos = new GridBagConstraints();
		gbc_textFieldMinutos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMinutos.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldMinutos.gridx = 3;
		gbc_textFieldMinutos.gridy = 4;
		getContentPane().add(textFieldMinutos, gbc_textFieldMinutos);
		textFieldMinutos.setText(String.valueOf(LocalTime.now().getMinute()));

		lblCostoT = new JLabel("Costo turista:");
		GridBagConstraints gbc_lblNewLabelCostoT = new GridBagConstraints();
		gbc_lblNewLabelCostoT.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabelCostoT.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelCostoT.gridx = 0;
		gbc_lblNewLabelCostoT.gridy = 5;
		getContentPane().add(lblCostoT, gbc_lblNewLabelCostoT);

		textFieldCostoT = new JTextField();
		GridBagConstraints gbc_textFieldCostoT = new GridBagConstraints();
		gbc_textFieldCostoT.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCostoT.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCostoT.gridx = 1;
		gbc_textFieldCostoT.gridy = 5;
		getContentPane().add(textFieldCostoT, gbc_textFieldCostoT);
		textFieldCostoT.setText("0");

		lblCostoE = new JLabel("Costo ejecutivo:");
		GridBagConstraints gbc_lblCostoE = new GridBagConstraints();
		gbc_lblCostoE.anchor = GridBagConstraints.EAST;
		gbc_lblCostoE.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoE.gridx = 0;
		gbc_lblCostoE.gridy = 6;
		getContentPane().add(lblCostoE, gbc_lblCostoE);

		textFieldCostoE = new JFormattedTextField();
		GridBagConstraints gbc_textFieldCostoE = new GridBagConstraints();
		gbc_textFieldCostoE.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCostoE.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCostoE.gridx = 1;
		gbc_textFieldCostoE.gridy = 6;
		getContentPane().add(textFieldCostoE, gbc_textFieldCostoE);
		textFieldCostoE.setText("0");

		lblCostoEquipExt = new JLabel("Costo equipaje extra (unidad):");
		GridBagConstraints gbc_lblCostoEquipExt = new GridBagConstraints();
		gbc_lblCostoEquipExt.anchor = GridBagConstraints.EAST;
		gbc_lblCostoEquipExt.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoEquipExt.gridx = 0;
		gbc_lblCostoEquipExt.gridy = 7;
		getContentPane().add(lblCostoEquipExt, gbc_lblCostoEquipExt);

		textFieldCostoEquipExt = new JFormattedTextField();
		GridBagConstraints gbc_textFieldCostoEquipExt = new GridBagConstraints();
		gbc_textFieldCostoEquipExt.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCostoEquipExt.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCostoEquipExt.gridx = 1;
		gbc_textFieldCostoEquipExt.gridy = 7;
		getContentPane().add(textFieldCostoEquipExt, gbc_textFieldCostoEquipExt);
		textFieldCostoEquipExt.setText("0");

		lblCiudadO = new JLabel("Ciudad origen:");
		GridBagConstraints gbc_lblCiudadO = new GridBagConstraints();
		gbc_lblCiudadO.anchor = GridBagConstraints.EAST;
		gbc_lblCiudadO.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudadO.gridx = 0;
		gbc_lblCiudadO.gridy = 8;
		getContentPane().add(lblCiudadO, gbc_lblCiudadO);

		comboBoxCiudadO = new JComboBox();
		GridBagConstraints gbc_comboBoxCiudadO = new GridBagConstraints();
		gbc_comboBoxCiudadO.gridwidth = 2;
		gbc_comboBoxCiudadO.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCiudadO.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCiudadO.gridx = 1;
		gbc_comboBoxCiudadO.gridy = 8;
		getContentPane().add(comboBoxCiudadO, gbc_comboBoxCiudadO);

		lblCiudadD = new JLabel("Ciudad destino:");
		GridBagConstraints gbc_lblCiudadD = new GridBagConstraints();
		gbc_lblCiudadD.anchor = GridBagConstraints.EAST;
		gbc_lblCiudadD.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudadD.gridx = 0;
		gbc_lblCiudadD.gridy = 9;
		getContentPane().add(lblCiudadD, gbc_lblCiudadD);

		comboBoxCiudadD = new JComboBox();
		GridBagConstraints gbc_comboBoxCiudadD = new GridBagConstraints();
		gbc_comboBoxCiudadD.gridwidth = 2;
		gbc_comboBoxCiudadD.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCiudadD.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCiudadD.gridx = 1;
		gbc_comboBoxCiudadD.gridy = 9;
		getContentPane().add(comboBoxCiudadD, gbc_comboBoxCiudadD);

		// Cargar las ciudades en los JComboBox al inicializar la ventana
		cargarCiudadesEnComboBox();

		lblFechaAlta = new JLabel("Fecha de alta:");
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.anchor = GridBagConstraints.EAST;
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.gridx = 0;
		gbc_lblFechaAlta.gridy = 10;
		getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);

		textFieldDiaFechaAlta = new JTextField();
		GridBagConstraints gbc_textFieldDiaFechaAlta = new GridBagConstraints();
		gbc_textFieldDiaFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDiaFechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDiaFechaAlta.gridx = 1;
		gbc_textFieldDiaFechaAlta.gridy = 10;
		getContentPane().add(textFieldDiaFechaAlta, gbc_textFieldDiaFechaAlta);
		textFieldDiaFechaAlta.setText(String.valueOf(LocalDate.now().getDayOfMonth()));

		textFieldMesFechaAlta = new JTextField();
		GridBagConstraints gbc_textFieldMesFechaAlta = new GridBagConstraints();
		gbc_textFieldMesFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldMesFechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMesFechaAlta.gridx = 2;
		gbc_textFieldMesFechaAlta.gridy = 10;
		getContentPane().add(textFieldMesFechaAlta, gbc_textFieldMesFechaAlta);
		textFieldMesFechaAlta.setText(String.valueOf(LocalDate.now().getMonthValue()));

		textFieldAnioFechaAlta = new JTextField();
		GridBagConstraints gbc_textFieldAnioFechaAlta = new GridBagConstraints();
		gbc_textFieldAnioFechaAlta.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAnioFechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAnioFechaAlta.gridx = 3;
		gbc_textFieldAnioFechaAlta.gridy = 10;
		getContentPane().add(textFieldAnioFechaAlta, gbc_textFieldAnioFechaAlta);
		textFieldAnioFechaAlta.setText(String.valueOf(LocalDate.now().getYear()));

		lblCategorias = new JLabel("Categorías:");
		GridBagConstraints gbc_lblCategorias = new GridBagConstraints();
		gbc_lblCategorias.anchor = GridBagConstraints.EAST;
		gbc_lblCategorias.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategorias.gridx = 0;
		gbc_lblCategorias.gridy = 11;
		getContentPane().add(lblCategorias, gbc_lblCategorias);

		listCategorias = new JList<>();
		GridBagConstraints gbc_listCategorias = new GridBagConstraints();
		gbc_listCategorias.insets = new Insets(0, 0, 5, 5);
		gbc_listCategorias.gridwidth = 2;
		gbc_listCategorias.fill = GridBagConstraints.BOTH;
		gbc_listCategorias.gridx = 1;
		gbc_listCategorias.gridy = 11;
		getContentPane().add(listCategorias, gbc_listCategorias);
		listCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		btnAceptar = new JButton("Aceptar");
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 12;
		getContentPane().add(btnAceptar, gbc_btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					registrarDatosRV(event);
				} catch (NombreRVRepetidoExce e) {
					e.printStackTrace();
				}
			}
		});

		btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 12;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});

	}

	protected void registrarDatosRV(ActionEvent event) throws NombreRVRepetidoExce {
		String nickAerolinea = this.comboBoxAerolinea.getSelectedItem().toString();
		nickAerolinea = nickAerolinea.split(" ", 2)[0];
		String nombreRV = this.textFieldNombre.getText().trim();
		String descripcion = this.textAreaDescripcion.getText().trim();
		String desc_c = this.textAreaDescCorta.getText().trim();
		estadoRutaVuelo estado = estadoRutaVuelo.Ingresada;
		
		LocalTime hora = null;
		try {

			String horaStr = this.textFieldHoras.getText().trim();
			String minStr = this.textFieldMinutos.getText().trim();

			int horaInt = Integer.parseInt(horaStr);
			int minutosInt = Integer.parseInt(minStr);

			hora = LocalTime.of(horaInt, minutosInt);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos para la hora.",
					"Alta de ruta de vuelo", JOptionPane.ERROR_MESSAGE);
		}
		
		Float costoT = null;
		try {
			String costoTStr = this.textFieldCostoT.getText().trim();

			costoT = Float.parseFloat(costoTStr);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos váldios para los costos.",
					"Alta de ruta de vuelo", JOptionPane.ERROR_MESSAGE);
		}
		
		Float costoE = null;
		try {
			String costoEStr = this.textFieldCostoE.getText().trim();

			costoE = Float.parseFloat(costoEStr);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos váldios para los costos.",
					"Alta de ruta de vuelo", JOptionPane.ERROR_MESSAGE);
		}
	    
	    String ciudadO = this.comboBoxCiudadO.getSelectedItem().toString();
	    String ciudadD = this.comboBoxCiudadD.getSelectedItem().toString();
	    if (ciudadO.equalsIgnoreCase(ciudadD)) {
	        JOptionPane.showMessageDialog(this, "La ciudad de origen y destino no pueden ser la misma.",
	                "Alta de ruta de vuelo", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
		Float costoEquipExt = null;
		try {
			String costoEquipExtStr = this.textFieldCostoEquipExt.getText().trim();

			costoEquipExt = Float.parseFloat(costoEquipExtStr);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos váldios para los costos.",
					"Alta de ruta de vuelo", JOptionPane.ERROR_MESSAGE);
		}

		LocalDate fechaAlta = null;
		try {
			String diaStr = this.textFieldDiaFechaAlta.getText().trim();
			String mesStr = this.textFieldMesFechaAlta.getText().trim();
			String anioStr = this.textFieldAnioFechaAlta.getText().trim();

			int dia = Integer.parseInt(diaStr);
			int mes = Integer.parseInt(mesStr);
			int anio = Integer.parseInt(anioStr);

			fechaAlta = LocalDate.of(anio, mes, dia);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos para la fecha de alta.",
					"Alta de ruta de vuelo", JOptionPane.ERROR_MESSAGE);
		}
		
		Map<String, Aerolinea> mapAerolinea = handUsr.getAerolinea();
		Aerolinea aerolinea = null;
		for (Aerolinea aero : mapAerolinea.values()) {
			if (aero.getNickname().toLowerCase().equals(nickAerolinea.toLowerCase())) {
				aerolinea = aero;
				break;
			}
		}
		
		ArrayList<Categoria> listaCategorias = new ArrayList<>();
		for (Object nombreCategoriaObject : listCategorias.getSelectedValuesList()) {
			String nombreCategoriaString = (String) nombreCategoriaObject;
			listaCategorias.add(controlRV.obtenerCategoria(nombreCategoriaString));
		}
		
		if (nombreRV.isEmpty() || descripcion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No pueden haber campos vcacíos.", "Alta de ruta de vuelo",
					JOptionPane.ERROR_MESSAGE);
		} else if (costoT <= 0 || costoE <= 0 || costoEquipExt < 0) {
			JOptionPane.showMessageDialog(this, "El costo turista y ejecutivo deben ser mayores que 0,00.",
					"Alta de ruta de vuelo", JOptionPane.ERROR_MESSAGE);
		// } else if (fechaAlta.isBefore(LocalDate.now())) {
		// 	JOptionPane.showMessageDialog(this, "La fecha de alta debe ser mayor o igual a la fecha actual.",
		// 			"Alta de ruta de vuelo", JOptionPane.ERROR_MESSAGE);
		} else if (handRV.existeRutaVuelo(nombreRV)) {
			JOptionPane.showMessageDialog(this, "El nombre de la ruta de vuelo ya existe", "Alta de ruta de vuelo", JOptionPane.ERROR_MESSAGE);
		}
		else if (comboBoxAerolinea.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una aerolinea.", "Alta de ruta de vuelo",
					JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				controlRV.registrarRutaDeVuelo(nombreRV, descripcion, hora, fechaAlta, ciudadO, ciudadD, costoE, costoT,
						costoEquipExt, listaCategorias, aerolinea, desc_c, "", estado, "");
				JOptionPane.showMessageDialog(this,  "La ruta de vuelo se registró con éxito.", "Alta de ruta de vuelo", JOptionPane.INFORMATION_MESSAGE);
				limpiarFormulario();
				setVisible(false);
			} catch (NombreRVRepetidoExce e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void cargarCategoriasEnList() {
	    RutaVueloController controlRV = new RutaVueloController();
	    ArrayList<Categoria> categorias = controlRV.obtenerCategorias();

	    DefaultListModel<String> model = new DefaultListModel<>();

	    listCategorias.setModel(model);

	    if (categorias != null && !categorias.isEmpty()) {
	        for (Categoria categoria : categorias) {
	            model.addElement(categoria.getNombre());
	        }
	    }
	}

	
	protected void cargarCiudadesEnComboBox() {
	    RutaVueloController controlador = new RutaVueloController();
	    ArrayList<String> ciudades = controlador.listarCiudadesDisponibles();

	    comboBoxCiudadO.removeAllItems();
	    comboBoxCiudadD.removeAllItems();

	    if (ciudades != null || !ciudades.isEmpty()) {
	    	for (String ciudad : ciudades) {
	            comboBoxCiudadO.addItem(ciudad);
	            comboBoxCiudadD.addItem(ciudad);
	    	}
	        
	    }
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textAreaDescripcion.setText("");
		textFieldHoras.setText(String.valueOf(LocalTime.now().getHour()));
		textFieldMinutos.setText(String.valueOf(LocalTime.now().getMinute()));
		comboBoxAerolinea.setSelectedIndex(0);
		textFieldCostoT.setText("0");
		textFieldCostoE.setText("0");
		textFieldCostoEquipExt.setText("0");
		textFieldDiaFechaAlta.setText(String.valueOf(LocalDate.now().getDayOfMonth()));
	}

	protected void actualizarAerolineas() {
		comboBoxAerolinea.removeAllItems();
		comboBoxAerolinea.addItem("Seleccione...");
		comboBoxAerolinea.setSelectedIndex(0);
		try {
			aerolineas = controlUsr.getAerolineas();
			for (DataAerolinea dataAerolinea : aerolineas) {
				comboBoxAerolinea.addItem(dataAerolinea.toString());
			}
		} catch (UsuarioNoExisteExce e) {
			e.printStackTrace();
		}
	}

}
