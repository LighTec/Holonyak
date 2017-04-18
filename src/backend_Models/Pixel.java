/*
 * Created By Kell Larson for the Computer Science AP program during school year 2016/2017. Please ask before copying code.
 */
package backend_Models;

import java.awt.Color;

/**
 *
 * @author Kell
 */
public class Pixel {

    /**
     * This class serves an a singular object for registering values for each
     * Pixel controlled. Holds its position and RBG values. Meant to be put into
     * an array to set values from, and to be extended into a pixel object. More
     * or less acts as a class to hold values, protecting them from unsafe use.
     *
     */

    private int red;    // 0-255
    private int blue;   // 0-255
    private int green;  // 0-255

    private int addressX;
    private int addressY;   //only used if part of a matrix
    private int pwmNumber; //only used of part of a matrix

    public Pixel(int addressX, int addressY, int pwmNumber) { //LED matrix setup
        this.red = 0;
        this.blue = 0;
        this.green = 0;
        this.addressX = addressX;
        this.addressY = addressY;
        this.pwmNumber = pwmNumber;
    }

    public Pixel(int addressX) {   //LED strip or ring setup
        this.red = 0;
        this.blue = 0;
        this.green = 0;
        this.addressX = addressX;
        this.pwmNumber = addressX;
    }

    /**
     * Sets the red, green, and blue values. Obviously.
     *
     * @param red
     * @param green
     * @param blue
     */
    public void setRGB(int red, int green, int blue) { //sets the RGB color
        if (red > 255) {
            System.err.println("red value exceeded 255. Original value: " + red);
            red = 255;
        }
        if (blue > 255) {
            System.err.println("blue value exceeded 255. Original value: " + blue);
            blue = 255;
        }
        if (green > 255) {
            System.err.println("green value exceeded 255. Original value: " + green);
            green = 255;
        }
        this.red = red;
        this.blue = blue;
        this.green = green;
    }
    
    public void setRGB(Color c){
        this.red = c.getRed();
        this.green = c.getGreen();
        this.blue = c.getBlue();
    }

    /**
     * Prints out the red value, then the (red + green + blue) / 3 value, then
     * the 256 - ((red + green +blue) / 3) value, then prints out the lesser of
     * either the green or red values (the maximum amount of yellow). Got bored after watching
     * RWBY season 4 episode 3-ish.
     */
    public void printRWBY() {
        int white = (this.red + this.blue + this.green) / 3;
        int black = 256 - white;
        int yellow;
        if (this.red >= this.green) {
            yellow = this.green;
        } else {
            yellow = this.red;
        }
        System.out.println("An easter egg!\nRed value: " + this.red + "\nWhite value: " + white + "\nBlack value: " + black + "\nYellow value:" + yellow);
    }

    /*
     * 
     * return statements for RGB and positioning logic
     */
    public int getRed() {
        return this.red;
    }

    public int getGreen() {
        return this.green;
    }

    public int getBlue() {
        return this.blue;
    }

    public int getPosX() {
        return this.addressX;
    }

    public int getPosY() {
        return this.addressY;
    }

    public int getPWM() {
        return this.pwmNumber;
    }
}
