package ventanas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import clases.conexion;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class winPeliCartelera extends JFrame {
	private JLabel lblCartelera;
	private JLabel lblFechaFin;
	private JLabel lblFechaInicio;
	private JLabel lblPelicula;
	private JDateChooser dtcFechaFin;
	private JDateChooser dtcFechaInicio;
	private JComboBox<Object> cmbPelicula;
	private JButton btnVolver;
	private JButton btnGuardar;
	private JComboBox<Object> cmbCartelera;
	public winPeliCartelera(JFrame jf, conexion conec, int a) {
		setTitle("Pelicula Cartelera");
		setSize(330,192);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrar();
			}
		});
		
		lblCartelera = new JLabel("Cartelera:");
		lblCartelera.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCartelera.setBounds(10, 11, 99, 14);
		getContentPane().add(lblCartelera);
		
		lblPelicula = new JLabel("Pelicula:");
		lblPelicula.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPelicula.setBounds(10, 36, 99, 14);
		getContentPane().add(lblPelicula);
		
		lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaInicio.setBounds(10, 61, 99, 14);
		getContentPane().add(lblFechaInicio);
		
		lblFechaFin = new JLabel("Fecha FIn:");
		lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaFin.setBounds(10, 86, 99, 14);
		getContentPane().add(lblFechaFin);
		Date d = new Date();
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
	    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		dtcFechaFin = new JDateChooser();
		dtcFechaFin.setDateFormatString("yyyy-MM-dd");
		dtcFechaFin.setDate(d);
		dtcFechaFin.setMinSelectableDate(d);
		dtcFechaFin.setMaxSelectableDate(calendar.getTime());
		dtcFechaFin.setBounds(119, 86, 150, 20);
		getContentPane().add(dtcFechaFin);
		
		dtcFechaInicio = new JDateChooser();
		dtcFechaInicio.setDate(d);
		dtcFechaInicio.setDateFormatString("yyyy-MM-dd");
		dtcFechaInicio.setMinSelectableDate(d);
		dtcFechaInicio.setMaxSelectableDate(calendar.getTime());
		dtcFechaInicio.setBounds(119, 61, 150, 20);
		getContentPane().add(dtcFechaInicio);
		
		cmbPelicula = new JComboBox<Object>();
		cmbPelicula.setBounds(119, 33, 150, 20);
		getContentPane().add(cmbPelicula);
		
		cmbCartelera = new JComboBox<Object>();
		cmbCartelera.setBounds(119, 8, 150, 20);
		getContentPane().add(cmbCartelera);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(58, 117, 89, 23);
		getContentPane().add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (a == 0) {
					guardar(conec,jf);
				}else if (a == 1) {
					actualizar(conec,jf);
				}
			}
		});
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(157, 117, 89, 23);
		getContentPane().add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				jf.setVisible(true);
			}
		});
		
		cargarCmb(conec);
	}
	
	public void cerrar() {
		if(JOptionPane.showConfirmDialog(null, "¿Desea Salir?", "Salir",2) == 0)
			System.exit(0);
	}
	
	public void cargarCmb(conexion conec) {
		ResultSet rs = conec.ejecutarConsulta("SELECT * FROM tblPelicula");
		ResultSet rs1 = conec.ejecutarConsulta("SELECT * FROM tblCartelera");
		try {
			while (rs.next()) {
				cmbPelicula.addItem(rs.getString("titulo"));
			}
			while (rs1.next()) {
				cmbCartelera.addItem(rs1.getString("nombreCartelera"));
			}
			rs.close();
			rs1.close();
		} catch (SQLException e) {}
	}
	
	public void guardar(conexion conec, JFrame jf) {
		String fFin = new SimpleDateFormat(dtcFechaFin.getDateFormatString()).format(dtcFechaFin.getDate());
		String fInicio = new SimpleDateFormat(dtcFechaInicio.getDateFormatString()).format(dtcFechaInicio.getDate());
		System.out.println(fInicio);
		System.out.println(fFin);
		try {
			CallableStatement cstmt 
			= conec.obtenerConexion().prepareCall("{call dbo.spPeliculaCartelera(?,?,?,?,?,?)}");  
	        cstmt.setString(1, cmbCartelera.getSelectedItem().toString());
	        cstmt.setString(2, cmbPelicula.getSelectedItem().toString());
	        cstmt.setString(3, fInicio);
	        cstmt.setString(4, fFin);
		    cstmt.registerOutParameter(5, java.sql.Types.NVARCHAR);
		    cstmt.registerOutParameter(6, java.sql.Types.INTEGER);
		    cstmt.execute();
		    JOptionPane.showMessageDialog(null, cstmt.getNString(5));
		}catch (Exception e) { System.out.println(e.getMessage()); }
	}
	
	public void actualizar(conexion conec, JFrame jf) {
		
	}
}
