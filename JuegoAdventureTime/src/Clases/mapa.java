package Clases;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class mapa extends Atributos{
	
	public mapa(int a, int b, int c, String llave){
		x = a;
		y = b;
		velocidad = c;
		llaveImagen = llave;
	}
	
	public String getllave(){
		return llaveImagen;
	}
	
	public void setx(int x){
		this.x = x;
	}
		
	@Override
	public void pintar(Graphics2D g2D, BufferedImage imagen, ImageObserver canvas) {
		g2D.drawImage(imagen, x, y, canvas);
	}

	@Override
	public void mover() {
		x -= velocidad;
		if (Math.abs(x) == 6124)
			x = 0 ;	
	}

	

}
