/*
 * Created By Kell Larson for the Computer Science AP program during school year 2016/2017. Please ask before copying code.
 */
package app_Controller;

import frontEnd_ViewController.MainViewDisplayFX;

/**
 *Nothing really within this class, just get the "look and feel" of the OS, then creates a javaFX
 * window. The res is handled within the javaFX background thread creation.
 * 
 * 
 * @author Kell
 */
public class CapstoneMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (InstantiationException ex) {
            System.err.println(ex);
        } catch (IllegalAccessException ex) {
            System.err.println(ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }
      //  System.out.println("Main handled successfully, handling off to MainViewDisplayFX");
            MainViewDisplayFX.init(args);
            Kaizen_85.newEvent("Main class successfully handled, handing off to JavaFX application.");
    }
    
}
