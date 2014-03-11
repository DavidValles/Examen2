/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package examen2;

/**
 *
 * @author Dago
 */
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Base {
    private int posX;
    private int posY;
    private ImageIcon icono;
    private Animacion anim;
  
    
    public Base(int posX, int posY,Image image, Animacion anim){ //constructor 
        this.posX=posX;
        this.posY=posY;
        icono = new ImageIcon(image);
        this.anim=anim;
        
    }
    
    
     public void setPosX(int posX) { //metodo para cambiar posicion en X
		this.posX = posX;
	}
	
	
	public int getPosX() { //metodo para obtener posicion en X
		return posX;
	}
	
	
	public void setPosY(int posY) { //metodo para cambiar posicion en Y
		this.posY = posY;
	}
	
	
	public int getPosY() {  //metodo para obtener posicion en Y
		return posY;
	}
    
    public void setImageIcon(ImageIcon icono){ //imagen que utilizara el objeto
        this.icono=icono;
    }
    
    public ImageIcon getImageIcon() { // imagen que utiliza el objeto
		return icono;
	}
    
     public int getAncho() { //ancho de la image
		return icono.getIconWidth();
	}
     
     public int getAlto() { // alto de la imagen
		return icono.getIconHeight();
	}
     	
        public Image getImagenI() {
		return icono.getImage();
	}
        
        
        public Rectangle getPerimetro(){ // toma el perimetro del objeto
		return new Rectangle(getPosX(),getPosY(),getAncho(),getAlto());
	}
        
        
        
        public boolean intersecta(int x, int y){ // para saber si el mouse esta dentro del planeta
		return getPerimetro().contains(x,y);
	}
        
        public boolean intersecta2(Base obj){ // para saber si colisiona con el objeto
            return getPerimetro().intersects(obj.getPerimetro());
        }
        
        public boolean chocaArriba(int x, int y){ // usada para checar si colisiona abajo del objeto
            
            return  getPerimetro().contains(x,y);
        }
        
        
        
   

    
}
