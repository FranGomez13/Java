package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexion {
	private Connection conn;
	private final String connectionUrl = "jdbc:sqlserver://;database=dbCine;integratedSecurity=true;";
	public conexion() {
		try {
			  conn = DriverManager.getConnection(connectionUrl);
			  System.out.println("Conectado.");
		} catch (SQLException ex){
			  System.out.println("Error.");
		}
	}
	
	public ResultSet ejecutarConsulta(String query) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Connection obtenerConexion() {
		return conn;
	}
	
}
