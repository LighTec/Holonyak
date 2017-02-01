/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package frontEnd_ViewController;
/**
 *
 * @author kell-gigabyte
 */
public class ModelsAndViewsControllerFX {
    public ModelsAndViewsControllerFX(){
        System.out.println("View controller object created");
    }

    /*
     *
     * ModelsAndViewsController needs to have an instance variable to reference
     * the backend's models because the frontend's ModelsAndViewsController is
     * responsible for asking the backend to modify its data.
     *
     * Since the backend models is initially set up by an instance of the
     * BackendModelSetup class, we just need this one instance variable here:
     */
    //public BackendModelSetup theBackendModel;
    /*
    public void ModelsAndViewsController(BackendModelSetup aBackend) {
        this.theBackendModel = aBackend;
    }
     */
    public void HelloWorld() {
        System.out.println("Hello world!");
    }
    
    public long equateFibonacci(int index){
                switch (index) {
            case 1:
                return 5;
            case 0:
                return 3;
            case 2:
                return 8;
            default:
                return equateFibonacci(index - 1) + equateFibonacci(index - 2);
        }
    }
}
