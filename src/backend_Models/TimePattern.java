/*
 * This program was designed for a arduino Uno, or any similar device that can communicate over 
 * serial USB.
 * ---------------------------------------------------------
 * This program is designed to have the arduino itself run custom code, so do not expect
 * that you can run this on any random arduino. The code required to get the arduino to work
 * can be found as a .ino file recognizable by the arduino IDE, within the zip package of this
 * program.
 */
package backend_Models;

import app_Controller.Kaizen_85;
import frontend_View.Settings;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author kell-gigabyte
 */
public class TimePattern extends Pattern implements Runnable {

    private ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    public TimePattern(Settings set, SerialComms serial) throws GeneralSettingsException {
        super(set, serial, 2, 0, 0, 1, 0);
        Kaizen_85.newEvent("Date/Time Pattern Set.");
    }

    @Override
    public void start() {
        this.ses.scheduleAtFixedRate(this, 0, 1, TimeUnit.SECONDS); // updates every second
        super.start();
    }

    @Override
    public void stop() {
        this.ses.shutdown();
    }

    @Override
    public void run() {
        int hour = Integer.parseInt(new SimpleDateFormat("HH").format(Calendar.getInstance().getTime()));
        int minute = Integer.parseInt(new SimpleDateFormat("mm").format(Calendar.getInstance().getTime()));
        int second = Integer.parseInt(new SimpleDateFormat("ss").format(Calendar.getInstance().getTime()));
        //System.out.println(hour + ":" + minute + ":" + second);
        double redUnformatted = (double) hour * 2.5;
        int red = (int) Math.round(redUnformatted);
        double greenUnformatted = (double) minute * 1.75;
        int green = (int) Math.round(greenUnformatted);
        double blueUnformatted = (double) second * 2;
        int blue = (int) Math.round(blueUnformatted);
        //System.out.println("Red: " + red + " Green: " + green + " Blue " + blue);
        this.setUpdateColor(red, green, blue, 0);
        this.updatePattern();
    }
}
