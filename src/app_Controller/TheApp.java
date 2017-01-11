package app_Controller;

/**
 * Sets up the model-view-controller, and the multithreading classes of TheApp.
 * 
 * Overview: Sets up 4 seperate threads to be used within the program, one for the serial USB communication
 * class, one for the GUI, one for the pattern processing, and finally another as the plugin manager. This class
 * creates each thread, and within each, creates an object that is used for each section of MVC. A view obj to
 * deal with the GUI creation and usage, a models obj  which will spawn a pixelControl obj out of it, and a 
 * serial communications class, in order to "talk" to the arduino.
 *
 * @author Kell
 */
public class TheApp implements Runnable {

    @Override
    public void run() {
        /*
        BackendModelSetup theBackendModel = new BackendModelSetup();
        MainViewDisplay theMainViewDisplay = new MainViewDisplay(theBackendModel);
        ModelsAndViewsController theMainViewsController = new ModelsAndViewsController(theBackendModel, theMainViewDisplay);

        theMainViewDisplay.setVisible(true);
         */
        
        
    }
}
