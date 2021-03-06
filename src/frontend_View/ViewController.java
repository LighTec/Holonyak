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
import arduinocomms2.AlertBox;
import backend_Models.ColorFillPattern;
import backend_Models.CpuMemoryPattern;
import backend_Models.DebugPattern;
import backend_Models.GeneralSettingsException;
import backend_Models.KeyPressRectangle;
import backend_Models.Pattern;
import backend_Models.RainbowCyclePattern;
import backend_Models.RainbowPattern;
import backend_Models.SerialComms;
import backend_Models.TheaterChasePattern;
import backend_Models.TheaterChaseRainbowPattern;
import backend_Models.TimePattern;
import backend_Models.tetris.TetrisPattern;
import java.awt.Dimension;

/**
 *
 * @author kell-gigabyte
 */
public class ViewController {

    private static Pattern pattern;
    private static SerialComms serialComms;
    private final Settings settings;

    public ViewController(Settings s) {
        Kaizen_85.newEvent("ViewController Created");
        this.settings = s;
        serialComms = new SerialComms(this.settings);
    }

    protected void HelloWorld() {
        Kaizen_85.newEvent("Hello World button pressed, HELLO WORLD.");
        Kaizen_85.panic();
    }

    private boolean isStarted = false;

    protected boolean startStop() {
        if (isStarted) {

        } else {

        }
        isStarted = !isStarted;
        return this.isStarted;
    }

    public void setPattern(Pattern pat) {
        pattern = pat;
    }

    public void keyReact() {
        try {
            pattern.keyEvent();
        } catch (NullPointerException e) {

        }
    }

    public void setEnableKeyReact(boolean b) {
        try {
            pattern.setEnableKeyEvent(b);
        } catch (NullPointerException e) {

        }
    }

    public void setKeyReactSettings(KeyPressRectangle kpr) {
        pattern.setKeyPressSettings(kpr);
    }

    /**
     * Sets the pattern as a fill, just "fills in" all of the pixels, 1 by 1,
     * with the delay being the wait time between each pixel being filled in.
     */
    public void setFillPattern() {
        this.endPattern();
        try {
            pattern = new ColorFillPattern(this.settings, serialComms);
        } catch (GeneralSettingsException ex) {
            System.err.println("Pattern generation failed.");
        }
    }

    /**
     * Acts like the fill pattern, however cycles through the rainbow
     * constantly, at a set delay between each color change.
     */
    public void setRainbowPattern() {
        this.endPattern();
        try {
            pattern = new RainbowPattern(this.settings, serialComms);
        } catch (GeneralSettingsException ex) {
            System.err.println("Pattern generation failed.");
        }
    }

    /**
     * Draws the entire rainbow on the pixels, and slowly cycles them to appear
     * to be "flowing".
     */
    public void setRainbowCyclePattern() {
        this.endPattern();
        try {
            pattern = new RainbowCyclePattern(this.settings, serialComms);
        } catch (GeneralSettingsException ex) {
            System.err.println("Pattern generation failed.");
        }
    }

    /**
     * One pixel on, the next two off. Then shifts the "on" pixel over by 1.
     */
    public void setTheaterChasePattern() {
        this.endPattern();
        try {
            pattern = new TheaterChasePattern(this.settings, serialComms);
        } catch (GeneralSettingsException ex) {
            System.err.println("Pattern generation failed.");
        }
    }

    /**
     * One pixel on, the next two off. Then shifts the "on" pixel over by 1.
     * Pixel color values are calculated to show the entire RGB spectrum over
     * the LED strip / matrix.
     */
    public void setTheaterChaseRainbowPattern() {
        this.endPattern();
        try {
            pattern = new TheaterChaseRainbowPattern(this.settings, serialComms);
        } catch (GeneralSettingsException ex) {
            System.err.println("Pattern generation failed.");
        }
    }

    public void setTimePattern() {
        this.endPattern();
        try {
            pattern = new TimePattern(this.settings, serialComms);
        } catch (GeneralSettingsException ex) {
            System.err.println("Pattern generation failed.");
        }
    }

    public void setCpuMemPattern() {
        this.endPattern();
        try {
            pattern = new CpuMemoryPattern(this.settings, serialComms);
        } catch (GeneralSettingsException ex) {
            System.err.println("Pattern generation failed.");
        }
    }

    public void setDebugPattern() {
        this.endPattern();
        try {
            pattern = new DebugPattern(this.settings, serialComms);
        } catch (GeneralSettingsException ex) {
            System.err.println("Pattern generation failed.");
        }
    }
    
    public void setTetrisPattern() {
        this.endPattern();
        try {
            pattern = new TetrisPattern(this.settings, serialComms);
        } catch (GeneralSettingsException ex) {
            System.err.println("Pattern generation failed.");
        }
    }

    /*
    public void setTextPattern(){
    this.endPattern();
        try{
            pattern = new TextPattern(this.settings, serialComms);
        } catch (GeneralSettingsException ex) {
            System.err.println("Pattern generation failed.");
        }
    }
     */
    public void debugDisplay() {
        setDebugPattern();
        pattern.start();
    }

    public void setPatternColor(int r, int g, int b, int index) {

        try {
            //System.out.println(r + " " + g + " " + b + "   " + index);
            pattern.setColor(r, g, b, index);
        } catch (NullPointerException e) {
            AlertBox alert = new AlertBox(new Dimension(400, 200), "Set color error", "Please choose a pattern before setting its color.");
            alert.display();
        }

    }

    public void setPatternDelay(int wait, int index) {
        //System.out.println(wait + "   " + index);
        try {
            pattern.setDelay(wait, index);
        } catch (NullPointerException e) {
            AlertBox alert = new AlertBox(new Dimension(400, 200), "Set delay error", "Please choose a pattern before setting its delay.");
            alert.display();
        }
    }

    /**
     * Starts the pattern by sending byte values over USB. creates a pop-up if
     * the pattern is null.
     */
    public void startPattern() {
        try {
            pattern.start();
        } catch (NullPointerException e) {
            AlertBox alert = new AlertBox(new Dimension(400, 200), "Pattern start error", "Please choose a pattern before starting it...");
            alert.display();
        }
    }

    /**
     * Gets the current pattern, as a reference. Can be used to change pattern
     * variables, instantiate new patterns, or read values from it.
     *
     * @return pattern
     */
    public Pattern getPattern() {
        return pattern;
    }

    /**
     * Clears all colors from the display, and sets the pattern variable to
     * null. Used to return the pixel display to it's natural state, or "off".
     */
    public void ClearPattern() {
        Kaizen_85.newEvent("Clearing color and setting pattern to null.");
        setFillPattern();
        pattern.setColor(0, 0, 0, 0);
        pattern.setDelay(0, 0);
        pattern.setKeyPressSettings(new KeyPressRectangle(33, 33, 0, 0, 0, 0));
        pattern.start();
        pattern = null;
    }

    private void endPattern() {
        try {
            pattern.stop();
        } catch (NullPointerException e) {

        }
    }
}
