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
 * The base class used for all patterns, allows for the creation of new patterns
 * easily, as long as you also provide similar code on the Arduino side.
 *
 * @author kell-gigabyte
 */
public abstract class Pattern {

    private int commandNum = -1;
    private int colors = -1;
    private int delays = -1;

    private boolean keyReact = false;
    private boolean hasStarted = false;

    private Color[] Colors;
    private int[] Delays;

    private Color[] updateColors;
    private int[] updateDelays;

    private byte[] startBytes;
    private byte[] updateBytes;

    private final Settings settings;
    private static SerialComms serialcomms;

    private Color keyPressColor;
    private int keyPressDelay;

    public Pattern(Settings set, SerialComms serial, int commandNum, int colors, int delays) throws GeneralSettingsException {
        this.settings = set;
        serialcomms = serial;
        this.colors = colors;
        Colors = new Color[colors];
        for (int i = 0; i < this.colors; i++) {
            Colors[i] = new Color(0, 0, 0);
        }
        this.delays = delays;
        Delays = new int[delays];
        for (int i = 0; i < this.delays; i++) {
            Delays[i] = 0;
        }
        this.commandNum = commandNum;
        int bytelength = (colors * 3) + (delays * 2) + 7; // additional 3, for the escape, command, keypress bytes
        this.startBytes = new byte[bytelength];
    }
    
    public Pattern(Settings set, SerialComms serial, int commandNum, int colors, int delays, int hiddenColors, int hiddenDelays) throws GeneralSettingsException {
        this.settings = set;
        serialcomms = serial;
        this.colors = colors;
        Colors = new Color[colors];
        for (int i = 0; i < this.colors; i++) {
            Colors[i] = new Color(0, 0, 0);
        }
        this.delays = delays;
        Delays = new int[delays];
        for (int i = 0; i < this.delays; i++) {
            Delays[i] = 0;
        }
        this.commandNum = commandNum;
        int bytelength = (colors * 3) + (delays * 2) + 7; // additional 3, for the escape, command, keypress bytes
        this.startBytes = new byte[bytelength];
        
        this.updateColors = new Color[hiddenColors];
        for (int i = 0; i < hiddenColors; i++) {
            this.updateColors[i] = new Color(0, 0, 0);
        }
        //System.out.println(hiddenDelays + " Hidden delays");
        this.updateDelays = new int[hiddenDelays];
        for (int i = 0; i < hiddenDelays; i++) {
            this.updateDelays[i] = 0;
        }
    }

    /**
     * Enables or disables the Arduion to react to key presses. More
     * specifically, decides whether or not to send a key press byte when the
     * keyEvent() method is called.
     *
     * @param b
     */
    public void setKeyReaction(boolean b) {
        this.keyReact = b;
    }

    public int getAmountOfColors() {
        return this.colors;
    }

    public int getAmountOfDelays() {
        return this.delays;
    }
    
    public boolean hasStarted(){
        return this.hasStarted;
    }

    /**
     * Sets the size of the array of Colors used for updating patterns.
     *
     * @param amount
     */
    public void setAmountofUpdateColors(int amount) {
        this.updateColors = new Color[amount];
        for (int i = 0; i < amount; i++) {
            this.updateColors[i] = new Color(0, 0, 0);
        }
    }

    /**
     * Sets the size of the array of integers used for updating patterns.
     *
     * @param amount
     */
    public void setAmountofUpdateDelays(int amount) {
        this.updateDelays = new int[amount];
        for (int i = 0; i < amount; i++) {
            this.updateDelays[i] = 0;
        }
    }

    public int getAmountofUpdateColors() {
        return this.updateColors.length;
    }

    public int getAmountofUpdateDelays() {
        return this.updateDelays.length;
    }

    public void setUpdateColor(int r, int g, int b, int index) {
        //System.out.println(r + " " + g + " " + b  + " " + index);
        this.updateColors[index] = new Color(r, g, b);
        //System.out.println(this.updateColors[index].toString());
    }

    public void setUpdateDelay(int delay, int index) {
       // System.out.println("Setting Delay array index " + index + " to " + delay);
        this.updateDelays[index] = delay;
      //  System.out.println("done.");
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
        //System.out.println(c.getRed() + " " + c.getGreen() + " " + c.getBlue() + " set.");
        this.Colors[index] = c;
    }

    public void setColor(int r, int g, int b, int index) {
        //System.out.println(r + " " + g + " " + b + " set.");
        Color c = new Color(r, g, b);
        this.Colors[index] = c;
    }

