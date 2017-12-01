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
	//un espacio del ancho del ladrillo para dejaar bonito el nivel 3
	int espacio = 40;
	//estas son las medidas del ladrillo
	int anchoLadrillo = 40;
	int altoLadrillo = 20;
	// el espacio para el menu
	static int espacioMenu = 190;
	// numero de ladrillos en el nivel 1
	int ladrillosNivel1 = 55;
	//declaramos el texto de victoria
	GLabel _gg = new GLabel ("HAS GANADO");
	//declaramos el texto de derrota
	GLabel gameOver = new GLabel ("HAS PERDIDO");
	// declaramos el texto de intentar otra vez
	GLabel intenta = new GLabel ("INTENTALO DE NUEVO");
	// declaramos el texto de nivel 1 conseguido
	GLabel lvl1 = new GLabel ("nivel 1 completado");
	// declaramos el texto de nivel 2 conseguido
	GLabel lvl2 = new GLabel ("nivel 2 completado");
	//esto es el marcador
	GLabel lvl3 = new GLabel ("nivel 3 completado");
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
			//para que se mueva
			pelota1.muevete(this);
			pause(0);
			// con esto te añade una vida al eliminar el ladrillo numero 50 si vas con 1 vida
			if (marcador.puntuacion == 50 && vidas.puntosVida <= 1){
				vidas.bonusVida(1);

			}
			//con esto pasamos al segundo nivel
			if (marcador.puntuacion == ladrillosNivel1){
				add(lvl1,410 , 300 );
				lvl1.setColor(Color.GREEN);
				lvl1.setFont(new Font ("verdana", Font.BOLD, 15));

				//dibujamos el nivel 2
				dibujaNivel02();

				//colocamos la pelota y hacemos q se mueva
				pelota1.setLocation(0, getHeight()* 0.60 - pelota1.getHeight());

				while(true){

					//asi hacemos que se mueva la pelota en el nivel 2
					pelota1.muevete(this);
					pause(0);
					//bonus por si llegas con 3 vidas
					if (marcador.puntuacion == 100 && vidas.puntosVida <= 2){
						vidas.bonusVida(1);

					}
					//si llegas con menos de 2 vidas saldra este mensaje para que intentes conseguir el bonus
					if (vidas.puntosVida < 2 && marcador.puntuacion == 110){
						//añadimos un lvl 2 completado
						add(lvl2,410 , 330 );
						lvl2.setColor(Color.GREEN);
						lvl2.setFont(new Font ("verdana", Font.BOLD, 15));
						//añadimos el cartelito de intentalo de nuevo para que intente llegar al bonus
						add(intenta,getWidth()/2 - 250,getHeight()/2);
						intenta.setColor(Color.BLACK);
						intenta.setFont(new Font ("verdana",Font.BOLD, 40));
						pelota1.setLocation(0, getHeight()*0.60 - pelota1.getHeight());
					}

					//añadimos un bonus de un nivel mas si llegas con mas de 2 vidas
					if (vidas.puntosVida >= 2 && marcador.puntuacion == 110){

						//añadimos un lvl 2 completado
						add(lvl2,410 , 330 );
						lvl2.setColor(Color.GREEN);
						lvl2.setFont(new Font ("verdana", Font.BOLD, 15));
						//dibujo el nivel 3
						dibujaNivel03();
						//coloco la pelota y hago q se mueva
						pelota1.setLocation(0, getHeight()* 0.60 - pelota1.getHeight());
						pelota1.muevete(this);
						pelota1.yVelocidad =-1; 
						//esto es para que se mueva la pelota
						while (true){
							pelota1.muevete(this);
							pause(2);
							//añadimos victoria
							if ( marcador.puntuacion == 201){
								//añadimos un lvl 2 completado
								add(lvl3,410 , 330 );
								lvl3.setColor(Color.GREEN);
								lvl3.setFont(new Font ("verdana", Font.BOLD, 15));
								//añadimos el cartelito de victoria
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
			//añadimos el cartelito de gameover
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
		for (int j=0; j<8; j++){
			for(int i=j; i<8; i++){

				Ladrillo miLadrillo = new Ladrillo(espacio+ anchoLadrillo*i - anchoLadrillo*j/2 , altoLadrillo* j + altoLadrillo  , anchoLadrillo, altoLadrillo, Color.BLACK);
				add(miLadrillo);
				pause(7);


			}
		}

	}




}