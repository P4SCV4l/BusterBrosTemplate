/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;
import java.util.Optional;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Separator;
import javafx.scene.shape.Rectangle;
import pedro.ieslaencanta.com.busterbros.Game;
import pedro.ieslaencanta.com.busterbros.Resources;
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
    private BallType type;
    private BallColor color;
    private BallType next;
    
    public Ball(){
        super();
    }
    
    public Ball(double gx, double gy, double vx, double vy, double x, double y, BallType type, BallColor color){
        super(gx, gy, true, true, x, y, type.getWidth(), type.getHeight());
        this.original_vx = vx;
        this.original_vy = vy;
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
        // Resources r = Resources.getInstance();
        // gc.drawImage(r.getImage("ballons"),
        // //inicio de la posicion
        // this.getType().getStartx(),
        // this.getColor().getStarty(),
        // this.getType().getWidth(),
        // this.getType().getHeight(),
        // //dibujar en el lienzo
        // (this.getRectangle().getMinX()) * Game.SCALE,
        // (this.getRectangle().getMinY()) * Game.SCALE,
        // this.getType().getWidth() * Game.SCALE,
        // this.getType().getHeight() * Game.SCALE);
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
    
    public void reset(){
        this.setVy(this.original_vy);
    }
    
    public Optional<Collision> collisionWithBrick(Element e) {
        Optional<Collision> c= super.collision(e);
        if(c.isPresent()){
            System.out.println("Element e || MaxX: " + e.getRectangle().getMaxX() + 
            " MinX:" + e.getRectangle().getMinX() + " MaxY: " + e.getRectangle().getMaxY() + " MinY: " + e.getRectangle().getMinY());
            // System.out.println("Element c || MaxX:" + e.getRectangle().getMaxX() + 
            // "MinX:" + e.getRectangle().getMinX() + "MaxY:" + e.getRectangle().getMaxY() + "MinY:" + e.getRectangle().getMinY());
            double dx=this.evalCollisionX(e);
            double dy=this.evalCollisionY(e);
            System.out.println("dx:" + dx);
            System.out.println("dy:" + dy);
            c.get().setSeparator(new Point2D(dx,dy));
            System.out.println(c.get().getSeparator());
        }
       // e.getRectangle().
       // e.getRectangle().Separator(Point2D(dx,dy));
        // var inter = intersection(getRectangle(), brick.getRectangle());
        // if (inter.getWidth() < 0.01 && inter.getHeight() < 0.01) {
        //     return Optional.empty();
        // }
        

         return c;
        
    }
    public double evalCollisionX(Element e){
        double distancia=0;
        Rectangle2D x=new Rectangle2D(this.rectangle.getMinX(),
            this.rectangle.getMinY()-this.getVy(), 
            this.rectangle.getWidth(), 
            this.rectangle.getHeight());
            if(x.intersects(e.getRectangle())){
              if(x.getMinX()<e.getRectangle().getMinX()){
                distancia=e.getRectangle().getMinX()-x.getMaxX();
              }else{
                distancia=e.getRectangle().getMaxX()-x.getMinX();
              }
            }
      return distancia;
    } 
    public double evalCollisionY(Element e){
        double distancia=0;
        Rectangle2D x=new Rectangle2D(this.rectangle.getMinX()-this.getVx(),
            this.rectangle.getMinY(), 
            this.rectangle.getWidth(), 
            this.rectangle.getHeight());
            if(x.intersects(e.getRectangle())){
                if(x.getMinY()<e.getRectangle().getMinY()){
                  distancia=e.getRectangle().getMinY()-x.getMaxY();
                }else{
                  distancia=e.getRectangle().getMaxY()-x.getMinY();
                }
              }
        return distancia;
    } 

    // Rectangle2D intersection(Rectangle2D rect1, Rectangle2D rect2)
    // {
    //     double newX = Math.max(rect1.getMinX(), rect2.getMinX());
    //     double newY = Math.max(rect1.getMinY(), rect2.getMinY());

    //     double newWidth = Math.min(rect1.getMinX() + rect1.getWidth(), rect2.getMinX() + rect2.getWidth()) - newX;
    //     double newHeight = Math.min(rect1.getMinY() + rect1.getHeight(), rect2.getMinY() + rect2.getHeight()) - newY;

    //     if (newWidth <= 0d || newHeight <= 0d) return null;

    //     return new Rectangle2D(newX, newY, newWidth, newHeight);
    // }
}
