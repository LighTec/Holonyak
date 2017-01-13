/*
 * Created By Kell Larson for the Computer Science AP program during school year 2016/2017. Please ask before copying code.
 */
package app_Controller;

/**
 *Nothing really within this class, just get the "look and feel" of the OS, then creates a runnable
 * of The_____ classes in order to initialize the program.
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

        java.awt.EventQueue.invokeLater(new TheGUI());
        java.awt.EventQueue.invokeLater(new TheBackend());
        java.awt.EventQueue.invokeLater(new TheSerialComms());
    }
    
}
