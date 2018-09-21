package Clases;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;

@SuppressWarnings("serial")
public class Ventana extends JFrame{
	private JLabel lblTiempoSimulacion;
	private JLabel lblTiempo;
	private JLabel lblCaja1;
	private JLabel lblCaja2;
	private JLabel lblCaja3;
	private JLabel lblCaja4;
	private JLabel lblCaja5;
	private JLabel lblTotal;
	private JLabel lblDinero;
	private JLabel lblClientes;
	private JLabel lblDineroC1;
	private JLabel lblDineroC2;
	private JLabel lblDineroC3;
	private JLabel lblDineroC4;
	private JLabel lblDineroC5;
	private JLabel lblDineroTotal;
	private JLabel lblClientesC1;
	private JLabel lblClientesC2;
	private JLabel lblClientesC3;
	private JLabel lblClientesC4;
	private JLabel lblClientesC5;
	private JLabel lblClientesTotal;
	private JLabel lblNoAtendidos;
	private JLabel lblNoAtendidosC1;
	private JLabel lblNoAtendidosC2;
	private JLabel lblNoAtendidosC3;
	private JLabel lblNoAtendidosC4;
	private JLabel lblNoAtendidosC5;
	private JLabel lblNoAtendidosTot;
	private int DineroTotal;
	private int ClientesTotales;
	private int NoAtendidos;
	private Image icono;
	