    public void setDelay(int delay, int index) {
        //System.out.println(delay + " set.");
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

    public Color getColor(int index) {
        return this.Colors[index];
    }

    public int getDelay(int index) {
        return this.Delays[index];
    }

    /**
     * Returns the static SerialComms object, which is used to send data to the
     * Arduino.
     *
     * @return SerialComms
     */
    public SerialComms getSerialComms() {
        return serialcomms;
    }

    /**
     * Parses all of the colors and delays required for the pattern, and sends
     * it over to the Arduino.
     */
    public void sendPattern() {
        /*                                                           
        System.out.println("Colors:");
        for (Color c : Colors) {
            System.out.println("R" + c.getRed() + " G" + c.getGreen() + " B" + c.getBlue());
        }
        System.out.println("Delays:");          // used for testing
        for (int i : Delays) {
            System.out.println(i);
        }   
         */
        startBytes[0] = (byte) (255 & 0xFF);
        startBytes[1] = (byte) (this.commandNum & 0xFF);
        startBytes[2] = (byte) (this.keyPressColor.getRed() & 0xFF);
        startBytes[3] = (byte) (this.keyPressColor.getGreen() & 0xFF);
        startBytes[4] = (byte) (this.keyPressColor.getBlue() & 0xFF);
        this.startBytes[5] = (byte) ((this.keyPressDelay >> 8) & 0xFF);
        this.startBytes[6] = (byte) (this.keyPressDelay & 0xFF);
        int i = 7;
        for (Color temp : Colors) {
            this.startBytes[i] = (byte) (temp.getRed() & 0xFF);
            this.startBytes[i + 1] = (byte) (temp.getGreen() & 0xFF);
            this.startBytes[i + 2] = (byte) (temp.getBlue() & 0xFF);
            i += 3;
        }
        for (int d : Delays) {
            this.startBytes[i] = (byte) ((d >> 8) & 0xFF);
            this.startBytes[i + 1] = (byte) (d & 0xFF);
            i += 2;
        }
        sendByteArray(startBytes);
        printByteArray(startBytes, "To Start the pattern");
    }

    /**
     * Sends the byte array to the arduino, at 9600 baud.
     *
     * @param b
     */
    public void sendByteArray(byte[] b) {
        getSerialComms().write(b);
    }

    /**
     * Prints the byte array onto console, helpful for debugging.
     *
     * @param b
     */
    public void printByteArray(byte[] b) {
        System.out.println("Byte Array:");
        int i = 0;
        for (byte bt : b) {
            System.out.println(i + "\t" + Byte.toUnsignedInt(bt));
            i++;
        }
    }
    
    public void printByteArray(byte[] b, String whatTheBytesDo) {
        System.out.println("Byte Array " + whatTheBytesDo + ":");
        int i = 0;
        for (byte bt : b) {
            System.out.println(i + "\t" + Byte.toUnsignedInt(bt));
            i++;
        }
    }

    /**
     * Sends a byte to the Arduino.
     *
     * @param b
     */
    public void sendByte(byte b) {
        byte[] arr = new byte[1];
        arr[0] = b;
        sendByteArray(arr);
        //printByteArray(arr);
    }

    /**
     * Returns the settings given to this pattern.
     *
     * @return settings
     */
    public Settings getSettings() {
        return this.settings;
    }

    /**
     * Gets the settings for a key press color flash on the Arduino/
     *
     * @param kpr
     */
    public void setKeyPressSettings(KeyPressRectangle kpr) {
        this.keyPressColor = new Color(kpr.getRed(), kpr.getGreen(), kpr.getBlue());
        this.keyPressDelay = kpr.getDelay();
    }

    /**
     * Starts the pattern, send the initial command bytes, as well as the colors
     * and delays.
     */
    public void start() {
        sendPattern();
        this.hasStarted = true;
    }

    /**
     * Called whenever a key event happens. Only does something if key events
     * are enabled.
     */
    public void keyEvent() {
        if (this.keyReact && this.hasStarted) {
            sendByte((byte) (127 & 0xFF));
        }
    }

    /**
     * Enables or Disables if a key press is registered and sent to the Arudino.
     *
     * @param b
     */
    public void setEnableKeyEvent(boolean b) {
        this.keyReact = b;
    }

    /**
     * Does nothing by default, is overridden to stop patterns like the
     * date/time pattern from updating every second.
     */
    public void stop() {
    }

    /**
     * Meant to be called whenever a pattern is in need of an update, for
     * example changing color every second in accordance to time.
     */
    public void updatePattern() {
        //System.out.println("Update Pattern called.");
        int colors = 0, delays = 0;
        if(this.updateColors != null){
            colors = this.updateColors.length;
        }
        if(this.updateDelays != null){
            delays = this.updateDelays.length;
        }
        int updateByteLength = (colors * 3) + (delays * 2) + 1;
        //System.out.println(updateByteLength);
        this.updateBytes = new byte[updateByteLength];
        //System.out.println("Byte Array length for updating: " + this.updateBytes.length);
        this.updateBytes[0] = (byte) (100 & 0xFF);
        int currentByteIndex = 1;
        if (this.updateColors != null) {
            for (Color temp : this.updateColors) {
                this.updateBytes[currentByteIndex] = (byte) (temp.getRed() & 0xFF);
                this.updateBytes[currentByteIndex + 1] = (byte) (temp.getGreen() & 0xFF);
                this.updateBytes[currentByteIndex + 2] = (byte) (temp.getBlue() & 0xFF);
                currentByteIndex += 3;
            }
        }
        //System.out.println("Done colors.");
        if (this.updateDelays != null) {
            //System.out.println("updateDelays is not null");
            for (int i : this.updateDelays) {
                //System.out.println("Delay setting begin");
                this.updateBytes[currentByteIndex] = (byte) ((i >> 8) & 0xFF);
                this.updateBytes[currentByteIndex+ 1] = (byte) (i & 0xFF);
               // System.out.println("Single Delay Done.");
                currentByteIndex += 2;
            }
        }
        //System.out.println("Done delays and colors......");
        this.sendByteArray(this.updateBytes);
        this.printByteArray(this.updateBytes, "To update the display");
    }

    //public void start(DemoDisplay d);           // will implement a demoDisplay for the GUI later hopefully.
}
