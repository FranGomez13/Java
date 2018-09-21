package ventanas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import clases.conexion;

import javax.swing.JTextField;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class winInformacion extends JFrame {
	//Personas
	private JLabel lblPrimerApellido;
	private JLabel lblSegundoNombre;
	private JLabel lblPrimerNombre;
	private JLabel lblSexo;
	private JLabel lblNacionalidad;
	private JLabel lblIdentidad;
	private JLabel lblDireccion;
	private JLabel lblCorreo;
	private JTextField txtPrimerNombre;
	private JTextField txtSegundoNombre;
	private JTextField txtPrimerApellido;
	private JTextField txtSegundoApellido;
	private JTextField txtCorreo;
	private JTextField txtDireccion;
	private JTextField txtIdentidad;
	private JComboBox<Object> cmbSexo;
	private JComboBox<Object> cmbNacionalidad;
	private JLabel lblSegundoApellido;
	//PELICULA
	private JFileChooser abrir = new JFileChooser();
	private JLabel lblSinopsis;
	private JLabel lblTitulo;
	private JLabel lblDuracion;
	private JLabel lblPais;
	private JLabel lblEstudio;
	private JLabel lblDirector;
	private JLabel lblAnio;
	private JTextField txtTitulo;
	private JTextField txtDuracion;
	private JTextField txtAnio;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnImagen;
	private JTextArea txtSinopsis;
	private JComboBox<Object> cmbEstudio;
	private JComboBox<Object> cmbDirector;
	private JLabel lblClasificacion;
	private JComboBox<Object> cmbClasificacion;
	private String img = "";

	public winInformacion(JFrame jf, conexion conec, String pantalla, int a) {
		if (pantalla.equals("Peliculas")) {
			iniciarPeliculas(conec, jf, pantalla, a);
		}else if (pantalla.equals("Carteleras")) {
			iniciarGenerico(conec, jf, pantalla, a);
		}else if (pantalla.equals("Directores")) {
			iniciarGenerico(conec, jf, pantalla, a);
		}else if (pantalla.equals("Actores")) {
			iniciarGenerico(conec, jf, pantalla, a);
		}else {
			iniciarGenerico(conec, jf, pantalla, a);
			iniciarComponentes(conec, jf, pantalla, a);
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrar();
			}
		});
	}
	
	public winInformacion(JFrame jf, conexion conec, String cod, String pantalla, int a) {
		if (pantalla.equals("Peliculas")) {
			iniciarPeliculas(conec, jf, pantalla, a);
		}else if (pantalla.equals("Carteleras")) {
			iniciarGenerico(conec, jf, pantalla, a);
		}else if (pantalla.equals("Directores")) {
			iniciarGenerico(conec, jf, pantalla, a);
		}else if (pantalla.equals("Actores")) {
			iniciarGenerico(conec, jf, pantalla, a);
		}else {
			iniciarGenerico(conec, jf, pantalla, a);
			iniciarComponentes(conec, jf, pantalla, a);
			cargarInformacion(conec,cod, pantalla);
		}
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
	
	public void iniciarGenerico(conexion conec, JFrame jf, String pantalla, int a) {
		setTitle("Informacion "+pantalla);
		getContentPane().setLayout(null);
		setSize(350,310);
		setLocationRelativeTo(null);
		lblPrimerNombre = new JLabel("Primer Nombre:");
		lblPrimerNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrimerNombre.setBounds(10, 11, 116, 14);
		getContentPane().add(lblPrimerNombre);
		
		lblSegundoNombre = new JLabel("Segundo Nombre:");
		lblSegundoNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSegundoNombre.setBounds(10, 36, 116, 14);
		getContentPane().add(lblSegundoNombre);
		
		lblPrimerApellido = new JLabel("Primer Apellido: ");
		lblPrimerApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrimerApellido.setBounds(10, 61, 116, 14);
		getContentPane().add(lblPrimerApellido);
		
		lblSegundoApellido = new JLabel("Segundo Apellido:");
		lblSegundoApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSegundoApellido.setBounds(10, 86, 116, 14);
		getContentPane().add(lblSegundoApellido);
		
		txtPrimerNombre = new JTextField();
		txtPrimerNombre.setBounds(136, 8, 175, 20);
		getContentPane().add(txtPrimerNombre);
		txtPrimerNombre.setColumns(10);
		
		txtSegundoNombre = new JTextField();
		txtSegundoNombre.setBounds(136, 33, 175, 20);
		getContentPane().add(txtSegundoNombre);
		txtSegundoNombre.setColumns(10);
		
		txtPrimerApellido = new JTextField();
		txtPrimerApellido.setBounds(136, 58, 175, 20);
		getContentPane().add(txtPrimerApellido);
		txtPrimerApellido.setColumns(10);
		
		txtSegundoApellido = new JTextField();
		txtSegundoApellido.setBounds(136, 83, 175, 20);
		getContentPane().add(txtSegundoApellido);
		txtSegundoApellido.setColumns(10);

		lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNacionalidad.setBounds(10, 111, 116, 14);
		getContentPane().add(lblNacionalidad);

		cmbNacionalidad = new JComboBox<Object>();
		cmbNacionalidad.setBounds(136, 108, 175, 20);
		getContentPane().add(cmbNacionalidad);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				guardar(conec, pantalla, a, jf);
			}
		});
		btnGuardar.setBounds(68, 237, 89, 23);
		getContentPane().add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jf.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(176, 237, 89, 23);
		getContentPane().add(btnCancelar);
		
		ResultSet rs1 = conec.ejecutarConsulta("SELECT DISTINCT * FROM tblPais");
		try {
			while (rs1.next()) {
				cmbNacionalidad.addItem(rs1.getString("nombre"));
			}
			rs1.close();
		} catch (SQLException e) {}
	}
	
	public void iniciarPeliculas(conexion conec, JFrame jf, String pantalla, int a) {
		abrir.setFileFilter(new FileNameExtensionFilter("Imagenes","jpg"));
		setTitle("Pelicula");
		getContentPane().setLayout(null);
		setSize(350,340);
		setLocationRelativeTo(null);
		lblTitulo = new JLabel("Titulo:");
		lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitulo.setBounds(10, 11, 116, 14);
		getContentPane().add(lblTitulo);

		lblSinopsis = new JLabel("Sinopsis:");
		lblSinopsis.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSinopsis.setBounds(10, 36, 116, 14);
		getContentPane().add(lblSinopsis);
		
		lblDuracion = new JLabel("Duracion:");
		lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDuracion.setBounds(10, 111, 116, 14);
		getContentPane().add(lblDuracion);
		
		lblAnio = new JLabel("A\u00F1o:");
		lblAnio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnio.setBounds(10, 136, 116, 14);
		getContentPane().add(lblAnio);
		
		lblDirector = new JLabel("Director:");
		lblDirector.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDirector.setBounds(10, 161, 116, 14);
		getContentPane().add(lblDirector);
		
		lblEstudio = new JLabel("Estudio:");
		lblEstudio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstudio.setBounds(10, 186, 116, 14);
		getContentPane().add(lblEstudio);
		
		lblPais = new JLabel("Pais:");
		lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPais.setBounds(10, 211, 116, 14);
		getContentPane().add(lblPais);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(136, 8, 175, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtDuracion = new JTextField();
		txtDuracion.setBounds(136, 108, 175, 20);
		getContentPane().add(txtDuracion);
		txtDuracion.setColumns(10);
		
		txtAnio = new JTextField();
		txtAnio.setBounds(136, 133, 175, 20);
		getContentPane().add(txtAnio);
		txtAnio.setColumns(10);
		
		cmbNacionalidad = new JComboBox<Object>();
		cmbNacionalidad.setBounds(136, 208, 175, 20);
		getContentPane().add(cmbNacionalidad);
		
		
		txtSinopsis = new JTextArea();
		JScrollPane sp = new JScrollPane(txtSinopsis);
		sp.setBounds(136, 31, 175, 66);
		getContentPane().add(sp);
		
		cmbEstudio = new JComboBox<Object>();
		cmbEstudio.setBounds(136, 183, 175, 20);
		getContentPane().add(cmbEstudio);
		
		cmbDirector = new JComboBox<Object>();
		cmbDirector.setBounds(136, 158, 175, 20);
		getContentPane().add(cmbDirector);
		
		lblClasificacion = new JLabel("Clasificacion:");
		lblClasificacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClasificacion.setBounds(10, 236, 116, 14);
		getContentPane().add(lblClasificacion);
		
		cmbClasificacion = new JComboBox<Object>();
		cmbClasificacion.setBounds(136, 233, 175, 20);
		getContentPane().add(cmbClasificacion);
		
		btnImagen = new JButton("Portada");
		btnImagen.setBounds(10, 260, 50, 23);
		add(btnImagen);
		
		btnImagen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				img = abrir();
			}
		});
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				guardar(conec, pantalla, a, jf);
			}
		});
		btnGuardar.setBounds(78, 260, 89, 23);
		getContentPane().add(btnGuardar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jf.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(186, 260, 89, 23);
		getContentPane().add(btnCancelar);
		
		ResultSet rs = conec.ejecutarConsulta("SELECT DISTINCT * FROM tblClasificacion");
		ResultSet rs1 = conec.ejecutarConsulta("SELECT DISTINCT * FROM tblPais");
		ResultSet rs2 = conec.ejecutarConsulta("SELECT DISTINCT * FROM tblDirector");
		ResultSet rs3 = conec.ejecutarConsulta("SELECT DISTINCT * FROM tblEstudio");
		try {
			while (rs.next()) {
				cmbClasificacion.addItem(rs.getString("nombre"));
			}
			while (rs1.next()) {
				cmbNacionalidad.addItem(rs1.getString("nombre"));
			}
			while(rs2.next()) {
				cmbDirector.addItem(rs2.getString("primerNombre"));
			}
			while(rs3.next()) {
				cmbEstudio.addItem(rs3.getString("nombre"));
			}
			rs.close();
			rs1.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void iniciarComponentes(conexion conec, JFrame jf, String pantalla, int a) {
		lblSexo = new JLabel("Sexo:");
		lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSexo.setBounds(10, 211, 116, 14);
		getContentPane().add(lblSexo);
		
		lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCorreo.setBounds(10, 136, 116, 14);
		getContentPane().add(lblCorreo);
		
		lblDireccion = new JLabel("Direccion:");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(10, 161, 116, 14);
		getContentPane().add(lblDireccion);
		
		lblIdentidad = new JLabel("Identidad:");
		lblIdentidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdentidad.setBounds(10, 186, 116, 14);
		getContentPane().add(lblIdentidad);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(136, 133, 175, 20);
		getContentPane().add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(136, 158, 175, 20);
		getContentPane().add(txtDireccion);
		txtDireccion.setColumns(10);
		
		txtIdentidad = new JTextField();
		txtIdentidad.setBounds(136, 183, 175, 20);
		getContentPane().add(txtIdentidad);
		txtIdentidad.setColumns(10);
		
		cmbSexo = new JComboBox<Object>();
		cmbSexo.setBounds(136, 208, 175, 20);
		getContentPane().add(cmbSexo);
		
		ResultSet rs = conec.ejecutarConsulta("SELECT DISTINCT * FROM tblGenero");
		try {
			while (rs.next()) {
				cmbSexo.addItem(rs.getString("nombre"));
			}
			rs.close();
		} catch (SQLException e) {}
	}

	public void guardar(conexion conec, String pantalla, int a, JFrame jf) {
		String sql = "";
		int sexo = 0;
		if (a == 0) {
			if (pantalla.equals("Clientes")) {
				sexo = String.valueOf(cmbSexo.getSelectedItem()).equals("Masculino") ? 1:2;
				try {
					CallableStatement cstmt 
					= conec.obtenerConexion().prepareCall("{call dbo.spInsertarCliente(?,?,?,?,?,?,?,?,?,?,?)}");  
			        cstmt.setString(1, txtPrimerNombre.getText());
			        cstmt.setString(2, txtSegundoNombre.getText());
			        cstmt.setString(3, txtPrimerApellido.getText());
			        cstmt.setString(4, txtSegundoApellido.getText());
			        cstmt.setString(5, txtCorreo.getText());
			        cstmt.setString(6, txtDireccion.getText());
			        cstmt.setString(7, txtIdentidad.getText());
			        cstmt.setString(8, cmbNacionalidad.getSelectedItem().toString());
			        cstmt.setString(9, String.valueOf(sexo));
				    cstmt.registerOutParameter(10, java.sql.Types.NVARCHAR);
				    cstmt.registerOutParameter(11, java.sql.Types.INTEGER);
				    cstmt.execute();
				    JOptionPane.showMessageDialog(null, cstmt.getNString(10));
				    if (cstmt.getInt(11) == 1) {
					    this.setVisible(false);
					    jf.setVisible(true);
				    }
			    }catch (Exception e) { System.out.println(e.getMessage()); }  
			}else if (pantalla.equals("Empleados")) {
				sexo = String.valueOf(cmbSexo.getSelectedItem()).equals("Masculino") ? 1:2;
				try {
					CallableStatement cstmt 
					= conec.obtenerConexion().prepareCall("{call dbo.spInsertarEmpleado(?,?,?,?,?,?,?,?,?,?,?)}");  
			        cstmt.setString(1, txtPrimerNombre.getText());
			        cstmt.setString(2, txtSegundoNombre.getText());
			        cstmt.setString(3, txtPrimerApellido.getText());
			        cstmt.setString(4, txtSegundoApellido.getText());
			        cstmt.setString(5, txtCorreo.getText());
			        cstmt.setString(6, txtDireccion.getText());
			        cstmt.setString(7, txtIdentidad.getText());
			        cstmt.setString(8, cmbNacionalidad.getSelectedItem().toString());
			        cstmt.setString(9, String.valueOf(sexo));
				    cstmt.registerOutParameter(10, java.sql.Types.NVARCHAR);
				    cstmt.registerOutParameter(11, java.sql.Types.INTEGER);
				    cstmt.execute();
				    JOptionPane.showMessageDialog(null, cstmt.getNString(10));
				    if (cstmt.getInt(11) == 1) {
					    this.setVisible(false);
					    jf.setVisible(true);
				    }
			    }catch (Exception e) { System.out.println(e.getMessage()); }
			}else if (pantalla.equals("Directores")) {
				sql = "INSERT INTO tblDirector(primerNombre,segundoNombre,primerApellido"
						+ ",segundoApellido,nacionalidad)VALUES"
						+ "('"+txtPrimerNombre.getText()+"','"+txtSegundoNombre.getText()+"','"
						+ txtPrimerApellido.getText()+"','"+txtSegundoApellido.getText()+"',"
						+ "dbo.obtenerIdPais('"+String.valueOf(cmbNacionalidad.getSelectedItem())+"'))";
				conec.ejecutarConsulta(sql);
				return;
			}else if (pantalla.equals("Actores")) {
				sql = "INSERT INTO tblActor(primerNombre,segundoNombre,primerApellido"
						+ ",segundoApellido,nacionalidad)VALUES"
						+ "('"+txtPrimerNombre.getText()+"','"+txtSegundoNombre.getText()+"','"
						+ txtPrimerApellido.getText()+"','"+txtSegundoApellido.getText()+"',"
						+ "dbo.obtenerIdPais('"+String.valueOf(cmbNacionalidad.getSelectedItem())+"'))";
				conec.ejecutarConsulta(sql);
				return;
			}else if (pantalla.equals("Peliculas")) {
				try {
					CallableStatement cstmt 
					= conec.obtenerConexion().prepareCall("{call dbo.spGuardarPelicula(?,?,?,?,?,?,?,?,?,?,?)}");  
			        cstmt.setString(1, txtTitulo.getText());
			        cstmt.setString(2, txtSinopsis.getText());
			        cstmt.setString(3, txtDuracion.getText());
			        cstmt.setString(4, txtAnio.getText());
			        cstmt.setString(5, cmbEstudio.getSelectedItem().toString());
			        cstmt.setString(6, cmbNacionalidad.getSelectedItem().toString());
			        cstmt.setString(7, cmbClasificacion.getSelectedItem().toString());
			        cstmt.setString(8, cmbDirector.getSelectedItem().toString());
			        cstmt.setString(9, img);
				    cstmt.registerOutParameter(10, java.sql.Types.NVARCHAR);
				    cstmt.registerOutParameter(11, java.sql.Types.INTEGER);
				    cstmt.execute();
				    JOptionPane.showMessageDialog(null, cstmt.getNString(10));
				    if (cstmt.getInt(11) == 1) {
					    this.setVisible(false);
					    jf.setVisible(true);
				    }
			    }catch (Exception e) { System.out.println(e.getMessage()); }  
			}
		}else if (a == 1) {
			if (pantalla.equals("Directores")) {
				return;
			}else {
				sexo = String.valueOf(cmbSexo.getSelectedItem()).equals("Masculino") ? 1:2;
				sql = "UPDATE tblPersona SET primerNombre = '"+txtPrimerNombre.getText()+"', "
						+ "segundoNombre = '"+txtSegundoNombre.getText()+"', "
						+ "primerApellido = '"+txtPrimerApellido.getText()+"', "
						+ "segundoApellido = '"+txtSegundoApellido.getText()+"', "
						+ "sexo = "+sexo+", "
						+ "correo = '"+txtCorreo.getText()+"', "
						+ "direccion = '"+txtDireccion.getText()+"' , "
						+ "identidad = '"+txtIdentidad.getText()+"', "
						+ "nacionalidad = ("
						+ "SELECT dbo.obtenerIdPais('"+String.valueOf(cmbNacionalidad.getSelectedItem())+"'))"
						+ " WHERE id=(SELECT dbo.obtenerIdPersona('"+txtIdentidad.getText()+"'))";
				conec.ejecutarConsulta(sql);
				JOptionPane.showMessageDialog(null, "Datos Actualizados Correctamente!!!");
				this.setVisible(false);
				jf.setVisible(true);
			}
		}
	}
	
	public void cargarInformacion(conexion conec, String Cod, String pantalla) {
		ResultSet rs = null;
		if (pantalla.equals("Clientes")) {
			rs = conec.ejecutarConsulta("SELECT * FROM vwInformacionClientes WHERE codigo = '"+Cod+"'");
		}else if (pantalla.equals("Empleados")) {
			rs = conec.ejecutarConsulta("SELECT * FROM vwInformacionEmpleados WHERE codigo = '"+Cod+"'");
		}
		try {
			while (rs.next()) {
				String[] partes = rs.getString("nombreCompleto").split(" ");
				if (partes.length == 3) {
					txtPrimerNombre.setText(partes[0]);
					txtSegundoNombre.setText("");
					txtPrimerApellido.setText(partes[1]);
					txtSegundoApellido.setText(partes[2]);
				} else if (partes.length == 4) {
					txtPrimerNombre.setText(partes[0]);
					txtSegundoNombre.setText(partes[1]);
					txtPrimerApellido.setText(partes[2]);
					txtSegundoApellido.setText(partes[3]);
				}
				txtCorreo.setText(rs.getString("correo"));
				txtDireccion.setText(rs.getString("direccion"));
				txtIdentidad.setText(rs.getString("identidad"));
				cmbSexo.setSelectedItem(rs.getString("sexo"));
				cmbNacionalidad.setSelectedItem(rs.getString("nacionalidad"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String abrir(){
		int auxApertura = abrir.showOpenDialog(null);
		if (auxApertura == JFileChooser.CANCEL_OPTION) 
			return null;
		abrir.setCurrentDirectory(abrir.getSelectedFile());
		return abrir.getSelectedFile().toString();
	}
	
	
}
