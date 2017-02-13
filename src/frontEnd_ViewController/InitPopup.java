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

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

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
public class InitPopup extends MainViewDisplayFX
{

    boolean isMatrix;
    boolean isRGBW;               //if the LED strip supports an extra addressable white LED

    boolean isComplete = false;

    String patternFolder;

    int stripLength;
    int stripWidnth;

    public InitPopup() {

    }

    public void run() {
        createStage();
    }

    protected boolean isComplete() {
        return this.isComplete;
    }

    private void createStage() {         // creates the GUI for the popup
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Just need a little bit of info");
        dialog.setHeaderText("Hello, I'd just like you to let me know about your LED strip.");

        ButtonType confirmButtonType = new ButtonType("Enter", ButtonData.APPLY);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType);

        String StripStr = "In a Strip";
        String MatrixStr = "In a Matrix";
        String y = "Yes";
        String n = "No";

        ToggleButton RGBW = new ToggleButton(n);
        ToggleGroup aRGBW = new ToggleGroup();
        RGBW.setToggleGroup(aRGBW);
        ToggleButton Strip = new ToggleButton(StripStr);
        ToggleGroup aStrip = new ToggleGroup();
        Strip.setToggleGroup(aStrip);

        aStrip.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle toggle, Toggle new_toggle) {
                if (new_toggle == null) {
                    Strip.setText(StripStr);
                    isMatrix = false;
                } else {
                    Strip.setText(MatrixStr);
                    isMatrix = true;
                }

            }
        });

        aRGBW.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle toggle, Toggle new_toggle) {
                if (new_toggle == null) {
                    RGBW.setText(n);
                    isRGBW = false;
                } else {
                    RGBW.setText(y);
                    isRGBW = true;
                }

            }
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Enter number here");

        grid.add(new Label("Number of LED's total:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Does it have an addressable white pixel?"), 0, 1);
        grid.add(RGBW, 1, 1);
        grid.add(new Label("Are your LED's in an matrix, or a strip?"), 0, 2, 1, 2);
        grid.add(Strip, 1, 2);

        Node confirmButton = dialog.getDialogPane().lookupButton(confirmButtonType);
        confirmButton.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            //System.out.println("Text box change observed.");
            confirmButton.setDisable(newValue.trim().isEmpty());
            boolean b = checkData(username.getText());
            confirmButton.setDisable(!b);
            //System.out.println(b);
        });
        dialog.getDialogPane().setContent(grid);

// Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        dialog.showAndWait();
    }

    private boolean checkData(String s) {    // makes sure the data entered is formattable
        try {
            int i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            //System.err.println("Number entered on init panel is invalid, please try again.");
            return false;
        }

    }
    
    protected Settings SaveSettings(){
        Settings newSettings = new Settings(this.isRGBW,this.isMatrix,this.patternFolder,this.stripLength,this.stripWidnth);
        return newSettings;
    }

}
