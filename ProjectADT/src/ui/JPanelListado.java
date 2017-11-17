package ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Contenido;
import clases.Serie;
import datos.Manager;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
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
	private ArrayList<Contenido> contenidosMostrados;

	/**
	 * Crea el panel que se encargará de listar el contenido del usuario y ofrecerle opciones sobre el 
	 */
	public JPanelListado() {
		setBounds(269, 39, 755, 729);
		
		tablaContenido();
		labels();
		comboBox();
		botones();
	
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
				contenidosMostrados = new ArrayList<Contenido>();
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
					String nombreContenido=(String) dtm.getValueAt(tablaListadoPersonal.getSelectedRow(), 0);
					dtm.removeRow(tablaListadoPersonal.getSelectedRow());
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
				String nombreContenido=(String) dtm.getValueAt(tablaListadoPersonal.getSelectedRow(),0);
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
				contenidoSeleccionado= new Serie();
				contenidoSeleccionado.setNombre("House");
				/*for(Contenido contenido:contenidosMostrados) {
					if(contenido.getNombre().equals((String) dtm.getValueAt(tablaListadoPersonal.getSelectedRow(),0))) {
						contenidoSeleccionado = contenido;
						break;
					}
				}
				*/
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

	/**
	 * Tabla que contendrá el contenido del usuario una vez se busque
	 */
	private void tablaContenido() {
		//Inicia vacia la tabla
		Object[][] rowData = {
				{"LazyTown","Infantil","Sí","2","3"},
				{"House","Fantasia","Sí","9","5"}
			};
			String[] columnNames = {"Nombre","Género","Recomendado","Puntuacion","Temporadas"};
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
