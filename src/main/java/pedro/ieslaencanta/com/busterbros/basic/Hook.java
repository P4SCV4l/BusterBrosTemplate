/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import pedro.ieslaencanta.com.busterbros.Game;

/**
 *
 * @author PC
 */
public class Hook extends ElementResizable {
    private static final int WIDTH=10;
    private static final int ORIGINALHEIGHT=1;
    public static int HEIGHT=1;
    private int vw;
    private final int vy=2;
    private static final int[] ARROWS=new int[1];

    public Hook() {
        super();
    }
    
    public Hook(double x, double y){
        super(0, 10, x, y, Hook.WIDTH, Hook.HEIGHT);
    }
    
    /*public Hook(double x, double y, double iw, double ih) {
        super(iw, ih, x, y, Hook.WIDTH, 1);
    }*/
    
    
    @Override
    public void resizeHeigth() {
        Hook.HEIGHT=Hook.HEIGHT+this.vy;
        // this.moveDown(HEIGHT);
        // Hook hook = new Hook(this.getRectangle().getMinX(), 250);
    }

    @Override
    public void paint(GraphicsContext gc) {
//        gc.setFill(this.color);
        //se tendrá que sustituro por img
        gc.fillRect(this.getRectangle().getMinX() * Game.SCALE, this.getRectangle().getMinY() * Game.SCALE, this.getRectangle().getWidth() * 
        Game.SCALE, Hook.HEIGHT * Game.SCALE);
        if (this.isDebug()) {

            this.debug(gc);
        }
    }

    
    public void ajustTop(Rectangle2D game_zone){
//        if(this.getRectangle().getMinY()<=game_zone.getMaxY()){
//            
//        }
        
    }

    public void resetHeigth(){
        this.HEIGHT=this.ORIGINALHEIGHT;
    }

    @Override
    public void update() {
        this.resizeHeigth();
        // this.ajustTop(rectangle);
    }



    /**
     * @return the heigth
     */
    // public int getHeigth() {
    //     return heigth;
    // }

    /**
     * @param heigth the heigth to set
     */
    // public void setHeigth(double heigth) {
    //     this.heigth = heigth;
    // }
    
    
}
