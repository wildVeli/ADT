package ui;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import control.PersonalMouseListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

/**
 * Frame principal, todo el programa funciona sobre este frame
 *
 */
public class JFramePrincipal extends JFrame {

	/**
	 * 
	 */

	
	private static final long serialVersionUID = 1L;
	private static short tipo=0;
	private static String usuarioConectado;
	
	private int anchoPrograma=1024;
	private int altoPrograma=768;
	
	private int pX;
	private int pY;
	
	private JPanel fondo;

	private MouseListener personalMouseListener;
	private JPanelTopBar jPanelTopBar;
	private JPanelMenu jPanelMenu;
	private JPanelCalendario jPanelCalendario;
	private JPanelRecomendaciones jPanelRecomendaciones;
	private JPanelLogin jPanelLogin;
	private JPanelListado jPanelListado;
	private JDialogRegistro jDialogRegistro;
	
	


	/**
	 * Inicia el jFramePrincipal, el inicio del programa
	 * @param args argumentos que con los que incia el programa, en este caso no usa ninguno
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFramePrincipal framePrincipal = new JFramePrincipal();
					framePrincipal.setVisible(true);
					framePrincipal.setBackground (new Color(1.0f,1.0f,1.0f,0.5f));
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Configura el frame que sirve de base para todos los componentes, crea mas componentes
	 */

	public JFramePrincipal() {
		
		fondo = new JPanel();
		fondo.setBorder(new EmptyBorder(5, 5, 5, 5));
		fondo.setLayout(null);
		
		setContentPane(fondo);
		setBounds(0,0, anchoPrograma, altoPrograma);
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		//Creamos el mouseListener que se encargará de llevar todos los movimientos del ratón, 
		//mandando como parámetro este jframe para poder acceder a los jpanel que crearemos y que estarán en dicho jframe
		personalMouseListener=new PersonalMouseListener(this);
		
		//Añadimos el mouselistener a esta ventana para controlar los registros que se produzcan en el jframe en vez de los jpanel
		this.addMouseListener(personalMouseListener);
		
		//Creamos los jpanel que utilizaremos en el jframe
		//Se le manda el mouselistener que creamos antes para que se añadan al mouselistener los componentes de dicho jpanel que necesitemos
		jPanelTopBar=new JPanelTopBar(this);
		
		//Añadimos el jpanel al contenedor principal del jframe y efectuamos las acciones deseadas sobre dicho panel
		getContentPane().add(jPanelTopBar);
		jPanelTopBar.setBounds(0, 0, 1024, 40);
		jPanelTopBar.setFocusable(true);
		//Se utilizará para arrastrar la pantalla con el jpanel pulsado
		jPanelTopBar.addMouseListener(personalMouseListener);
		//jPanelTopBar.setVisible(false);

		jPanelMenu=new JPanelMenu(personalMouseListener);
		jPanelMenu.setBounds(0, 39, 270, 729);
		fondo.add(jPanelMenu);
		
		jPanelCalendario=new JPanelCalendario();
		jPanelCalendario.setBounds(269, 39, 755, 729);
		fondo.add(jPanelCalendario);
		
		jPanelRecomendaciones=new JPanelRecomendaciones(personalMouseListener);
		jPanelRecomendaciones.setBounds(269, 39, 755, 729);
		fondo.add(jPanelRecomendaciones);
		
		jPanelLogin=new JPanelLogin(this);
		jPanelLogin.setBounds(0,0, 1024, 768);
		fondo.add(jPanelLogin);
		jPanelLogin.setVisible(true);
		
		jPanelListado=new JPanelListado();		
		jPanelListado.setBounds(269, 39, 755, 729);
		fondo.add(jPanelListado);
		
		
		JLabel ImagenDeFondo = new JLabel("\r\n");
		ImagenDeFondo.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/imagenes/Plantilla.png")));
		ImagenDeFondo.setBounds(0, 0, anchoPrograma, altoPrograma);
		fondo.add(ImagenDeFondo);
		
		moverVentana();
		visibilidadInicialVentanas();
	}
	
