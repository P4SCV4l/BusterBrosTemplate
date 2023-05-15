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
 * @author PC
 */
public class Hook extends ElementResizable {
    private static final int WIDTH=10;
    private static int VW;
    private static int VY=2;
    private static final int[] ARROWS=new int[1];

    public Hook() {
        super();
    }
    
    public Hook(double x, double y){
        super(0, 2, x, y, Hook.WIDTH, 2);
    }

    
    
    @Override
    public void resizeHeigth() {
        this.rectangle= new Rectangle2D(this.rectangle.getMinX(),
        this.rectangle.getMinY()-VY,
         this.rectangle.getWidth(),
         this.rectangle.getHeight()+ VY);
    }

    @Override
    public void paint(GraphicsContext gc) {
        gc.fillRect(this.getRectangle().getMinX() * Game.SCALE, this.getRectangle().getMinY() * Game.SCALE, 
        this.getRectangle().getWidth() * 
        Game.SCALE, this.getRectangle().getHeight() * Game.SCALE);
        if (this.isDebug()) {
            this.debug(gc);
        }
    }

    
    public boolean ajustTop(Rectangle2D game_zone){
        boolean a;
        a=false;
        if (this.getRectangle().getMinY() < game_zone.getMinY()) {
            a=true;
        }
        return a;
    }


    @Override
    public void update() {
        this.resizeHeigth();
        
    }
}
