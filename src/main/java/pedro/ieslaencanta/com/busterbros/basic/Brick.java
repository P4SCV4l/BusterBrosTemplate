/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author DAWTarde
 */
public class Brick extends Element{
    private Rectangle2D original;

    public Brick() {
        super();
    }
    
    public Brick(Rectangle2D original, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.original = original;
    }
    
    @Override
    public void paint(GraphicsContext gc){  
    }
    
    /**
     * @param original the original to set
     */
    public void setOriginal(Rectangle2D original) {
        this.original = original;
    }   
}
