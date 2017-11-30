package codigo;
//importamos todo lo necesario
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import acm.graphics.*;
/*
 *Autor:Juan Pablo
 *
 *El arkanoid orientado a objetos
 */



public class Arkanoid extends acm.program.GraphicsProgram{
	//declaramos la pelota 
	Pelota pelota1 = new Pelota(7, Color.BLUE);
	
// declaramos la barra
	Barra barra1 = new Barra(700, 15, Color.RED);
	//estas son las medidas del ladrillo
	int anchoLadrillo = 40;
	int altoLadrillo = 20;
	// el espacio para el menu
	static int espacioMenu = 190;
	// numero de ladrillos en el nivel 1
	int ladrillosNivel1 = 55;
	//marcamos el texto de victoria
	GLabel _gg = new GLabel ("HAS GANADO");
	//marcamos el texto de derrota
	GLabel gameOver = new GLabel ("HAS PERDIDO");
	//esto es el marcador
	Marcador marcador = new Marcador(60, 40);
	//Esto hace que salga el numero de vidas
	Vidas vidas = new Vidas (60,40);

	public void init(){
		//añadimos el listener del raton 
		addMouseListeners();
		//el tamaño de la pantalla
		setSize(600,600);
		//declaramos la pared q separa el menu de el final de la pantalla
		GRect lateral = new GRect (3,getHeight());
		lateral.setFilled(true);
		add(lateral, getWidth() - espacioMenu - lateral.getWidth() - pelota1.getWidth(), 0);
		//añadimos la pelota 
		add (pelota1, 0, getHeight()*0.60 - pelota1.getHeight());
		//añadimos la barra
		add (barra1, 0, getHeight()*0.80);	



	}

	public void run(){
		//dibujamos el nivel 1
		dibujaNivel01();
		//añadimos el marcador y las vidas
		marcador.dibuja(this);
		add(marcador.texto, 520, 20);
		vidas.dibuja(this);
		//con esto dibujamos el nivel 2
		


		while (true){
			//pa que se mueva
			pelota1.muevete(this);
			pause(0);
			
			//con esto pasamos al segundo nivel
			if (marcador.puntuacion == ladrillosNivel1){
				//dibujamos el nivel 2
				dibujaNivel02();
				//colocamos la pelota y hacemos q se mueva
				pelota1.setLocation(0, getHeight()* 0.60 - pelota1.getHeight());
				
				while(true){

					//asi hacemos que se mueva la pelota en el nivel 2
					pelota1.muevete(this);
					pause(2);
					//añadimos victoria
					if ( marcador.puntuacion == 100){
						add (_gg,getWidth()/2 -200,getHeight()/2);
						_gg.setColor(Color.BLACK);
						_gg.setFont(new Font ("verdana",Font.BOLD, 50));
						pelota1.setLocation(0, getHeight()*0.60- pelota1.getHeight());
					}
				}	   

			}

			//devolvemos la pelota 
			if (pelota1.getY() >  getHeight() && vidas.puntosVida < 0){
				
				
				pelota1.setLocation(0, getHeight()* 0.60 - pelota1.getHeight());
				pelota1.muevete(this);

			}
			//añadimos gameover
			if(vidas.puntosVida == 0){
				add(gameOver,getWidth()/2 - 200,getHeight()/2);
				gameOver.setColor(Color.BLACK);
				gameOver.setFont(new Font ("verdana",Font.BOLD, 50));
				pelota1.setLocation(0, getHeight()* 0.60 - pelota1.getHeight());
			}
		}

	}



	public void mouseMoved(MouseEvent evento){
		barra1.mueveBarra(evento.getX(), getWidth());

	}

	private void dibujaNivel01(){
		for (int j=0; j<10; j++){
			for(int i=j; i<10; i++){
				Ladrillo miLadrillo = new Ladrillo(anchoLadrillo*i - anchoLadrillo*j/2, altoLadrillo*j + altoLadrillo, anchoLadrillo, altoLadrillo, Color.GRAY);
				add(miLadrillo);
				pause(7);
			}
		}
	}

	private void dibujaNivel02(){
		for (int j=0; j<9; j++){
			for(int i=j; i<9; i++){
				Ladrillo miLadrillo = new Ladrillo(anchoLadrillo*i - anchoLadrillo*j, altoLadrillo*j , anchoLadrillo, altoLadrillo, Color.GRAY);
				add(miLadrillo);
				pause(7);
			}
		}
	}

	private GLabel  gameOver(){


		GLabel _gameOver = new GLabel ("HAS PERDIDO");
		_gameOver.setColor(Color.BLACK);
		_gameOver.setFont(new Font("verdana",Font.BOLD,50));
		return _gameOver;
	}




}
