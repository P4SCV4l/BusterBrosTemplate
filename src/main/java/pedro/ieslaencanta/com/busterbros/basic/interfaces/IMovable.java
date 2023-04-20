/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic.interfaces;

import javafx.geometry.Rectangle2D;

/**
 *
 * @author DAWTarde
 */
public interface IMovable {
    public enum BorderCollision{
        LEFT,
        RIGHT,
        TOP,
        DOWN,
        NONE
    }
    public BorderCollision IsInBorder(Rectangle2D border);
    public void move(double x, double y);
    
    public void move();
    
    public void moveLeft();
    
    public void moveLeft(double inc);
    
    public void moveRight();
    
    public void moveRight(double inc);
    
    public void moveUp();
    
    public void moveUp(double inc);
    
    public void moveDown();
    
    public void moveDown(double inc);
    
    
}
