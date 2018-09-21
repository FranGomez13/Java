package Algoritmos;

public class Burbuja{
	private int[] A = {8,3,0,2,4,9,5,6,7,1};
	private int temp = 0;

	public void Iniciar(){
		for (int i = 0; i<A.length-2; i++){
			for (int j = A.length-1; j>i; j--){
				if (A[j]<A[j-1])
					Intercambiar(j, j-1);
			}
		}
	}
	
	public void Intercambiar(int x, int y){
		temp = A[x];
		A[x] = A[y];
		A[y] = temp;
	}
	
}
