package Libreria_TDAs;

public class TDA_Cola {
	TDA_Lista Cola = new TDA_Lista();
	
	public void Anula(){
		Cola.Anula();
	}
	
	public Object Frente(){
		if (Cola.Primero() == Cola.Fin())
			return "Cola Vacia";
		else
			return Cola.Recupera(Cola.Primero());
	}
	
	public void Pone_En_Cola(Object x){
		Cola.Inserta(x, Cola.Fin());
	}
	
	public void Quita_De_Cola(){
		Cola.Suprime(Cola.Primero());
	}
	
	public boolean Vacia(){
		if (Cola.Primero() == Cola.Fin())
			return true;
		else
			return false;
	}
}