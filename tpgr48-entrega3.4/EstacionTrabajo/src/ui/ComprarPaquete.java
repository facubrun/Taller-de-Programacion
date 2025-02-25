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
import javax.swing.JOptionPane;
import java.util.ArrayList;
import logica.Fabrica;
import logica.Datatypes.DataCliente;
import logica.Datatypes.DataPaquete;
import logica.Interfaces.IPaqueteController;
import logica.Interfaces.IUsuariosController;
public class ComprarPaquete extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JComboBox comboPaquetes;
	private JComboBox comboClientes;
	DataCliente[] clientes;

	Fabrica fabrica = Fabrica.getInstance();
	IPaqueteController ctrlPaquete = fabrica.getIControladorPaquete();
	IUsuariosController ctrlUsuario = fabrica.getIControladorUsuario();
	ArrayList<DataPaquete> paquetes = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComprarPaquete frame = new ComprarPaquete();
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
	public ComprarPaquete() {

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Compra de Paquete");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(571, 414);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);

		setBounds(100, 100, 550, 400);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		
				JLabel lblNewLabel = new JLabel("Paquetes con rutas de vuelo");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel.gridx = 2;
				gbc_lblNewLabel.gridy = 3;
				getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		comboPaquetes = new JComboBox();
		GridBagConstraints gbc_comboPaquetes = new GridBagConstraints();
		gbc_comboPaquetes.insets = new Insets(0, 0, 5, 5);
		gbc_comboPaquetes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboPaquetes.gridx = 3;
		gbc_comboPaquetes.gridy = 3;
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
					if(p.getRutasPaquete() != null) {
						comboPaquetes.addItem(p.getNombre());
					}
				}

			}
		});
		
				JLabel lblNewLabel2 = new JLabel("Clientes");
				GridBagConstraints gbc_lblNewLabel2 = new GridBagConstraints();
				gbc_lblNewLabel2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel2.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel2.gridx = 2;
				gbc_lblNewLabel2.gridy = 4;
				getContentPane().add(lblNewLabel2, gbc_lblNewLabel2);

		comboClientes = new JComboBox();
		GridBagConstraints gbc_comboClientes = new GridBagConstraints();
		gbc_comboClientes.insets = new Insets(0, 0, 5, 5);
		gbc_comboClientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboClientes.gridx = 3;
		gbc_comboClientes.gridy = 4;
		getContentPane().add(comboClientes, gbc_comboClientes);
		comboClientes.addItem("Seleccionar cliente");

		comboClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					clientes = ctrlUsuario.getClientes();
				} catch (Exception e1) {
					clientes = new DataCliente[0];
				}
				for (DataCliente cli : clientes) {
					comboClientes.addItem(cli.getNickname());
				}
			}
		});


		JButton btnComprar = new JButton("Comprar");
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String paqueteSelec = (String) comboPaquetes.getSelectedItem();
				DataPaquete dataPaq;
				try {
					dataPaq = ctrlPaquete.verInfoPaquete(paqueteSelec);
					String nickC = (String) comboClientes.getSelectedItem();
					LocalDate fechaCompra = LocalDate.now();
					Integer validez = dataPaq.getValidezDias();
					LocalDate fechaVencimiento = fechaCompra.plusDays(validez); 
					IPaqueteController ctrlPaquete = fabrica.getIControladorPaquete();
					ctrlPaquete.comprarPaquete(nickC, paqueteSelec, fechaCompra, fechaVencimiento);
					JOptionPane.showMessageDialog(null, "Paquete comprado");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error, el paquete ya fue comprado por el cliente", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnComprar = new GridBagConstraints();
		gbc_btnComprar.insets = new Insets(0, 0, 5, 5);
		gbc_btnComprar.gridx = 2;
		gbc_btnComprar.gridy = 7;
		getContentPane().add(btnComprar, gbc_btnComprar);

		JButton btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 7;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		actualizarPaquetes();
		actualizarClientes();
	}

	protected void actualizarPaquetes() {
		comboPaquetes.removeAllItems();
		try {
			paquetes = ctrlPaquete.listarPaquetesDisponibles();
			for (DataPaquete paq : paquetes) {
				comboPaquetes.addItem(paq.getNombre());
			}
		} catch (Exception e) {
		}
	}
	protected void actualizarClientes() {
		comboClientes.removeAllItems();
		try {
			clientes = ctrlUsuario.getClientes();
			for (DataCliente cli : clientes) {
				comboClientes.addItem(cli.getNickname());
			}
		} catch (Exception e) {
		}
	}

	private void limpiarFormulario() {
		comboPaquetes.setSelectedIndex(0);
		comboClientes.setSelectedIndex(0);
	};
}
