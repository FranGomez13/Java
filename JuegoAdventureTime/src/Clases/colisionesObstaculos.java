package Clases;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class colisionesObstaculos extends Colisiones{
	
	@Override
	public boolean colision(int xP, int yP, int xO, int yO, BufferedImage Imagen1, BufferedImage Imagen2){
		return finn.getBounds(xP, yP, Imagen1).intersects(getBounds(xO, yO, Imagen2));
	}

	@Override
	public Rectangle getBounds(int x, int y, BufferedImage Imagen){
		objeto.setBounds(x, y, Imagen.getWidth(), Imagen.getHeight());
		return objeto;
	}
}
