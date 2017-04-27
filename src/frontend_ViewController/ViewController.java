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
import backend_Models.ColorFillPattern;
import backend_Models.Pattern;
import backend_Models.SerialComms;
import java.awt.Color;

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
    
    public void setPatternColor(Color c){
                this.pattern.setColor(c);
    }
    
    public void setPatternColor(int r, int g, int b){
        this.pattern.setColor(r, g, b);
    }
    
    public void setPatternDelay(int wait){
           this.pattern.setDelay(wait);
    }

    public void startPattern() {
        pattern.startPattern();
    }
}
