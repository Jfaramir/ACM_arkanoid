package codigo;

import java.awt.Color;

import acm.graphics.GObject;
import acm.graphics.GOval;

/*
 * Autor:Alejandro Lago
 * 
 * La clase pelota es la que vamos a utilizar para
 * el juego del arkanoid
 * Tiene dos constructores
 */

public class Pelota extends GOval {


	double xVelocidad = 1;// velocidad de la bola en el eje x
	double yVelocidad =-1;// velocidad de la bola en el eje y


	/*
	 * Este es el constructor basico, que es identico
	 * al de la clase GOval
	 * @param ancho
	 * @param alto
	 */
	

	/*
	 * Este es el constructor bueno que permite
	 * pasar el color como parametro
	 * @param ancho indica el ancho y el alto de la bola
	 * @param color
	 */
	public Pelota(double ancho, Color color){

		super(ancho, ancho);
		if(ancho<=0){
			this.setSize(3, 3);
		}
		this.setFillColor(Color.BLACK);
		this.setFilled(true);
	}

	/*
	 * se encarga de mover la pelota y chequear si ha habido colision
	 */

	public void muevete(Arkanoid arkanoid){

		//chequea si choca con el techo
		if(this.getY()<0){
			yVelocidad *=-1;

		}
		//para q vuelva a salir la eplota una vez pierdes la primera bola
		if (this.getY() > arkanoid.getHeight()){
			if(arkanoid.vidas.puntosVida > 0){
				arkanoid.vidas.actualizaVidas(1);
				this.setLocation(0, arkanoid.getHeight()* 0.60 - this.getHeight());
				yVelocidad =-0.75;
				
			}
		}
		
		
		//chequea si se ha chocado con la pared izquierda o derecha
		if(this.getX()+this.getWidth()>= arkanoid.getWidth()- arkanoid.espacioMenu
				||this.getX()<0){
			xVelocidad *=-1;

		}

		if(chequeaColision(getX(), getY(), arkanoid)){//chequeo la esquina superior izq
			if(chequeaColision(getX()+getWidth(), getY(), arkanoid)){//chequeo la esquina superio der
				if(chequeaColision(getX(), getY()+getHeight(), arkanoid)){//chequeo la esquina inf izq
					
					if(chequeaColision(getX()+getWidth(), getY()+getHeight(), arkanoid)){//chequeo la esquina inf der

					}

				}

			}
		}

		/*voy a crear un metodo chequeaColision generico
		*que sirva para comprobar los choques entre la bola y los ladrillos
		*y el cursor
		*/
		move(xVelocidad, yVelocidad);

	}




	private boolean chequeaColision(double posX, double posY, Arkanoid arkanoid){
		boolean noHaChocado=true;
		GObject auxiliar;
		auxiliar= arkanoid.getElementAt(posX,posY);


		if(auxiliar instanceof Ladrillo){
			if(auxiliar.getY()== posY || auxiliar.getY() + auxiliar.getHeight() == posY){
				yVelocidad*=-1;

			}
			else if(auxiliar.getX()== posX || auxiliar.getX() + auxiliar.getWidth() == posX){
				xVelocidad *=-1;
			}
			arkanoid.remove(auxiliar);
			arkanoid.marcador.actualizaMarcador(1);
			noHaChocado=false;
		
		}
		else if (auxiliar instanceof Barra){
			//vamos a modificar el rebote de la bola con el cursor
			//para que no sea siempre igual
			
			//calculo la posici�n x del punto central de la bola
			double centroBola = getX() + getWidth()/2;
			if (centroBola > auxiliar.getX() + auxiliar.getWidth()/3 && 
				centroBola < auxiliar.getX() + 2 * auxiliar.getWidth()/3){
				yVelocidad = -0.75;
			}
			else {
				yVelocidad = -0.5;
			}
			noHaChocado = false;
		}
		return noHaChocado;

	}




}