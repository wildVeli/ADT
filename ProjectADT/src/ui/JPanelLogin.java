package ui;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import datos.Manager;

import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class JPanelLogin extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usuarioLogin;
	private JTextField passwordLogin;
	private JButton botonRegistrarse;
	private JButton botonLogin;
	private Manager manager= new Manager();
	private JRadioButton rdbtnMongodb;
	private JRadioButton rdbtnDbrelacional;
	private JRadioButton radioButton_2;
	private JRadioButton rdbtnDBObjects;
	private ButtonGroup basesDeDatos = new ButtonGroup();


	/**
	 * Panel de login
	 * @param principal Frame padre de la aplicación
	 */
	public JPanelLogin(JFrame principal) {

		setSize(1024,768);
		setLayout(null);
	
		radioButtons();
		textFields();
		botones(principal);
		separators();
		labels();
		
		
	}

	/**
	 * Configura los radioButton
	 */
	private void radioButtons() {
		
		ActionListener radioGroupListener= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object x = e.getSource();
				if(x.equals(rdbtnDBObjects)) {
					JFramePrincipal.setTipo((short) 1);
				}else if(x.equals(rdbtnMongodb)) {
					JFramePrincipal.setTipo((short) 2);
				}else if(x.equals(rdbtnDbrelacional)) {
					JFramePrincipal.setTipo((short) 3);
				}else if(x.equals(radioButton_2)) {
					JFramePrincipal.setTipo((short) 4);
				}
			}
		};
		rdbtnDBObjects = new JRadioButton("Object dataBase");
		rdbtnDBObjects.setForeground(Color.BLACK);
		rdbtnDBObjects.setBounds(515, 689, 123, 23);
		add(rdbtnDBObjects);
		rdbtnDBObjects.setBackground(Color.LIGHT_GRAY);
		rdbtnDBObjects.addActionListener(radioGroupListener);
		
		rdbtnMongodb = new JRadioButton("MongoDB");
		rdbtnMongodb.setForeground(Color.BLACK);
		rdbtnMongodb.setBackground(Color.LIGHT_GRAY);
		rdbtnMongodb.setBounds(640, 689, 123, 23);
		add(rdbtnMongodb);
		rdbtnMongodb.addActionListener(radioGroupListener);
		
		rdbtnDbrelacional = new JRadioButton("DBRelacional");
		rdbtnDbrelacional.setForeground(Color.BLACK);
		rdbtnDbrelacional.setBackground(Color.LIGHT_GRAY);
		rdbtnDbrelacional.setBounds(765, 689, 123, 23);
		add(rdbtnDbrelacional);
		rdbtnDbrelacional.addActionListener(radioGroupListener);
		
		radioButton_2 = new JRadioButton("");
		radioButton_2.setForeground(Color.BLACK);
		radioButton_2.setBackground(Color.LIGHT_GRAY);
		radioButton_2.setBounds(890, 689, 123, 23);
		add(radioButton_2);
		radioButton_2.addActionListener(radioGroupListener);
		
		basesDeDatos.add(rdbtnDBObjects);
		basesDeDatos.add(rdbtnMongodb);
		basesDeDatos.add(rdbtnDbrelacional);
		basesDeDatos.add(radioButton_2);
		

	}


	/**
	 * Configuración de textfields
	 */
	private void textFields() {
		

		passwordLogin = new JTextField();
		passwordLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordLogin.setBounds(584, 425, 376, 30);
		passwordLogin.setBorder(null);
		passwordLogin.setOpaque(false);
		passwordLogin.setText("Contraseña");
		add(passwordLogin);

		
		usuarioLogin = new JTextField();
		usuarioLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usuarioLogin.setBounds(584, 318, 376, 30);
		usuarioLogin.setBorder(null);
		usuarioLogin.setOpaque(false);
		usuarioLogin.setText("Usuario");
		add(usuarioLogin);
		
	}


	/**
	 * Configuración de botones
	 * @param principal Frame padre de la aplicación
	 */
	private void botones(JFrame principal) {
		botonLogin = new JButton("");
		botonLogin.addActionListener(new ActionListener() {
			//Entra en la aplicación si el usuario y la contraseña son correctas
			public void actionPerformed(ActionEvent e) {
				
				//Si no ha seleccionado base de datos
				if(JFramePrincipal.getTipo()==0) {
					JOptionPane.showMessageDialog(principal, "Seleccione una base de datos");
				}else {
					//SI ACCEDE A LA BASE DE DATOS
					try {
						if(manager.validarUsuario(usuarioLogin.getText(),passwordLogin.getText(),"login",JFramePrincipal.getTipo())) {
							((JFramePrincipal) principal).getjPanelCalendario().setVisible(true);
							((JFramePrincipal) principal).getjPanelListado().setVisible(false);
							((JFramePrincipal) principal).getjPanelMenu().setVisible(true);
							((JFramePrincipal) principal).getjPanelRecomendaciones().setVisible(false);
							((JFramePrincipal) principal).getjPanelTopBar().setVisible(true);
							((JFramePrincipal) principal).getjPanelLogin().setVisible(false);
							
						//SI NO ACCEDE
						}else {
							JOptionPane.showMessageDialog(principal, "Usuario o Contraseña incorrectos");
						}
					} catch (IOException e1) {
						
					}
				}
			}
		});
		botonLogin.setBorder(null);
		botonLogin.setFocusPainted(false);
		botonLogin.setRolloverEnabled(false);
		botonLogin.setContentAreaFilled(false);	
		botonLogin.setBounds(578, 525, 382, 48);
		add(botonLogin);
		
		botonRegistrarse = new JButton("");
		botonRegistrarse.addActionListener(new ActionListener() {
			//Ejecuta la ventana de registro
			public void actionPerformed(ActionEvent e) {
				JDialogRegistro nRegistro= new JDialogRegistro();
				nRegistro.setVisible(true);
			}
		});
		botonRegistrarse.setBorder(null);
		botonRegistrarse.setFocusPainted(false);
		botonRegistrarse.setRolloverEnabled(false);
		botonRegistrarse.setContentAreaFilled(false);	
		botonRegistrarse.setBounds(128, 439, 257, 43);
		add(botonRegistrarse);
		
	}


	/**
	 * Configuración de los separadores(usados de decoración)
	 */
	private void separators() {
		JSeparator sombraUsuario = new JSeparator();
		sombraUsuario.setBounds(584, 346, 376, 2);
		add(sombraUsuario);
		
		JSeparator sombraLogin = new JSeparator();
		sombraLogin.setBounds(584, 453, 376, 2);
		add(sombraLogin);
		
	}

	/**
	 * Configuración de labels(usado en este caso como imagen de fondo)
	 */
	private void labels() {
		JLabel ImagenDeFondo = new JLabel("\r\n");
		ImagenDeFondo.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/imagenes/Login.png")));
		ImagenDeFondo.setBounds(0,0, 1024, 768);
		this.add(ImagenDeFondo);
		
	}
}
