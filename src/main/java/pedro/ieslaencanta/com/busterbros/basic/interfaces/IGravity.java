/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic.interfaces;

/**
 *
 * @author DAWTarde
 */
public interface IGravity {
    
    public boolean isActive();
    
    public boolean isActiveHorizontalGravity();
    
    public boolean isActiveVerticalGravity();
    
    public void activeGravity();
    
    public void activeHorizontalGravity();
    
    public void activeVerticalGravity();

    public void unactiveGravity();
    
    public void unactiveHorizontalGravity();
    
    public void unactiveVerticalGravity();

    public void setHorizontalGravity(double gravity);
    
    public void setVerticalGravity(double gravity);
    
    public double getHorizontalGravity();
    
    public double getVerticalGravity();
}
