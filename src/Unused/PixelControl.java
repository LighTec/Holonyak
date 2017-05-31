/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package Unused;

import app_Controller.Kaizen_85;
import backend_Models.Pixel;
import backend_Models.Pixel;
import backend_Models.SerialComms;
import backend_Models.SerialComms;
import frontend_View.Settings;
import java.awt.Color;

/**
 * This class is the manager for an array of PixelOld objects. Acts as a
 * messanger and "glue" between the actions class within the GUI, the serial
 * communications class, and the individual PixelOld classes. Also connects the
 * Pattern class into the rest of the program, and control the Plugin class, to
 * allow for additiona, easy mods to be created for this program.
 *
 * @author kell-gigabyte
 */
public class PixelControl {
    
    Pixel[] pixels;
    
    private Settings settings;
    private SerialComms comms;
    
    private int length;
    private int width;
    
    public PixelControl(Settings settings) {
        this.settings = settings;
        this.comms = new SerialComms(this.settings);
        int m = 0;
        if (this.settings.getIsMatrix()) {
            this.pixels = new Pixel[this.length * this.width];
            for (int l = 0; l < this.length; l++) {
                for (int w = 0; w < this.width; w++, m++) {
                    pixels[m] = new Pixel(l, w, m);
                }
            }
            Kaizen_85.newEvent("PixelControl created, as a matrix.");
        } else {
            this.pixels = new Pixel[length];
            for (Pixel pixel : this.pixels) {
                pixel = new Pixel(m);
                m++;
            }
            Kaizen_85.newEvent("PixelControl created, as a strip.");
        }
    }
    
    protected void setPixel(int index, Color c) {
        this.pixels[index].setRGB(c);
    }
    
    protected void setPixel(int index, int red, int green, int blue) {
        this.pixels[index].setRGB(red, green, blue);
    }
    
    protected void setPixel(int x, int y, Color c) throws PatternParseException {
        if (this.settings.getIsMatrix() == false) {
            throw new PatternParseException("The LEDs are not set to be a matrix, cannot call via x and y coordinates.");
        } else {
            this.pixels[x * y].setRGB(c);
        }
    }

    protected void setPixel(int x, int y, int red, int green, int blue) throws PatternParseException {
        if (this.settings.getIsMatrix() == false) {
            throw new PatternParseException("The LEDs are not set to be a matrix, cannot call via x and y coordinates.");
        } else {
            this.pixels[x * y].setRGB(red, green, blue);
        }
    }
     
    /**
     *  Uses String s to send a pattern command to the Arduino.
     * @param s 
     */
    public void frame(String s){
        this.comms.writeChars(s);
    }
    
    /**
     *  Sends all pixel values manually to the Arduino.
     */
    public void customFrame(){
        // TODO: write a method to turn the enture array to bytes as per the structure written in the Arduino.
    }
    
}
