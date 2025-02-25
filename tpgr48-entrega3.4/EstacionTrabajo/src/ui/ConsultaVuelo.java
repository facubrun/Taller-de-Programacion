package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptions.UsuarioNoExisteExce;
import exceptions.RVNoExisteExce;
import exceptions.VueloNoExisteExce;
import java.util.ArrayList;
import logica.Fabrica;
import logica.Datatypes.DataAerolinea;
import logica.Datatypes.DataReserva;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataVuelo;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.Interfaces.IVuelosController;

public class ConsultaVuelo extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nombreVuelo;
	private JTextField fechaVuelo;
	private JTextField duracionVuelo;
	private JTextField capacidadTVuelo;
	private JTextField capacidadEVuelo;
	private JTextField fechaAltaVuelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaVuelo frame = new ConsultaVuelo();
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
	public ConsultaVuelo() {

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		setClosable(true);
		setTitle("Consultar un Vuelo");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 3, myScreen.getScreenSize().height / 2);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);

		Fabrica fabrica = Fabrica.getInstance();

		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Aerolineas");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		JComboBox comboAerolineas = new JComboBox();
		GridBagConstraints gbc_comboAerolineas = new GridBagConstraints();
		gbc_comboAerolineas.insets = new Insets(0, 0, 5, 0);
		gbc_comboAerolineas.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboAerolineas.gridx = 3;
		gbc_comboAerolineas.gridy = 1;

		JLabel lblNewLabel_1 = new JLabel("Rutas de Vuelo");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		JComboBox comboRutasVuelo = new JComboBox();
		GridBagConstraints gbc_comboRutasVuelo = new GridBagConstraints();
		gbc_comboRutasVuelo.insets = new Insets(0, 0, 5, 0);
		gbc_comboRutasVuelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboRutasVuelo.gridx = 3;
		gbc_comboRutasVuelo.gridy = 2;
		getContentPane().add(comboRutasVuelo, gbc_comboRutasVuelo);

		JLabel lblNewLabel_2 = new JLabel("Vuelos");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 3;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		JComboBox comboVuelos = new JComboBox();
		GridBagConstraints gbc_comboVuelos = new GridBagConstraints();
		gbc_comboVuelos.insets = new Insets(0, 0, 5, 0);
		gbc_comboVuelos.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboVuelos.gridx = 3;
		gbc_comboVuelos.gridy = 3;
		getContentPane().add(comboVuelos, gbc_comboVuelos);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 5;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_3 = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 0;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		nombreVuelo = new JTextField();
		nombreVuelo.setEditable(false);
		GridBagConstraints gbc_nombreVuelo = new GridBagConstraints();
		gbc_nombreVuelo.insets = new Insets(0, 0, 5, 0);
		gbc_nombreVuelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreVuelo.gridx = 1;
		gbc_nombreVuelo.gridy = 0;
		panel.add(nombreVuelo, gbc_nombreVuelo);
		nombreVuelo.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Fecha");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 1;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		fechaVuelo = new JTextField();
		fechaVuelo.setEditable(false);
		GridBagConstraints gbc_fechaVuelo = new GridBagConstraints();
		gbc_fechaVuelo.insets = new Insets(0, 0, 5, 0);
		gbc_fechaVuelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_fechaVuelo.gridx = 1;
		gbc_fechaVuelo.gridy = 1;
		panel.add(fechaVuelo, gbc_fechaVuelo);
		fechaVuelo.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Duración");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 2;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);

		duracionVuelo = new JTextField();
		duracionVuelo.setEditable(false);
		GridBagConstraints gbc_duracionVuelo = new GridBagConstraints();
		gbc_duracionVuelo.insets = new Insets(0, 0, 5, 0);
		gbc_duracionVuelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_duracionVuelo.gridx = 1;
		gbc_duracionVuelo.gridy = 2;
		panel.add(duracionVuelo, gbc_duracionVuelo);
		duracionVuelo.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Capacidad turista");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 3;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		capacidadTVuelo = new JTextField();
		capacidadTVuelo.setEditable(false);
		GridBagConstraints gbc_capacidadTVuelo = new GridBagConstraints();
		gbc_capacidadTVuelo.insets = new Insets(0, 0, 5, 0);
		gbc_capacidadTVuelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_capacidadTVuelo.gridx = 1;
		gbc_capacidadTVuelo.gridy = 3;
		panel.add(capacidadTVuelo, gbc_capacidadTVuelo);
		capacidadTVuelo.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Capacidad ejecutiva");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 4;
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);

		capacidadEVuelo = new JTextField();
		capacidadEVuelo.setEditable(false);
		GridBagConstraints gbc_capacidadEVuelo = new GridBagConstraints();
		gbc_capacidadEVuelo.insets = new Insets(0, 0, 5, 0);
		gbc_capacidadEVuelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_capacidadEVuelo.gridx = 1;
		gbc_capacidadEVuelo.gridy = 4;
		panel.add(capacidadEVuelo, gbc_capacidadEVuelo);
		capacidadEVuelo.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Fecha alta");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 5;
		panel.add(lblNewLabel_8, gbc_lblNewLabel_8);

		fechaAltaVuelo = new JTextField();
		fechaAltaVuelo.setEditable(false);
		GridBagConstraints gbc_fechaAltaVuelo = new GridBagConstraints();
		gbc_fechaAltaVuelo.insets = new Insets(0, 0, 5, 0);
		gbc_fechaAltaVuelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_fechaAltaVuelo.gridx = 1;
		gbc_fechaAltaVuelo.gridy = 5;
		panel.add(fechaAltaVuelo, gbc_fechaAltaVuelo);
		fechaAltaVuelo.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Reservas");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 6;
		panel.add(lblNewLabel_9, gbc_lblNewLabel_9);

		JList reservasVuelo = new JList();
		GridBagConstraints gbc_reservasVuelo = new GridBagConstraints();
		gbc_reservasVuelo.fill = GridBagConstraints.BOTH;
		gbc_reservasVuelo.gridx = 1;
		gbc_reservasVuelo.gridy = 6;
		panel.add(reservasVuelo, gbc_reservasVuelo);

		IUsuariosController ctrlUser = fabrica.getIControladorUsuario();
		DataAerolinea[] aerolineas;

		try {
			aerolineas = ctrlUser.getAerolineas();
		} catch (UsuarioNoExisteExce e) {
			aerolineas = new DataAerolinea[0];
		}
		getContentPane().add(comboAerolineas, gbc_comboAerolineas);
		for (DataAerolinea aerolinea : aerolineas) {
			comboAerolineas.addItem(aerolinea.getNickname());
		}

		comboAerolineas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboRutasVuelo.removeAllItems();
				comboVuelos.removeAllItems();
				String aerolinea = (String) comboAerolineas.getSelectedItem();

				IRutaVueloController ctrlRutaVuelo = fabrica.getIControladorRutaVuelo();
				ArrayList<DataRutaVuelo> rutasVuelo = new ArrayList<>();
				try {
					rutasVuelo = ctrlRutaVuelo.listarRutasVueloPorAerolinea(aerolinea);
				} catch (UsuarioNoExisteExce e1) {
					e1.printStackTrace();
				}
				comboRutasVuelo.removeAllItems();
				for (DataRutaVuelo rutaVuelo : rutasVuelo) {
					comboRutasVuelo.addItem(rutaVuelo.getNombre());
				}
				comboVuelos.removeAllItems();

			}
		});

		comboRutasVuelo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboVuelos.removeAllItems();
				String rutaVuelo = (String) comboRutasVuelo.getSelectedItem();

				IVuelosController ctrlVuelo = fabrica.getIControladorVuelos();
				ArrayList<DataVuelo> vuelos = new ArrayList<>();

				try {
					if (rutaVuelo != null) {
						vuelos = ctrlVuelo.listarVuelosPorRutaVuelo(rutaVuelo);
					}
				} catch (RVNoExisteExce e1) {
					e1.printStackTrace();
				}
				comboVuelos.removeAllItems();
				for (DataVuelo vuelo : vuelos) {
					comboVuelos.addItem(vuelo.getNombre());
				}
			}
		});

		JButton btnConsulta = new JButton("Consultar");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reservasVuelo.removeAll();

				String nombreVueloSeleccionado = comboVuelos.getSelectedItem().toString();
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
			}
		});
		GridBagConstraints gbc_btnConsulta = new GridBagConstraints();
		gbc_btnConsulta.insets = new Insets(0, 0, 5, 0);
		gbc_btnConsulta.gridx = 3;
		gbc_btnConsulta.gridy = 4;
		getContentPane().add(btnConsulta, gbc_btnConsulta);

	}
}
