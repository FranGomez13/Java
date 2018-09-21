package Clases;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class Atributos {
	protected int x;
	protected int y;
	protected int velocidad;
	protected int anchoImagen;
	protected int altoImagen;
	protected String llaveImagen;
	protected int contador = 0;

	public abstract void pintar(Graphics2D g2D, BufferedImage imagen, ImageObserver canvas);
	
	public abstract void mover();
}
