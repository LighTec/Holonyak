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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
         this.MVControl = new ModelsAndViewsControllerFX();

        System.out.println("Initializing components....");

        primaryStage.setTitle("JavaFX Testground, Capstone V0.03");

        BorderPane root = new BorderPane();

        HBox hbox = addHBox();
        root.setTop(hbox);

      //  root.setCenter(addGridPane());

        root.setRight(addFlowPane());

        root.setCenter(addAnchorPane(addGridPane()));

        Scene scene = new Scene(root);
        
       
        
        primaryStage.setScene(scene);
        System.out.println("Showing the primary stage.");
        
        primaryStage.show();
    }

    private HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #FF4500;");

        Button buttonCurrent = new Button("Set LED");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Set Strip");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

        return hbox;
    }
    
    protected TextField FibText;

    private GridPane addGridPane() {
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.TOP_RIGHT);
        //grid.setHgap(25);
        //grid.setVgap(25);
       grid.setPadding(new Insets(0, 15, 50, 15));
       
       grid.setStyle("-fx-background-color:#0000FF;");
       
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setFill(Color.SALMON);

        Text LedConfirm = new Text("[pixel index]![red colour]![green colour]![blue colour]");
        LedConfirm.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));

        TextField LedSetText = new TextField();
        this.FibText = new TextField();
        this.FibText.setText("Fib Number here");

        grid.add(scenetitle, 0, 0, 2, 1);

        grid.add(LedSetText, 2, 0, 1, 1);

        grid.add(LedConfirm, 2, 1, 1, 1);
        grid.add(FibText, 0, 2, 1, 1);

        

        //grid.setGridLinesVisible(true);

        return grid;
    }

    private FlowPane addFlowPane() {

        FlowPane flow = new FlowPane();
        flow.setPadding(new Insets(5, 0, 5, 0));
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(170); // preferred width allows for two columns
        flow.setStyle("-fx-background-color:#00FF00;");
        Button HelloBtn = new Button();
        Button CalcFibBtn = new Button();
        Button SetLedBtn = new Button();

        HelloBtn.setText("Say 'Hello World'");
        
        HelloBtn.setOnAction(this::HelloWorld);

        SetLedBtn.setText("Set the led a colour");

        CalcFibBtn.setText("Print out fib of this index:");
        
        CalcFibBtn.setOnAction(this::CalcFib);

        flow.getChildren().addAll(HelloBtn, CalcFibBtn, SetLedBtn);

        return flow;
    }

    private AnchorPane addAnchorPane(GridPane grid) {

        AnchorPane anchorpane = new AnchorPane();
        
        anchorpane.setStyle("-fx-background-color: #FFD700");

        Button buttonSave = new Button("Save");
        Button buttonCancel = new Button("Cancel");

        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(buttonSave, buttonCancel);

        anchorpane.getChildren().addAll(grid, hb);
        // Anchor buttons to bottom right, anchor grid to top
        AnchorPane.setBottomAnchor(hb, 8.0);
        AnchorPane.setRightAnchor(hb, 5.0);
        AnchorPane.setTopAnchor(grid, 10.0);

        return anchorpane;
    }

    private void HelloWorld(ActionEvent event) {
        this.MVControl.HelloWorld();
    }
    
    private void CalcFib(ActionEvent event){
        String s = this.FibText.getText();
        this.MVControl.CalcFib(s);
    }
}
