/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package frontend_View;

import app_Controller.Kaizen_85;
import arduinocomms2.AlertBox;
import backend_Models.GeneralSettingsException;
import java.awt.Dimension;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

    private FlowPane delayPane;
    private FlowPane colorPane;

    private ArrayList<Rectangle> colorRectArr = new ArrayList<>();
    private ArrayList<Integer> redArr = new ArrayList<>();
    private ArrayList<Integer> greenArr = new ArrayList<>();
    private ArrayList<Integer> blueArr = new ArrayList<>();

    private ArrayList<Rectangle> delayRectArr = new ArrayList<>();
    private ArrayList<Integer> delayArr = new ArrayList<>();

    private TextField redField = new TextField("0");
    private TextField greenField = new TextField("0");
    private TextField blueField = new TextField("0");
    private TextField delayField = new TextField("0");

    private Slider redSlider = new Slider();
    private Slider greenSlider = new Slider();
    private Slider blueSlider = new Slider();
    private Slider delaySlider = new Slider();

    private Rectangle lastRect;

    private final String hexHbox = "4A444B";
    private final String hexButtonBox = "BA0101";
    private final String hexColorBox = "FFFFF0";
    private final String hexDelayBox = "78866B";
    private final String hexSliderBox = "007FFF";

    @Override
    public void start(Stage primaryStage) throws GeneralSettingsException {
        primaryStage.setTitle("Capstone V0.21 NeoPixPat");
        primaryStage.getIcons().add(new Image("/JavaFX_Resources/icon256.png"));
        primaryStage.getIcons().add(new Image("/JavaFX_Resources/icon128.png"));
        primaryStage.getIcons().add(new Image("/JavaFX_Resources/icon64.png"));
        primaryStage.getIcons().add(new Image("/JavaFX_Resources/icon32.png"));
        primaryStage.getIcons().add(new Image("/JavaFX_Resources/icon16.png"));

        BorderPane root = new BorderPane();

        HBox hbox = addHBox();
        root.setTop(hbox);
        root.setRight(addFlowPane());
        root.setLeft(addColorPane());
        root.setBottom(addDelayPane());

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

    private FlowPane addColorPane() {
        this.delayPane = new FlowPane();
        this.delayPane.setStyle("-fx-background-color: #" + this.hexColorBox + ";");
        this.delayPane.setPadding(new Insets(5, 10, 5, 10));
        this.delayPane.setVgap(4);
        this.delayPane.setHgap(4);
        this.delayPane.setPrefWrapLength(180); // preferred width allows for two columns

        boolean debugThisPane = false;
        if (debugThisPane) {
            Rectangle rect1 = new Rectangle(75, 75);
            rect1.setFill(Color.CORNFLOWERBLUE);
            Rectangle rect2 = new Rectangle(75, 75);
            rect2.setFill(Color.GREENYELLOW);
            Rectangle rect3 = new Rectangle(75, 75);
            rect3.setFill(Color.DARKVIOLET);
            Rectangle rect4 = new Rectangle(75, 75);
            rect4.setFill(Color.BURLYWOOD);
            Rectangle rect5 = new Rectangle(75, 75);
            rect5.setFill(Color.WHITE);
            Rectangle rect6 = new Rectangle(75, 75);
            rect6.setFill(Color.PEACHPUFF);
            Rectangle rect7 = new Rectangle(75, 75);
            rect7.setFill(Color.BLUE);
            this.delayPane.getChildren().addAll(rect1, rect2, rect3, rect4, rect5, rect6, rect7);
        }

        return this.delayPane;
    }

    private FlowPane addDelayPane() {
        this.colorPane = new FlowPane();
        this.colorPane.setStyle("-fx-background-color: #" + this.hexDelayBox + ";");
        this.colorPane.setPadding(new Insets(5, 10, 5, 10));
        this.colorPane.setVgap(4);
        this.colorPane.setHgap(4);
        this.colorPane.setPrefWrapLength(720); // preferred width allows for eight rows (?)
        this.colorPane.setMinHeight(85);

        boolean debugThisPane = false;
        if (debugThisPane) {
            Rectangle rect1 = new Rectangle(75, 75);
            rect1.setFill(Color.CORNFLOWERBLUE);
            Rectangle rect2 = new Rectangle(75, 75);
            rect2.setFill(Color.GREENYELLOW);
            Rectangle rect3 = new Rectangle(75, 75);
            rect3.setFill(Color.DARKVIOLET);
            Rectangle rect4 = new Rectangle(75, 75);
            rect4.setFill(Color.BURLYWOOD);
            Rectangle rect5 = new Rectangle(75, 75);
            rect5.setFill(Color.WHITE);
            Rectangle rect6 = new Rectangle(75, 75);
            rect6.setFill(Color.PEACHPUFF);
            Rectangle rect7 = new Rectangle(75, 75);
            rect7.setFill(Color.BLUE);
            this.colorPane.getChildren().addAll(rect1, rect2, rect3, rect4, rect5, rect6, rect7);
        }

        return this.colorPane;
    }

    private HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #" + this.hexHbox + ";");

        Button ClearBtn = new Button("Clear Display, and reset the pattern");
        ClearBtn.setOnAction(this::ClearColors);

        Button DebugBtn = new Button("Fix Arduino Buffer");
        DebugBtn.setOnAction(this::debugDisplay);

        hbox.getChildren().addAll(ClearBtn, DebugBtn);
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

        this.delaySlider.setDisable(true);
        this.delayField.setDisable(true);
        this.redSlider.setDisable(true);
        this.redField.setDisable(true);
        this.greenSlider.setDisable(true);
        this.greenField.setDisable(true);
        this.blueSlider.setDisable(true);
        this.blueField.setDisable(true);

        this.redSlider.valueProperty().addListener((ov, t, t1) -> {
            updateColorText();
        });
        this.blueSlider.valueProperty().addListener((ov, t, t1) -> {
            updateColorText();
        });
        this.greenSlider.valueProperty().addListener((ov, t, t1) -> {
            updateColorText();
        });
        this.delaySlider.valueProperty().addListener((ov, t, t1) -> {
            updateDelayText();
        });

        this.redField.textProperty().addListener((ov, t, t1) -> {
            updateColorSliders();
        });
        this.greenField.textProperty().addListener((ov, t, t1) -> {
            updateColorSliders();
        });
        this.blueField.textProperty().addListener((ov, t, t1) -> {
            updateColorSliders();
        });
        this.delayField.textProperty().addListener((ov, t, t1) -> {
            updateDelaySliders();
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
        flow.setStyle("-fx-background-color: #" + this.hexButtonBox + ";");
        flow.setPadding(new Insets(5, 10, 5, 10));
        flow.setVgap(4);
        flow.setHgap(4);
        flow.setPrefWrapLength(120); // preferred width allows for two columns

        Button ColorFillBtn = new Button("Color Fill");
        ColorFillBtn.setOnAction(this::ColorFillPattern);

        Button RainbowBtn = new Button("Rainbow");
        RainbowBtn.setOnAction(this::RainbowPattern);

        Button RainbowCycleBtn = new Button("Rainbow Cycle");
        RainbowCycleBtn.setOnAction(this::RainbowCyclePattern);

        Button TheaterChaseBtn = new Button("Theater Chase");
        TheaterChaseBtn.setOnAction(this::TheaterChasePattern);

        Button TheaterChaseRainbowBtn = new Button("Rainbow Chase");
        TheaterChaseRainbowBtn.setOnAction(this::TheaterChaseRainbowPattern);

        // HelloBtn.setText("Say 'Hello World'");
        //  HelloBtn.setOnAction(this::HelloWorld);
        flow.getChildren().addAll(ColorFillBtn, RainbowBtn, RainbowCycleBtn, TheaterChaseBtn, TheaterChaseRainbowBtn);

        return flow;
    }

    private AnchorPane addAnchorPane(GridPane grid) {

        AnchorPane anchorpane = new AnchorPane();
        anchorpane.setStyle("-fx-background-color: #" + this.hexSliderBox + ";");

        //anchorpane.setStyle("-fx-background-color: #FFD700");
        Button StartBtn = new Button("Start");
        StartBtn.setOnAction(this::startPattern);

        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(StartBtn);

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
        updateRectangles(this.MVControl.getPattern().getAmountOfColors(), this.MVControl.getPattern().getAmountOfDelays());
    }

    private void RainbowPattern(ActionEvent event) {
        Kaizen_85.newEvent("Rainbow pattern set.");
        this.MVControl.setRainbowPattern();
        updateRectangles(this.MVControl.getPattern().getAmountOfColors(), this.MVControl.getPattern().getAmountOfDelays());
    }

    private void RainbowCyclePattern(ActionEvent event) {
        Kaizen_85.newEvent(" pattern set.");
        this.MVControl.setRainbowCyclePattern();
        updateRectangles(this.MVControl.getPattern().getAmountOfColors(), this.MVControl.getPattern().getAmountOfDelays());
    }

    private void TheaterChasePattern(ActionEvent event) {
        Kaizen_85.newEvent(" pattern set.");
        this.MVControl.setTheaterChasePattern();
        updateRectangles(this.MVControl.getPattern().getAmountOfColors(), this.MVControl.getPattern().getAmountOfDelays());
    }

    private void TheaterChaseRainbowPattern(ActionEvent event) {
        Kaizen_85.newEvent(" pattern set.");
        this.MVControl.setTheaterChaseRainbowPattern();
        updateRectangles(this.MVControl.getPattern().getAmountOfColors(), this.MVControl.getPattern().getAmountOfDelays());
    }

    private void ClearColors(ActionEvent event) {
        this.MVControl.ClearPattern();
    }

    private void debugDisplay(ActionEvent event) {
        this.MVControl.debugDisplay();
    }

    private void updateRectangles(int colors, int delays) {
        //System.out.println("Given: " + colors + " Colors, " + delays + " Delays.");
        //System.out.println("Currently have: " + this.colorRectArr.size() + " Colors, " + this.delayRectArr.size() + " Delays.");
        for (Rectangle rect : this.colorRectArr) {
            this.delayPane.getChildren().remove(rect);
        }
        for (Rectangle rect : this.delayRectArr) {
            this.colorPane.getChildren().remove(rect);
        }

        if (this.colorRectArr.size() == colors) {
            //System.out.println("color rect arr equal to colors");
            // do nothing
        } else {
            while (this.colorRectArr.size() < colors) {
                //System.out.println("color rect arr less than colors");
                addRectangle(true);
            }
            while (this.colorRectArr.size() > colors) {
                //System.out.println("color rect arr greater than colors");
                this.colorRectArr.remove(this.colorRectArr.size() - 1);
                this.redArr.remove(this.redArr.size() - 1);
                this.greenArr.remove(this.greenArr.size() - 1);
                this.blueArr.remove(this.blueArr.size() - 1);
            }
        }

        if (delayRectArr.size() == delays) {
            //System.out.println("delay rect arr equal to colors");
            // do nothing
        } else {
            while (this.delayRectArr.size() < delays) {
                //System.out.println("delay rect arr less than delays");
                addRectangle(false);
            }
            while (this.delayRectArr.size() > delays) {
                //System.out.println("delay rect arr greather than delays");
                this.delayRectArr.remove(this.delayRectArr.size() - 1);
            }
        }
        for (Rectangle rect : this.colorRectArr) {
            this.delayPane.getChildren().add(rect);
        }
        for (Rectangle rect : this.delayRectArr) {
            this.colorPane.getChildren().add(rect);
        }
    }

    private void addRectangle(boolean isColor) {
        Rectangle rect = new Rectangle(75, 75);
        int defaultRed = 0;
        int defaultGreen = 0;
        int defaultBlue = 0;
        int defaultDelay = 0;
        Color defaultDelayColor = Color.CADETBLUE;

        if (isColor) {
            rect.setFill(Color.rgb(defaultRed, defaultGreen, defaultBlue));
        } else {
            rect.setFill(defaultDelayColor);
        }
        rect.setStrokeWidth(3);
        rect.setStroke(Color.BLACK);

        rect.setOnMouseClicked((MouseEvent t) -> {
            if (this.lastRect == null) {
                this.lastRect = rect;
            } else {
                if (isColor) {
                    this.setRectangles(rect);
                } else {
                    this.setRectangles(rect);
                }
            }
            disableSliders(isColor);

        });

        if (isColor) {
            colorRectArr.add(rect);
            redArr.add(defaultRed);
            greenArr.add(defaultGreen);
            blueArr.add(defaultBlue);
        } else {
            delayRectArr.add(rect);
            delayArr.add(defaultDelay);
        }
    }

    private void disableSliders(boolean disableDelay) {
        if (disableDelay) {
            this.delaySlider.setDisable(true);
            this.delayField.setDisable(true);
            this.redSlider.setDisable(false);
            this.redField.setDisable(false);
            this.greenSlider.setDisable(false);
            this.greenField.setDisable(false);
            this.blueSlider.setDisable(false);
            this.blueField.setDisable(false);
        } else {
            this.delaySlider.setDisable(false);
            this.delayField.setDisable(false);
            this.redSlider.setDisable(true);
            this.redField.setDisable(true);
            this.greenSlider.setDisable(true);
            this.greenField.setDisable(true);
            this.blueSlider.setDisable(true);
            this.blueField.setDisable(true);
        }
    }


    /*
    private void Pattern(ActionEvent event){
        Kaizen_85.newEvent(" pattern set.");
        this.MVControl.setPattern();
    }
     */
    private void startPattern(ActionEvent event) {
        Kaizen_85.newEvent("Pattern button pressed, pattern starting.");
        this.setRectangles();
        this.sendRectangleValues();
        this.MVControl.startPattern();
    }

    private void setRectangles(Rectangle newRectangle) {
        int colorIndex = this.colorRectArr.indexOf(this.lastRect);
        int delayIndex = this.delayRectArr.indexOf(this.lastRect);
        if (colorIndex < 0 && delayIndex > -1) {
            this.delayArr.set(delayIndex, (int) Math.round(this.delaySlider.getValue()));
        } else if (colorIndex > -1 && delayIndex < 0) {
            this.redArr.set(colorIndex, (int) Math.round(this.redSlider.getValue()));
            this.greenArr.set(colorIndex, (int) Math.round(this.greenSlider.getValue()));
            this.blueArr.set(colorIndex, (int) Math.round(this.blueSlider.getValue()));
        } else {
            AlertBox alert = new AlertBox(new Dimension(400, 200), "Pattern Start Error", "Could not find the last Rectangle in either array... ???");
            alert.display();
        }
        
        int newIndexColor = this.colorRectArr.indexOf(newRectangle);
        int newIndexDelay = this.delayRectArr.indexOf(newRectangle);
        this.lastRect = newRectangle;
        
        if(newIndexColor < 0 && newIndexDelay > -1){
            this.setDelaySlider(this.delayArr.get(newIndexDelay));
        }else if(newIndexColor > -1 && newIndexDelay < 0){
            this.setColorSliders(this.redArr.get(newIndexColor), this.greenArr.get(newIndexColor), this.blueArr.get(newIndexColor));
        }else{
            AlertBox alert = new AlertBox(new Dimension(400, 200), "Pattern Start Error", "Could not find the current Rectangle in either array... ???");
            alert.display();
        }
    }

    private void setRectangles() {
        int colorIndex = this.colorRectArr.indexOf(this.lastRect);
        int delayIndex = this.delayRectArr.indexOf(this.lastRect);
        if (colorIndex < 0 && delayIndex > -1) {
            this.delayArr.set(delayIndex, (int) Math.round(this.delaySlider.getValue()));

        } else if (colorIndex > -1 && delayIndex < 0) {
            this.redArr.set(colorIndex, (int) Math.round(this.redSlider.getValue()));
            this.greenArr.set(colorIndex, (int) Math.round(this.greenSlider.getValue()));
            this.blueArr.set(colorIndex, (int) Math.round(this.blueSlider.getValue()));
        } else {
            AlertBox alert = new AlertBox(new Dimension(400, 200), "Pattern Start Error", "Could not find the last Rectangle in either array... ???");
            alert.display();
        }
    }

    private void updateColorSliders() { // updates sliders from text fields
        //Kaizen_85.newEvent("Values dupated from text flields to sliders.");
        int red, green, blue;
        if (this.redField.getText().equals("")) {
            red = 0;
        } else {
            red = Integer.parseInt(this.redField.getText());
        }
        if (this.greenField.getText().equals("")) {
            green = 0;
        } else {
            green = Integer.parseInt(this.greenField.getText());
        }
        if (this.blueField.getText().equals("")) {
            blue = 0;
        } else {
            blue = Integer.parseInt(this.blueField.getText());
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

        this.redSlider.setValue(red);
        this.greenSlider.setValue(green);
        this.blueSlider.setValue(blue);
        this.lastRect.setFill(Color.rgb(red, green, blue));
    }

    private void updateColorText() { // updates text fields from sliders
        //Kaizen_85.newEvent("Values dupated from sliders to text fields.");
        int red = (int) Math.round(this.redSlider.getValue());
        int green = (int) Math.round(this.greenSlider.getValue());
        int blue = (int) Math.round(this.blueSlider.getValue());
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

        String r = "" + red;
        String g = "" + green;
        String b = "" + blue;

        this.redField.setText(r);
        this.greenField.setText(g);
        this.blueField.setText(b);
        this.lastRect.setFill(Color.rgb(red, green, blue));
    }

    private void updateDelayText() { // updates text fields from sliders
        int delay = (int) Math.round(this.delaySlider.getValue());
        if (delay > 2000) {
            delay = 2000;
        } else if (delay < 0) {
            delay = 0;
        }

        String d = "" + delay;

        this.delayField.setText(d);
        int red = delay % 255;
        int green = (delay + 86) % 255;
        int blue = (delay + 172) % 255;
        this.lastRect.setFill(Color.rgb(red, green, blue));
    }

    private void updateDelaySliders() { // updates sliders from text fields
        //Kaizen_85.newEvent("Values dupated from text flields to sliders.");
        int delay;

        if (this.delayField.getText().equals("")) {
            delay = 0;
        } else {
            delay = Integer.parseInt(this.delayField.getText());
        }
        if (delay > 2000) {
            delay = 2000;
        } else if (delay < 0) {
            delay = 0;
        }
        this.delaySlider.setValue(delay);
        int red = delay % 255;
        int green = (delay + 86) % 255;
        int blue = (delay + 172) % 255;
        this.lastRect.setFill(Color.rgb(red, green, blue));
    }

    private void setColorSliders(int red, int green, int blue) {
        //System.out.println("Red: " + red + " Green " + green + " Blue: " + blue);
        this.redSlider.setValue(red);
        this.greenSlider.setValue(green);
        this.blueSlider.setValue(blue);
        updateColorText();
    }

    private void setDelaySlider(int delay) {
        //System.out.println("Delay: " + delay);
        this.delaySlider.setValue(delay);
        updateDelayText();
    }

    /**
     * Sends the values of colors and delays from the Rectangles on the GUI to
     * the pattern at the backend.
     */
    public void sendRectangleValues() {
        for (int i = 0; i < this.redArr.size(); i++) {
            //System.out.println("Sending color from array index " + i + ", red: " + this.redArr.get(i) + " green: " + this.greenArr.get(i) + " blue: " + this.blueArr.get(i));
            this.MVControl.setPatternColor(this.redArr.get(i), this.greenArr.get(i), this.blueArr.get(i), i);
        }
        int i = 0;
        for (int delay : this.delayArr) {
            //System.out.println("Sending delay from array index " + i + ", delay: " + delay);
            this.MVControl.setPatternDelay(delay, i);
            i++;
        }
    }
}
