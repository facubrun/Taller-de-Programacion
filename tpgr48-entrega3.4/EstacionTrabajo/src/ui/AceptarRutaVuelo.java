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
import javax.swing.JOptionPane;
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
import logica.Datatypes.estadoRutaVuelo;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.Interfaces.IVuelosController;
import logica.clases.RutaVuelo;

public class AceptarRutaVuelo extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox comboRutasVuelo;
	private JComboBox comboAerolineas;

	Fabrica fabrica = Fabrica.getInstance();
	IUsuariosController ctrlUser = fabrica.getIControladorUsuario();
	IRutaVueloController ctrlRV = fabrica.getIControladorRutaVuelo();
	DataAerolinea[] aerolineas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AceptarRutaVuelo frame = new AceptarRutaVuelo();
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
	public AceptarRutaVuelo() {

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		setClosable(true);
		setTitle("Aceptar una Ruta de Vuelo");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 3, myScreen.getScreenSize().height / 2);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);

		Fabrica fabrica = Fabrica.getInstance();

		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 122, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Aerolineas");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		comboAerolineas = new JComboBox();
		GridBagConstraints gbc_comboAerolineas = new GridBagConstraints();
		gbc_comboAerolineas.insets = new Insets(0, 0, 5, 0);
		gbc_comboAerolineas.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboAerolineas.gridx = 3;
		gbc_comboAerolineas.gridy = 1;

		JLabel lblNewLabel_1 = new JLabel("Rutas de Vuelo");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		comboRutasVuelo = new JComboBox();
		GridBagConstraints gbc_comboRutasVuelo = new GridBagConstraints();
		gbc_comboRutasVuelo.insets = new Insets(0, 0, 5, 0);
		gbc_comboRutasVuelo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboRutasVuelo.gridx = 3;
		gbc_comboRutasVuelo.gridy = 2;
		getContentPane().add(comboRutasVuelo, gbc_comboRutasVuelo);

		try {
			aerolineas = ctrlUser.getAerolineas();
			comboAerolineas.addItem("Seleccionar aerolinea");
			comboAerolineas.setSelectedIndex(0);
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
				String aerolinea = (String) comboAerolineas.getSelectedItem();

				IRutaVueloController ctrlRutaVuelo = fabrica.getIControladorRutaVuelo();
				ArrayList<DataRutaVuelo> rutasVueloIngresadas = new ArrayList<>();
				try {
					rutasVueloIngresadas = ctrlRutaVuelo.listarRutasVueloIngresadasPorAerolinea(aerolinea);
				} catch (UsuarioNoExisteExce e1) {
					e1.printStackTrace();
				}
				comboRutasVuelo.removeAllItems();
				for (DataRutaVuelo rutaVuelo : rutasVueloIngresadas) {
					comboRutasVuelo.addItem(rutaVuelo.getNombre());
				}

			}
		});

		
				JButton btnConsulta = new JButton("Confirmar");
				btnConsulta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String rutaVuelo = (String) comboRutasVuelo.getSelectedItem();
						RutaVuelo rutaConfirmada = ctrlRV.obtenerRutaVuelo(rutaVuelo);
						rutaConfirmada.setEstado(estadoRutaVuelo.Confirmada);
						JOptionPane.showMessageDialog(null, "Ruta de vuelo confirmada");
						actualizarRutasVuelo();
					}
				});
				
				JButton btnNewButton = new JButton("Rechazar");
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
				gbc_btnNewButton.gridx = 2;
				gbc_btnNewButton.gridy = 5;
				getContentPane().add(btnNewButton, gbc_btnNewButton);
				GridBagConstraints gbc_btnConsulta = new GridBagConstraints();
				gbc_btnConsulta.insets = new Insets(0, 0, 5, 0);
				gbc_btnConsulta.gridx = 3;
				gbc_btnConsulta.gridy = 5;
				getContentPane().add(btnConsulta, gbc_btnConsulta);

				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String rutaVuelo = (String) comboRutasVuelo.getSelectedItem();
						RutaVuelo rutaConfirmada = ctrlRV.obtenerRutaVuelo(rutaVuelo);
						rutaConfirmada.setEstado(estadoRutaVuelo.Rechazada);
						JOptionPane.showMessageDialog(null, "Ruta de vuelo rechazada");
						actualizarRutasVuelo();
					}
				});
	}
	
	protected void actualizarRutasVuelo() {
		comboRutasVuelo.removeAllItems();
		comboRutasVuelo.addItem("Seleccionar ruta de vuelo");
		comboRutasVuelo.setSelectedIndex(0);
		if (this.comboAerolineas.getSelectedItem() == null) {
		    return;
		}
		comboRutasVuelo.removeAllItems();
		String aerolinea = (String) comboAerolineas.getSelectedItem();

		IRutaVueloController ctrlRutaVuelo = fabrica.getIControladorRutaVuelo();
		ArrayList<DataRutaVuelo> rutasVueloIngresadas = new ArrayList<>();
		try {
			rutasVueloIngresadas = ctrlRutaVuelo.listarRutasVueloIngresadasPorAerolinea(aerolinea);
		} catch (UsuarioNoExisteExce e1) {
			e1.printStackTrace();
		}
		comboRutasVuelo.removeAllItems();
		for (DataRutaVuelo rutaVuelo : rutasVueloIngresadas) {
			comboRutasVuelo.addItem(rutaVuelo.getNombre());
		}
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
	
	private void limpiarFormulario() {
		comboRutasVuelo.setSelectedIndex(0);
		comboAerolineas.setSelectedIndex(0);
	};
}
