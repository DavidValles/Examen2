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
    
    private Bueno bird;
    private int constY;
    private Graphics dbg;               //Objeto tipo Graphics
    private Image dbImage;              //Imagen para el doblebuffer 
    private boolean pausa;              // boolean para pausa
    private Animacion animB;
    private Image bg; //imagen del background
    private String st;
    
     //Variables de control de tiempo de la animación
        private long tiempoActual;
        private long tiempoInicial;

    public void init() throws IOException {
        
        this.setSize(700, 600); // tamano del applet
        addKeyListener(this); //utilizada para los metodos de KeyBoard
         Image bird1 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/bird1.png"));
         Image bird2 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/bird2.png"));
         Image bird3 = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/bird3.png"));
         
         animB = new Animacion();
         animB.sumaCuadro(bird1,100);
         animB.sumaCuadro(bird2,100);
         animB.sumaCuadro(bird3,100);
         
         bird = new Bueno(300, 275, bird1, animB);
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
        
    }

    public void checaColision() {

    }

    public void keyPressed(KeyEvent e) {

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
            g.drawImage(animB.getImagen(), bird.getPosX(), bird.getPosY(), this);
    }
}



      