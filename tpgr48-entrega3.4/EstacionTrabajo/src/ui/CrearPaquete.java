package ui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.DateTimeException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptions.PaqueteExisteExce;
import logica.Fabrica;
import logica.Interfaces.IPaqueteController;

public class CrearPaquete extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField nombrePaquete;
	private JTextField descPaquete;
	private JTextField validezPaquete;
	private JTextField descuentoPaquete;
	private JTextField costoPaquete;
	private JTextField diaPaquete;
	private JTextField mesPaquete;
	private JTextField anioPaquete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPaquete frame = new CrearPaquete();
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
	public CrearPaquete() {
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setTitle("Crear Paquete");
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(200, 200, 600, 400);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 124, 110, 0, 0, 92, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 20, 108, 26 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		Fabrica fabrica = Fabrica.getInstance();
		IPaqueteController ctrlPaquetes = fabrica.getIControladorPaquete();
		JLabel lblNewLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		nombrePaquete = new JTextField();
		GridBagConstraints gbc_nombrePaquete = new GridBagConstraints();
		gbc_nombrePaquete.insets = new Insets(0, 0, 5, 5);
		gbc_nombrePaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombrePaquete.gridx = 3;
		gbc_nombrePaquete.gridy = 0;
		getContentPane().add(nombrePaquete, gbc_nombrePaquete);
		nombrePaquete.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.gridx = 2;
		gbc_lblDescripcion.gridy = 1;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		descPaquete = new JTextField();
		GridBagConstraints gbc_descPaquete = new GridBagConstraints();
		gbc_descPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_descPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_descPaquete.gridx = 3;
		gbc_descPaquete.gridy = 1;
		getContentPane().add(descPaquete, gbc_descPaquete);
		descPaquete.setColumns(10);

		JLabel lblValidezdias = new JLabel("Validez (dias)");
		GridBagConstraints gbc_lblValidezdias = new GridBagConstraints();
		gbc_lblValidezdias.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidezdias.anchor = GridBagConstraints.EAST;
		gbc_lblValidezdias.gridx = 2;
		gbc_lblValidezdias.gridy = 2;
		getContentPane().add(lblValidezdias, gbc_lblValidezdias);

		validezPaquete = new JTextField();
		GridBagConstraints gbc_validezPaquete = new GridBagConstraints();
		gbc_validezPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_validezPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_validezPaquete.gridx = 3;
		gbc_validezPaquete.gridy = 2;
		getContentPane().add(validezPaquete, gbc_validezPaquete);
		validezPaquete.setColumns(10);

		JLabel lblDescuento = new JLabel("Descuento");
		GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
		gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuento.anchor = GridBagConstraints.EAST;
		gbc_lblDescuento.gridx = 2;
		gbc_lblDescuento.gridy = 3;
		getContentPane().add(lblDescuento, gbc_lblDescuento);
		gbc_lblDescuento.anchor = GridBagConstraints.EAST;

		descuentoPaquete = new JTextField();
		GridBagConstraints gbc_descuentoPaquete = new GridBagConstraints();
		gbc_descuentoPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_descuentoPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_descuentoPaquete.gridx = 3;
		gbc_descuentoPaquete.gridy = 3;
		getContentPane().add(descuentoPaquete, gbc_descuentoPaquete);
		descuentoPaquete.setColumns(10);
		descuentoPaquete.setColumns(10);

		JLabel lblCosto = new JLabel("Costo");
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.gridy = 4;
		gbc_lblCosto.gridx = 2;
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		getContentPane().add(lblCosto, gbc_lblCosto);

		costoPaquete = new JTextField();
		GridBagConstraints gbc_costoPaquete = new GridBagConstraints();
		gbc_costoPaquete.fill = GridBagConstraints.HORIZONTAL;
		gbc_costoPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_costoPaquete.gridx = 3;
		gbc_costoPaquete.gridy = 4;
		getContentPane().add(costoPaquete, gbc_costoPaquete);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridwidth = 5;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 5;
		getContentPane().add(panel, gbc_panel);

		JLabel lblNewLabel_1 = new JLabel("Dia");
		panel.add(lblNewLabel_1);

		diaPaquete = new JTextField();
		panel.add(diaPaquete);
		diaPaquete.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Mes");
		panel.add(lblNewLabel_2);

		mesPaquete = new JTextField();
		panel.add(mesPaquete);
		mesPaquete.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Año");
		panel.add(lblNewLabel_3);

		anioPaquete = new JTextField();
		panel.add(anioPaquete);
		anioPaquete.setColumns(10);
		        JButton confirmBtn = new JButton("Confirmar");
		        confirmBtn.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        		
		        		try {
		        			try {
		        				LocalDate.of(Integer.parseInt(anioPaquete.getText()),
		        						Integer.parseInt(mesPaquete.getText()), Integer.parseInt(diaPaquete.getText()));
		        				Integer.parseInt(validezPaquete.getText());
		        				Float.parseFloat(descuentoPaquete.getText());
		        				Float.parseFloat(costoPaquete.getText());
		        			}catch(DateTimeException e3) {
		        				JOptionPane.showMessageDialog(null,
		        						"Por favor, ingresa valores numéricos válidos para la fecha.");
		        			}catch(NumberFormatException e2) {
		        				JOptionPane.showMessageDialog(null,
		        						"Por favor, ingresa valores numéricos válidos.");

		        			}
		        			LocalDate fecha = LocalDate.of(Integer.parseInt(anioPaquete.getText()),
		        					Integer.parseInt(mesPaquete.getText()), Integer.parseInt(diaPaquete.getText()));
		        			if(fecha.isBefore(LocalDate.now())) {
		        				JOptionPane.showMessageDialog(null, "La fecha de alta no es valida");
		        			}else if( Integer.parseInt(validezPaquete.getText())<0 || Float.parseFloat(descuentoPaquete.getText()) < 0) {
		        				JOptionPane.showMessageDialog(null, "Dias de validez de paquete y descuento invalido");
		        			}else {
		        				ctrlPaquetes.crearPaquete(nombrePaquete.getText(), descPaquete.getText(),
		        						Integer.parseInt(validezPaquete.getText()), Float.parseFloat(descuentoPaquete.getText()), Float.parseFloat(costoPaquete.getText()), fecha, null);

		        				JOptionPane.showMessageDialog(null, "Paquete creado con exito", "Exito",
		        						JOptionPane.INFORMATION_MESSAGE);
		        				setVisible(false);
		        				limpiarFormulario();
		        			}
		        		} catch (PaqueteExisteExce e1) {
		        			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        		}
		        	}
		        });		
		        GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		        gbc_btnConfirmar.insets = new Insets(0, 0, 5, 5);
		        gbc_btnConfirmar.gridx = 3;
		        gbc_btnConfirmar.gridy = 6;
		        getContentPane().add(confirmBtn, gbc_btnConfirmar);
		
		
		        JButton btnCancelar = new JButton("Cancelar");
		        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		        gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		        gbc_btnCancelar.gridx = 4;
		        gbc_btnCancelar.gridy = 6;
		        getContentPane().add(btnCancelar, gbc_btnCancelar);
		        btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
			}});

	}

	private void limpiarFormulario() {
		nombrePaquete.setText("");
		descPaquete.setText("");;
		validezPaquete.setText("");;
		descuentoPaquete.setText("");;
		costoPaquete.setText("");;
		diaPaquete.setText("");;
		mesPaquete.setText("");;
		anioPaquete.setText("");;
    };
}
