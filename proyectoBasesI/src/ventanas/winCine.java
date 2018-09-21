package ventanas;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import clases.conexion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class winCine extends JFrame {
	private JButton btnVolver;
	private JButton btnActor;
	private JButton btnDirector;
	private JButton btnPelicula;
	private JButton btnCartelera;
	private JButton btnBoletos;
	private winLista lista;
	private winBoleto boletos;
	public winCine(JFrame jf, conexion conec) {
		setTitle("Menu Cine");
		getContentPane().setLayout(null);
		
		btnCartelera = new JButton("Cartelera");
		btnCartelera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista = new winLista(winCine.this, conec,"Carteleras");
				lista.setVisible(true);
				setVisible(false);
			}
		});
		btnCartelera.setBounds(60, 45, 89, 23);
		getContentPane().add(btnCartelera);
		
		btnPelicula = new JButton("Pelicula");
		btnPelicula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista = new winLista(winCine.this, conec,"Peliculas");
				lista.setVisible(true);
				setVisible(false);
			}
		});
		btnPelicula.setBounds(60, 11, 89, 23);
		getContentPane().add(btnPelicula);
		
		btnDirector = new JButton("Director");
		btnDirector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista = new winLista(winCine.this, conec,"Directores");
				lista.setVisible(true);
				setVisible(false);
			}
		});
		btnDirector.setBounds(60, 109, 89, 23);
		getContentPane().add(btnDirector);
		
		btnActor = new JButton("Actor");
		btnActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista = new winLista(winCine.this, conec,"Actores");
				lista.setVisible(true);
				setVisible(false);
			}
		});
		btnActor.setBounds(60, 143, 89, 23);
		getContentPane().add(btnActor);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(60, 177, 89, 23);
		getContentPane().add(btnVolver);
		
		btnBoletos = new JButton("Boletos");
		btnBoletos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boletos = new winBoleto(winCine.this, conec, "Registrar");
				boletos.setVisible(true);
				setVisible(false);
			}
		});
		btnBoletos.setBounds(60, 75, 89, 23);
		getContentPane().add(btnBoletos);
		setSize(226,250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrar();
			}
		});
	}
	
	public void cerrar() {
		if(JOptionPane.showConfirmDialog(null, "¿Desea Salir?", "Salir",2) == 0)
			System.exit(0);
	}
}
