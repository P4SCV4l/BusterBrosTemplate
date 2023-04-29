/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import java.util.Optional;
import javafx.scene.canvas.GraphicsContext;
import pedro.ieslaencanta.com.busterbros.Game;

/**
 *
 * @author PC
 */
public class Bros extends ElementWithGravity{
//    private Weapon weapon;
    private static final float WIDTH = 30;
    private static final float HEIGHT = 32;
    private int frecuency;
    private State state;
    private boolean paused;
    private int state_counter;
    private int lifes;

    public Bros() {
        super();
    }
    
    public Bros(double gx, double gy) {
        super(gx, gy);
    }

    public Bros(double gx, double gy, double vx, double vy, double x, double y) {
        super(gx, gy, true, true, x, y, Bros.WIDTH, Bros.HEIGHT); 
    }

//    public ElementResizable shoot(){
//    }
    
//    public setWeapon(Weapon weapon){
//    }
    
    @Override
    public void update(){
        
    }
    
    @Override
    public void moveUp(double inc){
        super.moveUp(inc);
    }
    
    @Override
    public void moveDown(double inc){
        super.moveDown(inc);
    }
    
    @Override
    public void moveLeft(){
        super.moveLeft();
    }
    
    @Override
    public void moveRight(){
        super.moveRight();
    }
    
    public void setStopped(){

    }
    
    public void setPaused(){
        
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
    
    @Override
    public Optional<Collision> collision(Element e){
        return null; 
    }
    
    public void changeWeapon(){
        
    }

    /**
     * @return the WIDTH
     */
    public static float getWIDTH() {
        return WIDTH;
    }

    /**
     * @return the HEIGHT
     */
    public static float getHEIGHT() {
        return HEIGHT;
    }
}
