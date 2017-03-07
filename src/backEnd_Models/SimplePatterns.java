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

import frontEnd_ViewController.Settings;
import java.awt.Color;

/**
 *
 * @author kell-gigabyte
 */
public class SimplePatterns extends PatternBase{
    
    public SimplePatterns(Settings s) throws GeneralSettingsException {
        super(s);
    }
    
      /**
     * Subtracts x amount of color (red, blue, green, yellow, purple, orange) from pixel y.
     * @param colorName
     * @param amount
     * @param pixel 
     */
    private void subtract(String colorName, int amount, int pixel) {

    }

    /**
     * Adds x amount of color (red, blue, green, yellow, purple, orange) to pixel y.
     * @param colorName
     * @param amount
     * @param pixel 
     */
    private void add(String colorName, int amount, int pixel) {

    }

    /**
     * Adds all the RGB values of pixel x by y amount.
     * @param amount
     * @param pixel 
     */
    private void lighten(int amount, int pixel) {

    }

    /**
     * Subtracts all the RGB values of pixel x by y amount.
     * @param amount
     * @param pixel 
     */
    private void darken(int amount, int pixel) {

    }
    
    
/**
 * shifts all pixel values "Left" x amount of times, e.g. allowing a wave to form.
 * @param times 
 */
    private void shiftLeft(int times) {

    }

    /**
     * shifts all pixel values "Right" x amount of times, e.g. allowing a wave to form.
     * @param times 
     */
    private void shiftRight(int times) {

    }

    /**
     * sets pixel x to become a random Color.
     * @param pixel 
     */
    private void randomColor(int pixel) {

    }

    /**
     * makes all pixel RGB values 0,0,0.
     */
    private void clearColors() {

    }
    
    /**
     * sets color c to pixel x.
     * @param c1
     * @param pixel 
     */
    private void setColor(Color c1, int pixel) {

    }
}
