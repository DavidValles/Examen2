/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examen2;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author David
 */
public class Examen2 {
    
    public Examen2(){
    
    }
   
    public static void main(String[] args) throws IOException {
        Juego variable;
        variable = new Juego();
        variable.setVisible(true);
        variable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
