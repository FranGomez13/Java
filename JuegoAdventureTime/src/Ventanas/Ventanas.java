package Ventanas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Clases.mapa;
import Clases.colisionesItems;
import Clases.colisionesObstaculos;
import Clases.items;
import Clases.obstaculos;
import Clases.personaje;

@SuppressWarnings("serial")
public class Ventanas extends Canvas{
	//VENTANAS
	private JFrame winMenuInicio;
	private JFrame winJugar;
	private JFrame winInstrucciones;
	private JFrame winHighScore;
	private JFrame winPerdio;
	//BOTONES
	private JButton btnJugar;
	private JButton btnInstrucciones;
	private JButton btnHighScore;
	private JButton btnAcercaDe;
	private JButton btnSalir;
	private JButton btnVolver1;
	private JButton btnVolver2;
	private JButton btnAceptar;
	//ETIQUETAS
	private JLabel lblnombre;
	private JLabel lblpuntaje;
	private JLabel lblp1;
	private JLabel lblp2;
	private JLabel lblp3;
	private JLabel lblp4;
	private JLabel lblp5;
	private JLabel lblp6;
	private JLabel lblp7;
	private JLabel lblp8;
	private JLabel lblp9;
	private JLabel lblp10;
	private JLabel lbln1;
	private JLabel lbln2;
	private JLabel lbln3;
	private JLabel lbln4;
	private JLabel lbln5;
	private JLabel lbln6;
	private JLabel lbln7;
	private JLabel lbln8;
	private JLabel lbln9;
	private JLabel lbln10;
	//DIMENSIONES
	private final int ANCHO = 1024;
	private final int ALTO = 665;
	//HASHMAPS
	private HashMap<String,BufferedImage> mapas;
	private HashMap<String,BufferedImage> obstaculos;
	private HashMap<String,BufferedImage> personaje;
	private HashMap<String,BufferedImage> items;
	//VARIABLES AUXILIARES
	private BufferStrategy dobleBuffer;
	private Graphics2D g2D;
	private String nombre = "";
	private Image icono;
	private KeyListener teclas;
	private int lastFpsTime;
	private int FPS;
	private boolean jugando  = true;
	private int play = 0;
	private int puntos;
	private int auxVidas;
	private String vidas;
	private String puntuacion;
	private final int[] puntaje = new int[11];
	private final String[] nombres = new String[11];
	//CLASES
	private personaje finn;
	private mapa mapa;
	private obstaculos obs;
	private items item;
	//COLISIONES
	private colisionesItems colisionItem;
	private colisionesObstaculos colisionObs;
	//FUENTES
	private Font normal;
	private Font negrita;
	//ARCHIVO
	private File puntuaciones = new File("Puntuaciones.csv");
	
	public Ventanas(){
		instanciasObj();
		ubicarObj();
		accionBtn();
		eventosTeclas();
		iniciarImagenes();
		iniciarVentanas();
		propiedades();
		winMenuInicio.setVisible(true);
		leerPuntuaciones();
		cicloPrincipal();
	}	
	
