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
import frontend_ViewController.Settings;

/**
 *
 * @author kell-gigabyte
 */
public class RainbowPattern extends Pattern {

    private final int CMDNUMBER = 6;

    public RainbowPattern(Settings set, SerialComms serial) {
        super(set, serial);
    }

    @Override
    public void startPattern() {
        Kaizen_85.newEvent("Rainbow Pattern Started.");

      byte[] b = new byte[3];
            b[0] = (byte) this.CMDNUMBER;
            b[1] = (byte) ((getDelay() >> 8)& 0xFF);
            b[2] = (byte) (getDelay()& 0xFF);
            getSerialComms().write(b);
    }

}
