package Libreria_TDAs;

public class TDA_Pila {
	TDA_Lista Pila = new TDA_Lista();

	public void Mete(Object x){
		Pila.Inserta(x, Pila.Fin());
	}
	
	public Object Tope(){
		if (0 < Pila.Anterior(Pila.Fin())){ //Verifica Que La Pila No Este Vacia
			return Pila.Recupera(Pila.Anterior(Pila.Fin()));
		}else{
			return "La Pila Esta Vacia";
		}
	}
	
	public void Saca(){
		Pila.Suprime(Pila.Anterior(Pila.Fin()));
	}
	
	public void Anula(){
		Pila.Anula();
	}
	
	public boolean Vacia(){
		if (Pila.Primero() == Pila.Fin())
			return true;
		else
			return false;
	}
}