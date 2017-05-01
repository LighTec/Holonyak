/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package frontend_ViewController;

import app_Controller.Kaizen_85;
import arduinocomms2.AlertBox;
import backend_Models.ColorFillPattern;
import backend_Models.Pattern;
import backend_Models.RainbowCyclePattern;
import backend_Models.RainbowPattern;
import backend_Models.SerialComms;
import backend_Models.TheaterChasePattern;
import backend_Models.TheaterChaseRainbowPattern;
import java.awt.Color;
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

    public void setFillPattern() {
        pattern = new ColorFillPattern(this.settings, serialComms);
    }

    public void setRainbowPattern() {
        pattern = new RainbowPattern(this.settings, serialComms);
    }

    public void setRainbowCyclePattern() {
        pattern = new RainbowCyclePattern(this.settings, serialComms);
    }

    public void setTheaterChasePattern() {
        pattern = new TheaterChasePattern(this.settings, serialComms);
    }

    public void setTheaterChaseRainbowPattern() {
        pattern = new TheaterChaseRainbowPattern(this.settings, serialComms);
    }

    /*
    public void setPattern(){
        pattern = new Pattern(this.settings, serialComms);
    }
     */
    public void setPatternColor(Color c) {
        try {
            pattern.setColor(c);
        } catch (NullPointerException e) {
            AlertBox alert = new AlertBox(new Dimension(400, 200), "Set color error", "Please choose a pattern before setting its color.");
            alert.display();
        }
    }

    public void setPatternColor(int r, int g, int b) {
        try {
            pattern.setColor(r, g, b);
        } catch (NullPointerException e) {
            AlertBox alert = new AlertBox(new Dimension(400, 200), "Set color error", "Please choose a pattern before setting its color.");
            alert.display();
        }

    }

    public void setPatternDelay(int wait) {
        try {
            pattern.setDelay(wait);
        } catch (NullPointerException e) {
            AlertBox alert = new AlertBox(new Dimension(400, 200), "Set delay error", "Please choose a pattern before setting its delay.");
            alert.display();
        }
    }

    public void startPattern() {
        try {
            pattern.startPattern();
        } catch (NullPointerException e) {
            AlertBox alert = new AlertBox(new Dimension(400, 200), "Pattern start error", "Please choose a pattern before starting it...");
            alert.display();
        }
    }
}
