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

import app_Controller.Kaizen_85;
import frontend_View.Settings;

/**
 *
 * @author kell-gigabyte
 */
public class TheaterChasePattern extends Pattern {
    public TheaterChasePattern(Settings set, SerialComms serial) throws GeneralSettingsException {
        super(set, serial,5,3,1);
        Kaizen_85.newEvent("Theater Chase Pattern Set.");
    }
}
