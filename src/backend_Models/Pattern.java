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

import frontend_View.Settings;
import java.awt.Color;

/**
 *
 * @author kell-gigabyte
 */
public abstract class Pattern {

    private int commandNum = -1;
    private int colors = -1;
    private int delays = -1;

    private boolean keyReact = false;

    private Color[] Colors;
    private int[] Delays;

    private byte[] serialBytes;

    private final Settings settings;
    private static SerialComms serialcomms;

    /*
    public Pattern(Settings set, SerialComms serial, int colors, int delays) {
        this.settings = set;
        serialcomms = serial;
        this.colors = colors;
        this.delays = delays;
        this.Colors = new Color[this.colors];
        this.Delays = new int[this.delays];
    }
     */
    public Pattern(Settings set, SerialComms serial, int commandNum, int colors, int delays) throws GeneralSettingsException {
        this.settings = set;
        serialcomms = serial;
        this.colors = colors;
        Colors = new Color[colors];
        this.delays = delays;
        Delays = new int[delays];
        this.commandNum = commandNum;
        int bytelength = (colors * 3) + (delays * 2) + 3; // additional 3, for the escape and command bytes
        this.serialBytes = new byte[bytelength];
    }

    public void setKeyReaction(boolean b) {
        this.keyReact = b;
    }

    public int getAmountOfColors() {
        return this.colors;
    }

    public int getAmountOfDelays() {
        return this.delays;
    }

    protected void setAmountOfColors(int i) {
        this.colors = i;
        Color[] newArr = new Color[this.colors];
        for (int k = 0; k < newArr.length; k++) {
            newArr[k] = this.Colors[k];
        }
        this.Colors = newArr;
    }

    protected void setAmountOfDelays(int i) {
        this.delays = i;
        int[] newArr = new int[this.delays];
        for (int k = 0; k < newArr.length; i++) {
            newArr[k] = this.Delays[k];
        }
    }

    public void setColor(Color c, int index) {
        this.Colors[index] = c;
    }

    public void setColor(int r, int g, int b, int index) {
        Color c = new Color(r, g, b);
        this.Colors[index] = c;
    }

    public void setDelay(int delay, int index) {
        this.Delays[index] = delay;
    }

    public int getRed(int index) {
        return this.Colors[index].getRed();
    }

    public int getGreen(int index) {
        return this.Colors[index].getGreen();
    }

    public int getBlue(int index) {
        return this.Colors[index].getBlue();
    }
    
    public Color getColor(int index){
        return this.Colors[index];
    }

    public int getDelay(int index) {
        return this.Delays[index];
    }

    public SerialComms getSerialComms() {
        return serialcomms;
    }

    public void sendPattern() {
        
        serialBytes[0] = (byte) (255 & 0xFF);
        serialBytes[1] = (byte) (this.commandNum & 0xFF);
        if (keyReact) {
            serialBytes[2] = (byte) (127 & 0xFF);
        } else {
            serialBytes[2] = (byte) (0 & 0xFF); // Could just use 0xFF, just wanted to use this to avoid confusion
        }
        int i = 3;
        for (Color temp : Colors) {
            this.serialBytes[i] = (byte) (temp.getRed() & 0xFF);
            this.serialBytes[i + 1] = (byte) (temp.getGreen() & 0xFF);
            this.serialBytes[i + 2] = (byte) (temp.getBlue() & 0xFF);
            i += 3;
        }
        for (int d : Delays) {
            this.serialBytes[i] = (byte) ((d >> 8) & 0xFF);
            this.serialBytes[i + 1] = (byte) (d & 0xFF);
            i += 2;
        }
        sendByteArray(serialBytes);
    }

    public void sendByteArray(byte[] b) {
        getSerialComms().write(b);
    }

    public Settings getSettings() {
        return this.settings;
    }

    public void start() {
        sendPattern();
    }

    //public void start(DemoDisplay d);           // will implement a demoDisplay for the GUI later hopefully.
}
