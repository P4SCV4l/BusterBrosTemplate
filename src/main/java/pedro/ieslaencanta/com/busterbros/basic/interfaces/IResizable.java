/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic.interfaces;

/**
 *
 * @author PC
 */
public interface IResizable {
    
    public void resizeWidth();
    
    public void resizeWidth(double inc);
    
    public void resizeHeigth();
    
    public void resizeHeigth(double inc);
    
    public void setDefaultIncWidth(double incw);
    
    public void setDefaultIncHeigth(double inch);
}
