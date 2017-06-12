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
import backend_Models.KeyPressRectangle;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
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
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

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
    private final String hexSliderBox = "90AFFF";

    private boolean nativeKeyActive = false;
    private boolean lastRectangleKeyRect = false;
    private KeyPressRectangle keyRect = new KeyPressRectangle(200, 25, Color.BLACK);

    private NativeKeyListener keyListener = new NativeKeyListener() {
        @Override
        public void nativeKeyPressed(NativeKeyEvent e) {
            //System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
            getMVControl().keyReact();
        }

        @Override
        public void nativeKeyReleased(NativeKeyEvent e) {
            //System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        }

        @Override
        public void nativeKeyTyped(NativeKeyEvent e) {
            //System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
        }
    };

    public ViewController getMVControl() {
        return this.MVControl;
    }

    @Override
    public void start(Stage primaryStage) throws GeneralSettingsException {
        primaryStage.setTitle("Holonyak V1.0: PanelClear");
        primaryStage.getIcons().add(new Image("/JavaFX_Resources/icon256.png"));
        primaryStage.getIcons().add(new Image("/JavaFX_Resources/icon128.png"));
        primaryStage.getIcons().add(new Image("/JavaFX_Resources/icon64.png"));
        primaryStage.getIcons().add(new Image("/JavaFX_Resources/icon32.png"));
        primaryStage.getIcons().add(new Image("/JavaFX_Resources/icon16.png"));
        
        primaryStage.getIcons().add(new Image("File:/JavaFX_Resources/icon32.png"));
        
        for(int i = 0; i < 1000; i++){
            double d = Math.hypot(6000.01321321521, 126523.14142131);
        }

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

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            Logger.getLogger(MainViewDisplayFX.class.getName()).log(Level.SEVERE, null, ex);
        }
        primaryStage.setOnCloseRequest(event -> {
            //System.out.println("Stage is closing");
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException ex) {
                Logger.getLogger(MainViewDisplayFX.class.getName()).log(Level.SEVERE, null, ex);

            }
        });
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);

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
        Text keyReactTitle = new Text("Flashes when a key is pressed?");

        ToggleButton keyReact = new ToggleButton("False");
        ToggleGroup aKeyReact = new ToggleGroup();
        keyReact.setToggleGroup(aKeyReact);

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

        aKeyReact.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) -> {
            if (this.nativeKeyActive) {
                GlobalScreen.removeNativeKeyListener(this.keyListener);
                this.nativeKeyActive = false;
                keyReact.setText("False");
            } else {
                GlobalScreen.addNativeKeyListener(this.keyListener);
                this.nativeKeyActive = true;
                keyReact.setText("True");
            }
        });

        grid.add(sceneTitle, 0, 0, 2, 1);

        grid.add(redTitle, 0, 1);
        grid.add(greenTitle, 0, 2);
        grid.add(blueTitle, 0, 3);
        grid.add(delayTitle, 0, 4);
        grid.add(keyReactTitle, 0, 5, 2, 1);

        grid.add(this.redSlider, 1, 1);
        grid.add(this.greenSlider, 1, 2);
        grid.add(this.blueSlider, 1, 3);
        grid.add(this.delaySlider, 1, 4);

        grid.add(keyReact, 2, 5);

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

        Button TimeBtn = new Button("Time Based Colors");
        TimeBtn.setOnAction(this::TimePattern);

        Button CpuMemBtn = new Button("Cpu/Mem Usage");
        CpuMemBtn.setOnAction(this::CpuMemPattern);

        /*
        Button TextBtn = new Button("Scrolling Text");
        TextBtn.setOnAction(this::TextPattern);
         */
        // HelloBtn.setText("Say 'Hello World'");
        //  HelloBtn.setOnAction(this::HelloWorld);
        flow.getChildren().addAll(ColorFillBtn, RainbowBtn, RainbowCycleBtn, TheaterChaseBtn, TheaterChaseRainbowBtn, TimeBtn, CpuMemBtn);

        return flow;
    }

    private AnchorPane addAnchorPane(GridPane grid) {

        AnchorPane anchorpane = new AnchorPane();
        anchorpane.setStyle("-fx-background-color: #" + this.hexSliderBox + ";");

        //anchorpane.setStyle("-fx-background-color: #FFD700");
        Button StartBtn = new Button("Start");
        StartBtn.setOnAction(this::startPattern);

        this.keyRect.setOnMouseClicked((MouseEvent t) -> {
            this.setLastRectangle();
            this.lastRectangleKeyRect = true;
            int red, green, blue, delay;
            red = this.keyRect.getRed();
            green = this.keyRect.getGreen();
            blue = this.keyRect.getBlue();
            delay = this.keyRect.getDelay();
            this.setColorSliders(red, green, blue);
            this.setDelaySlider(delay);
            //System.out.println("Key press rect pressed.");
            this.enableAllSliders();
        });

        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 10, 10, 10));
        hb.setSpacing(10);
        hb.getChildren().addAll(this.keyRect, StartBtn);

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
        Kaizen_85.newEvent("Rainbow cycle pattern set.");
        this.MVControl.setRainbowCyclePattern();
        updateRectangles(this.MVControl.getPattern().getAmountOfColors(), this.MVControl.getPattern().getAmountOfDelays());
    }

    private void TheaterChasePattern(ActionEvent event) {
        Kaizen_85.newEvent("Theater chase pattern set.");
        this.MVControl.setTheaterChasePattern();
        updateRectangles(this.MVControl.getPattern().getAmountOfColors(), this.MVControl.getPattern().getAmountOfDelays());
    }

    private void TheaterChaseRainbowPattern(ActionEvent event) {
        Kaizen_85.newEvent("Theater Chase rainbow pattern set.");
        this.MVControl.setTheaterChaseRainbowPattern();
        updateRectangles(this.MVControl.getPattern().getAmountOfColors(), this.MVControl.getPattern().getAmountOfDelays());
    }

    private void TimePattern(ActionEvent event) {
        Kaizen_85.newEvent(" pattern set");
        this.MVControl.setTimePattern();
        updateRectangles(this.MVControl.getPattern().getAmountOfColors(), this.MVControl.getPattern().getAmountOfDelays());
    }

    private void CpuMemPattern(ActionEvent event) {
        Kaizen_85.newEvent(" pattern set");
        this.MVControl.setCpuMemPattern();
        updateRectangles(this.MVControl.getPattern().getAmountOfColors(), this.MVControl.getPattern().getAmountOfDelays());
    }

    /*
    private void TextPattern(ActionEvent event){
        Kaizen_85.newEvent(" pattern set");
        this.MVControl.setTextPattern();
        updateRectangles(this.MVControl.getPattern().getAmountOfColors(), this.MVControl.getPattern().getAmountOfDelays());
    }
     */
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
        Color defaultDelayColor = Color.CHARTREUSE;

        if (isColor) {
            rect.setFill(Color.rgb(defaultRed, defaultGreen, defaultBlue));
        } else {
            rect.setFill(defaultDelayColor);
        }
        rect.setStrokeWidth(3);
        rect.setStroke(Color.BLACK);

        rect.setOnMouseClicked((MouseEvent t) -> {
            if (this.lastRectangleKeyRect) {
                this.updateKeyPressRect();
                this.lastRectangleKeyRect = false;
            }
            if (this.lastRect == null) {
                this.lastRect = rect;
                this.setColorSlidersToCurrentRectangle(rect);
            } else {
                this.setRectangles(rect);
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

    private void enableAllSliders() {
        this.redSlider.setDisable(false);
        this.redField.setDisable(false);
        this.greenSlider.setDisable(false);
        this.greenField.setDisable(false);
        this.blueSlider.setDisable(false);
        this.blueField.setDisable(false);
        this.delaySlider.setDisable(false);
        this.delayField.setDisable(false);
    }


    /*
    private void Pattern(ActionEvent event){
        Kaizen_85.newEvent(" pattern set.");
        this.MVControl.setPattern();
    }
     */
    private void startPattern(ActionEvent event) {
        Kaizen_85.newEvent("Pattern button pressed, pattern starting.");
        this.MVControl.setEnableKeyReact(this.nativeKeyActive);
        if (this.MVControl.getPattern().getAmountOfColors() == 0 && this.MVControl.getPattern().getAmountOfDelays() == 0) {
        } else {      // If no colors or delays, skip sending the rectangles' values (except for the key press one)
            this.setLastRectangle();
            this.sendRectangleValues();
        }
        if (this.lastRectangleKeyRect) {
            this.updateKeyPressRect();
        }
        this.MVControl.setKeyReactSettings(this.keyRect);
        this.MVControl.startPattern();
    }

    private void setRectangles(Rectangle newRectangle) {
        //System.out.println("Setting old rectangle values");
        int colorIndex = this.colorRectArr.indexOf(this.lastRect);
        int delayIndex = this.delayRectArr.indexOf(this.lastRect);
        if (colorIndex < 0 && delayIndex > -1) {
            this.delayArr.set(delayIndex, (int) Math.round(this.delaySlider.getValue()));
        } else if (colorIndex > -1 && delayIndex < 0) {
            this.redArr.set(colorIndex, (int) Math.round(this.redSlider.getValue()));
            this.greenArr.set(colorIndex, (int) Math.round(this.greenSlider.getValue()));
            this.blueArr.set(colorIndex, (int) Math.round(this.blueSlider.getValue()));
        } else {
            //AlertBox alert = new AlertBox(new Dimension(400, 200), "Pattern Start Error", "Could not find the last Rectangle in either array... ???");
            //alert.display();
        }
        this.lastRect = newRectangle;
        this.setColorSlidersToCurrentRectangle(newRectangle);
    }

    private void setLastRectangle() {
        //System.out.println("Setting old rectangle values, and setting lastRect to null");
        int colorIndex = this.colorRectArr.indexOf(this.lastRect);
        int delayIndex = this.delayRectArr.indexOf(this.lastRect);
        if (colorIndex < 0 && delayIndex > -1) {
            this.delayArr.set(delayIndex, (int) Math.round(this.delaySlider.getValue()));
        } else if (colorIndex > -1 && delayIndex < 0) {
            this.redArr.set(colorIndex, (int) Math.round(this.redSlider.getValue()));
            this.greenArr.set(colorIndex, (int) Math.round(this.greenSlider.getValue()));
            this.blueArr.set(colorIndex, (int) Math.round(this.blueSlider.getValue()));
        } else {
            //AlertBox alert = new AlertBox(new Dimension(400, 200), "Pattern Start Error", "Could not find the last Rectangle in either array... ???");
            //alert.display();
        }
        this.lastRect = null;
    }

    private void setColorSlidersToCurrentRectangle(Rectangle rect) {
        //System.out.println("Setting color sliders to the current rectangle");
        int indexColor = this.colorRectArr.indexOf(rect);
        int indexDelay = this.delayRectArr.indexOf(rect);
        if (indexColor < 0 && indexDelay > -1) {
            this.setDelaySlider(this.delayArr.get(indexDelay));
            this.updateDelayText();
        } else if (indexColor > -1 && indexDelay < 0) {
            this.setColorSliders(this.redArr.get(indexColor), this.greenArr.get(indexColor), this.blueArr.get(indexColor));
            this.updateColorText();
        } else {
            AlertBox alert = new AlertBox(new Dimension(400, 200), "Pattern Start Error", "Could not find the current Rectangle in either array... ???");
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
        if (!this.lastRectangleKeyRect) {
            this.lastRect.setFill(Color.rgb(red, green, blue));
        } else {
            this.keyRect.setRed(red);
            this.keyRect.setGreen(green);
            this.keyRect.setBlue(blue);
            this.keyRect.updateColors();
        }
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
        if (!this.lastRectangleKeyRect) {
            this.lastRect.setFill(Color.rgb(red, green, blue));
        } else {
            this.keyRect.setRed(red);
            this.keyRect.setGreen(green);
            this.keyRect.setBlue(blue);
            this.keyRect.updateColors();
        }
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
        if (!this.lastRectangleKeyRect) {
            this.lastRect.setFill(Color.rgb(red, green, blue));
        }
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
        if (!this.lastRectangleKeyRect) {
            this.lastRect.setFill(Color.rgb(red, green, blue));
        }
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

    private void updateKeyPressRect() {
        System.out.println("Saving key rect settings");
        this.keyRect.setAll((int) Math.round(this.redSlider.getValue()), (int) Math.round(this.greenSlider.getValue()), (int) Math.round(this.blueSlider.getValue()), (int) Math.round(this.delaySlider.getValue()));
    }
}