package ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import clases.Contenido;
import clases.Libro;
import clases.Musica;
import clases.Pelicula;
import clases.Serie;
import datos.Manager;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class JDialogNuevoLista extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5038539237899941790L;
	private JButton botonCancelar;
	private JButton botonAnadirNuevo;
	private JTextField tFnombre;
	private JTextField tFgenero;
	private JTextField tFpuntuacion;
	private JTextField tFtemporadas;
	private JTextField tFdirector;
	private JTextField tFautor;
	private JComboBox<String> comboBoxTipos;
	private JTextField tFcantante;
	private JComboBox<String> comboBoxTipoMusica;
	private Manager manager=new Manager();
	private JCheckBox cbRecomendado;
	private String tipoContenidoAModificar;
	private Contenido contenidoAModificar;
	


	/**
	 * Crea el dialogo que usa para añadir nuevos registros a la tabla de contenidos del usuario, se accede desde el panel de Listado
	 * @param accion indica la funcionalidad con la que se abrirá la ventana , "Añadir" en el caso de 1 solo parametro
	 */
	public JDialogNuevoLista(String accion) {
		

		getContentPane().setBackground(Color.LIGHT_GRAY);
		setModal(true);

		
		setSize(600, 400);		
		setLocationRelativeTo(null);

		setUndecorated(true);
		getContentPane().setLayout(null);
		
		textFieds();
		comboBox();
		botones(accion);
		checkbox();
		labels();		
		
	}
	/**
	 * Crea el dialogo que usa para modificar registros de contenidos del usuario, se accede desde el panel de Listado
	 * @param accion indica la funcionalidad con la que se abrirá la ventana , "Modificar" en el caso de 1 solo parametro
	 * @param tipoContenidoAModificar especifica si es "Serie" ,"Película", "Libro" o "Música" el contenido que se va a modificar
	 */
	public JDialogNuevoLista(String accion,String tipoContenidoAModificar,Contenido contenidoAModificar) {

		this.tipoContenidoAModificar=tipoContenidoAModificar;
		this.contenidoAModificar=contenidoAModificar;
		
		checkbox();
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setModal(true);

		
		setSize(600, 400);		
		setLocationRelativeTo(null);

		setUndecorated(true);
		getContentPane().setLayout(null);
		
		textFieds();
		comboBox();
		botones(accion);
		
		labels();	
	}

	/**
	 * Configuración de los campos de texto
	 */
	private void textFieds() {
		tFnombre = new JTextField();
		tFnombre.setBounds(164, 135, 221, 20);
		getContentPane().add(tFnombre);
		tFnombre.setColumns(10);
		
		tFgenero = new JTextField();
		tFgenero.setBounds(164, 166, 86, 20);
		getContentPane().add(tFgenero);
		tFgenero.setColumns(10);
		
		tFpuntuacion = new JTextField();
		tFpuntuacion.setBounds(164, 197, 86, 20);
		getContentPane().add(tFpuntuacion);
		tFpuntuacion.setColumns(10);
		

		tFtemporadas = new JTextField();
		tFtemporadas.setBounds(414, 166, 122, 20);
		getContentPane().add(tFtemporadas);
		tFtemporadas.setColumns(10);
		
		tFdirector = new JTextField();
		tFdirector.setBounds(414, 197, 122, 20);
		getContentPane().add(tFdirector);
		tFdirector.setColumns(10);
		
		tFautor = new JTextField();
		tFautor.setBounds(414, 228, 122, 20);
		getContentPane().add(tFautor);
		tFautor.setColumns(10);
		
		tFcantante = new JTextField();
		tFcantante.setBounds(414, 259, 122, 20);
		getContentPane().add(tFcantante);
		tFcantante.setColumns(10);

		
	}
	/**
	 * Creación y configuración de los combo box 
	 */
	private void comboBox() {
		comboBoxTipos = new JComboBox<String>();
		comboBoxTipos.setBounds(154, 61, 193, 23);
		getContentPane().add(comboBoxTipos);
		comboBoxTipos.addItem("Serie");
		comboBoxTipos.addItem("Película");
		comboBoxTipos.addItem("Música");
		comboBoxTipos.addItem("Libro");
		comboBoxTipos.setSelectedIndex(-1);
		
		
		comboBoxTipoMusica = new JComboBox<String>();
		comboBoxTipoMusica.setBounds(164, 259, 122, 20);
		getContentPane().add(comboBoxTipoMusica);
		comboBoxTipoMusica.addItem("Disco");
		comboBoxTipoMusica.addItem("Canción");
		
	}


	/**
	 * Creación y configuración de botones(incluyendo sus listener)
	 * @param accion 
	 */
	private void botones(String accion) {
		
		JButton btnElegir = new JButton("Elegir");
		btnElegir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxTipos.getSelectedIndex()!=-1) {
					tFdirector.setEnabled(true);
					tFautor.setEnabled(true);
					tFtemporadas.setEnabled(true);
					comboBoxTipoMusica.setEnabled(true);
					tFcantante.setEnabled(true);
					if(comboBoxTipos.getSelectedItem().equals("Serie")) {
						tFdirector.setEnabled(false);
						tFautor.setEnabled(false);
						comboBoxTipoMusica.setEnabled(false);
						tFcantante.setEnabled(false);
					}else if(comboBoxTipos.getSelectedItem().equals("Película")) {
						tFtemporadas.setEnabled(false);
						tFautor.setEnabled(false);
						comboBoxTipoMusica.setEnabled(false);
						tFcantante.setEnabled(false);
					}else if(comboBoxTipos.getSelectedItem().equals("Música")){
						tFdirector.setEnabled(false);
						tFautor.setEnabled(false);
						tFtemporadas.setEnabled(false);

					}else {
						tFdirector.setEnabled(false);
						tFtemporadas.setEnabled(false);
						comboBoxTipoMusica.setEnabled(false);
						tFcantante.setEnabled(false);
					}
				}

			}
		});
		btnElegir.setBounds(411, 61, 89, 23);
		getContentPane().add(btnElegir);
		
		botonAnadirNuevo = new JButton("Añadir");
		botonAnadirNuevo.addActionListener(new ActionListener() {
			//Añade los datos a la base de datos o da error si no se cumple lo minimo
			public void actionPerformed(ActionEvent e) {
				if(accion.equals("Modificar")) {
					Contenido contenido=cargarContenido();
					manager.modificarContenido(JFramePrincipal.getUsuarioConectado(),contenido , tipoContenidoAModificar, JFramePrincipal.getTipo());
					dispose();
				}else {
					//Si no ha seleccionado tipo genera un error avisando al usuario
					if(comboBoxTipos.getSelectedIndex()==-1 || tFnombre.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Seleccione un tipo y rellene los campos obligatorios para añadir","Error",JOptionPane.ERROR_MESSAGE);
					}else {
						Contenido contenido=cargarContenido();
						manager.anadirNuevoContenido(JFramePrincipal.getUsuarioConectado(), contenido, comboBoxTipos.getSelectedItem().toString(), JFramePrincipal.getTipo());
						dispose();
						//Añade lo efectuado a la base de datos
					}
				}
				

				
			}
			/**
			 * Carga los datos del tipo de contenido que ha elegido el usuario
			 * @return devuelve el contenido que ha seleccionado el usuario cargado
			 */
			private Contenido cargarContenido() {
				Contenido contenido;
				String x = comboBoxTipos.getSelectedItem().toString();
				//Carga los datos en contenido segun los distintos tipos que se hayan podido elegir
				if(x.equals("Serie")) {
					contenido=(Serie)new Serie(Integer.valueOf(tFtemporadas.getText()));
				}else if(x.equals("Película")) {
					contenido=new Pelicula(tFdirector.getText());
				}else if(x.equals("Música")) {
					contenido=new Musica(tFcantante.getText(),comboBoxTipoMusica.getSelectedItem().toString());
				}else {
					contenido=new Libro(tFautor.getText());
				}
				contenido.setNombre(tFnombre.getText());
				contenido.setGenero(tFgenero.getText());
				contenido.setPuntucacion(Short.valueOf(tFpuntuacion.getText()));
				contenido.setRecomendado(cbRecomendado.isSelected());
				return contenido;
			}
		});
		
		//Tipo de contenido para añadir ,establece distintos campos habilitados según la opción
		if(accion.equals("Modificar")) {
			
			botonAnadirNuevo.setText("Modificar");
			comboBoxTipos.setEditable(false);
			comboBoxTipos.setSelectedItem(tipoContenidoAModificar);
			tFnombre.setText(contenidoAModificar.getNombre());
			tFgenero.setText(contenidoAModificar.getGenero());
			tFpuntuacion.setText(String.valueOf(contenidoAModificar.getPuntucacion()));
			if(contenidoAModificar.getRecomendado()) {
				cbRecomendado.setSelected(true);
			}
			switch (tipoContenidoAModificar) {
			case "Serie":
				Serie serieAModificar= (Serie)contenidoAModificar;
				tFtemporadas.setText(String.valueOf(serieAModificar.getTemporadas()));
				break;
			case "Película":
				Pelicula peliculaAModificar= (Pelicula)contenidoAModificar;
				tFdirector.setText(peliculaAModificar.getDirector());
				break;
			case "Libro":
				Libro libroAModificar= (Libro)contenidoAModificar;
				tFautor.setText(libroAModificar.getAutor());
				break;
			case "Música":
				Musica musicaAModificar= (Musica)contenidoAModificar;
				comboBoxTipoMusica.setSelectedItem(musicaAModificar.getTipo());
				tFcantante.setText(musicaAModificar.getCantante());
				break;
			}
			comboBoxTipos.setEnabled(false);
			tFnombre.setEditable(false);
			btnElegir.doClick();
			btnElegir.setEnabled(false);
			

		}

		botonAnadirNuevo.setFocusPainted(false);
		botonAnadirNuevo.setRolloverEnabled(false);
		botonAnadirNuevo.setContentAreaFilled(false);	
		botonAnadirNuevo.setBounds(168, 363, 89, 23);
		getContentPane().add(botonAnadirNuevo);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			//Sale de la ventana
			public void actionPerformed(ActionEvent e) {
				//Acaba la ventana
				dispose();
			}
		});
		botonCancelar.setBackground(Color.blue);
		botonCancelar.setRolloverEnabled(false);
		botonCancelar.setContentAreaFilled(false);	
		botonCancelar.setBounds(315, 363, 89, 23);
		getContentPane().add(botonCancelar);
		
		
	}



	/**
	 * Creación y configuraciones de los checkbox de la ventana
	 */
	private void checkbox() {
		cbRecomendado = new JCheckBox("");
		cbRecomendado.setBounds(164, 224, 21, 23);
		getContentPane().add(cbRecomendado);
		
		
	}



	


	/**
	 * Creación y configuración de los labels de la ventana
	 */
	private void labels() {
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(97, 65, 46, 14);
		getContentPane().add(lblTipo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(66, 138, 46, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblGnero = new JLabel("G\u00E9nero");
		lblGnero.setBounds(66, 170, 46, 14);
		getContentPane().add(lblGnero);
		
		JLabel lblRecomendado = new JLabel("Recomendado");
		lblRecomendado.setBounds(66, 231, 77, 14);
		getContentPane().add(lblRecomendado);
		
		JLabel lblPuntuacin = new JLabel("Puntuaci\u00F3n\r\n(1-10)");
		lblPuntuacin.setBounds(66, 200, 99, 14);
		getContentPane().add(lblPuntuacin);
		
		JLabel lblTemporadas = new JLabel("Temporadas");
		lblTemporadas.setBounds(315, 166, 89, 14);
		getContentPane().add(lblTemporadas);
		
		JLabel lblDirector = new JLabel("Director");
		lblDirector.setBounds(315, 200, 60, 14);
		getContentPane().add(lblDirector);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(315, 231, 46, 14);
		getContentPane().add(lblAutor);
		
		JLabel lblNuevo = new JLabel("Nuevo contenido");
		lblNuevo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNuevo.setBounds(0, 11, 599, 28);
		getContentPane().add(lblNuevo);
		

		
		JLabel lblCantante = new JLabel("Cantante");
		lblCantante.setBounds(315, 262, 70, 14);
		getContentPane().add(lblCantante);

		
		JLabel lblTipomusica = new JLabel("Tipo(musica)");
		lblTipomusica.setBounds(66, 262, 77, 14);
		getContentPane().add(lblTipomusica);
		
		JLabel lblElCampoNombre = new JLabel("Nota: El campo nombre es el unico obligatorio");
		lblElCampoNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblElCampoNombre.setBounds(66, 98, 434, 14);
		getContentPane().add(lblElCampoNombre);
	}

}
