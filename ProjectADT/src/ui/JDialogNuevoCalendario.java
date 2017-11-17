package ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import clases.Calendario;
import clases.Serie;
import datos.Manager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class JDialogNuevoCalendario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5038539237899941790L;
	private JButton botonCancelar;
	private JButton botonAñadirNuevo;
	private JButton botonBorrar;
	private JLabel lblTitulo;
	private Manager manager=new Manager();
	private JComboBox<String> comboDiasSemana;
	private JComboBox<String> comboContenido;



	/**
	 * Crea el diálogo que servirá para introducir o borrar una entrada en el calendario (se accede desde el Panel de calendario)
	 * @param accion con la que se efectuara y configurará esta ventana (Borrar o Añadir)
	 */
	public JDialogNuevoCalendario(String accion) {
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setModal(true);
		
		setSize(400, 200);		
		setLocationRelativeTo(null);

		setUndecorated(true);
		getContentPane().setLayout(null);
		

		labels();
		comboBox();
		botones();

		//Distintas configuraciones dependiendo del paramentro acción recibido
		if(accion.equals("borrar")) {

			lblTitulo.setText("Borrar");
			botonBorrar = new JButton("Borrar");
			botonBorrar.setFocusPainted(false);
			botonBorrar.setRolloverEnabled(false);
			botonBorrar.setContentAreaFilled(false);	
			botonBorrar.setBounds(73, 148, 89, 23);
			getContentPane().add(botonBorrar);
			botonBorrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					manager.borrarRegistroCalendario(JFramePrincipal.getUsuarioConectado(), comboContenido.getSelectedItem().toString(), comboDiasSemana.getSelectedItem().toString().toLowerCase(), JFramePrincipal.getTipo());

				}
			});
			
			
			JButton btnVer = new JButton("Ver");
			btnVer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Calendario[] calendario=new Calendario[1];
					calendario=manager.getCalendarios(JFramePrincipal.getUsuarioConectado(),(short) (comboDiasSemana.getSelectedIndex()+1), JFramePrincipal.getTipo());
					
					ArrayList<Serie> x = calendario[1].getSeries();
					for (short i = 0; i < x.size(); i++) {
						comboContenido.setSelectedItem(x.get(i).getNombre().toString());
					}
				}
			});
			btnVer.setBounds(259, 67, 89, 23);
			getContentPane().add(btnVer);	


		}else {
			lblTitulo.setText("Añadir");
			botonAñadirNuevo = new JButton("A\u00F1adir");
			botonAñadirNuevo.setFocusPainted(false);
			botonAñadirNuevo.setRolloverEnabled(false);
			botonAñadirNuevo.setContentAreaFilled(false);	
			botonAñadirNuevo.setBounds(73, 148, 89, 23);
			getContentPane().add(botonAñadirNuevo);
			botonAñadirNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Añade el contenido de la base de datos en calendario el dia indicado
					manager.nuevoRegistroCalendario(JFramePrincipal.getUsuarioConectado(), comboContenido.getSelectedItem().toString(), comboDiasSemana.getSelectedItem().toString().toLowerCase(),JFramePrincipal.getTipo());

					
				}
			});

			//Cargar contenido de la base de datos en el comboBox
		}
	

	}
	

	/**
	 * Creación y configuración de los labels de la ventana
	 */
	private void labels() {
		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(46, 101, 99, 14);
		getContentPane().add(lblContenido);
		
		JLabel lblDa = new JLabel("D\u00EDa");
		lblDa.setBounds(46, 50, 68, 14);
		getContentPane().add(lblDa);
		
		lblTitulo = new JLabel();
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitulo.setBounds(0, 11, 400, 28);
		getContentPane().add(lblTitulo);
	} 
	
	/**
	 * Creación y configuración de los combo box 
	 */
	private void comboBox() {
		comboContenido = new JComboBox<String>();
		comboContenido.setBounds(46, 114, 257, 23);
		getContentPane().add(comboContenido);
		
		comboDiasSemana = new JComboBox<String>();
		comboDiasSemana.setBounds(46, 67, 171, 23);
		getContentPane().add(comboDiasSemana);
		comboDiasSemana.addItem("Lunes");
		comboDiasSemana.addItem("Martes");
		comboDiasSemana.addItem("Miercoles");
		comboDiasSemana.addItem("Jueves");
		comboDiasSemana.addItem("Viernes");
		comboDiasSemana.addItem("Sabado");
		comboDiasSemana.addItem("Domingo");
	}
	
	/**
	 * Creación y configuración de botones(incluyendo sus listener)
	 */
	private void botones() {
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botonCancelar.setBackground(Color.blue);
		botonCancelar.setRolloverEnabled(false);
		botonCancelar.setContentAreaFilled(false);	
		botonCancelar.setBounds(214, 148, 89, 23);
		getContentPane().add(botonCancelar);
		
	}






}
