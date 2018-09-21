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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import Libreria_TDAs.TDA_Lista;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ReproductorIntermedio extends JFrame implements BasicPlayerListener{
	private JButton btnPlay = new JButton("Play");
	private JButton btnStop = new JButton("Stop");
	private JButton btnAbrir = new JButton("Abrir");
	private JButton btnLista = new JButton("Lista");
	private JButton btnSiguiente = new JButton("Siguiente");
	private JButton btnAnterior = new JButton("Anterior");
	private JFileChooser abrir = new JFileChooser();
	private JSlider Tiempo = new JSlider();
	private JLabel lblTiempo = new JLabel("");
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
	private DefaultListModel<CancionPropiedades> modelo;
	private JSlider sldVolumen = new JSlider();

	public ReproductorIntermedio(){
		icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono.png"));
		setIconImage(icono);
		abrir.setFileFilter(new FileNameExtensionFilter("Archivos De Audio","mp3"));
		Reproductor = new BasicPlayer();
		Reproductor.addBasicPlayerListener(this);
		Reproductor1 = new BasicPlayer();
		Reproductor1.addBasicPlayerListener(this);
		playlist = new TDA_Lista();
		posicion = playlist.Primero();
		orden = playlist.Primero();
		Tiempo.setBounds(10, 10, 510, 10);
		//Tiempo.setMinimum(0);
		Tiempo.setValue(0);
		setResizable(false);
		setVisible(true);
		setSize(600,400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		lblTiempo.setBounds(529, 10, 55, 14);
		getContentPane().add(lblTiempo);
		sldVolumen.setBounds(10, 30, 100, 26);
		getContentPane().add(sldVolumen);
		btnStop.setBounds(314, 31, 55, 25);
		getContentPane().add(btnStop);
		btnPlay.setBounds(249, 31, 55, 25);
		getContentPane().add(btnPlay);
		btnAbrir.setBounds(184, 31, 55, 25);
		getContentPane().add(btnAbrir);
		btnAnterior.setBounds(444, 31, 55, 25);
		getContentPane().add(btnAnterior);
		btnSiguiente.setBounds(379, 31, 55, 25);
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
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.isMetaDown()){
					if (posicion-1 > listPlaylist.getSelectedIndex()+1)
						posicion--;
					else if (posicion-1 == listPlaylist.getSelectedIndex()+1){
							detener();
							setTitle("");
							posicion--;							
					}
					playlist.Suprime(listPlaylist.getSelectedIndex()+1);
					System.out.println(posicion);
					modelo.remove(listPlaylist.getSelectedIndex());
					orden--;
				}else if (e.isAltDown()){
				}else if (e.getClickCount()==2){
						try {
							if (rep == 0){
								posicion = listPlaylist.getSelectedIndex()+1;
								Reproductor1.open(new File((String)playlist.Recupera(posicion)));
								Reproductor.stop();
								Reproductor1.play();
								setTitle(modelo.getElementAt(posicion-1).getNombre());
								listPlaylist.setSelectedIndex(posicion-1);
								posicion++;
								rep = 1;
							}else{
								posicion = listPlaylist.getSelectedIndex()+1;
								Reproductor.open(new File((String)playlist.Recupera(posicion)));
								Reproductor1.stop();
								Reproductor.play();
								setTitle(modelo.getElementAt(posicion-1).getNombre());
								listPlaylist.setSelectedIndex(posicion-1);
								posicion++;
								rep = 0;
							}
							btnPlay.setText("Pausa");
						} catch (BasicPlayerException e1) {
							e1.printStackTrace();
						}
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
								rep = 1;
							}else{
								Reproductor.open(new File((String)playlist.Recupera(posicion-2)));
								Reproductor1.stop();
								Reproductor.play();
								rep = 0;
							}
							setTitle(modelo.getElementAt(posicion-3).getNombre());
							listPlaylist.setSelectedIndex(posicion-3);
							posicion--;
							btnPlay.setText("Pausa");
					}else if (posicion-1 == playlist.Primero()){
						posicion = playlist.Fin();
						if (rep == 0){
							Reproductor1.open(new File((String)playlist.Recupera(posicion-1)));
							Reproductor.stop();
							Reproductor1.play();
							rep = 1;
						}else{
							Reproductor.open(new File((String)playlist.Recupera(posicion-1)));
							Reproductor1.stop();
							Reproductor.play();
							rep = 0;
						}
						btnPlay.setText("Pausa");
						setTitle(modelo.getElementAt(posicion-2).getNombre());
						listPlaylist.setSelectedIndex(posicion-2);
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
								rep = 1;
							}else{
								Reproductor.open(new File((String)playlist.Recupera(posicion)));
								Reproductor1.stop();
								Reproductor.play();
								rep = 0;
							}
							setTitle(modelo.getElementAt(posicion-1).getNombre());
							listPlaylist.setSelectedIndex(posicion-1);
							posicion++;
							btnPlay.setText("Pausa");
					}else if (posicion == playlist.Fin()){
						posicion = playlist.Primero();
						if (rep == 0){
							Reproductor1.open(new File((String)playlist.Recupera(posicion)));
							Reproductor.stop();
							Reproductor1.play();
							rep = 1;
						}else{
							Reproductor.open(new File((String)playlist.Recupera(posicion)));
							Reproductor1.stop();
							Reproductor.play();
							rep = 0;
						}
						setTitle(modelo.getElementAt(posicion-1).getNombre());
						listPlaylist.setSelectedIndex(posicion-1);
						posicion++;
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
		sldVolumen.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					if (rep == 0)
						Reproductor.setGain(0.01*sldVolumen.getValue());
					else
						Reproductor1.setGain(0.01*sldVolumen.getValue());
				} catch (BasicPlayerException e1) {
					e1.printStackTrace();
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
	
	private String Duracion(double time){
		  int horas   = 0;
		  int minutos = 0;
		  int segundos = 0;
		  segundos = (int) (time*0.000001);
		  horas = (int) (segundos/3600);
		  if(horas > 0){
		    segundos -= 3600*horas;
		  }
		  minutos = (int) (segundos/60);
		  if(minutos > 0){
		    segundos -= 60*minutos;
		  }
		  return String.format("%02d:%02d:%02d", horas,minutos,segundos);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {}
			new ReproductorIntermedio();
	}
	
	@Override
	public void opened(Object arg0, @SuppressWarnings("rawtypes") Map arg1) {
		if (arg1.containsKey("duration")) {
			  lblTiempo.setText(Duracion(Double.parseDouble(arg1.get("duration").toString())));
		}
		
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
	}
	
	@Override
	public void setController(BasicController arg0) {}
	
	@Override
	public void stateUpdated(BasicPlayerEvent arg0) {}
}