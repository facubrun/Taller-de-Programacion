package ui;

import java.awt.EventQueue;
import java.util.Arrays;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import exceptions.RVNoExisteExce;
import logica.Fabrica;
import logica.Datatypes.DataRutaVuelo;
import logica.Interfaces.IRutaVueloController;

public class VisitasRutasVuelo extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private IRutaVueloController ctrlRutasVuelo;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	VisitasRutasVuelo frame = new VisitasRutasVuelo();
                frame.setVisible(true);
            }
        });
    }

    /**
     * Create the frame.
     * @throws RVNoExisteExce 
     */
    public VisitasRutasVuelo() {
        setTitle("Visitas por Ruta de Vuelo");
        setBounds(100, 100, 600, 400); // Adjusted size for better table display
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setClosable(true); // Allow the frame to be closed
        setResizable(true);
        setIconifiable(true); // Allow minimizing
        setMaximizable(true); // Allow maximizing
        setLayout(null);
        
        Fabrica fabrica = Fabrica.getInstance();
        ctrlRutasVuelo = fabrica.getIControladorRutaVuelo();
        
        DataRutaVuelo[] rutas = new DataRutaVuelo[1];
		try {
			rutas = ctrlRutasVuelo.listarRutasVuelo();
		} catch (RVNoExisteExce e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Arrays.sort(rutas, (a, b) -> b.getVisitas().compareTo(a.getVisitas()));
        
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("#");
        tableModel.addColumn("Ruta de Vuelo");
        tableModel.addColumn("Aerolinea");
        tableModel.addColumn("Ciudad Origen");
        tableModel.addColumn("Ciudad Destino");
        tableModel.addColumn("Visitas");
        
        Integer i = 1;
        for (DataRutaVuelo ruta: rutas) {
        	tableModel.addRow(new Object[]{i.toString(), ruta.getNombre(), ruta.getAerolinea(), ruta.getCiudadOrigen(), ruta.getCiudadDestino(), ruta.getVisitas().toString()});
        	i++;
        }

        // Initialize the JTable with the model
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 550, 300);
        add(scrollPane);
    }
}