	public Ventana(){
		icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono.png"));
		setIconImage(icono);
		Instancias();
		Propiedades();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void Instancias(){
		lblTiempoSimulacion = new JLabel("Tiempo Sim:");
		lblTiempo = new JLabel( "00:00:000" );
		lblCaja1 = new JLabel("Caja 1");
		lblCaja2 = new JLabel("Caja 2");	
		lblCaja3 = new JLabel("Caja 3");	
		lblCaja4 = new JLabel("Caja 4");	
		lblCaja5 = new JLabel("Caja 5");		
		lblTotal = new JLabel("Total");		
		lblDinero = new JLabel("Dinero:");		
		lblClientes = new JLabel("Clientes Aten.:");		
		lblDineroC1 = new JLabel("0");		
		lblDineroC2 = new JLabel("0");		
		lblDineroC3 = new JLabel("0");		
		lblDineroC4 = new JLabel("0");		
		lblDineroC5 = new JLabel("0");		
		lblDineroTotal = new JLabel("0");		
		lblClientesC1 = new JLabel("0");		
		lblClientesC2 = new JLabel("0");		
		lblClientesC3 = new JLabel("0");		
		lblClientesC4 = new JLabel("0");	
		lblClientesC5 = new JLabel("0");		
		lblClientesTotal = new JLabel("0");		
		lblNoAtendidos = new JLabel("No Atendidos:");		
		lblNoAtendidosC1 = new JLabel("0");		
		lblNoAtendidosC2 = new JLabel("0");		
		lblNoAtendidosC3 = new JLabel("0");		
		lblNoAtendidosC4 = new JLabel("0");		
		lblNoAtendidosC5 = new JLabel("0");		
		lblNoAtendidosTot = new JLabel("0");
	}

	public void Propiedades(){
		lblCaja1.setForeground(Color.RED);
		lblCaja2.setForeground(Color.RED);
		lblCaja3.setForeground(Color.RED);
		lblCaja4.setForeground(Color.RED);
		lblCaja5.setForeground(Color.RED);
		lblTotal.setForeground(Color.RED);
		lblDinero.setForeground(Color.RED);
		lblClientes.setForeground(Color.RED);
		lblDineroTotal.setForeground(Color.MAGENTA);
		lblClientesTotal.setForeground(Color.MAGENTA);
		lblNoAtendidosTot.setForeground(Color.MAGENTA);
		lblNoAtendidos.setForeground(Color.RED);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Simulacion Agencia Bancaria");
		setSize(730, 200);
		getContentPane().setLayout(null);
		lblTiempoSimulacion.setBounds(612, 11, 100, 14);
		getContentPane().add(lblTiempoSimulacion);
		lblTiempoSimulacion.setForeground(Color.BLUE);
		lblTiempoSimulacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiempo.setBounds(611, 36, 100, 14);
		getContentPane().add(lblTiempo);
		lblCaja1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaja1.setBounds(132, 11, 70, 14);
		getContentPane().add(lblCaja1);
		lblCaja2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaja2.setBounds(212, 11, 70, 14);
		getContentPane().add(lblCaja2);
		lblCaja3.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaja3.setBounds(292, 11, 70, 14);
		getContentPane().add(lblCaja3);
		lblCaja4.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaja4.setBounds(372, 11, 70, 14);
		getContentPane().add(lblCaja4);
		lblCaja5.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaja5.setBounds(452, 11, 70, 14);
		getContentPane().add(lblCaja5);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setBounds(532, 11, 70, 14);
		getContentPane().add(lblTotal);
		lblDinero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDinero.setBounds(10, 36, 100, 14);
		getContentPane().add(lblDinero);
		lblClientes.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClientes.setBounds(10, 61, 100, 14);
		getContentPane().add(lblClientes);
		lblDineroC1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDineroC1.setBounds(132, 36, 70, 14);
		getContentPane().add(lblDineroC1);
		lblDineroC2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDineroC2.setBounds(212, 36, 70, 14);
		getContentPane().add(lblDineroC2);
		lblDineroC3.setHorizontalAlignment(SwingConstants.CENTER);
		lblDineroC3.setBounds(292, 36, 70, 14);
		getContentPane().add(lblDineroC3);
		lblDineroC4.setHorizontalAlignment(SwingConstants.CENTER);
		lblDineroC4.setBounds(372, 36, 70, 14);
		getContentPane().add(lblDineroC4);
		lblDineroC5.setHorizontalAlignment(SwingConstants.CENTER);
		lblDineroC5.setBounds(452, 36, 70, 14);
		getContentPane().add(lblDineroC5);
		lblDineroTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblDineroTotal.setBounds(531, 36, 70, 14);
		getContentPane().add(lblDineroTotal);
		lblClientesC1.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientesC1.setBounds(132, 61, 70, 14);
		getContentPane().add(lblClientesC1);
		lblClientesC2.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientesC2.setBounds(212, 61, 70, 14);
		getContentPane().add(lblClientesC2);
		lblClientesC3.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientesC3.setBounds(292, 61, 70, 14);
		getContentPane().add(lblClientesC3);
		lblClientesC4.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientesC4.setBounds(372, 61, 70, 14);
		getContentPane().add(lblClientesC4);
		lblClientesC5.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientesC5.setBounds(452, 61, 70, 14);
		getContentPane().add(lblClientesC5);
		lblClientesTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientesTotal.setBounds(532, 61, 70, 14);
		getContentPane().add(lblClientesTotal);
		lblNoAtendidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNoAtendidos.setBounds(10, 102, 100, 14);
		getContentPane().add(lblNoAtendidos);
		lblNoAtendidosC1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoAtendidosC1.setBounds(132, 102, 70, 14);
		getContentPane().add(lblNoAtendidosC1);
		lblNoAtendidosC2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoAtendidosC2.setBounds(212, 102, 70, 14);
		getContentPane().add(lblNoAtendidosC2);
		lblNoAtendidosC3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoAtendidosC3.setBounds(292, 102, 70, 14);
		getContentPane().add(lblNoAtendidosC3);
		lblNoAtendidosC4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoAtendidosC4.setBounds(372, 102, 70, 14);
		getContentPane().add(lblNoAtendidosC4);
		lblNoAtendidosC5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoAtendidosC5.setBounds(452, 102, 70, 14);
		getContentPane().add(lblNoAtendidosC5);
		lblNoAtendidosTot.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoAtendidosTot.setBounds(532, 102, 70, 14);
		getContentPane().add(lblNoAtendidosTot);
	}

	public void Caja1(int d, int c, int n){
		lblDineroC1.setText(String.valueOf(d));
		lblClientesC1.setText(String.valueOf(c));
		lblNoAtendidosC1.setText(String.valueOf(n));
	}
	
	public void Caja2(int d, int c, int n){
		lblDineroC2.setText(String.valueOf(d));
		lblClientesC2.setText(String.valueOf(c));
		lblNoAtendidosC2.setText(String.valueOf(n));
	}
	
	public void Caja3(int d, int c, int n){
		lblDineroC3.setText(String.valueOf(d));
		lblClientesC3.setText(String.valueOf(c));
		lblNoAtendidosC3.setText(String.valueOf(n));
	}
	
	public void Caja4(int d, int c, int n){
		lblDineroC4.setText(String.valueOf(d));
		lblClientesC4.setText(String.valueOf(c));
		lblNoAtendidosC4.setText(String.valueOf(n));
	}
	
	public void Caja5(int d, int c, int n){
		lblDineroC5.setText(String.valueOf(d));
		lblClientesC5.setText(String.valueOf(c));
		lblNoAtendidosC5.setText(String.valueOf(n));
	}
	
	public void Total(int x){
		DineroTotal = Integer.parseInt(lblDineroC1.getText()) 
			+ Integer.parseInt(lblDineroC2.getText()) + Integer.parseInt(lblDineroC3.getText())
			+ Integer.parseInt(lblDineroC4.getText()) + Integer.parseInt(lblDineroC5.getText());
		
		ClientesTotales = Integer.parseInt(lblClientesC1.getText())
			+ Integer.parseInt(lblClientesC2.getText()) + Integer.parseInt(lblClientesC3.getText())
			+ Integer.parseInt(lblClientesC4.getText()) + Integer.parseInt(lblClientesC5.getText());
		
		NoAtendidos = Integer.parseInt(lblNoAtendidosC1.getText())
			+ Integer.parseInt(lblNoAtendidosC2.getText()) + Integer.parseInt(lblNoAtendidosC3.getText())
			+ Integer.parseInt(lblNoAtendidosC4.getText()) + Integer.parseInt(lblNoAtendidosC5.getText())
			+ x;
		
		lblDineroTotal.setText(String.valueOf(DineroTotal));
		lblClientesTotal.setText(String.valueOf(ClientesTotales));
		lblNoAtendidosTot.setText(String.valueOf(NoAtendidos));
	}

	public void Tiempo(String tiempo){
		lblTiempo.setText(tiempo);
	}
	
}
