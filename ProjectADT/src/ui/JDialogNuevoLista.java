package ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
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
	


	/**
	 * Crea el dialogo que usa para a�adir nuevos registros a la tabla de contenidos del usuario, se accede desde el panel de Listado
	 * @param accion funcionalidad con la que se abrir� la ventana
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
		botones();
		checkbox();
		labels();		
		
	}

	/**
	 * Configuraci�n de los campos de texto
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
	 * Creaci�n y configuraci�n de los combo box 
	 */
	private void comboBox() {
		comboBoxTipos = new JComboBox<String>();
		comboBoxTipos.setBounds(154, 61, 193, 23);
		getContentPane().add(comboBoxTipos);
		comboBoxTipos.addItem("Serie");
		comboBoxTipos.addItem("Pel�cula");
		comboBoxTipos.addItem("M�sica");
		comboBoxTipos.addItem("Libro");
		comboBoxTipos.setSelectedIndex(-1);
		
		
		comboBoxTipoMusica = new JComboBox<String>();
		comboBoxTipoMusica.setBounds(164, 259, 122, 20);
		getContentPane().add(comboBoxTipoMusica);
		comboBoxTipoMusica.addItem("Disco");
		comboBoxTipoMusica.addItem("Canci�n");
		
	}


	/**
	 * Creaci�n y configuraci�n de botones(incluyendo sus listener)
	 */
	private void botones() {
		
		//Tipo de contenido para a�adir ,establece distintos campos habilitados seg�n la opci�n
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
					}else if(comboBoxTipos.getSelectedItem().equals("Pel�cula")) {
						tFtemporadas.setEnabled(false);
						tFautor.setEnabled(false);
						comboBoxTipoMusica.setEnabled(false);
						tFcantante.setEnabled(false);
					}else if(comboBoxTipos.getSelectedItem().equals("M�sica")){
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
		
		botonAnadirNuevo = new JButton("A\u00F1adir\r\n");
		botonAnadirNuevo.addActionListener(new ActionListener() {
			//A�ade los datos a la base de datos o da error si no se cumple lo minimo
			public void actionPerformed(ActionEvent e) {
				if(comboBoxTipos.getSelectedIndex()==-1 || tFnombre.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione un tipo y rellene los campos obligatorios para a�adir","Error",JOptionPane.ERROR_MESSAGE);
				}else {
					//A�ade lo efectuado a la base de datos
				}
				
			}
		});
		//botonRegistrarNuevo.setBorder(null);
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
		//botonCancelar.setBorder(null);
		botonCancelar.setBackground(Color.blue);
		//botonCancelar.setFocusPainted(false);
		botonCancelar.setRolloverEnabled(false);
		botonCancelar.setContentAreaFilled(false);	
		botonCancelar.setBounds(315, 363, 89, 23);
		getContentPane().add(botonCancelar);
		
	}



	/**
	 * Creaci�n y configuraciones de los checkbox de la ventana
	 */
	private void checkbox() {
		JCheckBox cBfavoritos = new JCheckBox("");
		cBfavoritos.setBounds(164, 224, 21, 23);
		getContentPane().add(cBfavoritos);
		
		
	}



	


	/**
	 * Creaci�n y configuraci�n de los labels de la ventana
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
