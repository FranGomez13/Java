package Reproductor;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import Libreria_TDALista.TDA_Lista;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ReproductorBasico extends JFrame implements BasicPlayerListener{
	private JButton btnPlay = new JButton("Play");
	private JButton btnStop = new JButton("Stop");
	private JButton btnAbrir = new JButton("Abrir");
	private JButton btnLista = new JButton("Lista");
	private JButton btnSiguiente = new JButton("Siguiente");
	private JButton btnAnterior = new JButton("Anterior");
	private JFileChooser abrir = new JFileChooser();
	private JSlider Tiempo = new JSlider();
	private File Archivo;
	private BasicPlayer Reproductor;
	private BasicPlayer Reproductor1;
	private double longitud_Bytes;
	private File cancion;
	private int aux = 0;
	private int rep = 0;
	private Image icono;
	private TDA_Lista playlist;
	private int posicion = 0;
	private int orden = 0;
	private boolean lista = true;
	private final JSeparator separator = new JSeparator();
	private final JScrollPane scrollPane = new JScrollPane();
	private JList<CancionPropiedades> listPlaylist;
	DefaultListModel<CancionPropiedades> modelo;
  
	public ReproductorBasico(){
		icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon.png"));
		setIconImage(icono);
		abrir.setFileFilter(new FileNameExtensionFilter("Archivos De Audio","mp3"));
		Reproductor = new BasicPlayer();
		Reproductor.addBasicPlayerListener(this);
		Reproductor1 = new BasicPlayer();
		Reproductor1.addBasicPlayerListener(this);
		playlist = new TDA_Lista();
		posicion = playlist.Primero();
		orden = playlist.Primero();
		Tiempo.setBounds(10, 10, 580, 10);
		Tiempo.setMinimum(0);
		Tiempo.setValue(0);
		setResizable(false);
		setVisible(true);
		setSize(600,400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		btnStop.setBounds(238, 31, 75, 25);
		getContentPane().add(btnStop);
		btnPlay.setBounds(153, 31, 75, 25);
		getContentPane().add(btnPlay);
		btnAbrir.setBounds(68, 31, 75, 25);
		getContentPane().add(btnAbrir);
		btnAnterior.setBounds(424, 31, 75, 25);
		getContentPane().add(btnAnterior);
		btnSiguiente.setBounds(323, 31, 91, 25);
		getContentPane().add(btnSiguiente);
		btnLista.setBounds(509, 31, 75, 25);
		getContentPane().add(btnLista);
		getContentPane().add(Tiempo);
		separator.setBounds(0, 70, 594, 2);
		getContentPane().add(separator);
		scrollPane.setBounds(12, 83, 572, 278);
		getContentPane().add(scrollPane);
		modelo = new DefaultListModel<CancionPropiedades>();
		listPlaylist = new JList<CancionPropiedades>(modelo);
		scrollPane.setViewportView(listPlaylist);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		listPlaylist.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.isMetaDown()) {
				}else if (e.isAltDown()) {}
				else if (e.getClickCount() == 2) {
					System.out.println("Doble Click");
				}
			}
		});
		
		btnAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (abrir()){
					try {
						modelo.addElement(new CancionPropiedades(Archivo.getName(),
								Archivo.getPath(), orden));
						playlist.Inserta(Archivo.getPath(), orden);
						if (Reproductor.getStatus()!=0 && Reproductor1.getStatus()!=0){
							Reproductor.open(new File((String)playlist.Recupera(orden)));
							setTitle(modelo.getElementAt(orden-1).getNombre());
							listPlaylist.setSelectedIndex(orden-1);
							posicion = orden + 1;
							Reproductor.play();
							rep = 0;
							btnPlay.setText("Pausa");
						}
						orden++;
					} catch (BasicPlayerException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	    btnPlay.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (Reproductor.getStatus() == 3 || Reproductor1.getStatus() == 3)
						reproducir();
					else if (Reproductor.getStatus() == 0 || Reproductor1.getStatus() == 0) 
						pausar();
					else if (Reproductor.getStatus() == 1 || Reproductor1.getStatus() == 1)
						resumir();
					else if (Reproductor.getStatus() == 2 || Reproductor1.getStatus() == 2)
						reproducir();
				}
			});  
		btnStop.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					detener();
				}
			});
		btnAnterior.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (posicion-1 > playlist.Primero()){
							if (rep == 0){
								Reproductor1.open(new File((String)playlist.Recupera(posicion-2)));
								Reproductor.stop();
								Reproductor1.play();
								setTitle(modelo.getElementAt(posicion-3).getNombre());
								listPlaylist.setSelectedIndex(posicion-3);
								posicion--;
								rep = 1;
							}else{
								Reproductor.open(new File((String)playlist.Recupera(posicion-2)));
								Reproductor1.stop();
								Reproductor.play();
								setTitle(modelo.getElementAt(posicion-3).getNombre());
								listPlaylist.setSelectedIndex(posicion-3);
								posicion--;
								rep = 0;
							}
							btnPlay.setText("Pausa");
					}else if (posicion-1 == playlist.Primero()){
						posicion = playlist.Fin();
						if (rep == 0){
							Reproductor1.open(new File((String)playlist.Recupera(posicion-1)));
							Reproductor.stop();
							Reproductor1.play();
							setTitle(modelo.getElementAt(posicion-2).getNombre());
							listPlaylist.setSelectedIndex(posicion-2);
							rep = 1;
						}else{
							Reproductor.open(new File((String)playlist.Recupera(posicion-1)));
							Reproductor1.stop();
							Reproductor.play();
							setTitle(modelo.getElementAt(posicion-2).getNombre());
							listPlaylist.setSelectedIndex(posicion-2);
							rep = 0;
						}
						btnPlay.setText("Pausa");
					}
				} catch (BasicPlayerException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSiguiente.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (posicion < playlist.Fin()){
							if (rep == 0){
								Reproductor1.open(new File((String)playlist.Recupera(posicion)));
								Reproductor.stop();
								Reproductor1.play();
								setTitle(modelo.getElementAt(posicion-1).getNombre());
								listPlaylist.setSelectedIndex(posicion-1);
								posicion++;
								rep = 1;
							}else{
								Reproductor.open(new File((String)playlist.Recupera(posicion)));
								Reproductor1.stop();
								Reproductor.play();
								setTitle(modelo.getElementAt(posicion-1).getNombre());
								listPlaylist.setSelectedIndex(posicion-1);
								posicion++;
								rep = 0;
							}
							btnPlay.setText("Pausa");
					}else if (posicion == playlist.Fin()){
						posicion = playlist.Primero();
						if (rep == 0){
							Reproductor1.open(new File((String)playlist.Recupera(posicion)));
							Reproductor.stop();
							Reproductor1.play();
							setTitle(modelo.getElementAt(posicion-1).getNombre());
							listPlaylist.setSelectedIndex(posicion-1);
							posicion++;
							rep = 1;
						}else{
							Reproductor.open(new File((String)playlist.Recupera(posicion)));
							Reproductor1.stop();
							Reproductor.play();
							setTitle(modelo.getElementAt(posicion-1).getNombre());
							listPlaylist.setSelectedIndex(posicion-1);
							posicion++;
							rep = 0;
						}
						btnPlay.setText("Pausa");
					}
				} catch (BasicPlayerException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lista == false){
					setSize(600,400);
					lista = true;
				}else{
					setSize(600,90);
					lista = false;
				}
			}
		});
	}
 			
	public boolean abrir(){
		int auxApertura = abrir.showOpenDialog(null);
		if (auxApertura == JFileChooser.CANCEL_OPTION) 
			return false;
		Archivo = abrir.getSelectedFile();
		//Poner carpeta JFilechooser a la ultima donde se abra algun archivo
		abrir.setCurrentDirectory(Archivo);
		return true;
	}
	
	public void reproducir(){
		try {
			if (rep == 0)
				Reproductor.play();
			else
				Reproductor1.play();
			
			btnPlay.setText("Pausa");
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}	
	}
	
	public void resumir(){
		try {
			if (rep == 0)
				Reproductor.resume();
			else
				Reproductor1.resume();
			
			btnPlay.setText("Pausa");
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}	
	}
	
	public void pausar(){
		try {
			if (rep == 0)
				Reproductor.pause();
			else
				Reproductor1.pause();
			
			btnPlay.setText("Play");
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}
	
	public void detener(){
		try {
			if (rep == 0)
				Reproductor.stop();
			else
				Reproductor1.stop();
			
			btnPlay.setText("Play");
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
		Tiempo.setValue(0);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {}
			new ReproductorBasico();
	}
	
	@Override
	public void opened(Object arg0, @SuppressWarnings("rawtypes") Map arg1) {
		if (arg1.containsKey("audio.length.bytes")) {
			  longitud_Bytes = Double.parseDouble(arg1.get("audio.length.bytes").toString());
			  aux = (int) longitud_Bytes;
			  Tiempo.setMaximum(aux);
			 }
	}
	
	@Override
	public void progress(int bytes_Leidos, long arg1, byte[] arg2, @SuppressWarnings("rawtypes") Map arg3) {
		 float progressUpdate = (float) (bytes_Leidos * 1.0f / longitud_Bytes * 1.0f);
		 int progressNow = (int) (longitud_Bytes * progressUpdate);
		 Tiempo.setValue(progressNow);
		 if (progressNow == longitud_Bytes){
				 btnPlay.setText("Play");
				 progressUpdate = 0;
				 if (progressUpdate == 0){
					if (posicion != playlist.Fin()){
						cancion = new File((String)playlist.Recupera(posicion));
						try {
							if (rep == 0){
								Reproductor1.open(cancion);
								rep = 1;
								setTitle(modelo.getElementAt(posicion-1).getNombre());
								listPlaylist.setSelectedIndex(posicion-1);
								posicion++;
								reproducir();
							}else{
								Reproductor.open(cancion);
								rep = 0;
								setTitle(modelo.getElementAt(posicion-1).getNombre());
								listPlaylist.setSelectedIndex(posicion-1);
								posicion++;
								reproducir();
							}
						} catch (BasicPlayerException e) {
							e.printStackTrace();
						}
				   }
				}
		 }
		 // Descomentando la siguiente línea se mosrtaría el progreso
		 // System.out.println(" -&gt; " + progressNow);
	}
	
	@Override
	public void setController(BasicController arg0) {}
	
	@Override
	public void stateUpdated(BasicPlayerEvent arg0) {}
	
}