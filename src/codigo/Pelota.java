package codigo;
/*
 * Autor:Juan Pablo
 * 
 * La clase es la que vamos a utilizar para 
 * el juego de arkanoid
 */
import java.awt.Color;

import acm.graphics.GObject;
import acm.graphics.GOval;

/*
 * 
 */

public class Pelota extends GOval{
	
	double xVelocidad = 1;  //velocidad de la bola en el eje x
	double yVelocidad = -1; //velocidad de la bola en el eje x
	
	
	/**
	 * Este es el constructor basico, q es identico al de la clase GOval
	 * @param _ancho indica el ancho de la bola
	 * @param _alto indica el alto de la bola
	 */
	public  Pelota(double _ancho,double _alto){
		super(_ancho,_alto);
	}
	/**
	 * este es el constructor dabuti, que permite pasar el color como parametro
	 * @param _ancho
	 * @param _color
	 */
	public  Pelota(double _ancho,Color _color ){
		
		super(_ancho,_ancho);
		if (_ancho <=0){
			this.setSize(1, 1);
		}
		this.setFillColor(_color);
		this.setFilled(true);
	}
	/**
	 * se encarga de mover las pelotas y chequear si ha habido colisiones
	 * 
	 */
	public void muevete(Arkanoid _arkanoid){
		//chequea si a chocado con las paredes de la derecha
		if (this.getX() + this.getWidth() >= _arkanoid.getWidth() - _arkanoid.espacioMenu || this.getX() < 0){
			xVelocidad *=-1;
		}
		//chequea si ha chocado con el techo
		if (this.getY() < 0){
			yVelocidad *=-1;
		}
		if (chequeaColision(getX(), getY(), _arkanoid));{ //cheque esquina superior izquierda
			if (chequeaColision(getX() + getWidth(), getY(), _arkanoid));{ //cheque esquina superior derecha
				if (chequeaColision(getX() , getY() + getHeight(), _arkanoid));{ //cheque esquina inferior izquierda
					if (chequeaColision(getX() + getWidth(), getY() + getHeight(), _arkanoid));{ //cheque esquina superior derecha
						
						
					}
				}
			}
		}
		
		//voy a crear un chequea colision generico 
		//que sirva para comprobar los choques entre la bola y los ladrillos 
		//y la bola del cursor
		move(xVelocidad,yVelocidad);
	}
	private boolean chequeaColision(double posX, double posY, Arkanoid _arkanoid){
		boolean noHaChocado = true;
		GObject auxiliar;
		auxiliar = _arkanoid.getElementAt(posX, posY);
		
		if (auxiliar instanceof Ladrillo){
			if (auxiliar.getY() == posY || auxiliar.getY() + auxiliar.getHeight() == posY ){
				yVelocidad *=-1;
			}
			else if (auxiliar.getX() == posX || auxiliar.getX() + auxiliar.getWidth() == posX ){
				xVelocidad *=-1;
			}
			_arkanoid.remove(auxiliar);
			_arkanoid.marcador.actualizaMarcador(1);
			noHaChocado = false;
			}
		else if (auxiliar instanceof Barra){
			yVelocidad +=-1;
			noHaChocado = false;
		}
		return noHaChocado;
	}
	/*private void chequeaColisionConBarra(Arkanoid _arkanoid){
	*	// chequea las esquinas inferiores de la bola para ver si hay una barra
	*	if (_arkanoid.getElementAt(getX(),getY()+ getHeight()) == _arkanoid.barra1 ){
	*		yVelocidad *=-1;
	*	}
	*	else if(_arkanoid.getElementAt(getX() + getWidth(), getY() + getHeight()) == _arkanoid.barra1){
	*		yVelocidad *=-1;
	*	}
	*}
	*/
}
