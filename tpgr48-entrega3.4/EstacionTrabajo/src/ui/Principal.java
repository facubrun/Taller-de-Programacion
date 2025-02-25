package ui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import logica.controllers.DatosPruebaController;
import servidor.PublicadorPaquete;
import servidor.PublicadorRutasVuelo;
import servidor.PublicadorUsuarios;
import servidor.PublicadorVuelos;


public class Principal {

	private JFrame frame;

	private PublicadorUsuarios publicadorUsuario;
	private PublicadorRutasVuelo publicadorRutasVuelo;
	private PublicadorVuelos publicadorVuelo;
	private PublicadorPaquete publicadorPaquete;
	
	private DatosPruebaController datosPruebaController;

	private CrearUsuario creUsrInternalFrame;
	private ConsultarUsuario conUsrInternalFrame;
	private ModificarUsuario modUsrInternalFrame;

	private ReservaVuelo resVInternalFrame;

	private AltaRutaVuelo altaRVInternalFrame;
	private ConsultaRutaVuelo consultaRVInternalFrame;
	private AceptarRutaVuelo aceptarRVInternalFrame;
	private AltaCiudad altaCiudadInternalFrame;
	private VisitasRutasVuelo visitasRutasVueloInternalFrame;

	private AltaVuelo altaVueloInternalFrame;
	private ConsultaVuelo consultaVueloInternalFrame;

	private AgregarRutaVueloAPaquete AgregarRVaPaqueteInternalFrame;
	private CrearPaquete crearPaqueteInternalFrame;
	private ConsultarPaquete ConsultarPaqueteInternalFrame;
	private ComprarPaquete ComprarPaqueteInternalFrame;

	private Properties prop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application. 
	 */
	public Principal() {
		initialize();
		cargarConfig();

		creUsrInternalFrame = new CrearUsuario();
		creUsrInternalFrame.setVisible(false);
		conUsrInternalFrame = new ConsultarUsuario();
		conUsrInternalFrame.setVisible(false);
		modUsrInternalFrame = new ModificarUsuario();
		modUsrInternalFrame.setVisible(false);

		altaRVInternalFrame = new AltaRutaVuelo();
		altaRVInternalFrame.setVisible(false);
		consultaRVInternalFrame = new ConsultaRutaVuelo();
		consultaRVInternalFrame.setVisible(false);
		aceptarRVInternalFrame = new AceptarRutaVuelo();
		aceptarRVInternalFrame.setVisible(false);
		altaCiudadInternalFrame = new AltaCiudad();
		altaCiudadInternalFrame.setVisible(false);

		altaVueloInternalFrame = new AltaVuelo();
		altaVueloInternalFrame.setVisible(false);

		altaCiudadInternalFrame = new AltaCiudad();
		altaCiudadInternalFrame.setVisible(false);

		AgregarRVaPaqueteInternalFrame = new AgregarRutaVueloAPaquete();
		AgregarRVaPaqueteInternalFrame.setVisible(false);
		
		ConsultarPaqueteInternalFrame = new ConsultarPaquete();
		ConsultarPaqueteInternalFrame.setVisible(false);
		
		ComprarPaqueteInternalFrame = new ComprarPaquete();
		ComprarPaqueteInternalFrame.setVisible(false);

		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(creUsrInternalFrame);
		frame.getContentPane().add(conUsrInternalFrame);
		frame.getContentPane().add(modUsrInternalFrame);

		frame.getContentPane().add(altaRVInternalFrame);
		frame.getContentPane().add(consultaRVInternalFrame);
		frame.getContentPane().add(aceptarRVInternalFrame);
		frame.getContentPane().add(altaCiudadInternalFrame);

		frame.getContentPane().add(altaVueloInternalFrame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Administracion Volando.uy");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit myScreen = Toolkit.getDefaultToolkit();

		frame.setSize(myScreen.getScreenSize().width * 2 / 3, myScreen.getScreenSize().height * 2 / 3);
		frame.setLocation(myScreen.getScreenSize().width / 5, myScreen.getScreenSize().height / 5);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuSistema = new JMenu("Sistema");
		menuBar.add(menuSistema);

		JMenuItem menuDatos = new JMenuItem("Datos de prueba");
		menuDatos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cargarDatosDePrueba(arg0);
			}
		});

		JMenuItem menuSalir = new JMenuItem("Salir");
		menuSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
			}
		});

		// Nueva opción de menú para Publicar Servicios
        JMenuItem menuPublicar = new JMenuItem("Publicar Servicios");
        menuPublicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	publicarServicios();
            }
        });

		menuSistema.add(menuDatos);
		menuSistema.add(menuPublicar);
		menuSistema.add(menuSalir);

		JMenu menuUsuarios = new JMenu("Usuarios");
		menuBar.add(menuUsuarios);

		JMenuItem menuItemRegistrar = new JMenuItem("Registrar usuario");
		menuItemRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				creUsrInternalFrame.setVisible(true);
			}
		});
		menuUsuarios.add(menuItemRegistrar);

		JMenuItem menuItemVerInfo = new JMenuItem("Consultar Usuario");
		menuItemVerInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				conUsrInternalFrame.setVisible(true);
			}
		});
		menuUsuarios.add(menuItemVerInfo);

		JMenuItem mntmListaUsuarios = new JMenuItem("Modificar usuario");
		mntmListaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				modUsrInternalFrame.setVisible(true);
			}
		});
		menuUsuarios.add(mntmListaUsuarios);

		JMenu menuVuelos = new JMenu("Vuelos");
		menuBar.add(menuVuelos);

		JMenuItem menuItemResV = new JMenuItem("Reserva de vuelo");
		menuItemResV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				resVInternalFrame = new ReservaVuelo();
				resVInternalFrame.setVisible(true);
				frame.getContentPane().add(resVInternalFrame);
				resVInternalFrame.toFront();
			}
		});
		menuVuelos.add(menuItemResV);

		JMenu menuAltaRV = new JMenu("Rutas de vuelo");
		menuBar.add(menuAltaRV);

		JMenuItem menuItemAltaRV = new JMenuItem("Alta de ruta de vuelo");
		menuItemAltaRV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
