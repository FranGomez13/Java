package Clases;

import java.util.Random;

import Libreria_TDAs.TDA_Cola;
import Libreria_TDAs.TDA_Pila;

public class Caja {
	private int dinero;
	private int clientesCola;
	private int billetes1;
	private int billetes2;
	private int billetes5;
	private int billetes10;
	private int billetes20;
	private int billetes50;
	private int billetes100;
	private int billetes500;
	private int aux;
	private Random billetes;
	private TDA_Cola cola;
	private TDA_Pila pila1;
	private TDA_Pila pila2;
	private TDA_Pila pila5;
	private TDA_Pila pila10;
	private TDA_Pila pila20;
	private TDA_Pila pila50;
	private TDA_Pila pila100;
	private TDA_Pila pila500;
	
	public Caja(){
		dinero = 1000000;
		billetes = new Random();
		cola = new TDA_Cola();
		pila1 = new TDA_Pila();
		pila2 = new TDA_Pila();
		pila5 = new TDA_Pila();
		pila10 = new TDA_Pila();
		pila20 = new TDA_Pila();
		pila50 = new TDA_Pila();
		pila100 = new TDA_Pila();
		pila500 = new TDA_Pila();
		IniciarBilletes();
	}
	
	public int DineroEnCaja(){
		return dinero;
	}

	public Object ClientesCola(){
		return cola.Frente();
	}
	
	public boolean ColaVacia(){
		return cola.Vacia();
	}
	
	public void AgregarClientes(Object x){
		cola.Pone_En_Cola(x);
		clientesCola += 1;
	}

	public void QuitarClientes(){
		cola.Quita_De_Cola();
		clientesCola -= 1;
	}
	
	public void IniciarBilletes(){
		/* tipos de billetes
		 * 1 -> 1 lempira
		 * 2 -> 2 lempiras
		 * 3 -> 5 lempiras
		 * 4 -> 10 lempiras
		 * 5 -> 20 lempiras
		 * 6 -> 50 lempiras
		 * 7 -> 100 lempiras
		 * 8 -> 500 lempiras
		 */
		while(dinero > 0){
			aux = (int)(billetes.nextDouble() * 8 + 1);
			if (aux == 1){
					pila1.Mete("*");
					billetes1 += 1;
					dinero -= 1;
			}else if ((aux == 2) && (dinero > 2)){
					pila2.Mete("*");
					billetes2 += 1;
					dinero -= 2;
			}else if ((aux == 3) && (dinero > 5)){
					pila5.Mete("*");
					billetes5 += 1;
					dinero -= 5;
			}else if ((aux == 4) && (dinero > 10)){
					pila10.Mete("*");
					billetes10 += 1;
					dinero -= 10;
			}else if ((aux == 5) && (dinero > 20)){
					pila20.Mete("*");
					billetes20 += 1;
					dinero -= 20;
			}else if ((aux == 6) && (dinero > 50)){
					pila50.Mete("*");
					billetes50 += 1;
					dinero -= 50;
			}else if ((aux == 7) && (dinero > 100)){
					pila100.Mete("*");
					billetes100 += 1;
					dinero -= 100;
			}else if ((aux == 8) && (dinero > 500)){
					pila500.Mete("*");
					billetes500 += 1;
					dinero -= 500;
			}else{}
		}
		dinero = 1000000;
	}
	
	public void MeterBilletes(int tipo){
		/* tipos de billetes
		 * 1 -> 1 lempira
		 * 2 -> 2 lempiras
		 * 3 -> 5 lempiras
		 * 4 -> 10 lempiras
		 * 5 -> 20 lempiras
		 * 6 -> 50 lempiras
		 * 7 -> 100 lempiras
		 * 8 -> 500 lempiras
		 */
		if (tipo == 1){
			pila1.Mete("*");
			billetes1 += 1;
			dinero +=1;
		}else if (tipo == 2){
			pila2.Mete("*");
			billetes2 += 1;
			dinero +=2;
		}else if (tipo == 3){
			pila5.Mete("*");
			billetes5 += 1;
			dinero +=5;
		}else if (tipo == 4){
			pila10.Mete("*");
			billetes10 += 1;
			dinero +=10;
		}else if (tipo == 5){
			pila20.Mete("*");
			billetes20 += 1;
			dinero +=20;
		}else if (tipo == 6){
			pila50.Mete("*");
			billetes50 += 1;
			dinero +=50;
		}else if (tipo == 7){
			pila100.Mete("*");
			billetes100 += 1;
			dinero +=100;
		}else{
			pila500.Mete("*");
			billetes500 += 1;
			dinero +=500;
		}
	}
	
	public void SacarBilletes(int tipo){
		/* tipos de billetes
		 * 1 -> 1 lempira
		 * 2 -> 2 lempiras
		 * 3 -> 5 lempiras
		 * 4 -> 10 lempiras
		 * 5 -> 20 lempiras
		 * 6 -> 50 lempiras
		 * 7 -> 100 lempiras
		 * 8 -> 500 lempiras
		 */
		if (tipo == 1){
			pila1.Saca();
			billetes1 -= 1;
			dinero -=1;
		}else if (tipo == 2){
			pila2.Saca();
			billetes2 -= 1;
			dinero -=2;
		}else if (tipo == 3){
			pila5.Saca();
			billetes5 -= 1;
			dinero -=5;
		}else if (tipo == 4){
			pila10.Saca();
			billetes10 -= 1;
			dinero -=10;
		}else if (tipo == 5){
			pila20.Saca();
			billetes20 -= 1;
			dinero -=20;
		}else if (tipo == 6){
			pila50.Saca();
			billetes50 -= 1;
			dinero -=50;
		}else if (tipo == 7){
			pila100.Saca();
			billetes100 -= 1;
			dinero -=100;
		}else{ 
			pila500.Saca();
			billetes500 -= 1;
			dinero -=500;
		}
	}
	
	public int ClientesEnCola(){
		return clientesCola;
	}
	
	public int CantBilletes1(){
		return billetes1;
	}
	
	public int CantBilletes2(){
		return billetes2;
	}
	
	public int CantBilletes5(){
		return billetes5;
	}
	
	public int CantBilletes10(){
		return billetes10;
	}
	
	public int CantBilletes20(){
		return billetes20;
	}
	
	public int CantBilletes50(){
		return billetes50;
	}
	
	public int CantBilletes100(){
		return billetes100;
	}
	
	public int CantBilletes500(){
		return billetes500;
	}
}
