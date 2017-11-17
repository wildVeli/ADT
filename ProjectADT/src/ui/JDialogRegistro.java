package ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import datos.Manager;
import sun.nio.cs.ext.TIS_620;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class JDialogRegistro extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5038539237899941790L;
	private JButton botonCancelar;
	private JButton botonRegistrarNuevo;
	private JTextField textFieldPassword;
	private JTextField textFieldUsuario;
	private Manager manager= new Manager();
	



	/**
	 * Crea el dialogo de registro(se crea en el login) para efectuar un registro de un usuario
	 * 
	 */
	public JDialogRegistro() {
		
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setModal(true);

		setSize(200, 300);		
		setLocationRelativeTo(null);

		setUndecorated(true);
		getContentPane().setLayout(null);
		
		textFields();
		separators();
		labels();
		botones();
		
	}

	/**
	 * Configuración de los campos de texto
	 */
	private void textFields() {
		textFieldUsuario = new JTextField("Usuario");
		textFieldUsuario.setBounds(58, 86, 86, 20);
		textFieldUsuario.setBorder(null);
		textFieldUsuario.setOpaque(false);
		getContentPane().add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textFieldPassword = new JTextField("Contraseña");
		textFieldPassword.setBounds(58, 149, 86, 20);
		textFieldPassword.setBorder(null);
		textFieldPassword.setOpaque(false);
		getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
	}
	
	/**
	 * Configuración de los separadores, que se utilizan como sombra de los campos en este caso(mera decoración)
	 */
	private void separators() {
		JSeparator sombraUsuario = new JSeparator();
		sombraUsuario.setBounds(58, 106, 86, 2);
		getContentPane().add(sombraUsuario);
		
		JSeparator sombraPassword = new JSeparator();
		sombraPassword.setBounds(58, 169, 86, 2);
		getContentPane().add(sombraPassword);
		
	}
	/**
	 * Creación y configuración de los labels de la ventana
	 */
	private void labels() {
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRegistro.setBounds(0, 11, 200, 28);
		getContentPane().add(lblRegistro);
		
	}
	/**
	 * Creación y configuración de botones(incluyendo sus listener)
	 */
	private void botones() {

		botonRegistrarNuevo = new JButton("Registrar");
		botonRegistrarNuevo.addActionListener(new ActionListener() {
			//Registra el usuario en la base de datos o devuelve error si ya existe
			public void actionPerformed(ActionEvent e) {
				
				//Comprobar en la base de datos si exite
				//Si existe le avisamos de que eliga otro nombre
				if(manager.validarUsuario(textFieldUsuario.getText(),textFieldPassword.getText(), "registro", JFramePrincipal.getTipo())) {
					JOptionPane.showMessageDialog(null, "Seleccione otro usuario, el seleccionado ya existe","Error",JOptionPane.ERROR_MESSAGE);
				//si no existe lo registramos y cerramos la ventana
				}else {
					manager.registrarUsuario(textFieldUsuario.getText(), textFieldPassword.getText(),JFramePrincipal.getTipo());
					dispose();
				}

			}
		});
		//botonRegistrarNuevo.setBorder(null);
		botonRegistrarNuevo.setFocusPainted(false);
		botonRegistrarNuevo.setRolloverEnabled(false);
		botonRegistrarNuevo.setContentAreaFilled(false);	
		botonRegistrarNuevo.setBounds(58, 211, 89, 23);
		getContentPane().add(botonRegistrarNuevo);
		
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cerrar la ventana
				dispose();
			}
		});
		//botonCancelar.setBorder(null);
		botonCancelar.setBackground(Color.blue);
		//botonCancelar.setFocusPainted(false);
		botonCancelar.setRolloverEnabled(false);
		botonCancelar.setContentAreaFilled(false);	
		botonCancelar.setBounds(58, 245, 89, 23);
		getContentPane().add(botonCancelar);
		
	}
}
