/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import pedro.ieslaencanta.com.busterbros.Game;
import  pedro.ieslaencanta.com.busterbros.basic.BallColor;
import  pedro.ieslaencanta.com.busterbros.basic.BallType;
import  pedro.ieslaencanta.com.busterbros.basic.Brick;

/**
 *
 * @author PC
 */
public class Ball extends ElementWithGravity {
    private double original_vx;
    private double original_vy;
    private double x;
    private double y;
    private BallType type;
    private BallColor color;
    private BallType next;
    
    public Ball(){
        super();
    }
    
    public Ball(double gx, double gy, double vx, double vy, double x, double y, BallType type, BallColor color){
        super(gx, gy);
        this.x = x;
        this.y = y;
        this.original_vx = original_vx;
        this.original_vy = original_vy;
        this.type = type;
        this.color = color;
    }
    
    public void update(Rectangle2D game_zone){
        
    }
    
    public Ball[] blow(){
        return null;
    }
    
    @Override
    public void paint(GraphicsContext gc) {
//        gc.setFill(this.color);
        //se tendrá que sustituro por img
        gc.fillRect(this.getRectangle().getMinX() * Game.SCALE, this.getRectangle().getMinY() * Game.SCALE, this.getRectangle().getWidth() * Game.SCALE, this.getRectangle().getHeight() * Game.SCALE);
        if (this.isDebug()) {

            this.debug(gc);
        }
    }
    
    public void evalCollision(Brick brick) {
        
    }

    /**
     * @return the type
     */
    public BallType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(BallType type) {
        this.type = type;
    }

    /**
     * @return the color
     */
    public BallColor getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(BallColor color) {
        this.color = color;
    }
    
    
}
