package ui;

import java.awt.EventQueue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.time.LocalDate;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;

import exceptions.AerolineaNoExisteExce;
import exceptions.NombreVRepetidoExce;
import exceptions.RVNoExisteExce;
import exceptions.UsuarioNoExisteExce;
import exceptions.VueloNoExisteExce;
import exceptions.ReservaExce;
import logica.Fabrica;
import logica.Datatypes.DataAerolinea;
import logica.Datatypes.DataCliente;
import logica.Datatypes.DataPasajes;
import logica.Datatypes.DataRutaVuelo;
import logica.Datatypes.DataVuelo;
import logica.Datatypes.tipoAsiento;
import logica.Handlers.VuelosHandler;
import logica.Interfaces.IRutaVueloController;
import logica.Interfaces.IUsuariosController;
import logica.Interfaces.IVuelosController;
import logica.clases.Cliente;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.util.List;

import logica.clases.Vuelo;

public class ReservaVuelo extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    
    private IUsuariosController controlUsr;
    private IVuelosController cVuelos;
    private IRutaVueloController controlRutasVuelo;
    private JLabel lblTipoAsiento;
    private JLabel lblCantPasajes;
    private JLabel lblEquipExtra;
    private JLabel lblSeleccioneUnaAerolinea;
    private JLabel lblSeleccioneUnaRutaVuelo;
    private JLabel lblSeleccioneUnVuelo;
    private JLabel lblSeleccioneUnCliente;
    private JLabel lblDuracion;
    private JLabel lblFechaVuelo;
    private JLabel lblFechaAlta;
    private JLabel lblMaxE;
    private JLabel lblMaxT;
    private JButton btnReservar;
    private JComboBox comboBoxAerolinea;
    private JComboBox comboBoxRutasVuelo;    
    private JComboBox comboBoxVuelos;
    private JComboBox comboBoxCliente;
    private JComboBox comboBoxTipoAsiento; 
    private JTextField textFieldCantPasajes;
    private JTextField textFieldEquipExtra;
    private JTextField textFieldDuracion;
    private JTextField textFieldFecha;
    private JTextField textFieldMaxT;
    private JTextField textFieldMaxE;
    private JTextField textFieldNombre;
    private JTextField textFieldNombreCliente;
    private JTextField txtDiaV;
    private JTextField txtMesV;
    private JTextField txtAnioV;
    private JTextField txtDiaA;
    private JTextField txtMesA;
    private JTextField txtAnioA;
    
    DataAerolinea[] aerolineas;
    DataAerolinea aerolinea;
    private JPanel panel;
    private JTextField txt_asientosTurista;
    private JTextField txt_asientosEjecutivo;
    private JTextField txt_fechaAlta;
    private JTextField txt_fechaVuelo;
    private JTextField txt_fechaDuracion;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JPanel panel_1;
    private JLabel lblNewLabel_5;
    private JPanel panel_2;
    private JButton btnCancelar;
    private JButton btnVerInfoVuelo;
    private JTextField txt_apellidoPasajero;
    private JTextField txt_nombrePasajero;
    private JList listPasajeros;
    private JButton btnAgregarPasajero;
    private JButton btnEliminarPasajero;

    private void formInternalFrameClosingV(javax.swing.event.InternalFrameEvent evt) {
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
                    ReservaVuelo frame = new ReservaVuelo();
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
	@SuppressWarnings("rawtypes")
	public ReservaVuelo() {
        
		    setResizable(true);
	        setIconifiable(true);
	        setMaximizable(true);
	        setClosable(true);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        
        Fabrica fabrica = Fabrica.getInstance();
        cVuelos = fabrica.getIControladorVuelos();
        controlUsr = fabrica.getIControladorUsuario();
        controlRutasVuelo = fabrica.getIControladorRutaVuelo();

        ArrayList<String> pasajeros = new ArrayList<String>();
        
        
		try {
			aerolineas = controlUsr.getAerolineas();
		} catch (UsuarioNoExisteExce e) {
			aerolineas = new DataAerolinea[0];
		}
       
        
		setBounds(100, 100, 810, 449);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 120, 200, 200, 0, 200, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 0, 30, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);
		

		 
		 //SELECCION DE AEROLINEA
		 lblSeleccioneUnaAerolinea = new JLabel("Seleccione una aerolinea:");
		 GridBagConstraints gbclblSeleccioneUnAerolinea = new GridBagConstraints();
		 gbclblSeleccioneUnAerolinea.anchor = GridBagConstraints.EAST;
		 gbclblSeleccioneUnAerolinea.insets = new Insets(0, 0, 5, 5);
		 gbclblSeleccioneUnAerolinea.gridx = 0;
		 gbclblSeleccioneUnAerolinea.gridy = 1;
		 getContentPane().add(lblSeleccioneUnaAerolinea, gbclblSeleccioneUnAerolinea);
        
        comboBoxRutasVuelo = new JComboBox();
        GridBagConstraints gbccomboBoxRutasVuelo = new GridBagConstraints();
        gbccomboBoxRutasVuelo.gridwidth = 2;
        gbccomboBoxRutasVuelo.insets = new Insets(0, 0, 5, 5);
        gbccomboBoxRutasVuelo.fill = GridBagConstraints.HORIZONTAL;
        gbccomboBoxRutasVuelo.gridx = 1;
        gbccomboBoxRutasVuelo.gridy = 2;
        comboBoxRutasVuelo.addItem("Seleccione una ruta de vuelo");
        comboBoxRutasVuelo.setSelectedIndex(0);
        comboBoxRutasVuelo.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent arg0) {
            		if (comboBoxRutasVuelo.getSelectedIndex() != 0 && comboBoxAerolinea.getSelectedIndex() != 0) {
        				cmdBuscarVuelo(arg0);
        				actualizarVuelos();
        				
            		}
            	}		
            });
        
        comboBoxAerolinea = new JComboBox();
        GridBagConstraints gbccomboBoxAerolinea = new GridBagConstraints();
        gbccomboBoxAerolinea.gridwidth = 2;
        gbccomboBoxAerolinea.insets = new Insets(0, 0, 5, 5);
        gbccomboBoxAerolinea.fill = GridBagConstraints.HORIZONTAL;
        gbccomboBoxAerolinea.gridx = 1;
        gbccomboBoxAerolinea.gridy = 1;
        comboBoxAerolinea.addItem("Seleccione una aerolinea");
        comboBoxAerolinea.setSelectedIndex(0);
        comboBoxAerolinea.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        			if (comboBoxAerolinea.getSelectedIndex() != 0 && comboBoxAerolinea.getSelectedIndex() != 0) {
        				cmdBuscarAerolinea(arg0);
        				actualizarRutasVuelo();
        			}
        	}			
        });		
        getContentPane().add(comboBoxAerolinea, gbccomboBoxAerolinea);
        
        //SELECCION DE RUTA VUELO
        lblSeleccioneUnaRutaVuelo = new JLabel("Seleccione una Ruta de Vuelo:");
        lblSeleccioneUnaRutaVuelo.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbclblSeleccioneUnaRutaVuelo = new GridBagConstraints();
        gbclblSeleccioneUnaRutaVuelo.anchor = GridBagConstraints.EAST;
        gbclblSeleccioneUnaRutaVuelo.insets = new Insets(0, 0, 5, 5);
        gbclblSeleccioneUnaRutaVuelo.gridx = 0;
        gbclblSeleccioneUnaRutaVuelo.gridy = 2;
        getContentPane().add(lblSeleccioneUnaRutaVuelo, gbclblSeleccioneUnaRutaVuelo);
        getContentPane().add(comboBoxRutasVuelo, gbccomboBoxRutasVuelo);
        
        lblDuracion = new JLabel("Duración:");
        GridBagConstraints gbclblDuracion = new GridBagConstraints();
        gbclblDuracion.anchor = GridBagConstraints.EAST;
        gbclblDuracion.insets = new Insets(0, 0, 5, 5);
        gbclblDuracion.gridx = 0;
        gbclblDuracion.gridy = 8;
        lblDuracion.setVisible(false);
               
        textFieldDuracion = new JTextField();
        textFieldDuracion.setEditable(false);
        GridBagConstraints gbctextFieldDuracion = new GridBagConstraints();
        gbctextFieldDuracion.fill = GridBagConstraints.HORIZONTAL;
        gbctextFieldDuracion.insets = new Insets(0, 0, 5, 5);
        gbctextFieldDuracion.gridx = 1;
        gbctextFieldDuracion.gridy = 8;
        textFieldDuracion.setVisible(false);
        textFieldDuracion.setColumns(10);
        
        
        lblFechaVuelo = new JLabel("Fecha:");
		GridBagConstraints gbclblFechaVuelo = new GridBagConstraints();
		gbclblFechaVuelo.gridx = 2;
		gbclblFechaVuelo.gridy = 8;
		lblFechaVuelo.setVisible(false);


		txtDiaV = new JTextField();
		GridBagConstraints gbctextFieldDia = new GridBagConstraints();
		gbctextFieldDia.gridwidth = 10;
		gbctextFieldDia.insets = new Insets(0, 0, 5, 5);
		gbctextFieldDia.fill = GridBagConstraints.EAST;
		gbctextFieldDia.gridx = 3;
		gbctextFieldDia.gridy = 8;
		txtDiaV.setColumns(3);
		
		txtMesV = new JTextField();
		GridBagConstraints gbctextFieldMes = new GridBagConstraints();
		gbctextFieldMes.gridwidth = 10;
		gbctextFieldMes.insets = new Insets(0, 0, 5, 5);
		gbctextFieldMes.fill = GridBagConstraints.EAST;
		gbctextFieldMes.gridx = 4;
		gbctextFieldMes.gridy = 8;
		txtMesV.setColumns(3);
		
		txtAnioV = new JTextField();
		GridBagConstraints gbctextFieldAnio = new GridBagConstraints();
		gbctextFieldAnio.gridwidth = 10;
		gbctextFieldAnio.insets = new Insets(0, 0, 5, 5);
		gbctextFieldAnio.fill = GridBagConstraints.EAST;
		gbctextFieldAnio.gridx = 5;
		gbctextFieldAnio.gridy = 8;
		txtAnioV.setColumns(3);
        
        lblMaxT = new JLabel("Cantidad asientos turista:");
        GridBagConstraints gbclblMaxT = new GridBagConstraints();
        gbclblMaxT.anchor = GridBagConstraints.EAST;
        gbclblMaxT.insets = new Insets(0, 0, 5, 5);
        gbclblMaxT.gridx = 0;
        gbclblMaxT.gridy = 9;
        lblMaxT.setVisible(false);
        
        textFieldMaxT = new JTextField();
        textFieldMaxT.setEditable(false);
        GridBagConstraints gbctextFieldMaxT = new GridBagConstraints();
        gbctextFieldMaxT.insets = new Insets(0, 0, 5, 5);
        gbctextFieldMaxT.fill = GridBagConstraints.HORIZONTAL;
        gbctextFieldMaxT.gridx = 1;
        gbctextFieldMaxT.gridy = 9;
        textFieldMaxT.setVisible(false);
        textFieldDuracion.setColumns(10);
        
        lblMaxE = new JLabel("Cantidad asientos ejecutivo:");
        GridBagConstraints gbclblMaxE = new GridBagConstraints();
        gbclblMaxE.anchor = GridBagConstraints.EAST;
        gbclblMaxE.insets = new Insets(0, 0, 5, 5);
        gbclblMaxE.gridx = 3;
        gbclblMaxE.gridy = 9;
        lblMaxE.setVisible(false); 
        
        textFieldMaxE = new JTextField();
        textFieldMaxE.setEditable(false);
        GridBagConstraints gbctextFieldMaxE = new GridBagConstraints();
        gbctextFieldMaxE.insets = new Insets(0, 0, 5, 5);
        gbctextFieldMaxE.fill = GridBagConstraints.HORIZONTAL;
        gbctextFieldMaxE.gridx = 4;
        gbctextFieldMaxE.gridy = 9;
        textFieldMaxE.setVisible(false);
        textFieldDuracion.setColumns(10);
        
	      //SELECCION Y DAR DATOS VUELO
        
	    lblSeleccioneUnVuelo = new JLabel("Seleccione un Vuelo:");
	    lblSeleccioneUnVuelo.setHorizontalAlignment(SwingConstants.RIGHT);
	    GridBagConstraints gbclblSeleccioneUnVuelo = new GridBagConstraints();
	    gbclblSeleccioneUnVuelo.anchor = GridBagConstraints.EAST;
	    gbclblSeleccioneUnVuelo.insets = new Insets(0, 0, 5, 5);
	    gbclblSeleccioneUnVuelo.gridx = 0;
	    gbclblSeleccioneUnVuelo.gridy = 3;
	    getContentPane().add(lblSeleccioneUnVuelo, gbclblSeleccioneUnVuelo);
        
        comboBoxVuelos = new JComboBox();
        GridBagConstraints gbccomboBoxVuelos = new GridBagConstraints();
        gbccomboBoxVuelos.gridwidth = 2;
        gbccomboBoxVuelos.insets = new Insets(0, 0, 5, 5);
        gbccomboBoxVuelos.fill = GridBagConstraints.HORIZONTAL;
        gbccomboBoxVuelos.gridx = 1;
        gbccomboBoxVuelos.gridy = 3;
        getContentPane().add(comboBoxVuelos, gbccomboBoxVuelos);
        
        btnVerInfoVuelo = new JButton("Ver Informacion");
        btnVerInfoVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBoxVuelos.getSelectedItem() == null || comboBoxRutasVuelo.getSelectedItem() == null || comboBoxAerolinea.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una ruta y un vuelo", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        String nombreVuelo = comboBoxVuelos.getSelectedItem().toString();
                        DataVuelo vuelo = cVuelos.verInfoVuelo(nombreVuelo);
                        txt_fechaDuracion.setText(Integer.valueOf(vuelo.getDuracion().getHour()).toString() + ":" + Integer.valueOf(vuelo.getDuracion().getMinute()).toString());
                        txt_fechaVuelo.setText(vuelo.getFecha().toString());
                        txt_fechaAlta.setText(vuelo.getFechaAlta().toString());
                        txt_asientosTurista.setText(vuelo.getMaxTurista().toString());
                        txt_asientosEjecutivo.setText(vuelo.getMaxEjecutivo().toString());
                    } catch (VueloNoExisteExce e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        GridBagConstraints gbc_btnVerInfoVuelo = new GridBagConstraints();
        gbc_btnVerInfoVuelo.insets = new Insets(0, 0, 5, 5);
        gbc_btnVerInfoVuelo.gridx = 3;
        gbc_btnVerInfoVuelo.gridy = 3;
        getContentPane().add(btnVerInfoVuelo, gbc_btnVerInfoVuelo);
        
        panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.gridheight = 3;
        gbc_panel.gridwidth = 3;
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 0;
        gbc_panel.gridy = 4;
        getContentPane().add(panel, gbc_panel);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
        gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        panel.setLayout(gbl_panel);
        
        lblNewLabel_3 = new JLabel("Fecha vuelo");
        GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
        gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_3.gridx = 0;
        gbc_lblNewLabel_3.gridy = 0;
        panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
        
        txt_fechaVuelo = new JTextField();
        txt_fechaVuelo.setEditable(false);
        GridBagConstraints gbc_txt_fechaVuelo = new GridBagConstraints();
        gbc_txt_fechaVuelo.insets = new Insets(0, 0, 5, 5);
        gbc_txt_fechaVuelo.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_fechaVuelo.gridx = 1;
        gbc_txt_fechaVuelo.gridy = 0;
        panel.add(txt_fechaVuelo, gbc_txt_fechaVuelo);
        txt_fechaVuelo.setColumns(10);
        
        lblNewLabel = new JLabel("Asientos turista");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel.gridx = 2;
        gbc_lblNewLabel.gridy = 0;
        panel.add(lblNewLabel, gbc_lblNewLabel);
        
        txt_asientosTurista = new JTextField();
        txt_asientosTurista.setEditable(false);
        GridBagConstraints gbc_txt_asientosTurista = new GridBagConstraints();
        gbc_txt_asientosTurista.insets = new Insets(0, 0, 5, 0);
        gbc_txt_asientosTurista.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_asientosTurista.gridx = 3;
        gbc_txt_asientosTurista.gridy = 0;
        panel.add(txt_asientosTurista, gbc_txt_asientosTurista);
        txt_asientosTurista.setColumns(10);
        
        lblNewLabel_4 = new JLabel("Duracion");
        GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
        gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_4.gridx = 0;
        gbc_lblNewLabel_4.gridy = 1;
        panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
        
        txt_fechaDuracion = new JTextField();
        txt_fechaDuracion.setEditable(false);
        GridBagConstraints gbc_txt_fechaDuracion = new GridBagConstraints();
        gbc_txt_fechaDuracion.insets = new Insets(0, 0, 5, 5);
        gbc_txt_fechaDuracion.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_fechaDuracion.gridx = 1;
        gbc_txt_fechaDuracion.gridy = 1;
        panel.add(txt_fechaDuracion, gbc_txt_fechaDuracion);
        txt_fechaDuracion.setColumns(10);
        
        lblNewLabel_1 = new JLabel("Asientos ejecutivo");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_1.anchor = GridBagConstraints.ABOVE_BASELINE_TRAILING;
        gbc_lblNewLabel_1.gridx = 2;
        gbc_lblNewLabel_1.gridy = 1;
        panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
        
        txt_asientosEjecutivo = new JTextField();
        txt_asientosEjecutivo.setEditable(false);
        GridBagConstraints gbc_txt_asientosEjecutivo = new GridBagConstraints();
        gbc_txt_asientosEjecutivo.insets = new Insets(0, 0, 5, 0);
        gbc_txt_asientosEjecutivo.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_asientosEjecutivo.gridx = 3;
        gbc_txt_asientosEjecutivo.gridy = 1;
        panel.add(txt_asientosEjecutivo, gbc_txt_asientosEjecutivo);
        txt_asientosEjecutivo.setColumns(10);
        
        lblNewLabel_2 = new JLabel("Fecha de alta");
        GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
        gbc_lblNewLabel_2.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
        gbc_lblNewLabel_2.gridx = 2;
        gbc_lblNewLabel_2.gridy = 2;
        panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
        
        txt_fechaAlta = new JTextField();
        txt_fechaAlta.setEditable(false);
        GridBagConstraints gbc_txt_fechaAlta = new GridBagConstraints();
        gbc_txt_fechaAlta.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_fechaAlta.gridx = 3;
        gbc_txt_fechaAlta.gridy = 2;
        panel.add(txt_fechaAlta, gbc_txt_fechaAlta);
        txt_fechaAlta.setColumns(10);
        
        
       //SELECCION CLIENTE  
        lblSeleccioneUnCliente = new JLabel("Seleccionar pasajero principal:");
        GridBagConstraints gbclblSeleccioneUnCliente = new GridBagConstraints();
        gbclblSeleccioneUnCliente.anchor = GridBagConstraints.EAST;
        gbclblSeleccioneUnCliente.insets = new Insets(0, 0, 5, 5);
        gbclblSeleccioneUnCliente.gridx = 0;
        gbclblSeleccioneUnCliente.gridy = 7;
        getContentPane().add(lblSeleccioneUnCliente, gbclblSeleccioneUnCliente);
        
        comboBoxCliente = new JComboBox();
        GridBagConstraints gbccomboBoxCliente = new GridBagConstraints();
        gbccomboBoxCliente.gridwidth = 2;
        gbccomboBoxCliente.insets = new Insets(0, 0, 5, 5);
        gbccomboBoxCliente.fill = GridBagConstraints.HORIZONTAL;
        gbccomboBoxCliente.gridx = 1;
        gbccomboBoxCliente.gridy = 7;
        getContentPane().add(comboBoxCliente, gbccomboBoxCliente);
        	
        lblTipoAsiento = new JLabel("Seleccione tipo de Asiento:");
        GridBagConstraints gbclblTipoAsiento = new GridBagConstraints();
        gbclblTipoAsiento.anchor = GridBagConstraints.EAST;
        gbclblTipoAsiento.insets = new Insets(0, 0, 5, 5);
        gbclblTipoAsiento.gridx = 0;
        gbclblTipoAsiento.gridy = 8;
        getContentPane().add(lblTipoAsiento, gbclblTipoAsiento);
                
        comboBoxTipoAsiento = new JComboBox();
        comboBoxTipoAsiento.addItem(tipoAsiento.Turista);
        comboBoxTipoAsiento.addItem(tipoAsiento.Ejecutivo);
        GridBagConstraints gbccomboBoxTipoAsiento = new GridBagConstraints();
        gbccomboBoxTipoAsiento.gridwidth = 2;
        gbccomboBoxTipoAsiento.insets = new Insets(0, 0, 5, 5);
        gbccomboBoxTipoAsiento.fill = GridBagConstraints.HORIZONTAL;
        gbccomboBoxTipoAsiento.gridx = 1;
        gbccomboBoxTipoAsiento.gridy = 8;
        getContentPane().add(comboBoxTipoAsiento, gbccomboBoxTipoAsiento);

        lblCantPasajes = new JLabel("Cantidad de pasajes adicionales:");
        GridBagConstraints gbclblCantPasajes = new GridBagConstraints();
        gbclblCantPasajes.anchor = GridBagConstraints.EAST;
        gbclblCantPasajes.insets = new Insets(0, 0, 5, 5);
        gbclblCantPasajes.gridx = 0;
        gbclblCantPasajes.gridy = 9;
        getContentPane().add(lblCantPasajes, gbclblCantPasajes);
        
        textFieldCantPasajes = new JTextField();
        GridBagConstraints gbctextFieldCantPasajes = new GridBagConstraints();
        gbctextFieldCantPasajes.gridwidth = 2;
        gbctextFieldCantPasajes.insets = new Insets(0, 0, 5, 5);
        gbctextFieldCantPasajes.fill = GridBagConstraints.HORIZONTAL;
        gbctextFieldCantPasajes.gridx = 1;
        gbctextFieldCantPasajes.gridy = 9;
        getContentPane().add(textFieldCantPasajes, gbctextFieldCantPasajes);
        textFieldCantPasajes.setColumns(10);
        
        lblEquipExtra = new JLabel("Equipaje Extra:");
        GridBagConstraints gbclblEquipExtra = new GridBagConstraints();
        gbclblEquipExtra.anchor = GridBagConstraints.EAST;
        gbclblEquipExtra.insets = new Insets(0, 0, 5, 5);
        gbclblEquipExtra.gridx = 0;
        gbclblEquipExtra.gridy = 10;
        getContentPane().add(lblEquipExtra, gbclblEquipExtra);
        
        textFieldEquipExtra = new JTextField();
        GridBagConstraints gbctextFieldEquipExtra = new GridBagConstraints();
        gbctextFieldEquipExtra.gridwidth = 2;
        gbctextFieldEquipExtra.insets = new Insets(0, 0, 5, 5);
        gbctextFieldEquipExtra.fill = GridBagConstraints.HORIZONTAL;
        gbctextFieldEquipExtra.gridx = 1;
        gbctextFieldEquipExtra.gridy = 10;
        getContentPane().add(textFieldEquipExtra, gbctextFieldEquipExtra);
        textFieldEquipExtra.setColumns(10);
        
        lblNewLabel_5 = new JLabel("Datos de pasajeros adicionales: ");
        GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
        gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel_5.gridx = 0;
        gbc_lblNewLabel_5.gridy = 11;
        getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
        
        panel_1 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.gridwidth = 2;
        gbc_panel_1.gridheight = 3;
        gbc_panel_1.insets = new Insets(0, 0, 5, 5);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 11;
        getContentPane().add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{0, 0, 0};
        gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
        gbl_panel_1.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);
        
        txt_nombrePasajero = new JTextField();
        GridBagConstraints gbc_txt_nombrePasajero = new GridBagConstraints();
        gbc_txt_nombrePasajero.insets = new Insets(0, 0, 5, 5);
        gbc_txt_nombrePasajero.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_nombrePasajero.gridx = 0;
        gbc_txt_nombrePasajero.gridy = 0;
        panel_1.add(txt_nombrePasajero, gbc_txt_nombrePasajero);
        txt_nombrePasajero.setColumns(10);
        
        txt_apellidoPasajero = new JTextField();
        GridBagConstraints gbc_txt_apellidoPasajero = new GridBagConstraints();
        gbc_txt_apellidoPasajero.insets = new Insets(0, 0, 5, 0);
        gbc_txt_apellidoPasajero.fill = GridBagConstraints.HORIZONTAL;
        gbc_txt_apellidoPasajero.gridx = 1;
        gbc_txt_apellidoPasajero.gridy = 0;
        panel_1.add(txt_apellidoPasajero, gbc_txt_apellidoPasajero);
        txt_apellidoPasajero.setColumns(10);
        
        listPasajeros = new JList();
        GridBagConstraints gbc_listPasajeros = new GridBagConstraints();
        gbc_listPasajeros.gridheight = 2;
        gbc_listPasajeros.gridwidth = 2;
        gbc_listPasajeros.insets = new Insets(0, 0, 5, 5);
        gbc_listPasajeros.fill = GridBagConstraints.BOTH;
        gbc_listPasajeros.gridx = 0;
        gbc_listPasajeros.gridy = 1;
        panel_1.add(listPasajeros, gbc_listPasajeros);
        
        btnAgregarPasajero = new JButton("Agregar pasajero");
        btnAgregarPasajero.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                pasajeros.add(txt_nombrePasajero.getText() + " " + txt_apellidoPasajero.getText());
                listPasajeros.setListData(pasajeros.toArray());
                txt_nombrePasajero.setText("");
                txt_apellidoPasajero.setText("");
        	}
        });
        GridBagConstraints gbc_btnAgregarPasajero = new GridBagConstraints();
        gbc_btnAgregarPasajero.insets = new Insets(0, 0, 5, 5);
        gbc_btnAgregarPasajero.gridx = 3;
        gbc_btnAgregarPasajero.gridy = 11;
        getContentPane().add(btnAgregarPasajero, gbc_btnAgregarPasajero);
        
        btnEliminarPasajero = new JButton("Eliminar Pasajero");
        btnEliminarPasajero.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                List jListpasajeros = listPasajeros.getSelectedValuesList();
                for (int i = 0; i < jListpasajeros.size(); i++) {
                    pasajeros.remove(jListpasajeros.get(i).toString());
                    listPasajeros.setListData(pasajeros.toArray());
                }
        	}
        });
        GridBagConstraints gbc_btnEliminarPasajero = new GridBagConstraints();
        gbc_btnEliminarPasajero.insets = new Insets(0, 0, 5, 5);
        gbc_btnEliminarPasajero.gridx = 3;
        gbc_btnEliminarPasajero.gridy = 12;
        getContentPane().add(btnEliminarPasajero, gbc_btnEliminarPasajero);
        
        lblFechaAlta = new JLabel("Fecha de reserva(dd/mm/yyyy):");
        GridBagConstraints gbclblFechaAlta = new GridBagConstraints();
        gbclblFechaAlta.gridheight = 2;
        gbclblFechaAlta.insets = new Insets(0, 0, 5, 5);
        gbclblFechaAlta.gridx = 0;
        gbclblFechaAlta.gridy = 14;
        getContentPane().add(lblFechaAlta, gbclblFechaAlta);
        
        panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.gridheight = 2;
        gbc_panel_2.gridwidth = 2;
        gbc_panel_2.insets = new Insets(0, 0, 5, 5);
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 1;
        gbc_panel_2.gridy = 14;
        getContentPane().add(panel_2, gbc_panel_2);
        
        txtDiaA = new JTextField();
        txtDiaA.setVisible(true);
        txtDiaA.setColumns(3);
        
        txtMesA = new JTextField();
        txtMesA.setVisible(true);
        txtMesA.setColumns(3);
        
        txtAnioA = new JTextField();
        txtAnioA.setVisible(true);
        txtAnioA.setColumns(3);
        panel_2.setLayout(new GridLayout(0, 3, 0, 0));
        panel_2.add(txtDiaA);
        panel_2.add(txtMesA);
        panel_2.add(txtAnioA);
        
        btnReservar = new JButton("Reservar");
        GridBagConstraints gbcbtnReservar = new GridBagConstraints();
        gbcbtnReservar.insets = new Insets(0, 0, 0, 5);
        gbcbtnReservar.gridx = 1;
        gbcbtnReservar.gridy = 16;
        getContentPane().add(btnReservar, gbcbtnReservar);
        
        btnCancelar = new JButton("Cancelar");
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 16;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
        btnReservar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent event) {
                String nombreVuelo = comboBoxVuelos.getSelectedItem().toString();
                Vuelo vueloInstancia = VuelosHandler.getinstance().obtenerVuelo(nombreVuelo);
                LocalDate fechaVuelo = vueloInstancia.getFecha();
                String cliente = comboBoxCliente.getSelectedItem().toString();
                Integer cantPasajes = Integer.parseInt(textFieldCantPasajes.getText()) + 1;
                Integer cantEquipaje = Integer.parseInt(textFieldEquipExtra.getText()); 

                //FECHA
                String diaStr = txtDiaA.getText().trim();
                String mesStr = txtMesA.getText().trim();
                String anioStr = txtAnioA.getText().trim();
                
                int dia = Integer.parseInt(diaStr);
                int mes = Integer.parseInt(mesStr);
                int anio = Integer.parseInt(anioStr);
                
                LocalDate fechaReserva = LocalDate.of(anio, mes, dia); 
	            tipoAsiento tipoAsientoSeleccionado = (tipoAsiento) comboBoxTipoAsiento.getSelectedItem();
                
                try {
                	if((Integer.parseInt(txt_asientosTurista.getText()) >= cantPasajes && tipoAsientoSeleccionado == tipoAsiento.Turista && fechaVuelo.isBefore(fechaReserva)) || (Integer.parseInt(txt_asientosEjecutivo.getText()) >= cantPasajes && tipoAsientoSeleccionado == tipoAsiento.Ejecutivo && fechaVuelo.isBefore(fechaReserva))) {
	                    cVuelos.reservarVuelo(nombreVuelo, cliente, tipoAsientoSeleccionado, cantPasajes, cantEquipaje, fechaReserva, pasajeros);
	                    JOptionPane.showMessageDialog(null, "Vuelo reservado con éxito");
	                    limpiarFormulario();
                	} else {
                		JOptionPane.showMessageDialog(null, "Error al reservar vuelo: No hay suficientes asientos");
                	}
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al reservar vuelo: " + e.getMessage());
                }
            }
        });

        actualizarRutasVuelo();
        actualizarAerolineas();
        actualizarClientes();
}

    protected void cmdBuscarAerolinea(ActionEvent event) {
		if (comboBoxAerolinea.getSelectedItem() != null) {
			lblSeleccioneUnaRutaVuelo.setVisible(true);
			comboBoxRutasVuelo.setVisible(true);
		}
	}
    
    protected void cmdBuscarVuelo(ActionEvent event) {
		if (comboBoxRutasVuelo.getSelectedItem() != null) {
			lblSeleccioneUnaRutaVuelo.setVisible(true);
			comboBoxVuelos.setVisible(true);
			btnVerInfoVuelo.setVisible(true);

		}
	}
    
    protected void actualizarAerolineas() {
    	comboBoxAerolinea.removeAllItems();
		comboBoxAerolinea.addItem("Seleccionar aerolinea");
		comboBoxAerolinea.setSelectedIndex(0);
		try {
			aerolineas = controlUsr.getAerolineas();
			for (DataAerolinea dataAerolinea : aerolineas) {
				comboBoxAerolinea.addItem(dataAerolinea.getNickname());
			}
		} catch (UsuarioNoExisteExce e) {
			e.printStackTrace();
		}
	}
    protected void actualizarClientes() {
    	comboBoxCliente.removeAllItems();
		comboBoxCliente.addItem("Seleccionar Cliente");
		comboBoxCliente.setSelectedIndex(0);
		try {
			DataCliente[] clientes = controlUsr.getClientes();
            ArrayList<String> nicknames = new ArrayList<String>();
			for (DataCliente dataCli : clientes) {
				comboBoxCliente.addItem(dataCli.getNickname());
                nicknames.add(dataCli.getNickname());
			}
		} catch (UsuarioNoExisteExce e) {
			e.printStackTrace();
		}
	}
    
    protected void actualizarRutasVuelo() {
		comboBoxRutasVuelo.removeAllItems();
		comboBoxRutasVuelo.addItem("Seleccionar ruta de vuelo");
		comboBoxRutasVuelo.setSelectedIndex(0);
		try {
            if (this.comboBoxAerolinea.getSelectedItem() == null) {
                return;
            }
            
			String aer = this.comboBoxAerolinea.getSelectedItem().toString();
			aer = aer.split(" ", 2)[0];
			aerolinea = controlUsr.verInfoAerolinea(aer);
			ArrayList<DataRutaVuelo> rutasVueloAero = aerolinea.getRutasVuelo();
			for (DataRutaVuelo datarv : rutasVueloAero) {
				comboBoxRutasVuelo.addItem(datarv.getNombre());
			}

		} catch (UsuarioNoExisteExce e) {

		}
	}
    protected void actualizarVuelos() {
		comboBoxVuelos.removeAllItems();
		comboBoxVuelos.addItem("Seleccionar vuelo");
		comboBoxVuelos.setSelectedIndex(0);
		try {
            if (comboBoxRutasVuelo.getSelectedItem() == null) {
                return;
            }
			String rv = this.comboBoxRutasVuelo.getSelectedItem().toString();
			rv = rv.split(" ", 2)[0];
			DataRutaVuelo rutavuelo = controlRutasVuelo.verInfoRutaVuelo(rv);
			ArrayList<DataVuelo> Vuelos_RV = rutavuelo.getVuelos();
			for (DataVuelo vuelo : Vuelos_RV) {
				comboBoxVuelos.addItem(vuelo.getNombre());
			}

		} catch (RVNoExisteExce e) {

		}
	}
    
    private void limpiarFormulario() {
        comboBoxAerolinea.setSelectedIndex(0);
        comboBoxRutasVuelo.setSelectedIndex(0);
        comboBoxVuelos.setSelectedIndex(0);
        comboBoxCliente.setSelectedIndex(-1);
        comboBoxTipoAsiento.setSelectedIndex(-1);
        textFieldCantPasajes.setText("");
        textFieldEquipExtra.setText("");
        txtDiaA.setText("");
        txtMesA.setText("");
        txtAnioA.setText("");
        txt_nombrePasajero.setText("");
        txt_apellidoPasajero.setText("");
        listPasajeros.setListData(new String[0]);
    };
    
    

}
