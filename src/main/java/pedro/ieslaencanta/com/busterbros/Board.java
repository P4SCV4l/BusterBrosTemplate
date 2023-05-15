/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros;

import java.util.Optional;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import pedro.ieslaencanta.com.busterbros.basic.Ball;
import pedro.ieslaencanta.com.busterbros.basic.BallColor;
import pedro.ieslaencanta.com.busterbros.basic.BallType;
import pedro.ieslaencanta.com.busterbros.basic.Balls;
import pedro.ieslaencanta.com.busterbros.basic.Brick;
import pedro.ieslaencanta.com.busterbros.basic.BrickBreakable;
import pedro.ieslaencanta.com.busterbros.basic.Bros;
import pedro.ieslaencanta.com.busterbros.basic.Collision;
import pedro.ieslaencanta.com.busterbros.basic.Element;
import pedro.ieslaencanta.com.busterbros.basic.ElementMovable;
import pedro.ieslaencanta.com.busterbros.basic.ElementResizable;
import pedro.ieslaencanta.com.busterbros.basic.ElementWithGravity;
import pedro.ieslaencanta.com.busterbros.basic.FixedHook;
import pedro.ieslaencanta.com.busterbros.basic.Hook;
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
    private Ball ball;
    private Balls balls;
    private Bros jugador;
    private Hook hook;
    Optional<Collision> c;

    // double vx=-1;
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
        this.jugador = new Bros(0.0, 5, 0.5, 0, game_zone.getMaxX() / 2, game_zone.getMaxY());
        this.balls = new Balls(40);
        this.ball = new Ball(0.0, 0.1, 0.4, 0.4, 300, 50, BallType.EXTRABIG, BallColor.BLUE);
        this.ball.setVx(1);
        this.ball.setVy(0);
        this.balls.addBall(ball);
        this.ball = new Ball(0.0, 0.1, 0.4, 0.4, 40, 50, BallType.EXTRABIG, BallColor.RED);
        this.ball.setVx(-1);
        this.balls.addBall(ball);
    }
    //Crear niveles.
    private void createLevels() {
        int y = 44;
        this.levels = new Level[50];
        for (int i = 0; i < 25; i++) {
            this.levels[2 * i] = new Level();// (8, y);
            this.levels[2 * i].setX(8);
            this.levels[2 * i].setY(y);
            this.levels[2 * i].setImagename("bricks");
            this.levels[2 * i].setBackgroundname("backgrounds");
            this.levels[2 * i].setSoundName("fondo");
            this.levels[2 * i].setTime(30);

            this.levels[2 * i + 1] = new Level();// (8, y);
            this.levels[2 * i + 1].setX(400);
            this.levels[2 * i + 1].setY(y);
            this.levels[2 * i + 1].setImagename("bricks");
            this.levels[2 * i + 1].setBackgroundname("backgrounds");
            this.levels[2 * i + 1].setSoundName("fondo");

            this.levels[2 * i + 1].setTime(30);

            y += 216;
        }
    }
    //Crear los elementos pertenecientes a cada nivel, almacenados en un array.
    private void createElementsLevel() {
        Brick tempo;
        BrickBreakable tempo2;
        Pair<Level.ElementType, Rectangle2D>[] fi = this.levels[this.actual_level].getFigures();
        this.elements = new Element[fi.length];
        for (int i = 0; i < fi.length; i++) {


            switch (fi[i].getKey()) {
                case FIXED:
                    tempo = new Brick((fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                            (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                            fi[i].getValue().getWidth(),
                            fi[i].getValue().getHeight());
                    tempo.setOriginal(fi[i].getValue());
                    tempo.setImg(Resources.getInstance().getImage("bricks"));

                    this.elements[i] = tempo;
                    break;
                case BREAKABLE:
                    tempo2 = new BrickBreakable(
                            (fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                            (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                            fi[i].getValue().getWidth(),
                            fi[i].getValue().getHeight());
                    tempo2.setOriginal(fi[i].getValue());
                    tempo2.setImg(Resources.getInstance().getImage("bricks"));
                    this.elements[i] = tempo2;
                    break;
                case LADDER:
                    Ladder ladder = new Ladder(
                            (fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                            (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                            fi[i].getValue().getWidth(),
                            fi[i].getValue().getHeight());
                    ladder.setOriginal(fi[i].getValue());
                    ladder.setImg(Resources.getInstance().getImage("bricks"));
                    this.elements[i] = ladder;
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
        // actualizar
    }

    /**
     * 
     */
    private void update() {
        if (this.jugador.getLifes() > 0){
            for (int i = 0; i < this.balls.getSize(); i++) {
                if (this.balls.getBall(i) != null) {
                    this.balls.getBall(i).move();
                }
            }
            this.evalCollisions();
            if(this.hook!=null){
                this.hook.resizeHeigth();
            }
            if(balls.getNumberOfElements() <= 0){
                this.nextLevel();
                this.createBall();
            }
        }
    }

    private void render() {
        this.clear();
        //Pintamos todo lo que hay en elements.
        if (this.elements != null) {
            for (int i = 0; i < this.elements.length; i++) {
                if (this.elements[i] != null) {
                    this.elements[i].paint(gc);
                }
            }
             //Pintamos al jugador.
            if (this.jugador != null) {
                this.jugador.paint(gc);
                if (this.jugador.isDebug())
                {
                    this.jugador.debug(gc);
                }
            }
            //Pintamos el arpón.
            if (this.hook != null) {
                this.hook.paint(gc);
            }
            for (int i = 0; i < this.balls.getSize(); i++) {
                if (this.balls.getBall(i) != null) {
                    this.balls.getBall(i).paint(gc);

                    if (this.balls.getBall(i).isDebug())
                    {
                        this.balls.getBall(i).debug(gc);
                    }
                }
            }
        }

    }

    private void process_input() {
        this.movimientos();
    }

        /**
     * limpiar la pantalla
     */
    private void clear() {
        this.gc.restore();
        this.gc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE,
                this.original_size.getHeight() * Game.SCALE);
    }

        /**
     * pintar el fonodo
     */
    public void paintBackground() {
        if (this.bggc != null) {
            this.bggc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE,
                    (this.original_size.getHeight() + Game.INFOAREA) * Game.SCALE);
            this.bggc.setFill(Color.BLACK);
            this.bggc.fillRect(0, 0, this.original_size.getWidth() * Game.SCALE,
                    (this.original_size.getHeight() + Game.INFOAREA) * Game.SCALE);
            if (this.gc != null) {
                this.gc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE,
                        (this.original_size.getHeight() + Game.INFOAREA) * Game.SCALE);
            }

            this.bggc.drawImage(this.levels[actual_level].getBackground(),
                    this.levels[actual_level].getX(), this.levels[actual_level].getYBackground(),
                    this.original_size.getWidth(), this.original_size.getHeight(),
                    0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);
        }
    }

    private void evalCollisions() {
        //Evaluar colisiones de la pelota con los bordes del tablero.
        for (int i = 0; i < this.balls.getSize(); i++) {
            if (this.balls.getBall(i) != null) {
                if (this.balls.getBall(i).IsInBorder(game_zone) == IMovable.BorderCollision.DOWN) {
                    this.balls.getBall(i).setVy(-this.balls.getBall(i).getVy());
                    this.balls.getBall(i).setPosition(this.balls.getBall(i).getRectangle().getMinX(),
                            this.game_zone.getMaxY() - this.balls.getBall(i).getHeight());
                } else if (this.balls.getBall(i).IsInBorder(game_zone) == IMovable.BorderCollision.TOP) {
                    this.balls.getBall(i).setVy(-this.balls.getBall(i).getVy());
                    this.balls.getBall(i).setPosition(this.balls.getBall(i).getRectangle().getMinX(),
                            this.game_zone.getMinY());
                } else if (this.balls.getBall(i).IsInBorder(game_zone) == IMovable.BorderCollision.RIGHT) {
                    this.balls.getBall(i).setVx(-this.balls.getBall(i).getVx());
                    this.balls.getBall(i).setPosition(this.game_zone.getMaxX() - this.balls.getBall(i).getWidth(),
                            this.balls.getBall(i).getRectangle().getMinY());
                } else if (this.balls.getBall(i).IsInBorder(game_zone) == IMovable.BorderCollision.LEFT) {
                    this.balls.getBall(i).setPosition(this.game_zone.getMinX(),
                            this.balls.getBall(i).getRectangle().getMinY());
                    this.balls.getBall(i).setVx(-this.balls.getBall(i).getVx());
                } else {

                    for (int j = 0; j < elements.length; j++) {
                        if (elements[j] instanceof Brick) {
                            Optional<Collision>  c = this.balls.getBall(i).collision(elements[j]);
                            if(c.isPresent()){
                                this.balls.getBall(i).move(c.get().getSeparator().getX(), c.get().getSeparator().getY());
                                if(c.get().getSeparator().getY()!=0){
                                    this.balls.getBall(i).setVy(- this.balls.getBall(i).getVy());
                                }
                                if(c.get().getSeparator().getX()!=0){
                                    this.balls.getBall(i).setVx(- this.balls.getBall(i).getVx());
                                }
                            }
                        }
                    }
                }
            }
        }
        //Choque del arma con las bolas.
        if (this.hook != null){
            for (int j = 0; j < this.balls.getSize(); j++) {
                if (balls.getBall(j) != null) {
                    if (this.hook.getRectangle().intersects(balls.getBall(j).getRectangle())) {
                        this.hook = null;
                        this.balls.dividir(this.balls.getBall(j));
                    }
                }

                if (hook == null){ 
                    j = balls.getSize();
                }
            }
        }
        //Choque del arma con muros y muros destruible.
        if (this.hook != null){
            for (int i = 0; i < elements.length; i++){
                if (this.elements[i] != null){
                    if (this.hook.getRectangle().intersects(elements[i].getRectangle())) {
                            if (this.elements[i] instanceof BrickBreakable) {
                                this.hook = null;
                                this.elements[i] = null;
                            } else if (this.elements[i] instanceof Brick) {
                                this.hook = null;
                        }
                    }
                }

                if (hook == null){ 
                    i = balls.getSize();
                }
            }
            //Choque del arma con la parte superior.
            if(hook != null && this.hook.ajustTop(game_zone)){
                this.hook = null;
            }
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

            case S:
                this.setDebug();
                break;

            case SPACE:
            //Gancho normal.
            if(this.hook==null){
                hook = new Hook(this.jugador.getCenterX(), this.jugador.getCenter().getY());
            }
                break;
        }

    }

    private void nextLevel() {
        this.actual_level++;
        if (this.actual_level >= this.levels.length) {
            this.actual_level = 0;
        }
        this.backgroundsound=Resources.getInstance().getSound("fondo");
        this.backgroundsound.play();
        this.levels[this.actual_level].analyze();
        this.createElementsLevel();

        this.paintBackground();
        Game.reset_counter();

    }

    public void setDebug() {
        this.debug = !this.debug;
        this.jugador.setDebug(debug);
        this.ball.setDebug(debug);
        for (int i = 0; i < balls.getSize(); i++)
        {
            if (balls.getBall(i) != null) balls.getBall(i).setDebug(debug);
        }
    }

    public void movimientos() {
        if (this.left_press) {
            this.jugador.moveLeft(2);
            if (this.jugador.IsInBorder(game_zone) == IMovable.BorderCollision.LEFT) {
                this.jugador.setPosition(this.game_zone.getMinX(), this.jugador.getRectangle().getMinY());
            }
        } else if (this.right_press) {
            this.jugador.moveRight(2);
            if (this.jugador.IsInBorder(game_zone) == IMovable.BorderCollision.RIGHT) {
                this.jugador.setPosition(this.game_zone.getMaxX() - Bros.WIDTH, this.jugador.getRectangle().getMinY());
            }
        }
        if (this.jugador.IsInBorder(game_zone) == IMovable.BorderCollision.TOP) {
            this.jugador.setPosition(this.jugador.getRectangle().getMinX(), this.game_zone.getMinY());
        }
        if (this.up_press) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] instanceof Ladder) {
                    if (this.jugador.getRectangle().intersects(elements[i].getRectangle())) {
                        this.jugador.moveUp(7);

                    }
                }
            }
        }
        for (int i = 0; i < elements.length; i++) {
                if (elements[i] instanceof Brick 
                        && this.jugador.getRectangle().intersects(elements[i].getRectangle())         
                        && this.jugador.isActiveVerticalGravity()) {
                    this.jugador.moveUp(5);
                    this.jugador.unactiveVerticalGravity();
            }      
        }
        if (this.down_press) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] instanceof Ladder) {
                    if (this.jugador.getRectangle().intersects(elements[i].getRectangle())) {


                    }
                }
            }
        }
        if (this.jugador.IsInBorder(game_zone) == IMovable.BorderCollision.DOWN) {
            this.jugador.setPosition(this.jugador.getRectangle().getMinX(), this.game_zone.getMaxY() - Bros.HEIGHT);
            this.jugador.setVerticalGravity(0);
        }
        if ((this.jugador.getRectangle().getMaxY()) < (this.game_zone.getMaxY())) {

            this.jugador.activeVerticalGravity();
            this.jugador.setVerticalGravity(5);
            this.jugador.moveDown(this.jugador.getVerticalGravity());
        }
        int i = 0;
        for (; i < this.balls.getSize(); i++) {
            if(this.balls.getBall(i) != null && this.jugador.getRectangle().intersects(this.balls.getBall(i).getRectangle())){
                this.jugador.restarVida();
                Optional<Collision>  c = this.balls.getBall(i).collision(this.jugador);
                if(c.isPresent()){
                    this.balls.getBall(i).move(c.get().getSeparator().getX(), c.get().getSeparator().getY());
                    if(c.get().getSeparator().getY()!=0){
                        this.balls.getBall(i).setVy(- this.balls.getBall(i).getVy());
                    }
                    if(c.get().getSeparator().getX()!=0){
                        this.balls.getBall(i).setVx(- this.balls.getBall(i).getVx());
                    }
                }
            }
        }
    }

    public void createBall(){
        this.ball = new Ball(0.0, 0.1, 0.4, 0.4, 300, 50, BallType.EXTRABIG, BallColor.BLUE);
        this.ball.setVx(1);
        this.balls.addBall(ball);
        this.ball = new Ball(0.0, 0.1, 0.4, 0.4, 40, 50, BallType.EXTRABIG, BallColor.RED);
        this.ball.setVx(-1);
        this.balls.addBall(ball);
    }
}
