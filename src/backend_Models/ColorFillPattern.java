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
public class ColorFillPattern extends Pattern {

    public ColorFillPattern(Settings set, SerialComms serial) {
        super(set, serial);
    }
    
    @Override
    public void startPattern() {
        System.out.println("Pattern started");
        byte[] b = new byte[4];
        b[0] = (byte)getDelay();
        b[1] = (byte)getRed();
        b[2] = (byte)getGreen();
        b[3] = (byte)getBlue();
        getSerialComms().write(b);
        
    }
    
    @Override
    public void stopPattern(){
        throw new UnsupportedOperationException("Not supported yet.");
        // This method must make the pattern send the kill String, and stop sending anything more into the serial port.
    }
    
}
