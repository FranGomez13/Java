package Clases;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class obstaculos extends Atributos {
	private String[] Llaves = {"obstaculo1", "obstaculo2","obstaculo3","obstaculo4","obstaculo5"
			,"obstaculo6", "obstaculo7", "obstaculo8", "obstaculo9", "obstaculo10","obstaculo12"
			,"obstaculo12","obstaculo13","obstaculo14","obstaculo15","obstaculo16","obstaculo17"
			,"obstaculo18"};
	
	public obstaculos(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setx(int x){
		this.x = x;
	}
	
	public int getX(){
		return x;
	}
		
	public void sety(int y){
		this.y = y;
	}
	
	public int getY(){
		return y;
	}
	
	public String getLlave(){
		return llaveImagen;
	}
	
	@Override
	public void pintar(Graphics2D g2D, BufferedImage imagen, ImageObserver canvas) {
		g2D.drawImage(imagen, x, y, canvas);
	}

	@Override
	public void mover() {
		if (Math.abs(x)==15){
			llaveImagen = Llaves[(int)Math.floor(Math.random()*(17))];
			x=(int)Math.floor(Math.random()*(2000-1000+1)+1000);
			y = 460;
			if (llaveImagen.equals("obstaculo15")||llaveImagen.equals("obstaculo16")
					||llaveImagen.equals("obstaculo17")||llaveImagen.equals("obstaculo18"))
				y=(int)Math.floor(Math.random()*(400-300+1)+300);
		}else
			x-=4;
		if(x < -150)
			x=-15;
	}

}
