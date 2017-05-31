/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package frontend_View;

import app_Controller.Kaizen_85;
import com.fazecast.jSerialComm.SerialPort;

/**
 *
 * @author kell-gigabyte
 */
public class Settings {
    
    private SerialPort port;
    private int pin;
    
    private boolean isMatrix;
    private int stripLength;
    private int stripWidth;
    
    private boolean debugMode = false;
    
    
    public Settings(boolean isMatrix, String patternFolder, int stripLength, int stripWidth, SerialPort serialport, int pin){
        this.isMatrix = isMatrix;
        this.stripLength = stripLength;
        this.stripWidth = stripWidth;
        this.port = serialport;
        this.pin = pin;
        //System.out.println("Is it a Matrix? " + this.isMatrix + ", Length and Width? " + this.stripLength + "," + this.stripWidth + ", on port " + this.port + ", using pin # " + this.pin);
        Kaizen_85.newEvent("Settings initialized normally.");
    }
    
    public Settings(){
        this.debugMode = true;
        //System.err.println("Debug mode activated.");
        Kaizen_85.newEvent("Settings initialized uncorrectly, Debug mode enabled.");
    }
    
    public boolean getIsMatrix(){
         return this.isMatrix;
    }
           
    public int getStripLength(){
        return this.stripLength;
    }
    
    public int getStripWidth(){
        return this.stripWidth;
    }
    
    public SerialPort getSerialPort(){
        return this.port;
    }
    
    public int getPin(){
        return this.pin;
    }
    
    public boolean getDebugMode(){
        return this.debugMode;
    }
}