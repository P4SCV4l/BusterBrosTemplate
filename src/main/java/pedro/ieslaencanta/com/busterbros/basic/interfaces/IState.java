/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic.interfaces;

/**
 *
 * @author PC
 */
public interface IState {
    public enum State{
        START,
        STOP,
        PAUSE
    }
    public void stop();
    public void start();
    public void pause();
    public void getState();
    public void setState(State s);
}
