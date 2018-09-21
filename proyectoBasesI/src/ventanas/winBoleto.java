package ventanas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import clases.conexion;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class winBoleto extends JFrame {
	private JLabel lblFecha;
	private JLabel lblPelicula;
	private JLabel lblTipo;
	private JLabel lblSala;
	private JLabel lblCliente;
	private JTextField txtIdentidad;
	private JComboBox<Object> cmbPelicula;
	private JComboBox<Object> cmbTipo;
	private JComboBox<Object> cmbSala;
	private JTextField txtCantidad;
	private JButton btnGuardar;
	private JButton btnVolver;
	private JLabel lblHora;
	private JTextField txtHora;
	private JDateChooser dateChooser;
	
	public winBoleto(JFrame jf, conexion conec, String pantalla) {
		if (pantalla.equals("Registrar")) {
			registrar(jf, conec, pantalla);
		}else if (pantalla.equals("Venta")) {
			venta(jf, conec, pantalla);
		}
		setLocationRelativeTo(null);
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
	
	public void registrar(JFrame jf, conexion conec, String pantalla) {
		setTitle("Agregar Boleto");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(300, 185);
		getContentPane().setLayout(null);
		
		lblPelicula = new JLabel("Pelicula: ");
		lblPelicula.setBounds(10, 11, 62, 14);
		getContentPane().add(lblPelicula);
		
		cmbPelicula = new JComboBox<Object>();
		cmbPelicula.setBounds(82, 8, 192, 20);
		getContentPane().add(cmbPelicula);
		
		lblHora = new JLabel("Hora:");
		lblHora.setBounds(10, 36, 62, 14);
		getContentPane().add(lblHora);
		
		txtHora = new JTextField();
		txtHora.setBounds(82, 33, 100, 20);
		getContentPane().add(txtHora);
		txtHora.setColumns(10);
		
		lblTipo = new JLabel("Tipo: ");
		lblTipo.setBounds(10, 61, 62, 14);
		getContentPane().add(lblTipo);
		
		cmbTipo = new JComboBox<Object>();
		cmbTipo.setBounds(82, 58, 192, 20);
		getContentPane().add(cmbTipo);
		
		lblSala = new JLabel("Sala:");
		lblSala.setBounds(10, 86, 46, 14);
		getContentPane().add(lblSala);
		
		cmbSala = new JComboBox<Object>();
		cmbSala.setBounds(82, 83, 192, 20);
		getContentPane().add(cmbSala);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				guardar(jf, conec, pantalla);
			}
		});
		btnGuardar.setBounds(51, 114, 89, 23);
		getContentPane().add(btnGuardar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(150, 114, 89, 23);
		getContentPane().add(btnVolver);
		
		cmbTipo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				cmbSala.removeAllItems();
				ResultSet rs = conec.ejecutarConsulta("SELECT * FROM tblSala WHERE nombre='"
						+cmbTipo.getSelectedItem().toString()+"'");
				try {
					while (rs.next()) {
						cmbSala.addItem(rs.getString("nombre")+"/"
								+rs.getString("sucursal"));
					}
					rs.close();
				} catch (SQLException ex) {}
			}
		});
		
		ResultSet rs = conec.ejecutarConsulta("SELECT * FROM vwCarteleraInfo");
		ResultSet rs1 = conec.ejecutarConsulta("SELECT * FROM tblTipoBoleto");
		try {
			while (rs.next()) {
				cmbPelicula.addItem(rs.getString("nombre")+"-"+rs.getString("titulo"));
			}
			while (rs1.next()) {
				cmbTipo.addItem(rs1.getString("nombre"));
			}
			rs.close();
			rs1.close();
		} catch (SQLException e) {}
	}
	
	public void venta(JFrame jf, conexion conec, String pantalla) {
		setTitle("Venta Boletos");
		setSize(250, 250);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		
		lblFecha = new JLabel("Fecha: ");
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setBounds(10, 36, 61, 14);
		getContentPane().add(lblFecha);
		
		lblPelicula = new JLabel("Pelicula:");
		lblPelicula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPelicula.setBounds(10, 67, 61, 14);
		getContentPane().add(lblPelicula);
		
		lblSala = new JLabel("Sala:");
		lblSala.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSala.setBounds(10, 117, 61, 14);
		getContentPane().add(lblSala);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setBounds(10, 92, 61, 14);
		getContentPane().add(lblTipo);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setBounds(10, 142, 61, 14);
		getContentPane().add(lblCantidad);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(81, 36, 127, 20);
		dateChooser.setDateFormatString("yyyy-MM-dd");
		//dateChooser.setMinSelectableDate(new Date());
		getContentPane().add(dateChooser);
		
		cmbPelicula = new JComboBox<Object>();
		cmbPelicula.setBounds(81, 64, 127, 20);
		getContentPane().add(cmbPelicula);
		
		lblCliente = new JLabel("Cliente:");
		lblCliente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCliente.setBounds(10, 11, 61, 14);
		getContentPane().add(lblCliente);
		
		txtIdentidad = new JTextField();
		txtIdentidad.setBounds(81, 8, 127, 20);
		getContentPane().add(txtIdentidad);
		txtIdentidad.setColumns(10);
		
		cmbTipo = new JComboBox<Object>();
		cmbTipo.setBounds(81, 89, 127, 20);
		getContentPane().add(cmbTipo);
		
		cmbSala = new JComboBox<Object>();
		cmbSala.setBounds(81, 114, 127, 20);
		getContentPane().add(cmbSala);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(81, 139, 61, 20);
		getContentPane().add(txtCantidad);
		txtCantidad.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				guardar(jf, conec, pantalla);
			}
		});
		btnGuardar.setBounds(20, 177, 89, 23);
		getContentPane().add(btnGuardar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(119, 177, 89, 23);
		getContentPane().add(btnVolver);
		dateChooser.setDate(new Date());
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				String fecha = new SimpleDateFormat(dateChooser.getDateFormatString()).
						format(dateChooser.getDate());
				cmbPelicula.removeAllItems();
				ResultSet rs = conec.ejecutarConsulta("SELECT DISTINCT cartelera, pelicula FROM vwInformacionFuncion WHERE "
						+ "inicio <='"+fecha+"' AND fin >= '"+fecha+"'");
				try {
					while (rs.next()) {
						cmbPelicula.addItem(rs.getString("cartelera")+"-"+rs.getString("pelicula"));
					}
				} catch (SQLException ex) {}
			}
		});
		
		cmbPelicula.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				cmbTipo.removeAllItems();
				try {
					String[] p = cmbPelicula.getSelectedItem().toString().split("-"); 
					ResultSet rs = conec.ejecutarConsulta("SELECT DISTINCT formato FROM vwInformacionFuncion WHERE"
							+ " pelicula='"+p[1]+"'");
					try {
						while (rs.next()) {
							cmbTipo.addItem(rs.getString("formato"));
						}
						rs.close();
					} catch (SQLException exep) {}
				}catch(Exception ex) {}
				
			}
		});
		
		cmbTipo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				cmbSala.removeAllItems();
				try {
					String[] p = cmbPelicula.getSelectedItem().toString().split("-"); 
					ResultSet rs = conec.ejecutarConsulta("SELECT * FROM vwInformacionFuncion WHERE"
							+ " formato='"+cmbTipo.getSelectedItem().toString()+"' AND "
							+ "pelicula='"+p[1]+"'");
					try {
						while (rs.next()) {
							cmbSala.addItem(rs.getString("sala")+"/"+
							rs.getString("hora")+"/"+rs.getString("sucursal"));
						}
						rs.close();
					} catch (SQLException ex) {}
				}catch(Exception ex) {}
			}
		});
		
	}
	
	public void guardar(JFrame jf, conexion conec, String pantalla) {
		if (pantalla.equals("Registrar")) {
			String[] aux1 = cmbPelicula.getSelectedItem().toString().split("-");
			String[] aux2 = cmbSala.getSelectedItem().toString().split("/");
			try {
				CallableStatement cstmt 
				= conec.obtenerConexion().prepareCall("{call dbo.spRegistrarBoleto(?,?,?,?,?,?)}");  
		        cstmt.setString(1, aux1[0]);
		        cstmt.setString(2, aux1[1]);
		        cstmt.setString(3, txtHora.getText());
		        cstmt.setString(4, cmbTipo.getSelectedItem().toString());
			    cstmt.setString(5, aux2[0]);
			    cstmt.setString(6, aux2[1]);
			    cstmt.execute();
			    JOptionPane.showMessageDialog(null, "Boleto Registrado Correctamente");
			}catch (Exception e) { System.out.println(e.getMessage()); }
		}else if (pantalla.equals("Venta")) {
			String[] aux1 = cmbPelicula.getSelectedItem().toString().split("-");
			String[] aux2 = cmbSala.getSelectedItem().toString().split("/");
			String fecha = new SimpleDateFormat(dateChooser.getDateFormatString()).
					format(dateChooser.getDate());
			try {
				CallableStatement cstmt 
				= conec.obtenerConexion().prepareCall("{call dbo.spGuardarBoleto(?,?,?,?,?,?,?,?,?,?)}");  
		        cstmt.setString(1, txtIdentidad.getText());
		        cstmt.setString(2, aux1[0]);
		        cstmt.setString(3, aux1[1]);
		        cstmt.setString(4, aux2[2]);
		        cstmt.setString(5, fecha);
			    cstmt.setString(6, aux2[1]);
			    cstmt.setString(7, aux2[0]);
			    cstmt.setInt(8, Integer.parseInt(txtCantidad.getText()));
			    cstmt.registerOutParameter(9, java.sql.Types.NVARCHAR);
			    cstmt.registerOutParameter(10, java.sql.Types.INTEGER);
			    cstmt.execute();
			    JOptionPane.showMessageDialog(null, cstmt.getNString(9));
			}catch (Exception e) { System.out.println(e.getMessage()); }
		}
	}
}
