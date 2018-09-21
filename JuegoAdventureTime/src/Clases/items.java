package Clases;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;


public class items extends Atributos {
	private String[] Llaves = {"item1", "item2","item3","item4","item5"
			,"item6", "item7", "item8", "item9", "item10"};

	public items(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setx(int x){
		this.x = x;
	}
		
	public void sety(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
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
			llaveImagen = Llaves[(int)Math.floor(Math.random()*(9))];
			x=(int)Math.floor(Math.random()*(2000-1000)+1000);
			y=(int)Math.floor(Math.random()*(400-200+1)+200);
		}else
			x-=4;
		if(x<-150)
			x=-15;
	}

}