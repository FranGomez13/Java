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
public class winMenu extends JFrame {
	private JButton btnFacturas;
	private JButton btnClientes;
	private JButton btnEmpleados;
	private JButton btnSalir;
	private JButton btnCerrarSesion;
	private JButton btnCine;
	private JButton btnBoletos;
	private winLista lista;
	private winFacturacion facturacion;
	private winCine cine;
	private winBoleto boletos;
	public winMenu(JFrame jf, conexion conec) {
		setTitle("Menu");
		getContentPane().setLayout(null);
		btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "¿Desea Cerrar La Sesion?", "Cerrar Sesion",2) == 0) {
					setVisible(false);
					jf.setVisible(true);
				}
			}
		});
		btnCerrarSesion.setBounds(65, 186, 105, 23);
		getContentPane().add(btnCerrarSesion);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
			}
		});
		btnSalir.setBounds(65, 220, 105, 23);
		getContentPane().add(btnSalir);
		
		btnEmpleados = new JButton("Empleados");
		btnEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista = new winLista(winMenu.this,conec,"Empleados");
				lista.setVisible(true);
				setVisible(false);
			}
		});
		btnEmpleados.setBounds(65, 152, 105, 23);
		getContentPane().add(btnEmpleados);
		
		btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista = new winLista(winMenu.this,conec,"Clientes");
				lista.setVisible(true);
				setVisible(false);
			}
		});
		btnClientes.setBounds(65, 116, 105, 23);
		getContentPane().add(btnClientes);
		
		btnFacturas = new JButton("Facturas");
		btnFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				facturacion = new winFacturacion(winMenu.this,conec);
				facturacion.setVisible(true);
				setVisible(false);
			}
		});
		btnFacturas.setBounds(65, 82, 105, 23);
		getContentPane().add(btnFacturas);
		
		btnBoletos = new JButton("Boletos");
		btnBoletos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boletos = new winBoleto(winMenu.this, conec, "Venta");
				boletos.setVisible(true);
				setVisible(false);
			}
		});
		btnBoletos.setBounds(65, 48, 105, 23);
		getContentPane().add(btnBoletos);
		
		btnCine = new JButton("Cine");
		btnCine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cine = new winCine(winMenu.this,conec);
				cine.setVisible(true);
				setVisible(false);
			}
		});
		btnCine.setBounds(65, 14, 105, 23);
		getContentPane().add(btnCine);
		setSize(250,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		winMenu.this.addWindowListener(new WindowAdapter() {
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
