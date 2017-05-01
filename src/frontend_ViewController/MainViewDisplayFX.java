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
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
        primaryStage.setTitle("Capstone V0.21 NeoPixPat");
        primaryStage.getIcons().add(new Image("/JavaFX/icon256.png"));
        primaryStage.getIcons().add(new Image("/JavaFX/icon128.png"));
        primaryStage.getIcons().add(new Image("/JavaFX/icon64.png"));
        primaryStage.getIcons().add(new Image("/JavaFX/icon32.png"));
        primaryStage.getIcons().add(new Image("/JavaFX/icon16.png"));
        
        BorderPane root = new BorderPane();

        HBox hbox = addHBox();
        root.setTop(hbox);
        root.setRight(addFlowPane());

        root.setCenter(addAnchorPane(addGridPane()));

        Scene scene = new Scene(root);

        InitPopup InfoGetter = new InitPopup();
        InfoGetter.run();

        Settings settings = InfoGetter.SaveSettings();

        this.MVControl = new ViewController(settings);

        primaryStage.setScene(scene);
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

    private TextField redField = new TextField("0");
    private TextField greenField = new TextField("0");
    private TextField blueField = new TextField("0");
    private TextField delayField = new TextField("0");

    private Slider redSlider = new Slider();
    private Slider greenSlider = new Slider();
    private Slider blueSlider = new Slider();
    private Slider delaySlider = new Slider();

    private GridPane addGridPane() {
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.TOP_RIGHT);
        //grid.setHgap(25);
        //grid.setVgap(25);
        grid.setPadding(new Insets(0, 15, 50, 15));

        //grid.setStyle("-fx-background-color:#0000FF;");
        //scenetitle.setFill(Color.SALMON);
        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Text redTitle = new Text("Red: ");
        Text greenTitle = new Text("Green: ");
        Text blueTitle = new Text("Blue: ");
        Text delayTitle = new Text("Delay: ");

        this.redSlider.setMin(0);
        this.greenSlider.setMin(0);
        this.blueSlider.setMin(0);
        this.delaySlider.setMin(0);

        this.redSlider.setMax(255);
        this.greenSlider.setMax(255);
        this.blueSlider.setMax(255);
        this.delaySlider.setMax(1000);

        this.redField.setMaxWidth(45);
        this.greenField.setMaxWidth(45);
        this.blueField.setMaxWidth(45);
        this.delayField.setMaxWidth(60);

        this.redSlider.valueProperty().addListener((ov, t, t1) -> {
            updateValuesText();
        });
        this.blueSlider.valueProperty().addListener((ov, t, t1) -> {
            updateValuesText();
        });
        this.greenSlider.valueProperty().addListener((ov, t, t1) -> {
            updateValuesText();
        });
        this.delaySlider.valueProperty().addListener((ov, t, t1) -> {
            updateValuesText();
        });

        this.redField.textProperty().addListener((ov, t, t1) -> {
            updateValuesSlider();
        });
        this.greenField.textProperty().addListener((ov, t, t1) -> {
            updateValuesSlider();
        });
        this.blueField.textProperty().addListener((ov, t, t1) -> {
            updateValuesSlider();
        });
        this.delayField.textProperty().addListener((ov, t, t1) -> {
            updateValuesSlider();
        });

        grid.add(sceneTitle, 0, 0, 2, 1);

        grid.add(redTitle, 0, 1);
        grid.add(greenTitle, 0, 2);
        grid.add(blueTitle, 0, 3);
        grid.add(delayTitle, 0, 4);

        grid.add(this.redSlider, 1, 1);
        grid.add(this.greenSlider, 1, 2);
        grid.add(this.blueSlider, 1, 3);
        grid.add(this.delaySlider, 1, 4);

        grid.add(this.redField, 2, 1);
        grid.add(this.greenField, 2, 2);
        grid.add(this.blueField, 2, 3);
        grid.add(this.delayField, 2, 4);

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

        Button ColorFillBtn = new Button("Color Fill");
        ColorFillBtn.setOnAction(this::ColorFillPattern);

        Button RainbowBtn = new Button("Rainbow");
        RainbowBtn.setOnAction(this::RainbowPattern);

        Button RainbowCycleBtn = new Button("Rainbow Cycle");
        RainbowCycleBtn.setOnAction(this::RainbowCyclePattern);

        Button TheaterChaseBtn = new Button("TheaterChase");
        TheaterChaseBtn.setOnAction(this::TheaterChasePattern);

        Button TheaterChaseRainbowBtn = new Button("TheaterChaseRainbow");
        TheaterChaseRainbowBtn.setOnAction(this::TheaterChaseRainbowPattern);

        // HelloBtn.setText("Say 'Hello World'");
        //  HelloBtn.setOnAction(this::HelloWorld);
        flow.getChildren().addAll(ColorFillBtn, RainbowBtn, RainbowCycleBtn, TheaterChaseBtn, TheaterChaseRainbowBtn);

        return flow;
    }

    private AnchorPane addAnchorPane(GridPane grid) {

        AnchorPane anchorpane = new AnchorPane();

        //anchorpane.setStyle("-fx-background-color: #FFD700");
        Button StartBtn = new Button("Start");
        StartBtn.setOnAction(this::startPattern);

        Button SetColorBtn = new Button("Set Color");
        SetColorBtn.setOnAction(this::ColorSet);

        Button setDelayBtn = new Button("Set Delay");
        setDelayBtn.setOnAction(this::DelaySet);

        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(StartBtn, SetColorBtn, setDelayBtn);

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

    private void ColorFillPattern(ActionEvent event) {
        Kaizen_85.newEvent("Color fill pattern set.");
        this.MVControl.setFillPattern();
    }

    private void RainbowPattern(ActionEvent event) {
        Kaizen_85.newEvent("Rainbow pattern set.");
        this.MVControl.setRainbowPattern();
    }

    private void RainbowCyclePattern(ActionEvent event) {
        Kaizen_85.newEvent(" pattern set.");
        this.MVControl.setRainbowCyclePattern();
    }

    private void TheaterChasePattern(ActionEvent event) {
        Kaizen_85.newEvent(" pattern set.");
        this.MVControl.setTheaterChasePattern();
    }

    private void TheaterChaseRainbowPattern(ActionEvent event) {
        Kaizen_85.newEvent(" pattern set.");
        this.MVControl.setTheaterChaseRainbowPattern();
    }

    /*
    private void Pattern(ActionEvent event){
        Kaizen_85.newEvent(" pattern set.");
        this.MVControl.setPattern();
    }
     */
    private void startPattern(ActionEvent event) {
        Kaizen_85.newEvent("Pattern button pressed, pattern starting.");
        this.MVControl.startPattern();
    }

    private void ColorSet(ActionEvent event) {
        int red = Integer.parseInt(this.redField.getText());
        int green = Integer.parseInt(this.greenField.getText());
        int blue = Integer.parseInt(this.blueField.getText());
        Kaizen_85.newEvent("Setting color, " + red + "  " + green + "  " + blue);
        this.MVControl.setPatternColor(red, green, blue);
    }

    private void DelaySet(ActionEvent event) {
        int delay = Integer.parseInt(this.delayField.getText());
        this.MVControl.setPatternDelay(delay);
    }

    private void updateValuesSlider() { // updates sliders from text fields
        //Kaizen_85.newEvent("Values dupated from text flields to sliders.");
        int red, green, blue, delay;
        if(this.redField.getText().equals("")){
            red = 0;
        }else{
             red = Integer.parseInt(this.redField.getText());
        }
        if(this.greenField.getText().equals("")){
            green = 0;
        }else{
             green = Integer.parseInt(this.greenField.getText());
        }
        if(this.blueField.getText().equals("")){
            blue = 0;
        }else{
             blue = Integer.parseInt(this.blueField.getText());
        }
        if(this.delayField.getText().equals("")){
            delay = 0;
        }else{
            delay = Integer.parseInt(this.delayField.getText());
        }
       
        
       
      
        if (red > 255) {
            red = 255;
        } else if (red < 0) {
            red = 0;
        }
        if (green > 255) {
            green = 255;
        } else if (green < 0) {
            green = 0;
        }
        if (blue > 255) {
            blue = 255;
        } else if (blue < 0) {
            blue = 0;
        }
        if (delay > 2000) {
            delay = 2000;
        } else if (delay < 0) {
            delay = 0;
        }
        this.redSlider.setValue(red);
        this.greenSlider.setValue(green);
        this.blueSlider.setValue(blue);
        this.delaySlider.setValue(delay);
    }

    private void updateValuesText() { // updates text fields from sliders
        //Kaizen_85.newEvent("Values dupated from sliders to text fields.");
        int red = (int) Math.round(this.redSlider.getValue());
        int green = (int) Math.round(this.greenSlider.getValue());
        int blue = (int) Math.round(this.blueSlider.getValue());
        int delay = (int) Math.round(this.delaySlider.getValue());
        if (red > 255) {
            red = 255;
        } else if (red < 0) {
            red = 0;
        }
        if (green > 255) {
            green = 255;
        } else if (green < 0) {
            green = 0;
        }
        if (blue > 255) {
            blue = 255;
        } else if (blue < 0) {
            blue = 0;
        }
        if (delay > 2000) {
            delay = 2000;
        } else if (delay < 0) {
            delay = 0;
        }

        String r = "" + red;
        String g = "" + green;
        String b = "" + blue;
        String d = "" + delay;

        this.redField.setText(r);
        this.greenField.setText(g);
        this.blueField.setText(b);
        this.delayField.setText(d);
    }

}
