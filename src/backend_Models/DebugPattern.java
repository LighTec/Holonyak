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
public class DebugPattern extends Pattern {

        public DebugPattern(Settings set, SerialComms serial) throws GeneralSettingsException {
        super(set, serial, 255, 0, 0);
        Kaizen_85.newEvent("Debug Pattern Set");
    }
    
    @Override
    public void sendPattern(){
        Kaizen_85.newEvent("Debug Pattern started...");
         byte[] b = new byte[80];
            for(int i = 0; i < b.length; i++){
                b[i] = (byte)(255 & 0xFF);
            }
            getSerialComms().write(b);
    }
}
