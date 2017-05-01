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
public class ExamplePattern extends Pattern {

    private final int CMDNUMBER = 9999;
    
    public ExamplePattern(Settings set, SerialComms serial) {
        super(set, serial);
    }
    
    @Override
    public void startPattern() {
        throw new UnsupportedOperationException("Cannot start the example pattern, because IT IS AN EXAMPLE AND HAS NO CODE IN IT DUMMY.");
         // make a byte array of the command number plus the three colors plus the delay, as [cmd] [red] [green] [blue] [timer] [timer]
         
         /*
            The code used for ColorFillPattern:
         
            byte[] b = new byte[6];
            b[0] = (byte) this.CMDNUMBER;
            b[1] = (byte) (this.getRed() & 0xFF);
            b[2] = (byte) (this.getGreen() & 0xFF);
            b[3] = (byte) (this.getBlue() & 0xFF);
            b[4] = (byte) ((getDelay() >> 8)& 0xFF);
            b[5] = (byte) (getDelay()& 0xFF);
            getSerialComms().write(b);

         */
    }
}
