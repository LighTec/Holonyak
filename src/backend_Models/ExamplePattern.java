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

import frontend_ViewController.Settings;

/**
 *
 * @author kell-gigabyte
 */
public class ExamplePattern extends AutoPattern {

    public ExamplePattern(Settings set, SerialComms serial) {
        super(set, serial);
    }
    
    @Override
    public void startPattern() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         // something like make a char array of the command char plus the three colors one after another in char form
    }
    
}
