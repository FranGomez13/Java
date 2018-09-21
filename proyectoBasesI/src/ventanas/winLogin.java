package ventanas;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import clases.conexion;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class winLogin extends JFrame {
	private JPasswordField txtPass;
	private JTextField txtUser;
	private conexion conec;
	private winMenu menu;
	public winLogin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrar();
			}
		});
		conec = new conexion();
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(90, 42, 114, 20);
		getContentPane().add(txtPass);
		
		txtUser = new JTextField();
		txtUser.setBounds(90, 11, 114, 20);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 14, 60, 14);
		getContentPane().add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 45, 70, 14);
		getContentPane().add(lblPassword);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verificarLogin();
			}
		});
		btnIngresar.setBounds(58, 99, 89, 23);
		getContentPane().add(btnIngresar);
		this.setSize(230, 172);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void cerrar() {
		if(JOptionPane.showConfirmDialog(null, "¿Desea Salir?", "Salir",2) == 0)
			System.exit(0);
	}
	
	public void verificarLogin() {
		char passArray[] = txtPass.getPassword();
        for (int i = 0; i < passArray.length; i++) {
        	char c = passArray[i];
            if (!Character.isLetterOrDigit(c)) { 
            	System.out.println("La contraseña contiene caracteres invalidos!");
            	return;
            }
        }
        String pass = new String(passArray);
        if (txtUser.getText().equals(pass) && !txtUser.getText().equals("")) {
            ResultSet rs = conec.ejecutarConsulta("SELECT * FROM vwInformacionEmpleados WHERE "
            		+ "identidad = '"+pass+"'");
            if (rs != null) {
            	try {
					while(rs.next()) {
						System.out.println(rs.getString("codigo"));
			        	menu = new winMenu(this,conec);
			        	menu.setVisible(true);
			        	this.setVisible(false);
			        	txtUser.setText("");
			        	txtPass.setText("");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        }else {
        	System.out.println(":c");
        }
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {}
		new winLogin();
	}
}
