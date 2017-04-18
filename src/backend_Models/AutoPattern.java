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
 * Used if there's code within the Arduino for the pattern implementing the
 * pattern super-interface. Booleans shoudl return false if the set___ failed,
 * or because the pattern does not require it.
 *
 * @author kell-gigabyte
 */
public abstract class AutoPattern extends Pattern {

    protected int red;
    protected int green;
    protected int blue;
    protected int delay;

    public AutoPattern(Settings set, SerialComms serial) {
        super(set, serial);
    }

    public void setColor(Color c) {
        this.red = c.getRed();
        this.green = c.getGreen();
        this.blue = c.getBlue();
    }

    public boolean setColor(int r, int g, int b) {
        if(r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) // if any color values exceed 255, or less than 0
            return false;
        this.red = r;
        this.green = g;
        this.blue = b;
        return true;
    }

    public void setDelay(int i) {
        this.delay = i;
    }
}
