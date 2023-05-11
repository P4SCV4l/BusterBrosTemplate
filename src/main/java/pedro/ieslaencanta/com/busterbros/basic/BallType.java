/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

/**
 *
 * @author PC
 */
public enum BallType {
    EXTRABIG(48,40, 1, 6),
    BIG(32,26,52, 13),
    MEDIUM(15,14,86, 19),
    LITTLE(8,7,106, 23);
    
    private final int width;
    private final int height;
    private final int startx;
    private final int starty;
        
    BallType(int width, int height, int startx, int starty) {
        this.width = width;
        this.height = height;
        this.startx = startx;
        this.starty = starty;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the startx
     */
    public int getStartx() {
        return startx;
    }

    public int getStarty(){
        return starty;
    }
}