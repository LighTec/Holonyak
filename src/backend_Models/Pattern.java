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
public abstract class Pattern {

    protected final Settings settings;
    protected static SerialComms serialcomms;
    
    public Pattern(Settings set, SerialComms serial){
        this.settings = set;
        serialcomms = serial;
    }

    public void startPattern(){
         throw new UnsupportedOperationException("startPattern method has not been overridden!");
    }

    //public void startPattern(DemoDisplay d);           // will implement a demoDisplay for the GUI later hopefully.
}