////				altaRVInternalFrame.actualizarAerolineas();
////				altaRVInternalFrame.setVisible(true);
//				if (datosPruebaController == null) {
//		            datosPruebaController = new DatosPruebaController();
//		            try {
//						datosPruebaController.cargarCiudades();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}  
//		        }
//		        altaRVInternalFrame.actualizarAerolineas(); 

//		        altaRVInternalFrame.setVisible(true);
				altaRVInternalFrame = new AltaRutaVuelo();
				altaRVInternalFrame.setVisible(true);
				frame.getContentPane().add(altaRVInternalFrame);
				altaRVInternalFrame.actualizarAerolineas();
		        altaRVInternalFrame.cargarCiudadesEnComboBox(); 
		        altaRVInternalFrame.cargarCategoriasEnList();
				if (datosPruebaController == null) {
		            datosPruebaController = new DatosPruebaController();
		            try {
						datosPruebaController.cargarCiudades();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		        }
				
				altaRVInternalFrame.toFront();
				
			}
		});
		menuAltaRV.add(menuItemAltaRV);

		JMenuItem menuItemConsultaRV = new JMenuItem("Consultar ruta de vuelo");
		menuItemConsultaRV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				consultaRVInternalFrame.actualizarAerolineas();
//				consultaRVInternalFrame.setVisible(true);
				
				consultaRVInternalFrame = new ConsultaRutaVuelo();
				consultaRVInternalFrame.setVisible(true);
				frame.getContentPane().add(consultaRVInternalFrame);
				consultaRVInternalFrame.actualizarAerolineas();
				consultaRVInternalFrame.toFront();
				
			}
		});
		menuAltaRV.add(menuItemConsultaRV);
		
		JMenuItem menuItemAceptarRV = new JMenuItem("Confirmar ruta de vuelo");
		menuItemAceptarRV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				aceptarRVInternalFrame.actualizarAerolineas();
//				aceptarRVInternalFrame.setVisible(true);
				
				aceptarRVInternalFrame = new AceptarRutaVuelo();
				aceptarRVInternalFrame.setVisible(true);
				frame.getContentPane().add(aceptarRVInternalFrame);
				aceptarRVInternalFrame.actualizarAerolineas();
				aceptarRVInternalFrame.toFront();
			}
		});
		menuAltaRV.add(menuItemAceptarRV);
		
		JMenuItem menuItemAltaCiudad = new JMenuItem("Alta de Ciudad");
		menuItemAltaCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				altaCiudadInternalFrame.setVisible(true);
			}
		});
		menuAltaRV.add(menuItemAltaCiudad);
		
		JMenuItem menuItemVisitasRV = new JMenuItem("Visitas a Rutas Vuelo");
		menuItemVisitasRV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				visitasRutasVueloInternalFrame = new VisitasRutasVuelo();
				visitasRutasVueloInternalFrame.setVisible(true);
				frame.getContentPane().add(visitasRutasVueloInternalFrame);
				visitasRutasVueloInternalFrame.toFront();
			}
		});
		menuAltaRV.add(menuItemVisitasRV);

		JMenuItem menuItemAltaVuelo = new JMenuItem("Registrar vuelo");
		menuItemAltaVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
