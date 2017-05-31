/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package Unused;

import Unused.PixelControl;
import app_Controller.Kaizen_85;
import backend_Models.GeneralSettingsException;
import backend_Models.SerialComms;
import frontend_View.Settings;
import java.awt.Color;

/**
 *
 * @author kell-gigabyte
 */
public class Pattern {

    public final Settings settings;
    public PixelControl pixelControl;
    public SerialComms serialComms; 

    public Pattern(Settings s) throws GeneralSettingsException {
        this.settings = s;
        initialize();

    }

    /**
     * Initializes the patternBase class. Creates a pixelControl object and a SerialComms object.
     * @throws GeneralSettingsException 
     */
    public void initialize() throws GeneralSettingsException {
        Kaizen_85.newEvent("PatternBase, and all classes it get extended in initialized.");
            this.pixelControl = new PixelControl(this.settings);
    }
     /**
     * Subtracts x amount of color (red, blue, green, yellow, purple, orange) from pixel y.
     * @param colorName
     * @param amount
     * @param pixel 
     */
    public void subtract(String colorName, int amount, int pixel) {

    }

    /**
     * Adds x amount of color (red, blue, green, yellow, purple, orange) to pixel y.
     * @param colorName
     * @param amount
     * @param pixel 
     */
    public void add(String colorName, int amount, int pixel) {

    }

    /**
     * Adds all the RGB values of pixel x by y amount.
     * @param amount
     * @param pixel 
     */
    public void lighten(int amount, int pixel) {

    }

    /**
     * Subtracts all the RGB values of pixel x by y amount.
     * @param amount
     * @param pixel 
     */
    public void darken(int amount, int pixel) {

    }
    
    
/**
 * shifts all pixel values "Left" x amount of times, e.g. allowing a wave to form.
 * @param times 
 */
    public void shiftLeft(int times) {

    }

    /**
     * shifts all pixel values "Right" x amount of times, e.g. allowing a wave to form.
     * @param times 
     */
    public void shiftRight(int times) {

    }

    /**
     * sets pixel x to become a random Color.
     * @param pixel 
     */
    public void randomColor(int pixel) {

    }

    /**
     * makes all pixel RGB values 0,0,0.
     */
    public void clearColors() {

    }
    
    /**
     * sets color c to pixel x.
     * @param c1
     * @param pixel 
     */
    public void setColor(Color c1, int pixel) {

    }
}
