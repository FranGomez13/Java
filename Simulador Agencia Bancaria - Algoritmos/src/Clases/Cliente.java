package Clases;

import java.util.Random;

public class Cliente {
	private char tipo_Transaccion; // 1: Deposito (d), 2: Retiro (r), 3: Cambio (c) 
	private int billetera;
	private int monto_Transaccion;
	private int aux;
	private Random aleatorio = new Random();
	// Necesidad Por Denominacio
	private int denominacion1;
	private int denominacion2;
	private int denominacion5;
	private int denominacion10;
	private int denominacion20;
	private int denominacion50;
	private int denominacion100;
	private int denominacion500;
	private int aux1;
	
	public Cliente(){
		monto_Transaccion = (int)(aleatorio.nextDouble() * 50000 + 500); //# Aleatorio [500,50000]
		Transaccion();
		NecesidadDenominacion();
	}
	
	public char Transaccion(){
		aux = (int)(aleatorio.nextDouble() * 3 + 1); //# Aleatorio [1,3]
		if (aux == 1)
			tipo_Transaccion = 'd';
		else if (aux == 2)
			tipo_Transaccion = 'r';
		else
			tipo_Transaccion = 'c';
		
		return tipo_Transaccion;
	}
	
	public int Monto(){
		return monto_Transaccion;
	}
	
	public void Meter_Billetera(int cant){
		billetera += cant;
	}
	
	public void Sacar_Billetera(int cant){
		billetera -= cant;
	}
	
	public int Monto_Billetera(){
		return billetera;
	}
	
	public void NecesidadDenominacion(){
		aux1 = monto_Transaccion;
		while(aux1 > 0){
			aux = (int)(aleatorio.nextDouble() * 8 + 1);
			if (aux == 1){
				denominacion1 += 1;
				aux1 -= 1;
			}else if ((aux == 2) && (aux1 > 2)){
					aux1 -= 2;
					denominacion2 += 1;
			}else if ((aux == 3) && (aux1 > 5)){
					aux1 -= 5;
					denominacion5 += 1;
			}else if ((aux == 4) && (aux1 > 10)){
					aux1 -= 10;
					denominacion10 += 1;
			}else if ((aux == 5) && (aux1 > 20)){
					aux1 -= 20;
					denominacion20 += 1;
			}else if ((aux == 6) && (aux1 > 50)){
					aux1 -= 50;
					denominacion50 += 1;
			}else if ((aux == 7) && (aux1 > 100)){
					aux1 -= 100;
					denominacion100 += 1;
			}else if ((aux == 8) && (aux1 > 500)){
					aux1 -= 500;
					denominacion500 += 1;
			}else{}
		}
	}
	
	public String toString(){
		return	String.valueOf(tipo_Transaccion)+","+
				String.valueOf(denominacion1)+","+String.valueOf(denominacion2)+","
				+String.valueOf(denominacion5)+","+String.valueOf(denominacion10)+","
				+String.valueOf(denominacion20)+","+String.valueOf(denominacion50)+","
				+String.valueOf(denominacion100)+","+String.valueOf(denominacion500)+","
				+String.valueOf(monto_Transaccion);
	}
}