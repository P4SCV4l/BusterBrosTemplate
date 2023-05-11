/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

/**
 *
 * @author PC
 */
public enum BallColor {
    RED(0),
    GREEN(99),
    BLUE(50);
    
    private final int starty;

    private BallColor(int starty) {
        this.starty = starty;
    }

    /**
     * @return the starty
     */
    public int getStarty() {
        return starty;
    }
    
    
}
