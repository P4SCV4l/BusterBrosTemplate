/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import pedro.ieslaencanta.com.busterbros.basic.Brick;
import pedro.ieslaencanta.com.busterbros.basic.BrickBreakable;
import pedro.ieslaencanta.com.busterbros.basic.Element;
import pedro.ieslaencanta.com.busterbros.basic.ElementMovable;
import pedro.ieslaencanta.com.busterbros.basic.Ladder;
import pedro.ieslaencanta.com.busterbros.basic.Level;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable;

;

/**
 * Tablero del juego, posee un fondo (imagen estática, solo se cambia cuando se
 * cambia el nivel), Una bola, un vector de niveles, un disparador y una matriz
 * de bolas gestiona la pulsación de tecla derecha e izquierda
 *
 * @author Pedro
 * @see Bubble Level Shttle BallGrid
 */
public class Board implements IKeyListener {

    private Rectangle2D game_zone;
    
    private GraphicsContext gc;
    private GraphicsContext bggc;
    private final Dimension2D original_size;
    

    private boolean debug;
    private boolean left_press, right_press, up_press, down_press;
    private Level levels[];
    private int actual_level = -1;
    private MediaPlayer backgroundsound;
    private Element[] elements;
    private ElementMovable jugador;
    
    public Board(Dimension2D original) {
        this.gc = null;
        this.game_zone = new Rectangle2D(8, 8, 368, 192);
        this.original_size = original;
        this.right_press = false;
        this.left_press = false;
        this.up_press = false;
        this.down_press = false;

        this.debug = false;

        this.actual_level = 12;

        this.createLevels();
        this.nextLevel();
        this.jugador=new ElementMovable(50,50,32,32);
    }

    private void createLevels() {
        int y = 44;
        this.levels = new Level[50];
        for (int i = 0; i < 25; i++) {
            this.levels[2 * i] = new Level();//(8, y);
            this.levels[2 * i].setX(8);
            this.levels[2 * i].setY(y);
            this.levels[2 * i].setImagename("bricks");
            this.levels[2 * i].setBackgroundname("backgrounds");
            this.levels[2 * i].setSoundName("fondo");
            this.levels[2 * i].setTime(30);

            this.levels[2 * i + 1] = new Level();//(8, y);
            this.levels[2 * i + 1].setX(400);
            this.levels[2 * i + 1].setY(y);
            this.levels[2 * i + 1].setImagename("bricks");
            this.levels[2 * i + 1].setBackgroundname("backgrounds");
            this.levels[2 * i + 1].setSoundName("fondo");

            this.levels[2 * i + 1].setTime(30);

            y += 216;
        }
    }

    private void createElementsLevel() {
        Brick tempo;
        BrickBreakable tempo2;
        Pair<Level.ElementType, Rectangle2D>[] fi = this.levels[this.actual_level].getFigures();
        this.elements = new Element[fi.length];
        for (int i = 0; i < fi.length; i++) {

            //this.elements[i].setColor(Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            switch (fi[i].getKey()) {
                case FIXED:
                    tempo = new Brick((fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                            (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                            fi[i].getValue().getWidth(),
                            fi[i].getValue().getHeight()
                    );
                    tempo.setOriginal(fi[i].getValue());
                    tempo.setImg(Resources.getInstance().getImage("bricks"));
                    
                    this.elements[i]=tempo;
                    break;
                case BREAKABLE:
                    tempo2 = new BrickBreakable(
                            (fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                            (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                            fi[i].getValue().getWidth(),
                            fi[i].getValue().getHeight()
                    );
                    tempo2.setOriginal(fi[i].getValue());
                    tempo2.setImg(Resources.getInstance().getImage("bricks"));
                    this.elements[i]=tempo2;
                    break;
                case LADDER:
                    Ladder ladder = new Ladder(
                            (fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                            (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                            fi[i].getValue().getWidth(),
                            fi[i].getValue().getHeight()
                    );
                    ladder.setOriginal(fi[i].getValue());
                    ladder.setImg(Resources.getInstance().getImage("bricks"));
                    this.elements[i]=ladder;
                    break;

            }
        }

    }

    /**
     * @param debug the debug to set
     */
    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;

    }

    public void setBackGroundGraphicsContext(GraphicsContext gc) {
        this.bggc = gc;
        this.paintBackground();
    }

    /**
     * cuando se produce un evento
     */
    public synchronized void TicTac() {
        this.process_input();

        this.update();
        this.render();

        //actualizar
    }

    private void update() {
        for(int i=0;i<this.elements.length;i++){
            if(this.elements[i]!=null && this.elements[i] instanceof ElementMovable){
                ((ElementMovable)this.elements[i]).move();
            }
        }

    }

    private void evalCollisions() {
        
    }

    private void render() {
        this.clear();
        if (this.elements != null) {
            for (int i = 0; i < this.elements.length; i++) {
                if (this.elements[i] != null) {
                    this.elements[i].paint(gc);
                }
            }
            this.jugador.paint(gc);
        }

    }

    private void process_input() {

        if (this.left_press) {
            this.jugador.moveLeft(2);
            if(this.jugador.IsInBorder(game_zone)==IMovable.BorderCollision.LEFT){
                this.jugador.setPosition(this.game_zone.getMinX(),this.jugador.getRectangle().getMinY());
            }
        } else if (this.right_press) {
            this.jugador.moveRight(2);
        }
        if (this.up_press) {
            this.jugador.moveUp(2);
        }
        if (this.down_press) {
            this.jugador.moveDown(2);
        }

    }

    /**
     * limpiar la pantalla
     */
    private void clear() {
        this.gc.restore();
        this.gc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);
    }

    /**
     * pintar el fonodo
     */
    public void paintBackground() {
        if (this.bggc != null) {
            this.bggc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, (this.original_size.getHeight() + Game.INFOAREA) * Game.SCALE);
            this.bggc.setFill(Color.BLACK);
            this.bggc.fillRect(0, 0, this.original_size.getWidth() * Game.SCALE, (this.original_size.getHeight() + Game.INFOAREA) * Game.SCALE);
            if (this.gc != null) {
                this.gc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, (this.original_size.getHeight() + Game.INFOAREA) * Game.SCALE);
            }

            this.bggc.drawImage(this.levels[actual_level].getBackground(),
                    this.levels[actual_level].getX(), this.levels[actual_level].getYBackground(), this.original_size.getWidth(), this.original_size.getHeight(),
                    0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);
        }
    }

    /**
     * gestión de pulsación
     *
     * @param code
     */
    @Override
    public void onKeyPressed(KeyCode code) {
        switch (code) {
            case LEFT:
                this.left_press = true;

                break;
            case RIGHT:

                this.right_press = true;
                break;
            case UP:
                this.up_press = true;
                break;
            case DOWN:
                this.down_press = true;
                break;
        }
    }

    @Override
    public void onKeyReleased(KeyCode code) {

        switch (code) {
            case LEFT:
                this.left_press = false;
                break;
            case RIGHT:
                this.right_press = false;
                break;
            case UP:
                this.up_press = false;
                break;
            case DOWN:
                this.down_press = false;
                break;

            case N:
                this.nextLevel();
                break;

        }

    }

    private void nextLevel() {
        this.actual_level++;
        if (this.actual_level >= this.levels.length) {
            this.actual_level = 0;
        }
//        if (this.nextLevel()!=null){
//            this.backgroundsound.stop();
//        }
//        this.backgroundsound=Resources.getInstance().getSound("fondo");
//        this.backgroundsound.play();
        this.levels[this.actual_level].analyze();
        this.createElementsLevel();

        this.paintBackground();
        Game.reset_counter();

    }
}
