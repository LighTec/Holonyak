/*
 * This program was designed for an arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package frontEnd_ViewController;

import app_Controller.Kaizen_85;
import arduinocomms2.PortDropdownMenuFX;
import com.fazecast.jSerialComm.SerialPort;
import java.io.File;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;

/**
 * This class is meant to be called in order to create a dialouge window, which
 * has several text boxes to get info from the user, like the size of the LED
 * strip, and if it is standard RGB or has a extra led, like the RBGW strips.
 * Should also detect if it cannot properly format the data entered, and ask for
 * it again, with the incorrect text boxes being cleared/highlighted.
 *
 *
 * @author kell-gigabyte
 */
public class InitPopup extends MainViewDisplayFX {

    boolean isMatrix;

    boolean isComplete = false;

    String patternFolder = "";
    String logFolder = "";

    int stripLength;
    int stripWidth;
    
    SerialPort currentPort;

    public InitPopup() {

    }

    public void run() {
        createStage();
    }

    protected boolean isComplete() {
        return this.isComplete;
    }

    private void setLogFolder(String s) {
        this.logFolder = s;
    }
    
       private void changePort(SerialPort port){
        this.currentPort = port;
    }

    private TextField LedStrText = new TextField();

    private void createStage() {         // creates the GUI for the popup
        Dialog dialog = new Dialog<>();
        dialog.setTitle("Hello!");
        dialog.setHeaderText("Hello, I'd just like you to let me know about your LED strip.");

        ButtonType confirmButtonType = new ButtonType("Enter", ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType);

        String StripStr = "In a Strip";
        String MatrixStr = "In a Matrix";
        String logFolderStr = "Click here to choose a log location.";

        ToggleButton Strip = new ToggleButton(StripStr);
        ToggleGroup aStrip = new ToggleGroup();
        Strip.setToggleGroup(aStrip);

            PortDropdownMenuFX portChooser = new PortDropdownMenuFX();
            portChooser.refreshMenu();

        Button LogBtn = new Button(logFolderStr);
        
        portChooser.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends SerialPort> observable, SerialPort oldvalue, SerialPort newvalue) -> {
            changePort(newvalue);
            checkData();
            //System.out.println(newvalue.getSystemPortName());
        });

        LogBtn.setOnAction((ActionEvent event) -> {
            String logFolder1;
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedFolder = directoryChooser.showDialog(dialog.getOwner());
            if (selectedFolder == null) {
            } else {
                
                setLogFolder(selectedFolder.getAbsolutePath());
                //System.out.println(selectedFolder.getAbsolutePath());
                checkData();
            }
        });

        aStrip.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) -> {
            if (new_toggle == null) {
                Strip.setText(StripStr);
                isMatrix = false;
            } else {
                Strip.setText(MatrixStr);
                isMatrix = true;
            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        LedStrText.setPromptText("Enter number here");

        grid.add(new Label("Number of LED's total:"), 0, 0);
        grid.add(LedStrText, 1, 0);
        grid.add(new Label("Please pick the port the Arduino is on (usually ttyACM0, or COM1)"), 0, 1);
        grid.add(portChooser, 1, 1);
        grid.add(new Label("Are your LED's in an matrix, or a strip?"), 0, 2);
        grid.add(Strip, 1, 2);
        grid.add(new Label("Please choose a folder for the logs:"), 0, 3);
        grid.add(LogBtn, 1, 3);
        grid.setGridLinesVisible(false);

        this.confirmButton = dialog.getDialogPane().lookupButton(confirmButtonType);
        confirmButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        LedStrText.textProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println("Text box change observed.");
            confirmButton.setDisable(newValue.trim().isEmpty());
            checkData();
        });
        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> LedStrText.requestFocus());
        Kaizen_85.newEvent("Initialization window created, showing.");
        dialog.showAndWait();
    }

    private Node confirmButton;

    /**
     * Ensures the user enters certain data before they can continue
     */
    private void checkData() {    // makes sure the data entered is formattable
        Kaizen_85.newEvent("Data check for init dialog, path folder is " + this.logFolder + " and the current LED text field entry is " + LedStrText.getText());
        boolean intParsable = false;

        try {
            int i = Integer.parseInt(LedStrText.getText());
            intParsable = true;
        } catch (NumberFormatException e) {
            //System.err.println("Number entered on init panel is invalid, please try again.");
        }

        if (intParsable && this.currentPort != null) {
            confirmButton.setDisable(false);
        } else {
            confirmButton.setDisable(true);
        }
        /*
        if (!this.logFolder.isEmpty() && intParsable) {             // USE THIS VERSION FOR FINAL RELEASE
            confirmButton.setDisable(false);
        } else {
            confirmButton.setDisable(true);
        }
         */
    }

    protected Settings SaveSettings() {
        Settings newSettings = new Settings(this.isMatrix, this.patternFolder, this.stripLength, this.stripWidth, this.currentPort);
        //System.out.println(this.currentPort.getSystemPortName());
        Kaizen_85.setLogPath(this.logFolder);
        return newSettings;
    }

}
