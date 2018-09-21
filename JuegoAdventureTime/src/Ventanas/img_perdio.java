package Ventanas;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class img_perdio extends JPanel{
	@Override
	public void paintComponent (Graphics g){
	try {
		g.drawImage(ImageIO.read(getClass().getResource("/Recursos/fin_juego.jpg")), 0, 0, this);
	} catch (IOException e) {
		e.printStackTrace();
	}
    setOpaque(false);
	super.paintComponent(g);
	}
}
