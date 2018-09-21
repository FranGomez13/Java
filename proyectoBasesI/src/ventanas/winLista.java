package ventanas;

import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.conexion;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class winLista extends JFrame {
	private JTable table;
	private JButton btnVolver;
	private JButton btnEditar;
	private JButton btnAgregar;
	private winInformacion informacion;
	private winPeliCartelera peliCartelera;
	public winLista(JFrame jf,conexion conec, String pantalla) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrar();
			}
		});
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(287, 256, 89, 23);
		getContentPane().add(btnAgregar);
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(485, 257, 89, 23);
		getContentPane().add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(true);
				dispose();
			}
		});
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(386, 257, 89, 23);
		getContentPane().add(btnEditar);
		setSize(600,330);
		setLocationRelativeTo(null);
		setTitle("Lista "+pantalla);
		getContentPane().setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 584, 245);
		getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		cargarTabla(conec, pantalla);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informacion = new winInformacion(winLista.this, conec, 
						(String)table.getValueAt(table.getSelectedRow(), 0), pantalla, 1);
				informacion.setVisible(true);
				setVisible(false);
				/*int row = table.getSelectedRow();
				for (int i = 0; i < table.getColumnCount(); i++) {
					System.out.println(table.getValueAt(row, i));
				}*/
			}
		});
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (pantalla.equals("Carteleras")) {
					peliCartelera = new winPeliCartelera(winLista.this, conec, 0);
					peliCartelera.setVisible(true);
					setVisible(false);
				}else {
					informacion = new winInformacion(winLista.this, conec, pantalla, 0);
					informacion.setVisible(true);
					setVisible(false);
				}
			}
		});
	}
	
	public void cerrar() {
		if(JOptionPane.showConfirmDialog(null, "¿Desea Salir?", "Salir",2) == 0)
			System.exit(0);
	}
	
	public void cargarTabla(conexion conec, String pantalla) {
		DefaultTableModel modelo = new DefaultTableModel();
		if (pantalla.equals("Clientes")) {
			ResultSet rs = conec.ejecutarConsulta("SELECT * FROM vwInformacionClientes");
			modelo.setColumnIdentifiers(new Object[] {"cod","nombre Completo"
					,"sexo","identidad",
					"FechaRegistro","Membresia"});
			try {
				while(rs.next()) {
					ResultSet rs1 = conec.ejecutarConsulta("SELECT dbo.obtenerMembresia('"+rs.getString("codigo")+"') "
							+ "Membresia");
					while(rs1.next()) {
						modelo.addRow(new Object[] {rs.getString("codigo"),rs.getString("nombreCompleto"),
								rs.getString("sexo"),rs.getString("identidad"),
								rs.getString("fechaRegistro"),rs1.getString("Membresia")}
						);
					}
				}
				
			} catch (Exception e) {}
		}else if (pantalla.equals("Empleados")) {
			ResultSet rs = conec.ejecutarConsulta("SELECT * FROM vwInformacionEmpleados");
			modelo.setColumnIdentifiers(new Object[] {"cod","nombre Completo"
					,"sexo","identidad",
					"FechaContratacion","Puesto"});
			try {
				while(rs.next()) {
					ResultSet rs1 = conec.ejecutarConsulta("SELECT dbo.obtenerPuesto('"+rs.getString("codigo")+"') "
							+ "Puesto");
					while(rs1.next()) {
						modelo.addRow(new Object[] {rs.getString("codigo"),rs.getString("nombreCompleto"),
								rs.getString("sexo"),rs.getString("identidad"),
								rs.getString("fechaContrato"),rs1.getString("Puesto")}
						);
					}
				}
				
			} catch (Exception e) {}
		}else if (pantalla.equals("Directores")) {
			ResultSet rs = conec.ejecutarConsulta("SELECT * FROM tblDirector");
			modelo.setColumnIdentifiers(new Object[] {"primerNombre","segundoNombre","primerApellido",
					"segundoApellido"});
			try {
				while(rs.next()) {
					modelo.addRow(new Object[] {rs.getString("primerNombre"),rs.getString("segundoNombre"),
							rs.getString("primerApellido"),rs.getString("segundoApellido")}
					);
				}
			} catch (Exception e) {}
		}else if (pantalla.equals("Actores")) {
			ResultSet rs = conec.ejecutarConsulta("SELECT * FROM tblActor");
			modelo.setColumnIdentifiers(new Object[] {"primerNombre","segundoNombre","primerApellido",
					"segundoApellido"});
			try {
				while(rs.next()) {
					modelo.addRow(new Object[] {rs.getString("primerNombre"),rs.getString("segundoNombre"),
							rs.getString("primerApellido"),rs.getString("segundoApellido")}
					);
				}
			} catch (Exception e) {}
		}else if (pantalla.equals("Carteleras")) {
			ResultSet rs = conec.ejecutarConsulta("SELECT * FROM vwCarteleraInfo");
			modelo.setColumnIdentifiers(new Object[] {"Cartelera","Pelicula","Fecha Inicio",
					"Fecha Fin"});
			try {
				while(rs.next()) {
					modelo.addRow(new Object[] {rs.getString("nombre"),rs.getString("titulo"),
							rs.getString("fechaInicio"),rs.getString("fechaFin")}
					);
				}	
			} catch (Exception e) {}
		}else if (pantalla.equals("Peliculas")) {
			ResultSet rs = conec.ejecutarConsulta("SELECT * FROM vwInformacionPelicula");
			modelo.setColumnIdentifiers(new Object[] {"Titulo","Año","Duracion",
					"Director"});
			try {
				while(rs.next()) {
					modelo.addRow(new Object[] {rs.getString("titulo"),rs.getString("anio"),
							rs.getString("duracion"),rs.getString("director")}
					);
				}
			} catch (Exception e) {}
		}
		table.setModel(modelo);
	}
}
