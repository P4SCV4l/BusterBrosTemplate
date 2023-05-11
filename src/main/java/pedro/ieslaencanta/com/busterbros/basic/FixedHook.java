/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

/**
 *
 * @author DAWTarde
 */
public class FixedHook extends Hook{
    private int contador=0;
    private State state;

    public FixedHook(double x, double y) {
        super(x, y);
    }
    
    @Override
    public void stop(){
        contador++;
        if(contador>50){
        this.state = this.state.STOP;
        }
    }
}
