package codigo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;

/*
 *Autor:Juan Pablo
 *
 *El arkanoid orientado a objetos
 */

import acm.graphics.*;

public class Arkanoid extends acm.program.GraphicsProgram{
	Pelota pelota1 = new Pelota(7, Color.BLUE);
	
	Barra barra1 = new Barra(150, 15, Color.RED);
	int anchoLadrillo = 35;
	int altoLadrillo = 15;
	int espacioMenu = 200;
	int ladrillosNivel1 = 121;
	
	//esto es el marcador
	
	Marcador marcador = new Marcador(60, 40);
	Vidas vidas = new Vidas (60,40);
			
	public void init(){
		addMouseListeners();
		setSize(600,600);
		GRect lateral = new GRect (3,getHeight());
		lateral.setFilled(true);
		add(lateral, getWidth() - espacioMenu - lateral.getWidth() - pelota1.getWidth(), 0);
		
		 add (pelota1, 0, getHeight()*0.60 - pelota1.getHeight());
	
		add (barra1, 0, getHeight()*0.80);	
	
		
		
	}
	
	public void run(){
		dibujaNivel01();
		marcador.dibuja(this);
		add(marcador.texto, 520, 20);
		vidas.dibuja(this);
	
		
		while (true){
			pelota1.muevete(this);
		//LOLO RANDOM
			pause(2);
			if (pelota1.getY() >  getHeight()){
				for (int i=0; i<3; i++){
					setLocation(getHeight()* 0.60 - pelota1.getHeight());
				}
				add(gameOver(),getWidth()/2 - 200,getHeight()/2);
			
			}
		}
		
	}
	
	

	public void mouseMoved(MouseEvent evento){
		barra1.mueveBarra(evento.getX(), getWidth());
		
	}
	
	private void dibujaNivel02(){
		for (int j=0; j<11; j++){
			for(int i=j; i<11; i++){
				Ladrillo miLadrillo = new Ladrillo(anchoLadrillo*i - anchoLadrillo*j/2, altoLadrillo*j + altoLadrillo, anchoLadrillo, altoLadrillo, Color.GRAY);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	private void dibujaNivel01(){
		for (int j=0; j<11; j++){
			for(int i=0; i<11; i++){
				Ladrillo miLadrillo = new Ladrillo(anchoLadrillo*i , altoLadrillo*j , anchoLadrillo, altoLadrillo, Color.GRAY);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	private GLabel  gameOver(){
		this.vidas.actualizaVidas(1);
		
		GLabel _gameOver = new GLabel ("SOLO " + marcador.texto.getLabel() + "??");
		_gameOver.setColor(Color.BLACK);
		_gameOver.setFont(new Font("verdana",Font.BOLD,50));
		return _gameOver;
	}
	
}
