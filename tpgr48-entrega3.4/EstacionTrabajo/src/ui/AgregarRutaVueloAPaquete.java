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
import logica.Datatypes.DataVuelo;
import logica.Datatypes.tipoAsiento;
import logica.Interfaces.IPaqueteController;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.Interfaces.IVuelosController;
import logica.clases.Paquete;
import logica.clases.RutaVuelo;

public class AgregarRutaVueloAPaquete extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JComboBox comboRutasVuelo;
	private JComboBox comboPaquetes;
	private JComboBox comboAerolineas;

	Fabrica fabrica = Fabrica.getInstance();
	IPaqueteController ctrlPaquete = fabrica.getIControladorPaquete();
	ArrayList<DataPaquete> paquetes = new ArrayList<>();
	IUsuariosController ctrlUser = fabrica.getIControladorUsuario();
	DataAerolinea[] aerolineas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarRutaVueloAPaquete frame = new AgregarRutaVueloAPaquete();
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
	public AgregarRutaVueloAPaquete() {

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		setClosable(true);
		setTitle("Agreagr Ruta de Vuelo a Paquete");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(571, 414);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);

		setBounds(100, 100, 550, 400);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Paquetes disponibles");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		comboPaquetes = new JComboBox();
		GridBagConstraints gbc_comboPaquetes = new GridBagConstraints();
		gbc_comboPaquetes.insets = new Insets(0, 0, 5, 0);
		gbc_comboPaquetes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboPaquetes.gridx = 4;
		gbc_comboPaquetes.gridy = 1;
		getContentPane().add(comboPaquetes, gbc_comboPaquetes);

		comboAerolineas = new JComboBox();
		GridBagConstraints gbc_comboAerolineas = new GridBagConstraints();
		gbc_comboAerolineas.insets = new Insets(0, 0, 5, 0);
		gbc_comboAerolineas.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboAerolineas.gridx = 4;
		gbc_comboAerolineas.gridy = 2;
		comboAerolineas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboAerolineas.getSelectedIndex() != 0) {
					actualizarRutasVuelo();
				}
			}
		});

		JLabel lblNewLabel1 = new JLabel("Aerolineas");
		GridBagConstraints gbc_lblNewLabel1 = new GridBagConstraints();
		gbc_lblNewLabel1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel1.gridx = 2;
		gbc_lblNewLabel1.gridy = 2;
		getContentPane().add(lblNewLabel1, gbc_lblNewLabel1);

		JLabel lblNewLabel_1 = new JLabel("Rutas de Vuelo");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 3;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		comboRutasVuelo = new JComboBox();
		GridBagConstraints gbc_comboRutasVuelo = new GridBagConstraints();
		gbc_comboRutasVuelo.insets = new Insets(0, 0, 5, 0);
		gbc_comboRutasVuelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboRutasVuelo.gridx = 4;
		gbc_comboRutasVuelo.gridy = 3;
		getContentPane().add(comboRutasVuelo, gbc_comboRutasVuelo);

		try {
			aerolineas = ctrlUser.getAerolineas();
		} catch (UsuarioNoExisteExce e) {
			aerolineas = new DataAerolinea[0];
		}
		getContentPane().add(comboAerolineas, gbc_comboAerolineas);
		for (DataAerolinea aerolinea : aerolineas) {
			comboAerolineas.addItem(aerolinea.getNickname());
		}

		try {
			paquetes = ctrlPaquete.listarPaquetesDisponibles();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "No hay paquetes disponibles", JOptionPane.ERROR_MESSAGE);
		}
		for (DataPaquete p : paquetes) {
			if(p.getCompraClientes().isEmpty()) {
				comboPaquetes.addItem(p.getNombre());
			}	
		}


		comboAerolineas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboRutasVuelo.removeAllItems();
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

			}
		});

		JLabel lblNewLabel_2 = new JLabel("Cantidad a agregar");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 4;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 4;
		getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Tipo de asiento");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 5;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		JComboBox comboBoxAsiento = new JComboBox();
		GridBagConstraints gbc_comboBoxAsiento = new GridBagConstraints();
		gbc_comboBoxAsiento.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxAsiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAsiento.gridx = 4;
		gbc_comboBoxAsiento.gridy = 5;
		getContentPane().add(comboBoxAsiento, gbc_comboBoxAsiento);
		comboBoxAsiento.addItem(tipoAsiento.Turista);
		comboBoxAsiento.addItem(tipoAsiento.Ejecutivo);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String PaqueteSelec = (String) comboPaquetes.getSelectedItem();
				String nomRutaP = (String) comboRutasVuelo.getSelectedItem();
				tipoAsiento asientoSelec = (tipoAsiento) comboBoxAsiento.getSelectedItem();
				Integer cantidadRutas =  Integer.valueOf(textField.getText());
				IPaqueteController ctrlPaquete = fabrica.getIControladorPaquete();
				try {
					ctrlPaquete.agregarRVaPaquete(PaqueteSelec, nomRutaP, asientoSelec, cantidadRutas);
					JOptionPane.showMessageDialog(null, "Ruta de vuelo agregada con éxito");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error, la ruta de vuelo ya está en el paquete", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
		gbc_btnAgregar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAgregar.gridx = 2;
		gbc_btnAgregar.gridy = 8;
		getContentPane().add(btnAgregar, gbc_btnAgregar);

		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 8;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				limpiarFormulario();
			}
		});

		actualizarPaquetes();
		actualizarAerolineas();
		actualizarRutasVuelo();
	}

	protected void actualizarAerolineas() {
		comboAerolineas.removeAllItems();
		comboAerolineas.addItem("Seleccionar aerolinea");
		comboAerolineas.setSelectedIndex(0);
		try {
			aerolineas = ctrlUser.getAerolineas();
			for (DataAerolinea dataAerolinea : aerolineas) {
				comboAerolineas.addItem(dataAerolinea.getNickname());
			}
		} catch (UsuarioNoExisteExce e) {
			e.printStackTrace();
		}
	}

	protected void actualizarPaquetes() {
		comboPaquetes.removeAllItems();
		comboPaquetes.addItem("Seleccionar paquete");
		comboPaquetes.setSelectedItem(0);
		try {
			paquetes = ctrlPaquete.listarPaquetesDisponibles();
			for (DataPaquete paq : paquetes) {
				if(paq.getCompraClientes().isEmpty()) {
					comboPaquetes.addItem(paq.getNombre());
				}
			}
		} catch (Exception e) {
		}
	}

	protected void actualizarRutasVuelo() {
		comboRutasVuelo.removeAllItems();
		comboRutasVuelo.addItem("Seleccionar ruta de vuelo");
		comboRutasVuelo.setSelectedIndex(0);
		try {
			if (this.comboAerolineas.getSelectedItem() == null) {
				return;
			}

			String aer = this.comboAerolineas.getSelectedItem().toString();
			aer = aer.split(" ", 2)[0];
			DataAerolinea aerolineas2 = ctrlUser.verInfoAerolinea(aer);
			ArrayList<DataRutaVuelo> rutasVueloAero = aerolineas2.getRutasVuelo();
			for (DataRutaVuelo datarv : rutasVueloAero) {
				comboRutasVuelo.addItem(datarv.getNombre());
			}

		} catch (UsuarioNoExisteExce e) {

		}
	}

	private void limpiarFormulario() {
		comboPaquetes.setSelectedIndex(0);
		comboAerolineas.setSelectedIndex(0);
		comboRutasVuelo.setSelectedIndex(0);
		textField.setText("");
	};
}
