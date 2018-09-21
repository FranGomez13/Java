package Ventanas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class principal implements ActionListener, Runnable {
	private JFrame menu;
	private JButton btnSalir;
	private ventBurbuja burbuja;
	private ventInsercion insercion;
	private ventSeleccion seleccion;
	private JButton btnBurbuja;
	private JButton btnInsercion;
	private JButton btnSeleccion;
	private boolean activo;
	private boolean pausar;
	private Thread hilo;
	private int n = 0;
	private int[]A;
	private int temp = 0;
	private Scanner sc = new Scanner( System.in );
	private int tipoOrdenamiento = 0;
	//1: Burbuja, 2:Insercion, 3: Seleccion
	
	public principal(){
		menu = new JFrame();
		btnSalir = new JButton("Salir");
		burbuja = new ventBurbuja();
		btnBurbuja = new JButton("Burbuja");
		insercion = new ventInsercion();
		btnInsercion = new JButton("Insercion");
		seleccion = new ventSeleccion();
		btnSeleccion = new JButton("Seleccion");
		propiedades_ventana();
	}
	
	public void propiedades_ventana(){
		menu.setTitle("Tipos De Ordenamiento");
		menu.setSize(350, 350);
		menu.setLayout(null);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
		menu.setResizable(false);
		menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		btnBurbuja.setBounds(125, 50, 100, 30);
		btnBurbuja.addActionListener(this);
		menu.add(btnBurbuja);
		btnInsercion.setBounds(125, 100, 100, 30);
		btnInsercion.addActionListener(this);
		menu.add(btnInsercion);
		btnSeleccion.setBounds(125, 150, 100, 30);
		btnSeleccion.addActionListener(this);
		menu.add(btnSeleccion);
		btnSalir.setBounds(125, 250, 100, 30);
		btnSalir.addActionListener(this);
		menu.add(btnSalir);
	}
			
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {}
		new principal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource().equals(btnSalir)){
			System.exit(0);
		}else if (e.getSource().equals(btnBurbuja)){	
		    System.out.println("Ingrese La Cantidad De Datos A Ordenar");
			n = sc.nextInt();
			A = new int [n];
	        for( int i = 0 ; i < n ; ++i ){
	        	System.out.println("Ingrese El Dato " + (i+1));
	            A[i] = sc.nextInt();
	        }
			burbuja.setVisible(true);
		    activo = true;
		    pausar = true;
		    tipoOrdenamiento = 1;
		    hilo = new Thread(this);
		    hilo.start();
		}else if (e.getSource().equals(btnInsercion)){
		    System.out.println("Ingrese La Cantidad De Datos A Ordenar");
			n = sc.nextInt();
			A = new int [n];
	        for( int i = 0 ; i < n ; ++i ){
	        	System.out.println("Ingrese El Dato " + (i+1));
	            A[i] = sc.nextInt();
	        }
			insercion.setVisible(true);
		    activo = true;
		    pausar = true;
		    tipoOrdenamiento = 2;
		    hilo = new Thread(this);
		    hilo.start();
		}else if (e.getSource().equals(btnSeleccion)){
		    System.out.println("Ingrese La Cantidad De Datos A Ordenar");
			n = sc.nextInt();
			A = new int [n];
	        for( int i = 0 ; i < n ; ++i ){
	        	System.out.println("Ingrese El Dato " + (i+1));
	            A[i] = sc.nextInt();
	        }
			seleccion.setVisible(true);
		    activo = true;
		    pausar = true;
		    tipoOrdenamiento = 3;
		    hilo = new Thread(this);
		    hilo.start();
		}
	}

	@Override
	public void run() {
	        try {
	            while(pausar){
	                while( activo ) {
	                	switch(tipoOrdenamiento){
	                		case 1:
	                			IniciarBurbuja();
	                			break;
	                		case 2:
	                			IniciarInsercion();
	                			break;
	                		case 3:
	                			IniciarSeleccion();
	                			break;
	                	}
	                		
	                }
	            }
	        }catch(Exception e){}
	}
		
	//ALGORITMO BURBUJA
	public void IniciarBurbuja(){
		try {
			for (int i = 0; i<A.length-2; i++){
				Thread.sleep(150);
				burbuja.lblLinea6.setForeground(Color.black);
				burbuja.lblLinea1.setForeground(Color.red);
				Thread.sleep(250);
				burbuja.lblLinea1.setForeground(Color.black);
				for (int j = A.length-1; j>i; j--){
					Thread.sleep(150);
					burbuja.lblLinea2.setForeground(Color.red);
					burbuja.lblLinea4.setForeground(Color.black);
					burbuja.lblLinea5.setForeground(Color.black);
					burbuja.slider.setValue(100);
					Thread.sleep(250);
					burbuja.lblcomp1.setText(String.valueOf(A[j]));
					burbuja.lblcomp2.setText(String.valueOf(A[j-1]));
					burbuja.lblLinea2.setForeground(Color.black);
					burbuja.lblLinea3.setForeground(Color.red);
					Thread.sleep(250); 
					if (A[j]<A[j-1]){
						burbuja.lblCheck.setText("SI");
						burbuja.lblLinea3.setForeground(Color.black);
						burbuja.lblLinea4.setForeground(Color.red);
						while(burbuja.slider.getValue() != 0){
							burbuja.slider.setValue(burbuja.slider.getValue()-5);
							if (burbuja.slider.getValue() == 50){
								burbuja.lblcomp1.setText(String.valueOf(A[j-1]));
								burbuja.lblcomp2.setText(String.valueOf(A[j]));
							}else if (burbuja.slider.getValue() == 15){
								burbuja.lblcomp1.setText(String.valueOf(A[j]));
								burbuja.lblcomp2.setText("");
							}
							Thread.sleep(100); 
						}
						burbuja.lblCheck.setText("");
						Intercambiar(j, j-1);
					}else{
						burbuja.lblCheck.setText("NO");
						burbuja.lblLinea3.setForeground(Color.black);
						while(burbuja.slider.getValue() != 0){
							burbuja.slider.setValue(burbuja.slider.getValue()-5);
							if (burbuja.slider.getValue() == 15){
								burbuja.lblcomp1.setText(String.valueOf(A[j-1]));
								burbuja.lblcomp2.setText("");
							}
							Thread.sleep(100); 
						}
						burbuja.lblCheck.setText("");
					}
					burbuja.lblLinea4.setForeground(Color.black);
					burbuja.lblLinea5.setForeground(Color.red);
				}
				burbuja.lblLinea5.setForeground(Color.black);
				burbuja.lblLinea6.setForeground(Color.red);
			}
			activo = false;
			burbuja.lblcomp1.setText("");
			burbuja.lblcomp2.setText("");
			System.out.println("Ordenamiento Terminado");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void Intercambiar(int x, int y){
		temp = A[x];
		A[x] = A[y];
		A[y] = temp;
	}

	//ALGORITMO POR INSERCION
	public void IniciarInsercion(){
		try {
			int p, z;
		    int aux;
		    for (p = 1; p < A.length; p++){
				Thread.sleep(150);
				insercion.lblLinea9.setForeground(Color.black);
				insercion.lblLinea1.setForeground(Color.red);
				insercion.slider.setValue(100);
	            aux = A[p];
	            insercion.lblCheck.setText("Aux: "+String.valueOf(aux));
				Thread.sleep(150);
				insercion.lblLinea1.setForeground(Color.black);
				insercion.lblLinea2.setForeground(Color.red);
	            z = p - 1;
				Thread.sleep(150);
				insercion.lblLinea2.setForeground(Color.black);
				insercion.lblLinea3.setForeground(Color.red);
	            while ((z >= 0) && (aux < A[z])){
					Thread.sleep(150);
					insercion.lblcomp1.setText(String.valueOf(A[z+1]));
					insercion.lblcomp2.setText(String.valueOf(A[z]));
					insercion.lblLinea3.setForeground(Color.black);
					insercion.lblLinea4.setForeground(Color.red);
	                           A[z + 1] = A[z];
		           				Thread.sleep(150);
		        				insercion.lblLinea4.setForeground(Color.black);
		        				insercion.lblLinea6.setForeground(Color.black);
		        				insercion.lblLinea5.setForeground(Color.red);
	                           while(insercion.slider.getValue() != 0){
	   							insercion.slider.setValue(insercion.slider.getValue()-5);
	   							if (insercion.slider.getValue() == 50){
	   								insercion.lblcomp1.setText(String.valueOf(A[z]));
	   								insercion.lblcomp2.setText("");
	   							}else if(insercion.slider.getValue() == 10){
	   								insercion.lblcomp1.setText("");
	   							}
	   							Thread.sleep(100); 
	   							}
	                           z--;
	                        insercion.slider.setValue(100);
	           				Thread.sleep(150);
	        				insercion.lblLinea5.setForeground(Color.black);
	        				insercion.lblLinea6.setForeground(Color.red);
	            }
				Thread.sleep(150);
				insercion.lblLinea6.setForeground(Color.black);
				insercion.lblLinea3.setForeground(Color.black);
				insercion.lblLinea7.setForeground(Color.red);
				insercion.lblcomp1.setText(String.valueOf(A[z+1]));
				insercion.lblcomp2.setText(String.valueOf(aux));
				insercion.lblCheck.setText("");
				Thread.sleep(150);
				insercion.lblLinea7.setForeground(Color.black);
				insercion.lblLinea8.setForeground(Color.red);
	            A[z + 1] = aux;
                while(insercion.slider.getValue() != 0){
					insercion.slider.setValue(insercion.slider.getValue()-5);
					if (insercion.slider.getValue() == 50){
						insercion.lblcomp1.setText(String.valueOf(aux));
						insercion.lblcomp2.setText("");
					}else if(insercion.slider.getValue() == 10){
						insercion.lblcomp1.setText("");
					}
					Thread.sleep(100); 
					}
				Thread.sleep(150);
				insercion.lblLinea8.setForeground(Color.black);
				insercion.lblLinea9.setForeground(Color.red);
		    }
		    Thread.sleep(150);
		    insercion.lblLinea9.setForeground(Color.black);
			activo = false;
			System.out.println("Ordenamiento Terminado");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//ALGORITMO POR SELECCION
	public void IniciarSeleccion(){
		try {
			int min;
		    int aux = 0;
		    for (int i = 0; i < A.length - 1; i++){
				Thread.sleep(150);
				seleccion.slider.setValue(100);
				seleccion.lblLinea9.setForeground(Color.black);
				seleccion.lblLinea1.setForeground(Color.red);
	            min = i;
				Thread.sleep(150);
				seleccion.lblLinea1.setForeground(Color.black);
				seleccion.lblLinea2.setForeground(Color.red);
				Thread.sleep(150);
				seleccion.lblLinea2.setForeground(Color.black);
				seleccion.lblLinea3.setForeground(Color.red);
				 for (int j = i + 1; j < A.length; j++){
						Thread.sleep(150);
						seleccion.lblLinea3.setForeground(Color.black);
						seleccion.lblLinea4.setForeground(Color.red);
        				seleccion.lblcomp1.setText("");
						seleccion.lblcomp2.setText("");
           				Thread.sleep(150);
        				seleccion.lblLinea4.setForeground(Color.black);
        				seleccion.lblLinea6.setForeground(Color.black);
        				seleccion.lblLinea5.setForeground(Color.red);
						if (A[j] < A[min]){
							Thread.sleep(150);
	        				seleccion.lblLinea6.setForeground(Color.red);
	        				seleccion.lblLinea5.setForeground(Color.black);
							min = j;
					    }
						Thread.sleep(150);
        				seleccion.lblLinea5.setForeground(Color.black);
        				seleccion.lblLinea6.setForeground(Color.black);
				 }
         			  if (i != min){
  						Thread.sleep(150);
        				seleccion.lblLinea7.setForeground(Color.black);
        				seleccion.lblLinea8.setForeground(Color.red);
        				seleccion.lblcomp1.setText(String.valueOf(A[min]));
						seleccion.lblcomp2.setText(String.valueOf(A[i]));
                        while(seleccion.slider.getValue() != 0){
	   						seleccion.slider.setValue(seleccion.slider.getValue()-5);
	   						if (seleccion.slider.getValue() == 50){
	   							seleccion.lblcomp1.setText(String.valueOf(A[i]));
	   							seleccion.lblcomp2.setText(String.valueOf(A[min]));
	   							}else if(seleccion.slider.getValue() == 10){
	   								seleccion.lblcomp1.setText(String.valueOf(A[min]));
		   							seleccion.lblcomp2.setText("");
	   							}
	   							Thread.sleep(100); 
	   							}
         				aux= A[i];
         				A[i] = A[min];
         				A[min] = aux;
         				   }
         			 seleccion.lblLinea8.setForeground(Color.black);
         				  }
			Thread.sleep(150);
			seleccion.lblLinea8.setForeground(Color.black);
			seleccion.lblLinea9.setForeground(Color.red);
		    Thread.sleep(150);
		    seleccion.lblLinea9.setForeground(Color.black);
			activo = false;
			seleccion.lblcomp1.setText("");
			seleccion.lblcomp2.setText("");
			System.out.println("Ordenamiento Terminado");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}}

}
