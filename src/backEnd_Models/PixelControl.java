/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package backEnd_Models;

import app_Controller.Kaizen_85;

/**
 *This class is the manager for an array of Pixel objects. Acts as a messanger and "glue" between the
 * actions class within the GUI, the serial communications class, and the individual Pixel classes. Also connects
 * the Pattern class into the rest of the program, and control the Plugin class, to allow for additiona, easy mods
 * to be created for this program.
 * 
 * @author kell-gigabyte
 */
public class PixelControl {
    
    Pixel[] LEDmatrix;
    
    String patternFolder;
    
    public PixelControl(int length, boolean whitePixel){
        Kaizen_85.newEvent("PixelControl created, using a strip");
        this.LEDmatrix = new Pixel[length];
    }
    
    public PixelControl(int length, int width, boolean whitePixel){
        Kaizen_85.newEvent("PixelControl created, using a matrix");
    }
    
    public void setPatternFolder(String s){
        this.patternFolder = s;
    }
    
    public String getPatternFolder(){
        return this.patternFolder;
    }
    
    
    
}
