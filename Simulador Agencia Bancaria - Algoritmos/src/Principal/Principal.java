package Principal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import Clases.Caja;
import Clases.Cliente;
import Clases.Ventana;

public class Principal implements Runnable{
	private Ventana interfaz;
	private JButton btnFunciones;
	private Thread cronometro;
    private boolean activo;
    private boolean pausar;
    private int estado = 0;
    private Random generador;
    private int c;
    private int b;
    private int lleno;
    private Caja caja1 = new Caja();
    private String clienteEnCaja1[];
    private String auxCaja1[] = {"0","0","0","0","0","0","0","0","0"};
    private int auxc1;
    private int auxc11;
    private boolean boolC1 = false;
    private int c1;
    private int a1;
    private Caja caja2 = new Caja();
    private String clienteEnCaja2[];
    private String auxCaja2[] = {"0","0","0","0","0","0","0","0","0"};
    private int auxc2;
    private int auxc21;
    private boolean boolC2 = false;
    private int c2;
    private int a2;
    private Caja caja3 = new Caja();
    private String clienteEnCaja3[];
    private String auxCaja3[] = {"0","0","0","0","0","0","0","0","0"};
    private int auxc3;
    private int auxc31;
    private boolean boolC3 = false;
    private int c3; 
    private int a3;
    private Caja caja4 = new Caja();
    private String clienteEnCaja4[];
    private String auxCaja4[] = {"0","0","0","0","0","0","0","0","0"};
    private int auxc4;
    private int auxc41;
    private boolean boolC4 = false;
    private int c4; 
    private int a4;
    private Caja caja5 = new Caja();
    private String clienteEnCaja5[];
    private String auxCaja5[] = {"0","0","0","0","0","0","0","0","0"};
    private int auxc5;
    private int auxc51;
    private boolean boolC5 = false;
    private int c5;
    private int a5;
	
	public Principal(){
		Instancias();
		c1 = 8;
		a1 = 0;
		c2 = 8;
		a2 = 0;
		c3 = 8;
		a3 = 0;
		c4 = 8;
		a4 = 0;
		c5 = 8;
		a5 = 0;
		b = 0;
		lleno = 0;
	}

