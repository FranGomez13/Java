package Clases;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class personaje extends Atributos{
	private boolean agacharse;
	private boolean saltar;
	private boolean bajar;
	private Rectangle personaje = new Rectangle();
	
	public void setY(int y){
		this.y = y;
	}
		
	public int getY(){
		return y;
	}
	
	public boolean isBajar() {
		return bajar;
	}

	public void setBajar(boolean bajar) {
		this.bajar = bajar;
	}

	public boolean isSaltar() {
		return saltar;
	}

	public void setSaltar(boolean saltar) {
		this.saltar = saltar;
	}

	public void setLlave(String a){
		llaveImagen = a;
	}
		
	public boolean isAgacharse() {
		return agacharse;
	}

	public void setAgacharse(boolean agacharse) {
		this.agacharse = agacharse;
	}

	public String getLlave(){
		return llaveImagen;
	}
	
	public Rectangle getBounds(int xP, int yP, BufferedImage Imagen){
		personaje.setBounds(xP+20, yP+20, Imagen.getWidth()-40, Imagen.getHeight()-40);
		return personaje;
	}
	
	@Override
	public void pintar(Graphics2D g2D, BufferedImage imagen, ImageObserver canvas) {
		g2D.drawImage(imagen, x, y, canvas);
	}
	
	@Override
	public void mover() {
		x = 100;
		if (saltar == true){
			if(bajar == false){
				llaveImagen = "personajesaltar";
				y-=4;
				if (y<220)
					bajar = true;
		}else if(bajar == true){
				y+=5;
				if (y>400){
					bajar = false;
					saltar = false;
					contador = 23;
				}	
			}
		}else if (saltar == false && agacharse == true){
			y = 428;
			if(contador == 1)
				llaveImagen = "personajeagacharse1";
			else if(contador == 9)
					llaveImagen = "personajeagacharse2";
			else if (contador == 18)
					llaveImagen = "personajeagacharse3";
			else if (contador == 27)
					llaveImagen = "personajeagacharse4";
			else if (contador == 36)
					llaveImagen = "personajeagacharse5";
			else if (contador == 45)
					llaveImagen = "personajeagacharse6";
			else if (contador == 54){
					llaveImagen = "personajeagacharse1";
					contador=0;
		  }else if(contador > 54)
			  contador=0;
		  }else{
			y = 400;
			if(contador == 8)
				llaveImagen = "personajecaminar2";
			  else if (contador == 16)
				   	   llaveImagen = "personajecaminar3";
			  else if (contador == 24)
				  		llaveImagen = "personajecaminar4";
			  else if (contador == 32)
						llaveImagen = "personajecaminar5";
			  else if (contador == 40)
						llaveImagen = "personajecaminar6";
			  else if (contador == 48)
						llaveImagen = "personajecaminar7";
			  else if (contador == 56)
						llaveImagen = "personajecaminar8";
			  else if (contador == 64){
						llaveImagen = "personajecaminar1";
						contador=0;
			  }else if(contador > 64)
				  contador=0;
			}
		contador++;
	}
	
}
