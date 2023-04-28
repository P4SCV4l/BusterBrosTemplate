/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import pedro.ieslaencanta.com.busterbros.basic.interfaces.IGravity;

/**
 *
 * @author DAWTarde
 */
public class ElementWithGravity extends ElementMovable implements IGravity {

    private double gx;
    private double gy;
    private boolean activegravityx;
    private boolean activegravityy;

    public ElementWithGravity() {
        super();
    }
    
    public ElementWithGravity(double gx, double gy) {
        this.gx = gx;
        this.gy = gy;
    }

    public ElementWithGravity(double gx, double gy, boolean activegravityx, boolean activegravityy, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.gx = gx;
        this.gy = gy;
        this.activegravityx = activegravityx;
        this.activegravityy = activegravityy;
    }

    @Override
    public boolean isActive() {
        return this.activegravityx || this.activegravityy;
    }

    @Override
    public boolean isActiveHorizontalGravity() {
        return this.activegravityx;
    }

    @Override
    public boolean isActiveVerticalGravity() {
        return this.activegravityy;
    }

    @Override
    public void activeGravity() {
        this.activegravityx = true;
        this.activegravityy = true;
    }

    @Override
    public void activeHorizontalGravity() {
        this.activegravityx = true;
    }

    @Override
    public void activeVerticalGravity() {
        this.activegravityy = true;
    }

    @Override
    public void unactiveGravity() {
        this.activegravityx = false;
        this.activegravityy = false;
    }

    @Override
    public void unactiveHorizontalGravity() {
        this.activegravityx = false;
    }

    @Override
    public void unactiveVerticalGravity() {
        this.activegravityy = false;
    }

    @Override
    public void setHorizontalGravity(double gravity) {
        this.gx = gravity;
    }

    @Override
    public void setVerticalGravity(double gravity) {
        this.gy = gravity;
    }

    @Override
    public double getHorizontalGravity() {
       return this.gx;
    }

    @Override
    public double getVerticalGravity() {
        return this.gy;
    }

    @Override
    public void update() {

    }
    
    @Override
    public void move(){
        super.move(super.getVx(), super.getVy());
        if(this.isActiveHorizontalGravity()){
        setVx(this.getVx()+gx);
        }
        if(this.isActiveVerticalGravity()){
        setVy(this.getVy()+gy);
        }
    }
}
