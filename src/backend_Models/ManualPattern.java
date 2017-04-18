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

import frontend_ViewController.Settings;
import java.awt.Color;

/**
 *  Used if there is no code within the Arduino for the pattern implementing the
 * pattern super-interface. Returns booleans if the color or index values are above what is 
 * allowed (over the max LED index, or below 0 / above 255 for color values)
 * 
 * @author kell-gigabyte
 */
public abstract class ManualPattern extends Pattern {

    public ManualPattern(Settings set, SerialComms serial) {
        super(set, serial);
    }

    public boolean setColor(Color c, int index){
        return false;
    }

    public boolean setColor(int r, int g, int b, int index){
        return false;
    }
    
    public boolean setColor(Color c, int x, int y){
        return false;
    }

    public boolean setColor(int r, int g, int b, int x, int y){
        return false;
    }

}
