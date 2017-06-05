/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package backend_Models;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author kell-gigabyte
 */
public class KeyPressRectangle extends Rectangle {

    private int red, green, blue, delay;

    public KeyPressRectangle(int h, int w) {
        super(h, w);
    }

    public KeyPressRectangle(int h, int w, Paint p) {
        super(h, w, p);
    }

    public KeyPressRectangle(int h, int w, int r, int g, int b, int del) {
        super(h, w);
        this.red = r;
        this.green = g;
        this.blue = b;
        this.delay = del;
        updateColors();
    }

    public void setAll(int r, int g, int b, int delay) {
        this.red = r;
        this.green = g;
        this.blue = b;
        this.delay = delay;
        updateColors();
    }

    public void setRed(int r) {
        this.red = r;
        updateColors();
    }

    public void setGreen(int g) {
        this.green = g;
        updateColors();
    }

    public void setBlue(int b) {
        this.blue = b;
        updateColors();
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void updateColors() {
        this.setFill(Color.rgb(this.red, this.green, this.blue));
    }

    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

    public int getDelay() {
        return this.delay;
    }
}
