/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import javafx.geometry.Point2D;

/**
 *
 * @author DAWTarde
 */
public class Collision {
    private Element a;
    private Element b;
    private double distance;
    private Point2D separator;

    public Collision() {
    }

    public Collision(Element a, Element b, double distance, Point2D separator) {
        this.a = a;
        this.b = b;
        this.distance = distance;
        this.separator = separator;
    }

    /**
     * @return the a
     */
    public Element getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(Element a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    public Element getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(Element b) {
        this.b = b;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the separator
     */
    public Point2D getSeparator() {
        return separator;
    }

    /**
     * @param separator the separator to set
     */
    public void setSeparator(Point2D separator) {
        this.separator = separator;
    }
    
    
}
