/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import javafx.geometry.Rectangle2D;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.IResizable;

/**
 *
 * @author PC
 */
public class ElementResizable extends ElementDynamic implements IResizable, IMovable {
    private double iw;
    private double ih;

    public ElementResizable() {
        super();
    }

    public ElementResizable(double iw, double ih) {
        this.iw = iw;
        this.ih = ih;
    }

    public ElementResizable(double x, double y, double width, double height){
        super(x, y, width, height);
    }
    
    public ElementResizable(double iw, double ih, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.iw = iw;
        this.ih = ih;
    }
    
    @Override
    public void resizeWidth() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void resizeWidth(double inc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void resizeHeigth() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void resizeHeigth(double inc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDefaultIncWidth(double incw) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setDefaultIncHeigth(double inch) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * @return the ih
     */
    public double getIh() {
        return ih;
    }

    /**
     * @param ih the ih to set
     */
    public void setIh(double ih) {
        this.ih = ih;
    }

    @Override
    public BorderCollision IsInBorder(Rectangle2D border) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'IsInBorder'");
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
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void moveLeft() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveLeft'");
    }

    @Override
    public void moveLeft(double inc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveLeft'");
    }

    @Override
    public void moveRight() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveRight'");
    }

    @Override
    public void moveRight(double inc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveRight'");
    }

    @Override
    public void moveUp() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveUp'");
    }

    @Override
    public void moveUp(double inc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveUp'");
    }

    @Override
    public void moveDown() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'moveDown'");
    }

    @Override
    public void moveDown(double inc) {
        this.move(0,+inc);
    }
}
