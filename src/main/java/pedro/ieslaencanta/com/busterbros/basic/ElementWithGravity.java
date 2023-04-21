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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isActiveVerticalGravity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void unactiveVerticalGravity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setHorizontalGravity(double gravity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setVerticalGravity(double gravity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getHorizontalGravity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getVerticalGravity() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update() {

    }
}
