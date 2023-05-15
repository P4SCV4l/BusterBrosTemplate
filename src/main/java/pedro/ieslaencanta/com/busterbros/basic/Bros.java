/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import java.util.Optional;
import javafx.scene.canvas.GraphicsContext;
import pedro.ieslaencanta.com.busterbros.Game;
import pedro.ieslaencanta.com.busterbros.Resources;

/**
 *
 * @author PC
 */
public class Bros extends ElementWithGravity{
//    private Weapon weapon;
    public static final float WIDTH = 30;
    public static final float HEIGHT = 32;
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
        this.lifes=3;
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
        Resources r = Resources.getInstance();
        gc.drawImage(r.getImage("player"),
        //inicio de la posicion
        12,
        2,
        Bros.WIDTH,
        Bros.HEIGHT,
        //dibujar en el lienzo
        (this.getRectangle().getMinX()) * Game.SCALE,
        (this.getRectangle().getMinY()) * Game.SCALE,
        Bros.WIDTH * Game.SCALE,
        Bros.HEIGHT * Game.SCALE);
    }
    
    @Override
    public Optional<Collision> collision(Element e){
        return null; 
    }
    
    public void changeWeapon(){
        
    }
    
    public void restarVida(){
        this.lifes--;
    }

    
    /**
     * @return the lifes
     */
    public int getLifes() {
        return lifes;
    }
}
