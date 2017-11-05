package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class JPanelTopBar extends JPanel{

	private JButton botonSalir;
	private JButton botonMinimizar;


	private static final long serialVersionUID = 1L;

	
	/**
	 * Panel superior que maneja los botones de salir y minimizar
	 * @param principal El frame padre de la aplicación 
	 */
	public JPanelTopBar(JFrame principal) {
        
		setBounds(0, 0, 1024, 40);
		setLayout(null);
		this.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		
	/*	
		nombrePrograma = new JLabel("EntertainmentDiary");
		nombrePrograma.setFont(new Font("Tahoma", Font.BOLD, 14));
		nombrePrograma.setForeground(Color.WHITE);
		nombrePrograma.setBounds(23, 11, 211, 20);
		this.add(nombrePrograma);
	*/
		
		botones(principal);
		
	}

	/**
	 * Configuracón de botones 
	 * @param principal Frame padre de la aplicación
	 */
	private void botones(JFrame principal) {
		
		
		botonSalir = new JButton("x");
		botonSalir.setBorder(null);
		botonSalir.setFocusPainted(false);
		botonSalir.setRolloverEnabled(false);
		botonSalir.setContentAreaFilled(false);	
		botonSalir.setVerticalAlignment(SwingConstants.TOP);
		botonSalir.setHorizontalAlignment(SwingConstants.CENTER);
		botonSalir.setFont(new Font("Tahoma", Font.BOLD, 33));
		botonSalir.setForeground(UIManager.getColor("CheckBox.background"));
		botonSalir.setBounds(980, 0, 38, 40);
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.dispose();
				System.out.println("hello");
			}
		});
		this.add(botonSalir);
		
		botonMinimizar = new JButton("-");
		botonMinimizar.setBorder(null);
		botonMinimizar.setFocusPainted(false);
		botonMinimizar.setRolloverEnabled(false);
		botonMinimizar.setContentAreaFilled(false);	
		botonMinimizar.setVerticalAlignment(SwingConstants.TOP);
		botonMinimizar.setForeground(UIManager.getColor("CheckBox.background"));
		botonMinimizar.setFont(new Font("Tahoma", Font.PLAIN, 46));
		botonMinimizar.setHorizontalAlignment(SwingConstants.CENTER);
		botonMinimizar.setBounds(932, 0, 44, 40);
		botonMinimizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.setState(Frame.ICONIFIED);
			}
		});
		add(botonMinimizar);		
	}

	
}
