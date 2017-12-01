package codigo;

import java.awt.Color;
import java.awt.color.*;

import acm.graphics.GRect;

/**
 * 
 * @author Juan Pablo Carpio
 *
 *La clase sirve para dibujar los ladrillos
 *del juego
 */
public class Ladrillo extends GRect{
	/**
	 * construye un ladrillo
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * 
	 * futuros cambios: que admita numero de golpes
	 * @param _color 
	 */
	public Ladrillo(double x, double y, double width, double height, Color _color) {
		super(x, y, width, height);
		setFilled(true);
		setFillColor(_color);
	}

}