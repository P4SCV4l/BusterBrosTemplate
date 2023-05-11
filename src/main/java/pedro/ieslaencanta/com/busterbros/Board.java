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
    private ElementWithGravity jugador;
    private Ball ball;
    private Balls balls;
    private Bros jugado;
    private Hook hook;
    private FixedHook fixedhook;
    Optional<Collision> c;
    private int contador;
    private Hook[] hooks;

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
        hooks = new Hook[1];
        this.actual_level = 12;

        this.createLevels();
        this.nextLevel();
        this.jugador = new ElementWithGravity(2, 2, true, true, 50, 50, 20, 20);
        this.jugado = new Bros(0.0, 5, 0.5, 0, game_zone.getMaxX() / 2, game_zone.getMaxY());
        this.ball = new Ball(0.0, 0.1, 0.4, 0.4, 86, 50, BallType.EXTRABIG, BallColor.BLUE);
        this.ball.setVx(1);
        this.ball.setVy(0);

        this.balls = new Balls(40);
        this.balls.addBall(ball);
    }

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

    private void createElementsLevel() {
        Brick tempo;
        BrickBreakable tempo2;
        Pair<Level.ElementType, Rectangle2D>[] fi = this.levels[this.actual_level].getFigures();
        this.elements = new Element[fi.length];
        for (int i = 0; i < fi.length; i++) {

            // this.elements[i].setColor(Color.rgb((int) (Math.random() * 255), (int)
            // (Math.random() * 255), (int) (Math.random() * 255)));
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
        for (int i = 0; i < this.balls.getSize(); i++) {
            if (this.balls.getBall(i) != null) {
                this.balls.getBall(i).move();
            }
        }
        this.evalCollisions();
        if(this.hook!=null){
            this.hook.resizeHeigth();
            // this.CollisionHook();
        if(this.fixedhook!=null){
            this.fixedhook.resizeHeigth();
            this.fixedhook.stop();
        }
        }
        //this.AumentarEliminar();
        /*
         * for (int i = 0; i < this.elements.length; i++) {
         * if (this.elements[i] != null && this.elements[i] instanceof ElementMovable) {
         * ((ElementMovable) this.elements[i]).move();
         * }
         * // else if(this.elements[i]!=null && this.elements[i] instanceof
         * // ElementResizable && contador > 0){
         * // ((Hook)this.elements[i]).update();
         * // }
         * }
         */
        /*
         * jugador.move();
         * IMovable.BorderCollision b = this.jugador.IsInBorder(game_zone);
         * switch (b) {
         * case DOWN:
         * this.jugador.setVy(Math.abs(this.jugador.getVy()));
         * break;
         * default:
         * throw new AssertionError();
         * }
         */
    }

    private void evalCollisions() {
        for (int i = 0; i < this.balls.getSize(); i++) {
            if (this.balls.getBall(i) != null) {
                // this.balls.getBall(i).move();
                // double vy=this.balls.getBall(i).getVy();
                // vy*=-0.975;
                if (this.balls.getBall(i).IsInBorder(game_zone) == IMovable.BorderCollision.DOWN) {
                    // this.balls.getBall(i).setVx(this.balls.getBall(i).getVx());
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
                            Optional<Collision>  c = this.balls.getBall(i).collisionWithBrick(elements[j]);
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
        
        if (this.hook != null){
            for (int j = 0; j < this.balls.getSize(); j++) {
                for (int h = 0; h < elements.length; h++) {
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
        }

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

            if(hook != null && this.hook.ajustTop(game_zone)){
                this.hook = null;
            }
        }
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
            if (this.jugado != null) {
                this.jugado.paint(gc);
                // if (this.hook != null){
                // this.hook.paint(gc);
                // }
                if (this.jugado.isDebug())
                {
                    this.jugado.debug(gc);
                }
            }
            if (this.hook != null) {
                this.hook.paint(gc);
            }
            if (this.fixedhook != null) {
                this.fixedhook.paint(gc);
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
        // this.evalCollisions();
        // this.AumentarEliminar();
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

            case F:
            if(this.fixedhook==null){
                fixedhook = new FixedHook(this.jugado.getCenterX(), this.jugado.getCenter().getY());
            }
                break;
            case SPACE:
            if(this.hook==null){
                hook = new Hook(this.jugado.getCenterX(), this.jugado.getCenter().getY());
            }
                break;
        }

    }

    private void nextLevel() {
        this.actual_level++;
        if (this.actual_level >= this.levels.length) {
            this.actual_level = 0;
        }
        // if (this.nextLevel()!=null){
        // this.backgroundsound.stop();
        // }
        // this.backgroundsound=Resources.getInstance().getSound("fondo");
        // this.backgroundsound.play();
        this.levels[this.actual_level].analyze();
        this.createElementsLevel();

        this.paintBackground();
        Game.reset_counter();

    }

    public void setDebug() {
        this.debug = !this.debug;
        this.jugador.setDebug(debug);
        this.jugado.setDebug(debug);
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
                this.jugador.setPosition(this.game_zone.getMaxX() - this.jugador.getWidth(),
                        this.jugador.getRectangle().getMinY());
            }
        } else if (this.up_press) {
            this.jugador.moveUp(2);
            if (this.jugador.IsInBorder(game_zone) == IMovable.BorderCollision.TOP) {
                this.jugador.setPosition(this.jugador.getRectangle().getMinX(), this.game_zone.getMinY());
            }
        } else if (this.down_press) {
            this.jugador.moveDown(2);
            if (this.jugador.IsInBorder(game_zone) == IMovable.BorderCollision.DOWN) {
                this.jugador.setPosition(this.jugador.getRectangle().getMinX(),
                        this.game_zone.getMaxY() - this.jugador.getHeight());
            }
        }

        if (this.left_press) {
            this.jugado.moveLeft(2);
            if (this.jugado.IsInBorder(game_zone) == IMovable.BorderCollision.LEFT) {
                this.jugado.setPosition(this.game_zone.getMinX(), this.jugado.getRectangle().getMinY());
            }
        } else if (this.right_press) {
            this.jugado.moveRight(2);
            if (this.jugado.IsInBorder(game_zone) == IMovable.BorderCollision.RIGHT) {
                this.jugado.setPosition(this.game_zone.getMaxX() - Bros.WIDTH, this.jugado.getRectangle().getMinY());
            }
        }
        if (this.jugado.IsInBorder(game_zone) == IMovable.BorderCollision.TOP) {
            this.jugado.setPosition(this.jugado.getRectangle().getMinX(), this.game_zone.getMinY());
        }
        if (this.up_press) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] instanceof Ladder) {
                    if (this.jugado.getRectangle().intersects(elements[i].getRectangle())) {
                        this.jugado.moveUp(7);

                    }
                }
            }
        }
        for (int i = 0; i < elements.length; i++) {
                if (elements[i] instanceof Brick 
                        && this.jugado.getRectangle().intersects(elements[i].getRectangle())         
                        && this.jugado.isActiveVerticalGravity()) {
                    this.jugado.moveUp(5);
                    this.jugado.unactiveVerticalGravity();
            }      
        }

        // for(int i=0;i<elements.length;i++){
        // if(elements[i] instanceof Brick){
        // if(this.jugado.getRectangle().intersects(elements[i].getRectangle()) &&
        // this.jugado.isActiveVerticalGravity()){
        // this.jugado.moveUp(5);
        // this.jugado.unactiveVerticalGravity();
        // }
        // }if (this.up_press) {
        // for(int j=0;j<elements.length;j++){
        // if(elements[j] instanceof Ladder){
        // if(this.jugado.getRectangle().intersects(elements[j].getRectangle())){
        // this.jugado.moveUp(7);

        // }
        // }
        // }
        // }if((this.jugado.getRectangle().getMaxY())<(this.game_zone.getMaxY())){
        // this.jugado.moveDown(5);
        // this.jugado.activeVerticalGravity();
        // }
        // }

        if (this.down_press) {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] instanceof Ladder) {
                    if (this.jugado.getRectangle().intersects(elements[i].getRectangle())) {


                    }
                }
            }
        }
        if (this.jugado.IsInBorder(game_zone) == IMovable.BorderCollision.DOWN) {
            //this.jugado.setPosition(this.jugado.getRectangle().getMinX(), this.game_zone.getMaxY() - Bros.HEIGHT);
            this.jugado.setVerticalGravity(0);
            //this.jugado.moveDown(this.jugado.getVerticalGravity());
        }

        // if((this.jugado.getCenterY())<(this.game_zone.getMaxY()-16)){
        // this.jugado.moveDown(5);
        // // this.jugado.move();
        // }
        if ((this.jugado.getRectangle().getMaxY()) < (this.game_zone.getMaxY())) {
            // this.jugado.move();

            this.jugado.activeVerticalGravity();
            this.jugado.setVerticalGravity(5);
            this.jugado.moveDown(this.jugado.getVerticalGravity());
        }
        // if((this.jugado.getCenterY()+(Bros.HEIGHT/2))<(this.game_zone.getMaxY())){
        //// this.jugado.moveDown(2);
        // this.jugado.move();
        // }
        // if((this.jugado.getCenterY()+(Bros.getHEIGHT()/2))<(this.game_zone.getMaxY())){
        //// this.jugado.moveDown(2);
        // this.jugado.move();
        // }
        // if(!(this.jugado.IsInBorder(game_zone)==IMovable.BorderCollision.DOWN)){
        // this.jugado.move();
        // }
        // if(!(this.jugado.IsInBorder(game_zone)==IMovable.BorderCollision.DOWN) &&
        // !(this.jugado.IsInBorder(game_zone)==IMovable.BorderCollision.TOP) &&
        // !(this.jugado.IsInBorder(game_zone)==IMovable.BorderCollision.RIGHT) &&
        // !(this.jugado.IsInBorder(game_zone)==IMovable.BorderCollision.LEFT)){
        //// this.jugado.moveDown(2);
        // this.jugado.move();
        //
        // }

        // ball.move();
        //// // double vx=this.ball.getVx();
        // double vy=this.ball.getVy();
        // vy*=-0.975;
        //// // for (int i = 0; i < 4; i++)
        // if(this.ball.IsInBorder(game_zone)==IMovable.BorderCollision.DOWN){
        //// double delta = this.ball.getRectangle().getMaxY() -
        // this.game_zone.getMaxY();
        //// double vy=this.ball.getVy();
        // //System.out.println(vy + " -> " + delta);
        //// vy*=-0.975;
        // //vy+=delta;
        // this.ball.setVy(vy);
        //// this.ball.setVy(-4.5);
        //// this.ball.reset();
        //// this.ball.setVy(-this.ball.getVy());
        // this.ball.setVx(this.ball.getVx());
        //
        // // if(this.vx<-3.667590){
        // // this.vx=1;
        // // this.ball.setVx(this.vx);
        // // }else{
        // // this.vx=-1;
        // // this.ball.setVx(this.vx);
        // // }
        // this.ball.setPosition(this.ball.getRectangle().getMinX(),
        // this.game_zone.getMaxY()-this.ball.getHeight());
        //
        // }
        // if(this.ball.IsInBorder(game_zone)==IMovable.BorderCollision.TOP){
        //// double delta = this.game_zone.getMinY() -
        // this.ball.getRectangle().getMinY();
        //// double vy=this.ball.getVy();
        //// vy*=-0.975;
        ////// vy+=delta;
        //// this.ball.setVy(vy);
        // this.ball.setVy(vy);
        //
        // this.ball.setPosition(this.ball.getRectangle().getMinX(),
        // this.game_zone.getMinY());
        // }
        // if(this.ball.IsInBorder(game_zone)==IMovable.BorderCollision.RIGHT){
        //// double vx = this.ball.getVx();
        //// vx*=-0.975;
        //// this.ball.setVx(v);
        //// this.ball.setVx(this.ball.getVx());
        //// this.ball.setVx(-1);
        // this.ball.setVx(-this.ball.getVx());
        //// this.vx=-1;
        // // this.ball.setVx(this.vx);
        // // this.ball.setVx(this.vx);
        // this.ball.setPosition(this.game_zone.getMaxX()-this.ball.getWidth(),this.ball.getRectangle().getMinY());
        // }
        ////
        //// if(this.ball.IsInBorder(game_zone)==IMovable.BorderCollision.LEFT){
        //// this.ball.setPosition(this.game_zone.getMinX(),this.ball.getRectangle().getMinY());
        //// this.ball.moveRight(-this.ball.getVx());
        //// }
        // if(this.ball.IsInBorder(game_zone)==IMovable.BorderCollision.LEFT){
        // this.ball.setPosition(this.game_zone.getMinX(),this.ball.getRectangle().getMinY());
        //// double vx = this.ball.getVx();
        //// vx*=+0.975;
        //// this.ball.setVx(-v);
        //// this.ball.setVx(-this.ball.getVx());
        //// this.ball.setVx(1);
        // this.ball.setVx(-this.ball.getVx());
        //// this.vx=1;
        // // this.ball.setVx(this.vx);
        //// this.ball.moveRight(this.vx);
        //
        //
        // // this.ball.setVx(this.vx);
        //
        // }

        int i = 0;
        for (; i < this.balls.getSize(); i++) {

            // this.balls.getBall(i).move();
            // double vy=this.balls.getBall(i).getVy();
            // vy*=-0.975;
            // if(this.balls.getBall(i).IsInBorder(game_zone)==IMovable.BorderCollision.DOWN){
            // this.balls.getBall(i).setVx(this.balls.getBall(i).getVx());
            // this.balls.getBall(i).setVy(vy);
            // this.balls.getBall(i).setPosition(this.balls.getBall(i).getRectangle().getMinX(),
            // this.game_zone.getMaxY()-this.balls.getBall(i).getHeight());

            // }
            // if(this.balls.getBall(i).IsInBorder(game_zone)==IMovable.BorderCollision.TOP){
            // this.balls.getBall(i).setVy(vy);

            // this.balls.getBall(i).setPosition(this.balls.getBall(i).getRectangle().getMinX(),
            // this.game_zone.getMinY());
            // }
            // if(this.balls.getBall(i).IsInBorder(game_zone)==IMovable.BorderCollision.RIGHT){
            // this.balls.getBall(i).setVx(-this.balls.getBall(i).getVx());
            // this.balls.getBall(i).setPosition(this.game_zone.getMaxX()-this.balls.getBall(i).getWidth(),this.balls.getBall(i).getRectangle().getMinY());
            // }
            // if(this.balls.getBall(i).IsInBorder(game_zone)==IMovable.BorderCollision.LEFT){
            // this.balls.getBall(i).setPosition(this.game_zone.getMinX(),this.balls.getBall(i).getRectangle().getMinY());
            // this.balls.getBall(i).setVx(-this.balls.getBall(i).getVx());
            // }


            //Restar vidas del jugador.
            if(this.balls.getBall(i) != null && this.jugado.getRectangle().intersects(this.balls.getBall(i).getRectangle())){
            this.jugado.restarVida();
            // this.balls.getBall(i).setPosition(0, 0);
            // this.balls.getBall(i).setVy(vy);
            // this.balls.getBall(i).setVx(this.balls.getBall(i).getVx());
            }

            // //Eliminar al jugador.
            // // if(this.jugado.getLifes()<0){
            // // this.jugado = null;
            // // }

            // for(int j=0; j<this.hooks.length; j++){
            // if(this.balls.getBall(i) != null){
            // if(this.hooks[j] != null){
            // if(this.hooks[j].getRectangle().getMaxX() >=
            // this.balls.getBall(i).getRectangle().getMinX()
            // && this.hooks[j].getRectangle().getMinX() <=
            // this.balls.getBall(i).getRectangle().getMaxX()
            // && this.hooks[j].getRectangle().getMaxY() >=
            // this.balls.getBall(i).getRectangle().getMinY()
            // && this.hooks[j].getRectangle().getMinY() <=
            // this.balls.getBall(i).getRectangle().getMaxY()){
            // this.balls.dividir(this.balls.getBall(i));
            // this.hooks[j].resetHeigth();
            // this.hooks[j] = null;
            // }
            // }
            // }
            // }
        }
    }

    // public void AumentarEliminar() {
    //     if (this.hooks[0] != null) {
    //         double originalx = this.hooks[0].getRectangle().getMinX();

    //         for (int i = 0; i < this.hooks.length; i++) {
    //             if (this.hooks[i].getRectangle().getMaxY() <= this.game_zone.getMaxY()) {
    //                 this.hooks[0].resizeHeigth();
    //                 if (this.hooks[i] != null) {
    //                     this.hooks[i] = null;
    //                 }
    //                 if (this.hooks[i] == null) {
    //                     this.hooks[i] = new Hook(originalx, this.game_zone.getMinY());
    //                 }
    //             } else {
    //                 this.hooks[i].resetHeigth();
    //                 this.hooks[i] = null;
    //             }
    //             for (int j = 0; j < this.balls.getSize(); j++) {
    //                 for (int h = 0; h < elements.length; h++) {
    //                     if (balls.getBall(j) != null && this.hooks[i] != null) {
    //                         if (this.hooks[i].getRectangle().intersects(balls.getBall(j).getRectangle())) {
    //                             this.hooks[i].resetHeigth();
    //                             this.hooks[i] = null;
    //                             this.balls.dividir(this.balls.getBall(j));
    //                         } else if (this.elements[h] != null) {
    //                             if (this.hooks[i].getRectangle().intersects(elements[h].getRectangle())) {
    //                                 if (this.elements[h] instanceof BrickBreakable) {
    //                                     this.hooks[i].resetHeigth();
    //                                     this.hooks[i] = null;
    //                                     this.elements[h] = null;
    //                                 } else if (this.elements[h] instanceof Brick) {
    //                                     this.hooks[i].resetHeigth();
    //                                     this.hooks[i] = null;
    //                                 }
    //                             }
    //                         }
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // }

    // public void CollisionHook(){
    //     for (int j = 0; j < this.balls.getSize(); j++) {
    //         for (int h = 0; h < elements.length; h++) {
    //             if (balls.getBall(j) != null && this.hook != null) {
    //                 if (this.hook.getRectangle().intersects(balls.getBall(j).getRectangle())) {
    //                     this.hook = null;
    //                     this.balls.dividir(this.balls.getBall(j));
    //                 } else if (this.elements[h] != null) {
    //                     if (this.hook.getRectangle().intersects(elements[h].getRectangle())) {
    //                         if (this.elements[h] instanceof BrickBreakable) {
    //                             this.hook = null;
    //                             this.elements[h] = null;
    //                         } else if (this.elements[h] instanceof Brick) {
    //                             this.hook = null;
    //                         }
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     if(this.hook != null){
    //         if(this.hook.ajustTop(game_zone)){
    //             this.hook = null;
    //         }
    //     }
    // }

    /*public void AumentarEliminar() {
        if (this.hook != null) {
            double originalx = this.hook.getRectangle().getMinX();
                if (this.hook.getRectangle().getMaxY() <= this.game_zone.getMaxY()) {
                    this.hook.resizeHeigth();
                    if (this.hook != null) {
                        this.hook = null;
                    }
                    if (this.hook == null) {
                        this.hook = new Hook(originalx, this.game_zone.getMinY());
                    }
                } else {
                   // this.hook.resizeHeigth();
                    this.hook = null;
                }
                for (int j = 0; j < this.balls.getSize(); j++) {
                    for (int h = 0; h < elements.length; h++) {
                        if (balls.getBall(j) != null && this.hook != null) {
                            if (this.hook.getRectangle().intersects(balls.getBall(j).getRectangle())) {
                                this.hook.resetHeigth();
                                this.hook = null;
                                this.balls.dividir(this.balls.getBall(j));
                            } else if (this.elements[h] != null) {
                                if (this.hook.getRectangle().intersects(elements[h].getRectangle())) {
                                    if (this.elements[h] instanceof BrickBreakable) {
                                        this.hook.resetHeigth();
                                        this.hook = null;
                                        this.elements[h] = null;
                                    } else if (this.elements[h] instanceof Brick) {
                                        this.hook.resetHeigth();
                                        this.hook = null;
                                    }
                                }
                            }
                        }
                    }
                }
            
        }
    }*/
}
