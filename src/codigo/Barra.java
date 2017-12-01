package codigo;

import java.awt.Color;

import acm.graphics.GRect;

/*
 * 
 * @author Juan Pablo Carpio
 *
 *la clase Barra es la que dibuja el cursor del juego
 */
public class Barra extends GRect{

	/*
	 * crea el cursor del juego
	 * 
	 * @param width --> el ancho del cursor
	 * @param height --> el alto del cursor
	 * @param _color --> el color del cursor
	 * 
	 */

	public Barra(double width, double height, Color _color) {
		super(width, height);

		setFilled(true);
		setFillColor(_color);

	}
	/*
	 * mueveBarra reposiciona la barra en la cordenada en la q esta el raton
	 * @param posX La posicion x en la q esta el raton
	 * @param anchoPantalla pq asi no tengo q pasar nada mas. 
	 */
	public void mueveBarra(int posX, int anchoPantalla){
		if (posX + getWidth() < anchoPantalla - Arkanoid.espacioMenu){
			setLocation(posX, getY());
		}
	}
}