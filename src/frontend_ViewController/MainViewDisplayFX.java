/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package frontend_ViewController;

import app_Controller.Kaizen_85;
import backend_Models.GeneralSettingsException;
import java.awt.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
        //System.out.println("GUI Initializing");
        launch(args);

    }

    private ViewController MVControl;

    @Override
    public void start(Stage primaryStage) throws GeneralSettingsException {
        primaryStage.setTitle("JavaFX Testground, Capstone V0.11 ColorExt");

        BorderPane root = new BorderPane();

        //HBox hbox = addHBox();
        //root.setTop(hbox);

        //  root.setCenter(addGridPane());
        root.setRight(addFlowPane());

        root.setCenter(addAnchorPane(addGridPane()));

        Scene scene = new Scene(root);

        InitPopup InfoGetter = new InitPopup();
        InfoGetter.run();                                                             // UNREM FOR TESTING SERIALCOMMS

        Settings settings = InfoGetter.SaveSettings();

        this.MVControl = new ViewController(settings);

        primaryStage.setScene(scene);
        //  System.out.println("Showing the primary stage.");
        Kaizen_85.newEvent("Init complete, showing main stage.");
        primaryStage.show();
    }

    private HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #FF4500;");
        /*
        Button buttonCurrent = new Button("Set LED");
        buttonCurrent.setPrefSize(100, 20);

        Button buttonProjected = new Button("Set Strip");
        buttonProjected.setPrefSize(100, 20);

      hbox.getChildren().addAll(buttonCurrent, buttonProjected);
         */
        return hbox;
    }

    private GridPane addGridPane() {
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.TOP_RIGHT);
        //grid.setHgap(25);
        //grid.setVgap(25);
        grid.setPadding(new Insets(0, 15, 50, 15));

        //grid.setStyle("-fx-background-color:#0000FF;");
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        //scenetitle.setFill(Color.SALMON);

        //Text LedConfirm = new Text("[pixel index]![red colour]![green colour]![blue colour]");
        //LedConfirm.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        /*
        Slider redSlider = new Slider();
        Slider greenSlider = new Slider();
        Slider blueSlider = new Slider();

        redSlider.setMin(0);
        greenSlider.setMin(0);
        blueSlider.setMin(0);

        redSlider.setMax(255);
        greenSlider.setMax(255);
        blueSlider.setMax(255);

        
        TextField redField = new TextField();
        TextField greenField = new TextField();
        TextField blueField = new TextField();

         */
        //TextField LedSetText = new TextField();
        grid.add(scenetitle, 0, 0, 2, 1);

        //grid.add(LedSetText, 2, 0, 1, 1);
        //grid.add(LedConfirm, 2, 1, 1, 1);
        //grid.setGridLinesVisible(true);
        return grid;
    }

    private FlowPane addFlowPane() {

        FlowPane flow = new FlowPane();
        flow.setStyle("-fx-background-color: #B0C4DE;");
        flow.setPadding(new Insets(5, 10, 5, 10));
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(170); // preferred width allows for two columns
        //Button HelloBtn = new Button();
        //Button SetLedBtn = new Button();

        //Button RainbowBtn = new Button();

        Button RedBtn = new Button();
        Button GreenBtn = new Button();
        Button BlueBtn = new Button();
     //   Button WhiteBtn = new Button();
      //  Button ClearBtn = new Button();

        //RainbowBtn.setText("Rainbow");

       // HelloBtn.setText("Say 'Hello World'");

      //  HelloBtn.setOnAction(this::HelloWorld);

        RedBtn.setOnAction(this::RedPattern);
        GreenBtn.setOnAction(this::GreenPattern);
        BlueBtn.setOnAction(this::BluePattern);

       // SetLedBtn.setText("Set the led a colour");

        RedBtn.setText("Make LED Red");
        GreenBtn.setText("Make LED Green");
        BlueBtn.setText("Make LED Blue");

        flow.getChildren().addAll(/*HelloBtn, SetLedBtn,*/ RedBtn, GreenBtn, BlueBtn);
        

        return flow;
    }

    private AnchorPane addAnchorPane(GridPane grid) {

        AnchorPane anchorpane = new AnchorPane();

        //anchorpane.setStyle("-fx-background-color: #FFD700");
        Button buttonStart = new Button("Start");
        buttonStart.setOnAction(this::startPattern);

        //Button buttonCancel = new Button("Show Example");

        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(buttonStart/*, buttonCancel*/);

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

    private void RedPattern(ActionEvent event) {
        Kaizen_85.newEvent("Default pattern start.");
        this.MVControl.setFillPattern();
        this.MVControl.setPatternColor(Color.red);
        this.MVControl.setPatternDelay(20);
    }

    private void BluePattern(ActionEvent event) {
        Kaizen_85.newEvent("Default pattern start.");
        this.MVControl.setFillPattern();
        this.MVControl.setPatternColor(Color.blue);
        this.MVControl.setPatternDelay(20);
    }

    private void GreenPattern(ActionEvent event) {
        Kaizen_85.newEvent("Default pattern start.");
        this.MVControl.setFillPattern();
        this.MVControl.setPatternColor(Color.green);
        this.MVControl.setPatternDelay(20);
    }

    private void startPattern(ActionEvent event) {
        this.MVControl.startPattern();
    }
}
