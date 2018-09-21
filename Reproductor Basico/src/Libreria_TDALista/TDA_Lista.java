package Libreria_TDALista;

import javax.swing.JOptionPane;

public class TDA_Lista {
	private Container Contenedor = new Container();
	
	public void Inserta(Object x, int p){
		if ((p-1)<(Contenedor.Final()+1))// verifica que la posicion sea valida 
			Contenedor.AgregarDato(x, (p-1));
		else
			JOptionPane.showMessageDialog(null, "Ingrese Una Posicion Valida", "¡ERROR!", 0);
	}
	
	public int Fin(){
		return (Contenedor.Final()+1);
	}
	
	public int Localiza(Object x){
		return Contenedor.BuscarDato(x);
	}
	
	public Object Recupera(int p){
		if ((p-1)<=Contenedor.Final())// verifica que la posicion sea valida 
			return Contenedor.RecuperarDato(p-1);
		else
			JOptionPane.showMessageDialog(null, "Ingrese Una Posicion Valida", "¡ERROR!", 0);
		return null;
	}
	
	public void Suprime(int p){
		if ((p-1)<(Contenedor.Final()))// verifica que la posicion sea valida 
			Contenedor.EliminarDato(p-1);
		else
			JOptionPane.showMessageDialog(null, "Ingrese Una Posicion Valida", "¡ERROR!", 0);
	}
	
	public void Anula(){
		Contenedor.VaciarDatos();
	}
	
	public int Siguiente(int p){
		if ((p-1)<(Contenedor.Final()))// verifica que la posicion sea valida 
			return (p+1);
		else
			JOptionPane.showMessageDialog(null, "Ingrese Una Posicion Valida", "¡ERROR!", 0);
		return (Contenedor.Final()+1);
	}
	
	public int Anterior(int p){
		if ((p-1)<=Contenedor.Final())// verifica que la posicion sea valida 
			return (p-1);
		else
			JOptionPane.showMessageDialog(null, "Ingrese Una Posicion Valida", "¡ERROR!", 0);
		return (Contenedor.Final()+1);
	}
	
	public int Primero(){
		return 1;
	}
}
