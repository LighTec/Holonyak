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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author kell-gigabyte
 */
public class MainViewDisplayFX extends Application {

    public static void init(String[] args) {
        System.out.println("GUI Initializing");
        launch(args);

    }

    ModelsAndViewsControllerFX MVControl;

    @Override
    public void start(Stage primaryStage) {

        System.out.println("Initializing components....");

        primaryStage.setTitle("JavaFX Testground, Capstone V0.03");
        
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        this.MVControl = new ModelsAndViewsControllerFX();

        Button HelloBtn = new Button();
        Button CalcFibBtn = new Button();
        TextField TextField = new TextField();

        grid.add(HelloBtn, 0, 0, 1, 1);
        grid.add(CalcFibBtn, 0, 1, 1, 1);
        grid.add(TextField, 0, 2, 1, 2);

        HelloBtn.setText("Say 'Hello World'");
        
        CalcFibBtn.setText("Print out fib of this index:");

        CalcFibBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Handling Fib calc....");
                try{
                String s = TextField.getText();
                //System.out.println("String of fib num is !" + s + "! (shows exact string, seperated by exclamations.)");
                int i = Integer.parseInt(s);
                //System.out.println("Int version is " + i);
                long returnFib = MVControl.equateFibonacci(i);
                System.out.println("Fib of index " + i + " is " + returnFib);
                }catch(NullPointerException e){
                    System.err.println("Null pointer thrown, fib not calculated.");
                }
            }
        });

        HelloBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Handling Hello World....");
                MVControl.HelloWorld();
            }
        });


        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        System.out.println("Showing the primary stage.");
        primaryStage.show();
    }

}
