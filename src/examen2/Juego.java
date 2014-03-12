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

    public void init() {

    }

    public void start() {
        
    }

    public void stop() {

    }

    public void destroy() {

    }

    public void run() {

        //Ciclo principal del Applet. Actualiza y despliega en pantalla la animación hasta que el Applet sea cerrado
        //sigue mientras pausa sea true
        //Actualiza la animación
        actualiza();

        //Checa colision
        checaColision();

        

        //Hace una pausa de 200 milisegundos
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
        }

    }

    public void actualiza() {

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

    }

    public void paint1(Graphics g) {

    }
}



      