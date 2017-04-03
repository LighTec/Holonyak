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
import frontEnd_ViewController.Settings;

/**
 *
 * @author kell-gigabyte
 */
public class PatternBase {

    private final Settings settings;
    private PixelControl pixelControl;
    private SerialComms serialComms;

    public PatternBase(Settings s) throws GeneralSettingsException {
        this.settings = s;
        initialize();

    }

    /**
     * Initializes the patternBase class. Creates a pixelControl object and a SerialComms object.
     * @throws GeneralSettingsException 
     */
    private void initialize() throws GeneralSettingsException {
        Kaizen_85.newEvent("PatternBase, and all classes it get extended in initialized.");
        if (!this.settings.getIsMatrix()) {        // if it is a strip
            this.pixelControl = new PixelControl(this.settings.getStripLength());
        } else if (this.settings.getIsMatrix()) {  // if it is a matrix
            this.pixelControl = new PixelControl(this.settings.getStripLength(), this.settings.getStripWidth());
        } else {
            GeneralSettingsException gse = new GeneralSettingsException("cannot tell if matrix or strip");
            Kaizen_85.panic();
            throw gse;
        }
    }
    
     /**
     * The first step of a pattern. Parses the String to choose which methods to operate with.
     * @param s
     * @return true if success
     */
    private boolean parsePattern(String s) {
        return false;
    }
}
