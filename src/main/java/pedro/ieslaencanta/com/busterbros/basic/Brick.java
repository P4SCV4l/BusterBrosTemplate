/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import pedro.ieslaencanta.com.busterbros.Game;

/**
 *
 * @author DAWTarde
 */
public class Brick extends Element{
    private Rectangle2D original;

    public Brick() {
        super();
    }
    
    public Brick( double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    
    @Override
    public void paint(GraphicsContext gc){
        gc.drawImage(this.img,
                this.original.getMinX(),
                this.original.getMinY(),
                this.original.getWidth(),
                this.original.getHeight(),
                //Se pinta en el juego.
                this.rectangle.getMinX()*Game.SCALE,
                this.rectangle.getMinY()*Game.SCALE,
                this.rectangle.getWidth()*Game.SCALE,
                this.rectangle.getHeight()*Game.SCALE
        );
    }
    
    /**
     * @param original the original to set
     */
    public void setOriginal(Rectangle2D original) {
        this.original = original;
    }

    /**
     * @return the original
     */
    public Rectangle2D getOriginal() {
        return original;
    }
}
