/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examen2;

import java.awt.Image;

public class Bueno extends Base {
   private static int score;
    
    public Bueno(int posX,int posY,Image image,Animacion anim){ //constructor
		super(posX,posY,image,anim);
                
   
        }
         public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
                
	}
    }



