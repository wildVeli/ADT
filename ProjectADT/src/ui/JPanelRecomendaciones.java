
package ui;

import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.BorderFactory;


public class JPanelRecomendaciones extends JPanel {

	/**
	 * 
	 */

	private static final long serialVersionUID = -1468518434081938442L;
	private DefaultTableModel dtm;

	/**
	 * Ventana de recomendaciones(tiene su propio mouse listener)
	 * @param personalMouseListener mouse listener especifico para esta ventana
	 */
	public JPanelRecomendaciones(MouseListener personalMouseListener) {

		
		setSize(755,729);
		setLayout(null);
		
		labels();
		
		favoritosComunidad();
		recomendacionesAmigos();
	}
	/**
	 * Configuración de los labels
	 */
	private void labels() {
		
		JLabel lblRecomendacionesDeAmigos = new JLabel("Recomendaciones de amigos");
		lblRecomendacionesDeAmigos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRecomendacionesDeAmigos.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecomendacionesDeAmigos.setBounds(375, 55, 368, 41);
		add(lblRecomendacionesDeAmigos);
		
		JLabel lblFavoritosDeLa = new JLabel("Favoritos de la comunidad");
		lblFavoritosDeLa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFavoritosDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblFavoritosDeLa.setBounds(0, 55, 377, 41);
		add(lblFavoritosDeLa);
		
	}
	/**
	 * Crea la parte de los favoritos de la comunidad(lo que recomienda la comunidad)
	 */
	private void favoritosComunidad() {
		
		//AQUÍ SE RECOGEN DATOS DE LA BASE DE DATOS Y SE PLASMAN EN LA TABLA
		Object[][] rowData = {
			    {"Isabel"},
			    {"ICarly"},
			    {"El chavo del 8"},
			    {"Hora de aventuras"},
			    {"lazy town"}
			};
			String[] columnNames = {"Nombre"};
		//FIN RECOGIDA
		
			
		JTable tablaFavoritosComunidad= new JTable(rowData,columnNames);
		JScrollPane scrollPaneFavoritosComunidad = new JScrollPane(tablaFavoritosComunidad);
		scrollPaneFavoritosComunidad.setBorder(BorderFactory.createEmptyBorder());
		scrollPaneFavoritosComunidad.setBounds(20, 128, 347, 381);
		tablaFavoritosComunidad.setFillsViewportHeight(true);
		add(scrollPaneFavoritosComunidad);
		tablaFavoritosComunidad.setEnabled(false);
		tablaFavoritosComunidad.setBackground(new Color(219, 219, 219));
		tablaFavoritosComunidad.setShowVerticalLines(false);
		tablaFavoritosComunidad.setShowGrid(false);
		tablaFavoritosComunidad.setRowHeight(20);
		tablaFavoritosComunidad.setTableHeader(null);
		
	}
	/**
	 * Crea la parte que lleva las recomendaciones efectuadas por amigos
	 */
	private void recomendacionesAmigos() {
		
		//AQUÍ SE RECOGEN DATOS DE LA BASE DE DATOS Y SE PLASMAN EN LA TABLA
		Object[][] rowData = {
			    {"Smith", "House"},
			    {"John", "Hora de aventuras"},
			    {"Sue", "Castle"},
			    {"Jane", "Vikings"},
			    {"Joe", "Juego de tronos"}
			};
			String[] columnNames = {"Amigo",
	                "Recomendación"
	                };
		//FIN RECOGIDA
		dtm = new DefaultTableModel(rowData,columnNames);
		JTable tablaRecomendacionesAmigos= new JTable(dtm);
		JScrollPane scrollPaneRecomendacionesAmigos = new JScrollPane(tablaRecomendacionesAmigos);
		scrollPaneRecomendacionesAmigos.setBorder(BorderFactory.createEmptyBorder());
		scrollPaneRecomendacionesAmigos.setBounds(387, 128, 347, 381);
		tablaRecomendacionesAmigos.setFillsViewportHeight(true);
		add(scrollPaneRecomendacionesAmigos);
		tablaRecomendacionesAmigos.setShowVerticalLines(false);
		tablaRecomendacionesAmigos.getColumn("Recomendación").setMinWidth(270);
		tablaRecomendacionesAmigos.getColumn("Recomendación").setWidth(270);
		tablaRecomendacionesAmigos.getColumn("Recomendación").setMaxWidth(270);
		tablaRecomendacionesAmigos.setBackground(new Color(219, 219, 219));
		
		JLabel lblRecomendaciones = new JLabel("Recomendaciones");
		lblRecomendaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecomendaciones.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRecomendaciones.setBounds(8, 12, 735, 26);
		add(lblRecomendaciones);
		tablaRecomendacionesAmigos.setDefaultEditor(Object.class, null);
		

		tablaRecomendacionesAmigos.getTableHeader().setReorderingAllowed(false);
		tablaRecomendacionesAmigos.getTableHeader().setBackground(new Color(0,0,0,0));
	}
}
