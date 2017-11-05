package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import ui.JFramePrincipal;

/**
 * Mouse listener que recopila todas las acciones efectuadas por la seccion menu de JPanelMenu
 * @author casa
 *
 */
public class PersonalMouseListener implements MouseListener{

	private JFramePrincipal principal;

	
	public PersonalMouseListener(JFramePrincipal jFrame) {
		principal=jFrame;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		

		 Object seleccion = e.getSource();
	//Botones Menu	
		if(seleccion.equals(principal.getjPanelMenu().getBotonListado())) {
			principal.getjPanelListado().setVisible(true);
			principal.getjPanelCalendario().setVisible(false);
			principal.getjPanelRecomendaciones().setVisible(false);
			
		}else if(seleccion.equals(principal.getjPanelMenu().getBotonCalendario())) {
			principal.getjPanelListado().setVisible(false);
			principal.getjPanelCalendario().setVisible(true);
			principal.getjPanelRecomendaciones().setVisible(false);
		}else if(seleccion.equals(principal.getjPanelMenu().getBotonRecomendaciones())) {
			principal.getjPanelListado().setVisible(false);
			principal.getjPanelCalendario().setVisible(false);
			principal.getjPanelRecomendaciones().setVisible(true);
		}
		
	
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object seleccion = e.getSource();
		
		if(seleccion.equals(principal.getjPanelMenu().getBotonListado())){
			principal.getjPanelMenu().putShadowBotonListado(true);
		}else if(seleccion.equals(principal.getjPanelMenu().getBotonCalendario())){
			principal.getjPanelMenu().putShadowBotonCalendario(true);
		}else if(seleccion.equals(principal.getjPanelMenu().getBotonRecomendaciones())){
			principal.getjPanelMenu().putShadowBotonRecomendaciones(true);
		}
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object seleccion = e.getSource();
		
		if(seleccion.equals(principal.getjPanelMenu().getBotonListado())){
			principal.getjPanelMenu().putShadowBotonListado(false);
		}else if(seleccion.equals(principal.getjPanelMenu().getBotonCalendario())){
			principal.getjPanelMenu().putShadowBotonCalendario(false);
		}else if(seleccion.equals(principal.getjPanelMenu().getBotonRecomendaciones())){
			principal.getjPanelMenu().putShadowBotonRecomendaciones(false);
		}
		principal.repaint();
		principal.validate();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
