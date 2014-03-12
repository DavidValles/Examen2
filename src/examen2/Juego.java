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
//Flappy Bird
public class Juego extends JFrame implements Runnable, KeyListener{
    public Juego() throws IOException {
        init();
        start();
    }
    
    private LinkedList<Malo> lista; // Lista
    private int score;
    private Bueno bird;
    private int constY;
    private Graphics dbg;               //Objeto tipo Graphics
    private Image dbImage;              //Imagen para el doblebuffer 
    private boolean pausa;              // boolean para pausa
    private Animacion animB;
    private Animacion animP;
    private Animacion animM1;
    private Animacion animM2;
    private Image bg; //imagen del background
    private String st;
    private int salto;
    private boolean fuerza;
    private Malo muro;
    private Malo piso;
    private SoundClip golpe;
    private SoundClip sigue;
    private int altura;
    private int ancho;
    private int inicio;
    
    
    
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
         
         
         animB = new Animacion();
         animB.sumaCuadro(bird1,100);
         animB.sumaCuadro(bird2,100);
         animB.sumaCuadro(bird3,100);
         
          animP = new Animacion();
         animP.sumaCuadro(piso1,100);
         animP.sumaCuadro(piso2,100);
         animP.sumaCuadro(piso3,100);
         
         animM1 = new Animacion();
         animM1.sumaCuadro(muroA,100);
         
         animM2 = new Animacion();
         animM2.sumaCuadro(muroB,100);
         
         
         bird = new Bueno(300, 180, bird1, animB);
         
         URL uURL = this.getClass().getResource("images/bgF.png");
         bg = Toolkit.getDefaultToolkit().getImage(uURL);
         fuerza=true;
         
         sigue = new SoundClip("sounds/golpe.wav");
         golpe = new SoundClip("sounds/pasa.wav");
         inicio=600;
         
         lista= new LinkedList<Malo>();
             for(int j=0; j<2; j++){
                 
                 altura=((int) (Math.random() * 400 + 200));
                 ancho=((int) (Math.random() * 200 + 100));
                 
                 muro = new Malo(inicio,altura,muroA,animM1);
                 lista.add(muro);
                 muro = new Malo(inicio,altura+70,muroB,animM1);
                 lista.add(muro);
              
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
         
         if(fuerza){salto=60;}
         else{salto=0;}
         
         bird.setPosY(bird.getPosY()+20-salto);
         fuerza=false;
         
         for(Malo muro:lista){
              
             muro.setPosX(muro.getPosX()-20);
              
           }
         
        
    }

    public void checaColision() {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {//Presiono flecha arriba
            fuerza=true;
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
           for(Malo muro:lista){
               g.drawImage(muro.getImagenI(), muro.getPosX(), muro.getPosY(), this);
              
           }
    }
}



      