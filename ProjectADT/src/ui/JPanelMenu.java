package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelMenu extends JPanel{

	/**
	 * 
	 */
	private JLabel botonListado;
	private JLabel botonCalendario;
	private JLabel botonRecomendaciones;
	


	private static final long serialVersionUID = -7425637791813068536L;
	private JTextField nuevoAmigo;
	private DefaultTableModel dtm;
	private JTable tablaAmigos;

	/**
	 * Panel que se encarga de las distintas partes del menú
	 * @param personalMouseListener mouseListener que se encarga de las acciones del menú
	 */
	public JPanelMenu(MouseListener personalMouseListener) {
		
		
		setBounds(0, 39, 270, 729);
		this.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		setLayout(null);
		
		opcionesMenu(personalMouseListener);
		amigos();
		

	}
	/**
	 * Accesos que permitén cambiar entre paneles en el jframe
	 * @param personalMouseListener 
	 */
	private void opcionesMenu(MouseListener personalMouseListener) {
		/*
		botonCalendario = new JButton("");
		botonCalendario.setBorder(null);
		botonCalendario.setRolloverEnabled(false);
		botonCalendario.setContentAreaFilled(false);
		botonCalendario.setFocusPainted(false);
		botonCalendario.setBounds(0, 223, 270, 43);
		botonCalendario.addMouseListener(personalMouseListener);
		this.add(botonCalendario);
		*/
		
		botonCalendario = new JLabel("");
		botonCalendario.setBounds(0, 223, 270, 43);
		botonCalendario.addMouseListener(personalMouseListener);
		this.add(botonCalendario);
		
		botonListado = new JLabel("");
		botonListado.setBounds(0, 179, 270, 43);
		botonListado.addMouseListener(personalMouseListener);
		this.add(botonListado);
		
		
		botonRecomendaciones = new JLabel();
		botonRecomendaciones.setBackground(Color.gray);
		botonRecomendaciones.setBounds(0, 267, 270, 43);
		botonRecomendaciones.addMouseListener(personalMouseListener);
		this.add(botonRecomendaciones);
		
	}
	/**
	 * Se encarga del areá de amigos en la parte inferior del menú
	 */
	private void amigos() {

		tablaAmigos();
		textfields();
		botones();
	
	}


	/**
	 * Configuración de la tabla que contendrá los amigos de la bda del usuario
	 */
	private void tablaAmigos() {
		//AQUÍ SE RECOGEN DATOS DE LA BASE DE DATOS Y SE PLASMAN EN LA TABLA
		Object[][] rowData = {
			    {""},
			    {"John"},
			    {"Sue"},
			    {"Jane"},
			    {"Joe"}
			};
			String[] columnNames = {"Nombre"};
		//FIN RECOGIDA
		
		dtm = new DefaultTableModel(rowData,columnNames);
		tablaAmigos= new JTable(dtm);
		tablaAmigos.setForeground(Color.LIGHT_GRAY);
		tablaAmigos.setFont(new Font("Tahoma", Font.BOLD, 15));
		tablaAmigos.setRowHeight(100);
		JScrollPane scrollTablaAmigos = new JScrollPane(tablaAmigos);
		scrollTablaAmigos.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
		scrollTablaAmigos.setBackground(new Color(0,0,0,0));
		scrollTablaAmigos.setBounds(25, 371, 224, 267);
		tablaAmigos.setFillsViewportHeight(true);
		add(scrollTablaAmigos);
		//tablaAmigos.setEnabled(false);
		//tablaAmigos.setBackground(new Color(0,0,0,0));
		tablaAmigos.setBackground(new Color(55, 60, 68));
		tablaAmigos.setShowGrid(false);
		tablaAmigos.setRowHeight(20);
		tablaAmigos.setTableHeader(null);
		tablaAmigos.setDefaultEditor(Object.class, null);
		
	}
	/**
	 * TextField para añadir amigos
	 */
	private void textfields() {
		nuevoAmigo = new JTextField();
		nuevoAmigo.setBounds(25, 649, 131, 20);
		add(nuevoAmigo);
		nuevoAmigo.setColumns(10);
		
	}
	/**
	 * Botones de opciones para los amigos
	 */
	private void botones() {
		JButton btnAadir = new JButton("A\u00F1adir");
		btnAadir.addActionListener(new ActionListener() {
			//Añade el amigo que se ha escrito en nuevoAmigo
			public void actionPerformed(ActionEvent arg0) {
				if(!nuevoAmigo.getText().trim().equals("")) {
					dtm.addRow(new Object[] {nuevoAmigo.getText()});
					nuevoAmigo.setText("");
				}
				//Añade a la base de datos el amigo o efectua un panel de error avisando de que ya se tiene agregado
			}
		});
		btnAadir.setBounds(160, 649, 89, 23);
		add(btnAadir);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			//Elimina un amigo de la base de datos y de la tabla de amigos
			public void actionPerformed(ActionEvent e) {
				if(tablaAmigos.getSelectedRow()!=-1) {
					dtm.removeRow(tablaAmigos.getSelectedRow());
				}
				
			}
		});
		btnEliminar.setBounds(25, 683, 224, 23);
		add(btnEliminar);
		
	}
	/**
	 * Especifica si es opaco el label, para ver el color de fondo si es true o dejar de verlo si es false
	 *  Se cambia el color de fondo cada vez que se deja ver el label para poderse visualizar el cambio
	 * @param opaque establece si será o no opaco
	 */
	public void putShadowBotonCalendario(boolean opaque) {
		botonCalendario.setOpaque(opaque);
		if(opaque) {
			botonCalendario.setBackground(new Color(0.3f,0.3f,0.3f,0.3f));
		}else{
			botonCalendario.setBackground(null);
		}
		
	}
	/**
	 * Especifica si es opaco el label, para ver el color de fondo si es true o dejar de verlo si es false
	 * Se cambia el color de fondo cada vez que se deja ver el label para poderse visualizar el cambio
	 * @param opaque establece si será o no opaco
	 */
	
	public void putShadowBotonListado(boolean opaque) {
		botonListado.setOpaque(opaque);
		if(opaque) {
			botonListado.setBackground(new Color(0.3f,0.3f,0.3f,0.3f));
		}else{
			botonListado.setBackground(null);
		}
	}
	/**
	 * Especifica si es opaco el label, para ver el color de fondo si es true o dejar de verlo si es false
	 *  Se cambia el color de fondo cada vez que se deja ver el label para poderse visualizar el cambio
	 * @param opaque establece si será o no opaco
	 */
	public void putShadowBotonRecomendaciones(boolean opaque) {
		botonRecomendaciones.setOpaque(opaque);
		
		if(opaque) {
			botonRecomendaciones.setBackground (new Color(0.3f,0.3f,0.3f,0.3f));
		}else{
			botonRecomendaciones.setBackground(null);
		}

	}
	public JLabel getBotonListado() {
		return botonListado;
	}

	public JLabel getBotonCalendario() {
		return botonCalendario;
	}

	public JLabel getBotonRecomendaciones() {
		return botonRecomendaciones;
	}
}
