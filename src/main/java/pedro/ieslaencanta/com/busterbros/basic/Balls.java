/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

/**
 *
 * @author PC
 */
public class Balls {
    private Ball[] elements;
    protected int size;

    public Balls(int size) {
        this.size = size;
        this.elements = new Ball[size];
    }

    public int getSize() {
        return size;
    }

    public Ball getBall(int i) {
        Ball b = null;
        if (i >= 0 && i < getSize() && this.elements[i] != null) {
            b = this.elements[i];
        }
        return b;
    }

    public int addBall(Ball b) {
        int index = -1;
        for (int i = 0; i < this.elements.length && index == -1; i++) {
            if (this.elements[i] == null) {
                this.elements[i] = b;
                index = i;
            }
        }
        return index;
    }

    // public void addBall(Ball b) {
    //     // for (int i = 0; i < this.elements.length; i++) {
    //     //     if (this.elements[i] == null) {
    //     //         this.elements[i] = b;
    //     //     }
    //     // }
    //     this.elements[i] = b;
    // }

    public void removeBall(Ball b) {
        boolean borrado = false;
        for (int i = 0; i < this.elements.length && !borrado; i++) {
            if (b == this.elements[i]) {
                borrado = true;
                this.elements[i] = null;
            }
        }
    }

    public int getNumberOfElements() {
        int counter = 0;
        for (int i = 0; i < this.elements.length; i++) {
            if (this.elements[i] != null) {
                counter++;
            }
        }
        return counter;
    }

    public boolean isEmpty() {
        return (this.getNumberOfElements() <= 0);
    }
    
    public void dividir(Ball b){
        switch (b.getType()) {
            case EXTRABIG:
                Ball b1 = new Ball(b.getHorizontalGravity(), b.getVerticalGravity(), b.getVx(), b.getVy(), b.getCenterX(), b.getCenterY(), BallType.BIG, b.getColor());
                this.addBall(b1);
                b1.setVx(b.getVx());
                Ball b2 = new Ball(b.getHorizontalGravity(), b.getVerticalGravity(), -b.getVx(), b.getVy(), b.getCenterX(), b.getCenterY(), BallType.BIG, b.getColor());
                this.addBall(b2);
                b2.setVx(-b.getVx());
                removeBall(b);
                break;
            
            case BIG:
                Ball b3 = new Ball(b.getHorizontalGravity(), b.getVerticalGravity(), b.getVx(), b.getVy(), b.getCenterX(), b.getCenterY(), BallType.MEDIUM, b.getColor());
                this.addBall(b3);
                b3.setVx(b.getVx());
                Ball b4 = new Ball(b.getHorizontalGravity(), b.getVerticalGravity(), -b.getVx(), -b.getVy(), b.getCenterX(), b.getCenterY(), BallType.MEDIUM, b.getColor());
                this.addBall(b4);
                b4.setVx(-b.getVx());
                removeBall(b);
                break;
                
            case MEDIUM:
                Ball b5 = new Ball(b.getHorizontalGravity(), b.getVerticalGravity(), b.getVx(), b.getVy(), b.getCenterX(), b.getCenterY(), BallType.LITTLE, b.getColor());
                this.addBall(b5);
                b5.setVx(b.getVx());
                Ball b6 = new Ball(b.getHorizontalGravity(), b.getVerticalGravity(), -b.getVx(), b.getVy(), b.getCenterX(), b.getCenterY(), BallType.LITTLE, b.getColor());
                this.addBall(b6);
                b6.setVx(-b.getVx());
                removeBall(b);
                break;
                
            case LITTLE:
                removeBall(b);
            default:
                throw new AssertionError();
        }
    }
}
