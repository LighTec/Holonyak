/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package frontEnd_ViewController;

import com.fazecast.jSerialComm.SerialPort;

/**
 *
 * @author kell-gigabyte
 */
public class Settings {
    
    private SerialPort port;
    private boolean isMatrix;
    private String patternFolder;
    private int stripLength;
    private int stripWidth;
    
    
    public Settings(boolean isMatrix, String patternFolder, int stripLength, int stripWidth, SerialPort serialport){
        this.isMatrix = isMatrix;
        this.patternFolder = patternFolder;
        this.stripLength = stripLength;
        this.stripWidth = stripWidth;
        this.port = serialport;
        System.out.println(this.port);
    }
    
    public boolean getIsMatrix(){
         return this.isMatrix;
    }
    
    public String getPatternFolder(){
        return this.patternFolder;
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
}
