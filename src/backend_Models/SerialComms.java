/*
 * Created By Kell Larson for the Computer Science AP program during school year 2016/2017. Please ask before copying code.
 */
package backend_Models;

import app_Controller.Kaizen_85;
import arduinocomms2.AlertBox;
import arduinocomms2.Arduino;
import com.fazecast.jSerialComm.SerialPort;
import frontend_View.Settings;
import java.awt.Dimension;
import java.io.Closeable;
import java.util.Formatter;
import javafx.application.Platform;

/**
 * Talks to whatever is controlling the LEDs. uses the JArduino library to do
 * so.
 *
 * Takes the zero index from a string array shared between itself and
 * PixelControl, then chews through it, one string at a time. The string array
 * acts as a buffer between the 2 threads, and this class just grabs the index 0
 * string within the array, then moves the rest down 1 index for the next time
 * around. Then, it parses the string into 4 int values, resulting in a LED
 * position, and RGB values. Then it sends it off through serial port
 * communication to the arduino, or whatever is plugged in.
 *
 * @author Kell
 */
public class SerialComms implements Closeable {

    public SerialComms(Settings settings) {
        if (settings.getDebugMode()) {
            this.arduino = null;
        } else {
            try {
                this.arduino = new Arduino(settings.getSerialPort().getSystemPortName(), 9600); //enter the port name here, and ensure that Arduino is connected, otherwise exception will be thrown.
                arduino.openConnection();

                Kaizen_85.newEvent("SerialComms object constructed.");

                if (settings.getPin() > 255 || settings.getPin() < 0) {
                    throw new GeneralSettingsException("Pin # above acceptable range" + settings.getPin());
                }

                String pin = Integer.toHexString(settings.getPin());
                while(pin.length() < 2){
                    pin = "0" + pin;
                }
                //System.out.println(pin + " is the pin set.");
                Kaizen_85.newEvent("Pin as a hex: " + pin);
                
                String pixelsNum = Integer.toHexString(settings.getStripLength() * settings.getStripWidth());
                while(pixelsNum.length() < 3){
                    pixelsNum = "0" + pixelsNum;
                }
                
                Kaizen_85.newEvent("Pixel amount, as hexadecimal: " + pixelsNum);
                //System.out.println(pixelsNum + " is the set # of pixels.");
                
                String initString = "" + pin + pixelsNum + "z";
                
                arduino.serialWrite(initString);
                //System.out.println(initString);

            } catch (NullPointerException | GeneralSettingsException e) {
                AlertBox alert = new AlertBox(new Dimension(600, 200), "Setting Error", "Failed to intialize the serial communication layer: " + e);
                alert.display();
                Platform.exit();
            }
        }
    }

    private Arduino arduino;

    public void printPortsAvailable(SerialPort[] ports) {
        for (int i = 0; i < ports.length; i++) {

            System.out.println("Port " + i + "\t\t" + ports[i].getSystemPortName());
            System.out.println("Baud rate: " + ports[i].getBaudRate());
        }
    }

    @Override
    public void close() {
        this.arduino.closeConnection();
    }

    public void write(byte[] b) {
        //System.out.println(this.arduino.getPortDescription());
        arduino.byteArrayWrite(b);
    }

    public void write(byte[] b, int off, int len) {
        arduino.byteArrayWrite(b, off, len);
    }

    public void writeChars(String s) {
        arduino.serialWrite(s);
    }

    /**
     * Warning: int cannot be over 255, or below 0.
     *
     * @param i
     */
    public void writeInt255(int i) {
        if (i < 0 || i > 255) {
            throw new PixelValueException("Trying to write a value over 255 to the arduino.");
        } else {
            writeChar((char) i);
        }
    }

    public void writeShort(short s) {
        byte[] b = new byte[2];
        int m = 0;
        b[m] = (byte) ((s >> 8) & 0xff);
        m++;
        b[m] = (byte) (s & 0xff);
        this.write(b);
    }

    public void writeChar(char c) {
        this.arduino.serialWrite(c);
    }
}
