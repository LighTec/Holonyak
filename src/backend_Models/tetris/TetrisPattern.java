/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package backend_Models.tetris;

import app_Controller.Kaizen_85;
import backend_Models.GeneralSettingsException;
import backend_Models.Pattern;
import backend_Models.SerialComms;
import com.sun.glass.ui.Application;
import com.sun.media.jfxmediaimpl.platform.Platform;
import frontend_View.Settings;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author kell-gigabyte
 */
public class TetrisPattern extends Pattern implements Runnable {

    private Game myGame;

    private Settings set;

    private byte[] bArr;

    private int ledLength;

    private final long autoFall = 1000;

    private ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    public TetrisPattern(Settings set, SerialComms serial) throws GeneralSettingsException {
        super(set, serial, 9, 0, 0);
        this.set = set;
        this.ledLength = set.getStripLength() * set.getStripWidth();
        this.bArr = new byte[(ledLength * 3) + 1];
        this.bArr[0] = (byte) (100 & 0xFF);
    }

    @Override
    public void start() {

        super.start();
        this.myGame = new Game(set.getStripWidth(), set.getStripLength());
        
        set.getSavedScene().setOnKeyPressed(keyPressed);

        this.ses.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS); // updates every second
    }

    @Override
    public void stop() {
        System.out.println("Stopping tetris pattern");
        this.ses.shutdown();
    }

    public void updateRectangles() {

        //System.out.println("Rectangle Update!");
        Block[] blocks = this.myGame.getArrayBlocks();
        for (int iter = 1; iter < this.ledLength; iter += 3) {
            this.bArr[iter] = (byte) (0 & 0xFF);
            this.bArr[iter + 1] = (byte) (0 & 0xFF);
            this.bArr[iter + 2] = (byte) (0 & 0xFF);
        }

        //System.out.println("Colors cleared");
        int iter = 1;
        for (Block block : blocks) {
            int red = 0;
            int green = 0;
            int blue = 0;
            if (block != null) {
                Color c = block.getColor();
                red = (int) (c.getRed() * 30);
                green = (int) (c.getGreen() * 30);
                blue = (int) (c.getBlue() * 30);
                System.out.println("Pixel " + ((iter - 1) / 3) + "is Color (R,G,B) " + red + " " + green + " " + blue);
            }
            this.bArr[iter] = (byte) (red & 0xFF);
            this.bArr[iter + 1] = (byte) (green & 0xFF);
            this.bArr[iter + 2] = (byte) (blue & 0xFF);

            iter += 3;
        }
        super.sendByteArray(this.bArr);
    }

    private EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            //System.out.println(myGame.toString());

            // Gets the name of the key, aka the unicode character
            String keyName = event.getCode().getName();
            //System.out.println(event.getCode().getName());

            if (keyName.equals("S")) {
                System.out.println("mov down");
                myGame.tick(false, 0);
                updateRectangles();
            }

            if (keyName.equals("A")) {
                System.out.println("mov left");
                myGame.tick(true, 1);
                updateRectangles();
            }

            if (keyName.equals("D")) {
                System.out.println("mov right");
                myGame.tick(true, 2);
                updateRectangles();
            }

            if (keyName.equals("Q")) {
                System.out.println("rot left");
                myGame.tick(true, 3);
                updateRectangles();
            }

            if (keyName.equals("E")) {
                System.out.println("rot right");
                myGame.tick(true, 4);
                updateRectangles();
            }
        }
    };

    @Override
    public void run() {
        myGame.tick(false, -1);
        updateRectangles();
        Kaizen_85.newEvent("Drop speed for step: " + autoFall);
    }
}
