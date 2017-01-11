/*
 * Created By Kell Larson for the Computer Science AP program during school year 2016/2017. Please ask before copying code.
 */
package backEnd_Models;

/**
 *
 * @author Kell
 */
public class LED {
    /**
     * This class serves an a singular object for registering values for each
     * LED controlled. Holds its position and RBG values. Meant to be put into
     * an array to set values from, and to be extended into a pixel object. More or less acts as a class to hold
     * values, protecting them from unsafe use.
     * 
     */

    private int red;    // 0-255
    private int blue;   // 0-255
    private int green;  // 0-255

    private int addressX;
    private int addressY;   //only used if part of a matrix
    private int pwmNumber;

    public LED(int addressX, int addressY, int pwmNumber) { //LED matrix setup
        this.red = 0;
        this.blue = 0;
        this.green = 0;
        this.addressX = addressX;
        this.addressY = addressY;
        this.pwmNumber = pwmNumber;
    }
    
    public LED(int addressX){   //LED strip or ring setup
        this.red = 0;
        this.blue = 0;
        this.green = 0;
        this.addressX = addressX;
        this.pwmNumber = addressX;
    }
    
    public void setRGB(int red, int green, int blue){ //sets the RGB color
        if(red > 255){
            System.err.println("red value exceeded 255. Original value: " + red);
            red = 255;
        }
        if(blue > 255){ 
            System.err.println("blue value exceeded 255. Original value: " + blue);
            blue = 255;
        }
        if(green > 255){
            System.err.println("green value exceeded 255. Original value: " + green);
            green = 255;
        }
        this.red = red;
        this.blue = blue;
        this.green = green;
    }
    
    /**
     * 
     * return statements for RGB and positioning logic
     */
    
    public int getRed(){
        return this.red;
    }
    
    public int getGreen(){
        return this.green;
    }
    
    public int getBlue(){
        return this.blue;
    }
    
    public int getPosX(){
        return this.addressX;
    }
    
    public int getPosY(){
        return this.addressY;
    }
    
    public int getPWM(){
        return this.pwmNumber;
    }
}
