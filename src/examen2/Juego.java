



      /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen2;

import javax.swing.JFrame;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.LinkedList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.io.File;
import java.io.FileWriter;

/**
 * /**
 *
 * @author Dago
 */
//Flappy Bird 2
public class Juego extends JFrame implements Runnable, KeyListener {

    public Juego() throws IOException {
        init();
        start();
    }

    private LinkedList<Malo> lista; // Lista
    private double score;   //int para score
    private Bueno bird;     // Objeto principal bueno
    private int constY;     // constante Y
    private Graphics dbg;               //Objeto tipo Graphics
    private Image dbImage;              //Imagen para el doblebuffer 
    private boolean pausa;              // boolean para pausa
    private Animacion animB;            // animacion bird
    private Animacion animP;            // animacion piso
    private Animacion animM1;           // animacion Muro1
    private Animacion animM2;           // animacion Muro2
    private Image bg; //imagen del background
    private Image go; //imagen de gameover
    private String st;  // string que contiene el score
    private int salto;  // salto que mueve al bird
    private boolean fuerza;
    private Malo muro;  // objeto malo
    private Malo piso; // objeto malo
    private SoundClip golpe;    // sonido cuando muere
    private SoundClip sigue;    // sonido cuando pasa por un muro
    private int altura;         // variable de altura
    private int ancho;          // variable de ancho
    private int inicio;         // posicion inicial
    private int dif;            // diferencia;
    private boolean entrada;
    private int cont;
    private boolean start;
    private boolean gameover;
    private SoundClip pasa;
    private int contnivel;
    private int velocidad;


    //Variables de control de tiempo de la animación
    private long tiempoActual;
    private long tiempoInicial;

    public void init() throws IOException {

        this.setSize(700, 400); // tamano del applet
        addKeyListener(this); //utilizada para los metodos de KeyBoard
        Image bird1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/bird1.png"));
        Image bird2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/bird2.png"));
        Image bird3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/bird3.png"));
        Image piso1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/piso1.png"));
        Image piso2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/piso2.png"));
        Image piso3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/piso3.png"));
        Image muroA = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/muroA.png"));
        Image muroB = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/muroB.png"));

        pasa = new SoundClip("sounds/pasa.wav");

        animB = new Animacion();                //crea animacion 
        animB.sumaCuadro(bird1, 100);
        animB.sumaCuadro(bird2, 100);
        animB.sumaCuadro(bird3, 100);

        animP = new Animacion();
        animP.sumaCuadro(piso1, 100);
        animP.sumaCuadro(piso2, 100);
        animP.sumaCuadro(piso3, 100);

        animM1 = new Animacion();
        animM1.sumaCuadro(muroA, 100);

        animM2 = new Animacion();
        animM2.sumaCuadro(muroB, 100);

        cont = 2;
        score = 0;
        start = false;
        gameover = false;
        contnivel=1;
        velocidad=10;

        bird = new Bueno(300, 180, bird1, animB);

        URL uURL = this.getClass().getResource("images/bgF.png");
        bg = Toolkit.getDefaultToolkit().getImage(uURL);

        URL gURL = this.getClass().getResource("images/bird gameover.jpg");
        go = Toolkit.getDefaultToolkit().getImage(gURL);

        fuerza = true;

        sigue = new SoundClip("sounds/golpe.wav");
        golpe = new SoundClip("sounds/pasa.wav");
        inicio = 600;
        entrada = true;

        muro = new Malo(1000, 1000, muroB, animM2, entrada);
        dif = muro.getAlto();

        lista = new LinkedList<Malo>();
        for (int j = 0; j < 3; j++) {

            altura = ((int) (Math.random() * 100)) + 155;       //random altura
            ancho = this.getHeight() - altura - 400;            // random anchura(diferencia en x)

            muro = new Malo(inicio, (altura - dif), muroB, animM2, entrada);
            lista.add(muro);    // se crea muro de arriba y se agrega
            entrada = !entrada;
            muro = new Malo(inicio, altura + 100, muroA, animM1, entrada);
            lista.add(muro); // se crea muro de abajo y se agrega
            entrada = !entrada;

            inicio = inicio + 250; // inicio se recorre

        }

    }