	public void Instancias(){
		IniciarCajas();
		interfaz = new Ventana();
		generador = new Random();
		btnFunciones = new JButton("Iniciar");
		btnFunciones.setBounds(612, 132, 100, 23);
		interfaz.add(btnFunciones);
		btnFunciones.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
					if (estado == 0){
						IniciarCrono();
						estado = 1;
					}else if (estado == 1){
						PararCrono();
						estado = 2;
					}else if (estado == 2){
						ReiniciarCrono();
						estado = 0;
					}
				}
			});
	}

	public void IniciarCajas(){
		caja1 = new Caja();
		clienteEnCaja1 = null;
		caja2 = new Caja();
		clienteEnCaja2 = null;
		caja3 = new Caja();
		clienteEnCaja3 = null;
		caja4 = new Caja();
		clienteEnCaja4 = null;
		caja5 = new Caja();
		clienteEnCaja5 = null;
	}
	
	public void GenerarClientesCaja(){
		if (activo == true){
			//Genera Num del 1 al 15, si sale 1 crea un cliente 
			if ((int)(generador.nextDouble() * 15 + 1) == 1){
				c = (int)(generador.nextDouble() * 5 + 1);
				switch(c){
					case 1:
						AgrCaja1();
						break;
					case 2:
						AgrCaja2();
						break;
					case 3:
						AgrCaja3();
						break;
					case 4:
						AgrCaja4();
						break;
					case 5:
						AgrCaja5();
						break;
				}
			}
		}
	}
	
	public void AgrCaja1(){
		if (caja1.ClientesEnCola() < 20){
			caja1.AgregarClientes(new Cliente());
			b = 0;
			if (clienteEnCaja1 == null)
				clienteEnCaja1 = caja1.ClientesCola().toString().split(",");
		}else{
			b++;
			if (b == 5){
				lleno++;
				b = 0;
			}else
				AgrCaja2();
		}
	}
	
	public void GenCaja1(){
		auxc1 = Integer.valueOf(clienteEnCaja1[9]);
		while(auxc1 > 0){
			auxc11 = (int)(generador.nextDouble() * 8 + 1);
			if (auxc11 == 1){
				auxCaja1[1] = String.valueOf(Integer.valueOf(auxCaja1[1])+1);
				auxc1 -= 1;
			}else if ((auxc11 == 2) && (auxc1 > 2)){
					auxc1 -= 2;
					auxCaja1[2] = String.valueOf(Integer.valueOf(auxCaja1[2])+1);
			}else if ((auxc11 == 3) && (auxc1 > 5)){
					auxc1 -= 5;
					auxCaja1[3] = String.valueOf(Integer.valueOf(auxCaja1[3])+1);
			}else if ((auxc11 == 4) && (auxc1 > 10)){
					auxc1 -= 10;
					auxCaja1[4] = String.valueOf(Integer.valueOf(auxCaja1[4])+1);
			}else if ((auxc11 == 5) && (auxc1 > 20)){
					auxc1 -= 20;
					auxCaja1[5] = String.valueOf(Integer.valueOf(auxCaja1[5])+1);
			}else if ((auxc11 == 6) && (auxc1 > 50)){
					auxc1 -= 50;
					auxCaja1[6] = String.valueOf(Integer.valueOf(auxCaja1[6])+1);
			}else if ((auxc11 == 7) && (auxc1 > 100)){
					auxc1 -= 100;
					auxCaja1[7] = String.valueOf((Integer.valueOf(auxCaja1[7]))+1);
			}else if ((auxc11 == 8) && (auxc1 > 500)){
					auxc1 -= 500;
					auxCaja1[8] = String.valueOf(Integer.valueOf(auxCaja1[8])+1);
			}else{}
		}
		auxCaja1[0] = String.valueOf(1);
	}
	
	public void AgrCaja2(){
		if (caja2.ClientesEnCola() < 20){
			caja2.AgregarClientes(new Cliente());
			b = 0;
			if (clienteEnCaja2 == null)
				clienteEnCaja2 = caja2.ClientesCola().toString().split(",");
		}else{
			b++;
			if (b == 5){
				lleno++;
				b = 0;
			}else
				AgrCaja3();
		}
	}
	
	public void GenCaja2(){
		auxc2 = Integer.valueOf(clienteEnCaja2[9]);
		while(auxc2 > 0){
			auxc21 = (int)(generador.nextDouble() * 8 + 1);
			if (auxc21 == 1){
				auxCaja2[1] = String.valueOf(Integer.valueOf(auxCaja2[1])+1);
				auxc2 -= 1;
			}else if ((auxc21 == 2) && (auxc2 > 2)){
					auxc2 -= 2;
					auxCaja2[2] = String.valueOf(Integer.valueOf(auxCaja2[2])+1);
			}else if ((auxc21 == 3) && (auxc2 > 5)){
					auxc2 -= 5;
					auxCaja2[3] = String.valueOf(Integer.valueOf(auxCaja2[3])+1);
			}else if ((auxc21 == 4) && (auxc2 > 10)){
					auxc2 -= 10;
					auxCaja2[4] = String.valueOf(Integer.valueOf(auxCaja2[4])+1);
			}else if ((auxc21 == 5) && (auxc2 > 20)){
					auxc2 -= 20;
					auxCaja2[5] = String.valueOf(Integer.valueOf(auxCaja2[5])+1);
			}else if ((auxc21 == 6) && (auxc1 > 50)){
					auxc2 -= 50;
					auxCaja2[6] = String.valueOf(Integer.valueOf(auxCaja2[6])+1);
			}else if ((auxc21 == 7) && (auxc2 > 100)){
					auxc2 -= 100;
					auxCaja2[7] = String.valueOf((Integer.valueOf(auxCaja2[7]))+1);
			}else if ((auxc21 == 8) && (auxc2 > 500)){
					auxc2 -= 500;
					auxCaja2[8] = String.valueOf(Integer.valueOf(auxCaja2[8])+1);
			}else{}
		}
		auxCaja2[0] = String.valueOf(1);
	}	
	
	public void AgrCaja3(){
		if (caja3.ClientesEnCola() < 20){
			caja3.AgregarClientes(new Cliente());
			b = 0;
			if (clienteEnCaja3 == null)
				clienteEnCaja3 = caja3.ClientesCola().toString().split(",");
		}else{
			b++;
			if (b == 5){
				lleno++;
				b = 0;
			}else
				AgrCaja4();
		}
	}
	
	public void GenCaja3(){
		auxc3 = Integer.valueOf(clienteEnCaja3[9]);
		while(auxc3 > 0){
			auxc31 = (int)(generador.nextDouble() * 8 + 1);
			if (auxc31 == 1){
				auxCaja3[1] = String.valueOf(Integer.valueOf(auxCaja3[1])+1);
				auxc3 -= 1;
			}else if ((auxc31 == 2) && (auxc3 > 2)){
					auxc3 -= 2;
					auxCaja3[2] = String.valueOf(Integer.valueOf(auxCaja3[2])+1);
			}else if ((auxc31 == 3) && (auxc1 > 5)){
					auxc3 -= 5;
					auxCaja3[3] = String.valueOf(Integer.valueOf(auxCaja3[3])+1);
			}else if ((auxc31 == 4) && (auxc3 > 10)){
					auxc3 -= 10;
					auxCaja3[4] = String.valueOf(Integer.valueOf(auxCaja3[4])+1);
			}else if ((auxc31 == 5) && (auxc3 > 20)){
					auxc3 -= 20;
					auxCaja3[5] = String.valueOf(Integer.valueOf(auxCaja3[5])+1);
			}else if ((auxc31 == 6) && (auxc3 > 50)){
					auxc3 -= 50;
					auxCaja3[6] = String.valueOf(Integer.valueOf(auxCaja3[6])+1);
			}else if ((auxc31 == 7) && (auxc3 > 100)){
					auxc3 -= 100;
					auxCaja3[7] = String.valueOf((Integer.valueOf(auxCaja3[7]))+1);
			}else if ((auxc31 == 8) && (auxc3 > 500)){
					auxc3 -= 500;
					auxCaja3[8] = String.valueOf(Integer.valueOf(auxCaja3[8])+1);
			}else{}
		}
		auxCaja3[0] = String.valueOf(1);
	}
	
	public void AgrCaja4(){
		if (caja4.ClientesEnCola() < 20){
			caja4.AgregarClientes(new Cliente());
			b = 0;
			if (clienteEnCaja4 == null)
				clienteEnCaja4 = caja4.ClientesCola().toString().split(",");
		}else{
			b++;
			if (b == 5){
				lleno++;
				b = 0;
			}else
				AgrCaja5();
		}
	}
	
	public void GenCaja4(){
		auxc4 = Integer.valueOf(clienteEnCaja4[9]);
		while(auxc4 > 0){
			auxc41 = (int)(generador.nextDouble() * 8 + 1);
			if (auxc41 == 1){
				auxCaja4[1] = String.valueOf(Integer.valueOf(auxCaja4[1])+1);
				auxc4 -= 1;
			}else if ((auxc41 == 2) && (auxc4 > 2)){
					auxc4 -= 2;
					auxCaja4[2] = String.valueOf(Integer.valueOf(auxCaja4[2])+1);
			}else if ((auxc41 == 3) && (auxc4 > 5)){
					auxc4 -= 5;
					auxCaja4[3] = String.valueOf(Integer.valueOf(auxCaja4[3])+1);
			}else if ((auxc41 == 4) && (auxc4 > 10)){
					auxc4 -= 10;
					auxCaja4[4] = String.valueOf(Integer.valueOf(auxCaja4[4])+1);
			}else if ((auxc41 == 5) && (auxc4 > 20)){
					auxc4 -= 20;
					auxCaja4[5] = String.valueOf(Integer.valueOf(auxCaja4[5])+1);
			}else if ((auxc41 == 6) && (auxc4 > 50)){
					auxc4 -= 50;
					auxCaja4[6] = String.valueOf(Integer.valueOf(auxCaja4[6])+1);
			}else if ((auxc41 == 7) && (auxc4 > 100)){
					auxc4 -= 100;
					auxCaja4[7] = String.valueOf((Integer.valueOf(auxCaja4[7]))+1);
			}else if ((auxc41 == 8) && (auxc4 > 500)){
					auxc4 -= 500;
					auxCaja4[8] = String.valueOf(Integer.valueOf(auxCaja4[8])+1);
			}else{}
		}
		auxCaja4[0] = String.valueOf(1);
	}
	
	public void AgrCaja5(){
		if (caja5.ClientesEnCola() < 20){
			caja5.AgregarClientes(new Cliente());
			b = 0;
			if (clienteEnCaja5 == null)
				clienteEnCaja5 = caja5.ClientesCola().toString().split(",");
		}else{
			b++;
			if (b == 5){
				lleno++;
				b = 0;
			}else
				AgrCaja1();
		}
	}
	
	public void GenCaja5(){
		auxc5 = Integer.valueOf(clienteEnCaja5[9]);
		while(auxc5 > 0){
			auxc51 = (int)(generador.nextDouble() * 8 + 1);
			if (auxc51 == 1){
				auxCaja5[1] = String.valueOf(Integer.valueOf(auxCaja5[1])+1);
				auxc5 -= 1;
			}else if ((auxc51 == 2) && (auxc5 > 2)){
					auxc5 -= 2;
					auxCaja5[2] = String.valueOf(Integer.valueOf(auxCaja5[2])+1);
			}else if ((auxc51 == 3) && (auxc5 > 5)){
					auxc5 -= 5;
					auxCaja5[3] = String.valueOf(Integer.valueOf(auxCaja5[3])+1);
			}else if ((auxc51 == 4) && (auxc5 > 10)){
					auxc5 -= 10;
					auxCaja5[4] = String.valueOf(Integer.valueOf(auxCaja5[4])+1);
			}else if ((auxc51 == 5) && (auxc5 > 20)){
					auxc5 -= 20;
					auxCaja5[5] = String.valueOf(Integer.valueOf(auxCaja5[5])+1);
			}else if ((auxc51 == 6) && (auxc5 > 50)){
					auxc5 -= 50;
					auxCaja5[6] = String.valueOf(Integer.valueOf(auxCaja5[6])+1);
			}else if ((auxc51 == 7) && (auxc5 > 100)){
					auxc5 -= 100;
					auxCaja5[7] = String.valueOf((Integer.valueOf(auxCaja5[7]))+1);
			}else if ((auxc51 == 8) && (auxc5 > 500)){
					auxc5 -= 500;
					auxCaja5[8] = String.valueOf(Integer.valueOf(auxCaja5[8])+1);
			}else{}
		}
		auxCaja5[0] = String.valueOf(1);
	}
	
	public void DepositoCaja1(){
		if (Integer.valueOf(clienteEnCaja1[9]) == 0){
				caja1.QuitarClientes();
				if (!caja1.ColaVacia())
					clienteEnCaja1 = caja1.ClientesCola().toString().split(",");
				else{
					clienteEnCaja1 = null;
					return;
				}
			}
		if (Integer.valueOf(clienteEnCaja1[c1]) != 0){
			clienteEnCaja1[c1] = String.valueOf(Integer.valueOf(clienteEnCaja1[c1])-1);
			caja1.MeterBilletes(c1);
		}else{
			c1--;
			if(c1 == 0){
				clienteEnCaja1[9] = String.valueOf(0);
				c1 = 8;
				a1++;
			}
		}
	}
	
	public void RetiroCaja1(){
		if (Integer.valueOf(clienteEnCaja1[9]) == 0){
				caja1.QuitarClientes();
				if (!caja1.ColaVacia())
					clienteEnCaja1 = caja1.ClientesCola().toString().split(",");
				else{
					clienteEnCaja1 = null;
					return;
				}
			}
		if (Integer.valueOf(clienteEnCaja1[c1]) != 0){
			clienteEnCaja1[c1] = String.valueOf(Integer.valueOf(clienteEnCaja1[c1])-1);
			caja1.SacarBilletes(c1);
		}else{
			c1--;
			if(c1 == 0){
				clienteEnCaja1[9] = String.valueOf(0);
				c1 = 8;
				a1++;
				boolC1 = false;
			}
		}
	}
	
	public void CambioCaja1(){
		if (Integer.valueOf(clienteEnCaja1[9]) == 0){
				caja1.QuitarClientes();
				if (!caja1.ColaVacia()){
					clienteEnCaja1 = caja1.ClientesCola().toString().split(",");
					GenCaja1();
				}else{
					clienteEnCaja1 = null;
					return;
				}
			}
		if (boolC1 != true){
			if (Integer.valueOf(auxCaja1[c1]) != 0){
				auxCaja1[c1] = String.valueOf(Integer.valueOf(auxCaja1[c1])-1);
				caja1.MeterBilletes(c1);
			}else{
				c1--;
				if (c1==0){
					boolC1 = true;
					c1 = 8;
				}
			}
		}
		if (boolC1 != false){
			RetiroCaja1();
		}
	}
	
	public void DepositoCaja2(){
		if (Integer.valueOf(clienteEnCaja2[9]) == 0){
				caja2.QuitarClientes();
				if (!caja2.ColaVacia())
					clienteEnCaja2 = caja2.ClientesCola().toString().split(",");
				else{
					clienteEnCaja2 = null;
					return;
				}
			}
		if (Integer.valueOf(clienteEnCaja2[c2]) != 0){
			clienteEnCaja2[c2] = String.valueOf(Integer.valueOf(clienteEnCaja2[c2])-1);
			caja2.MeterBilletes(c2);
		}else{
			c2--;
			if(c2 == 0){
				clienteEnCaja2[9] = String.valueOf(0);
				c2 = 8;
				a2++;
			}
		}
	}
	
	public void RetiroCaja2(){
		if (Integer.valueOf(clienteEnCaja2[9]) == 0){
				caja2.QuitarClientes();
				if (!caja2.ColaVacia())
					clienteEnCaja2 = caja2.ClientesCola().toString().split(",");
				else{
					clienteEnCaja2 = null;
					return;
				}
			}
		if (Integer.valueOf(clienteEnCaja2[c2]) != 0){
			clienteEnCaja2[c2] = String.valueOf(Integer.valueOf(clienteEnCaja2[c2])-1);
			caja2.SacarBilletes(c2);
		}else{
			c2--;
			if(c2 == 0){
				clienteEnCaja2[9] = String.valueOf(0);
				c2 = 8;
				a2++;
				boolC2 = false;
			}
		}
	}
	
	public void CambioCaja2(){
		if (Integer.valueOf(clienteEnCaja2[9]) == 0){
			caja2.QuitarClientes();
			if (!caja2.ColaVacia()){
				clienteEnCaja2 = caja2.ClientesCola().toString().split(",");
				GenCaja2();
			}else{
				clienteEnCaja2 = null;
				return;
			}
		}
		if (boolC2 != true){
			if (Integer.valueOf(auxCaja2[c2]) != 0){
				auxCaja2[c2] = String.valueOf(Integer.valueOf(auxCaja2[c2])-1);
				caja2.MeterBilletes(c2);
			}else{
				c2--;
				if (c2==0){
					boolC2 = true;
					c2 = 8;
				}
			}
		}
		if (boolC2 != false){
			RetiroCaja2();
		}
	}
	
	public void DepositoCaja3(){
		if (Integer.valueOf(clienteEnCaja3[9]) == 0){
				caja3.QuitarClientes();
				if (!caja3.ColaVacia())
					clienteEnCaja3 = caja3.ClientesCola().toString().split(",");
				else{
					clienteEnCaja3 = null;
					return;
				}
			}
		if (Integer.valueOf(clienteEnCaja3[c3]) != 0){
			clienteEnCaja3[c3] = String.valueOf(Integer.valueOf(clienteEnCaja3[c3])-1);
			caja3.MeterBilletes(c3);
		}else{
			c3--;
			if(c3 == 0){
				clienteEnCaja3[9] = String.valueOf(0);
				c3 = 8;
				a3++;
			}
		}
	}
	
	public void RetiroCaja3(){
		if (Integer.valueOf(clienteEnCaja3[9]) == 0){
				caja3.QuitarClientes();
				if (!caja3.ColaVacia())
					clienteEnCaja3 = caja3.ClientesCola().toString().split(",");
				else{
					clienteEnCaja3 = null;
					return;
				}
			}
		if (Integer.valueOf(clienteEnCaja3[c3]) != 0){
			clienteEnCaja3[c3] = String.valueOf(Integer.valueOf(clienteEnCaja3[c3])-1);
			caja3.SacarBilletes(c3);
		}else{
			c3--;
			if(c3 == 0){
				clienteEnCaja3[9] = String.valueOf(0);
				c3 = 8;
				a3++;
				boolC3 = false;
			}
		}
	}
	
	public void CambioCaja3(){
		if (Integer.valueOf(clienteEnCaja3[9]) == 0){
			caja3.QuitarClientes();
			if (!caja3.ColaVacia()){
				clienteEnCaja3 = caja3.ClientesCola().toString().split(",");
				GenCaja3();
			}else{
				clienteEnCaja3 = null;
				return;
			}
		}
		if (boolC3 != true){
			if (Integer.valueOf(auxCaja3[c3]) != 0){
				auxCaja3[c3] = String.valueOf(Integer.valueOf(auxCaja3[c3])-1);
				caja3.MeterBilletes(c3);
			}else{
				c3--;
				if (c3==0){
					boolC3 = true;
					c3 = 8;
				}
			}
		}
		if (boolC3 != false){
			RetiroCaja3();
		}
	}
	
	public void DepositoCaja4(){
		if (Integer.valueOf(clienteEnCaja4[9]) == 0){
				caja4.QuitarClientes();
				if (!caja4.ColaVacia())
					clienteEnCaja4 = caja4.ClientesCola().toString().split(",");
				else{
					clienteEnCaja4 = null;
					return;
				}
			}
		if (Integer.valueOf(clienteEnCaja4[c4]) != 0){
			clienteEnCaja4[c4] = String.valueOf(Integer.valueOf(clienteEnCaja4[c4])-1);
			caja4.MeterBilletes(c4);
		}else{
			c4--;
			if(c4 == 0){
				clienteEnCaja4[9] = String.valueOf(0);
				c4 = 8;
				a4++;
			}
		}
	}
		
	public void RetiroCaja4(){
		if (Integer.valueOf(clienteEnCaja4[9]) == 0){
				caja4.QuitarClientes();
				if (!caja4.ColaVacia())
					clienteEnCaja4 = caja4.ClientesCola().toString().split(",");
				else{
					clienteEnCaja4 = null;
					return;
				}
			}
		if (Integer.valueOf(clienteEnCaja4[c4]) != 0){
			clienteEnCaja4[c4] = String.valueOf(Integer.valueOf(clienteEnCaja4[c4])-1);
			caja4.SacarBilletes(c4);
		}else{
			c4--;
			if(c4 == 0){
				clienteEnCaja4[9] = String.valueOf(0);
				c4 = 8;
				a4++;
				boolC4 = false;
			}
		}
	}
	
	public void CambioCaja4(){
		if (Integer.valueOf(clienteEnCaja4[9]) == 0){
			caja4.QuitarClientes();
			if (!caja4.ColaVacia()){
				clienteEnCaja4 = caja4.ClientesCola().toString().split(",");
				GenCaja4();
			}else{
				clienteEnCaja4 = null;
				return;
			}
		}
		if (boolC4 != true){
			if (Integer.valueOf(auxCaja4[c4]) != 0){
				auxCaja4[c4] = String.valueOf(Integer.valueOf(auxCaja4[c4])-1);
				caja4.MeterBilletes(c4);
			}else{
				c4--;
				if (c4==0){
					boolC4 = true;
					c4 = 8;
				}
			}
		}
		if (boolC4 != false){
			RetiroCaja4();
		}
	}

	public void DepositoCaja5(){
		if (Integer.valueOf(clienteEnCaja5[9]) == 0){
				caja5.QuitarClientes();
				if (!caja5.ColaVacia())
					clienteEnCaja5 = caja5.ClientesCola().toString().split(",");
				else{
					clienteEnCaja5 = null;
					return;
				}
			}
		if (Integer.valueOf(clienteEnCaja5[c5]) != 0){
			clienteEnCaja5[c5] = String.valueOf(Integer.valueOf(clienteEnCaja5[c5])-1);
			caja5.MeterBilletes(c5);
		}else{
			c5--;
			if(c5 == 0){
				clienteEnCaja5[9] = String.valueOf(0);
				c5 = 8;
				a5++;
			}
		}
	}
		
	public void RetiroCaja5(){
		if (Integer.valueOf(clienteEnCaja5[9]) == 0){
				caja5.QuitarClientes();
				if (!caja5.ColaVacia())
					clienteEnCaja5 = caja5.ClientesCola().toString().split(",");
				else{
					clienteEnCaja5 = null;
					return;
				}
			}
		if (Integer.valueOf(clienteEnCaja5[c5]) != 0){
			clienteEnCaja5[c5] = String.valueOf(Integer.valueOf(clienteEnCaja5[c5])-1);
			caja5.SacarBilletes(c5);
		}else{
			c5--;
			if(c5 == 0){
				clienteEnCaja5[9] = String.valueOf(0);
				c5 = 8;
				a5++;
				boolC5 = false;
			}
		}
	}
	
	public void CambioCaja5(){
		if (Integer.valueOf(clienteEnCaja5[9]) == 0){
			caja5.QuitarClientes();
			if (!caja5.ColaVacia()){
				clienteEnCaja5 = caja5.ClientesCola().toString().split(",");
				GenCaja5();
			}else{
				clienteEnCaja5 = null;
				return;
			}
		}
		if (boolC5 != true){
			if (Integer.valueOf(auxCaja5[c5]) != 0){
				auxCaja5[c5] = String.valueOf(Integer.valueOf(auxCaja5[c5])-1);
				caja5.MeterBilletes(c5);
			}else{
				c5--;
				if (c5==0){
					boolC5 = true;
					c5 = 8;
				}
			}
		}
		if (boolC5 != false){
			RetiroCaja5();
		}
	}
	
	public static void main(String[] args) {
		new Principal();
	}
	
	@Override
	public void run() {
		Integer minutos = 0 , segundos = 0, milesimas = 0;
		String min="", seg="", mil="";
	        try {
	            while(pausar){
	                while( activo ) {
	                    Thread.sleep(125); // Dormir Hilo Por 125 Milesimas
	                    //Incrementamos 125 milesimas de segundo
	                    milesimas += 125;
	                    //Cuando llega a 1000 aumenta 1 segundo
	                    //y las milesimas de segundo de nuevo a 0
	                    if( milesimas == 1000 ) {
	                        milesimas = 0;
	                        segundos += 1;
	                        //Si los segundos llegan a 60 aumenta 1 los minutos
	                        //y los segundos vuelven a 0
	                        if( segundos == 60 ){
	                            segundos = 0;
	                            minutos++;
	                        }
	                    }
	                    //Imprimir el tiempo en Formato 00:00:000
	                    if( minutos < 10 )
	                    	min = "0" + minutos;
	                    else 
	                    	min = minutos.toString();
	                    if( segundos < 10 )
	                    	seg = "0" + segundos;
	                    else
	                    	seg = segundos.toString();

	                    if( milesimas < 10 )
	                    	mil = "00" + milesimas;
	                    else if( milesimas < 100 )
	                    	mil = "0" + milesimas;
	                    else
	                    	mil = milesimas.toString();
	                    interfaz.Tiempo( min + ":" + seg + ":" + mil );
	                    
	                    GenerarClientesCaja();
	                    if (clienteEnCaja1 != null){
	                    	if (clienteEnCaja1[0].equals("d")){
	                    		DepositoCaja1();
	                    	}else if (clienteEnCaja1[0].equals("r")){
	                    		RetiroCaja1();
	                    	}else{
	                    		if (auxCaja1[0].equals("0"))
	                    			GenCaja1();
	                    		CambioCaja1();
	                    	}
	                    }
	                    if (clienteEnCaja2 != null){
	                    	if (clienteEnCaja2[0].equals("d")){
	                    		DepositoCaja2();
	                    	}else if (clienteEnCaja2[0].equals("r")){
	                    		RetiroCaja2();
	                    	}else{
	                    		if (auxCaja2[0].equals("0"))
	                    			GenCaja2();
	                    		CambioCaja2();
	                    	}
	                    }
	                    if (clienteEnCaja3 != null){
	                    	if (clienteEnCaja3[0].equals("d")){
	                    		DepositoCaja3();
	                    	}else if (clienteEnCaja3[0].equals("r")){
	                    		RetiroCaja3();
	                    	}else{
	                    		if (auxCaja3[0].equals("0"))
	                    			GenCaja3();
	                    		CambioCaja3();
	                    	}
	                    }
	                    if (clienteEnCaja4 != null){
	                    	if (clienteEnCaja4[0].equals("d")){
	                    		DepositoCaja4();
	                    	}else if (clienteEnCaja4[0].equals("r")){
	                    		RetiroCaja4();
	                    	}else{
	                    		if (auxCaja4[0].equals("0"))
	                    			GenCaja4();
	                    		CambioCaja4();
	                    	}
	                    }
	                    if (clienteEnCaja5 != null){
	                    	if (clienteEnCaja5[0].equals("d")){
	                    		DepositoCaja5();
	                    	}else if (clienteEnCaja5[0].equals("r")){
	                    		RetiroCaja5();
	                    	}else{
	                    		if (auxCaja5[0].equals("0"))
	                    			GenCaja5();
	                    		CambioCaja5();
	                    	}
	                    }
	            		interfaz.Caja1(caja1.DineroEnCaja(), a1,caja1.ClientesEnCola());
	            		interfaz.Caja2(caja2.DineroEnCaja(), a2,caja2.ClientesEnCola());
	            		interfaz.Caja3(caja3.DineroEnCaja(), a3,caja3.ClientesEnCola());
	            		interfaz.Caja4(caja4.DineroEnCaja(), a4,caja4.ClientesEnCola());
	            		interfaz.Caja5(caja5.DineroEnCaja(), a5,caja5.ClientesEnCola());
	                    interfaz.Total(lleno);
	                    // Parar Automaticamente La simulacion en X tiempo
	                    if (minutos == 3){
	                    	PararCrono();
	                    	estado = 2;
	                    }
	                }
	                interfaz.Tiempo( min + ":" + seg + ":" + mil);
	            }
	        }catch(Exception e){}
	        //Reinicia Toda La Interfaz
	        interfaz.Tiempo("00:00:000");
	        interfaz.Caja1(0,0,0);
	        interfaz.Caja2(0,0,0);
	        interfaz.Caja3(0,0,0);
	        interfaz.Caja4(0,0,0);
	        interfaz.Caja5(0,0,0);
	        interfaz.Total(0);
	}
		
	public void IniciarCrono() {
	    activo = true;
	    pausar = true;
	    cronometro = new Thread(this);
	    cronometro.start();
	    btnFunciones.setText("Parar");
	}
	
	public void PararCrono(){
	    activo = false;
	    btnFunciones.setText("Reiniciar");
	 }
	
	public void ReiniciarCrono(){
	    pausar = false;
	    IniciarCajas();
		c1 = 8;
		a1 = 0;
		c2 = 8;
		a2 = 0;
		c3 = 8;
		a3 = 0;
		c4 = 8;
		a4 = 0;
		c5 = 8;
		a5 = 0;
		b = 0;
		lleno = 0;
		auxCaja1[0] = "0";
		auxCaja2[0] = "0";
		auxCaja3[0] = "0";
		auxCaja4[0] = "0";
		auxCaja5[0] = "0";
		boolC1 = false;
		boolC2 = false;
		boolC3 = false;
		boolC4 = false;
		boolC5 = false;
	    btnFunciones.setText("Iniciar");
	 }

}