	public void instanciasObj(){
		winMenuInicio = new JFrame("Adventure Time - In The Search Of Jake");
		winJugar = new JFrame("Adventure Time - In The Search Of Jake");
		winInstrucciones = new JFrame("Instrucciones");
		winHighScore = new JFrame("Puntaciones Mas Altas - High Score");
		winPerdio = new JFrame("Game Over");
		//BOTONES
		btnJugar = new JButton("Jugar");
		btnInstrucciones = new JButton("Instrucciones");
		btnHighScore = new JButton("High Score");
		btnAcercaDe = new JButton("Acerca De");
		btnSalir = new JButton("Salir");
		btnVolver1 = new JButton("Volver");
		btnVolver2 = new JButton("Volver");
		btnAceptar = new JButton("Aceptar");
		//HASHMAPS
		personaje = new HashMap<String,BufferedImage>();
		mapas = new HashMap<String,BufferedImage>();
		obstaculos = new HashMap<String,BufferedImage>();
		items = new HashMap<String,BufferedImage>();
		icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Recursos/icono.png"));
		//ETIQUETAS
		lblnombre = new JLabel("Nombre:");
		lblpuntaje = new JLabel("Puntaje:");
		lblp1 = new JLabel();
		lblp2 = new JLabel();
		lblp3 = new JLabel();
		lblp4 = new JLabel();
		lblp5 = new JLabel();
		lblp6 = new JLabel();
		lblp7 = new JLabel();
		lblp8 = new JLabel();
		lblp9 = new JLabel();
		lblp10 = new JLabel();
		lbln1 = new JLabel();
		lbln2 = new JLabel();
		lbln3 = new JLabel();
		lbln4 = new JLabel();
		lbln5 = new JLabel();
		lbln6 = new JLabel();
		lbln7 = new JLabel();
		lbln8 = new JLabel();
		lbln9 = new JLabel();
		lbln10 = new JLabel();
		//CLASES
		finn = new personaje();
		item = new items(0,0);
		obs = new obstaculos(0,0);
		mapa = new mapa(0, 0,4,"mapa1");
		//COLISIONES
		colisionItem = new colisionesItems();
		colisionObs = new colisionesObstaculos();
		//FUENTES
		normal = new Font("Arial",Font.PLAIN,12 );
		negrita = new Font("Arial", Font.BOLD, 12);
	}
	
	public void ubicarObj(){
		btnJugar.setBounds(420,330,150,25);
		btnInstrucciones.setBounds(420,370,150,25);
		btnHighScore.setBounds(420, 410, 150, 25);
		btnAcercaDe.setBounds(420, 450, 150, 25);
		btnSalir.setBounds(420, 490, 150, 25);
		btnVolver1.setBounds(437,600,150,25);
		btnVolver2.setBounds(437,600,150,25);
		btnAceptar.setBounds(480, 300, 150, 20);
		lblnombre.setBounds(355, 300, 125, 20);
		lblpuntaje.setBounds(550, 300, 125, 20);
		lblp1.setBounds(545, 320, 50, 20);
		lblp2.setBounds(545, 340, 50, 20);
		lblp3.setBounds(545, 360, 50, 20);
		lblp4.setBounds(545, 380, 50, 20);
		lblp5.setBounds(545, 400, 50, 20);
		lblp6.setBounds(545, 420, 50, 20);
		lblp7.setBounds(545, 440, 50, 20);
		lblp8.setBounds(545, 460, 50, 20);
		lblp9.setBounds(545, 480, 50, 20);
		lblp10.setBounds(545, 500, 50, 20);
		lbln1.setBounds(350, 320, 50, 20);
		lbln2.setBounds(350, 340, 50, 20);
		lbln3.setBounds(350, 360, 50, 20);
		lbln4.setBounds(350, 380, 50, 20);
		lbln5.setBounds(350, 400, 50, 20);
		lbln6.setBounds(350, 420, 50, 20);
		lbln7.setBounds(350, 440, 50, 20);
		lbln8.setBounds(350, 460, 50, 20);
		lbln9.setBounds(350, 480, 50, 20);
		lbln10.setBounds(350, 500, 50, 20);
	}
	
