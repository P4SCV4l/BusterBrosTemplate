/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import javafx.geometry.Rectangle2D;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable;

/**
 *
 * @author DAWTarde
 */
public class ElementMovable extends ElementDynamic implements IMovable {
    private double vx;
    private double vy;
    
    public ElementMovable(double x, double y, double width, double height){
        super(x, y, width, height);
    }
    public ElementMovable(){
        super();
    }
    
    @Override
    public IMovable.BorderCollision IsInBorder(Rectangle2D border) {
        IMovable.BorderCollision collision=IMovable.BorderCollision.NONE;
        //Colisión por arriba.
        if(this.rectangle.getMinY()<border.getMinY()){
            collision=IMovable.BorderCollision.TOP;
        }
        //Colisión por abajo.
        if(this.rectangle.getMaxY()>border.getMaxY()){
            collision=IMovable.BorderCollision.DOWN;
        }
        //Colisión por la izquierda.
        if(this.rectangle.getMinX()<border.getMinX()){
            collision=IMovable.BorderCollision.LEFT;
        }
        //Colisión por la derecha.
        if(this.rectangle.getMaxX()>border.getMaxX()){
            collision=IMovable.BorderCollision.RIGHT;
        }
        return collision;
    }
    
    @Override
    public void move(double x, double y) {
        this.rectangle= new Rectangle2D(
                   this.rectangle.getMinX()+x,
                   this.rectangle.getMinY()+y,
                this.rectangle.getWidth(),
                this.rectangle.getHeight());
    }
    
    @Override
    public void move(){
        this.move(this.vx,this.vy);
    }

    @Override
    public void moveLeft() {
        this.move(-this.vx,0);
    }
    
    @Override
    public void moveLeft(double inc) {
        this.move(-inc,0);
    }

    @Override
    public void moveRight() {
        this.move(+this.vx,0);
    }

    @Override
    public void moveRight(double inc) {
        this.move(+inc,0);
    }

    @Override
    public void moveUp() {
        this.move(0,-this.vy);
    }

    @Override
    public void moveUp(double inc) {
        this.move(0,-inc);
    }

    @Override
    public void moveDown() {
        this.move(0,+this.vy);    
    }

    @Override
    public void moveDown(double inc) {
        this.move(0,+inc);    
    }

    /**
     * @return the vx
     */
    public double getVx() {
        return vx;
    }

    /**
     * @param vx the vx to set
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * @return the vy
     */
    public double getVy() {
        return vy;
    }

    /**
     * @param vy the vy to set
     */
    public void setVy(double vy) {
        this.vy = vy;
    }

    
}
