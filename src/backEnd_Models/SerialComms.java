/*
 * Created By Kell Larson for the Computer Science AP program during school year 2016/2017. Please ask before copying code.
 */
package backEnd_Models;

import java.awt.Color;

/**
 *Talks to whatever is controlling the LEDs. uses the JArduino library to do so.
 * 
 * Takes the zero index from a string array shared between itself and PixelControl, then chews through it, one
 * string at a time. The string array acts as a buffer between the 2 threads, and this class just grabs the index 0
 * string within the array, then moves the rest down 1 index for the next time around. Then, it parses the string
 * into 4 int values, resulting in a LED position, and RGB values. Then it sends it off through serial port 
 * communication to the arduino, or whatever is plugged in.
 * 
 * @author Kell
 */
public abstract class SerialComms {
    
    private int port;   // Arduiino pin that the light strip/matrix is on.
    
    public SerialComms(int port){
        
    }
    
    /**
     *  Tells the Arduino to set pixel x to Color c. Uses the syntax of: 3 numbers for pixel num, 3 numbers
     * for Red, 3 numbers for Blue, 3 numbers for Green. For example: 001000100200 will result on a cyan-like
     * color.
     * @param pixel
     * @param c
     */
    protected void SendRGB(int pixel, Color c){
        
    }
    
    /**
     *  Sends a halt command to the Arduino, which is just a custom String of numbers telling 
     * it to zero all LED lights and not accept any further commands.
     */
    protected void SendHalt(){
        
    }
    
    public int getPort(){
        return this.port;
    }
}