	/**
	 * Establece el estado de visibilidad con el que iniciará cada una de las ventanas
	 */
	private void visibilidadInicialVentanas() {
		jPanelCalendario.setVisible(false);
		jPanelListado.setVisible(false);
		jPanelMenu.setVisible(false);
		jPanelRecomendaciones.setVisible(false);
		jPanelTopBar.setVisible(true);
		jPanelLogin.setVisible(true);
		
	}

	/**
	 * 
	 * Efectua los calculos necesarios para mover la ventana y redibujarla al arrastrar el ratón mientras se encuentra en la barra superior.
	 * El efecto de mover ventana es desactivado al quitar la decoración de ventana, por eso se utiliza esta función
	 */
	private void moverVentana() {

		 jPanelTopBar.addMouseListener(new MouseAdapter() {
           public void mousePressed(MouseEvent me) {
               //Apuntar posicion X e Y de el ratón al hacer click
               pX = me.getX();
               pY = me.getY();

           }

            public void mouseDragged(MouseEvent me) {
          	  //calcular la diferencia de distancia entre la anterior posicion del ratón y la actual.
          	  //poneer la ventana en la nueva posición
          	  // posicion de la ventana + (calculo de la diferencia del ratón,es decir lo que se ha desplazado)
               setLocation(getLocation().x + me.getX() - pX,
                       getLocation().y + me.getY() - pY);

           }
       });

		 jPanelTopBar.addMouseMotionListener(new MouseMotionAdapter() {
           public void mouseDragged(MouseEvent me) {

              setLocation(getLocation().x + me.getX() - pX,
              		 getLocation().y + me.getY() - pY);
           }
       });
	}
	public JPanelTopBar getjPanelTopBar() {
		return jPanelTopBar;
	}

	public void setjPanelTopBar(JPanelTopBar jPanelTopBar) {
		this.jPanelTopBar = jPanelTopBar;
	}
	public MouseListener getPersonalMouseListener() {
		return personalMouseListener;
	}

	public void setPersonalMouseListener(MouseListener personalMouseListener) {
		this.personalMouseListener = personalMouseListener;
	}


	public JPanelMenu getjPanelMenu() {
		return jPanelMenu;
	}

	public void setjPanelMenu(JPanelMenu jPanelMenu) {
		this.jPanelMenu = jPanelMenu;
	}

	public JPanelCalendario getjPanelCalendario() {
		return jPanelCalendario;
	}

	public void setjPanelCalendario(JPanelCalendario jPanelCalendario) {
		this.jPanelCalendario = jPanelCalendario;
	}

	public JPanelRecomendaciones getjPanelRecomendaciones() {
		return jPanelRecomendaciones;
	}

	public void setjPanelRecomendaciones(JPanelRecomendaciones jPanelRecomendaciones) {
		this.jPanelRecomendaciones = jPanelRecomendaciones;
	}

	public JPanelLogin getjPanelLogin() {
		return jPanelLogin;
	}

	public void setjPanelLogin(JPanelLogin jPanelLogin) {
		this.jPanelLogin = jPanelLogin;
	}

	public JPanelListado getjPanelListado() {
		return jPanelListado;
	}

	public void setjPanelListado(JPanelListado jPanelListado) {
		this.jPanelListado = jPanelListado;
	}

	public JDialogRegistro getjDialogRegistro() {
		return jDialogRegistro;
	}

	public void setjDialogRegistro(JDialogRegistro jDialogRegistro) {
		this.jDialogRegistro = jDialogRegistro;
	}

	public static short getTipo() {
		return tipo;
	}

	public static void setTipo(short tipo) {
		JFramePrincipal.tipo = tipo;
	}

	public static String getUsuarioConectado() {
		return usuarioConectado;
	}

	public static void setUsuarioConectado(String usuarioConectado) {
		JFramePrincipal.usuarioConectado = usuarioConectado;
	}
}