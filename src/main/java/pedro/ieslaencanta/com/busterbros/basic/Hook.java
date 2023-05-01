/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author PC
 */
public class Hook extends ElementResizable {
    private static final int WIDTH=30;
    private int heigth=30;
    private int vw;
    private int vy;
    private static final int[] ARROWS=new int[1];

    public Hook() {
        super();
    }
    
    public Hook(double x, double y, double iw, double ih) {
        super(iw, ih, x, y, Hook.WIDTH, Bros.getHEIGHT());
    }

    @Override
    public void resizeHeigth() {
        super.resizeHeigth(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void paint(GraphicsContext gc) {
        super.paint(gc); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    public void ajustTop(Rectangle2D game_zone){
    }

    @Override
    public void update() {
        super.update(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
