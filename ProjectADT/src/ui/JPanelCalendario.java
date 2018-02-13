package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import clases.Calendario;
import clases.Serie;
import datos.Manager;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class JPanelCalendario extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1448297934801688133L;
	private JButton btnAadirRegistro;
	private JButton btnBorrarSeleccionado;
	private Color colorTablasFondo =new Color(219, 219, 219);
	private Manager manager=new Manager();
	private JTable tablaLunes;
	private JTable tablaMartes;
	private JTable tablaMiercoles;
	private JTable tablaJueves;
	private JTable tablaViernes;
	private JTable tablaSabado;
	private JTable tablaDomingo;

	/**
	 * Crea el panel de calendario
	 */
	public JPanelCalendario() {
		
		setSize(755,729);
		setLayout(null);

		botones();
		creacionLabels();
		crearTablas();
		//cargarDatosEnModelo();
	}

	/**
	 * Configuración de los botones
	 */
	private void botones() {
		btnAadirRegistro = new JButton("A\u00F1adir registro");
		btnAadirRegistro.addActionListener(new ActionListener() {
			//LLama a nuevoCalendario para efectuar un nuevo registro con la accion de "añadir"
			public void actionPerformed(ActionEvent e) {
				JDialogNuevoCalendario nuevoRegistro= new JDialogNuevoCalendario("añadir");
				nuevoRegistro.setVisible(true);
				System.out.println("siso");
				cargarDatosEnModelo();
				
			}
		});
		btnAadirRegistro.setBounds(231, 39, 133, 23);
		add(btnAadirRegistro);
		
		btnBorrarSeleccionado = new JButton("Borrar registro");
		btnBorrarSeleccionado.addActionListener(new ActionListener() {
			//Llama a nuevoCalendario para borrar un registro con la accion "borrar"
			public void actionPerformed(ActionEvent arg0) {
				JDialogNuevoCalendario nuevoRegistro= new JDialogNuevoCalendario("borrar");
				nuevoRegistro.setVisible(true);
				cargarDatosEnModelo();
			}
		});
		btnBorrarSeleccionado.setBounds(403, 39, 151, 23);
		add(btnBorrarSeleccionado);
		
	}

	public void cargarDatosEnModelo() {
		DefaultTableModel tableModel = new DefaultTableModel();
		
		Vector<String> row = new Vector<String>();
		ArrayList<Calendario> calendarios = new  ArrayList<Calendario>();
		calendarios = manager.getCalendarios(JFramePrincipal.getUsuarioConectado(), JFramePrincipal.getTipo());
		System.out.println("interfaz"+calendarios.size());
		int numDia=0;
		tableModel.addColumn("Series");
		for (Calendario calendario : calendarios) {

			
			System.out.println("dentro "+calendario.getDia());
			System.out.println("tamanio series: "+calendario.getSeries().size());
			for(Serie serie :calendario.getSeries()) {
				System.out.println(serie.getNombre().toString());
				row.add(serie.getNombre());
				tableModel.addRow(row);
				System.out.println("row count "+tableModel.getRowCount());
				row = new Vector <String>();
			}
			
			asignarModelosConDatos(tableModel,calendario.getDia());		
			tableModel = new DefaultTableModel();
			tableModel.addColumn("Series");

		}

		
	}

	/**
	 * Crea las tablas cuando se inicia la ventana
	 */
	private void crearTablas() {
			tablaLunes= new JTable();
			JScrollPane scrollTablaLunes = new JScrollPane(tablaLunes);
			parametrosTabla(tablaLunes,scrollTablaLunes,25,90,190,155);	
			
			tablaMartes= new JTable();
			JScrollPane scrollTablaMartes = new JScrollPane(tablaMartes);
			parametrosTabla(tablaMartes,scrollTablaMartes,283, 90, 190, 155);

			tablaMiercoles= new JTable();
			JScrollPane scrollTablaMiercoles = new JScrollPane(tablaMiercoles);
			parametrosTabla(tablaMiercoles,scrollTablaMiercoles,530, 90, 190, 155);	

			tablaJueves= new JTable();
			JScrollPane scrollTablaJueves = new JScrollPane(tablaJueves);
			parametrosTabla(tablaJueves,scrollTablaJueves,142, 284, 190, 180);	

			tablaViernes= new JTable();
			JScrollPane scrolltablaViernes = new JScrollPane(tablaViernes);
			parametrosTabla(tablaViernes,scrolltablaViernes,403, 284, 190, 180);	

			tablaSabado= new JTable();
			JScrollPane scrollTablaSabado = new JScrollPane(tablaSabado);
			parametrosTabla(tablaSabado,scrollTablaSabado,142, 510, 190, 190);	

			tablaDomingo= new JTable();
			JScrollPane scrollTablaDomingo = new JScrollPane(tablaDomingo);
			parametrosTabla(tablaDomingo,scrollTablaDomingo,403, 510, 190, 190);
			cargarDatosEnModelo();
	}
	
	private void asignarModelosConDatos(DefaultTableModel tableModel, String dia) {
		dia = dia.toLowerCase();
		switch(dia) {
		case "lunes":
			tablaLunes.setModel(tableModel);
			break;
		case "martes":
			tablaMartes.setModel(tableModel);
			break;
		case "miércoles":
			tablaMiercoles.setModel(tableModel);
			break;
		case "jueves":
			tablaJueves.setModel(tableModel);
			break;
		case "viernes":
			tablaViernes.setModel(tableModel);
			break;
		case "sábado":
			tablaSabado.setModel(tableModel);
			break;
		case "domingo":
			tablaDomingo.setModel(tableModel);
			break;
		}
		
		
	}

	private void parametrosTabla(JTable tabla, JScrollPane scroll, int x, int y, int alto, int ancho) {

		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setBounds(x,y,alto,ancho);
		tabla.setFillsViewportHeight(true);
		add(scroll);
		tabla.setFillsViewportHeight(true);
		
		tabla.setEnabled(false);
		//tablaLunes.setFocusable(true);
		//tablaLunes.setRowSelectionAllowed(true);
	//	tablaLunes.setBackground(colorTablasFondo);
		tabla.setShowGrid(false);
		tabla.setRowHeight(20);
		//tabla.setTableHeader(null);
		
		this.repaint();
		this.validate();
	
	}



	/**
	 * Crea los labels que contendrán los nombres de los dias y los posiciona en el panel
	 */
	private void creacionLabels() {
		
		JLabel lblCalendario = new JLabel("Calendario");
		lblCalendario.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalendario.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblCalendario.setBounds(10, 11, 735, 26);
		add(lblCalendario); 
		
		JLabel lbllunes = new JLabel("LUNES");
		lbllunes.setVerticalAlignment(SwingConstants.BOTTOM);
		lbllunes.setHorizontalAlignment(SwingConstants.CENTER);
		lbllunes.setBounds(25, 73, 190, 14);
		add(lbllunes);
	
		
		JLabel lblMartes = new JLabel("MARTES");
		lblMartes.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMartes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMartes.setBounds(283, 73, 190, 14);
		add(lblMartes);
		
		JLabel lblMiercoles = new JLabel("MI\u00C9RCOLES");
		lblMiercoles.setVerticalAlignment(SwingConstants.BOTTOM);
		lblMiercoles.setHorizontalAlignment(SwingConstants.CENTER);
		lblMiercoles.setBounds(530, 73, 190, 14);
		add(lblMiercoles);
		
		JLabel lblJueves = new JLabel("JUEVES");
		lblJueves.setHorizontalAlignment(SwingConstants.CENTER);
		lblJueves.setBounds(142, 262, 190, 14);
		add(lblJueves);
		
		JLabel lblViernes = new JLabel("VIERNES");
		lblViernes.setHorizontalAlignment(SwingConstants.CENTER);
		lblViernes.setBounds(403, 262, 190, 14);
		add(lblViernes);
		

		JLabel lblSabado = new JLabel("S\u00C1BADO\r\n");
		lblSabado.setHorizontalAlignment(SwingConstants.CENTER);
		lblSabado.setBounds(142, 485, 190, 14);
		add(lblSabado);
		
		JLabel lblDomingo = new JLabel("DOMINGO\r\n");
		lblDomingo.setHorizontalAlignment(SwingConstants.CENTER);
		lblDomingo.setBounds(403, 485, 190, 14);
		add(lblDomingo);
		
	}

	public Color getColorTablasFondo() {
		return colorTablasFondo;
	}

	public void setColorTablasFondo(Color colorTablasFondo) {
		this.colorTablasFondo = colorTablasFondo;
	}
}