//				altaVueloInternalFrame.actualizarAerolineas();
//				altaVueloInternalFrame.setVisible(true);
				
				altaVueloInternalFrame = new AltaVuelo();
				altaVueloInternalFrame.setVisible(true);
				frame.getContentPane().add(altaVueloInternalFrame);
				altaVueloInternalFrame.actualizarAerolineas();
				altaVueloInternalFrame.toFront();
			}
		});
		menuVuelos.add(menuItemAltaVuelo);

		JMenuItem menuItemConsultaVuelo = new JMenuItem("Consultar vuelo");
		menuItemConsultaVuelo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				consultaVueloInternalFrame = new ConsultaVuelo();
				consultaVueloInternalFrame.setVisible(true);
				frame.getContentPane().add(consultaVueloInternalFrame);
				consultaVueloInternalFrame.toFront();
			}
		});
		menuVuelos.add(menuItemConsultaVuelo);

		JMenu menuPaquetes = new JMenu("Paquetes");
		menuBar.add(menuPaquetes);

		JMenuItem menuItemCrearPaquete = new JMenuItem("Crear Paquete");
		menuItemCrearPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				crearPaqueteInternalFrame = new CrearPaquete();
				crearPaqueteInternalFrame.setVisible(true);
				frame.getContentPane().add(crearPaqueteInternalFrame);
				crearPaqueteInternalFrame.toFront();
			}
		});
		menuPaquetes.add(menuItemCrearPaquete);


		JMenuItem menuItemAgregarRVaPaq = new JMenuItem("Agregar Ruta Vuelo a Paquete");
		menuItemAgregarRVaPaq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				AgregarRVaPaqueteInternalFrame = new AgregarRutaVueloAPaquete();
				AgregarRVaPaqueteInternalFrame.setVisible(true);
				frame.getContentPane().add(AgregarRVaPaqueteInternalFrame);
				AgregarRVaPaqueteInternalFrame.toFront();
			}
		});
	    menuPaquetes.add(menuItemAgregarRVaPaq);
		
	    JMenuItem menuItemConsultarPaquete = new JMenuItem("Consultar Paquete");
		menuItemConsultarPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				ConsultarPaqueteInternalFrame = new ConsultarPaquete();
				ConsultarPaqueteInternalFrame.setVisible(true);
				frame.getContentPane().add(ConsultarPaqueteInternalFrame);
				ConsultarPaqueteInternalFrame.toFront();
			}
		});
	    menuPaquetes.add(menuItemConsultarPaquete);

	JMenuItem menuItemComprarPaquete = new JMenuItem("Comprar Paquete");
	menuItemComprarPaquete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			ComprarPaqueteInternalFrame = new ComprarPaquete();
			ComprarPaqueteInternalFrame.setVisible(true);
			frame.getContentPane().add(ComprarPaqueteInternalFrame);
			ComprarPaqueteInternalFrame.toFront();
		}
	});
    menuPaquetes.add(menuItemComprarPaquete);
}

	private void cargarConfig() {
        prop = new Properties();
       	String configFilePath = System.getProperty("user.home") + "/.volandoUy/config.properties";
        // String configFilePath = "C:/Users/lu_si/git/tpgr48/EstacionTrabajo/config.properties";

        try (InputStream input = new FileInputStream(new File(configFilePath))) {
        	prop.load(input);
            System.out.println("Configuraciones cargadas desde: " + configFilePath);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar el archivo de configuración: " + configFilePath,
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
	
	private void publicarServicios() {
        publicadorUsuario = new PublicadorUsuarios();
        publicadorRutasVuelo = new PublicadorRutasVuelo();
        publicadorVuelo = new PublicadorVuelos();
        publicadorPaquete = new PublicadorPaquete();
        
        String host = prop.getProperty("hostIP");
        String puertoUsuario = prop.getProperty("hostPortUsuario");
        String puertoRutasVuelo = prop.getProperty("hostPortRutasVuelo");
        String puertoVuelos = prop.getProperty("hostPortVuelos");
        String puertoPaquetes = prop.getProperty("hostPortPaquetes");

        try {
            publicadorUsuario.publicar(host, puertoUsuario);
            publicadorRutasVuelo.publicar(host, puertoRutasVuelo);
            publicadorVuelo.publicar(host, puertoVuelos);
            publicadorPaquete.publicar(host, puertoPaquetes);

            JOptionPane.showMessageDialog(frame, "Servicios web publicados exitosamente", "Publicación",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error al publicar servicios web", "Error de Publicación",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

	private void cargarDatosDePrueba(ActionEvent arg0) {
		datosPruebaController = new DatosPruebaController();
		JOptionPane.showMessageDialog(null, "Datos cargados con exito");
		try {
			datosPruebaController.cargarDatosBasicos();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
