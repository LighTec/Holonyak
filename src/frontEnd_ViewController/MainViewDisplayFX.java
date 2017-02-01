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

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;




/**
 *
 * @author kell-gigabyte
 */
public class MainViewDisplayFX extends Application{
    
    public static void init(String[] args){
        System.out.println("GUI Initializing");
        launch(args);
        
    }

    ModelsAndViewsControllerFX MVControl;
    @Override
    public void start(Stage primaryStage) {
        System.out.println("Initializing components....");
        this.MVControl = new ModelsAndViewsControllerFX();
                Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Handling Hello World....");
                MVControl.HelloWorld();
            }
        });
                StackPane root = new StackPane();
        root.getChildren().add(btn);

 Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
   
}