    public void start() {
        //Crea el thread
        Thread hilo = new Thread(this);
        //Inicializa el thread
        hilo.start();
    }

    public void stop() {

    }

    public void destroy() {

    }

    public void run() {
        //Guarda el tiempo actual del sistema
        tiempoActual = System.currentTimeMillis();

        while (true) {

            //sigue mientras pausa sea true
            try {
                //Actualiza la animación
                actualiza();
            } catch (IOException ex) {
                Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Checa colision
            checaColision();

            //Manda a llamar al método paint() para mostrar en pantalla la animación
            repaint();

            //Hace una pausa de 200 milisegundos
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
            }

        }

    }

    public void actualiza() throws IOException {

        //Determina el tiempo que ha transcurrido desde que el Applet inicio su ejecución
        long tiempoTranscurrido
                = System.currentTimeMillis() - tiempoActual;

        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;

        //Actualiza la animación en base al tiempo transcurrido
        animB.actualiza(tiempoTranscurrido);
        if (start) { // espera inicio
            if (fuerza) {
                salto = 60;
            } else {
                salto = 0;
            }

            bird.setPosY(bird.getPosY() + 20 - salto); // hace el salto del bird
            fuerza = false;

            for (Malo muro : lista) {
                
                muro.setPosX(muro.getPosX() - velocidad); // movimiento de los muros

                if (muro.getPosX() + muro.getAncho() < 0) {

                    muro.setPosX(630);

                    if (muro.getEntrada()) { // se regresan los muros al inicio, pero con altura random
                        altura = ((int) (Math.random() * 100)) + 155;
                    } else {
                        altura = ((int) (Math.random() * 100)) + 155;

                    }

                }
                if(muro.getPosX()==bird.getPosX()){ // si el bird pasa 1 muro
                    bird.setScore(bird.getScore() +0.5);
                    pasa.play();
                }
                
            }

        }
        if(bird.getScore()>500){ // nivel2
            contnivel=2;
        }
        
          if(bird.getScore()>1000){ //nivel3
            contnivel=3;
        }

    }

    public void checaColision() {

        for (int i = 0; i < lista.size(); i++) {
            Malo actualA = (Malo) (lista.get(i));
            Malo actualB = (Malo) (lista.get(i));
            if (actualA.getPosX() + actualA.getAncho() < 0) {
                int y = 0;
            }

            if ((actualA.intersecta2(bird) || actualB.intersecta2(bird))) {
                start = false;
                gameover = true;
            }
        }

    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {//Presiono flecha arriba
            fuerza = true;
        }

        if (!start) {
            start = true;

        }

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }

    public void paint(Graphics g) {

        // Inicializan el DoubleBuffer
        if (dbImage == null) {
            dbImage = createImage(this.getSize().width, this.getSize().height);
            dbg = dbImage.getGraphics();
        }

        // Actualiza la imagen de fondo.
        dbg.setColor(getBackground());
        dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

        // Actualiza el Foreground.
        dbg.setColor(getForeground());
        paint1(dbg);

        // Dibuja la imagen actualizada
        g.drawImage(dbImage, 0, 0, this);

    }

    public void paint1(Graphics g) {
        g.drawImage(bg, 0, 0, this);
        g.drawImage(animB.getImagen(), bird.getPosX(), bird.getPosY(), this);
        for (Malo muro : lista) {
            g.drawImage(muro.getImagenI(), muro.getPosX(), muro.getPosY(), this);
            g.drawString("" + bird.getScore(), this.getWidth() / 8, 50);
            g.drawString("Score:", 40, 50);
            g.setColor(Color.WHITE);
            g.drawString("Nivel:" + contnivel, 20, 300);

            if (gameover) {
                g.drawImage(go, 0, 0, this);

            }
        }
    }
}