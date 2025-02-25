package ui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import exceptions.NicknameRepetidoExce;

import javax.swing.JInternalFrame;

import logica.Fabrica;
import logica.Interfaces.IRutaVueloController;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.JPanel;

public class AltaCiudad extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private IRutaVueloController controlRV;

	private JTextField textFieldCiudad;
	private JTextField textFieldPais;
	private JTextField textFieldAeropuerto;
	private JTextArea textAreaDescripcion;
	private JTextField textFieldSitioWeb;
	private JLabel lblCiudad;
	private JLabel lblPais;
	private JLabel lblAeropuerto;
	private JLabel lblDescripcion;
	private JLabel lblSitioWeb;
	private JLabel lblfechaAlta;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JPanel panel_3;
	private JLabel lblDia;
	private JLabel lblAnio;
	private JLabel lblMes;
	private JTextField textFieldMes;
	private JTextField textFieldDia;
	private JTextField textFieldAnio;

	private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
		limpiarFormulario();
		setVisible(false);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaCiudad frame = new AltaCiudad();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AltaCiudad() {
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
		setTitle("Alta de Ciudad");
		Toolkit myScreen = Toolkit.getDefaultToolkit();
		setSize(myScreen.getScreenSize().width / 3, myScreen.getScreenSize().height / 2);
		setLocation(myScreen.getScreenSize().width / 12, myScreen.getScreenSize().height / 12);

		Fabrica fabrica = Fabrica.getInstance();
		controlRV = fabrica.getIControladorRutaVuelo();

		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 70, 122, 93, 70 };
		gridBagLayout.rowHeights = new int[] { 60, 60, 60, 60 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0 };
		getContentPane().setLayout(gridBagLayout);

		lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 0;
		gbc_lblCiudad.gridy = 0;
		getContentPane().add(lblCiudad, gbc_lblCiudad);

		textFieldCiudad = new JTextField();
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 1;
		gbc_textFieldCiudad.gridy = 0;
		getContentPane().add(textFieldCiudad, gbc_textFieldCiudad);
		textFieldCiudad.setColumns(10);

		lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 2;
		gbc_lblDescripcion.gridy = 0;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		textAreaDescripcion = new JTextArea();
		GridBagConstraints gbc_textAreaDescripcion = new GridBagConstraints();
		gbc_textAreaDescripcion.insets = new Insets(0, 0, 5, 0);
		gbc_textAreaDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescripcion.gridx = 3;
		gbc_textAreaDescripcion.gridy = 0;
		getContentPane().add(textAreaDescripcion, gbc_textAreaDescripcion);

		lblPais = new JLabel("Pais:");
		lblPais.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPais = new GridBagConstraints();
		gbc_lblPais.anchor = GridBagConstraints.EAST;
		gbc_lblPais.insets = new Insets(0, 0, 5, 5);
		gbc_lblPais.gridx = 0;
		gbc_lblPais.gridy = 1;
		getContentPane().add(lblPais, gbc_lblPais);

		textFieldPais = new JTextField();
		GridBagConstraints gbc_textFieldPais = new GridBagConstraints();
		gbc_textFieldPais.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPais.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPais.gridx = 1;
		gbc_textFieldPais.gridy = 1;
		getContentPane().add(textFieldPais, gbc_textFieldPais);
		textFieldPais.setColumns(10);

		lblSitioWeb = new JLabel("Sitio Web:");
		lblSitioWeb.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblSitioWeb = new GridBagConstraints();
		gbc_lblSitioWeb.anchor = GridBagConstraints.EAST;
		gbc_lblSitioWeb.insets = new Insets(0, 0, 5, 5);
		gbc_lblSitioWeb.gridx = 2;
		gbc_lblSitioWeb.gridy = 1;
		getContentPane().add(lblSitioWeb, gbc_lblSitioWeb);

		textFieldSitioWeb = new JTextField();
		GridBagConstraints gbc_textFieldSitioWeb = new GridBagConstraints();
		gbc_textFieldSitioWeb.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldSitioWeb.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSitioWeb.gridx = 3;
		gbc_textFieldSitioWeb.gridy = 1;
		getContentPane().add(textFieldSitioWeb, gbc_textFieldSitioWeb);
		textFieldSitioWeb.setColumns(10);

		lblAeropuerto = new JLabel("Aeropuerto:");
		lblAeropuerto.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblAeropuerto = new GridBagConstraints();
		gbc_lblAeropuerto.anchor = GridBagConstraints.EAST;
		gbc_lblAeropuerto.insets = new Insets(0, 0, 5, 5);
		gbc_lblAeropuerto.gridx = 0;
		gbc_lblAeropuerto.gridy = 2;
		getContentPane().add(lblAeropuerto, gbc_lblAeropuerto);

		textFieldAeropuerto = new JTextField();
		GridBagConstraints gbc_textFieldAeropuerto = new GridBagConstraints();
		gbc_textFieldAeropuerto.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAeropuerto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAeropuerto.gridx = 1;
		gbc_textFieldAeropuerto.gridy = 2;
		getContentPane().add(textFieldAeropuerto, gbc_textFieldAeropuerto);
		textFieldAeropuerto.setColumns(10);

		lblfechaAlta = new JLabel("Fecha de alta:");
		lblfechaAlta.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblfechaAlta = new GridBagConstraints();
		gbc_lblfechaAlta.anchor = GridBagConstraints.EAST;
		gbc_lblfechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblfechaAlta.gridx = 2;
		gbc_lblfechaAlta.gridy = 2;
		getContentPane().add(lblfechaAlta, gbc_lblfechaAlta);

		panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 3;
		gbc_panel_3.gridy = 2;
		getContentPane().add(panel_3, gbc_panel_3);
		panel_3.setLayout(new GridLayout(2, 3, 0, 0));

		lblDia = new JLabel("Dia:");
		panel_3.add(lblDia);

		lblMes = new JLabel("Mes:");
		panel_3.add(lblMes);

		lblAnio = new JLabel("Año:");
		panel_3.add(lblAnio);

		textFieldDia = new JTextField();
		panel_3.add(textFieldDia);
		textFieldDia.setColumns(10);

		textFieldMes = new JTextField();
		panel_3.add(textFieldMes);
		textFieldMes.setColumns(10);

		textFieldAnio = new JTextField();
		panel_3.add(textFieldAnio);
		textFieldAnio.setColumns(10);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 3;
		getContentPane().add(btnAceptar, gbc_btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				registrarDatosCiudad(event);
			}
		});

		btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 3;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});
	}

	protected void registrarDatosCiudad(ActionEvent event) {
		String ciudad = this.textFieldCiudad.getText().trim();
		String pais = this.textFieldPais.getText().trim();
		String aeropuerto = this.textFieldAeropuerto.getText().trim();
		String descripcion = this.textAreaDescripcion.getText().trim();
		String sitioWeb = this.textFieldSitioWeb.getText().trim();
		String dia = this.textFieldDia.getText().trim();
		String mes = this.textFieldMes.getText().trim();
		String anio = this.textFieldAnio.getText().trim();
		if (ciudad.isEmpty() || pais.isEmpty() || aeropuerto.isEmpty() || descripcion.isEmpty() || sitioWeb.isEmpty()
				|| dia.isEmpty() || mes.isEmpty() || anio.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta de Ciudad",
					JOptionPane.ERROR_MESSAGE);
		} else {
			try {
				try {
					LocalDate.of(Integer.parseInt(anio), Integer.parseInt(mes), Integer.parseInt(dia));
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos para la fecha.");
				} catch (DateTimeException e) {
					JOptionPane.showMessageDialog(this, "Por favor, ingresa valores numéricos válidos para la fecha.");
				}
				LocalDate fechaAlta = LocalDate.of(Integer.parseInt(anio), Integer.parseInt(mes),
						Integer.parseInt(dia));
				if (!ciudad.matches("^[a-zA-Z ]+$")) {
					JOptionPane.showMessageDialog(this, "El nombre de la ciudad debe contener solo letras");
				} else if (!pais.matches("^[a-zA-Z ]+$")) {
					JOptionPane.showMessageDialog(this, "El nombre del pais debe contener solo letras");
				} else if (!sitioWeb
						.matches("^(https?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-])\\/?$")) {
					JOptionPane.showMessageDialog(this, "Sitio Web invalido");
				} else if (!aeropuerto.matches("^[a-zA-Z0-9 -]+$")) {
					JOptionPane.showMessageDialog(this, "aeropuerto invalido");
				} else {
					controlRV.registrarCiudad(ciudad, pais, aeropuerto, descripcion, sitioWeb, fechaAlta);
					JOptionPane.showMessageDialog(this, "El alta de la Ciudad se ejecuto con exito", "Alta de Ciudad",
							JOptionPane.INFORMATION_MESSAGE);
					limpiarFormulario();
					setVisible(false);
				}
			} catch (NicknameRepetidoExce e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Alta de Ciudad", JOptionPane.ERROR_MESSAGE);
				textFieldCiudad.setText("");
				textFieldPais.setText("");
			}
		}
	}

	private void limpiarFormulario() {
		textFieldCiudad.setText("");
		textFieldPais.setText("");
		textFieldAeropuerto.setText("");
		textAreaDescripcion.setText("");
		textFieldSitioWeb.setText("");
		textFieldMes.setText("");
		textFieldAnio.setText("");
		textFieldDia.setText("");
	}

}