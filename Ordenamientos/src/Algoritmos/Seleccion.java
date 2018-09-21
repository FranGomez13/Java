package Algoritmos;

public class Seleccion {
	private int[] A = {8,3,0,2,4,9,5,6,7,1};
	
	public void iniciar(){
	  for (int i = 0; i < A.length - 1; i++){
		  int min = i;
		  for (int j = i + 1; j < A.length; j++){
			  	if (A[j] < A[min]){
			  	min = j;
			  	}
		  }
		  if (i != min){
			  int aux= A[i];
			  A[i] = A[min];
			  A[min] = aux;
		  }
	  	}
	}
}
