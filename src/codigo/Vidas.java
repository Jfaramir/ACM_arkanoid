package codigo;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.GLabel;
import acm.graphics.GRect;

public class Vidas extends GRect{
	
	GLabel texto = new GLabel("");
	int puntosVida = 3;
	
	public Vidas(double width, double height) {
		super(width, height);
		setFilled(true);
		setFillColor(Color.WHITE);
		texto.setLabel("3");
		texto.setFont(new Font("Verdana",Font.BOLD, 18));
		
		
	}
	
	public void dibuja(Arkanoid _arkanoid){
		_arkanoid.add(this, _arkanoid.getWidth() - 30,getY());
		_arkanoid.add(texto, _arkanoid.getWidth() - 30,getY() + 80 );
		
		
	}
	
	public void actualizaVidas(int vida){
		puntosVida -= vida;
		texto.setLabel("" + puntosVida);
	}
	
	
}


