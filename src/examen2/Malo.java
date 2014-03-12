/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examen2;
import java.awt.Image;

/**
 *
 * @author Dago
 */
public class Malo extends Base{
    private int conteo; // variable de conteo
    private boolean entrada;// variable de entrada
    
    public Malo(int posX,int posY,Image image, Animacion anim, Boolean x){ //constructor
		super(posX,posY,image,anim);
               entrada=x;
    }

  
    public boolean getEntrada(){
        return entrada; // regresa el valor booleando de entrada
    }
    
    public int getConteo(){
        return conteo; // regresa el valor entero de conteo
    }
    
    public void setConteo(int conteo){
        this.conteo=conteo;
    } 
}
