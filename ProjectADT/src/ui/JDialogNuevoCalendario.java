package ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import clases.Calendario;
import clases.Serie;
import datos.Manager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class JDialogNuevoCalendario extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5038539237899941790L;
	private JButton botonCancelar;
	private JButton botonAnadirNuevo;
	private JButton botonBorrar;
	private JLabel lblTitulo;
	private Manager manager=new Manager();
	private JComboBox<String> comboDiasSemana;
	private JComboBox<String> comboContenido;



	/**
	 * Crea el di�logo que servir� para introducir o borrar una entrada en el calendario (se accede desde el Panel de calendario)
	 * @param accion con la que se efectuara y configurar� esta ventana (Borrar o A�adir)
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

		//Distintas configuraciones dependiendo del paramentro acci�n recibido
		if(accion.equals("borrar")) {

			lblTitulo.setText("Borrar");
			botonBorrar = new JButton("Borrar");
			botonBorrar.setFocusPainted(false);
			botonBorrar.setRolloverEnabled(false);
			botonBorrar.setContentAreaFilled(false);	
			botonBorrar.setBounds(73, 148, 89, 23);
			botonBorrar.setEnabled(false);
			getContentPane().add(botonBorrar);
			botonBorrar.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					manager.borrarRegistroCalendario(JFramePrincipal.getUsuarioConectado(), comboContenido.getSelectedItem().toString(), comboDiasSemana.getSelectedItem().toString().toLowerCase(), JFramePrincipal.getTipo());
					dispose();

				}
			});
			
			
			JButton btnVer = new JButton("Ver");
			btnVer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comboContenido.removeAllItems();
					ArrayList<Calendario> calendarios=new ArrayList<Calendario>();
					calendarios=manager.getCalendarios(JFramePrincipal.getUsuarioConectado(), JFramePrincipal.getTipo());
					
					comboDiasSemana.getSelectedIndex();
					System.out.println("interfaz"+calendarios.size());

					for (Calendario calendario : calendarios) {

						System.out.println("dentro "+calendario.getDia());
						System.out.println("tamanio series: "+calendario.getSeries().size());
						System.out.println("d�a semana "+ comboDiasSemana.getSelectedItem().toString());
						if(calendario.getDia().equals(comboDiasSemana.getSelectedItem().toString().toLowerCase())) {
							for(Serie serie :calendario.getSeries()) {
								comboContenido.addItem(serie.getNombre());
							}
							comboContenido.validate();
							comboContenido.repaint();
							break;
						}
					}
					
					if (comboContenido.getItemCount()>0) {
						botonBorrar.setEnabled(true);
					}else {
						botonBorrar.setEnabled(false);
					}
					
				}
			});
			btnVer.setBounds(259, 67, 89, 23);
			getContentPane().add(btnVer);	


		}else {
			lblTitulo.setText("A�adir");
			botonAnadirNuevo = new JButton("A\u00F1adir");
			botonAnadirNuevo.setFocusPainted(false);
			botonAnadirNuevo.setRolloverEnabled(false);
			botonAnadirNuevo.setContentAreaFilled(false);	
			botonAnadirNuevo.setBounds(73, 148, 89, 23);
			getContentPane().add(botonAnadirNuevo);
			botonAnadirNuevo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//A�ade el contenido de la base de datos en calendario el dia indicado
					manager.nuevoRegistroCalendario(JFramePrincipal.getUsuarioConectado(), comboContenido.getSelectedItem().toString(), comboDiasSemana.getSelectedItem().toString().toLowerCase(),JFramePrincipal.getTipo());
					dispose();
					
				}
			});

			
			
			
			//Cargar las series de la base de datos en el comboBox
			ArrayList<Object> series = new ArrayList<>();
			series = manager.getContenidoDeUnTipo(JFramePrincipal.getUsuarioConectado(),"Serie", JFramePrincipal.getTipo());
			for(Object serie: series) {
				
				comboContenido.addItem(((Serie)serie).getNombre());
			}
			comboContenido.setSelectedItem(1);
			
		}
	

	}
	

	/**
	 * Creaci�n y configuraci�n de los labels de la ventana
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
	 * Creaci�n y configuraci�n de los combo box 
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
		comboDiasSemana.addItem("Mi�rcoles");
		comboDiasSemana.addItem("Jueves");
		comboDiasSemana.addItem("Viernes");
		comboDiasSemana.addItem("S�bado");
		comboDiasSemana.addItem("Domingo");
	}
	
	/**
	 * Creaci�n y configuraci�n de botones(incluyendo sus listener)
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
