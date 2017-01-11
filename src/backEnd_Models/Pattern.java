/*
 * Created By Kell Larson for the Computer Science AP program during school year 2016/2017. Please ask before copying code.
 */
package backEnd_Models;

/**
 *
 * @author Kell
 */
public class Pattern {
    
    private boolean usesY; // declares if the pattern has a Y axis > 1
    
    private int lengthY; // X and Y lengths of LED strips/matrixes
    private int lengthX;
    
    private String patternName; // the name of the pattern
    
    private String pattern; // the arguments of the pattern
    private String setPattern; // a filled out pattern, with RGB values and time
    
    
    public static void testPattern(int R, int G, int B, int T){
        //Todo code a pattern to test comms here
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 
     * @constructs for a strip of LED's, 1 * x pixels.
     *
     * @param pattern : uses char patterns. See pattern documentation for more
     * information on how to properly use this parameter.
     * 
     * @deprecated until/if we create dynamic patterns
     */
    
    public Pattern(int length, String patternName, String pattern){
        this.usesY = false;
        this.lengthX = length;
        this.patternName = patternName;
        this.pattern = pattern;
    }
    
    /**
     * @constructs for a 2D array, aka a matrix of LED's. y * x.
     * 
     * @param pattern : uses char patterns. See pattern documentation for more
     * information on how to properly use this parameter.
     * 
     * @deprecated until/if we create dynamic patterns
     */
    
    public Pattern(int lengthX, int lengthY, String patternName, String pattern){
        this.usesY = true;
        this.lengthX = lengthX;
        this.lengthY = lengthY;
        this.patternName = patternName;
        this.pattern = pattern;
    }
    
    public void setPattern(){
        /**
         * TODO code logic to take the original pattern String, then add RBG
         * and time interval values, then store it in the setPattern string.
         * @deprecated until/if we create dynamic patterns
         */
    }
    
    public void runPattern(){
        /**
         * runs the setPattern string. Talks to ArduinoComms class.
         * @deprecated until/if we create dynamic patterns
         */
    }
}
