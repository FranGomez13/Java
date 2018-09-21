package Clases;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Colisiones {
	protected Rectangle objeto = new Rectangle();
	protected personaje finn = new personaje();
	
	public abstract boolean colision(int xP, int yP, int xO, int yO, BufferedImage Imagen1, BufferedImage Imagen2);
		
	public abstract Rectangle getBounds(int x, int y, BufferedImage Imagen);
}
