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

/**
 *
 * @author kell-gigabyte
 */
public class Settings {
    
    private boolean RGBW;
    private boolean isMatrix;
    private String patternFolder;
    private int stripLength;
    private int stripWidth;
    
    public Settings(boolean RGBW, boolean isMatrix, String patternFolder, int stripLength, int stripWidth){
        this.RGBW = RGBW;
        this.isMatrix = isMatrix;
        this.patternFolder = patternFolder;
        this.stripLength = stripLength;
        this.stripWidth = stripWidth;
    }
    
    public boolean getRGBW(){
        return this.RGBW;
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
}
