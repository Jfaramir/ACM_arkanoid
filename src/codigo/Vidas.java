package codigo;
/*
 * Autor: Juan Pablo Carpio 
 * esta pagina es para las vidas
 */
import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class Vidas extends GRect{

	GLabel texto = new GLabel("");

	int puntosVida = 3;

	//añadimos las caracteristicas de vida
	public Vidas(double width, double height) {
		super(width, height);
		setFilled(true);
		setFillColor(Color.WHITE);
		texto.setLabel("" + puntosVida);
		texto.setFont(new Font("Verdana",Font.BOLD, 18));


	}
	//para que se dibuje en el arkanoid
	public void dibuja(Arkanoid _arkanoid){

		_arkanoid.add(texto, _arkanoid.getWidth() - 100,getY() + 80 );


	}
	//asi se actualiza
	public void actualizaVidas(int vida){
		puntosVida -= vida;
		texto.setLabel("" + puntosVida);


	}



}

