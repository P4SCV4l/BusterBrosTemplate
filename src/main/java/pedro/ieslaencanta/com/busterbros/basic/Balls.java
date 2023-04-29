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

    // public void addBall(Ball b, int i) {
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
}
