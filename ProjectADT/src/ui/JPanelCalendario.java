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

	/**
	 * Crea el panel de calendario
	 */
	public JPanelCalendario() {
		
		setSize(755,729);
		setLayout(null);

		botones();
		creacionLabels();
		crearTablas();
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
			}
		});
		btnBorrarSeleccionado.setBounds(403, 39, 151, 23);
		add(btnBorrarSeleccionado);
		
	}

	/**
	 * Crea las distintas tablas que se usarán para cada día de la semana
	 */
	private void crearTablas() {
		
		/*
		tablaLunes();
		tablaMartes();
		tablaMiercoles();
		tablaJueves();
		tablaViernes();
		tablaSabado();
		tablaDomingo();
		*/
		
		
	}
	private void tablaLunes() {

		//AQUÍ SE RECOGEN DATOS DE LA BASE DE DATOS Y SE PLASMAN EN LA TABLA
		try {
		
			Calendario[] x=new Calendario[1];
			x = manager.getCalendarios(JFramePrincipal.getUsuarioConectado(), (short) 1, JFramePrincipal.getTipo());
			ArrayList <Serie> series=x[1].getSeries();
			Object[][] rowData = {{"House"}};
			String[] columnNames = {"Nombre"};
			//FIN RECOGIDA
				
			DefaultTableModel tableModel = new DefaultTableModel(rowData, columnNames);
			JTable tablaLunes= new JTable(tableModel);
			for (int i = 0; i < series.size(); i++) {
				String nombre=series.get(i).getNombre().toString();
				Object[] data = {nombre};
				tableModel.addRow(data);
			}
				
			JScrollPane scrollTablaLunes = new JScrollPane(tablaLunes);
			scrollTablaLunes.setBorder(BorderFactory.createEmptyBorder());
			scrollTablaLunes.setBounds(25, 90, 190, 155);
			tablaLunes.setFillsViewportHeight(true);
			add(scrollTablaLunes);
			tablaLunes.setEnabled(false);
			//tablaLunes.setFocusable(true);
			//tablaLunes.setRowSelectionAllowed(true);
			
			
			tablaLunes.setBackground(colorTablasFondo);
			tablaLunes.setShowGrid(false);
			tablaLunes.setRowHeight(20);
			tablaLunes.setTableHeader(null);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
}

	private void tablaMartes() {
		
		//AQUÍ SE RECOGEN DATOS DE LA BASE DE DATOS Y SE PLASMAN EN LA TABLA

		
		try {
			Calendario[] x=new Calendario[1];
			x = manager.getCalendarios(JFramePrincipal.getUsuarioConectado(), (short) 2, JFramePrincipal.getTipo());
			ArrayList <Serie> series=x[1].getSeries();
			String[] columnNames = {"Nombre"};

			DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
			JTable tablaMartes= new JTable(tableModel);
			for (int i = 0; i < series.size(); i++) {
				String nombre=series.get(i).getNombre().toString();
				Object[] data = {nombre};
				tableModel.addRow(data);
			}
			//FIN RECOGIDA

				
			JScrollPane scrollTablaMartes = new JScrollPane(tablaMartes);
			scrollTablaMartes.setBorder(BorderFactory.createEmptyBorder());
			scrollTablaMartes.setBounds(283, 90, 190, 155);
			tablaMartes.setFillsViewportHeight(true);
			add(scrollTablaMartes);
			tablaMartes.setBackground(colorTablasFondo);
			tablaMartes.setShowGrid(false);
			tablaMartes.setRowHeight(20);
			tablaMartes.setTableHeader(null);
			tablaMartes.setEnabled(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void tablaMiercoles() {
		
		//AQUÍ SE RECOGEN DATOS DE LA BASE DE DATOS Y SE PLASMAN EN LA TABLA
		try {
			Calendario[] x=new Calendario[1];
			x = manager.getCalendarios(JFramePrincipal.getUsuarioConectado(), (short) 3, JFramePrincipal.getTipo());
			ArrayList <Serie> series=x[1].getSeries();
			String[] columnNames = {"Nombre"};
				
			DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
			JTable tablaMiercoles= new JTable(tableModel);
			for (int i = 0; i < series.size(); i++) {
				String nombre=series.get(i).getNombre().toString();
				Object[] data = {nombre};
				tableModel.addRow(data);
			}
		//FIN RECOGIDA
			
			JScrollPane scrollTablaMiercoles = new JScrollPane(tablaMiercoles);
			scrollTablaMiercoles.setBorder(BorderFactory.createEmptyBorder());
			scrollTablaMiercoles.setBounds(530, 90, 190, 155);
			tablaMiercoles.setFillsViewportHeight(true);
			add(scrollTablaMiercoles);
			tablaMiercoles.setBackground(colorTablasFondo);
			tablaMiercoles.setShowGrid(false);
			tablaMiercoles.setRowHeight(20);
			tablaMiercoles.setTableHeader(null);
			tablaMiercoles.setEnabled(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tablaJueves() {
		
		//AQUÍ SE RECOGEN DATOS DE LA BASE DE DATOS Y SE PLASMAN EN LA TABLA
		try {
			Calendario[] x=new Calendario[1];
			x = manager.getCalendarios(JFramePrincipal.getUsuarioConectado(), (short) 4, JFramePrincipal.getTipo());
			ArrayList <Serie> series=x[1].getSeries();
			String[] columnNames = {"Nombre"};
				
			DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
			JTable tablaJueves= new JTable(tableModel);
			for (int i = 0; i < series.size(); i++) {
				String nombre=series.get(i).getNombre().toString();
				Object[] data = {nombre};
				tableModel.addRow(data);
			}
		//FIN RECOGIDA
			
			JScrollPane scrollTablaJueves = new JScrollPane(tablaJueves);
			scrollTablaJueves.setBorder(BorderFactory.createEmptyBorder());
			scrollTablaJueves.setBounds(142, 284, 190, 180);
			tablaJueves.setFillsViewportHeight(true);
			add(scrollTablaJueves);
			tablaJueves.setBackground(colorTablasFondo);
			tablaJueves.setShowGrid(false);
			tablaJueves.setRowHeight(20);
			tablaJueves.setTableHeader(null);
			tablaJueves.setEnabled(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void tablaViernes() {
		
		//AQUÍ SE RECOGEN DATOS DE LA BASE DE DATOS Y SE PLASMAN EN LA TABLA
		try {
			Calendario[] x=new Calendario[1];
			x = manager.getCalendarios(JFramePrincipal.getUsuarioConectado(), (short) 5, JFramePrincipal.getTipo());
			ArrayList <Serie> series=x[1].getSeries();
			String[] columnNames = {"Nombre"};
				
			DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
			JTable tablaViernes= new JTable(tableModel);
			for (int i = 0; i < series.size(); i++) {
				String nombre=series.get(i).getNombre().toString();
				Object[] data = {nombre};
				tableModel.addRow(data);
			}
		//FIN RECOGIDA
			
			JScrollPane scrollTablaViernes = new JScrollPane(tablaViernes);
			scrollTablaViernes.setBorder(BorderFactory.createEmptyBorder());
			scrollTablaViernes.setBounds(403, 284, 190, 180);
			tablaViernes.setFillsViewportHeight(true);
			add(scrollTablaViernes);
			tablaViernes.setBackground(colorTablasFondo);
			tablaViernes.setShowGrid(false);
			tablaViernes.setRowHeight(20);
			tablaViernes.setTableHeader(null);
			tablaViernes.setEnabled(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tablaSabado() {
		
		//AQUÍ SE RECOGEN DATOS DE LA BASE DE DATOS Y SE PLASMAN EN LA TABLA
		try {
			Calendario[] x=new Calendario[1];
			x = manager.getCalendarios(JFramePrincipal.getUsuarioConectado(), (short) 6, JFramePrincipal.getTipo());
			ArrayList <Serie> series=x[1].getSeries();
			String[] columnNames = {"Nombre"};
				
			DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
			JTable tablaSabado= new JTable(tableModel);
			for (int i = 0; i < series.size(); i++) {
				String nombre=series.get(i).getNombre().toString();
				Object[] data = {nombre};
				tableModel.addRow(data);
			}
		//FIN RECOGIDA
			
			JScrollPane scrollTablaSabado = new JScrollPane(tablaSabado);
			scrollTablaSabado.setBorder(BorderFactory.createEmptyBorder());
			scrollTablaSabado.setBounds(142, 510, 190, 190);
			tablaSabado.setFillsViewportHeight(true);
			add(scrollTablaSabado);
			tablaSabado.setBackground(colorTablasFondo);
			tablaSabado.setShowGrid(false);
			tablaSabado.setRowHeight(20);
			tablaSabado.setTableHeader(null);
			tablaSabado.setEnabled(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void tablaDomingo() {
		
		//AQUÍ SE RECOGEN DATOS DE LA BASE DE DATOS Y SE PLASMAN EN LA TABLA
		try {
			Calendario[] x=new Calendario[1];
			x = manager.getCalendarios(JFramePrincipal.getUsuarioConectado(), (short) 7, JFramePrincipal.getTipo());
			ArrayList <Serie> series=x[1].getSeries();
			String[] columnNames = {"Nombre"};
				
			DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
			JTable tablaDomingo= new JTable(tableModel);
			for (int i = 0; i < series.size(); i++) {
				String nombre=series.get(i).getNombre().toString();
				Object[] data = {nombre};
				tableModel.addRow(data);
			}
		//FIN RECOGIDA
			
			JScrollPane scrollTablaDomingo = new JScrollPane(tablaDomingo);
			scrollTablaDomingo.setBorder(BorderFactory.createEmptyBorder());
			scrollTablaDomingo.setBounds(403, 510, 190, 190);
			tablaDomingo.setFillsViewportHeight(true);
			add(scrollTablaDomingo);
			tablaDomingo.setBackground(colorTablasFondo);
			tablaDomingo.setShowGrid(false);
			tablaDomingo.setRowHeight(20);
			tablaDomingo.setTableHeader(null);
			tablaDomingo.setEnabled(false);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
}
