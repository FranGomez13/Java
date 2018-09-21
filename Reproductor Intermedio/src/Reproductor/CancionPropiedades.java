package Reproductor;

public class CancionPropiedades {
	private String ruta;
	private String nombre;
	private int indice;
	private String aux;
	private int index;
	
	public CancionPropiedades(String a, String b, int c){
		aux = a;
        index = aux.lastIndexOf('.');
        aux = aux.substring(0, index);
        
        nombre = aux;
		ruta = b;
		indice = c;		
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
}
