package Libreria_TDAs;

public class Container {
	private Object[] container = new Object [10000];
	private int Final_Lista = -1;
	private int i;
	
	public void AgregarDato(Object x,int p){
		if (container[p] == null){//verifica que la casilla del arreglo no contenga ningun dato
			container[p] = x;
			Final_Lista++;
		}else{
			for (i = (Final_Lista+1); i>p; i--){
				container[i] = container [i-1];
			}
			container[p] = x;
			Final_Lista++;
		}
	}
	
	public void EliminarDato(int p){
		container[p] = null; // vacia la casilla del arreglo
		for (i = p; i<=Final_Lista;i++){
			container [i] = container[i+1];
		}
		Final_Lista--;
	}
	
	public int Final(){
		return Final_Lista+1;
	}
	
	public int BuscarDato(Object x){
		int p = (Final_Lista+1);
		for (i = 0; i <= Final_Lista; i++){
			if (container[i] == x){
				p = i;
				i = Final_Lista;
			}
		}
		return p+1;
	}
	
	public Object RecuperarDato(int p){
		return container[p];
	}
	
	public void VaciarDatos(){
		for (i = 0; i <= Final_Lista; i++){
			container[i] = null;
		}
		Final_Lista = -1;
	}
}
