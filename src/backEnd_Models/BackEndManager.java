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
 * This is meant to run on a thread created from ModelsAndViewsControllerFX, and
 * manages both the SerialComms class, and the PixelControl class. Acts as the
 * glue between serial communication, pattern management, and the frontend GUI.
 *
 * @author kell-gigabyte
 */
public class BackEndManager {

    private final Settings settings;
    private PixelControl pixelControl;
    private SerialComms serialComms;

    /**
     *  Creates a backendmanager, which creates serial comm and pixelcontrol objects. Acts as a
     * bridge between the frontend, and these two backend classes.
     * @param s
     * @throws GeneralSettingsException
     */
    public BackEndManager(Settings s) throws GeneralSettingsException {
        this.settings = s;
        initialize();

    }

    private void initialize() throws GeneralSettingsException {
        Kaizen_85.newEvent("BackEndManager initialized.");
        if (!this.settings.getIsMatrix()) {        // if it is a strip
            this.pixelControl = new PixelControl(this.settings.getStripLength(), this.settings.getRGBW());
        } else if (this.settings.getIsMatrix()) {  // if it is a matrix
            this.pixelControl = new PixelControl(this.settings.getStripLength(), this.settings.getStripWidth(), this.settings.getRGBW());
        } else {
            GeneralSettingsException gse = new GeneralSettingsException("cannot tell if matrix or strip");
            Kaizen_85.panic();
            throw gse;
        }
    }

    protected boolean sendCmd(String s) {
        return false;
    }

}
