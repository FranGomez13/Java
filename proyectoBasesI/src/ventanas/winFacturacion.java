package ventanas;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import clases.conexion;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class winFacturacion extends JFrame {
	private JTextField txtIdentidad;
	private JTextField txtCantidad;
	private JLabel lblCantidad;
	private JButton btnFacturar;
	private JLabel lblIdentidadCliente;
	private JButton btnVolver;
	private JLabel lblProducto;
	private JComboBox<Object> cmbProductos;
	private JList<Object> lstProductos;
	private JScrollPane scrollPane;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private DefaultListModel<Object> modelo;
	private JTextField txtEmpleado;
	private JLabel lblEmp;
	private winInformacion info;
	
	public winFacturacion(JFrame jf,conexion conec) {
		modelo = new DefaultListModel<Object>();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Facturacion");
		getContentPane().setLayout(null);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jf.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(225, 403, 89, 23);
		getContentPane().add(btnVolver);
		
		lblIdentidadCliente = new JLabel("Identidad Cliente");
		lblIdentidadCliente.setBounds(10, 11, 100, 14);
		getContentPane().add(lblIdentidadCliente);
		
		txtIdentidad = new JTextField();
		txtIdentidad.setBounds(120, 8, 179, 20);
		getContentPane().add(txtIdentidad);
		txtIdentidad.setColumns(10);
		
		btnFacturar = new JButton("Facturar");
		btnFacturar.setBounds(10, 403, 89, 23);
		getContentPane().add(btnFacturar);
		
		btnFacturar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				facturar(jf, conec);	
			}
		});
		
		lblCantidad = new JLabel("Cantidad: ");
		lblCantidad.setBounds(48, 68, 56, 14);
		getContentPane().add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(120, 65, 89, 20);
		getContentPane().add(txtCantidad);
		txtCantidad.setColumns(10);
		
		cmbProductos = new JComboBox<Object>();
		cmbProductos.setBounds(120, 96, 183, 20);
		getContentPane().add(cmbProductos);
		
		lblProducto = new JLabel("Producto: ");
		lblProducto.setBounds(48, 99, 62, 14);
		getContentPane().add(lblProducto);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 161, 304, 231);
		getContentPane().add(scrollPane);
		
		lstProductos = new JList<Object>();
		scrollPane.setViewportView(lstProductos);
		lstProductos.setModel(modelo);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(126, 127, 89, 23);
		getContentPane().add(btnAgregar);
		btnAgregar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modelo.addElement(txtCantidad.getText()+";"+cmbProductos.getSelectedItem());
				txtCantidad.setText("");
			}
		});
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(225, 127, 89, 23);
		getContentPane().add(btnEliminar);
		
		lblEmp = new JLabel("Identidad Empleado");
		lblEmp.setBounds(10, 39, 100, 14);
		getContentPane().add(lblEmp);
		
		txtEmpleado = new JTextField();
		txtEmpleado.setColumns(10);
		txtEmpleado.setBounds(120, 36, 179, 20);
		getContentPane().add(txtEmpleado);
		btnEliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modelo.remove(lstProductos.getSelectedIndex());
			}
		});
		setSize(340, 476);
		this.setLocationRelativeTo(null);
		
		cargarComboBox(conec);
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
	
	@SuppressWarnings("static-access")
	public void facturar(JFrame jf, conexion conec) {
		try {
			if (modelo.size() == 0) {
				new JOptionPane().showMessageDialog(null, "Favor Ingrese Al menos Producto");
				return;
			}
			CallableStatement cstmt 
			= conec.obtenerConexion().prepareCall("{call dbo.spGuardarFactura(?,?,?,?,?)}");  
	        cstmt.setString(1, txtIdentidad.getText());
	        cstmt.setString(2, txtEmpleado.getText());
		    cstmt.registerOutParameter(3, java.sql.Types.NVARCHAR);
		    cstmt.registerOutParameter(4, java.sql.Types.INTEGER);
		    cstmt.registerOutParameter(5, java.sql.Types.INTEGER);
		    cstmt.execute();
		    if (cstmt.getInt(4) == -1) {
		    	new JOptionPane().showMessageDialog(null, cstmt.getString(3));
		    	info = new winInformacion(winFacturacion.this, conec, "Clientes", 0);
		    	info.setVisible(true);
		    }else if (cstmt.getInt(4) == 1) {
			    String[] aux;
				int id = cstmt.getInt(5);
				CallableStatement cstmt1 
				= conec.obtenerConexion().prepareCall("{call dbo.spGuardarDetallesFactura(?,?,?,?,?)}");
			    for (int i = 0; i < modelo.getSize(); i++) {
					aux = String.valueOf(modelo.getElementAt(i)).split(";");
					cstmt1 
					= conec.obtenerConexion().prepareCall("{call dbo.spGuardarDetallesFactura(?,?,?,?,?)}");
					cstmt1.setString(1, aux[1]);
			        cstmt1.setInt(2, Integer.valueOf(aux[0]));
			        cstmt1.setInt(3, id);
				    cstmt1.registerOutParameter(4, java.sql.Types.NVARCHAR);
				    cstmt1.registerOutParameter(5, java.sql.Types.INTEGER);
				    cstmt1.execute();
				}
				ResultSet rs1 = conec.ejecutarConsulta("SELECT SUM(subTotal) Total FROM vwInformacionFacturas WHERE id="+id);
				while(rs1.next()) {
					new JOptionPane().showMessageDialog(null, "El Total De La Factura es: "+ rs1.getString("Total"));
				}
				modelo.clear();
				txtIdentidad.setText("");
				txtEmpleado.setText("");
		    }
	    }catch (Exception e) { System.out.println(e.getMessage()); }
	}
	
	public void cargarComboBox(conexion conec) {
		ResultSet rs = conec.ejecutarConsulta("SELECT DISTINCT * FROM tblProducto");
		try {
			while (rs.next()) {
				cmbProductos.addItem(rs.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
