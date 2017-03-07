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

/**
 *
 * @author kell-gigabyte
 */
public class PatternParseException extends Exception {

    /**
     * Creates a new instance of <code>PatternParseException</code> without
     * detail message.
     */
    public PatternParseException() {
    }

    /**
     * Constructs an instance of <code>PatternParseException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PatternParseException(String msg) {
        super(msg);
    }
}
