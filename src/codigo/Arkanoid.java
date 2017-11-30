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
	Barra barra1 = new Barra(70, 15, Color.RED);
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
		//a�adimos el listener del raton 
		addMouseListeners();
		//el tama�o de la pantalla
		setSize(600,600);
		//declaramos la pared q separa el menu de el final de la pantalla
		GRect lateral = new GRect (3,getHeight());
		lateral.setFilled(true);
		add(lateral, getWidth() - espacioMenu - lateral.getWidth() - pelota1.getWidth(), 0);
		//a�adimos la pelota 
		add (pelota1, 0, getHeight()*0.60 - pelota1.getHeight());
		//a�adimos la barra
		add (barra1, 0, getHeight()*0.80);	



	}

	public void run(){
		//dibujamos el nivel 1
		dibujaNivel01();
		//a�adimos el marcador y las vidas
		marcador.dibuja(this);
		add(marcador.texto, 520, 20);
		vidas.dibuja(this);
		//con esto dibujamos el nivel 2



		while (true){
			//pa que se mueva
			pelota1.muevete(this);
			pause(2);

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
					//a�adimos un bonus de un nivel mas si llegas con mas de 2 vidas
					if (vidas.puntosVida >= 2 && marcador.puntuacion == 110){
						//dibujo el nivel 3
						dibujaNivel03();
						//coloco la pelota y hago q se mueva
						pelota1.setLocation(0, getHeight()* 0.60 - pelota1.getHeight());
						pelota1.muevete(this);
						pelota1.yVelocidad =-1; 
						while (true){
							pelota1.muevete(this);
							pause(2);
							//a�adimos victoria
							if ( marcador.puntuacion == 220){
								add (_gg,getWidth()/2 -200,getHeight()/2);
								_gg.setColor(Color.BLACK);
								_gg.setFont(new Font ("verdana",Font.BOLD, 50));
								pelota1.setLocation(0, getHeight()*0.60- pelota1.getHeight());
							}
						}
					}

				}	   

			}

			//devolvemos la pelota cuando la pierdes
			if (pelota1.getY() >  getHeight() && vidas.puntosVida < 0){


				pelota1.setLocation(0, getHeight()* 0.60 - pelota1.getHeight());
				pelota1.muevete(this);
				pause(2);

			}
			//a�adimos gameover
			if(vidas.puntosVida == 0){
				add(gameOver,getWidth()/2 - 200,getHeight()/2);
				gameOver.setColor(Color.BLACK);
				gameOver.setFont(new Font ("verdana",Font.BOLD, 50));
				pelota1.setLocation(0, getHeight()* 0.60 - pelota1.getHeight());
			}
		}

	}


	//para que la barra siga al raton
	public void mouseMoved(MouseEvent evento){
		barra1.mueveBarra(evento.getX(), getWidth());

	}
	//codigo para dibujar el nivel 1
	private void dibujaNivel01(){
		for (int j=0; j<10; j++){
			for(int i=j; i<10; i++){
				Ladrillo miLadrillo = new Ladrillo(anchoLadrillo*i - anchoLadrillo*j/2, altoLadrillo*j + altoLadrillo, anchoLadrillo, altoLadrillo, Color.GRAY);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	//codigo para dibujar el nivel 2
	private void dibujaNivel02(){
		for (int j=0; j<10; j++){
			for(int i=j; i<10; i++){
				Ladrillo miLadrillo = new Ladrillo(anchoLadrillo*i - anchoLadrillo*j, altoLadrillo*j , anchoLadrillo, altoLadrillo, Color.GRAY);
				add(miLadrillo);
				pause(7);
			}
		}
	}
	//codigo para dibujar el nivel 3
	private void dibujaNivel03(){
		for(int k=0; k<10; k++){
			for(int l=k;l<10; l++){
				Ladrillo miLadrillo = new Ladrillo(anchoLadrillo*l - anchoLadrillo*k/2 , altoLadrillo* k + altoLadrillo  , anchoLadrillo, altoLadrillo, Color.GRAY);
				add(miLadrillo);
				pause(0);
			}
		}
		//otra piramide para duplicar los ladrillos y tener ladrillos lvl 2
		for (int j=0; j<7; j++){
			for(int i=j; i<7; i++){

				Ladrillo miLadrillo = new Ladrillo(anchoLadrillo*i - anchoLadrillo*j/2 , altoLadrillo* j + altoLadrillo  , anchoLadrillo, altoLadrillo, Color.BLACK);
				add(miLadrillo);
				pause(7);


			}
		}

	}




}
