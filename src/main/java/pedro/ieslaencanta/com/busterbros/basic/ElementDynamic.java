/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import java.util.Optional;
import javafx.geometry.Rectangle2D;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.ICollidable;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.IState;

/**
 *
 * @author PC
 */
public class ElementDynamic extends Element implements ICollidable, IState{
    private State state;

    public ElementDynamic() {
        super();
    }

    public ElementDynamic(double x, double y, double width, double height) {
        super(x, y, width, height);
    }
    
   
    @Override
    public Optional<Collision> collision(Element e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void restartLastX(){
    }
    
    public void restartLastY(){
    }
    
    public void restart(){
    }
    
    public void updatelast(double ix, double iy){
    }

    public void borderCollision(Rectangle2D rectangle){
    }
    
    @Override
    public void getState() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setState(State s) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void update(){
    }
}
