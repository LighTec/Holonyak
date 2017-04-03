/*
 * Created By Kell Larson for the Computer Science AP program during school year 2016/2017. Please ask before copying code.
 */
package backEnd_Models;

import arduinocomms2.Arduino;
import com.fazecast.jSerialComm.SerialPort;
import java.io.Closeable;
import java.io.DataOutput;
import java.io.Flushable;
import java.io.IOException;

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
public abstract class SerialComms implements Closeable, Flushable, DataOutput {

    public SerialComms() {
        SerialPort[] commPorts = SerialPort.getCommPorts();
        SerialPort commPort = commPorts[0];
        Arduino arduino = new Arduino(commPort.getSystemPortName(), 13000); //enter the port name here, and ensure that Arduino is connected, otherwise exception will be thrown.
        arduino.openConnection();

    }

    public void printPortsAvailable(SerialPort[] ports){
        for (int i = 0; i < ports.length; i++) {

            System.out.println("Port " + i + "\t\t" + ports[i].getSystemPortName());
            System.out.println("Baud rate: " + ports[i].getBaudRate());
        }
    }

    @Override
    public void flush() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void write(int b) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void write(byte[] arg0) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void write(byte[] arg0, int arg1, int arg2) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void writeChars(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeBoolean(boolean v) {
        throw new UnsupportedOperationException("Booleans are not properly read in the Arduino.");
    }

    @Override
    public void writeByte(int v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void writeShort(int v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void writeChar(int v) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void writeInt(int v) {
        throw new UnsupportedOperationException("Integers and Longs are not properly parses in the Arduino.");
    }

    @Override
    public void writeLong(long v) {
        throw new UnsupportedOperationException("Integers and Longs are not properly parses in the Arduino.");
    }

    @Override
    public void writeFloat(float v) {
        throw new UnsupportedOperationException("Floats and Doubles cannot be used to determine colors.");
    }

    @Override
    public void writeDouble(double v) {
        throw new UnsupportedOperationException("Floats and Doubles cannot be used to determine colors.");
    }

    @Override
    public void writeBytes(String s) {
        throw new UnsupportedOperationException("Unsupported for an Arduino.");
    }

    @Override
    public void writeUTF(String s) {
        throw new UnsupportedOperationException("Unsupported for an Arduino.");
    }
}
