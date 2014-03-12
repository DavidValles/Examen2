/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examen2;

import java.awt.Image;

public class Bueno extends Base {
   private static double score;
    
    public Bueno(int posX,int posY,Image image,Animacion anim){ //constructor
		super(posX,posY,image,anim);
                
   
        }
         public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return this.score;
                
	}
    }



