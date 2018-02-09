package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import clases.Contenido;
import clases.Libro;
import clases.Musica;
import clases.Pelicula;
import clases.Serie;
import datos.Manager;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class JPanelListado extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6097400309311389493L;
	private JScrollPane scrollPaneListadoPersonal;
	private JTable tablaListadoPersonal;
	private DefaultTableModel dtm;
	private Manager manager=new Manager();
	private JComboBox<String> comboBoxTipos;
	private ArrayList<Object> contenidosMostrados;
	private String tipoSeleccionado;

	/**
	 * Crea el panel que se encargará de listar el contenido del usuario y ofrecerle opciones sobre el 
	 */
	public JPanelListado() {
		setBounds(269, 39, 755, 729);
		
		
		labels();
		comboBox();
		botones();
		tablaContenido();
	
	}


	/**
	 * Configura los botones de la ventana
	 */
	private void botones() {
		JButton botonBuscar = new JButton("Buscar");
		botonBuscar.addActionListener(new ActionListener() {
			//Efectua una busqueda en la base de datos con el tipo de contenido seleccionado
			public void actionPerformed(ActionEvent e) {
				//Añade a la tabla todos los datos de contenido del usuario en la base de datos 
				//Del tipo seleccionado en la comb box
				//Que correspondenn al usuario conectado
				tipoSeleccionado = comboBoxTipos.getSelectedItem().toString();
				contenidosMostrados = new ArrayList<Object>();
				contenidosMostrados = manager.getContenidoDeUnTipo(JFramePrincipal.getUsuarioConectado(),comboBoxTipos.getSelectedItem().toString(),JFramePrincipal.getTipo());
				nuevosDatosContenido();
				
			}
		});
		botonBuscar.setBounds(334, 66, 89, 23);
		add(botonBuscar);
		
		JButton btnAadirNuevo = new JButton("A\u00F1adir Nuevo...");
		btnAadirNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Crea una nueva ventana para elegir el registro que se va a añadir
				JDialogNuevoLista nContenido=new JDialogNuevoLista("Añadir");
				nContenido.setVisible(true);
			}
		});
		btnAadirNuevo.setBounds(550, 53, 135, 23);
		add(btnAadirNuevo);
		
		JButton btnBorrarSeleccionado = new JButton("Borrar seleccionado");
		btnBorrarSeleccionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Borra la entrada seleccionada en la tabla y en la base de datos
				System.out.println(tablaListadoPersonal.getSelectedRow());
				if(tablaListadoPersonal.getSelectedRowCount()!=0) {
					String nombreContenido=(String) tablaListadoPersonal.getModel().getValueAt(tablaListadoPersonal.getSelectedRow(), 0);
					((DefaultTableModel)tablaListadoPersonal.getModel()).removeRow(tablaListadoPersonal.getSelectedRow());
					manager.borrarContenidoSeleccionado(JFramePrincipal.getUsuarioConectado(), nombreContenido.trim(),comboBoxTipos.getSelectedItem().toString(), JFramePrincipal.getTipo());
					


				}

				//Borra la entrada seleccionada en la base de datos
			}
		});
		btnBorrarSeleccionado.setBounds(50, 669, 169, 23);
		add(btnBorrarSeleccionado);
		
		JButton btnRecomendar = new JButton("Recomendar seleccionado");
		btnRecomendar.addActionListener(new ActionListener() {
			//Establece en la base de datos que recomienda el usuario
			public void actionPerformed(ActionEvent e) {
				
				//Añade a la base de datos que que recomeinda el contenido de la linea seleccionada
				String nombreContenido=(String) tablaListadoPersonal.getModel().getValueAt(tablaListadoPersonal.getSelectedRow(),0);
				manager.recomendarContenidoSeleccionado(JFramePrincipal.getUsuarioConectado(), nombreContenido,comboBoxTipos.getSelectedItem().toString(), JFramePrincipal.getTipo());


			}
		});
		btnRecomendar.setBounds(510, 669, 191, 23);
		add(btnRecomendar);
		
		JButton btnModificarSeleccio = new JButton("Modificar selecci\u00F3n...");
		btnModificarSeleccio.addActionListener(new ActionListener() {
			//Abre la ventana de nuevo lista con una acción de modificar
			public void actionPerformed(ActionEvent arg0) {
				Contenido contenidoSeleccionado = null;

				for(Object contenido:contenidosMostrados) {
					if(((Contenido)contenido).getNombre().equals((String) tablaListadoPersonal.getModel().getValueAt(tablaListadoPersonal.getSelectedRow(),0))) {
						contenidoSeleccionado = (Contenido)contenido;
						break;
					}
				}
				
				JDialogNuevoLista nContenido=new JDialogNuevoLista("Modificar",comboBoxTipos.getSelectedItem().toString(),contenidoSeleccionado);
			
				nContenido.setVisible(true);
			}
		});
		btnModificarSeleccio.setBounds(532, 87, 168, 23);
		add(btnModificarSeleccio);
		
		JLabel lblNewLabel = new JLabel("Listado personal de contenido");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(10, 11, 735, 26);
		add(lblNewLabel);
		
		
	}

	
	private void nuevosDatosContenido() {
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Nombre");
		tableModel.addColumn("Género");
		tableModel.addColumn("Recomendado");
		tableModel.addColumn("Puntuación");
		
		
		String tipo= comboBoxTipos.getSelectedItem().toString();
		switch (tipo) {
			
		case "Serie":
			tableModel.addColumn("Temporadas");
			break;
		case "Película":
			tableModel.addColumn("Director");
			break;
		case "Música":
			tableModel.addColumn("Tipo");
			tableModel.addColumn("Cantante");
			break;
		case "Libro":
			tableModel.addColumn("Autor");
			break;
		}
		
		
		Vector<String> row = new Vector<String>();
		
		ArrayList <String>data=new ArrayList <String>();
		for(Object contenido:contenidosMostrados) {
			System.out.println("size contenidos "+contenidosMostrados.size());
			row.add(((Contenido)contenido).getNombre());
			row.add(((Contenido)contenido).getGenero());
			row.add(String.valueOf(((Contenido)contenido).getRecomendado()));
			row.add(String.valueOf(((Contenido)contenido).getPuntucacion()));
			
			switch (tipo) {
			
			case "Serie":
				row.add(String.valueOf(((Serie)contenido).getTemporadas()));
				break;
			case "Película":
				row.add(((Pelicula)contenido).getDirector());
				break;
			case "Música":
				row.add(((Musica)contenido).getTipo());
				row.add(((Musica)contenido).getCantante());
				break;
			case "Libro":
				row.add(((Libro)contenido).getAutor());
				break;
			}
			
			System.out.println("row size "+row.size());
			tableModel.addRow(row);
			row = new Vector <String>();
			System.out.println("row size fin"+row.size());
		}
		tablaListadoPersonal.setModel(tableModel);
		
	}
	/**
	 * Tabla que contendrá el contenido del usuario una vez se busque
	 */
	private void tablaContenido() {


		//Inicia vacia la tabla
		Object[][] rowData = {
				{},
				{}
			};
			String[] columnNames = {};
		setLayout(null);
		
		dtm = new DefaultTableModel(rowData,columnNames);
		
		tablaListadoPersonal= new JTable(dtm);
		
		scrollPaneListadoPersonal = new JScrollPane(tablaListadoPersonal);
		//scrollPaneFavoritosComunidad.setBorder(BorderFactory.createEmptyBorder());
		scrollPaneListadoPersonal.setBounds(50, 120, 650,538 );
		tablaListadoPersonal.setFillsViewportHeight(true);
		add(scrollPaneListadoPersonal);
		//tablaListadoPersonal.setEnabled(false);
		//tablaListadoPersonal.setBackground(new Color(0,0,0,0));
		//tablaListadoPersonal.setShowVerticalLines(false);
		//tablaListadoPersonal.setShowGrid(false);
		//tablaListadoPersonal.setRowHeight(20);
		//tablaListadoPersonal.setTableHeader(null);
		tablaListadoPersonal.setDefaultEditor(Object.class, null);	
		tablaListadoPersonal.getTableHeader().setReorderingAllowed(false);
	}
	
	/**
	 * Configura los labels de la ventana
	 */
	private void labels() {
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(74, 70, 46, 14);
		add(lblTipo);
		
	}
	

	/**
	 * Configura los combobox de la ventana
	 */
	private void comboBox() {
		comboBoxTipos = new JComboBox<String>();
		comboBoxTipos.setBounds(111, 67, 213, 20);
		add(comboBoxTipos);
		comboBoxTipos.addItem("Serie");
		comboBoxTipos.addItem("Película");
		comboBoxTipos.addItem("Música");
		comboBoxTipos.addItem("Libro");
		
	}
}