	public void propiedades(){
		lblnombre.setFont(negrita);
		lblnombre.setForeground(Color.RED);
		lblpuntaje.setFont(negrita);
		lblpuntaje.setForeground(Color.RED);
		lblp1.setFont(negrita);
		lblp1.setHorizontalAlignment(SwingConstants.CENTER);
		lblp2.setFont(negrita);
		lblp2.setHorizontalAlignment(SwingConstants.CENTER);
		lblp3.setFont(negrita);
		lblp3.setHorizontalAlignment(SwingConstants.CENTER);
		lblp4.setFont(negrita);
		lblp4.setHorizontalAlignment(SwingConstants.CENTER);
		lblp5.setFont(negrita);
		lblp5.setHorizontalAlignment(SwingConstants.CENTER);
		lblp6.setFont(negrita);
		lblp6.setHorizontalAlignment(SwingConstants.CENTER);
		lblp7.setFont(negrita);
		lblp7.setHorizontalAlignment(SwingConstants.CENTER);
		lblp8.setFont(negrita);
		lblp8.setHorizontalAlignment(SwingConstants.CENTER);
		lblp9.setFont(negrita);
		lblp9.setHorizontalAlignment(SwingConstants.CENTER);
		lblp10.setFont(negrita);
		lblp10.setHorizontalAlignment(SwingConstants.CENTER);
		lbln1.setFont(negrita);
		lbln1.setHorizontalAlignment(SwingConstants.CENTER);
		lbln2.setFont(negrita);
		lbln2.setHorizontalAlignment(SwingConstants.CENTER);
		lbln3.setFont(negrita);
		lbln3.setHorizontalAlignment(SwingConstants.CENTER);
		lbln4.setFont(negrita);
		lbln4.setHorizontalAlignment(SwingConstants.CENTER);
		lbln5.setFont(negrita);
		lbln5.setHorizontalAlignment(SwingConstants.CENTER);
		lbln6.setFont(negrita);
		lbln6.setHorizontalAlignment(SwingConstants.CENTER);
		lbln7.setFont(negrita);
		lbln7.setHorizontalAlignment(SwingConstants.CENTER);
		lbln8.setFont(negrita);
		lbln8.setHorizontalAlignment(SwingConstants.CENTER);
		lbln9.setFont(negrita);
		lbln9.setHorizontalAlignment(SwingConstants.CENTER);
		lbln10.setFont(negrita);
		lbln10.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void accionBtn(){
		btnJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{	
					do{
						nombre = JOptionPane.showInputDialog(null, "Ingrese Su Nombre De Jugador", 
								null, JOptionPane.QUESTION_MESSAGE);
						if(nombre.equals(""))
							JOptionPane.showMessageDialog(null, "Ingrese Un Nombre", "¡ERROR!", 0);
						else if (nombre.length() >= 35)
							JOptionPane.showMessageDialog(null, "Nombre Demasiado Largo", "¡ERROR!", 0);
					}while(nombre.equals("") || nombre.length() >= 35);
					if (!nombre.equals("")){
						winJugar.setVisible(true);
						winMenuInicio.dispose();
						createBufferStrategy(2);
						dobleBuffer = getBufferStrategy();
						play = 1;
						puntos = 0;
						auxVidas = 5;
						vidas = "5Vida";
						finn.setLlave("personajecaminar1");
					}
				}catch(NullPointerException NullPointerException){}
			}
		});
		
		btnInstrucciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				winInstrucciones.setVisible(true);
				winMenuInicio.dispose();
			}
		});
		
		btnHighScore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarPuntuaciones();
				winHighScore.setVisible(true);
				winMenuInicio.dispose();
			}
		});
		
		btnAcercaDe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Proyecto Programacion II\n"
						+ "Desarrollado por: Francisco Gómez\n"
						+ "Ingenieria En Sistemas - UNAH"
						, "Acerca De", 1);
			}
		});
		
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
		
		btnVolver1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				winMenuInicio.setVisible(true);
				winInstrucciones.dispose();
			}
		});
		
		btnVolver2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				winMenuInicio.setVisible(true);
				winHighScore.dispose();
			}
		});
			
		btnAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				winJugar.setVisible(false);
				winPerdio.dispose();
				winMenuInicio.setVisible(true);
			}
		});
	}
	
	public void eventosTeclas(){
		teclas = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent k) {	
			}
			
			@Override
			public void keyReleased(KeyEvent k) {
				switch(k.getKeyCode()){
				case KeyEvent.VK_DOWN:
					finn.setAgacharse(false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent k) {
				switch(k.getKeyCode()){
					case KeyEvent.VK_SPACE:
						if( finn.isSaltar() == false){
							 finn.setSaltar(true);
							 finn.setBajar(false);
						}
					break;
					case KeyEvent.VK_UP:
						if( finn.isSaltar() == false){
							 finn.setSaltar(true);
							 finn.setBajar(false);
							}
					break;
					case KeyEvent.VK_DOWN:
						finn.setAgacharse(true);
				}
		};
	};
	Ventanas.this.addKeyListener(teclas);
	winJugar.addKeyListener(teclas);
}
		
	public void iniciarImagenes(){
		try {
			//IMAGENES PERSONAJE
			personaje.put("personajecaminar1", ImageIO.read(getClass().getResource("/Recursos/Personaje/Caminar/1.png")));
			personaje.put("personajecaminar2", ImageIO.read(getClass().getResource("/Recursos/Personaje/Caminar/2.png")));
			personaje.put("personajecaminar3", ImageIO.read(getClass().getResource("/Recursos/Personaje/Caminar/3.png")));
			personaje.put("personajecaminar4", ImageIO.read(getClass().getResource("/Recursos/Personaje/Caminar/4.png")));
			personaje.put("personajecaminar5", ImageIO.read(getClass().getResource("/Recursos/Personaje/Caminar/5.png")));
			personaje.put("personajecaminar6", ImageIO.read(getClass().getResource("/Recursos/Personaje/Caminar/6.png")));
			personaje.put("personajecaminar7", ImageIO.read(getClass().getResource("/Recursos/Personaje/Caminar/7.png")));
			personaje.put("personajecaminar8", ImageIO.read(getClass().getResource("/Recursos/Personaje/Caminar/8.png")));
			personaje.put("personajeagacharse1", ImageIO.read(getClass().getResource("/Recursos/Personaje/Agacharse/1.png")));
			personaje.put("personajeagacharse2", ImageIO.read(getClass().getResource("/Recursos/Personaje/Agacharse/2.png")));
			personaje.put("personajeagacharse3", ImageIO.read(getClass().getResource("/Recursos/Personaje/Agacharse/3.png")));
			personaje.put("personajeagacharse4", ImageIO.read(getClass().getResource("/Recursos/Personaje/Agacharse/4.png")));
			personaje.put("personajeagacharse5", ImageIO.read(getClass().getResource("/Recursos/Personaje/Agacharse/5.png")));
			personaje.put("personajeagacharse6", ImageIO.read(getClass().getResource("/Recursos/Personaje/Agacharse/6.png")));
			personaje.put("personajesaltar", ImageIO.read(getClass().getResource("/Recursos/Personaje/saltar.png")));
			personaje.put("2Vida", ImageIO.read(getClass().getResource("/Recursos/Personaje/Vidas/1Vida.png")));
			personaje.put("3Vida", ImageIO.read(getClass().getResource("/Recursos/Personaje/Vidas/2Vida.png")));
			personaje.put("4Vida", ImageIO.read(getClass().getResource("/Recursos/Personaje/Vidas/3Vida.png")));
			personaje.put("5Vida", ImageIO.read(getClass().getResource("/Recursos/Personaje/Vidas/4Vida.png")));
			//IMAGENES MAPA
			mapas.put("mapa1", ImageIO.read(getClass().getResource("/Recursos/Mapas/mapa.png")));
			//IMAGENES OBSTACULOS
			obstaculos.put("obstaculo1", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/1.png")));
			obstaculos.put("obstaculo2", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/2.png")));
			obstaculos.put("obstaculo3", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/3.png")));
			obstaculos.put("obstaculo4", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/4.png")));
			obstaculos.put("obstaculo5", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/5.png")));
			obstaculos.put("obstaculo6", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/6.png")));
			obstaculos.put("obstaculo7", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/7.png")));
			obstaculos.put("obstaculo8", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/8.png")));
			obstaculos.put("obstaculo9", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/9.png")));
			obstaculos.put("obstaculo10", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/10.png")));
			obstaculos.put("obstaculo11", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/11.png")));
			obstaculos.put("obstaculo12", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/12.png")));
			obstaculos.put("obstaculo13", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/13.png")));
			obstaculos.put("obstaculo14", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/14.png")));
			obstaculos.put("obstaculo15", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/15.png")));
			obstaculos.put("obstaculo16", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/16.png")));
			obstaculos.put("obstaculo17", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/17.png")));
			obstaculos.put("obstaculo18", ImageIO.read(getClass().getResource("/Recursos/Obstaculos/18.png")));
			//ITEMS
			items.put("item1", ImageIO.read(getClass().getResource("/Recursos/items/1.png")));
			items.put("item2", ImageIO.read(getClass().getResource("/Recursos/items/2.png")));
			items.put("item3", ImageIO.read(getClass().getResource("/Recursos/items/3.png")));
			items.put("item4", ImageIO.read(getClass().getResource("/Recursos/items/4.png")));
			items.put("item5", ImageIO.read(getClass().getResource("/Recursos/items/5.png")));
			items.put("item6", ImageIO.read(getClass().getResource("/Recursos/items/6.png")));
			items.put("item7", ImageIO.read(getClass().getResource("/Recursos/items/7.png")));
			items.put("item8", ImageIO.read(getClass().getResource("/Recursos/items/8.png")));
			items.put("item9", ImageIO.read(getClass().getResource("/Recursos/items/9.png")));
			items.put("item10", ImageIO.read(getClass().getResource("/Recursos/items/10.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void iniciarVentanas(){
		menuInicio();
		jugar();
		instrucciones();
		highScore();
		perdio();
	}
	
	public void menuInicio(){
		img_menu imgFondo = new img_menu();
		winMenuInicio.setContentPane(imgFondo);
		winMenuInicio.setIconImage(icono);
		winMenuInicio.setSize(ANCHO,ALTO);
		winMenuInicio.setResizable(false);
		winMenuInicio.setLocationRelativeTo(null);
		winMenuInicio.setLayout(null);
		winMenuInicio.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		winMenuInicio.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrar();
			}
		});
		winMenuInicio.add(btnJugar);
		winMenuInicio.add(btnInstrucciones);
		winMenuInicio.add(btnHighScore);
		winMenuInicio.add(btnAcercaDe);
		winMenuInicio.add(btnSalir);
	}
		
	public void jugar(){
		winJugar.setSize(ANCHO,ALTO);
		winJugar.setIconImage(icono);
		winJugar.setResizable(false);
		winJugar.setLocationRelativeTo(null);
		winJugar.add(Ventanas.this);
		winJugar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void instrucciones(){
		img_instrucciones imgInstrucciones = new img_instrucciones();
		winInstrucciones.setContentPane(imgInstrucciones);
		winInstrucciones.setIconImage(icono);
		winInstrucciones.setSize(ANCHO,ALTO);
		winInstrucciones.setResizable(false);
		winInstrucciones.setLocationRelativeTo(null);
		winInstrucciones.setLayout(null);
		winInstrucciones.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		winInstrucciones.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrar();
			}
		});
		winInstrucciones.add(btnVolver1);
	}
	
	public void highScore(){
		img_menu imgFondo = new img_menu();
		winHighScore.setContentPane(imgFondo);
		winHighScore.setIconImage(icono);
		winHighScore.setSize(ANCHO,ALTO);
		winHighScore.setResizable(false);
		winHighScore.setLocationRelativeTo(null);
		winHighScore.setLayout(null);
		winHighScore.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		winHighScore.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrar();
			}
		});
		winHighScore.add(lblnombre);
		winHighScore.add(lblpuntaje);
		winHighScore.add(lbln1);
		winHighScore.add(lbln2);
		winHighScore.add(lbln3);
		winHighScore.add(lbln4);
		winHighScore.add(lbln5);
		winHighScore.add(lbln6);
		winHighScore.add(lbln7);
		winHighScore.add(lbln8);
		winHighScore.add(lbln9);
		winHighScore.add(lbln10);
		winHighScore.add(lblp1);
		winHighScore.add(lblp2);
		winHighScore.add(lblp3);
		winHighScore.add(lblp4);
		winHighScore.add(lblp5);
		winHighScore.add(lblp6);
		winHighScore.add(lblp7);
		winHighScore.add(lblp8);
		winHighScore.add(lblp9);
		winHighScore.add(lblp10);
		winHighScore.add(btnVolver2);
	}
	
	public void perdio(){
		img_perdio imgPerdio = new img_perdio();
		winPerdio.setContentPane(imgPerdio);
		winPerdio.setIconImage(icono);
		winPerdio.setSize(640,358);
		winPerdio.setResizable(false);
		winPerdio.setLocationRelativeTo(null);
		winPerdio.setLayout(null);
		winPerdio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winPerdio.add(btnAceptar);
	}
		
	private void pintar(){
		 g2D = (Graphics2D)dobleBuffer.getDrawGraphics();
		 //PINTA EL FONDO(POSIBLE MODIFICACIONES)
		 mapa.pintar(g2D, mapas.get(mapa.getllave()), this);
		 //DIBUJA EL TEXTO
	     g2D.setColor(Color.BLACK);
	     g2D.setFont(negrita);
	     g2D.drawString("NOMBRE JUGADOR:",15,15);
	     g2D.drawString("PUNTUACIÓN:",850,15);
	     g2D.drawString("VIDAS:",400,15);
	     g2D.setColor(Color.RED);
	     g2D.setFont(normal);
	     g2D.drawString(nombre, 130, 15);
	     g2D.drawString(String.valueOf(puntos), 930, 15);
	     g2D.drawImage(personaje.get(vidas), 440, 0, this);
	     //OBSTACULOS
	     obs.pintar(g2D, obstaculos.get(obs.getLlave()), this);
	     //ITEMS
	     item.pintar(g2D, items.get(item.getLlave()), this);
	     //PERSONAJE
	     finn.pintar(g2D, personaje.get(finn.getLlave()), this);
	     
	     dobleBuffer.show();
	}
	
	public void actualizar(){
		mapa.mover();
		finn.mover();
		obs.mover();
		item.mover();
	}
		
	public void colisionesItems(){
		 try{
  		     if (colisionItem.colision(100, finn.getY(), item.getX(), item.getY(),
  				   personaje.get(finn.getLlave()), items.get(item.getLlave()))){
	    		   if (item.getLlave().equals("item7") || item.getLlave().equals("item10"))
	    			   puntos -= 10;
	    		   else if (item.getLlave().equals("item8") || item.getLlave().equals("item2"))
	    			   		puntos +=50;
			    		   else
			    			   puntos +=20;
		    	   item.sety(-200);
	    	 }	
		   }catch(Exception e){}
	}
	
	public void colisionesObs(){
		 try{
 		   if (colisionObs.colision(100, finn.getY(), obs.getX(), obs.getY(),
 				   personaje.get(finn.getLlave()), obstaculos.get(obs.getLlave()))){
	    	   		obs.sety(-200);
	    	   		obs.setx(-100);
	    	   		auxVidas--;
	    	   		vidas = auxVidas+"Vida";	
	    	   		if (auxVidas == 0)
	    	   			play = 2;
	    	}
		 }catch(Exception e){}
	}
	
	public void cicloPrincipal(){
		   long lastLoopTime = System.nanoTime(); 
	       final int TARGET_FPS = 60;
	       final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	       
	       while (jugando){
	    	   long now = System.nanoTime();
	    	   long updateLength = now - lastLoopTime;
	    	   lastLoopTime = now;
	    	   @SuppressWarnings("unused")
			   double delta = updateLength / ((double)OPTIMAL_TIME);
	    	   lastFpsTime += updateLength;
	    	   FPS++;
	    	   if (lastFpsTime >= 1000000000){
	    		   System.out.println("(FPS: "+FPS+")");
	    		   lastFpsTime = 0;
	    		   FPS = 0;
	    	   }
	    	  
	    	   if (play == 1){
	    		   pintar();    	   
	    		   actualizar();
	    		   colisionesItems();
	    		   colisionesObs();
	    	   }else if (play == 2){
	    		   winPerdio.setVisible(true);
	    		   mapa.setx(0);
	    		   finn.setY(400);
	    		   finn.setAgacharse(false);
	    		   finn.setSaltar(false);
	    		   item.setx(0);
	    		   obs.setx(0);
	    		   play = 0;
	    		   guardarPuntuaciones();
	    	   }
	    	   
	    	   try{Thread.sleep((lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );} 
	    	   catch(Exception e){};
	       }  
	    }
	
	public void mostrarPuntuaciones(){
		lbln1.setText(nombres[0]);
		lblp1.setText(String.valueOf(puntaje[0]));
		lbln2.setText(nombres[1]);
		lblp2.setText(String.valueOf(puntaje[1]));
		lbln3.setText(nombres[2]);
		lblp3.setText(String.valueOf(puntaje[2]));
		lbln4.setText(nombres[3]);
		lblp4.setText(String.valueOf(puntaje[3]));
		lbln5.setText(nombres[4]);
		lblp5.setText(String.valueOf(puntaje[4]));
		lbln6.setText(nombres[5]);
		lblp6.setText(String.valueOf(puntaje[5]));
		lbln7.setText(nombres[6]);
		lblp7.setText(String.valueOf(puntaje[6]));
		lbln8.setText(nombres[7]);
		lblp8.setText(String.valueOf(puntaje[7]));
		lbln9.setText(nombres[8]);
		lblp9.setText(String.valueOf(puntaje[8]));
		lbln10.setText(nombres[9]);
		lblp10.setText(String.valueOf(puntaje[9]));
	}
	
	public void leerPuntuaciones(){
		try {
			BufferedReader leer = new BufferedReader(new FileReader(puntuaciones));
			int i = 0;
			while ((puntuacion = leer.readLine()) !=null  || i>11){
				String[] aux = puntuacion.split(",");
				nombres[i] = aux[0];
				puntaje[i] = Integer.parseInt(aux[1]);
				i++;
			}
			leer.close();
			ordenar();
		} catch (Exception e) {}
	}
	
	public void ordenar(){
		for (int i = 1; i<puntaje.length; i++)
			for (int j = 0; j<puntaje.length-i; j++){
				if(puntaje[j] < puntaje[j+1]){
					int auxPuntaje = puntaje[j];
					puntaje[j] = puntaje[j+1];
					puntaje[j+1] = auxPuntaje;
					String auxNombres = nombres[j];
					nombres [j] = nombres[j+1];
					nombres [j+1] = auxNombres;
				}
			}
	}
	
	public void guardarPuntuaciones(){
		try {
			BufferedWriter escribir = new BufferedWriter(new FileWriter(puntuaciones));
			if (puntaje[10] < puntos){
				nombres[10]=nombre;
				puntaje[10]=puntos;
				ordenar();
				for (int i = 0; i<puntaje.length; i++){
					escribir.write(nombres[i]+","+puntaje[i]);
					escribir.newLine();
				}
			}
			escribir.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cerrar(){
		if(JOptionPane.showConfirmDialog(null, "¿Desea Salir?", "Salir",2) == 0)
			System.exit(0);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {}
		new Ventanas();
	}
}
