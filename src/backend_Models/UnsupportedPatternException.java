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

/**
 *
 * @author kell-gigabyte
 */
public class UnsupportedPatternException extends Exception {

    public UnsupportedPatternException() {
        super("Unsupported Pattern. The code for this pattern either does not exist (yet), or is broken.");
    }

    public UnsupportedPatternException(String msg) {
        super("Unsupported Pattern. The code for this pattern either does not exist (yet), or is broken. Specific message: " + msg);
    }
}
